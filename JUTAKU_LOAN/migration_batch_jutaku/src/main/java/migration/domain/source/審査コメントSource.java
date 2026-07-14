package migration.domain.source;

import java.util.Date;

/**
 * Source: 審査コメント (Review comment) - SZB_SMS.
 * 1:N per (申込番号, 申込目的), keyed by イベント/イベント日時.
 */
public class 審査コメントSource {

    private String 申込番号;      // VARCHAR2(12)
    private String 申込目的;      // VARCHAR2(2)
    private String イベント;      // VARCHAR2(50)
    private Date イベント日時;    // DATE
    private String コメント区分;  // VARCHAR2(2)
    private String 結果種類;      // VARCHAR2(10)
    private String コメント;      // VARCHAR2(1000)

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String v) { this.申込番号 = v; }

    public String get申込目的() { return 申込目的; }
    public void set申込目的(String v) { this.申込目的 = v; }

    public String getイベント() { return イベント; }
    public void setイベント(String v) { this.イベント = v; }

    public Date getイベント日時() { return イベント日時; }
    public void setイベント日時(Date v) { this.イベント日時 = v; }

    public String getコメント区分() { return コメント区分; }
    public void setコメント区分(String v) { this.コメント区分 = v; }

    public String get結果種類() { return 結果種類; }
    public void set結果種類(String v) { this.結果種類 = v; }

    public String getコメント() { return コメント; }
    public void setコメント(String v) { this.コメント = v; }
}
