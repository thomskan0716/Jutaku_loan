package migration.domain.source;

/**
 * Source: 申込 (Application) - Old System
 * DB: E00197SV0203/SZH_SMS
 */
public class ApplicationSource {
    
    private String 申込番号;           // VARCHAR2(12) PK , Primary Key (Row 51)
    
    private String 申込日;             // VARCHAR2(2)
    private String 申込目的;           // VARCHAR2(2) - Needs E申込目的 conversion
    
    private String 融資申込番号;       // VARCHAR2(12)
    
    private Integer 関連番;            // NUMBER(3,0)
    
    private String 連絡コード;         // VARCHAR2(6) - Needs E連絡コード conversion
    private String 経済記;             // VARCHAR2(20)
    
    private String 申込履歴号;         // VARCHAR2(12)
    private String 申込日付;           // VARCHAR2(2)
    
    private String 審査履歴;           // VARCHAR2(12)
    private String 日付;               // VARCHAR2(2)
    private String イベント;           // VARCHAR2(50)
    
    
    public String get申込番号() { return 申込番号; }
    public void set申込番号(String 申込番号) { this.申込番号 = 申込番号; }
    
    public String get申込日() { return 申込日; }
    public void set申込日(String 申込日) { this.申込日 = 申込日; }
    
    public String get申込目的() { return 申込目的; }
    public void set申込目的(String 申込目的) { this.申込目的 = 申込目的; }
    
    public String get融資申込番号() { return 融資申込番号; }
    public void set融資申込番号(String 融資申込番号) { this.融資申込番号 = 融資申込番号; }
    
    public Integer get関連番() { return 関連番; }
    public void set関連番(Integer 関連番) { this.関連番 = 関連番; }
    
    public String get連絡コード() { return 連絡コード; }
    public void set連絡コード(String 連絡コード) { this.連絡コード = 連絡コード; }
    
    public String get経済記() { return 経済記; }
    public void set経済記(String 経済記) { this.経済記 = 経済記; }
    
    public String get申込履歴号() { return 申込履歴号; }
    public void set申込履歴号(String 申込履歴号) { this.申込履歴号 = 申込履歴号; }
    
    public String get申込日付() { return 申込日付; }
    public void set申込日付(String 申込日付) { this.申込日付 = 申込日付; }
    
    public String get審査履歴() { return 審査履歴; }
    public void set審査履歴(String 審査履歴) { this.審査履歴 = 審査履歴; }
    
    public String get日付() { return 日付; }
    public void set日付(String 日付) { this.日付 = 日付; }
    
    public String getイベント() { return イベント; }
    public void setイベント(String イベント) { this.イベント = イベント; }
}
