package migration.domain.source;

/**
 * Source: 申込 (Application) - Old System DB: E00197SV0203/SZH_SMS Week 3-4 基礎実装: Only 2 columns
 */
public class 申込Source {
    
    private String 申込番号;           // VARCHAR2(12) PK
    private String 申込目的;           // VARCHAR2(2) - Needs E申込目的 conversion
    
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
