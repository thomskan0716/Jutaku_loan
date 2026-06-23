package migration.domain.target;

/**
 * Target: 保証人 (Guarantor) - ITF_SMS
 * Only guarantors belonging to the MAX 申込目的 per group.
 */
public class 保証人Target {

    private String 申込番号;    // VARCHAR2(12) PK1
    private String 申込目的;    // VARCHAR2(2)  PK2 - '10' or '20' after conversion
    private Integer 連番;       // NUMBER       PK3

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String 申込番号) { this.申込番号 = 申込番号; }

    public String get申込目的() { return 申込目的; }
    public void set申込目的(String 申込目的) { this.申込目的 = 申込目的; }

    public Integer get連番() { return 連番; }
    public void set連番(Integer 連番) { this.連番 = 連番; }
}
