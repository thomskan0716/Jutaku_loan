package migration.domain.target;

/**
 * Target: 申込 (Application) - New System
 * DB: E00736SV0001/SZH_SMS
 * Week 3-4 基礎実装: Only 2 columns
 */
public class ApplicationTarget {
    
    private String 申込番号;           // VARCHAR2(12) PK
    private String 申込目的;           // VARCHAR2(2) - Converted from E申込目的
    
    // Getters & Setters
    public String get申込番号() { 
        return 申込番号; 
    }
    public void set申込番号(String 申込番号) { 
        this.申込番号 = 申込番号; 
    }
    
    public String get申込目的() { 
        return 申込目的; 
    }
    public void set申込目的(String 申込目的) { 
        this.申込目的 = 申込目的; 
    }
}
