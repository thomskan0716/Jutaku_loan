package migration.domain.target;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Target: ＩＦ＿担保評価連携結果 (IF collateral valuation link result) - ITF_SMS
 * 申込番号 first digit 2→3; 申込目的 converted (10/15→10, 20/30→20) by service.
 * 一連番号 is set to the fixed value '99999' (per 編集仕様詳細). The remaining
 * columns pass through from 担保評価回答.
 */
public class ＩＦ＿担保評価連携結果Target {

    private String 申込番号;         // VARCHAR2(12) PK NOT NULL
    private String 申込目的;         // VARCHAR2(2)  PK NOT NULL
    private String 一連番号;         // VARCHAR2(5)  fixed '99999'
    private String イベント;         // VARCHAR2(50) NOT NULL
    private Date イベント日時;       // DATE NOT NULL
    private BigDecimal 簡易評価額;   // NUMBER(13,0)
    private BigDecimal 簡易土地評価額; // NUMBER(13,0)
    private BigDecimal 簡易建物評価額; // NUMBER(13,0)
    private String 土地特記事項;     // VARCHAR2(240)
    private String 建物特記事項;     // VARCHAR2(240)
    private BigDecimal 先順位控除額; // NUMBER

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

    public BigDecimal get簡易評価額() { return 簡易評価額; }
    public void set簡易評価額(BigDecimal v) { this.簡易評価額 = v; }

    public BigDecimal get簡易土地評価額() { return 簡易土地評価額; }
    public void set簡易土地評価額(BigDecimal v) { this.簡易土地評価額 = v; }

    public BigDecimal get簡易建物評価額() { return 簡易建物評価額; }
    public void set簡易建物評価額(BigDecimal v) { this.簡易建物評価額 = v; }

    public String get土地特記事項() { return 土地特記事項; }
    public void set土地特記事項(String v) { this.土地特記事項 = v; }

    public String get建物特記事項() { return 建物特記事項; }
    public void set建物特記事項(String v) { this.建物特記事項 = v; }

    public BigDecimal get先順位控除額() { return 先順位控除額; }
    public void set先順位控除額(BigDecimal v) { this.先順位控除額 = v; }
}
