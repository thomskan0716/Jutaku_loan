package migration.domain.source;

/**
 * Source: 申込担保回答ＰＤＦ (Collateral-answer PDF metadata) - SZB_SMS
 * Keyed by (申込番号, 申込目的, ファイル種類). 1:N per (申込番号, 申込目的).
 * NOTE: source has a single file-name column (ファイル名); target has both
 *       ファイル名称 and データファイル名, both mapped from ファイル名.
 */
public class 申込担保回答ＰＤＦSource {

    private String 申込番号;   // VARCHAR2(12) PK
    private String 申込目的;   // VARCHAR2(2)  PK
    private String ファイル種類; // VARCHAR2(1) PK  1:担保情報 / 2:物件明細情報
    private String ファイル名;   // VARCHAR2(100)

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String v) { this.申込番号 = v; }

    public String get申込目的() { return 申込目的; }
    public void set申込目的(String v) { this.申込目的 = v; }

    public String getファイル種類() { return ファイル種類; }
    public void setファイル種類(String v) { this.ファイル種類 = v; }

    public String getファイル名() { return ファイル名; }
    public void setファイル名(String v) { this.ファイル名 = v; }
}
