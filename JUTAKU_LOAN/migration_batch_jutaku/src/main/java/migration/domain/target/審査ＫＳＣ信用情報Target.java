package migration.domain.target;

import java.util.Date;

/**
 * Target: 審査ＫＳＣ信用情報 (Review KSC credit info) - ITF_SMS.
 * Only columns populated from the source are declared here; target-only
 * columns (ＫＳＣグレー, ＫＳＣ延滞, ＫＳＣ転居歴, etc.) are left null.
 * 申込番号 first digit 2->3; 申込目的 converted by service.
 */
public class 審査ＫＳＣ信用情報Target {

    private String 申込番号;              // VARCHAR2(12) PK
    private String 申込目的;              // VARCHAR2(2)  PK
    private String イベント;              // VARCHAR2(50) NOT NULL
    private Date イベント日時;            // DATE NOT NULL
    private Integer 連番;                 // NUMBER(1,0) NOT NULL
    private String ＫＳＣブラック;         // VARCHAR2(8)
    private String ＫＳＣ経由ＣＩＣブラック; // VARCHAR2(8)
    private String ＫＳＣ経由ＪＩＣブラック; // VARCHAR2(8)
    private Integer ＫＳＣ照会件数;        // NUMBER(3,0)
    private Integer ＫＳＣ契約件数;        // NUMBER(3,0)
    private String ＫＳＣ極度額オーバー;   // VARCHAR2(10)
    private Integer ＫＳＣレコード数;      // NUMBER(3,0)

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

    public String getＫＳＣブラック() { return ＫＳＣブラック; }
    public void setＫＳＣブラック(String v) { this.ＫＳＣブラック = v; }

    public String getＫＳＣ経由ＣＩＣブラック() { return ＫＳＣ経由ＣＩＣブラック; }
    public void setＫＳＣ経由ＣＩＣブラック(String v) { this.ＫＳＣ経由ＣＩＣブラック = v; }

    public String getＫＳＣ経由ＪＩＣブラック() { return ＫＳＣ経由ＪＩＣブラック; }
    public void setＫＳＣ経由ＪＩＣブラック(String v) { this.ＫＳＣ経由ＪＩＣブラック = v; }

    public Integer getＫＳＣ照会件数() { return ＫＳＣ照会件数; }
    public void setＫＳＣ照会件数(Integer v) { this.ＫＳＣ照会件数 = v; }

    public Integer getＫＳＣ契約件数() { return ＫＳＣ契約件数; }
    public void setＫＳＣ契約件数(Integer v) { this.ＫＳＣ契約件数 = v; }

    public String getＫＳＣ極度額オーバー() { return ＫＳＣ極度額オーバー; }
    public void setＫＳＣ極度額オーバー(String v) { this.ＫＳＣ極度額オーバー = v; }

    public Integer getＫＳＣレコード数() { return ＫＳＣレコード数; }
    public void setＫＳＣレコード数(Integer v) { this.ＫＳＣレコード数 = v; }
}
