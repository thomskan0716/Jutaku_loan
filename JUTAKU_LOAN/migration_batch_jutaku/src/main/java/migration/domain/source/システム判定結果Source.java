package migration.domain.source;

import java.util.Date;

/**
 * Source: システム判定結果 (System judgment result) - SZB_SMS.
 * 1:N per (申込番号, 申込目的), keyed by イベント/イベント日時.
 */
public class システム判定結果Source {

    private String 申込番号;              // VARCHAR2(12)
    private String 申込目的;              // VARCHAR2(2)
    private String イベント;              // VARCHAR2(30)
    private Date イベント日時;            // DATE
    private String システム判定結果;      // VARCHAR2(2)

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String v) { this.申込番号 = v; }

    public String get申込目的() { return 申込目的; }
    public void set申込目的(String v) { this.申込目的 = v; }

    public String getイベント() { return イベント; }
    public void setイベント(String v) { this.イベント = v; }

    public Date getイベント日時() { return イベント日時; }
    public void setイベント日時(Date v) { this.イベント日時 = v; }

    public String getシステム判定結果() { return システム判定結果; }
    public void setシステム判定結果(String v) { this.システム判定結果 = v; }
}
