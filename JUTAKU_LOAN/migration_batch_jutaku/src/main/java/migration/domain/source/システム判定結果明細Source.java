package migration.domain.source;

import java.util.Date;

/**
 * Source: システム判定結果明細 (System judgment result detail) - SZB_SMS.
 * 1:N per (申込番号, 申込目的), keyed by イベント/イベント日時/判定コード.
 */
public class システム判定結果明細Source {

    private String 申込番号;      // VARCHAR2(12)
    private String 申込目的;      // VARCHAR2(2)
    private String イベント;      // VARCHAR2(30)
    private Date イベント日時;    // DATE
    private String 判定コード;    // VARCHAR2(2)
    private String 値;            // VARCHAR2(30)
    private String 備考;          // VARCHAR2(100)

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String v) { this.申込番号 = v; }

    public String get申込目的() { return 申込目的; }
    public void set申込目的(String v) { this.申込目的 = v; }

    public String getイベント() { return イベント; }
    public void setイベント(String v) { this.イベント = v; }

    public Date getイベント日時() { return イベント日時; }
    public void setイベント日時(Date v) { this.イベント日時 = v; }

    public String get判定コード() { return 判定コード; }
    public void set判定コード(String v) { this.判定コード = v; }

    public String get値() { return 値; }
    public void set値(String v) { this.値 = v; }

    public String get備考() { return 備考; }
    public void set備考(String v) { this.備考 = v; }
}
