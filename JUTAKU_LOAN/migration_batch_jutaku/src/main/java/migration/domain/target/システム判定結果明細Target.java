package migration.domain.target;

import java.util.Date;

/**
 * Target: システム判定結果明細 (System judgment result detail) - ITF_SMS.
 * 申込番号 first digit 2→3; 申込目的 converted by service. Other columns pass through.
 */
public class システム判定結果明細Target {

    private String 申込番号;
    private String 申込目的;
    private String イベント;
    private Date イベント日時;
    private String 判定コード;
    private String 値;
    private String 備考;

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
