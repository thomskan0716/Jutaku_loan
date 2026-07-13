package migration.domain.source;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Source: 審査モデル回答明細Ｓ (Scoring model answer detail, S-segment) - SZB_SMS.
 * Same shape as 審査モデル回答明細.
 */
public class 審査モデル回答明細ＳSource {

    private String 申込番号;
    private String 申込目的;
    private String イベント;
    private Date イベント日時;

    private BigDecimal 勤続年数評点;
    private BigDecimal 勤続年数係数;
    private BigDecimal 勤続年数スコア;
    private BigDecimal 借入年数評点;
    private BigDecimal 借入年数係数;
    private BigDecimal 借入年数スコア;
    private BigDecimal 資本金評点;
    private BigDecimal 資本金係数;
    private BigDecimal 資本金スコア;
    private BigDecimal 合算返済比率評点;
    private BigDecimal 合算返済比率係数;
    private BigDecimal 合算返済比率スコア;
    private BigDecimal 合算返済比率;
    private BigDecimal 自己資金比率評点;
    private BigDecimal 自己資金比率係数;
    private BigDecimal 自己資金比率スコア;
    private BigDecimal 自己資金比率;
    private BigDecimal 調整定数;
    private BigDecimal 若年単身者評点;
    private BigDecimal 若年単身者係数;
    private BigDecimal 若年単身者スコア;

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String v) { this.申込番号 = v; }

    public String get申込目的() { return 申込目的; }
    public void set申込目的(String v) { this.申込目的 = v; }

    public String getイベント() { return イベント; }
    public void setイベント(String v) { this.イベント = v; }

    public Date getイベント日時() { return イベント日時; }
    public void setイベント日時(Date v) { this.イベント日時 = v; }

    public BigDecimal get勤続年数評点() { return 勤続年数評点; }
    public void set勤続年数評点(BigDecimal v) { this.勤続年数評点 = v; }

    public BigDecimal get勤続年数係数() { return 勤続年数係数; }
    public void set勤続年数係数(BigDecimal v) { this.勤続年数係数 = v; }

    public BigDecimal get勤続年数スコア() { return 勤続年数スコア; }
    public void set勤続年数スコア(BigDecimal v) { this.勤続年数スコア = v; }

    public BigDecimal get借入年数評点() { return 借入年数評点; }
    public void set借入年数評点(BigDecimal v) { this.借入年数評点 = v; }

    public BigDecimal get借入年数係数() { return 借入年数係数; }
    public void set借入年数係数(BigDecimal v) { this.借入年数係数 = v; }

    public BigDecimal get借入年数スコア() { return 借入年数スコア; }
    public void set借入年数スコア(BigDecimal v) { this.借入年数スコア = v; }

    public BigDecimal get資本金評点() { return 資本金評点; }
    public void set資本金評点(BigDecimal v) { this.資本金評点 = v; }

    public BigDecimal get資本金係数() { return 資本金係数; }
    public void set資本金係数(BigDecimal v) { this.資本金係数 = v; }

    public BigDecimal get資本金スコア() { return 資本金スコア; }
    public void set資本金スコア(BigDecimal v) { this.資本金スコア = v; }

    public BigDecimal get合算返済比率評点() { return 合算返済比率評点; }
    public void set合算返済比率評点(BigDecimal v) { this.合算返済比率評点 = v; }

    public BigDecimal get合算返済比率係数() { return 合算返済比率係数; }
    public void set合算返済比率係数(BigDecimal v) { this.合算返済比率係数 = v; }

    public BigDecimal get合算返済比率スコア() { return 合算返済比率スコア; }
    public void set合算返済比率スコア(BigDecimal v) { this.合算返済比率スコア = v; }

    public BigDecimal get合算返済比率() { return 合算返済比率; }
    public void set合算返済比率(BigDecimal v) { this.合算返済比率 = v; }

    public BigDecimal get自己資金比率評点() { return 自己資金比率評点; }
    public void set自己資金比率評点(BigDecimal v) { this.自己資金比率評点 = v; }

    public BigDecimal get自己資金比率係数() { return 自己資金比率係数; }
    public void set自己資金比率係数(BigDecimal v) { this.自己資金比率係数 = v; }

    public BigDecimal get自己資金比率スコア() { return 自己資金比率スコア; }
    public void set自己資金比率スコア(BigDecimal v) { this.自己資金比率スコア = v; }

    public BigDecimal get自己資金比率() { return 自己資金比率; }
    public void set自己資金比率(BigDecimal v) { this.自己資金比率 = v; }

    public BigDecimal get調整定数() { return 調整定数; }
    public void set調整定数(BigDecimal v) { this.調整定数 = v; }

    public BigDecimal get若年単身者評点() { return 若年単身者評点; }
    public void set若年単身者評点(BigDecimal v) { this.若年単身者評点 = v; }

    public BigDecimal get若年単身者係数() { return 若年単身者係数; }
    public void set若年単身者係数(BigDecimal v) { this.若年単身者係数 = v; }

    public BigDecimal get若年単身者スコア() { return 若年単身者スコア; }
    public void set若年単身者スコア(BigDecimal v) { this.若年単身者スコア = v; }
}
