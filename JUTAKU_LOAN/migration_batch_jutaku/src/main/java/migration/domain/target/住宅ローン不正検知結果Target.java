package migration.domain.target;

import java.util.Date;

/**
 * Target: 住宅ローン不正検知結果 (Housing loan fraud detection result) - ITF_SMS.
 * 申込番号 first digit 2→3; 申込目的 converted by service. Other columns pass through.
 */
public class 住宅ローン不正検知結果Target {

    private String 申込番号;
    private String 申込目的;
    private String イベント;
    private Date イベント日時;
    private String リクエストＩＤ;
    private String 得点;
    private String エラーコード;
    private String メッセージ;

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
