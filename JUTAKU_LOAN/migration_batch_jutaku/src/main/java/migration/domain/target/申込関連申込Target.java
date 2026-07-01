package migration.domain.target;

/**
 * Target: 申込関連申込 (Related Applications) - ITF_SMS
 * 1:N per 申込番号. Both 申込番号 and 関連申込番号 first digit converted: 2→3.
 */
public class 申込関連申込Target {

    private String 申込番号;     // VARCHAR2(12) PK NOT NULL
    private String 関連区分;     // VARCHAR2(1)  1:土地先行 / 2:ペア型 / 3:ミックス / その他
    private String 関連申込番号; // VARCHAR2(12) PK NOT NULL

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String v) { this.申込番号 = v; }

    public String get関連区分() { return 関連区分; }
    public void set関連区分(String v) { this.関連区分 = v; }

    public String get関連申込番号() { return 関連申込番号; }
    public void set関連申込番号(String v) { this.関連申込番号 = v; }
}
