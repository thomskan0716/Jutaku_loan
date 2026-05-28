package migration.common;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MyUtil {

    private static final Charset SJIS = Charset.forName("Windows-31J");
    // インスタンス化禁止
    private MyUtil() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static Timestamp convDate(Integer intDate, Integer intTime) {
        if (intDate == null || intTime == null)
            return null;

        String strDate = String.format("%08d", intDate); // YYYYMMDD
        String strTime = String.format("%06d", intTime); // HHMMSS
        String dateTimeStr = strDate + strTime;

        try {
            Date parsedDate = new SimpleDateFormat("yyyyMMddHHmmss").parse(dateTimeStr);
            return new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            return null;
        }
    }

    public static Timestamp convDate(Integer intDate) {
        if (intDate == null)
            return null;

        String strDate = String.format("%08d", intDate); // YYYYMMDD
        try {
            Date parsedDate = new SimpleDateFormat("yyyyMMdd").parse(strDate);
            return new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            return null;
        }
    }

    public static String convString(Long arg) {
        return (arg != null) ? arg.toString() : null;
    }

    public static String convString(Integer arg) {
        return (arg != null) ? arg.toString() : null;
    }

    public static String convString(Short arg) {
        return (arg != null) ? arg.toString() : null;
    }

    public static Short convShort(String arg) {
        try {
            return (arg != null) ? Short.valueOf(arg) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Short convShort(Integer arg) {
        return (arg != null) ? arg.shortValue() : null;
    }

    public static Integer convInteger(String str) {
        try {
            return (str != null) ? Integer.valueOf(str) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Long convLong(Integer arg) {
        return (arg != null) ? arg.longValue() : null;
    }

    public static Long convLong(String arg) {
        try {
            return (arg != null) ? Long.valueOf(arg) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * 文字列がnullまたは空白のみの場合にtrueを返す。
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * 文字列がnullでも空白でもない場合にtrueを返す。
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * Shortがnullまたは0の場合にtrueを返す。
     */
    public static boolean isBlank(Short value) {
        return value == null || value == 0;
    }

    /**
     * Shortがnullでも0でもない場合にtrueを返す。
     */
    public static boolean isNotBlank(Short value) {
        return !isBlank(value);
    }

    /**
     * Integerがnullまたは0の場合にtrueを返す。
     */
    public static boolean isBlank(Integer value) {
        return value == null || value == 0;
    }

    /**
     * Integerがnullでも0でもない場合にtrueを返す。
     */
    public static boolean isNotBlank(Integer value) {
        return !isBlank(value);
    }

    /**
     * Longがnullまたは0Lの場合にtrueを返す。
     */
    public static boolean isBlank(Long value) {
        return value == null || value == 0L;
    }

    /**
     * Longがnullでも0Lでもない場合にtrueを返す。
     */
    public static boolean isNotBlank(Long value) {
        return !isBlank(value);
    }

    /**
     * 姓と名を結合して氏名を返す。
     *
     * @param family 姓
     * @param first  名
     * @return 氏名
     */
    public static String concatName(String family, String first) {
        String result = (family != null ? family : "") + (first != null ? first : "");
        return result;
    }

    /**
     * 郵便番号1と郵便番号2を結合して郵便番号を返す。
     *
     * @param post1 郵便番号前半
     * @param post2 郵便番号後半
     * @return 郵便番号
     */
    public static String concatPost(String post1, String post2) {
        String result = (post1 != null ? post1 : "") + (post2 != null ? post2 : "");
        return result;
    }

    /**
     * 住所1と住所2を結合して住所を返す。
     *
     * @param address1 住所1
     * @param address2 住所2
     * @return 住所
     */
    public static String connatAddress2(String address1, String address2) {
        String result = (address1 != null ? address1 : "") + (address2 != null ? address2 : "");
        if (isBlank(result)) {
            result = null;
        }
        return result;
    }

    /**
     * 住所1、住所2、住所3を結合して住所を返す。
     *
     * @param address1 住所1
     * @param address2 住所2
     * @param address3 住所3
     * @return 住所
     */
    public static String connatAddress3(String address1, String address2, String address3) {
        String result = (address1 != null ? address1 : "") + (address2 != null ? address2 : "") + (address3 != null ? address3 : "");
        if (isBlank(result)) {
            result = null;
        }
        return result;
    }

    /**
     * 電話番号1、電話番号2、電話番号3を結合して電話番号を返す。
     *
     * @param tel1 電話番号1
     * @param tel2 電話番号2
     * @param tel3 電話番号3
     * @return 電話番号
     */
    public static String connetTel(String tel1, String tel2, String tel3) {
        String reuslt = "";
        if (tel1 != null && tel2 != null && tel3 != null) {
            reuslt = (tel1 != null ? tel1 : "")
                    + "-" + (tel2 != null ? tel2 : "")
                    + "-" + (tel3 != null ? tel3 : "");

        }
        return reuslt;
    }

    /**
     * 生年月日を和暦から西暦に変換してyyyyMMdd形式の文字列を返す。
     *
     * @param era   元号コード
     * @param year  年
     * @param month 月
     * @param day   日
     * @return yyyyMMdd形式の生年月日文字列
     */
    public static String connatBirthday(String era, Short year, Short month, Short day) {
        int baseYear;

        try {
            switch (era) {
                case "5":
                    baseYear = 2018; // 2019 - 1
                    break;
                case "4":
                    baseYear = 1988; // 1989 - 1
                    break;
                case "3":
                    baseYear = 1925; // 1926 - 1
                    break;
                case "2":
                    baseYear = 1911; // 1912 - 1
                    break;
                case "1":
                    baseYear = 1867; // 1868 - 1
                    break;
                default:
                    return null; // 不明な元号
            }

            LocalDate date = LocalDate.of(baseYear + year, month, day);
            return date.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        } catch (Exception e) {
            return null; // 変換できない場合
        }

    }

    /**
     * @param year
     * @param month
     * @return
     */
    public static Integer calcMonth(Short year, Short month) {
        if (year == null) {
            year = 0;
        }
        if (month == null) {
            month = 0;
        }

        Integer calcMonth = 0;
        try {
            calcMonth = year * 12 + month;
            if (calcMonth > 999) {
                calcMonth = 999;
            }
        } catch (Exception e) {
            return null;
        }

        return calcMonth;
    }

    /**
     * 数値を指定桁数でゼロパディングした文字列を返す。
     *
     * @param number 数値
     * @param length 桁数
     * @return ゼロパディングされた文字列
     */
    public static String zeroPad(int number, int length) {
        return String.format("%0" + length + "d", number);
    }

    /**
     * 文字列をゼロパディングした文字列を返す。
     *
     * @param input  入力文字列
     * @param length 桁数
     * @return ゼロパディングされた文字列
     */
    public static String zeroPad(String input, int length) {
        if (input == null) {
            input = "";
        }
        if (input.length() >= length) {
            return input;
        }
        return "0".repeat(length - input.length()) + input;
    }

    public static String substringByByte(String text, int limit) {

        if (text == null || limit <= 0) return "";
        CharsetEncoder enc = SJIS.newEncoder();

        StringBuilder out = new StringBuilder();
        int used = 0;

        for (int i = 0; i < text.length(); ) {
            int cp = text.codePointAt(i);
            String ch = new String(Character.toChars(cp));

            // SJISでエンコードできない文字はスキップ
            if (!enc.canEncode(ch)) {
                i += Character.charCount(cp);
                // スキップした文字を'?'に置換する場合はこちら
                // if (used + 1 <= limit) { out.append('?'); used += 1; }
                continue;
            }

            int blen = ch.getBytes(SJIS).length; // 半角=1, 全角=2
            if (used + blen > limit) break;

            out.append(ch);
            used += blen;
            i += Character.charCount(cp);
        }
        return out.toString();
    }
}
