package migration.domain.target;

import java.util.Date;

/**
 * Target: 審査預保照会 (Review deposit-guarantee inquiry) - ITF_SMS.
 * Source is 審査預保照会. 申込番号 first digit 2->3; 申込目的 converted by service.
 */
public class 審査預保照会Target {

    private String 申込番号;
    private String 申込目的;
    private String イベント;
    private Date イベント日時;
    private String 照会依頼番号;  // VARCHAR2(30)

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String v) { this.申込番号 = v; }

    public String get申込目的() { return 申込目的; }
    public void set申込目的(String v) { this.申込目的 = v; }

    public String getイベント() { return イベント; }
    public void setイベント(String v) { this.イベント = v; }

    public Date getイベント日時() { return イベント日時; }
    public void setイベント日時(Date v) { this.イベント日時 = v; }

    public String get照会依頼番号() { return 照会依頼番号; }
    public void set照会依頼番号(String v) { this.照会依頼番号 = v; }
}
