package migration.domain.source;

/**
 * Source: 申込審査状況 (Review Status) - Old System DB: E00197SV0203/SZH_SMS
 *
 * Confirmed columns (○ on RIGHT side): No.761 申込番号  VARCHAR2(12)  PK1 No.762 申込目的  VARCHAR2(2) PK2 - E申込目的 conversion   No.763 回数      NUMBER(3,0)   PK3
 */
public class 申込審査状況Source {

    private String 申込番号;
    private String 申込目的; 
    private Integer 回数;

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String 申込番号) { this.申込番号 = 申込番号; }

    public String get申込目的() { return 申込目的; }
    public void set申込目的(String 申込目的) { this.申込目的 = 申込目的; }

    public Integer get回数() { return 回数; }
    public void set回数(Integer 回数) { this.回数 = 回数; }
}
