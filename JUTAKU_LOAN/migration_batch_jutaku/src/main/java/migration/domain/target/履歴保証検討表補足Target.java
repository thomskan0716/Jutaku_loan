package migration.domain.target;

/**
 * Target: 履歴保証検討表補足 (History Guarantee Review Sheet Supplement) - ITF_SMS
 * One row per completed 申込 record within each group, keyed with 回数.
 * Phase 1: PKs only. Data columns (特殊案件区分, 借入額, etc.) migrated in Phase 2.
 */
public class 履歴保証検討表補足Target {

    private String 申込番号;    // VARCHAR2(12) PK1
    private String 申込目的;    // VARCHAR2(2)  PK2 - '10' or '20' after conversion
    private Integer 回数;       // NUMBER(3,0)  PK3 - ascending within group

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String 申込番号) { this.申込番号 = 申込番号; }

    public String get申込目的() { return 申込目的; }
    public void set申込目的(String 申込目的) { this.申込目的 = 申込目的; }

    public Integer get回数() { return 回数; }
    public void set回数(Integer 回数) { this.回数 = 回数; }
}
