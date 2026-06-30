package migration.domain.target;

import java.math.BigDecimal;

public class 申込Target {

    private String 申込番号;
    private String 申込目的;
    private String 商品大分類;
    private String 商品コード;
    private String 保証番号;
    private String 関連案件有無;
    private String 申込日;
    private String ＣＩＦ番号;
    private String 自宅郵便番号;
    private String 自宅住所カナ;
    private String 自宅住所漢字;
    private String 生年月日;
    private String 性別;
    private String 勤務先郵便番号;
    private String 携帯電話番号;
    private String 建物完成予定日;
    private String 検索用カナ氏名;
    private String 勤務先名漢字;
    private BigDecimal 勤務先入社年月;
    private BigDecimal 勤務先勤続年数;
    private String 勤務先勤業;
    private String 勤務先勤種;
    private String 勤務先資本金区分;
    private BigDecimal 勤務先逐業員数;
    private String 住居形態;
    private String 金融機関1名称;
    private String 金融機関1借入種類;
    private BigDecimal 金融機関1残高;
    private BigDecimal 金融機関1借入年間返済額;
    private BigDecimal 金融機関1借入期間;
    private String 金融機関1借入時完済解約予定;
    private BigDecimal 金融機関1利用限度額;
    private String 金融機関2名称;
    private String 金融機関2借入種類;
    private BigDecimal 金融機関2残高;
    private BigDecimal 金融機関2借入年間返済額;
    private BigDecimal 金融機関2借入期間;
    private String 金融機関2借入時完済解約予定;
    private BigDecimal 金融機関2利用限度額;
    private String 金融機関3名称;
    private String 金融機関3借入種類;
    private BigDecimal 金融機関3残高;
    private BigDecimal 金融機関3借入年間返済額;
    private BigDecimal 金融機関3借入期間;
    private String 金融機関3借入時完済解約予定;
    private BigDecimal 金融機関3利用限度額;
    private String 資金使途;
    private BigDecimal 借入金額;
    private BigDecimal 借入金額＿毎月;
    private BigDecimal 借入金額＿半年毎;
    private BigDecimal 返済額＿毎月;
    private BigDecimal 返済額＿半年毎;
    private BigDecimal 借入期間;
    private String 借入希望日;
    private String 借入希望日＿建物;
    private String 返済方法区分;
    private String 金利区分;
    private String 保証料区分;
    private String ボーナス返済月1;
    private String ボーナス返済月2;
    private String 同居予定家族＿配偶者;
    private String 同居予定家族＿父;
    private String 同居予定家族＿母;
    private String 同居予定家族＿その他;
    private BigDecimal 同居予定家族＿その他＿人数;
    private BigDecimal 同居予定家族＿子供人数;
    private BigDecimal 同居予定家族＿子供年齢＿1人目;
    private BigDecimal 同居予定家族＿子供年齢＿2人目;
    private BigDecimal 同居予定家族＿子供年齢＿3人目;
    private BigDecimal 同居予定家族＿子供年齢＿4人目;
    private String 同居予定家族＿本人;
    private BigDecimal 同居予定家族＿合計人数;
    private String 婚姻区分;
    private String 商品分類;
    private String 外部連携受付番号;
    private BigDecimal 勤務先資本金＿外部ローン;
    private String 土地契約予定日;
    private String 預金＿金融機関1＿名称;
    private BigDecimal 預金＿金融機関1＿本人預金;
    private BigDecimal 預金＿金融機関1＿家族預金;
    private String 預金＿金融機関2＿名称;
    private BigDecimal 預金＿金融機関2＿本人預金;
    private BigDecimal 預金＿金融機関2＿家族預金;
    private BigDecimal 預金＿金融機関3＿本人預金;
    private BigDecimal 預金＿金融機関3＿家族預金;
    private BigDecimal 預金＿金融機関4＿本人預金;
    private BigDecimal 預金＿金融機関4＿家族預金;
    private String 歩合給;
    private String 国家資格;
    private String 国家資格＿その他;
    private BigDecimal 配偶者年収;
    private String 資金使途＿マンション;
    private String 資金使途＿マンション以外;
    private String 資金使途＿ワイドローン一般口;
    private String 資金使途＿物件種別;
    private BigDecimal 必要資金＿土地;
    private BigDecimal 必要資金＿建物;
    private BigDecimal 必要資金＿借替;
    private BigDecimal 必要資金＿諸費用;
    private BigDecimal 必要資金＿その他;
    private BigDecimal 必要資金＿合計;
    private String 調達＿金融機関1＿名称;
    private BigDecimal 調達＿金融機関1＿金額;
    private BigDecimal 調達＿金融機関1＿期間;
    private String 調達＿金融機関2＿名称;
    private BigDecimal 調達＿金融機関2＿金額;
    private BigDecimal 調達＿金融機関2＿期間;
    private BigDecimal 調達＿合計;
    private BigDecimal 自己資金＿預貯金;
    private BigDecimal 自己資金＿その他;
    private BigDecimal 自己資金＿贈与;
    private String 給与振込;
    private BigDecimal 税込年収＿前々年;
    private BigDecimal 税込年収＿３年前;

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String v) { this.申込番号 = v; }
    public String get申込目的() { return 申込目的; }
    public void set申込目的(String v) { this.申込目的 = v; }
    public String get商品大分類() { return 商品大分類; }
    public void set商品大分類(String v) { this.商品大分類 = v; }
    public String get商品コード() { return 商品コード; }
    public void set商品コード(String v) { this.商品コード = v; }
    public String get保証番号() { return 保証番号; }
    public void set保証番号(String v) { this.保証番号 = v; }
    public String get関連案件有無() { return 関連案件有無; }
    public void set関連案件有無(String v) { this.関連案件有無 = v; }
    public String get申込日() { return 申込日; }
    public void set申込日(String v) { this.申込日 = v; }
    public String getＣＩＦ番号() { return ＣＩＦ番号; }
    public void setＣＩＦ番号(String v) { this.ＣＩＦ番号 = v; }
    public String get自宅郵便番号() { return 自宅郵便番号; }
    public void set自宅郵便番号(String v) { this.自宅郵便番号 = v; }
    public String get自宅住所カナ() { return 自宅住所カナ; }
    public void set自宅住所カナ(String v) { this.自宅住所カナ = v; }
    public String get自宅住所漢字() { return 自宅住所漢字; }
    public void set自宅住所漢字(String v) { this.自宅住所漢字 = v; }
    public String get生年月日() { return 生年月日; }
    public void set生年月日(String v) { this.生年月日 = v; }
    public String get性別() { return 性別; }
    public void set性別(String v) { this.性別 = v; }
    public String get勤務先郵便番号() { return 勤務先郵便番号; }
    public void set勤務先郵便番号(String v) { this.勤務先郵便番号 = v; }
    public String get携帯電話番号() { return 携帯電話番号; }
    public void set携帯電話番号(String v) { this.携帯電話番号 = v; }
    public String get建物完成予定日() { return 建物完成予定日; }
    public void set建物完成予定日(String v) { this.建物完成予定日 = v; }
    public String get検索用カナ氏名() { return 検索用カナ氏名; }
    public void set検索用カナ氏名(String v) { this.検索用カナ氏名 = v; }
    public String get勤務先名漢字() { return 勤務先名漢字; }
    public void set勤務先名漢字(String v) { this.勤務先名漢字 = v; }
    public BigDecimal get勤務先入社年月() { return 勤務先入社年月; }
    public void set勤務先入社年月(BigDecimal v) { this.勤務先入社年月 = v; }
    public BigDecimal get勤務先勤続年数() { return 勤務先勤続年数; }
    public void set勤務先勤続年数(BigDecimal v) { this.勤務先勤続年数 = v; }
    public String get勤務先勤業() { return 勤務先勤業; }
    public void set勤務先勤業(String v) { this.勤務先勤業 = v; }
    public String get勤務先勤種() { return 勤務先勤種; }
    public void set勤務先勤種(String v) { this.勤務先勤種 = v; }
    public String get勤務先資本金区分() { return 勤務先資本金区分; }
    public void set勤務先資本金区分(String v) { this.勤務先資本金区分 = v; }
    public BigDecimal get勤務先逐業員数() { return 勤務先逐業員数; }
    public void set勤務先逐業員数(BigDecimal v) { this.勤務先逐業員数 = v; }
    public String get住居形態() { return 住居形態; }
    public void set住居形態(String v) { this.住居形態 = v; }
    public String get金融機関1名称() { return 金融機関1名称; }
    public void set金融機関1名称(String v) { this.金融機関1名称 = v; }
    public String get金融機関1借入種類() { return 金融機関1借入種類; }
    public void set金融機関1借入種類(String v) { this.金融機関1借入種類 = v; }
    public BigDecimal get金融機関1残高() { return 金融機関1残高; }
    public void set金融機関1残高(BigDecimal v) { this.金融機関1残高 = v; }
    public BigDecimal get金融機関1借入年間返済額() { return 金融機関1借入年間返済額; }
    public void set金融機関1借入年間返済額(BigDecimal v) { this.金融機関1借入年間返済額 = v; }
    public BigDecimal get金融機関1借入期間() { return 金融機関1借入期間; }
    public void set金融機関1借入期間(BigDecimal v) { this.金融機関1借入期間 = v; }
    public String get金融機関1借入時完済解約予定() { return 金融機関1借入時完済解約予定; }
    public void set金融機関1借入時完済解約予定(String v) { this.金融機関1借入時完済解約予定 = v; }
    public BigDecimal get金融機関1利用限度額() { return 金融機関1利用限度額; }
    public void set金融機関1利用限度額(BigDecimal v) { this.金融機関1利用限度額 = v; }
    public String get金融機関2名称() { return 金融機関2名称; }
    public void set金融機関2名称(String v) { this.金融機関2名称 = v; }
    public String get金融機関2借入種類() { return 金融機関2借入種類; }
    public void set金融機関2借入種類(String v) { this.金融機関2借入種類 = v; }
    public BigDecimal get金融機関2残高() { return 金融機関2残高; }
    public void set金融機関2残高(BigDecimal v) { this.金融機関2残高 = v; }
    public BigDecimal get金融機関2借入年間返済額() { return 金融機関2借入年間返済額; }
    public void set金融機関2借入年間返済額(BigDecimal v) { this.金融機関2借入年間返済額 = v; }
    public BigDecimal get金融機関2借入期間() { return 金融機関2借入期間; }
    public void set金融機関2借入期間(BigDecimal v) { this.金融機関2借入期間 = v; }
    public String get金融機関2借入時完済解約予定() { return 金融機関2借入時完済解約予定; }
    public void set金融機関2借入時完済解約予定(String v) { this.金融機関2借入時完済解約予定 = v; }
    public BigDecimal get金融機関2利用限度額() { return 金融機関2利用限度額; }
    public void set金融機関2利用限度額(BigDecimal v) { this.金融機関2利用限度額 = v; }
    public String get金融機関3名称() { return 金融機関3名称; }
    public void set金融機関3名称(String v) { this.金融機関3名称 = v; }
    public String get金融機関3借入種類() { return 金融機関3借入種類; }
    public void set金融機関3借入種類(String v) { this.金融機関3借入種類 = v; }
    public BigDecimal get金融機関3残高() { return 金融機関3残高; }
    public void set金融機関3残高(BigDecimal v) { this.金融機関3残高 = v; }
    public BigDecimal get金融機関3借入年間返済額() { return 金融機関3借入年間返済額; }
    public void set金融機関3借入年間返済額(BigDecimal v) { this.金融機関3借入年間返済額 = v; }
    public BigDecimal get金融機関3借入期間() { return 金融機関3借入期間; }
    public void set金融機関3借入期間(BigDecimal v) { this.金融機関3借入期間 = v; }
    public String get金融機関3借入時完済解約予定() { return 金融機関3借入時完済解約予定; }
    public void set金融機関3借入時完済解約予定(String v) { this.金融機関3借入時完済解約予定 = v; }
    public BigDecimal get金融機関3利用限度額() { return 金融機関3利用限度額; }
    public void set金融機関3利用限度額(BigDecimal v) { this.金融機関3利用限度額 = v; }
    public String get資金使途() { return 資金使途; }
    public void set資金使途(String v) { this.資金使途 = v; }
    public BigDecimal get借入金額() { return 借入金額; }
    public void set借入金額(BigDecimal v) { this.借入金額 = v; }
    public BigDecimal get借入金額＿毎月() { return 借入金額＿毎月; }
    public void set借入金額＿毎月(BigDecimal v) { this.借入金額＿毎月 = v; }
    public BigDecimal get借入金額＿半年毎() { return 借入金額＿半年毎; }
    public void set借入金額＿半年毎(BigDecimal v) { this.借入金額＿半年毎 = v; }
    public BigDecimal get返済額＿毎月() { return 返済額＿毎月; }
    public void set返済額＿毎月(BigDecimal v) { this.返済額＿毎月 = v; }
    public BigDecimal get返済額＿半年毎() { return 返済額＿半年毎; }
    public void set返済額＿半年毎(BigDecimal v) { this.返済額＿半年毎 = v; }
    public BigDecimal get借入期間() { return 借入期間; }
    public void set借入期間(BigDecimal v) { this.借入期間 = v; }
    public String get借入希望日() { return 借入希望日; }
    public void set借入希望日(String v) { this.借入希望日 = v; }
    public String get借入希望日＿建物() { return 借入希望日＿建物; }
    public void set借入希望日＿建物(String v) { this.借入希望日＿建物 = v; }
    public String get返済方法区分() { return 返済方法区分; }
    public void set返済方法区分(String v) { this.返済方法区分 = v; }
    public String get金利区分() { return 金利区分; }
    public void set金利区分(String v) { this.金利区分 = v; }
    public String get保証料区分() { return 保証料区分; }
    public void set保証料区分(String v) { this.保証料区分 = v; }
    public String getボーナス返済月1() { return ボーナス返済月1; }
    public void setボーナス返済月1(String v) { this.ボーナス返済月1 = v; }
    public String getボーナス返済月2() { return ボーナス返済月2; }
    public void setボーナス返済月2(String v) { this.ボーナス返済月2 = v; }
    public String get同居予定家族＿配偶者() { return 同居予定家族＿配偶者; }
    public void set同居予定家族＿配偶者(String v) { this.同居予定家族＿配偶者 = v; }
    public String get同居予定家族＿父() { return 同居予定家族＿父; }
    public void set同居予定家族＿父(String v) { this.同居予定家族＿父 = v; }
    public String get同居予定家族＿母() { return 同居予定家族＿母; }
    public void set同居予定家族＿母(String v) { this.同居予定家族＿母 = v; }
    public String get同居予定家族＿その他() { return 同居予定家族＿その他; }
    public void set同居予定家族＿その他(String v) { this.同居予定家族＿その他 = v; }
    public BigDecimal get同居予定家族＿その他＿人数() { return 同居予定家族＿その他＿人数; }
    public void set同居予定家族＿その他＿人数(BigDecimal v) { this.同居予定家族＿その他＿人数 = v; }
    public BigDecimal get同居予定家族＿子供人数() { return 同居予定家族＿子供人数; }
    public void set同居予定家族＿子供人数(BigDecimal v) { this.同居予定家族＿子供人数 = v; }
    public BigDecimal get同居予定家族＿子供年齢＿1人目() { return 同居予定家族＿子供年齢＿1人目; }
    public void set同居予定家族＿子供年齢＿1人目(BigDecimal v) { this.同居予定家族＿子供年齢＿1人目 = v; }
    public BigDecimal get同居予定家族＿子供年齢＿2人目() { return 同居予定家族＿子供年齢＿2人目; }
    public void set同居予定家族＿子供年齢＿2人目(BigDecimal v) { this.同居予定家族＿子供年齢＿2人目 = v; }
    public BigDecimal get同居予定家族＿子供年齢＿3人目() { return 同居予定家族＿子供年齢＿3人目; }
    public void set同居予定家族＿子供年齢＿3人目(BigDecimal v) { this.同居予定家族＿子供年齢＿3人目 = v; }
    public BigDecimal get同居予定家族＿子供年齢＿4人目() { return 同居予定家族＿子供年齢＿4人目; }
    public void set同居予定家族＿子供年齢＿4人目(BigDecimal v) { this.同居予定家族＿子供年齢＿4人目 = v; }
    public String get同居予定家族＿本人() { return 同居予定家族＿本人; }
    public void set同居予定家族＿本人(String v) { this.同居予定家族＿本人 = v; }
    public BigDecimal get同居予定家族＿合計人数() { return 同居予定家族＿合計人数; }
    public void set同居予定家族＿合計人数(BigDecimal v) { this.同居予定家族＿合計人数 = v; }
    public String get婚姻区分() { return 婚姻区分; }
    public void set婚姻区分(String v) { this.婚姻区分 = v; }
    public String get商品分類() { return 商品分類; }
    public void set商品分類(String v) { this.商品分類 = v; }
    public String get外部連携受付番号() { return 外部連携受付番号; }
    public void set外部連携受付番号(String v) { this.外部連携受付番号 = v; }
    public BigDecimal get勤務先資本金＿外部ローン() { return 勤務先資本金＿外部ローン; }
    public void set勤務先資本金＿外部ローン(BigDecimal v) { this.勤務先資本金＿外部ローン = v; }
    public String get土地契約予定日() { return 土地契約予定日; }
    public void set土地契約予定日(String v) { this.土地契約予定日 = v; }
    public String get預金＿金融機関1＿名称() { return 預金＿金融機関1＿名称; }
    public void set預金＿金融機関1＿名称(String v) { this.預金＿金融機関1＿名称 = v; }
    public BigDecimal get預金＿金融機関1＿本人預金() { return 預金＿金融機関1＿本人預金; }
    public void set預金＿金融機関1＿本人預金(BigDecimal v) { this.預金＿金融機関1＿本人預金 = v; }
    public BigDecimal get預金＿金融機関1＿家族預金() { return 預金＿金融機関1＿家族預金; }
    public void set預金＿金融機関1＿家族預金(BigDecimal v) { this.預金＿金融機関1＿家族預金 = v; }
    public String get預金＿金融機関2＿名称() { return 預金＿金融機関2＿名称; }
    public void set預金＿金融機関2＿名称(String v) { this.預金＿金融機関2＿名称 = v; }
    public BigDecimal get預金＿金融機関2＿本人預金() { return 預金＿金融機関2＿本人預金; }
    public void set預金＿金融機関2＿本人預金(BigDecimal v) { this.預金＿金融機関2＿本人預金 = v; }
    public BigDecimal get預金＿金融機関2＿家族預金() { return 預金＿金融機関2＿家族預金; }
    public void set預金＿金融機関2＿家族預金(BigDecimal v) { this.預金＿金融機関2＿家族預金 = v; }
    public BigDecimal get預金＿金融機関3＿本人預金() { return 預金＿金融機関3＿本人預金; }
    public void set預金＿金融機関3＿本人預金(BigDecimal v) { this.預金＿金融機関3＿本人預金 = v; }
    public BigDecimal get預金＿金融機関3＿家族預金() { return 預金＿金融機関3＿家族預金; }
    public void set預金＿金融機関3＿家族預金(BigDecimal v) { this.預金＿金融機関3＿家族預金 = v; }
    public BigDecimal get預金＿金融機関4＿本人預金() { return 預金＿金融機関4＿本人預金; }
    public void set預金＿金融機関4＿本人預金(BigDecimal v) { this.預金＿金融機関4＿本人預金 = v; }
    public BigDecimal get預金＿金融機関4＿家族預金() { return 預金＿金融機関4＿家族預金; }
    public void set預金＿金融機関4＿家族預金(BigDecimal v) { this.預金＿金融機関4＿家族預金 = v; }
    public String get歩合給() { return 歩合給; }
    public void set歩合給(String v) { this.歩合給 = v; }
    public String get国家資格() { return 国家資格; }
    public void set国家資格(String v) { this.国家資格 = v; }
    public String get国家資格＿その他() { return 国家資格＿その他; }
    public void set国家資格＿その他(String v) { this.国家資格＿その他 = v; }
    public BigDecimal get配偶者年収() { return 配偶者年収; }
    public void set配偶者年収(BigDecimal v) { this.配偶者年収 = v; }
    public String get資金使途＿マンション() { return 資金使途＿マンション; }
    public void set資金使途＿マンション(String v) { this.資金使途＿マンション = v; }
    public String get資金使途＿マンション以外() { return 資金使途＿マンション以外; }
    public void set資金使途＿マンション以外(String v) { this.資金使途＿マンション以外 = v; }
    public String get資金使途＿ワイドローン一般口() { return 資金使途＿ワイドローン一般口; }
    public void set資金使途＿ワイドローン一般口(String v) { this.資金使途＿ワイドローン一般口 = v; }
    public String get資金使途＿物件種別() { return 資金使途＿物件種別; }
    public void set資金使途＿物件種別(String v) { this.資金使途＿物件種別 = v; }
    public BigDecimal get必要資金＿土地() { return 必要資金＿土地; }
    public void set必要資金＿土地(BigDecimal v) { this.必要資金＿土地 = v; }
    public BigDecimal get必要資金＿建物() { return 必要資金＿建物; }
    public void set必要資金＿建物(BigDecimal v) { this.必要資金＿建物 = v; }
    public BigDecimal get必要資金＿借替() { return 必要資金＿借替; }
    public void set必要資金＿借替(BigDecimal v) { this.必要資金＿借替 = v; }
    public BigDecimal get必要資金＿諸費用() { return 必要資金＿諸費用; }
    public void set必要資金＿諸費用(BigDecimal v) { this.必要資金＿諸費用 = v; }
    public BigDecimal get必要資金＿その他() { return 必要資金＿その他; }
    public void set必要資金＿その他(BigDecimal v) { this.必要資金＿その他 = v; }
    public BigDecimal get必要資金＿合計() { return 必要資金＿合計; }
    public void set必要資金＿合計(BigDecimal v) { this.必要資金＿合計 = v; }
    public String get調達＿金融機関1＿名称() { return 調達＿金融機関1＿名称; }
    public void set調達＿金融機関1＿名称(String v) { this.調達＿金融機関1＿名称 = v; }
    public BigDecimal get調達＿金融機関1＿金額() { return 調達＿金融機関1＿金額; }
    public void set調達＿金融機関1＿金額(BigDecimal v) { this.調達＿金融機関1＿金額 = v; }
    public BigDecimal get調達＿金融機関1＿期間() { return 調達＿金融機関1＿期間; }
    public void set調達＿金融機関1＿期間(BigDecimal v) { this.調達＿金融機関1＿期間 = v; }
    public String get調達＿金融機関2＿名称() { return 調達＿金融機関2＿名称; }
    public void set調達＿金融機関2＿名称(String v) { this.調達＿金融機関2＿名称 = v; }
    public BigDecimal get調達＿金融機関2＿金額() { return 調達＿金融機関2＿金額; }
    public void set調達＿金融機関2＿金額(BigDecimal v) { this.調達＿金融機関2＿金額 = v; }
    public BigDecimal get調達＿金融機関2＿期間() { return 調達＿金融機関2＿期間; }
    public void set調達＿金融機関2＿期間(BigDecimal v) { this.調達＿金融機関2＿期間 = v; }
    public BigDecimal get調達＿合計() { return 調達＿合計; }
    public void set調達＿合計(BigDecimal v) { this.調達＿合計 = v; }
    public BigDecimal get自己資金＿預貯金() { return 自己資金＿預貯金; }
    public void set自己資金＿預貯金(BigDecimal v) { this.自己資金＿預貯金 = v; }
    public BigDecimal get自己資金＿その他() { return 自己資金＿その他; }
    public void set自己資金＿その他(BigDecimal v) { this.自己資金＿その他 = v; }
    public BigDecimal get自己資金＿贈与() { return 自己資金＿贈与; }
    public void set自己資金＿贈与(BigDecimal v) { this.自己資金＿贈与 = v; }
    public String get給与振込() { return 給与振込; }
    public void set給与振込(String v) { this.給与振込 = v; }
    public BigDecimal get税込年収＿前々年() { return 税込年収＿前々年; }
    public void set税込年収＿前々年(BigDecimal v) { this.税込年収＿前々年 = v; }
    public BigDecimal get税込年収＿３年前() { return 税込年収＿３年前; }
    public void set税込年収＿３年前(BigDecimal v) { this.税込年収＿３年前 = v; }
}
