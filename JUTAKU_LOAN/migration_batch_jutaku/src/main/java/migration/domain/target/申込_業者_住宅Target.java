package migration.domain.target;

/**
 * Target: 申込_業者_住宅 (Application - Contractor/Housing, main table) - ITF_SMS
 * Inserted once per group using MAX 申込目的, same pattern as 申込Target.
 */
public class 申込_業者_住宅Target {

    private String 申込番号;    // VARCHAR2(12) PK1
    private String 申込目的;    // VARCHAR2(2)  PK2 - '10' or '20' after conversion

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String v) { this.申込番号 = v; }

    public String get申込目的() { return 申込目的; }
    public void set申込目的(String v) { this.申込目的 = v; }
}
