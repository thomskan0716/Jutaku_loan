package migration.domain.target;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Target: 審査モデル照会基本 (Scoring model inquiry - basic) - ITF_SMS.
 * 申込番号 first digit 2→3; 申込目的 converted by service. Other columns pass through.
 */
public class 審査モデル照会基本Target {

    private String 申込番号;
    private String 申込目的;
    private String イベント;
    private Date イベント日時;
    private BigDecimal 申込時年齢;
    private BigDecimal 実行時年齢;
    private BigDecimal 本人年収;
    private BigDecimal 合算者年収;
    private String 合算方法;
    private BigDecimal 申込金額;
    private BigDecimal 借入期間;
    private String 資金使途;
    private BigDecimal 勤続年数;
    private String 資本金区分;
    private BigDecimal 年間返済額;
    private BigDecimal 総借入額;
    private BigDecimal 自己資金;
    private BigDecimal 所要資金;
    private String 規定外項目;
    private BigDecimal 担保評価額;
    private String 保証料区分;
    private BigDecimal 先順位控除額;
    private BigDecimal 控除前担保評価額;
    private BigDecimal 同居家族数;

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String v) { this.申込番号 = v; }

    public String get申込目的() { return 申込目的; }
    public void set申込目的(String v) { this.申込目的 = v; }

    public String getイベント() { return イベント; }
    public void setイベント(String v) { this.イベント = v; }

    public Date getイベント日時() { return イベント日時; }
    public void setイベント日時(Date v) { this.イベント日時 = v; }

    public BigDecimal get申込時年齢() { return 申込時年齢; }
    public void set申込時年齢(BigDecimal v) { this.申込時年齢 = v; }

    public BigDecimal get実行時年齢() { return 実行時年齢; }
    public void set実行時年齢(BigDecimal v) { this.実行時年齢 = v; }

    public BigDecimal get本人年収() { return 本人年収; }
    public void set本人年収(BigDecimal v) { this.本人年収 = v; }

    public BigDecimal get合算者年収() { return 合算者年収; }
    public void set合算者年収(BigDecimal v) { this.合算者年収 = v; }

    public String get合算方法() { return 合算方法; }
    public void set合算方法(String v) { this.合算方法 = v; }

    public BigDecimal get申込金額() { return 申込金額; }
    public void set申込金額(BigDecimal v) { this.申込金額 = v; }

    public BigDecimal get借入期間() { return 借入期間; }
    public void set借入期間(BigDecimal v) { this.借入期間 = v; }

    public String get資金使途() { return 資金使途; }
    public void set資金使途(String v) { this.資金使途 = v; }

    public BigDecimal get勤続年数() { return 勤続年数; }
    public void set勤続年数(BigDecimal v) { this.勤続年数 = v; }

    public String get資本金区分() { return 資本金区分; }
    public void set資本金区分(String v) { this.資本金区分 = v; }

    public BigDecimal get年間返済額() { return 年間返済額; }
    public void set年間返済額(BigDecimal v) { this.年間返済額 = v; }

    public BigDecimal get総借入額() { return 総借入額; }
    public void set総借入額(BigDecimal v) { this.総借入額 = v; }

    public BigDecimal get自己資金() { return 自己資金; }
    public void set自己資金(BigDecimal v) { this.自己資金 = v; }

    public BigDecimal get所要資金() { return 所要資金; }
    public void set所要資金(BigDecimal v) { this.所要資金 = v; }

    public String get規定外項目() { return 規定外項目; }
    public void set規定外項目(String v) { this.規定外項目 = v; }

    public BigDecimal get担保評価額() { return 担保評価額; }
    public void set担保評価額(BigDecimal v) { this.担保評価額 = v; }

    public String get保証料区分() { return 保証料区分; }
    public void set保証料区分(String v) { this.保証料区分 = v; }

    public BigDecimal get先順位控除額() { return 先順位控除額; }
    public void set先順位控除額(BigDecimal v) { this.先順位控除額 = v; }

    public BigDecimal get控除前担保評価額() { return 控除前担保評価額; }
    public void set控除前担保評価額(BigDecimal v) { this.控除前担保評価額 = v; }

    public BigDecimal get同居家族数() { return 同居家族数; }
    public void set同居家族数(BigDecimal v) { this.同居家族数 = v; }
}
