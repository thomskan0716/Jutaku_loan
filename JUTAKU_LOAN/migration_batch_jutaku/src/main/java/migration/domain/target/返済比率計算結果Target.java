package migration.domain.target;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Target: 返済比率計算結果 (Repayment-ratio calculation result) - ITF_SMS.
 * 申込番号 first digit 2→3; 申込目的 converted by service. Other columns pass through.
 */
public class 返済比率計算結果Target {

    private String 申込番号;     // VARCHAR2(12)
    private String 申込目的;     // VARCHAR2(2)
    private String イベント;     // VARCHAR2(50)
    private Date イベント日時;   // DATE

    // --- base group ---
    private BigDecimal 計算年収;
    private BigDecimal 借入総額;
    private BigDecimal 無担保借入額;
    private BigDecimal 年間返済額;
    private String 総額借入比率;
    private String 無担保借入比率;
    private String 総額返済比率;
    private BigDecimal 無担保年間返済額;

    // --- 申告 group ---
    private BigDecimal 申告借入総額;
    private BigDecimal 申告無担保借入額;
    private BigDecimal 申告年間返済額;
    private String 申告総額借入比率;
    private String 申告無担保借入比率;
    private String 申告総額返済比率;
    private BigDecimal 申告無担保年間返済額;

    // --- 今回 group ---
    private BigDecimal 今回借入総額;
    private BigDecimal 今回無担保借入額;
    private BigDecimal 今回年間返済額;
    private String 今回総額借入比率;
    private String 今回無担保借入比率;
    private String 今回総額返済比率;
    private BigDecimal 今回無担保年間返済額;

    // --- 自行 group ---
    private BigDecimal 自行借入総額;
    private BigDecimal 自行無担保借入額;
    private BigDecimal 自行年間返済額;
    private String 自行総額借入比率;
    private String 自行無担保借入比率;
    private String 自行総額返済比率;
    private BigDecimal 自行無担保年間返済額;

    // --- ＫＳＣ group ---
    private BigDecimal ＫＳＣ借入総額;
    private BigDecimal ＫＳＣ無担保借入額;
    private BigDecimal ＫＳＣ年間返済額;
    private String ＫＳＣ総額借入比率;
    private String ＫＳＣ無担保借入比率;
    private String ＫＳＣ総額返済比率;
    private BigDecimal ＫＳＣ無担保年間返済額;

    // --- ＣＩＣ group ---
    private BigDecimal ＣＩＣ借入総額;
    private BigDecimal ＣＩＣ無担保借入額;
    private BigDecimal ＣＩＣ年間返済額;
    private String ＣＩＣ総額借入比率;
    private String ＣＩＣ無担保借入比率;
    private String ＣＩＣ総額返済比率;
    private BigDecimal ＣＩＣ無担保年間返済額;

    // --- ＣＣＢ group ---
    private BigDecimal ＣＣＢ借入総額;
    private BigDecimal ＣＣＢ無担保借入額;
    private BigDecimal ＣＣＢ年間返済額;
    private String ＣＣＢ総額借入比率;
    private String ＣＣＢ無担保借入比率;
    private String ＣＣＢ総額返済比率;
    private BigDecimal ＣＣＢ無担保年間返済額;

    // --- ＪＩＣＣ group ---
    private BigDecimal ＪＩＣＣ借入総額;
    private BigDecimal ＪＩＣＣ無担保借入額;
    private BigDecimal ＪＩＣＣ年間返済額;
    private String ＪＩＣＣ総額借入比率;
    private String ＪＩＣＣ無担保借入比率;
    private String ＪＩＣＣ総額返済比率;
    private BigDecimal ＪＩＣＣ無担保年間返済額;

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String v) { this.申込番号 = v; }

    public String get申込目的() { return 申込目的; }
    public void set申込目的(String v) { this.申込目的 = v; }

    public String getイベント() { return イベント; }
    public void setイベント(String v) { this.イベント = v; }

    public Date getイベント日時() { return イベント日時; }
    public void setイベント日時(Date v) { this.イベント日時 = v; }

    public BigDecimal get計算年収() { return 計算年収; }
    public void set計算年収(BigDecimal v) { this.計算年収 = v; }

    public BigDecimal get借入総額() { return 借入総額; }
    public void set借入総額(BigDecimal v) { this.借入総額 = v; }

    public BigDecimal get無担保借入額() { return 無担保借入額; }
    public void set無担保借入額(BigDecimal v) { this.無担保借入額 = v; }

    public BigDecimal get年間返済額() { return 年間返済額; }
    public void set年間返済額(BigDecimal v) { this.年間返済額 = v; }

    public String get総額借入比率() { return 総額借入比率; }
    public void set総額借入比率(String v) { this.総額借入比率 = v; }

    public String get無担保借入比率() { return 無担保借入比率; }
    public void set無担保借入比率(String v) { this.無担保借入比率 = v; }

    public String get総額返済比率() { return 総額返済比率; }
    public void set総額返済比率(String v) { this.総額返済比率 = v; }

    public BigDecimal get無担保年間返済額() { return 無担保年間返済額; }
    public void set無担保年間返済額(BigDecimal v) { this.無担保年間返済額 = v; }

    public BigDecimal get申告借入総額() { return 申告借入総額; }
    public void set申告借入総額(BigDecimal v) { this.申告借入総額 = v; }

    public BigDecimal get申告無担保借入額() { return 申告無担保借入額; }
    public void set申告無担保借入額(BigDecimal v) { this.申告無担保借入額 = v; }

    public BigDecimal get申告年間返済額() { return 申告年間返済額; }
    public void set申告年間返済額(BigDecimal v) { this.申告年間返済額 = v; }

    public String get申告総額借入比率() { return 申告総額借入比率; }
    public void set申告総額借入比率(String v) { this.申告総額借入比率 = v; }

    public String get申告無担保借入比率() { return 申告無担保借入比率; }
    public void set申告無担保借入比率(String v) { this.申告無担保借入比率 = v; }

    public String get申告総額返済比率() { return 申告総額返済比率; }
    public void set申告総額返済比率(String v) { this.申告総額返済比率 = v; }

    public BigDecimal get申告無担保年間返済額() { return 申告無担保年間返済額; }
    public void set申告無担保年間返済額(BigDecimal v) { this.申告無担保年間返済額 = v; }

    public BigDecimal get今回借入総額() { return 今回借入総額; }
    public void set今回借入総額(BigDecimal v) { this.今回借入総額 = v; }

    public BigDecimal get今回無担保借入額() { return 今回無担保借入額; }
    public void set今回無担保借入額(BigDecimal v) { this.今回無担保借入額 = v; }

    public BigDecimal get今回年間返済額() { return 今回年間返済額; }
    public void set今回年間返済額(BigDecimal v) { this.今回年間返済額 = v; }

    public String get今回総額借入比率() { return 今回総額借入比率; }
    public void set今回総額借入比率(String v) { this.今回総額借入比率 = v; }

    public String get今回無担保借入比率() { return 今回無担保借入比率; }
    public void set今回無担保借入比率(String v) { this.今回無担保借入比率 = v; }

    public String get今回総額返済比率() { return 今回総額返済比率; }
    public void set今回総額返済比率(String v) { this.今回総額返済比率 = v; }

    public BigDecimal get今回無担保年間返済額() { return 今回無担保年間返済額; }
    public void set今回無担保年間返済額(BigDecimal v) { this.今回無担保年間返済額 = v; }

    public BigDecimal get自行借入総額() { return 自行借入総額; }
    public void set自行借入総額(BigDecimal v) { this.自行借入総額 = v; }

    public BigDecimal get自行無担保借入額() { return 自行無担保借入額; }
    public void set自行無担保借入額(BigDecimal v) { this.自行無担保借入額 = v; }

    public BigDecimal get自行年間返済額() { return 自行年間返済額; }
    public void set自行年間返済額(BigDecimal v) { this.自行年間返済額 = v; }

    public String get自行総額借入比率() { return 自行総額借入比率; }
    public void set自行総額借入比率(String v) { this.自行総額借入比率 = v; }

    public String get自行無担保借入比率() { return 自行無担保借入比率; }
    public void set自行無担保借入比率(String v) { this.自行無担保借入比率 = v; }

    public String get自行総額返済比率() { return 自行総額返済比率; }
    public void set自行総額返済比率(String v) { this.自行総額返済比率 = v; }

    public BigDecimal get自行無担保年間返済額() { return 自行無担保年間返済額; }
    public void set自行無担保年間返済額(BigDecimal v) { this.自行無担保年間返済額 = v; }

    public BigDecimal getＫＳＣ借入総額() { return ＫＳＣ借入総額; }
    public void setＫＳＣ借入総額(BigDecimal v) { this.ＫＳＣ借入総額 = v; }

    public BigDecimal getＫＳＣ無担保借入額() { return ＫＳＣ無担保借入額; }
    public void setＫＳＣ無担保借入額(BigDecimal v) { this.ＫＳＣ無担保借入額 = v; }

    public BigDecimal getＫＳＣ年間返済額() { return ＫＳＣ年間返済額; }
    public void setＫＳＣ年間返済額(BigDecimal v) { this.ＫＳＣ年間返済額 = v; }

    public String getＫＳＣ総額借入比率() { return ＫＳＣ総額借入比率; }
    public void setＫＳＣ総額借入比率(String v) { this.ＫＳＣ総額借入比率 = v; }

    public String getＫＳＣ無担保借入比率() { return ＫＳＣ無担保借入比率; }
    public void setＫＳＣ無担保借入比率(String v) { this.ＫＳＣ無担保借入比率 = v; }

    public String getＫＳＣ総額返済比率() { return ＫＳＣ総額返済比率; }
    public void setＫＳＣ総額返済比率(String v) { this.ＫＳＣ総額返済比率 = v; }

    public BigDecimal getＫＳＣ無担保年間返済額() { return ＫＳＣ無担保年間返済額; }
    public void setＫＳＣ無担保年間返済額(BigDecimal v) { this.ＫＳＣ無担保年間返済額 = v; }

    public BigDecimal getＣＩＣ借入総額() { return ＣＩＣ借入総額; }
    public void setＣＩＣ借入総額(BigDecimal v) { this.ＣＩＣ借入総額 = v; }

    public BigDecimal getＣＩＣ無担保借入額() { return ＣＩＣ無担保借入額; }
    public void setＣＩＣ無担保借入額(BigDecimal v) { this.ＣＩＣ無担保借入額 = v; }

    public BigDecimal getＣＩＣ年間返済額() { return ＣＩＣ年間返済額; }
    public void setＣＩＣ年間返済額(BigDecimal v) { this.ＣＩＣ年間返済額 = v; }

    public String getＣＩＣ総額借入比率() { return ＣＩＣ総額借入比率; }
    public void setＣＩＣ総額借入比率(String v) { this.ＣＩＣ総額借入比率 = v; }

    public String getＣＩＣ無担保借入比率() { return ＣＩＣ無担保借入比率; }
    public void setＣＩＣ無担保借入比率(String v) { this.ＣＩＣ無担保借入比率 = v; }

    public String getＣＩＣ総額返済比率() { return ＣＩＣ総額返済比率; }
    public void setＣＩＣ総額返済比率(String v) { this.ＣＩＣ総額返済比率 = v; }

    public BigDecimal getＣＩＣ無担保年間返済額() { return ＣＩＣ無担保年間返済額; }
    public void setＣＩＣ無担保年間返済額(BigDecimal v) { this.ＣＩＣ無担保年間返済額 = v; }

    public BigDecimal getＣＣＢ借入総額() { return ＣＣＢ借入総額; }
    public void setＣＣＢ借入総額(BigDecimal v) { this.ＣＣＢ借入総額 = v; }

    public BigDecimal getＣＣＢ無担保借入額() { return ＣＣＢ無担保借入額; }
    public void setＣＣＢ無担保借入額(BigDecimal v) { this.ＣＣＢ無担保借入額 = v; }

    public BigDecimal getＣＣＢ年間返済額() { return ＣＣＢ年間返済額; }
    public void setＣＣＢ年間返済額(BigDecimal v) { this.ＣＣＢ年間返済額 = v; }

    public String getＣＣＢ総額借入比率() { return ＣＣＢ総額借入比率; }
    public void setＣＣＢ総額借入比率(String v) { this.ＣＣＢ総額借入比率 = v; }

    public String getＣＣＢ無担保借入比率() { return ＣＣＢ無担保借入比率; }
    public void setＣＣＢ無担保借入比率(String v) { this.ＣＣＢ無担保借入比率 = v; }

    public String getＣＣＢ総額返済比率() { return ＣＣＢ総額返済比率; }
    public void setＣＣＢ総額返済比率(String v) { this.ＣＣＢ総額返済比率 = v; }

    public BigDecimal getＣＣＢ無担保年間返済額() { return ＣＣＢ無担保年間返済額; }
    public void setＣＣＢ無担保年間返済額(BigDecimal v) { this.ＣＣＢ無担保年間返済額 = v; }

    public BigDecimal getＪＩＣＣ借入総額() { return ＪＩＣＣ借入総額; }
    public void setＪＩＣＣ借入総額(BigDecimal v) { this.ＪＩＣＣ借入総額 = v; }

    public BigDecimal getＪＩＣＣ無担保借入額() { return ＪＩＣＣ無担保借入額; }
    public void setＪＩＣＣ無担保借入額(BigDecimal v) { this.ＪＩＣＣ無担保借入額 = v; }

    public BigDecimal getＪＩＣＣ年間返済額() { return ＪＩＣＣ年間返済額; }
    public void setＪＩＣＣ年間返済額(BigDecimal v) { this.ＪＩＣＣ年間返済額 = v; }

    public String getＪＩＣＣ総額借入比率() { return ＪＩＣＣ総額借入比率; }
    public void setＪＩＣＣ総額借入比率(String v) { this.ＪＩＣＣ総額借入比率 = v; }

    public String getＪＩＣＣ無担保借入比率() { return ＪＩＣＣ無担保借入比率; }
    public void setＪＩＣＣ無担保借入比率(String v) { this.ＪＩＣＣ無担保借入比率 = v; }

    public String getＪＩＣＣ総額返済比率() { return ＪＩＣＣ総額返済比率; }
    public void setＪＩＣＣ総額返済比率(String v) { this.ＪＩＣＣ総額返済比率 = v; }

    public BigDecimal getＪＩＣＣ無担保年間返済額() { return ＪＩＣＣ無担保年間返済額; }
    public void setＪＩＣＣ無担保年間返済額(BigDecimal v) { this.ＪＩＣＣ無担保年間返済額 = v; }
}
