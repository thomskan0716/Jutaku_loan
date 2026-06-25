package migration.domain.source;

/**
 * Source: 保証検討表補足 (Guarantee Review Sheet Supplement) - SZH_SMS
 * Phase 1: PKs only. Data columns added in Phase 2.
 */
public class 保証検討表補足Source {

    private String 申込番号;    // VARCHAR2(12) PK1
    private String 申込目的;    // VARCHAR2(2)  PK2

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String 申込番号) { this.申込番号 = 申込番号; }

    public String get申込目的() { return 申込目的; }
    public void set申込目的(String 申込目的) { this.申込目的 = 申込目的; }
}