package migration.domain.target;

/**
 * Target: 申込担保情報ＰＤＦ (Collateral-info PDF metadata) - ITF_SMS
 * 申込番号 first digit 2→3; 申込目的 converted (10/15→10, 20/30→20) by service.
 */
public class 申込担保情報ＰＤＦTarget {

    private String 申込番号;       // VARCHAR2(12)  PK NOT NULL
    private String 申込目的;       // VARCHAR2(2)   PK NOT NULL
    private String ファイル種別;   // VARCHAR2(1)   NOT NULL  1:担保情報 / 2:物件明細情報
    private String ファイル名称;   // VARCHAR2(100)
    private String データファイル名; // VARCHAR2(128)

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String v) { this.申込番号 = v; }

    public String get申込目的() { return 申込目的; }
    public void set申込目的(String v) { this.申込目的 = v; }

    public String getファイル種別() { return ファイル種別; }
    public void setファイル種別(String v) { this.ファイル種別 = v; }

    public String getファイル名称() { return ファイル名称; }
    public void setファイル名称(String v) { this.ファイル名称 = v; }

    public String getデータファイル名() { return データファイル名; }
    public void setデータファイル名(String v) { this.データファイル名 = v; }
}
