package migration.domain.target;

/**
 * Target: 履歴申込審査段階 (History Review Stage) - ITF_SMS
 * All completed 申込審査段階 records within each group, with 回数 assigned ascending.
 */
public class 履歴申込審査段階Target {

    private String 申込番号;        // VARCHAR2(12) PK1
    private String 申込目的;        // VARCHAR2(2)  PK2 - '10' or '20' after conversion
    private Integer 回数;           // NUMBER(3,0)  PK3 - ascending within group (1=oldest, max=newest)
    private String 審査完了区分;    // VARCHAR2(1)  carried from source 申込審査段階

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String 申込番号) { this.申込番号 = 申込番号; }

    public String get申込目的() { return 申込目的; }
    public void set申込目的(String 申込目的) { this.申込目的 = 申込目的; }

    public Integer get回数() { return 回数; }
    public void set回数(Integer 回数) { this.回数 = 回数; }

    public String get審査完了区分() { return 審査完了区分; }
    public void set審査完了区分(String 審査完了区分) { this.審査完了区分 = 審査完了区分; }
}
