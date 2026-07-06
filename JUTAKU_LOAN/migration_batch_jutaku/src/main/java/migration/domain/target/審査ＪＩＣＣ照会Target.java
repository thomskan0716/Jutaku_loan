package migration.domain.target;

import java.util.Date;

/**
 * Target: 審査ＪＩＣＣ照会 (Review JICC inquiry) - ITF_SMS
 * 申込番号 first digit 2→3; 申込目的 converted (10/15→10, 20/30→20) by service.
 * イベント/イベント日時/連番/別名連番/受付日時/受付番号/コメント pass through from source.
 */
public class 審査ＪＩＣＣ照会Target {

    private String 申込番号;     // VARCHAR2(12) PK NOT NULL
    private String 申込目的;     // VARCHAR2(2)  PK NOT NULL
    private String イベント;     // VARCHAR2(50) NOT NULL
    private Date イベント日時;   // DATE NOT NULL
    private Integer 連番;        // NUMBER(1,0) NOT NULL
    private Integer 別名連番;    // NUMBER(1,0) NOT NULL
    private Date 受付日時;       // DATE
    private String 受付番号;     // CHAR(12)
    private String コメント;     // VARCHAR2(500)

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

    public Date get受付日時() { return 受付日時; }
    public void set受付日時(Date v) { this.受付日時 = v; }

    public String get受付番号() { return 受付番号; }
    public void set受付番号(String v) { this.受付番号 = v; }

    public String getコメント() { return コメント; }
    public void setコメント(String v) { this.コメント = v; }
}
