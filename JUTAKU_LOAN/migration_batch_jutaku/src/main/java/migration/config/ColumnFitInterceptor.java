package migration.config;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;

import lombok.extern.slf4j.Slf4j;

/**
 * Automatically fits every INSERT parameter to its real target column definition,
 * read live from the database (user_tab_columns), for ALL target tables:
 * <ul>
 *   <li>String values are truncated to the column's byte length (Shift-JIS / MS932)
 *       - prevents ORA-12899 (value too large).</li>
 *   <li>Numeric values (BigDecimal, BigInteger, Long, Integer, Short, Byte, Double,
 *       Float) are clamped to NUMBER(precision, scale)
 *       - prevents ORA-01438 (value larger than precision).</li>
 * </ul>
 * Column metadata and the per-statement property→column mapping are cached.
 * If metadata cannot be read, the insert proceeds unchanged (fail-safe).
 */
@Slf4j
@Intercepts({
    @Signature(type = Executor.class, method = "update",
               args = {MappedStatement.class, Object.class})
})
public class ColumnFitInterceptor implements Interceptor {

    private static final Charset SJIS = Charset.forName("MS932");

    private final DataSource dataSource;
    /** table name -> (column name -> limit) */
    private final Map<String, Map<String, ColLimit>> tableMeta = new ConcurrentHashMap<>();
    /** statement id -> (property name -> column name) */
    private final Map<String, Map<String, String>> stmtP2C = new ConcurrentHashMap<>();

    public ColumnFitInterceptor(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static final class ColLimit {
        String type;
        int byteLen;
        int precision = -1;
        int scale;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
        Object param = invocation.getArgs()[1];
        if (param != null && ms.getSqlCommandType() == SqlCommandType.INSERT) {
            try {
                fit(ms, param);
            } catch (RuntimeException e) {
                log.warn("ColumnFit skipped for {}: {}", ms.getId(), e.getMessage());
            }
        }
        return invocation.proceed();
    }

    private void fit(MappedStatement ms, Object param) {
        BoundSql bs = ms.getBoundSql(param);
        String sql = bs.getSql();
        String table = parseTable(sql);
        if (table == null) {
            return;
        }
        Map<String, String> p2c = stmtP2C.computeIfAbsent(ms.getId(),
                id -> buildProperty2Column(sql, bs.getParameterMappings()));
        Map<String, ColLimit> meta = tableMeta.computeIfAbsent(table, this::loadMeta);
        if (meta.isEmpty() || p2c.isEmpty()) {
            return;
        }
        MetaObject mo = ms.getConfiguration().newMetaObject(param);
        for (Map.Entry<String, String> e : p2c.entrySet()) {
            String property = e.getKey();
            ColLimit cl = meta.get(e.getValue());
            if (cl == null || !mo.hasGetter(property)) {
                continue;
            }
            Object v = mo.getValue(property);
            if (v == null || !mo.hasSetter(property)) {
                continue;
            }
            Object fitted = fitValue(v, cl, table, e.getValue());
            if (fitted != v) {
                mo.setValue(property, fitted);
            }
        }
    }

    private Object fitValue(Object v, ColLimit cl, String table, String column) {
        if (v instanceof String && isCharType(cl.type) && cl.byteLen > 0) {
            String s = (String) v;
            String tr = substringByByte(s, cl.byteLen);
            if (!tr.equals(s)) {
                log.warn("TRUNCATE {}.{}: \"{}\" -> \"{}\" ({}({}))",
                        table, column, s, tr, cl.type, cl.byteLen);
                return tr;
            }
        } else if (v instanceof Number && "NUMBER".equalsIgnoreCase(cl.type) && cl.precision > 0) {
            // MBG maps NUMBER(p,0) to Short/Integer/Long (not BigDecimal), so clamp every
            // numeric type - otherwise an oversized int slips through as ORA-01438.
            BigDecimal bd = toBigDecimal((Number) v);
            BigDecimal c = clamp(bd, cl.precision, Math.max(cl.scale, 0));
            if (c.compareTo(bd) != 0) {
                log.warn("CLAMP {}.{}: {} -> {} (NUMBER({},{}))",
                        table, column, bd.toPlainString(), c.toPlainString(), cl.precision, cl.scale);
                return convertToType(c, v.getClass());
            }
        }
        return v;
    }

    /** Widen any Number to BigDecimal without losing digits. */
    private static BigDecimal toBigDecimal(Number n) {
        if (n instanceof BigDecimal) {
            return (BigDecimal) n;
        }
        if (n instanceof java.math.BigInteger) {
            return new BigDecimal((java.math.BigInteger) n);
        }
        if (n instanceof Double || n instanceof Float) {
            return BigDecimal.valueOf(n.doubleValue());
        }
        return BigDecimal.valueOf(n.longValue());
    }

    /** Convert the clamped BigDecimal back to the original property's numeric type. */
    private static Object convertToType(BigDecimal c, Class<?> type) {
        if (type == BigDecimal.class) {
            return c;
        }
        if (type == java.math.BigInteger.class) {
            return c.toBigInteger();
        }
        if (type == Long.class) {
            return c.longValue();
        }
        if (type == Integer.class) {
            return c.intValue();
        }
        if (type == Short.class) {
            return c.shortValue();
        }
        if (type == Byte.class) {
            return c.byteValue();
        }
        if (type == Double.class) {
            return c.doubleValue();
        }
        if (type == Float.class) {
            return c.floatValue();
        }
        return c;
    }

    /** Extract the table name following INSERT INTO. */
    private static String parseTable(String sql) {
        int i = sql.toUpperCase().indexOf("INSERT INTO");
        if (i < 0) {
            return null;
        }
        String rest = sql.substring(i + "INSERT INTO".length()).trim();
        int end = 0;
        while (end < rest.length()
                && !Character.isWhitespace(rest.charAt(end))
                && rest.charAt(end) != '(') {
            end++;
        }
        String t = rest.substring(0, end).trim();
        if (t.contains(".")) {
            t = t.substring(t.lastIndexOf('.') + 1);
        }
        return t.replace("\"", "").trim();
    }

    /** Zip the INSERT column list with the parameter mappings (positional). */
    private static Map<String, String> buildProperty2Column(String sql, List<ParameterMapping> pms) {
        Map<String, String> map = new HashMap<>();
        int lp = sql.indexOf('(');
        int valIdx = sql.toUpperCase().indexOf("VALUES");
        if (lp < 0 || valIdx < 0) {
            return map;
        }
        int rp = sql.lastIndexOf(')', valIdx);
        if (rp <= lp) {
            return map;
        }
        String[] cols = sql.substring(lp + 1, rp).split(",");
        for (int i = 0; i < pms.size() && i < cols.length; i++) {
            map.put(pms.get(i).getProperty(), cols[i].trim().replace("\"", ""));
        }
        return map;
    }

    private Map<String, ColLimit> loadMeta(String table) {
        Map<String, ColLimit> m = new HashMap<>();
        String q = "SELECT column_name, data_type, data_length, data_precision, data_scale "
                 + "FROM user_tab_columns WHERE table_name = ?";
        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(q)) {
            ps.setString(1, table);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ColLimit cl = new ColLimit();
                    cl.type = rs.getString("DATA_TYPE");
                    cl.byteLen = rs.getInt("DATA_LENGTH");
                    int p = rs.getInt("DATA_PRECISION");
                    cl.precision = rs.wasNull() ? -1 : p;
                    int s = rs.getInt("DATA_SCALE");
                    cl.scale = rs.wasNull() ? 0 : s;
                    m.put(rs.getString("COLUMN_NAME").trim(), cl);
                }
            }
            log.info("ColumnFit: loaded metadata for table {} ({} columns)", table, m.size());
        } catch (Exception e) {
            log.warn("ColumnFit: could not load metadata for {}: {}", table, e.getMessage());
            return Collections.emptyMap();
        }
        return m;
    }

    private static boolean isCharType(String t) {
        return "VARCHAR2".equalsIgnoreCase(t) || "CHAR".equalsIgnoreCase(t)
                || "NVARCHAR2".equalsIgnoreCase(t) || "NCHAR".equalsIgnoreCase(t);
    }

    private static BigDecimal clamp(BigDecimal v, int precision, int scale) {
        BigDecimal r = v;
        if (r.scale() > scale) {
            r = r.setScale(scale, RoundingMode.DOWN);
        }
        BigDecimal max = BigDecimal.TEN.pow(precision).subtract(BigDecimal.ONE).movePointLeft(scale);
        if (r.compareTo(max) > 0) {
            return max;
        }
        if (r.compareTo(max.negate()) < 0) {
            return max.negate();
        }
        return r;
    }

    /** Truncate to fit maxBytes in Shift-JIS; only modifies when over the limit. */
    private static String substringByByte(String text, int maxBytes) {
        if (text == null || text.getBytes(SJIS).length <= maxBytes) {
            return text;
        }
        CharsetEncoder enc = SJIS.newEncoder();
        StringBuilder out = new StringBuilder();
        int used = 0;
        for (int i = 0; i < text.length();) {
            int cp = text.codePointAt(i);
            String ch = new String(Character.toChars(cp));
            if (enc.canEncode(ch)) {
                int blen = ch.getBytes(SJIS).length;
                if (used + blen > maxBytes) {
                    break;
                }
                out.append(ch);
                used += blen;
            }
            i += Character.charCount(cp);
        }
        return out.toString();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        // no configurable properties
    }
}
