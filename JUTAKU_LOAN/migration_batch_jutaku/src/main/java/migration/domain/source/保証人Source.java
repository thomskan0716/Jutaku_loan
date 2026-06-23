package migration.domain.source;

/**
 * Source: 保証人 (Guarantor) - SZB_SMS
 * Multiple rows per (申込番号, 申込目的): one per 連番.
 */
public class 保証人Source {

    private String 申込番号;    // VARCHAR2(12) PK1
    private String 申込目的;    // VARCHAR2(2)  PK2
    private Integer 連番;       // NUMBER       PK3

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String 申込番号) { this.申込番号 = 申込番号; }

    public String get申込目的() { return 申込目的; }
    public void set申込目的(String 申込目的) { this.申込目的 = 申込目的; }

    public Integer get連番() { return 連番; }
    public void set連番(Integer 連番) { this.連番 = 連番; }
}
