package migration.domain.target;

import java.util.Date;

/**
 * Target: 審査ＫＳＣ信用情報明細 (Review KSC credit info line) - ITF_SMS.
 * 申込番号 first digit 2->3; 申込目的 converted by service; rest pass-through.
 */
public class 審査ＫＳＣ信用情報明細Target {

    private String 申込番号;   // VARCHAR2(12) PK
    private String 申込目的;   // VARCHAR2(2)  PK
    private String イベント;   // VARCHAR2(50) NOT NULL
    private Date イベント日時; // DATE NOT NULL
    private Integer 連番;      // NUMBER(1,0) NOT NULL
    private Date 受付日時;     // DATE
    private String 受付番号;   // CHAR(20)
    private String テーブル名; // VARCHAR2(50) NOT NULL
    private String 項目名;     // VARCHAR2(50) NOT NULL
    private String コード番号; // VARCHAR2(10) NOT NULL
    private String コード;     // VARCHAR2(10) NOT NULL
    private String コード名称; // VARCHAR2(100)
    private String ブラック判断; // VARCHAR2(10)

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String v) { this.申込番号 = v; }

    public String get申込目的() { return 申込目的; }
    public void set申込目的(String v) { this.申込目的 = v; }

    public String getイベント() { return イベント; }
    public void setイベント(String v) { this.イベント = v; }

    public Date getイベント日時() { return イベント日時; }
    public void setイベント日時(Date v) { this.イベント日時 = v; }

    public Integer get連番() { return 連番; }
    public void set連番(Integer v) { this.連番 = v; }

    public Date get受付日時() { return 受付日時; }
    public void set受付日時(Date v) { this.受付日時 = v; }

    public String get受付番号() { return 受付番号; }
    public void set受付番号(String v) { this.受付番号 = v; }

    public String getテーブル名() { return テーブル名; }
    public void setテーブル名(String v) { this.テーブル名 = v; }

    public String get項目名() { return 項目名; }
    public void set項目名(String v) { this.項目名 = v; }

    public String getコード番号() { return コード番号; }
    public void setコード番号(String v) { this.コード番号 = v; }

    public String getコード() { return コード; }
    public void setコード(String v) { this.コード = v; }

    public String getコード名称() { return コード名称; }
    public void setコード名称(String v) { this.コード名称 = v; }

    public String getブラック判断() { return ブラック判断; }
    public void setブラック判断(String v) { this.ブラック判断 = v; }
}
