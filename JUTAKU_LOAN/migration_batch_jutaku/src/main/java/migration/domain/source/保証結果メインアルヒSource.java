package migration.domain.source;

import java.util.Date;

/**
 * Source: 保証結果メインアルヒ (Guarantee result main - ARUHI external linkage) - SZB_SMS.
 * 外部連携. 1:N per (申込番号, 申込目的), keyed by イベント/イベント日時.
 *
 * <p>All columns are VARCHAR2 (String) except イベント日時 / 出力日時 (DATE).
 * Numbered columns (連債者理由1..3, 敷地権利区分1..4, 担保提供者1..4) use FULL-WIDTH digits
 * in the DB column name; Java properties keep half-width digits (mapped in the resultMap).</p>
 *
 * <p>NOTE: this is a partial column set; more columns will be added later.</p>
 */
public class 保証結果メインアルヒSource {

    private String 申込番号;
    private String 申込目的;
    private String イベント;
    private Date イベント日時;
    private String 状態;
    private Date 出力日時;
    private String 保証会社案件番号;
    private String ＡＲＵＨＩ証書番号;
    private String 申込人姓名カナ;
    private String 申込人姓名;
    private String 申込人性別;
    private String 申込人国籍;
    private String 申込人生年月日;
    private String 申込人郵便番号;
    private String 申込人住所;
    private String 申込人現住所電話番号;
    private String 申込人携帯電話番号;
    private String 申込人勤務先名称カナ;
    private String 申込人勤務先名称;
    private String 申込人勤務先郵便番号;
    private String 申込人勤務先住所;
    private String 申込人勤務先電話番号;
    private String 申込人勤続年数;
    private String 申込人勤続月数;
    private String 申込人雇用形態;
    private String 申込人業種;
    private String 申込人勤務先事業内容;
    private String 申込人勤務先所属部署;
    private String 申込人職種;
    private String 申込人勤務先役職;
    private String 申込人勤務先資本金;
    private String 申込人収入種類;
    private String 申込人前年年収;
    private String 申込人前年年収内ボーナス;
    private String 申込人前々年年収;
    private String 連帯債務者有無;
    private String 連債者理由1;
    private String 連債者理由2;
    private String 連債者理由3;
    private String 連債者続柄;
    private String 連債者続柄その他;
    private String 連債者姓名カナ;
    private String 連債者姓名;
    private String 連債者性別;
    private String 連債者国籍;
    private String 連債者生年月日;
    private String 連債者郵便番号;
    private String 連債者住所;
    private String 連債者自宅電話番号;
    private String 連債者携帯電話番号;
    private String 連債者勤務先名カナ;
    private String 連債者勤務先名;
    private String 連債者勤務先郵便番号;
    private String 連債者勤務先住所;
    private String 連債者勤務先電話番号;
    private String 連債者勤続年数;
    private String 連債者勤続月数;
    private String 連債者雇用形態;
    private String 連債者業種;
    private String 連債者勤務先事業内容;
    private String 連債者勤務先所属部署;
    private String 連債者勤務先資本金;
    private String 収入合算者前年年収;
    private String 連債者前年年収;
    private String 収入合算者前年年収内ボーナス;
    private String 収入合算者前々年年収;
    private String 物件種類;
    private String 建設地郵便番号;
    private String 建設地住所;
    private String 敷地面積;
    private String 敷地権利区分1;
    private String 敷地権利区分2;
    private String 敷地権利区分3;
    private String 敷地権利区分4;
    private String 物件構造;
    private String 建物床面積＿住宅部分;
    private String 建物床面積＿非住宅部分;
    private String 売買契約予定年月日;
    private String 建物新築年月日;
    private String 申込人物件通勤時間;
    private String 連債者物件通勤時間;
    private String 工事請負会社名称カナ;
    private String 工事請負会社名称;
    private String 工事請負会社担当者;
    private String 工事請負会社郵便番号;
    private String 工事請負会社住所;
    private String 工事請負業者電話番号;
    private String 販売代理業者名カナ;
    private String 販売代理業者名称;
    private String 販売代理会社担当者;
    private String 販売代理会社郵便番号;
    private String 販売代理会社住所;
    private String 販売代理業者電話番号;
    private String リフォーム会社名称カナ;
    private String リフォーム会社名称;
    private String リフォーム会社担当者;
    private String リフォーム会社郵便番号;
    private String リフォーム会社住所;
    private String リフォーム業者電話番号;
    private String 現在住宅の建て方;
    private String 現在住宅種類;
    private String 現在住宅面積;
    private String 住宅必要理由;
    private String 物件共有予定土地;
    private String 物件共有予定建物;
    private String 担保提供者人数;
    private String 担保提供者1建物土地;
    private String 担保提供者1続柄;
    private String 担保提供者1続柄その他;
    private String 担保提供者1姓名カナ;
    private String 担保提供者1姓名;
    private String 担保提供者1生年月日;
    private String 担保提供者1郵便番号;
    private String 担保提供者1住所;
    private String 担保提供者2建物土地;
    private String 担保提供者2続柄;
    private String 担保提供者2続柄その他;
    private String 担保提供者2姓名カナ;
    private String 担保提供者2姓名;
    private String 担保提供者2生年月日;
    private String 担保提供者2郵便番号;
    private String 担保提供者2住所;
    private String 担保提供者3建物土地;
    private String 担保提供者3続柄;
    private String 担保提供者3続柄その他;
    private String 担保提供者3姓名カナ;
    private String 担保提供者3姓名;
    private String 担保提供者3生年月日;
    private String 担保提供者3郵便番号;
    private String 担保提供者3住所;
    private String 担保提供者4建物土地;
    private String 担保提供者4続柄;
    private String 担保提供者4続柄その他;
    private String 担保提供者4姓名カナ;
    private String 担保提供者4姓名;
    private String 担保提供者4生年月日;
    private String 担保提供者4郵便番号;
    private String 担保提供者4住所;

    private String 物件入居予定者数;
    private String 物件入居家族構成;
    private String 居住区分;
    private String 建築購入費;
    private String 土地取得費;
    private String 融資額;
    private String 予備1;
    private String 返済方法;
    private String 希望返済期間;
    private String ボーナス払希望有無;
    private String ボーナス払月;
    private String 希望額ボーナス分;
    private String 融資実行希望年月日;
    private String 借入公的;
    private String 借入公的＿借入先;
    private String 借入公的＿返済期間;
    private String 借入公的＿金利;
    private String 借入公的＿毎月返済額;
    private String 借入民間;
    private String 借入民間＿借入先;
    private String 借入民間＿返済期間;
    private String 借入民間＿金利;
    private String 借入民間＿毎月返済額;
    private String 借入勤務先;
    private String 借入勤務先＿返済期間;
    private String 借入勤務先＿毎月返済額;
    private String 借入親等;
    private String 借入親等＿借入先;
    private String 借入親等＿返済期間;
    private String 借入親等＿毎月返済額;
    private String 借入返済土地;
    private String 借入返済土地＿返済期間;
    private String 借入返済土地＿毎月返済額;
    private String 手持金;
    private String 住宅取得以外＿借入件数;
    private String 住宅取得以外＿借入金額;
    private String 住宅取得以外＿毎月返済額;
    private String 事前審査申請日;
    private String 本審査申請日;
    private String 申込受理日;
    private String 定期借地権;
    private String 前払賃料;
    private String 買戻権;
    private String 保留地物件サイン;
    private String 金消契約年月日;
    private String 審査金利;
    private String 返済負担率;
    private String 担保評価額;
    private String アルヒ審査担当者;
    private String ＬＴＶ;
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
    public String getＡＲＵＨＩ証書番号() { return ＡＲＵＨＩ証書番号; }
    public void setＡＲＵＨＩ証書番号(String v) { this.ＡＲＵＨＩ証書番号 = v; }
    public String get申込人姓名カナ() { return 申込人姓名カナ; }
    public void set申込人姓名カナ(String v) { this.申込人姓名カナ = v; }
    public String get申込人姓名() { return 申込人姓名; }
    public void set申込人姓名(String v) { this.申込人姓名 = v; }
    public String get申込人性別() { return 申込人性別; }
    public void set申込人性別(String v) { this.申込人性別 = v; }
    public String get申込人国籍() { return 申込人国籍; }
    public void set申込人国籍(String v) { this.申込人国籍 = v; }
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
    public String get申込人勤務先名称カナ() { return 申込人勤務先名称カナ; }
    public void set申込人勤務先名称カナ(String v) { this.申込人勤務先名称カナ = v; }
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
    public String get申込人勤続月数() { return 申込人勤続月数; }
    public void set申込人勤続月数(String v) { this.申込人勤続月数 = v; }
    public String get申込人雇用形態() { return 申込人雇用形態; }
    public void set申込人雇用形態(String v) { this.申込人雇用形態 = v; }
    public String get申込人業種() { return 申込人業種; }
    public void set申込人業種(String v) { this.申込人業種 = v; }
    public String get申込人勤務先事業内容() { return 申込人勤務先事業内容; }
    public void set申込人勤務先事業内容(String v) { this.申込人勤務先事業内容 = v; }
    public String get申込人勤務先所属部署() { return 申込人勤務先所属部署; }
    public void set申込人勤務先所属部署(String v) { this.申込人勤務先所属部署 = v; }
    public String get申込人職種() { return 申込人職種; }
    public void set申込人職種(String v) { this.申込人職種 = v; }
    public String get申込人勤務先役職() { return 申込人勤務先役職; }
    public void set申込人勤務先役職(String v) { this.申込人勤務先役職 = v; }
    public String get申込人勤務先資本金() { return 申込人勤務先資本金; }
    public void set申込人勤務先資本金(String v) { this.申込人勤務先資本金 = v; }
    public String get申込人収入種類() { return 申込人収入種類; }
    public void set申込人収入種類(String v) { this.申込人収入種類 = v; }
    public String get申込人前年年収() { return 申込人前年年収; }
    public void set申込人前年年収(String v) { this.申込人前年年収 = v; }
    public String get申込人前年年収内ボーナス() { return 申込人前年年収内ボーナス; }
    public void set申込人前年年収内ボーナス(String v) { this.申込人前年年収内ボーナス = v; }
    public String get申込人前々年年収() { return 申込人前々年年収; }
    public void set申込人前々年年収(String v) { this.申込人前々年年収 = v; }
    public String get連帯債務者有無() { return 連帯債務者有無; }
    public void set連帯債務者有無(String v) { this.連帯債務者有無 = v; }
    public String get連債者理由1() { return 連債者理由1; }
    public void set連債者理由1(String v) { this.連債者理由1 = v; }
    public String get連債者理由2() { return 連債者理由2; }
    public void set連債者理由2(String v) { this.連債者理由2 = v; }
    public String get連債者理由3() { return 連債者理由3; }
    public void set連債者理由3(String v) { this.連債者理由3 = v; }
    public String get連債者続柄() { return 連債者続柄; }
    public void set連債者続柄(String v) { this.連債者続柄 = v; }
    public String get連債者続柄その他() { return 連債者続柄その他; }
    public void set連債者続柄その他(String v) { this.連債者続柄その他 = v; }
    public String get連債者姓名カナ() { return 連債者姓名カナ; }
    public void set連債者姓名カナ(String v) { this.連債者姓名カナ = v; }
    public String get連債者姓名() { return 連債者姓名; }
    public void set連債者姓名(String v) { this.連債者姓名 = v; }
    public String get連債者性別() { return 連債者性別; }
    public void set連債者性別(String v) { this.連債者性別 = v; }
    public String get連債者国籍() { return 連債者国籍; }
    public void set連債者国籍(String v) { this.連債者国籍 = v; }
    public String get連債者生年月日() { return 連債者生年月日; }
    public void set連債者生年月日(String v) { this.連債者生年月日 = v; }
    public String get連債者郵便番号() { return 連債者郵便番号; }
    public void set連債者郵便番号(String v) { this.連債者郵便番号 = v; }
    public String get連債者住所() { return 連債者住所; }
    public void set連債者住所(String v) { this.連債者住所 = v; }
    public String get連債者自宅電話番号() { return 連債者自宅電話番号; }
    public void set連債者自宅電話番号(String v) { this.連債者自宅電話番号 = v; }
    public String get連債者携帯電話番号() { return 連債者携帯電話番号; }
    public void set連債者携帯電話番号(String v) { this.連債者携帯電話番号 = v; }
    public String get連債者勤務先名カナ() { return 連債者勤務先名カナ; }
    public void set連債者勤務先名カナ(String v) { this.連債者勤務先名カナ = v; }
    public String get連債者勤務先名() { return 連債者勤務先名; }
    public void set連債者勤務先名(String v) { this.連債者勤務先名 = v; }
    public String get連債者勤務先郵便番号() { return 連債者勤務先郵便番号; }
    public void set連債者勤務先郵便番号(String v) { this.連債者勤務先郵便番号 = v; }
    public String get連債者勤務先住所() { return 連債者勤務先住所; }
    public void set連債者勤務先住所(String v) { this.連債者勤務先住所 = v; }
    public String get連債者勤務先電話番号() { return 連債者勤務先電話番号; }
    public void set連債者勤務先電話番号(String v) { this.連債者勤務先電話番号 = v; }
    public String get連債者勤続年数() { return 連債者勤続年数; }
    public void set連債者勤続年数(String v) { this.連債者勤続年数 = v; }
    public String get連債者勤続月数() { return 連債者勤続月数; }
    public void set連債者勤続月数(String v) { this.連債者勤続月数 = v; }
    public String get連債者雇用形態() { return 連債者雇用形態; }
    public void set連債者雇用形態(String v) { this.連債者雇用形態 = v; }
    public String get連債者業種() { return 連債者業種; }
    public void set連債者業種(String v) { this.連債者業種 = v; }
    public String get連債者勤務先事業内容() { return 連債者勤務先事業内容; }
    public void set連債者勤務先事業内容(String v) { this.連債者勤務先事業内容 = v; }
    public String get連債者勤務先所属部署() { return 連債者勤務先所属部署; }
    public void set連債者勤務先所属部署(String v) { this.連債者勤務先所属部署 = v; }
    public String get連債者勤務先資本金() { return 連債者勤務先資本金; }
    public void set連債者勤務先資本金(String v) { this.連債者勤務先資本金 = v; }
    public String get収入合算者前年年収() { return 収入合算者前年年収; }
    public void set収入合算者前年年収(String v) { this.収入合算者前年年収 = v; }
    public String get連債者前年年収() { return 連債者前年年収; }
    public void set連債者前年年収(String v) { this.連債者前年年収 = v; }
    public String get収入合算者前年年収内ボーナス() { return 収入合算者前年年収内ボーナス; }
    public void set収入合算者前年年収内ボーナス(String v) { this.収入合算者前年年収内ボーナス = v; }
    public String get収入合算者前々年年収() { return 収入合算者前々年年収; }
    public void set収入合算者前々年年収(String v) { this.収入合算者前々年年収 = v; }
    public String get物件種類() { return 物件種類; }
    public void set物件種類(String v) { this.物件種類 = v; }
    public String get建設地郵便番号() { return 建設地郵便番号; }
    public void set建設地郵便番号(String v) { this.建設地郵便番号 = v; }
    public String get建設地住所() { return 建設地住所; }
    public void set建設地住所(String v) { this.建設地住所 = v; }
    public String get敷地面積() { return 敷地面積; }
    public void set敷地面積(String v) { this.敷地面積 = v; }
    public String get敷地権利区分1() { return 敷地権利区分1; }
    public void set敷地権利区分1(String v) { this.敷地権利区分1 = v; }
    public String get敷地権利区分2() { return 敷地権利区分2; }
    public void set敷地権利区分2(String v) { this.敷地権利区分2 = v; }
    public String get敷地権利区分3() { return 敷地権利区分3; }
    public void set敷地権利区分3(String v) { this.敷地権利区分3 = v; }
    public String get敷地権利区分4() { return 敷地権利区分4; }
    public void set敷地権利区分4(String v) { this.敷地権利区分4 = v; }
    public String get物件構造() { return 物件構造; }
    public void set物件構造(String v) { this.物件構造 = v; }
    public String get建物床面積＿住宅部分() { return 建物床面積＿住宅部分; }
    public void set建物床面積＿住宅部分(String v) { this.建物床面積＿住宅部分 = v; }
    public String get建物床面積＿非住宅部分() { return 建物床面積＿非住宅部分; }
    public void set建物床面積＿非住宅部分(String v) { this.建物床面積＿非住宅部分 = v; }
    public String get売買契約予定年月日() { return 売買契約予定年月日; }
    public void set売買契約予定年月日(String v) { this.売買契約予定年月日 = v; }
    public String get建物新築年月日() { return 建物新築年月日; }
    public void set建物新築年月日(String v) { this.建物新築年月日 = v; }
    public String get申込人物件通勤時間() { return 申込人物件通勤時間; }
    public void set申込人物件通勤時間(String v) { this.申込人物件通勤時間 = v; }
    public String get連債者物件通勤時間() { return 連債者物件通勤時間; }
    public void set連債者物件通勤時間(String v) { this.連債者物件通勤時間 = v; }
    public String get工事請負会社名称カナ() { return 工事請負会社名称カナ; }
    public void set工事請負会社名称カナ(String v) { this.工事請負会社名称カナ = v; }
    public String get工事請負会社名称() { return 工事請負会社名称; }
    public void set工事請負会社名称(String v) { this.工事請負会社名称 = v; }
    public String get工事請負会社担当者() { return 工事請負会社担当者; }
    public void set工事請負会社担当者(String v) { this.工事請負会社担当者 = v; }
    public String get工事請負会社郵便番号() { return 工事請負会社郵便番号; }
    public void set工事請負会社郵便番号(String v) { this.工事請負会社郵便番号 = v; }
    public String get工事請負会社住所() { return 工事請負会社住所; }
    public void set工事請負会社住所(String v) { this.工事請負会社住所 = v; }
    public String get工事請負業者電話番号() { return 工事請負業者電話番号; }
    public void set工事請負業者電話番号(String v) { this.工事請負業者電話番号 = v; }
    public String get販売代理業者名カナ() { return 販売代理業者名カナ; }
    public void set販売代理業者名カナ(String v) { this.販売代理業者名カナ = v; }
    public String get販売代理業者名称() { return 販売代理業者名称; }
    public void set販売代理業者名称(String v) { this.販売代理業者名称 = v; }
    public String get販売代理会社担当者() { return 販売代理会社担当者; }
    public void set販売代理会社担当者(String v) { this.販売代理会社担当者 = v; }
    public String get販売代理会社郵便番号() { return 販売代理会社郵便番号; }
    public void set販売代理会社郵便番号(String v) { this.販売代理会社郵便番号 = v; }
    public String get販売代理会社住所() { return 販売代理会社住所; }
    public void set販売代理会社住所(String v) { this.販売代理会社住所 = v; }
    public String get販売代理業者電話番号() { return 販売代理業者電話番号; }
    public void set販売代理業者電話番号(String v) { this.販売代理業者電話番号 = v; }
    public String getリフォーム会社名称カナ() { return リフォーム会社名称カナ; }
    public void setリフォーム会社名称カナ(String v) { this.リフォーム会社名称カナ = v; }
    public String getリフォーム会社名称() { return リフォーム会社名称; }
    public void setリフォーム会社名称(String v) { this.リフォーム会社名称 = v; }
    public String getリフォーム会社担当者() { return リフォーム会社担当者; }
    public void setリフォーム会社担当者(String v) { this.リフォーム会社担当者 = v; }
    public String getリフォーム会社郵便番号() { return リフォーム会社郵便番号; }
    public void setリフォーム会社郵便番号(String v) { this.リフォーム会社郵便番号 = v; }
    public String getリフォーム会社住所() { return リフォーム会社住所; }
    public void setリフォーム会社住所(String v) { this.リフォーム会社住所 = v; }
    public String getリフォーム業者電話番号() { return リフォーム業者電話番号; }
    public void setリフォーム業者電話番号(String v) { this.リフォーム業者電話番号 = v; }
    public String get現在住宅の建て方() { return 現在住宅の建て方; }
    public void set現在住宅の建て方(String v) { this.現在住宅の建て方 = v; }
    public String get現在住宅種類() { return 現在住宅種類; }
    public void set現在住宅種類(String v) { this.現在住宅種類 = v; }
    public String get現在住宅面積() { return 現在住宅面積; }
    public void set現在住宅面積(String v) { this.現在住宅面積 = v; }
    public String get住宅必要理由() { return 住宅必要理由; }
    public void set住宅必要理由(String v) { this.住宅必要理由 = v; }
    public String get物件共有予定土地() { return 物件共有予定土地; }
    public void set物件共有予定土地(String v) { this.物件共有予定土地 = v; }
    public String get物件共有予定建物() { return 物件共有予定建物; }
    public void set物件共有予定建物(String v) { this.物件共有予定建物 = v; }
    public String get担保提供者人数() { return 担保提供者人数; }
    public void set担保提供者人数(String v) { this.担保提供者人数 = v; }
    public String get担保提供者1建物土地() { return 担保提供者1建物土地; }
    public void set担保提供者1建物土地(String v) { this.担保提供者1建物土地 = v; }
    public String get担保提供者1続柄() { return 担保提供者1続柄; }
    public void set担保提供者1続柄(String v) { this.担保提供者1続柄 = v; }
    public String get担保提供者1続柄その他() { return 担保提供者1続柄その他; }
    public void set担保提供者1続柄その他(String v) { this.担保提供者1続柄その他 = v; }
    public String get担保提供者1姓名カナ() { return 担保提供者1姓名カナ; }
    public void set担保提供者1姓名カナ(String v) { this.担保提供者1姓名カナ = v; }
    public String get担保提供者1姓名() { return 担保提供者1姓名; }
    public void set担保提供者1姓名(String v) { this.担保提供者1姓名 = v; }
    public String get担保提供者1生年月日() { return 担保提供者1生年月日; }
    public void set担保提供者1生年月日(String v) { this.担保提供者1生年月日 = v; }
    public String get担保提供者1郵便番号() { return 担保提供者1郵便番号; }
    public void set担保提供者1郵便番号(String v) { this.担保提供者1郵便番号 = v; }
    public String get担保提供者1住所() { return 担保提供者1住所; }
    public void set担保提供者1住所(String v) { this.担保提供者1住所 = v; }
    public String get担保提供者2建物土地() { return 担保提供者2建物土地; }
    public void set担保提供者2建物土地(String v) { this.担保提供者2建物土地 = v; }
    public String get担保提供者2続柄() { return 担保提供者2続柄; }
    public void set担保提供者2続柄(String v) { this.担保提供者2続柄 = v; }
    public String get担保提供者2続柄その他() { return 担保提供者2続柄その他; }
    public void set担保提供者2続柄その他(String v) { this.担保提供者2続柄その他 = v; }
    public String get担保提供者2姓名カナ() { return 担保提供者2姓名カナ; }
    public void set担保提供者2姓名カナ(String v) { this.担保提供者2姓名カナ = v; }
    public String get担保提供者2姓名() { return 担保提供者2姓名; }
    public void set担保提供者2姓名(String v) { this.担保提供者2姓名 = v; }
    public String get担保提供者2生年月日() { return 担保提供者2生年月日; }
    public void set担保提供者2生年月日(String v) { this.担保提供者2生年月日 = v; }
    public String get担保提供者2郵便番号() { return 担保提供者2郵便番号; }
    public void set担保提供者2郵便番号(String v) { this.担保提供者2郵便番号 = v; }
    public String get担保提供者2住所() { return 担保提供者2住所; }
    public void set担保提供者2住所(String v) { this.担保提供者2住所 = v; }
    public String get担保提供者3建物土地() { return 担保提供者3建物土地; }
    public void set担保提供者3建物土地(String v) { this.担保提供者3建物土地 = v; }
    public String get担保提供者3続柄() { return 担保提供者3続柄; }
    public void set担保提供者3続柄(String v) { this.担保提供者3続柄 = v; }
    public String get担保提供者3続柄その他() { return 担保提供者3続柄その他; }
    public void set担保提供者3続柄その他(String v) { this.担保提供者3続柄その他 = v; }
    public String get担保提供者3姓名カナ() { return 担保提供者3姓名カナ; }
    public void set担保提供者3姓名カナ(String v) { this.担保提供者3姓名カナ = v; }
    public String get担保提供者3姓名() { return 担保提供者3姓名; }
    public void set担保提供者3姓名(String v) { this.担保提供者3姓名 = v; }
    public String get担保提供者3生年月日() { return 担保提供者3生年月日; }
    public void set担保提供者3生年月日(String v) { this.担保提供者3生年月日 = v; }
    public String get担保提供者3郵便番号() { return 担保提供者3郵便番号; }
    public void set担保提供者3郵便番号(String v) { this.担保提供者3郵便番号 = v; }
    public String get担保提供者3住所() { return 担保提供者3住所; }
    public void set担保提供者3住所(String v) { this.担保提供者3住所 = v; }
    public String get担保提供者4建物土地() { return 担保提供者4建物土地; }
    public void set担保提供者4建物土地(String v) { this.担保提供者4建物土地 = v; }
    public String get担保提供者4続柄() { return 担保提供者4続柄; }
    public void set担保提供者4続柄(String v) { this.担保提供者4続柄 = v; }
    public String get担保提供者4続柄その他() { return 担保提供者4続柄その他; }
    public void set担保提供者4続柄その他(String v) { this.担保提供者4続柄その他 = v; }
    public String get担保提供者4姓名カナ() { return 担保提供者4姓名カナ; }
    public void set担保提供者4姓名カナ(String v) { this.担保提供者4姓名カナ = v; }
    public String get担保提供者4姓名() { return 担保提供者4姓名; }
    public void set担保提供者4姓名(String v) { this.担保提供者4姓名 = v; }
    public String get担保提供者4生年月日() { return 担保提供者4生年月日; }
    public void set担保提供者4生年月日(String v) { this.担保提供者4生年月日 = v; }
    public String get担保提供者4郵便番号() { return 担保提供者4郵便番号; }
    public void set担保提供者4郵便番号(String v) { this.担保提供者4郵便番号 = v; }
    public String get担保提供者4住所() { return 担保提供者4住所; }
    public void set担保提供者4住所(String v) { this.担保提供者4住所 = v; }
    public String get物件入居予定者数() { return 物件入居予定者数; }
    public void set物件入居予定者数(String v) { this.物件入居予定者数 = v; }
    public String get物件入居家族構成() { return 物件入居家族構成; }
    public void set物件入居家族構成(String v) { this.物件入居家族構成 = v; }
    public String get居住区分() { return 居住区分; }
    public void set居住区分(String v) { this.居住区分 = v; }
    public String get建築購入費() { return 建築購入費; }
    public void set建築購入費(String v) { this.建築購入費 = v; }
    public String get土地取得費() { return 土地取得費; }
    public void set土地取得費(String v) { this.土地取得費 = v; }
    public String get融資額() { return 融資額; }
    public void set融資額(String v) { this.融資額 = v; }
    public String get予備1() { return 予備1; }
    public void set予備1(String v) { this.予備1 = v; }
    public String get返済方法() { return 返済方法; }
    public void set返済方法(String v) { this.返済方法 = v; }
    public String get希望返済期間() { return 希望返済期間; }
    public void set希望返済期間(String v) { this.希望返済期間 = v; }
    public String getボーナス払希望有無() { return ボーナス払希望有無; }
    public void setボーナス払希望有無(String v) { this.ボーナス払希望有無 = v; }
    public String getボーナス払月() { return ボーナス払月; }
    public void setボーナス払月(String v) { this.ボーナス払月 = v; }
    public String get希望額ボーナス分() { return 希望額ボーナス分; }
    public void set希望額ボーナス分(String v) { this.希望額ボーナス分 = v; }
    public String get融資実行希望年月日() { return 融資実行希望年月日; }
    public void set融資実行希望年月日(String v) { this.融資実行希望年月日 = v; }
    public String get借入公的() { return 借入公的; }
    public void set借入公的(String v) { this.借入公的 = v; }
    public String get借入公的＿借入先() { return 借入公的＿借入先; }
    public void set借入公的＿借入先(String v) { this.借入公的＿借入先 = v; }
    public String get借入公的＿返済期間() { return 借入公的＿返済期間; }
    public void set借入公的＿返済期間(String v) { this.借入公的＿返済期間 = v; }
    public String get借入公的＿金利() { return 借入公的＿金利; }
    public void set借入公的＿金利(String v) { this.借入公的＿金利 = v; }
    public String get借入公的＿毎月返済額() { return 借入公的＿毎月返済額; }
    public void set借入公的＿毎月返済額(String v) { this.借入公的＿毎月返済額 = v; }
    public String get借入民間() { return 借入民間; }
    public void set借入民間(String v) { this.借入民間 = v; }
    public String get借入民間＿借入先() { return 借入民間＿借入先; }
    public void set借入民間＿借入先(String v) { this.借入民間＿借入先 = v; }
    public String get借入民間＿返済期間() { return 借入民間＿返済期間; }
    public void set借入民間＿返済期間(String v) { this.借入民間＿返済期間 = v; }
    public String get借入民間＿金利() { return 借入民間＿金利; }
    public void set借入民間＿金利(String v) { this.借入民間＿金利 = v; }
    public String get借入民間＿毎月返済額() { return 借入民間＿毎月返済額; }
    public void set借入民間＿毎月返済額(String v) { this.借入民間＿毎月返済額 = v; }
    public String get借入勤務先() { return 借入勤務先; }
    public void set借入勤務先(String v) { this.借入勤務先 = v; }
    public String get借入勤務先＿返済期間() { return 借入勤務先＿返済期間; }
    public void set借入勤務先＿返済期間(String v) { this.借入勤務先＿返済期間 = v; }
    public String get借入勤務先＿毎月返済額() { return 借入勤務先＿毎月返済額; }
    public void set借入勤務先＿毎月返済額(String v) { this.借入勤務先＿毎月返済額 = v; }
    public String get借入親等() { return 借入親等; }
    public void set借入親等(String v) { this.借入親等 = v; }
    public String get借入親等＿借入先() { return 借入親等＿借入先; }
    public void set借入親等＿借入先(String v) { this.借入親等＿借入先 = v; }
    public String get借入親等＿返済期間() { return 借入親等＿返済期間; }
    public void set借入親等＿返済期間(String v) { this.借入親等＿返済期間 = v; }
    public String get借入親等＿毎月返済額() { return 借入親等＿毎月返済額; }
    public void set借入親等＿毎月返済額(String v) { this.借入親等＿毎月返済額 = v; }
    public String get借入返済土地() { return 借入返済土地; }
    public void set借入返済土地(String v) { this.借入返済土地 = v; }
    public String get借入返済土地＿返済期間() { return 借入返済土地＿返済期間; }
    public void set借入返済土地＿返済期間(String v) { this.借入返済土地＿返済期間 = v; }
    public String get借入返済土地＿毎月返済額() { return 借入返済土地＿毎月返済額; }
    public void set借入返済土地＿毎月返済額(String v) { this.借入返済土地＿毎月返済額 = v; }
    public String get手持金() { return 手持金; }
    public void set手持金(String v) { this.手持金 = v; }
    public String get住宅取得以外＿借入件数() { return 住宅取得以外＿借入件数; }
    public void set住宅取得以外＿借入件数(String v) { this.住宅取得以外＿借入件数 = v; }
    public String get住宅取得以外＿借入金額() { return 住宅取得以外＿借入金額; }
    public void set住宅取得以外＿借入金額(String v) { this.住宅取得以外＿借入金額 = v; }
    public String get住宅取得以外＿毎月返済額() { return 住宅取得以外＿毎月返済額; }
    public void set住宅取得以外＿毎月返済額(String v) { this.住宅取得以外＿毎月返済額 = v; }
    public String get事前審査申請日() { return 事前審査申請日; }
    public void set事前審査申請日(String v) { this.事前審査申請日 = v; }
    public String get本審査申請日() { return 本審査申請日; }
    public void set本審査申請日(String v) { this.本審査申請日 = v; }
    public String get申込受理日() { return 申込受理日; }
    public void set申込受理日(String v) { this.申込受理日 = v; }
    public String get定期借地権() { return 定期借地権; }
    public void set定期借地権(String v) { this.定期借地権 = v; }
    public String get前払賃料() { return 前払賃料; }
    public void set前払賃料(String v) { this.前払賃料 = v; }
    public String get買戻権() { return 買戻権; }
    public void set買戻権(String v) { this.買戻権 = v; }
    public String get保留地物件サイン() { return 保留地物件サイン; }
    public void set保留地物件サイン(String v) { this.保留地物件サイン = v; }
    public String get金消契約年月日() { return 金消契約年月日; }
    public void set金消契約年月日(String v) { this.金消契約年月日 = v; }
    public String get審査金利() { return 審査金利; }
    public void set審査金利(String v) { this.審査金利 = v; }
    public String get返済負担率() { return 返済負担率; }
    public void set返済負担率(String v) { this.返済負担率 = v; }
    public String get担保評価額() { return 担保評価額; }
    public void set担保評価額(String v) { this.担保評価額 = v; }
    public String getアルヒ審査担当者() { return アルヒ審査担当者; }
    public void setアルヒ審査担当者(String v) { this.アルヒ審査担当者 = v; }
    public String getＬＴＶ() { return ＬＴＶ; }
    public void setＬＴＶ(String v) { this.ＬＴＶ = v; }
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
