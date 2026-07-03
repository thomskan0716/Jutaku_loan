package migration.domain.target;

import java.util.Date;

/**
 * Target: ＩＦ＿担保評価連携結果＿ファイル (IF collateral valuation link result - file) - ITF_SMS
 * 申込番号 first digit 2→3; 申込目的 converted (10/15→10, 20/30→20) by service.
 * 一連番号 is set to the fixed value '99999' (per 編集仕様詳細).
 * イベント/イベント日時 come from 担保評価回答; file columns from 申込担保回答ＰＤＦ.
 */
public class ＩＦ＿担保評価連携結果＿ファイルTarget {

    private String 申込番号;         // VARCHAR2(12) PK NOT NULL
    private String 申込目的;         // VARCHAR2(2)  PK NOT NULL
    private String 一連番号;         // VARCHAR2(5)  fixed '99999'
    private String イベント;         // VARCHAR2(50) NOT NULL
    private Date イベント日時;       // DATE NOT NULL
    private String ファイル種別;     // VARCHAR2(1)  NOT NULL  1:地区情報 / 2:物件明細書類
    private String ファイル名称;     // VARCHAR2(100)
    private String データファイル名; // VARCHAR2(128)

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String v) { this.申込番号 = v; }

    public String get申込目的() { return 申込目的; }
    public void set申込目的(String v) { this.申込目的 = v; }

    public String get一連番号() { return 一連番号; }
    public void set一連番号(String v) { this.一連番号 = v; }

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
