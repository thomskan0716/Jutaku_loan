package migration.domain.target;

import java.util.Date;

/**
 * Target: 審査ＫＳＣ信用情報詳細 (Review KSC credit info detail) - ITF_SMS.
 * 申込番号 first digit 2->3; 申込目的 converted by service; rest pass-through.
 * Target-only column 延滞回数 has no source and is left null.
 */
public class 審査ＫＳＣ信用情報詳細Target {

    private String 申込番号;     // VARCHAR2(12) PK
    private String 申込目的;     // VARCHAR2(2)  PK
    private String イベント;     // VARCHAR2(50) NOT NULL
    private Date イベント日時;   // DATE NOT NULL
    private Integer 連番;        // NUMBER(1,0) NOT NULL
    private Integer 別名連番;    // NUMBER(1,0) NOT NULL
    private Integer 詳細連番;    // NUMBER(3,0) NOT NULL
    private Date 受付日時;       // DATE
    private String 受付番号;     // CHAR(20)
    private String 該当者通番;   // VARCHAR2(4)
    private String テーブル名;   // VARCHAR2(50)
    private String 項目名;       // VARCHAR2(50)
    private String コード番号;   // VARCHAR2(10)
    private String コード;       // VARCHAR2(10)
    private String コード名称;   // VARCHAR2(100)
    private String 氏名;         // VARCHAR2(50)
    private String 氏名カナ;     // VARCHAR2(50)
    private String 種類;         // VARCHAR2(50)
    private String 信用情報判断; // VARCHAR2(100)
    private String 信用情報;     // VARCHAR2(100)
    private String 判断項目名1;  // VARCHAR2(50)
    private String 判断項目1;    // VARCHAR2(50)
    private String 判断項目名2;  // VARCHAR2(50)
    private String 判断項目2;    // VARCHAR2(50)
    private String 判断項目名3;  // VARCHAR2(50)
    private String 判断項目3;    // VARCHAR2(50)
    private String 判断項目名4;  // VARCHAR2(50)
    private String 判断項目4;    // VARCHAR2(50)
    private String 判断項目名5;  // VARCHAR2(50)
    private String 判断項目5;    // VARCHAR2(50)
    private String ブラック判断; // VARCHAR2(10)
    private String 発生日;       // VARCHAR2(8)
    private String 契約日;       // VARCHAR2(8)

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

    public Integer get別名連番() { return 別名連番; }
    public void set別名連番(Integer v) { this.別名連番 = v; }

    public Integer get詳細連番() { return 詳細連番; }
    public void set詳細連番(Integer v) { this.詳細連番 = v; }

    public Date get受付日時() { return 受付日時; }
    public void set受付日時(Date v) { this.受付日時 = v; }

    public String get受付番号() { return 受付番号; }
    public void set受付番号(String v) { this.受付番号 = v; }

    public String get該当者通番() { return 該当者通番; }
    public void set該当者通番(String v) { this.該当者通番 = v; }

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

    public String get氏名() { return 氏名; }
    public void set氏名(String v) { this.氏名 = v; }

    public String get氏名カナ() { return 氏名カナ; }
    public void set氏名カナ(String v) { this.氏名カナ = v; }

    public String get種類() { return 種類; }
    public void set種類(String v) { this.種類 = v; }

    public String get信用情報判断() { return 信用情報判断; }
    public void set信用情報判断(String v) { this.信用情報判断 = v; }

    public String get信用情報() { return 信用情報; }
    public void set信用情報(String v) { this.信用情報 = v; }

    public String get判断項目名1() { return 判断項目名1; }
    public void set判断項目名1(String v) { this.判断項目名1 = v; }

    public String get判断項目1() { return 判断項目1; }
    public void set判断項目1(String v) { this.判断項目1 = v; }

    public String get判断項目名2() { return 判断項目名2; }
    public void set判断項目名2(String v) { this.判断項目名2 = v; }

    public String get判断項目2() { return 判断項目2; }
    public void set判断項目2(String v) { this.判断項目2 = v; }

    public String get判断項目名3() { return 判断項目名3; }
    public void set判断項目名3(String v) { this.判断項目名3 = v; }

    public String get判断項目3() { return 判断項目3; }
    public void set判断項目3(String v) { this.判断項目3 = v; }

    public String get判断項目名4() { return 判断項目名4; }
    public void set判断項目名4(String v) { this.判断項目名4 = v; }

    public String get判断項目4() { return 判断項目4; }
    public void set判断項目4(String v) { this.判断項目4 = v; }

    public String get判断項目名5() { return 判断項目名5; }
    public void set判断項目名5(String v) { this.判断項目名5 = v; }

    public String get判断項目5() { return 判断項目5; }
    public void set判断項目5(String v) { this.判断項目5 = v; }

    public String getブラック判断() { return ブラック判断; }
    public void setブラック判断(String v) { this.ブラック判断 = v; }

    public String get発生日() { return 発生日; }
    public void set発生日(String v) { this.発生日 = v; }

    public String get契約日() { return 契約日; }
    public void set契約日(String v) { this.契約日 = v; }
}
