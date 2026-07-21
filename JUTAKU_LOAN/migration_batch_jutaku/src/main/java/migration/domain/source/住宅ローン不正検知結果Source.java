package migration.domain.source;

import java.util.Date;

/**
 * Source: 住宅ローン不正検知結果 (Housing loan fraud detection result) - SZB_SMS.
 * 1:N per (申込番号, 申込目的), keyed by イベント/イベント日時.
 */
public class 住宅ローン不正検知結果Source {

    private String 申込番号;          // VARCHAR2(12)
    private String 申込目的;          // VARCHAR2(2)
    private String イベント;          // VARCHAR2(50)
    private Date イベント日時;        // DATE
    private String リクエストＩＤ;    // VARCHAR2(12)
    private String 得点;              // VARCHAR2(10)
    private String エラーコード;      // VARCHAR2(10)
    private String メッセージ;        // VARCHAR2(200)

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String v) { this.申込番号 = v; }

    public String get申込目的() { return 申込目的; }
    public void set申込目的(String v) { this.申込目的 = v; }

    public String getイベント() { return イベント; }
    public void setイベント(String v) { this.イベント = v; }

    public Date getイベント日時() { return イベント日時; }
    public void setイベント日時(Date v) { this.イベント日時 = v; }

    public String getリクエストＩＤ() { return リクエストＩＤ; }
    public void setリクエストＩＤ(String v) { this.リクエストＩＤ = v; }

    public String get得点() { return 得点; }
    public void set得点(String v) { this.得点 = v; }

    public String getエラーコード() { return エラーコード; }
    public void setエラーコード(String v) { this.エラーコード = v; }

    public String getメッセージ() { return メッセージ; }
    public void setメッセージ(String v) { this.メッセージ = v; }
}
