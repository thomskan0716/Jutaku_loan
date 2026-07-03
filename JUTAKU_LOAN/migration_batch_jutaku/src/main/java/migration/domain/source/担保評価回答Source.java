package migration.domain.source;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Source: 担保評価回答 (Collateral valuation answer) - SZB_SMS
 * Keyed by (申込番号, 申込目的, イベント, イベント日時). 1:N per (申込番号, 申込目的).
 */
public class 担保評価回答Source {

    private String 申込番号;         // VARCHAR2(12)
    private String 申込目的;         // VARCHAR2(2)
    private String イベント;         // VARCHAR2(50)
    private Date イベント日時;       // DATE
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
