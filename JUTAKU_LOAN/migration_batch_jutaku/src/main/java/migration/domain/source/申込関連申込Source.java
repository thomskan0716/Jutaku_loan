package migration.domain.source;

/**
 * Source: 申込関連申込 (Related Applications) - SZB_SMS
 * Keyed by 申込番号 (+ 関連申込番号). 1:N per 申込番号.
 */
public class 申込関連申込Source {

    private String 申込番号;     // VARCHAR2(12) PK
    private String 関連区分;     // VARCHAR2(1)
    private String 関連申込番号; // VARCHAR2(12) PK

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String v) { this.申込番号 = v; }

    public String get関連区分() { return 関連区分; }
    public void set関連区分(String v) { this.関連区分 = v; }

    public String get関連申込番号() { return 関連申込番号; }
    public void set関連申込番号(String v) { this.関連申込番号 = v; }
}
