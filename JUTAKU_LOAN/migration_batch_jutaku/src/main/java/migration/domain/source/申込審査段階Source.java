package migration.domain.source;

/**
 * Source: 申込審査段階 (Review Stage) - SZB_SMS
 * Key table for migration filter: 審査完了区分='1' means completed (migration target).
 * Multiple rows per 申込番号 (one per 申込目的: 10, 15, 20, 30).
 */
public class 申込審査段階Source {

    private String 申込番号;        // VARCHAR2(12) PK1
    private String 申込目的;        // VARCHAR2(2)  PK2
    private String 審査完了区分;    // VARCHAR2(1)  '0'=未完了, '1'=完了

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String 申込番号) { this.申込番号 = 申込番号; }

    public String get申込目的() { return 申込目的; }
    public void set申込目的(String 申込目的) { this.申込目的 = 申込目的; }

    public String get審査完了区分() { return 審査完了区分; }
    public void set審査完了区分(String 審査完了区分) { this.審査完了区分 = 審査完了区分; }
}
