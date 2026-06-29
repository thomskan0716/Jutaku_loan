package migration.domain.source;

import java.math.BigDecimal;

public class 申込Source {

    private String 申込番号;
    private String 申込目的;
    private String 商品大分類;
    private String 商品コード;
    private String 受付店舗;
    private String 保証番号;
    private String 関連案件有無;
    private String 申込日;
    private String 店舗;
    private String ＣＩＦ番号;
    private String カナ氏名;
    private String 漢字氏名;
    private String 自宅郵便番号;
    private String 自宅住所カナ;
    private String 自宅住所漢字;
    private String 生年月日;
    private String 性別;
    private String 自宅申込番号;
    private String 担保申込番号;
    private String 勤務先名漢字;
    private BigDecimal 勤務先入社年月;
    private BigDecimal 勤務先勤続年数;
    private String 勤務先職業;
    private String 勤務先職種役取;
    private String 勤務先資本金区分;
    private BigDecimal 勤務先従業員数;
    private String 住居区分;
    private BigDecimal 定積;
    private BigDecimal 展示年数;
    private BigDecimal 定積＿子の他;
    private String 勤務先歩合給区分;
    private String 勤務先給料払込;
    private BigDecimal 勤務先資本金;
    private String 借入＿利用先名1;
    private String 借入＿利用種類1;
    private BigDecimal 借入＿利用残高1;
    private BigDecimal 借入＿年間変払額1;
    private BigDecimal 借入＿残存期間1;
    private String 借入＿解約予定1;
    private BigDecimal 借入＿利用限度額1;
    private String 借入＿利用先名2;
    private String 借入＿利用種類2;
    private BigDecimal 借入＿利用残高2;
    private BigDecimal 借入＿年間変払額2;
    private BigDecimal 借入＿残存期間2;
    private String 借入＿解約予定2;
    private BigDecimal 借入＿利用限度額2;
    private String 借入＿利用先名3;
    private String 借入＿利用種類3;
    private BigDecimal 借入＿利用残高3;
    private BigDecimal 借入＿年間変払額3;
    private BigDecimal 借入＿残存期間3;
    private String 借入＿解約予定3;
    private BigDecimal 借入＿利用限度額3;
    private String 資金使途;
    private BigDecimal 借入金額;
    private BigDecimal 借入金額＿毎月;
    private BigDecimal 借入金額＿半年額;
    private BigDecimal 借入期間;
    private String 借入希望日;
    private String 借入希望日＿建物;
    private String 返済方法区分;
    private String 金利区分;
    private String 保証料区分;
    private String ボーナス返済月1;
    private String ボーナス返済月2;
    private BigDecimal 返済比率2;
    private BigDecimal 返済比率3;
    private BigDecimal 必要資金＿土地;
    private BigDecimal 必要資金＿建物;
    private BigDecimal 必要資金＿借替;
    private BigDecimal 必要資金＿諸費用;
    private BigDecimal 必要資金＿その他;
    private BigDecimal 必要資金＿合計;
    private String 調達＿その他1＿借入先;
    private BigDecimal 調達＿その他1;
    private BigDecimal 調達＿その他1＿期間;
    private String 調達＿その他2＿借入先;
    private BigDecimal 調達＿その他2;
    private BigDecimal 調達＿その他2＿期間;
    private BigDecimal 調達＿合計;
    private BigDecimal 自己資金＿預貯金;
    private BigDecimal 自己資金＿その他;
    private BigDecimal 自己資金＿贈与;
    private String 勤務先給与振込;
    private BigDecimal 年収２;
    private BigDecimal 年収３;
    private String 同居＿配偶者;
    private String 同居＿父;
    private String 同居＿母;
    private BigDecimal 同居＿その他人数;
    private BigDecimal 同居＿子の人数;
    private BigDecimal 同居＿子供年齢1;
    private BigDecimal 同居＿子供年齢2;
    private BigDecimal 同居＿子供年齢3;
    private BigDecimal 同居＿子供年齢4;
    private String 婚姻区分;
    private String 申込審区分;
    private String 外部連携受付番号;
    private String 土地契約予定日;
    private String 担保完成予定日;
    private String 預金＿金融機関名1;
    private BigDecimal 預金＿本人預金1;
    private BigDecimal 預金＿家族預金1;
    private String 預金＿金融機関名2;
    private BigDecimal 預金＿本人預金2;
    private BigDecimal 預金＿家族預金2;
    private BigDecimal 預金＿本人預金3;
    private BigDecimal 預金＿家族預金3;
    private BigDecimal 預金＿本人預金4;
    private BigDecimal 預金＿家族預金4;
    private String 勤務先郵便番号;
    private String 携帯電話番号;
    private String 建物完成予定日;
    private String 検索用カナ氏名;
    // From 申込ワイド (LEFT JOIN)
    private String 上場フラグ;
    private String 国家資格;
    private String 国家資格子の他;
    private BigDecimal 配偶者年収;

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String v) { this.申込番号 = v; }
    public String get申込目的() { return 申込目的; }
    public void set申込目的(String v) { this.申込目的 = v; }
    public String get商品大分類() { return 商品大分類; }
    public void set商品大分類(String v) { this.商品大分類 = v; }
    public String get商品コード() { return 商品コード; }
    public void set商品コード(String v) { this.商品コード = v; }
    public String get受付店舗() { return 受付店舗; }
    public void set受付店舗(String v) { this.受付店舗 = v; }
    public String get保証番号() { return 保証番号; }
    public void set保証番号(String v) { this.保証番号 = v; }
    public String get関連案件有無() { return 関連案件有無; }
    public void set関連案件有無(String v) { this.関連案件有無 = v; }
    public String get申込日() { return 申込日; }
    public void set申込日(String v) { this.申込日 = v; }
    public String get店舗() { return 店舗; }
    public void set店舗(String v) { this.店舗 = v; }
    public String getＣＩＦ番号() { return ＣＩＦ番号; }
    public void setＣＩＦ番号(String v) { this.ＣＩＦ番号 = v; }
    public String getカナ氏名() { return カナ氏名; }
    public void setカナ氏名(String v) { this.カナ氏名 = v; }
    public String get漢字氏名() { return 漢字氏名; }
    public void set漢字氏名(String v) { this.漢字氏名 = v; }
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
    public String get自宅申込番号() { return 自宅申込番号; }
    public void set自宅申込番号(String v) { this.自宅申込番号 = v; }
    public String get担保申込番号() { return 担保申込番号; }
    public void set担保申込番号(String v) { this.担保申込番号 = v; }
    public String get勤務先名漢字() { return 勤務先名漢字; }
    public void set勤務先名漢字(String v) { this.勤務先名漢字 = v; }
    public BigDecimal get勤務先入社年月() { return 勤務先入社年月; }
    public void set勤務先入社年月(BigDecimal v) { this.勤務先入社年月 = v; }
    public BigDecimal get勤務先勤続年数() { return 勤務先勤続年数; }
    public void set勤務先勤続年数(BigDecimal v) { this.勤務先勤続年数 = v; }
    public String get勤務先職業() { return 勤務先職業; }
    public void set勤務先職業(String v) { this.勤務先職業 = v; }
    public String get勤務先職種役取() { return 勤務先職種役取; }
    public void set勤務先職種役取(String v) { this.勤務先職種役取 = v; }
    public String get勤務先資本金区分() { return 勤務先資本金区分; }
    public void set勤務先資本金区分(String v) { this.勤務先資本金区分 = v; }
    public BigDecimal get勤務先従業員数() { return 勤務先従業員数; }
    public void set勤務先従業員数(BigDecimal v) { this.勤務先従業員数 = v; }
    public String get住居区分() { return 住居区分; }
    public void set住居区分(String v) { this.住居区分 = v; }
    public BigDecimal get定積() { return 定積; }
    public void set定積(BigDecimal v) { this.定積 = v; }
    public BigDecimal get展示年数() { return 展示年数; }
    public void set展示年数(BigDecimal v) { this.展示年数 = v; }
    public BigDecimal get定積＿子の他() { return 定積＿子の他; }
    public void set定積＿子の他(BigDecimal v) { this.定積＿子の他 = v; }
    public String get勤務先歩合給区分() { return 勤務先歩合給区分; }
    public void set勤務先歩合給区分(String v) { this.勤務先歩合給区分 = v; }
    public String get勤務先給料払込() { return 勤務先給料払込; }
    public void set勤務先給料払込(String v) { this.勤務先給料払込 = v; }
    public BigDecimal get勤務先資本金() { return 勤務先資本金; }
    public void set勤務先資本金(BigDecimal v) { this.勤務先資本金 = v; }
    public String get借入＿利用先名1() { return 借入＿利用先名1; }
    public void set借入＿利用先名1(String v) { this.借入＿利用先名1 = v; }
    public String get借入＿利用種類1() { return 借入＿利用種類1; }
    public void set借入＿利用種類1(String v) { this.借入＿利用種類1 = v; }
    public BigDecimal get借入＿利用残高1() { return 借入＿利用残高1; }
    public void set借入＿利用残高1(BigDecimal v) { this.借入＿利用残高1 = v; }
    public BigDecimal get借入＿年間変払額1() { return 借入＿年間変払額1; }
    public void set借入＿年間変払額1(BigDecimal v) { this.借入＿年間変払額1 = v; }
    public BigDecimal get借入＿残存期間1() { return 借入＿残存期間1; }
    public void set借入＿残存期間1(BigDecimal v) { this.借入＿残存期間1 = v; }
    public String get借入＿解約予定1() { return 借入＿解約予定1; }
    public void set借入＿解約予定1(String v) { this.借入＿解約予定1 = v; }
    public BigDecimal get借入＿利用限度額1() { return 借入＿利用限度額1; }
    public void set借入＿利用限度額1(BigDecimal v) { this.借入＿利用限度額1 = v; }
    public String get借入＿利用先名2() { return 借入＿利用先名2; }
    public void set借入＿利用先名2(String v) { this.借入＿利用先名2 = v; }
    public String get借入＿利用種類2() { return 借入＿利用種類2; }
    public void set借入＿利用種類2(String v) { this.借入＿利用種類2 = v; }
    public BigDecimal get借入＿利用残高2() { return 借入＿利用残高2; }
    public void set借入＿利用残高2(BigDecimal v) { this.借入＿利用残高2 = v; }
    public BigDecimal get借入＿年間変払額2() { return 借入＿年間変払額2; }
    public void set借入＿年間変払額2(BigDecimal v) { this.借入＿年間変払額2 = v; }
    public BigDecimal get借入＿残存期間2() { return 借入＿残存期間2; }
    public void set借入＿残存期間2(BigDecimal v) { this.借入＿残存期間2 = v; }
    public String get借入＿解約予定2() { return 借入＿解約予定2; }
    public void set借入＿解約予定2(String v) { this.借入＿解約予定2 = v; }
    public BigDecimal get借入＿利用限度額2() { return 借入＿利用限度額2; }
    public void set借入＿利用限度額2(BigDecimal v) { this.借入＿利用限度額2 = v; }
    public String get借入＿利用先名3() { return 借入＿利用先名3; }
    public void set借入＿利用先名3(String v) { this.借入＿利用先名3 = v; }
    public String get借入＿利用種類3() { return 借入＿利用種類3; }
    public void set借入＿利用種類3(String v) { this.借入＿利用種類3 = v; }
    public BigDecimal get借入＿利用残高3() { return 借入＿利用残高3; }
    public void set借入＿利用残高3(BigDecimal v) { this.借入＿利用残高3 = v; }
    public BigDecimal get借入＿年間変払額3() { return 借入＿年間変払額3; }
    public void set借入＿年間変払額3(BigDecimal v) { this.借入＿年間変払額3 = v; }
    public BigDecimal get借入＿残存期間3() { return 借入＿残存期間3; }
    public void set借入＿残存期間3(BigDecimal v) { this.借入＿残存期間3 = v; }
    public String get借入＿解約予定3() { return 借入＿解約予定3; }
    public void set借入＿解約予定3(String v) { this.借入＿解約予定3 = v; }
    public BigDecimal get借入＿利用限度額3() { return 借入＿利用限度額3; }
    public void set借入＿利用限度額3(BigDecimal v) { this.借入＿利用限度額3 = v; }
    public String get資金使途() { return 資金使途; }
    public void set資金使途(String v) { this.資金使途 = v; }
    public BigDecimal get借入金額() { return 借入金額; }
    public void set借入金額(BigDecimal v) { this.借入金額 = v; }
    public BigDecimal get借入金額＿毎月() { return 借入金額＿毎月; }
    public void set借入金額＿毎月(BigDecimal v) { this.借入金額＿毎月 = v; }
    public BigDecimal get借入金額＿半年額() { return 借入金額＿半年額; }
    public void set借入金額＿半年額(BigDecimal v) { this.借入金額＿半年額 = v; }
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
    public BigDecimal get返済比率2() { return 返済比率2; }
    public void set返済比率2(BigDecimal v) { this.返済比率2 = v; }
    public BigDecimal get返済比率3() { return 返済比率3; }
    public void set返済比率3(BigDecimal v) { this.返済比率3 = v; }
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
    public String get調達＿その他1＿借入先() { return 調達＿その他1＿借入先; }
    public void set調達＿その他1＿借入先(String v) { this.調達＿その他1＿借入先 = v; }
    public BigDecimal get調達＿その他1() { return 調達＿その他1; }
    public void set調達＿その他1(BigDecimal v) { this.調達＿その他1 = v; }
    public BigDecimal get調達＿その他1＿期間() { return 調達＿その他1＿期間; }
    public void set調達＿その他1＿期間(BigDecimal v) { this.調達＿その他1＿期間 = v; }
    public String get調達＿その他2＿借入先() { return 調達＿その他2＿借入先; }
    public void set調達＿その他2＿借入先(String v) { this.調達＿その他2＿借入先 = v; }
    public BigDecimal get調達＿その他2() { return 調達＿その他2; }
    public void set調達＿その他2(BigDecimal v) { this.調達＿その他2 = v; }
    public BigDecimal get調達＿その他2＿期間() { return 調達＿その他2＿期間; }
    public void set調達＿その他2＿期間(BigDecimal v) { this.調達＿その他2＿期間 = v; }
    public BigDecimal get調達＿合計() { return 調達＿合計; }
    public void set調達＿合計(BigDecimal v) { this.調達＿合計 = v; }
    public BigDecimal get自己資金＿預貯金() { return 自己資金＿預貯金; }
    public void set自己資金＿預貯金(BigDecimal v) { this.自己資金＿預貯金 = v; }
    public BigDecimal get自己資金＿その他() { return 自己資金＿その他; }
    public void set自己資金＿その他(BigDecimal v) { this.自己資金＿その他 = v; }
    public BigDecimal get自己資金＿贈与() { return 自己資金＿贈与; }
    public void set自己資金＿贈与(BigDecimal v) { this.自己資金＿贈与 = v; }
    public String get勤務先給与振込() { return 勤務先給与振込; }
    public void set勤務先給与振込(String v) { this.勤務先給与振込 = v; }
    public BigDecimal get年収２() { return 年収２; }
    public void set年収２(BigDecimal v) { this.年収２ = v; }
    public BigDecimal get年収３() { return 年収３; }
    public void set年収３(BigDecimal v) { this.年収３ = v; }
    public String get同居＿配偶者() { return 同居＿配偶者; }
    public void set同居＿配偶者(String v) { this.同居＿配偶者 = v; }
    public String get同居＿父() { return 同居＿父; }
    public void set同居＿父(String v) { this.同居＿父 = v; }
    public String get同居＿母() { return 同居＿母; }
    public void set同居＿母(String v) { this.同居＿母 = v; }
    public BigDecimal get同居＿その他人数() { return 同居＿その他人数; }
    public void set同居＿その他人数(BigDecimal v) { this.同居＿その他人数 = v; }
    public BigDecimal get同居＿子の人数() { return 同居＿子の人数; }
    public void set同居＿子の人数(BigDecimal v) { this.同居＿子の人数 = v; }
    public BigDecimal get同居＿子供年齢1() { return 同居＿子供年齢1; }
    public void set同居＿子供年齢1(BigDecimal v) { this.同居＿子供年齢1 = v; }
    public BigDecimal get同居＿子供年齢2() { return 同居＿子供年齢2; }
    public void set同居＿子供年齢2(BigDecimal v) { this.同居＿子供年齢2 = v; }
    public BigDecimal get同居＿子供年齢3() { return 同居＿子供年齢3; }
    public void set同居＿子供年齢3(BigDecimal v) { this.同居＿子供年齢3 = v; }
    public BigDecimal get同居＿子供年齢4() { return 同居＿子供年齢4; }
    public void set同居＿子供年齢4(BigDecimal v) { this.同居＿子供年齢4 = v; }
    public String get婚姻区分() { return 婚姻区分; }
    public void set婚姻区分(String v) { this.婚姻区分 = v; }
    public String get申込審区分() { return 申込審区分; }
    public void set申込審区分(String v) { this.申込審区分 = v; }
    public String get外部連携受付番号() { return 外部連携受付番号; }
    public void set外部連携受付番号(String v) { this.外部連携受付番号 = v; }
    public String get土地契約予定日() { return 土地契約予定日; }
    public void set土地契約予定日(String v) { this.土地契約予定日 = v; }
    public String get担保完成予定日() { return 担保完成予定日; }
    public void set担保完成予定日(String v) { this.担保完成予定日 = v; }
    public String get預金＿金融機関名1() { return 預金＿金融機関名1; }
    public void set預金＿金融機関名1(String v) { this.預金＿金融機関名1 = v; }
    public BigDecimal get預金＿本人預金1() { return 預金＿本人預金1; }
    public void set預金＿本人預金1(BigDecimal v) { this.預金＿本人預金1 = v; }
    public BigDecimal get預金＿家族預金1() { return 預金＿家族預金1; }
    public void set預金＿家族預金1(BigDecimal v) { this.預金＿家族預金1 = v; }
    public String get預金＿金融機関名2() { return 預金＿金融機関名2; }
    public void set預金＿金融機関名2(String v) { this.預金＿金融機関名2 = v; }
    public BigDecimal get預金＿本人預金2() { return 預金＿本人預金2; }
    public void set預金＿本人預金2(BigDecimal v) { this.預金＿本人預金2 = v; }
    public BigDecimal get預金＿家族預金2() { return 預金＿家族預金2; }
    public void set預金＿家族預金2(BigDecimal v) { this.預金＿家族預金2 = v; }
    public BigDecimal get預金＿本人預金3() { return 預金＿本人預金3; }
    public void set預金＿本人預金3(BigDecimal v) { this.預金＿本人預金3 = v; }
    public BigDecimal get預金＿家族預金3() { return 預金＿家族預金3; }
    public void set預金＿家族預金3(BigDecimal v) { this.預金＿家族預金3 = v; }
    public BigDecimal get預金＿本人預金4() { return 預金＿本人預金4; }
    public void set預金＿本人預金4(BigDecimal v) { this.預金＿本人預金4 = v; }
    public BigDecimal get預金＿家族預金4() { return 預金＿家族預金4; }
    public void set預金＿家族預金4(BigDecimal v) { this.預金＿家族預金4 = v; }
    public String get勤務先郵便番号() { return 勤務先郵便番号; }
    public void set勤務先郵便番号(String v) { this.勤務先郵便番号 = v; }
    public String get携帯電話番号() { return 携帯電話番号; }
    public void set携帯電話番号(String v) { this.携帯電話番号 = v; }
    public String get建物完成予定日() { return 建物完成予定日; }
    public void set建物完成予定日(String v) { this.建物完成予定日 = v; }
    public String get検索用カナ氏名() { return 検索用カナ氏名; }
    public void set検索用カナ氏名(String v) { this.検索用カナ氏名 = v; }
    public String get上場フラグ() { return 上場フラグ; }
    public void set上場フラグ(String v) { this.上場フラグ = v; }
    public String get国家資格() { return 国家資格; }
    public void set国家資格(String v) { this.国家資格 = v; }
    public String get国家資格子の他() { return 国家資格子の他; }
    public void set国家資格子の他(String v) { this.国家資格子の他 = v; }
    public BigDecimal get配偶者年収() { return 配偶者年収; }
    public void set配偶者年収(BigDecimal v) { this.配偶者年収 = v; }
}
