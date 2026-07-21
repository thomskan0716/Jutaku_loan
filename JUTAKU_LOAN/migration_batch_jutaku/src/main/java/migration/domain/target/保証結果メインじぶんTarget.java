package migration.domain.target;

import java.util.Date;

/**
 * Target: 保証結果メインじぶん (Guarantee result main - au Jibun external linkage) - ITF_SMS.
 * Source is 保証結果メインじぶん. 申込番号 first digit 2->3; 申込目的 converted by service.
 *
 * <p>All columns are VARCHAR2 (String) except イベント日時 / 出力日時 (DATE).
 * Numbered columns (保証人1..5, その他借入1/2, 借入利用…1) use FULL-WIDTH digits in
 * the DB column name; Java properties keep half-width digits (mapped in the INSERT).</p>
 *
 * <p>NOTE: this is a partial column set; more columns will be added later.</p>
 */
public class 保証結果メインじぶんTarget {

    private String 申込番号;
    private String 申込目的;
    private String イベント;
    private Date イベント日時;
    private String 状態;
    private Date 出力日時;
    private String 保証会社案件番号;
    private String 外部案件番号;
    private String 申込人姓名カナ;
    private String 申込人姓名;
    private String 申込人性別;
    private String 申込人生年月日;
    private String 申込人郵便番号;
    private String 申込人住所;
    private String 申込人現住所電話番号;
    private String 申込人携帯電話番号;
    private String 申込人勤務先名称;
    private String 申込人勤務先郵便番号;
    private String 申込人勤務先住所;
    private String 申込人勤務先電話番号;
    private String 申込人勤続年数;
    private String 申込人雇用形態;
    private String 申込人業種;
    private String 申込人勤務先資本金;
    private String 申込人勤務先従業員数;
    private String 申込人勤務先職種役職;
    private String 申込人勤務先所属部署;
    private String 国家資格;
    private String 申込本人前年年収;
    private String 保有資産;
    private String ペアローン有無;
    private String ペアローン案件番号;

    private String 保証人1債務区分;
    private String 保証人1収入合算有無;
    private String 保証人1郵便番号;
    private String 保証人1住所;
    private String 保証人1姓名カナ;
    private String 保証人1姓名;
    private String 保証人1生年月日;
    private String 保証人1性別;
    private String 保証人1現住所電話番号;
    private String 保証人1携帯電話番号;
    private String 保証人1続柄;
    private String 保証人1勤務先名称;
    private String 保証人1前年年収;

    private String 保証人2債務区分;
    private String 保証人2収入合算有無;
    private String 保証人2郵便番号;
    private String 保証人2住所;
    private String 保証人2姓名カナ;
    private String 保証人2姓名;
    private String 保証人2生年月日;
    private String 保証人2性別;
    private String 保証人2現住所電話番号;
    private String 保証人2携帯電話番号;
    private String 保証人2続柄;
    private String 保証人2勤務先名称;
    private String 保証人2前年年収;

    private String 保証人3債務区分;
    private String 保証人3収入合算有無;
    private String 保証人3郵便番号;
    private String 保証人3住所;
    private String 保証人3姓名カナ;
    private String 保証人3姓名;
    private String 保証人3生年月日;
    private String 保証人3性別;
    private String 保証人3現住所電話番号;
    private String 保証人3携帯電話番号;
    private String 保証人3続柄;
    private String 保証人3勤務先名称;
    private String 保証人3前年年収;

    private String 保証人4債務区分;
    private String 保証人4収入合算有無;
    private String 保証人4郵便番号;
    private String 保証人4住所;
    private String 保証人4姓名カナ;
    private String 保証人4姓名;
    private String 保証人4生年月日;
    private String 保証人4性別;
    private String 保証人4現住所電話番号;
    private String 保証人4携帯電話番号;
    private String 保証人4続柄;
    private String 保証人4勤務先名称;
    private String 保証人4前年年収;

    private String 保証人5債務区分;
    private String 保証人5収入合算有無;
    private String 保証人5郵便番号;
    private String 保証人5住所;
    private String 保証人5姓名カナ;
    private String 保証人5姓名;
    private String 保証人5生年月日;
    private String 保証人5性別;
    private String 保証人5現住所電話番号;
    private String 保証人5携帯電話番号;
    private String 保証人5続柄;
    private String 保証人5勤務先名称;
    private String 保証人5前年年収;

    private String 資金使途;
    private String 建設地郵便番号;
    private String 建設地住所;
    private String 売買契約予定年月日;
    private String 建物新築年月日;
    private String 婚姻区分;
    private String 同居家族配偶者;
    private String 同居家族子供人数;
    private String 同居家族その他人数;
    private String 住居区分;
    private String 必要資金土地;
    private String 必要資金建物;
    private String 必要資金借替;
    private String 必要資金諸費用;
    private String 必要資金その他;
    private String 融資額;
    private String 返済方法;
    private String 希望返済期間;
    private String 希望額ボーナス分;
    private String 希望額毎月分;
    private String 融資実行希望年月日;

    private String その他借入1借入金額;
    private String その他借入1借入先;
    private String その他借入1返済期間;
    private String その他借入1金利;
    private String その他借入2借入金額;
    private String その他借入2借入先;
    private String その他借入2返済期間;
    private String その他借入2金利;

    private String 資産売却;
    private String 自己資金;
    private String 自己資金うち贈与資金;

    private String 借入利用先1;
    private String 借入利用種類1;
    private String 借入利用残高1;
    private String 借入年間返済額1;
    private String 借入利用限度額1;
    private String 借入解約予定1;
    private String 借入利用先2;
    private String 借入利用種類2;
    private String 借入利用残高2;
    private String 借入年間返済額2;
    private String 借入利用限度額2;
    private String 借入解約予定2;
    private String 借入利用先3;
    private String 借入利用種類3;
    private String 借入利用残高3;
    private String 借入年間返済額3;
    private String 借入利用限度額3;
    private String 借入解約予定3;

    private String 業者会社名;
    private String 業者所在地;
    private String 物件種類;
    private String 戸建地積延床面積;
    private String 戸建構造;
    private String マンション名称;
    private String マンション建物構造;
    private String マンション完成年月;
    private String マンション専有面積;
    private String 事前審査申請日;
    private String 本審査申請日;
    private String 返済負担率;
    private String 担保評価額;
    private String ＬＴＶ;
    private String セグメント;
    private String 保証承認番号;
    private String 保証承認日;
    private String 保証料率;
    private String 事前審査回答日;
    private String 事前審査結果;
    private String 事前審査担当者;
    private String 本審査回答日当初;
    private String 本審査結果当初;
    private String 本審査担当者;
    private String 本審査回答日最新;
    private String 本審査結果最新;
    private String 申込目的＿出力用;
    private String 保証会社意見欄;

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
    public Date get出力日時() { return 出力日時; }
    public void set出力日時(Date v) { this.出力日時 = v; }
    public String get保証会社案件番号() { return 保証会社案件番号; }
    public void set保証会社案件番号(String v) { this.保証会社案件番号 = v; }
    public String get外部案件番号() { return 外部案件番号; }
    public void set外部案件番号(String v) { this.外部案件番号 = v; }
    public String get申込人姓名カナ() { return 申込人姓名カナ; }
    public void set申込人姓名カナ(String v) { this.申込人姓名カナ = v; }
    public String get申込人姓名() { return 申込人姓名; }
    public void set申込人姓名(String v) { this.申込人姓名 = v; }
    public String get申込人性別() { return 申込人性別; }
    public void set申込人性別(String v) { this.申込人性別 = v; }
    public String get申込人生年月日() { return 申込人生年月日; }
    public void set申込人生年月日(String v) { this.申込人生年月日 = v; }
    public String get申込人郵便番号() { return 申込人郵便番号; }
    public void set申込人郵便番号(String v) { this.申込人郵便番号 = v; }
    public String get申込人住所() { return 申込人住所; }
    public void set申込人住所(String v) { this.申込人住所 = v; }
    public String get申込人現住所電話番号() { return 申込人現住所電話番号; }
    public void set申込人現住所電話番号(String v) { this.申込人現住所電話番号 = v; }
    public String get申込人携帯電話番号() { return 申込人携帯電話番号; }
    public void set申込人携帯電話番号(String v) { this.申込人携帯電話番号 = v; }
    public String get申込人勤務先名称() { return 申込人勤務先名称; }
    public void set申込人勤務先名称(String v) { this.申込人勤務先名称 = v; }
    public String get申込人勤務先郵便番号() { return 申込人勤務先郵便番号; }
    public void set申込人勤務先郵便番号(String v) { this.申込人勤務先郵便番号 = v; }
    public String get申込人勤務先住所() { return 申込人勤務先住所; }
    public void set申込人勤務先住所(String v) { this.申込人勤務先住所 = v; }
    public String get申込人勤務先電話番号() { return 申込人勤務先電話番号; }
    public void set申込人勤務先電話番号(String v) { this.申込人勤務先電話番号 = v; }
    public String get申込人勤続年数() { return 申込人勤続年数; }
    public void set申込人勤続年数(String v) { this.申込人勤続年数 = v; }
    public String get申込人雇用形態() { return 申込人雇用形態; }
    public void set申込人雇用形態(String v) { this.申込人雇用形態 = v; }
    public String get申込人業種() { return 申込人業種; }
    public void set申込人業種(String v) { this.申込人業種 = v; }
    public String get申込人勤務先資本金() { return 申込人勤務先資本金; }
    public void set申込人勤務先資本金(String v) { this.申込人勤務先資本金 = v; }
    public String get申込人勤務先従業員数() { return 申込人勤務先従業員数; }
    public void set申込人勤務先従業員数(String v) { this.申込人勤務先従業員数 = v; }
    public String get申込人勤務先職種役職() { return 申込人勤務先職種役職; }
    public void set申込人勤務先職種役職(String v) { this.申込人勤務先職種役職 = v; }
    public String get申込人勤務先所属部署() { return 申込人勤務先所属部署; }
    public void set申込人勤務先所属部署(String v) { this.申込人勤務先所属部署 = v; }
    public String get国家資格() { return 国家資格; }
    public void set国家資格(String v) { this.国家資格 = v; }
    public String get申込本人前年年収() { return 申込本人前年年収; }
    public void set申込本人前年年収(String v) { this.申込本人前年年収 = v; }
    public String get保有資産() { return 保有資産; }
    public void set保有資産(String v) { this.保有資産 = v; }
    public String getペアローン有無() { return ペアローン有無; }
    public void setペアローン有無(String v) { this.ペアローン有無 = v; }
    public String getペアローン案件番号() { return ペアローン案件番号; }
    public void setペアローン案件番号(String v) { this.ペアローン案件番号 = v; }

    public String get保証人1債務区分() { return 保証人1債務区分; }
    public void set保証人1債務区分(String v) { this.保証人1債務区分 = v; }
    public String get保証人1収入合算有無() { return 保証人1収入合算有無; }
    public void set保証人1収入合算有無(String v) { this.保証人1収入合算有無 = v; }
    public String get保証人1郵便番号() { return 保証人1郵便番号; }
    public void set保証人1郵便番号(String v) { this.保証人1郵便番号 = v; }
    public String get保証人1住所() { return 保証人1住所; }
    public void set保証人1住所(String v) { this.保証人1住所 = v; }
    public String get保証人1姓名カナ() { return 保証人1姓名カナ; }
    public void set保証人1姓名カナ(String v) { this.保証人1姓名カナ = v; }
    public String get保証人1姓名() { return 保証人1姓名; }
    public void set保証人1姓名(String v) { this.保証人1姓名 = v; }
    public String get保証人1生年月日() { return 保証人1生年月日; }
    public void set保証人1生年月日(String v) { this.保証人1生年月日 = v; }
    public String get保証人1性別() { return 保証人1性別; }
    public void set保証人1性別(String v) { this.保証人1性別 = v; }
    public String get保証人1現住所電話番号() { return 保証人1現住所電話番号; }
    public void set保証人1現住所電話番号(String v) { this.保証人1現住所電話番号 = v; }
    public String get保証人1携帯電話番号() { return 保証人1携帯電話番号; }
    public void set保証人1携帯電話番号(String v) { this.保証人1携帯電話番号 = v; }
    public String get保証人1続柄() { return 保証人1続柄; }
    public void set保証人1続柄(String v) { this.保証人1続柄 = v; }
    public String get保証人1勤務先名称() { return 保証人1勤務先名称; }
    public void set保証人1勤務先名称(String v) { this.保証人1勤務先名称 = v; }
    public String get保証人1前年年収() { return 保証人1前年年収; }
    public void set保証人1前年年収(String v) { this.保証人1前年年収 = v; }

    public String get保証人2債務区分() { return 保証人2債務区分; }
    public void set保証人2債務区分(String v) { this.保証人2債務区分 = v; }
    public String get保証人2収入合算有無() { return 保証人2収入合算有無; }
    public void set保証人2収入合算有無(String v) { this.保証人2収入合算有無 = v; }
    public String get保証人2郵便番号() { return 保証人2郵便番号; }
    public void set保証人2郵便番号(String v) { this.保証人2郵便番号 = v; }
    public String get保証人2住所() { return 保証人2住所; }
    public void set保証人2住所(String v) { this.保証人2住所 = v; }
    public String get保証人2姓名カナ() { return 保証人2姓名カナ; }
    public void set保証人2姓名カナ(String v) { this.保証人2姓名カナ = v; }
    public String get保証人2姓名() { return 保証人2姓名; }
    public void set保証人2姓名(String v) { this.保証人2姓名 = v; }
    public String get保証人2生年月日() { return 保証人2生年月日; }
    public void set保証人2生年月日(String v) { this.保証人2生年月日 = v; }
    public String get保証人2性別() { return 保証人2性別; }
    public void set保証人2性別(String v) { this.保証人2性別 = v; }
    public String get保証人2現住所電話番号() { return 保証人2現住所電話番号; }
    public void set保証人2現住所電話番号(String v) { this.保証人2現住所電話番号 = v; }
    public String get保証人2携帯電話番号() { return 保証人2携帯電話番号; }
    public void set保証人2携帯電話番号(String v) { this.保証人2携帯電話番号 = v; }
    public String get保証人2続柄() { return 保証人2続柄; }
    public void set保証人2続柄(String v) { this.保証人2続柄 = v; }
    public String get保証人2勤務先名称() { return 保証人2勤務先名称; }
    public void set保証人2勤務先名称(String v) { this.保証人2勤務先名称 = v; }
    public String get保証人2前年年収() { return 保証人2前年年収; }
    public void set保証人2前年年収(String v) { this.保証人2前年年収 = v; }

    public String get保証人3債務区分() { return 保証人3債務区分; }
    public void set保証人3債務区分(String v) { this.保証人3債務区分 = v; }
    public String get保証人3収入合算有無() { return 保証人3収入合算有無; }
    public void set保証人3収入合算有無(String v) { this.保証人3収入合算有無 = v; }
    public String get保証人3郵便番号() { return 保証人3郵便番号; }
    public void set保証人3郵便番号(String v) { this.保証人3郵便番号 = v; }
    public String get保証人3住所() { return 保証人3住所; }
    public void set保証人3住所(String v) { this.保証人3住所 = v; }
    public String get保証人3姓名カナ() { return 保証人3姓名カナ; }
    public void set保証人3姓名カナ(String v) { this.保証人3姓名カナ = v; }
    public String get保証人3姓名() { return 保証人3姓名; }
    public void set保証人3姓名(String v) { this.保証人3姓名 = v; }
    public String get保証人3生年月日() { return 保証人3生年月日; }
    public void set保証人3生年月日(String v) { this.保証人3生年月日 = v; }
    public String get保証人3性別() { return 保証人3性別; }
    public void set保証人3性別(String v) { this.保証人3性別 = v; }
    public String get保証人3現住所電話番号() { return 保証人3現住所電話番号; }
    public void set保証人3現住所電話番号(String v) { this.保証人3現住所電話番号 = v; }
    public String get保証人3携帯電話番号() { return 保証人3携帯電話番号; }
    public void set保証人3携帯電話番号(String v) { this.保証人3携帯電話番号 = v; }
    public String get保証人3続柄() { return 保証人3続柄; }
    public void set保証人3続柄(String v) { this.保証人3続柄 = v; }
    public String get保証人3勤務先名称() { return 保証人3勤務先名称; }
    public void set保証人3勤務先名称(String v) { this.保証人3勤務先名称 = v; }
    public String get保証人3前年年収() { return 保証人3前年年収; }
    public void set保証人3前年年収(String v) { this.保証人3前年年収 = v; }

    public String get保証人4債務区分() { return 保証人4債務区分; }
    public void set保証人4債務区分(String v) { this.保証人4債務区分 = v; }
    public String get保証人4収入合算有無() { return 保証人4収入合算有無; }
    public void set保証人4収入合算有無(String v) { this.保証人4収入合算有無 = v; }
    public String get保証人4郵便番号() { return 保証人4郵便番号; }
    public void set保証人4郵便番号(String v) { this.保証人4郵便番号 = v; }
    public String get保証人4住所() { return 保証人4住所; }
    public void set保証人4住所(String v) { this.保証人4住所 = v; }
    public String get保証人4姓名カナ() { return 保証人4姓名カナ; }
    public void set保証人4姓名カナ(String v) { this.保証人4姓名カナ = v; }
    public String get保証人4姓名() { return 保証人4姓名; }
    public void set保証人4姓名(String v) { this.保証人4姓名 = v; }
    public String get保証人4生年月日() { return 保証人4生年月日; }
    public void set保証人4生年月日(String v) { this.保証人4生年月日 = v; }
    public String get保証人4性別() { return 保証人4性別; }
    public void set保証人4性別(String v) { this.保証人4性別 = v; }
    public String get保証人4現住所電話番号() { return 保証人4現住所電話番号; }
    public void set保証人4現住所電話番号(String v) { this.保証人4現住所電話番号 = v; }
    public String get保証人4携帯電話番号() { return 保証人4携帯電話番号; }
    public void set保証人4携帯電話番号(String v) { this.保証人4携帯電話番号 = v; }
    public String get保証人4続柄() { return 保証人4続柄; }
    public void set保証人4続柄(String v) { this.保証人4続柄 = v; }
    public String get保証人4勤務先名称() { return 保証人4勤務先名称; }
    public void set保証人4勤務先名称(String v) { this.保証人4勤務先名称 = v; }
    public String get保証人4前年年収() { return 保証人4前年年収; }
    public void set保証人4前年年収(String v) { this.保証人4前年年収 = v; }

    public String get保証人5債務区分() { return 保証人5債務区分; }
    public void set保証人5債務区分(String v) { this.保証人5債務区分 = v; }
    public String get保証人5収入合算有無() { return 保証人5収入合算有無; }
    public void set保証人5収入合算有無(String v) { this.保証人5収入合算有無 = v; }
    public String get保証人5郵便番号() { return 保証人5郵便番号; }
    public void set保証人5郵便番号(String v) { this.保証人5郵便番号 = v; }
    public String get保証人5住所() { return 保証人5住所; }
    public void set保証人5住所(String v) { this.保証人5住所 = v; }
    public String get保証人5姓名カナ() { return 保証人5姓名カナ; }
    public void set保証人5姓名カナ(String v) { this.保証人5姓名カナ = v; }
    public String get保証人5姓名() { return 保証人5姓名; }
    public void set保証人5姓名(String v) { this.保証人5姓名 = v; }
    public String get保証人5生年月日() { return 保証人5生年月日; }
    public void set保証人5生年月日(String v) { this.保証人5生年月日 = v; }
    public String get保証人5性別() { return 保証人5性別; }
    public void set保証人5性別(String v) { this.保証人5性別 = v; }
    public String get保証人5現住所電話番号() { return 保証人5現住所電話番号; }
    public void set保証人5現住所電話番号(String v) { this.保証人5現住所電話番号 = v; }
    public String get保証人5携帯電話番号() { return 保証人5携帯電話番号; }
    public void set保証人5携帯電話番号(String v) { this.保証人5携帯電話番号 = v; }
    public String get保証人5続柄() { return 保証人5続柄; }
    public void set保証人5続柄(String v) { this.保証人5続柄 = v; }
    public String get保証人5勤務先名称() { return 保証人5勤務先名称; }
    public void set保証人5勤務先名称(String v) { this.保証人5勤務先名称 = v; }
    public String get保証人5前年年収() { return 保証人5前年年収; }
    public void set保証人5前年年収(String v) { this.保証人5前年年収 = v; }

    public String get資金使途() { return 資金使途; }
    public void set資金使途(String v) { this.資金使途 = v; }
    public String get建設地郵便番号() { return 建設地郵便番号; }
    public void set建設地郵便番号(String v) { this.建設地郵便番号 = v; }
    public String get建設地住所() { return 建設地住所; }
    public void set建設地住所(String v) { this.建設地住所 = v; }
    public String get売買契約予定年月日() { return 売買契約予定年月日; }
    public void set売買契約予定年月日(String v) { this.売買契約予定年月日 = v; }
    public String get建物新築年月日() { return 建物新築年月日; }
    public void set建物新築年月日(String v) { this.建物新築年月日 = v; }
    public String get婚姻区分() { return 婚姻区分; }
    public void set婚姻区分(String v) { this.婚姻区分 = v; }
    public String get同居家族配偶者() { return 同居家族配偶者; }
    public void set同居家族配偶者(String v) { this.同居家族配偶者 = v; }
    public String get同居家族子供人数() { return 同居家族子供人数; }
    public void set同居家族子供人数(String v) { this.同居家族子供人数 = v; }
    public String get同居家族その他人数() { return 同居家族その他人数; }
    public void set同居家族その他人数(String v) { this.同居家族その他人数 = v; }
    public String get住居区分() { return 住居区分; }
    public void set住居区分(String v) { this.住居区分 = v; }
    public String get必要資金土地() { return 必要資金土地; }
    public void set必要資金土地(String v) { this.必要資金土地 = v; }
    public String get必要資金建物() { return 必要資金建物; }
    public void set必要資金建物(String v) { this.必要資金建物 = v; }
    public String get必要資金借替() { return 必要資金借替; }
    public void set必要資金借替(String v) { this.必要資金借替 = v; }
    public String get必要資金諸費用() { return 必要資金諸費用; }
    public void set必要資金諸費用(String v) { this.必要資金諸費用 = v; }
    public String get必要資金その他() { return 必要資金その他; }
    public void set必要資金その他(String v) { this.必要資金その他 = v; }
    public String get融資額() { return 融資額; }
    public void set融資額(String v) { this.融資額 = v; }
    public String get返済方法() { return 返済方法; }
    public void set返済方法(String v) { this.返済方法 = v; }
    public String get希望返済期間() { return 希望返済期間; }
    public void set希望返済期間(String v) { this.希望返済期間 = v; }
    public String get希望額ボーナス分() { return 希望額ボーナス分; }
    public void set希望額ボーナス分(String v) { this.希望額ボーナス分 = v; }
    public String get希望額毎月分() { return 希望額毎月分; }
    public void set希望額毎月分(String v) { this.希望額毎月分 = v; }
    public String get融資実行希望年月日() { return 融資実行希望年月日; }
    public void set融資実行希望年月日(String v) { this.融資実行希望年月日 = v; }

    public String getその他借入1借入金額() { return その他借入1借入金額; }
    public void setその他借入1借入金額(String v) { this.その他借入1借入金額 = v; }
    public String getその他借入1借入先() { return その他借入1借入先; }
    public void setその他借入1借入先(String v) { this.その他借入1借入先 = v; }
    public String getその他借入1返済期間() { return その他借入1返済期間; }
    public void setその他借入1返済期間(String v) { this.その他借入1返済期間 = v; }
    public String getその他借入1金利() { return その他借入1金利; }
    public void setその他借入1金利(String v) { this.その他借入1金利 = v; }
    public String getその他借入2借入金額() { return その他借入2借入金額; }
    public void setその他借入2借入金額(String v) { this.その他借入2借入金額 = v; }
    public String getその他借入2借入先() { return その他借入2借入先; }
    public void setその他借入2借入先(String v) { this.その他借入2借入先 = v; }
    public String getその他借入2返済期間() { return その他借入2返済期間; }
    public void setその他借入2返済期間(String v) { this.その他借入2返済期間 = v; }
    public String getその他借入2金利() { return その他借入2金利; }
    public void setその他借入2金利(String v) { this.その他借入2金利 = v; }

    public String get資産売却() { return 資産売却; }
    public void set資産売却(String v) { this.資産売却 = v; }
    public String get自己資金() { return 自己資金; }
    public void set自己資金(String v) { this.自己資金 = v; }
    public String get自己資金うち贈与資金() { return 自己資金うち贈与資金; }
    public void set自己資金うち贈与資金(String v) { this.自己資金うち贈与資金 = v; }

    public String get借入利用先1() { return 借入利用先1; }
    public void set借入利用先1(String v) { this.借入利用先1 = v; }
    public String get借入利用種類1() { return 借入利用種類1; }
    public void set借入利用種類1(String v) { this.借入利用種類1 = v; }
    public String get借入利用残高1() { return 借入利用残高1; }
    public void set借入利用残高1(String v) { this.借入利用残高1 = v; }
    public String get借入年間返済額1() { return 借入年間返済額1; }
    public void set借入年間返済額1(String v) { this.借入年間返済額1 = v; }
    public String get借入利用限度額1() { return 借入利用限度額1; }
    public void set借入利用限度額1(String v) { this.借入利用限度額1 = v; }
    public String get借入解約予定1() { return 借入解約予定1; }
    public void set借入解約予定1(String v) { this.借入解約予定1 = v; }
    public String get借入利用先2() { return 借入利用先2; }
    public void set借入利用先2(String v) { this.借入利用先2 = v; }
    public String get借入利用種類2() { return 借入利用種類2; }
    public void set借入利用種類2(String v) { this.借入利用種類2 = v; }
    public String get借入利用残高2() { return 借入利用残高2; }
    public void set借入利用残高2(String v) { this.借入利用残高2 = v; }
    public String get借入年間返済額2() { return 借入年間返済額2; }
    public void set借入年間返済額2(String v) { this.借入年間返済額2 = v; }
    public String get借入利用限度額2() { return 借入利用限度額2; }
    public void set借入利用限度額2(String v) { this.借入利用限度額2 = v; }
    public String get借入解約予定2() { return 借入解約予定2; }
    public void set借入解約予定2(String v) { this.借入解約予定2 = v; }
    public String get借入利用先3() { return 借入利用先3; }
    public void set借入利用先3(String v) { this.借入利用先3 = v; }
    public String get借入利用種類3() { return 借入利用種類3; }
    public void set借入利用種類3(String v) { this.借入利用種類3 = v; }
    public String get借入利用残高3() { return 借入利用残高3; }
    public void set借入利用残高3(String v) { this.借入利用残高3 = v; }
    public String get借入年間返済額3() { return 借入年間返済額3; }
    public void set借入年間返済額3(String v) { this.借入年間返済額3 = v; }
    public String get借入利用限度額3() { return 借入利用限度額3; }
    public void set借入利用限度額3(String v) { this.借入利用限度額3 = v; }
    public String get借入解約予定3() { return 借入解約予定3; }
    public void set借入解約予定3(String v) { this.借入解約予定3 = v; }

    public String get業者会社名() { return 業者会社名; }
    public void set業者会社名(String v) { this.業者会社名 = v; }
    public String get業者所在地() { return 業者所在地; }
    public void set業者所在地(String v) { this.業者所在地 = v; }
    public String get物件種類() { return 物件種類; }
    public void set物件種類(String v) { this.物件種類 = v; }
    public String get戸建地積延床面積() { return 戸建地積延床面積; }
    public void set戸建地積延床面積(String v) { this.戸建地積延床面積 = v; }
    public String get戸建構造() { return 戸建構造; }
    public void set戸建構造(String v) { this.戸建構造 = v; }
    public String getマンション名称() { return マンション名称; }
    public void setマンション名称(String v) { this.マンション名称 = v; }
    public String getマンション建物構造() { return マンション建物構造; }
    public void setマンション建物構造(String v) { this.マンション建物構造 = v; }
    public String getマンション完成年月() { return マンション完成年月; }
    public void setマンション完成年月(String v) { this.マンション完成年月 = v; }
    public String getマンション専有面積() { return マンション専有面積; }
    public void setマンション専有面積(String v) { this.マンション専有面積 = v; }
    public String get事前審査申請日() { return 事前審査申請日; }
    public void set事前審査申請日(String v) { this.事前審査申請日 = v; }
    public String get本審査申請日() { return 本審査申請日; }
    public void set本審査申請日(String v) { this.本審査申請日 = v; }
    public String get返済負担率() { return 返済負担率; }
    public void set返済負担率(String v) { this.返済負担率 = v; }
    public String get担保評価額() { return 担保評価額; }
    public void set担保評価額(String v) { this.担保評価額 = v; }
    public String getＬＴＶ() { return ＬＴＶ; }
    public void setＬＴＶ(String v) { this.ＬＴＶ = v; }
    public String getセグメント() { return セグメント; }
    public void setセグメント(String v) { this.セグメント = v; }
    public String get保証承認番号() { return 保証承認番号; }
    public void set保証承認番号(String v) { this.保証承認番号 = v; }
    public String get保証承認日() { return 保証承認日; }
    public void set保証承認日(String v) { this.保証承認日 = v; }
    public String get保証料率() { return 保証料率; }
    public void set保証料率(String v) { this.保証料率 = v; }
    public String get事前審査回答日() { return 事前審査回答日; }
    public void set事前審査回答日(String v) { this.事前審査回答日 = v; }
    public String get事前審査結果() { return 事前審査結果; }
    public void set事前審査結果(String v) { this.事前審査結果 = v; }
    public String get事前審査担当者() { return 事前審査担当者; }
    public void set事前審査担当者(String v) { this.事前審査担当者 = v; }
    public String get本審査回答日当初() { return 本審査回答日当初; }
    public void set本審査回答日当初(String v) { this.本審査回答日当初 = v; }
    public String get本審査結果当初() { return 本審査結果当初; }
    public void set本審査結果当初(String v) { this.本審査結果当初 = v; }
    public String get本審査担当者() { return 本審査担当者; }
    public void set本審査担当者(String v) { this.本審査担当者 = v; }
    public String get本審査回答日最新() { return 本審査回答日最新; }
    public void set本審査回答日最新(String v) { this.本審査回答日最新 = v; }
    public String get本審査結果最新() { return 本審査結果最新; }
    public void set本審査結果最新(String v) { this.本審査結果最新 = v; }
    public String get申込目的＿出力用() { return 申込目的＿出力用; }
    public void set申込目的＿出力用(String v) { this.申込目的＿出力用 = v; }
    public String get保証会社意見欄() { return 保証会社意見欄; }
    public void set保証会社意見欄(String v) { this.保証会社意見欄 = v; }
}
