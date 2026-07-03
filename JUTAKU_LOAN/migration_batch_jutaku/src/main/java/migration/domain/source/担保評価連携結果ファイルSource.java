package migration.domain.source;

import java.util.Date;

/**
 * Source (joined): drives ＩＦ＿担保評価連携結果＿ファイル.
 * イベント / イベント日時 come from 担保評価回答; ファイル種別 / ファイル名称 /
 * データファイル名 come from 申込担保回答ＰＤＦ, matched on (申込番号, 申込目的).
 */
public class 担保評価連携結果ファイルSource {

    private String 申込番号;         // VARCHAR2(12)
    private String 申込目的;         // VARCHAR2(2)
    private String イベント;         // VARCHAR2(50)  from 担保評価回答
    private Date イベント日時;       // DATE          from 担保評価回答
    private String ファイル種別;     // VARCHAR2(1)   from 申込担保回答ＰＤＦ.ファイル種類 (1:地区情報 / 2:物件明細書類)
    private String ファイル名称;     // VARCHAR2(100) from 申込担保回答ＰＤＦ.ファイル名
    private String データファイル名; // VARCHAR2(128) from 申込担保回答ＰＤＦ.ファイル名

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String v) { this.申込番号 = v; }

    public String get申込目的() { return 申込目的; }
    public void set申込目的(String v) { this.申込目的 = v; }

    public String getイベント() { return イベント; }
    public void setイベント(String v) { this.イベント = v; }

    public Date getイベント日時() { return イベント日時; }
    public void setイベント日時(Date v) { this.イベント日時 = v; }

    public String getファイル種別() { return ファイル種別; }
    public void setファイル種別(String v) { this.ファイル種別 = v; }

    public String getファイル名称() { return ファイル名称; }
    public void setファイル名称(String v) { this.ファイル名称 = v; }

    public String getデータファイル名() { return データファイル名; }
    public void setデータファイル名(String v) { this.データファイル名 = v; }
}
