package migration.domain.target;

import java.util.Date;

/**
 * Target: 申込審査履歴 (Application review event history) - ITF_SMS
 * 申込番号 first digit 2→3; 申込目的 converted (10/15→10, 20/30→20) by service.
 * イベント/イベント日時/進捗コード/ユーザＩＤ/ユーザ名/回数 passed through from source.
 */
public class 申込審査履歴Target {

    private String 申込番号;   // VARCHAR2(12) PK NOT NULL
    private String 申込目的;   // VARCHAR2(2)  PK NOT NULL
    private String イベント;   // VARCHAR2(50) NOT NULL
    private Date イベント日時; // DATE NOT NULL
    private String 進捗コード; // VARCHAR2(6)
    private String ユーザＩＤ;   // VARCHAR2(10)
    private String ユーザ名;   // VARCHAR2(50)
    private Integer 回数;      // NUMBER(3,0)

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String v) { this.申込番号 = v; }

    public String get申込目的() { return 申込目的; }
    public void set申込目的(String v) { this.申込目的 = v; }

    public String getイベント() { return イベント; }
    public void setイベント(String v) { this.イベント = v; }

    public Date getイベント日時() { return イベント日時; }
    public void setイベント日時(Date v) { this.イベント日時 = v; }

    public String get進捗コード() { return 進捗コード; }
    public void set進捗コード(String v) { this.進捗コード = v; }

    public String getユーザＩＤ() { return ユーザＩＤ; }
    public void setユーザＩＤ(String v) { this.ユーザＩＤ = v; }

    public String getユーザ名() { return ユーザ名; }
    public void setユーザ名(String v) { this.ユーザ名 = v; }

    public Integer get回数() { return 回数; }
    public void set回数(Integer v) { this.回数 = v; }
}
