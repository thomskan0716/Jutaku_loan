package migration.domain.target;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Target: 住宅ローン不正検知照会 (Housing loan fraud detection inquiry) - ITF_SMS.
 * 申込番号 first digit 2→3; 申込目的 converted by service. Other columns pass through.
 * Column names 事前＿申込金額 / 事前＿借入期間 / 総借入額＿総借入額 use full-width ＿ (U+FF3F).
 */
public class 住宅ローン不正検知照会Target {

    private String 申込番号;
    private String 申込目的;
    private String イベント;
    private Date イベント日時;
    private String 状態;
    private String 状態説明;
    private BigDecimal 優先度;
    private String リクエストＩＤ;
    private BigDecimal 事前＿申込金額;
    private BigDecimal 事前＿借入期間;
    private BigDecimal 年齢;
    private BigDecimal 勤務先従業員数;
    private BigDecimal 勤続年数;
    private BigDecimal 歩合給区分;
    private BigDecimal 年収;
    private BigDecimal 自己資金;
    private BigDecimal 家賃;
    private BigDecimal 居住年数;
    private BigDecimal 同居予定合計;
    private BigDecimal 同居予定配偶者;
    private BigDecimal 土地担保時価額;
    private BigDecimal 建物担保時価額;
    private BigDecimal 借入総額;
    private BigDecimal 年収倍率;
    private BigDecimal 返済比率;
    private BigDecimal 合算年収;
    private BigDecimal 年間返済額;
    private BigDecimal 融資倍率;
    private BigDecimal 総借入額＿総借入額;
    private String 職業区分コード;
    private String 職種役職コード;
    private String 勤務先資本金区分;
    private String 資金使途;
    private String 住居区分名称コード;
    private String セグメント;
    private String 住所郵便番号;
    private String 勤務先郵便番号;
    private String 物件郵便番号;

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String v) { this.申込番号 = v; }

    public String get申込目的() { return 申込目的; }
    public void set申込目的(String v) { this.申込目的 = v; }

    public String getイベント() { return イベント; }
    public void setイベント(String v) { this.イベント = v; }

    public Date getイベント日時() { return イベント日時; }
    public void setイベント日時(Date v) { this.イベント日時 = v; }

    public String get状態() { return 状態; }
    public void set状態(String v) { this.状態 = v; }

    public String get状態説明() { return 状態説明; }
    public void set状態説明(String v) { this.状態説明 = v; }

    public BigDecimal get優先度() { return 優先度; }
    public void set優先度(BigDecimal v) { this.優先度 = v; }

    public String getリクエストＩＤ() { return リクエストＩＤ; }
    public void setリクエストＩＤ(String v) { this.リクエストＩＤ = v; }

    public BigDecimal get事前＿申込金額() { return 事前＿申込金額; }
    public void set事前＿申込金額(BigDecimal v) { this.事前＿申込金額 = v; }

    public BigDecimal get事前＿借入期間() { return 事前＿借入期間; }
    public void set事前＿借入期間(BigDecimal v) { this.事前＿借入期間 = v; }

    public BigDecimal get年齢() { return 年齢; }
    public void set年齢(BigDecimal v) { this.年齢 = v; }

    public BigDecimal get勤務先従業員数() { return 勤務先従業員数; }
    public void set勤務先従業員数(BigDecimal v) { this.勤務先従業員数 = v; }

    public BigDecimal get勤続年数() { return 勤続年数; }
    public void set勤続年数(BigDecimal v) { this.勤続年数 = v; }

    public BigDecimal get歩合給区分() { return 歩合給区分; }
    public void set歩合給区分(BigDecimal v) { this.歩合給区分 = v; }

    public BigDecimal get年収() { return 年収; }
    public void set年収(BigDecimal v) { this.年収 = v; }

    public BigDecimal get自己資金() { return 自己資金; }
    public void set自己資金(BigDecimal v) { this.自己資金 = v; }

    public BigDecimal get家賃() { return 家賃; }
    public void set家賃(BigDecimal v) { this.家賃 = v; }

    public BigDecimal get居住年数() { return 居住年数; }
    public void set居住年数(BigDecimal v) { this.居住年数 = v; }

    public BigDecimal get同居予定合計() { return 同居予定合計; }
    public void set同居予定合計(BigDecimal v) { this.同居予定合計 = v; }

    public BigDecimal get同居予定配偶者() { return 同居予定配偶者; }
    public void set同居予定配偶者(BigDecimal v) { this.同居予定配偶者 = v; }

    public BigDecimal get土地担保時価額() { return 土地担保時価額; }
    public void set土地担保時価額(BigDecimal v) { this.土地担保時価額 = v; }

    public BigDecimal get建物担保時価額() { return 建物担保時価額; }
    public void set建物担保時価額(BigDecimal v) { this.建物担保時価額 = v; }

    public BigDecimal get借入総額() { return 借入総額; }
    public void set借入総額(BigDecimal v) { this.借入総額 = v; }

    public BigDecimal get年収倍率() { return 年収倍率; }
    public void set年収倍率(BigDecimal v) { this.年収倍率 = v; }

    public BigDecimal get返済比率() { return 返済比率; }
    public void set返済比率(BigDecimal v) { this.返済比率 = v; }

    public BigDecimal get合算年収() { return 合算年収; }
    public void set合算年収(BigDecimal v) { this.合算年収 = v; }

    public BigDecimal get年間返済額() { return 年間返済額; }
    public void set年間返済額(BigDecimal v) { this.年間返済額 = v; }

    public BigDecimal get融資倍率() { return 融資倍率; }
    public void set融資倍率(BigDecimal v) { this.融資倍率 = v; }

    public BigDecimal get総借入額＿総借入額() { return 総借入額＿総借入額; }
    public void set総借入額＿総借入額(BigDecimal v) { this.総借入額＿総借入額 = v; }

    public String get職業区分コード() { return 職業区分コード; }
    public void set職業区分コード(String v) { this.職業区分コード = v; }

    public String get職種役職コード() { return 職種役職コード; }
    public void set職種役職コード(String v) { this.職種役職コード = v; }

    public String get勤務先資本金区分() { return 勤務先資本金区分; }
    public void set勤務先資本金区分(String v) { this.勤務先資本金区分 = v; }

    public String get資金使途() { return 資金使途; }
    public void set資金使途(String v) { this.資金使途 = v; }

    public String get住居区分名称コード() { return 住居区分名称コード; }
    public void set住居区分名称コード(String v) { this.住居区分名称コード = v; }

    public String getセグメント() { return セグメント; }
    public void setセグメント(String v) { this.セグメント = v; }

    public String get住所郵便番号() { return 住所郵便番号; }
    public void set住所郵便番号(String v) { this.住所郵便番号 = v; }

    public String get勤務先郵便番号() { return 勤務先郵便番号; }
    public void set勤務先郵便番号(String v) { this.勤務先郵便番号 = v; }

    public String get物件郵便番号() { return 物件郵便番号; }
    public void set物件郵便番号(String v) { this.物件郵便番号 = v; }
}
