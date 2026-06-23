package migration.domain.target;

/**
 * Target: 申込審査段階 (Review Stage) - ITF_SMS
 * Receives only the MAX 申込目的 record per group (事前: 15→10, 正式: 30→20).
 */
public class 申込審査段階Target {

    private String 申込番号;        // VARCHAR2(12) PK1
    private String 申込目的;        // VARCHAR2(2)  PK2 - always '10' or '20' after conversion
    private String 審査完了区分;    // VARCHAR2(1)  always '1' (only completed records migrate)

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String 申込番号) { this.申込番号 = 申込番号; }

    public String get申込目的() { return 申込目的; }
    public void set申込目的(String 申込目的) { this.申込目的 = 申込目的; }

    public String get審査完了区分() { return 審査完了区分; }
    public void set審査完了区分(String 審査完了区分) { this.審査完了区分 = 審査完了区分; }
}
