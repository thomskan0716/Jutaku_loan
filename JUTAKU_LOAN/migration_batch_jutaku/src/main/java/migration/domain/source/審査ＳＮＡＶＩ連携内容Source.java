package migration.domain.source;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Source: 審査ＳＮＡＶＩ連携内容 (Review S-NAVI linkage content) - SZB_SMS.
 * 105 columns, 1:N per (申込番号, 申込目的) keyed by イベント/イベント日時.
 * Note: source column 上乗せ保証料 maps to target 段階保証料率コード (aliased in SQL);
 * the field below is already named 段階保証料率コード to match the target shape.
 */
public class 審査ＳＮＡＶＩ連携内容Source {

    private String 申込番号;
    private String 申込目的;
    private String イベント;
    private Date イベント日時;
    private String 保証番号;
    private String 静銀信用保証番号;
    private String 審査結果通知年月日;
    private String 店番;
    private String 取扱店番;
    private String ＣＩＦ番号;
    private String 漢字氏名姓;
    private String 漢字氏名名;
    private BigDecimal 借入希望額＿極度額;
    private BigDecimal 返済期間年;
    private BigDecimal 返済期間月;
    private String 集中審査＿返済方法コード;
    private String 団信付保コード＿ローン;
    private BigDecimal 勤続年月数＿年数;
    private String 勤務先漢字名称;
    private BigDecimal 一年前所得年収;
    private BigDecimal 資産土地建物;
    private BigDecimal 資産預貯金;
    private BigDecimal 資産有価証券;
    private BigDecimal 資産その他;
    private BigDecimal 負債銀行借入;
    private BigDecimal 負債クレジット信販;
    private BigDecimal 負債その他;
    private BigDecimal 負債その他借入;
    private BigDecimal 内訳＿毎月返済総額;
    private BigDecimal 一回の元利支払＿毎月;
    private String ボーナス返済月1;
    private String ボーナス返済月2;
    private BigDecimal 内訳＿ボーナス総額;
    private BigDecimal 一回の元利支払＿ボーナス;
    private BigDecimal 当初借入額＿住宅金融公庫;
    private BigDecimal 当初借入額＿年金併せ;
    private BigDecimal 当初借入額＿年金その他;
    private BigDecimal 当初借入額＿県市町村;
    private BigDecimal 当初借入額＿勤務先制度;
    private BigDecimal 当初借入額＿その他;
    private BigDecimal 借入金残高合計;
    private BigDecimal 年間元利返済額＿住宅金融公庫;
    private BigDecimal 年間元利返済額＿年金併せ;
    private BigDecimal 年間元利返済額＿年金その他;
    private BigDecimal 年間元利返済額＿県市町村;
    private BigDecimal 年間元利返済額＿勤務先制度;
    private BigDecimal 年間元利返済額＿その他;
    private BigDecimal 借入金年間返済額合計;
    private BigDecimal 一年前加算者年収;
    private String 購入物件区分;
    private String 購入物件所在地＿漢字;
    private String 購入物件土地地目;
    private BigDecimal 購入物件土地面積;
    private BigDecimal 所要資金＿土地;
    private BigDecimal 所要資金＿建物;
    private BigDecimal 所要資金＿経費;
    private BigDecimal 所要資金＿合計;
    private String 購入物件建物構造;
    private String 購入物件建物屋根;
    private BigDecimal 購入物件建物延面積;
    private String 購入物件土地資金支払先;
    private String 購入物件土地資金支払先その他;
    private String 購入物件建物資金支払先;
    private String 購入物件建物資金支払先その他;
    private BigDecimal 当初借入額＿自己資金;
    private String 保証会社条件1;
    private String 保証会社条件2;
    private String 保証会社条件3;
    private String 保証会社条件4;
    private String 保証会社条件5;
    private String 保証会社条件6;
    private String 保証会社条件7;
    private String 保証会社条件8;
    private String 保証会社条件9;
    private String 保証会社条件10;
    private String スコアリングセグメントコード;
    private String スコアリング一次判定コード;
    private BigDecimal 年間返済比率;
    private String 連帯債務者店番;
    private String 連帯債務者ＣＩＦ番号;
    private String 連帯債務者漢字氏名姓;
    private String 連帯債務者漢字氏名名;
    private BigDecimal 本人団信付保割合;
    private BigDecimal 連帯債務者団信付保割合;
    private String 段階保証料率コード;
    private String 連帯債務者有無;
    private BigDecimal 保証料;
    private BigDecimal 取扱手数料;
    private String 実行前条件1;
    private String 実行前条件2;
    private String 実行前条件3;
    private String 実行前条件4;
    private String 実行前条件5;
    private String 実行前条件6;
    private String 実行前条件7;
    private String 実行前条件8;
    private String 実行前条件9;
    private String 実行前条件10;
    private String 実行予定日;
    private String 分割実行フラグ;
    private String 分割実行予定日;
    private String その他借入先1;
    private BigDecimal その他借入金1金額;
    private String その他借入先2;
    private BigDecimal その他借入金2金額;

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String v) { this.申込番号 = v; }
    public String get申込目的() { return 申込目的; }
    public void set申込目的(String v) { this.申込目的 = v; }
    public String getイベント() { return イベント; }
    public void setイベント(String v) { this.イベント = v; }
    public Date getイベント日時() { return イベント日時; }
    public void setイベント日時(Date v) { this.イベント日時 = v; }
    public String get保証番号() { return 保証番号; }
    public void set保証番号(String v) { this.保証番号 = v; }
    public String get静銀信用保証番号() { return 静銀信用保証番号; }
    public void set静銀信用保証番号(String v) { this.静銀信用保証番号 = v; }
    public String get審査結果通知年月日() { return 審査結果通知年月日; }
    public void set審査結果通知年月日(String v) { this.審査結果通知年月日 = v; }
    public String get店番() { return 店番; }
    public void set店番(String v) { this.店番 = v; }
    public String get取扱店番() { return 取扱店番; }
    public void set取扱店番(String v) { this.取扱店番 = v; }
    public String getＣＩＦ番号() { return ＣＩＦ番号; }
    public void setＣＩＦ番号(String v) { this.ＣＩＦ番号 = v; }
    public String get漢字氏名姓() { return 漢字氏名姓; }
    public void set漢字氏名姓(String v) { this.漢字氏名姓 = v; }
    public String get漢字氏名名() { return 漢字氏名名; }
    public void set漢字氏名名(String v) { this.漢字氏名名 = v; }
    public BigDecimal get借入希望額＿極度額() { return 借入希望額＿極度額; }
    public void set借入希望額＿極度額(BigDecimal v) { this.借入希望額＿極度額 = v; }
    public BigDecimal get返済期間年() { return 返済期間年; }
    public void set返済期間年(BigDecimal v) { this.返済期間年 = v; }
    public BigDecimal get返済期間月() { return 返済期間月; }
    public void set返済期間月(BigDecimal v) { this.返済期間月 = v; }
    public String get集中審査＿返済方法コード() { return 集中審査＿返済方法コード; }
    public void set集中審査＿返済方法コード(String v) { this.集中審査＿返済方法コード = v; }
    public String get団信付保コード＿ローン() { return 団信付保コード＿ローン; }
    public void set団信付保コード＿ローン(String v) { this.団信付保コード＿ローン = v; }
    public BigDecimal get勤続年月数＿年数() { return 勤続年月数＿年数; }
    public void set勤続年月数＿年数(BigDecimal v) { this.勤続年月数＿年数 = v; }
    public String get勤務先漢字名称() { return 勤務先漢字名称; }
    public void set勤務先漢字名称(String v) { this.勤務先漢字名称 = v; }
    public BigDecimal get一年前所得年収() { return 一年前所得年収; }
    public void set一年前所得年収(BigDecimal v) { this.一年前所得年収 = v; }
    public BigDecimal get資産土地建物() { return 資産土地建物; }
    public void set資産土地建物(BigDecimal v) { this.資産土地建物 = v; }
    public BigDecimal get資産預貯金() { return 資産預貯金; }
    public void set資産預貯金(BigDecimal v) { this.資産預貯金 = v; }
    public BigDecimal get資産有価証券() { return 資産有価証券; }
    public void set資産有価証券(BigDecimal v) { this.資産有価証券 = v; }
    public BigDecimal get資産その他() { return 資産その他; }
    public void set資産その他(BigDecimal v) { this.資産その他 = v; }
    public BigDecimal get負債銀行借入() { return 負債銀行借入; }
    public void set負債銀行借入(BigDecimal v) { this.負債銀行借入 = v; }
    public BigDecimal get負債クレジット信販() { return 負債クレジット信販; }
    public void set負債クレジット信販(BigDecimal v) { this.負債クレジット信販 = v; }
    public BigDecimal get負債その他() { return 負債その他; }
    public void set負債その他(BigDecimal v) { this.負債その他 = v; }
    public BigDecimal get負債その他借入() { return 負債その他借入; }
    public void set負債その他借入(BigDecimal v) { this.負債その他借入 = v; }
    public BigDecimal get内訳＿毎月返済総額() { return 内訳＿毎月返済総額; }
    public void set内訳＿毎月返済総額(BigDecimal v) { this.内訳＿毎月返済総額 = v; }
    public BigDecimal get一回の元利支払＿毎月() { return 一回の元利支払＿毎月; }
    public void set一回の元利支払＿毎月(BigDecimal v) { this.一回の元利支払＿毎月 = v; }
    public String getボーナス返済月1() { return ボーナス返済月1; }
    public void setボーナス返済月1(String v) { this.ボーナス返済月1 = v; }
    public String getボーナス返済月2() { return ボーナス返済月2; }
    public void setボーナス返済月2(String v) { this.ボーナス返済月2 = v; }
    public BigDecimal get内訳＿ボーナス総額() { return 内訳＿ボーナス総額; }
    public void set内訳＿ボーナス総額(BigDecimal v) { this.内訳＿ボーナス総額 = v; }
    public BigDecimal get一回の元利支払＿ボーナス() { return 一回の元利支払＿ボーナス; }
    public void set一回の元利支払＿ボーナス(BigDecimal v) { this.一回の元利支払＿ボーナス = v; }
    public BigDecimal get当初借入額＿住宅金融公庫() { return 当初借入額＿住宅金融公庫; }
    public void set当初借入額＿住宅金融公庫(BigDecimal v) { this.当初借入額＿住宅金融公庫 = v; }
    public BigDecimal get当初借入額＿年金併せ() { return 当初借入額＿年金併せ; }
    public void set当初借入額＿年金併せ(BigDecimal v) { this.当初借入額＿年金併せ = v; }
    public BigDecimal get当初借入額＿年金その他() { return 当初借入額＿年金その他; }
    public void set当初借入額＿年金その他(BigDecimal v) { this.当初借入額＿年金その他 = v; }
    public BigDecimal get当初借入額＿県市町村() { return 当初借入額＿県市町村; }
    public void set当初借入額＿県市町村(BigDecimal v) { this.当初借入額＿県市町村 = v; }
    public BigDecimal get当初借入額＿勤務先制度() { return 当初借入額＿勤務先制度; }
    public void set当初借入額＿勤務先制度(BigDecimal v) { this.当初借入額＿勤務先制度 = v; }
    public BigDecimal get当初借入額＿その他() { return 当初借入額＿その他; }
    public void set当初借入額＿その他(BigDecimal v) { this.当初借入額＿その他 = v; }
    public BigDecimal get借入金残高合計() { return 借入金残高合計; }
    public void set借入金残高合計(BigDecimal v) { this.借入金残高合計 = v; }
    public BigDecimal get年間元利返済額＿住宅金融公庫() { return 年間元利返済額＿住宅金融公庫; }
    public void set年間元利返済額＿住宅金融公庫(BigDecimal v) { this.年間元利返済額＿住宅金融公庫 = v; }
    public BigDecimal get年間元利返済額＿年金併せ() { return 年間元利返済額＿年金併せ; }
    public void set年間元利返済額＿年金併せ(BigDecimal v) { this.年間元利返済額＿年金併せ = v; }
    public BigDecimal get年間元利返済額＿年金その他() { return 年間元利返済額＿年金その他; }
    public void set年間元利返済額＿年金その他(BigDecimal v) { this.年間元利返済額＿年金その他 = v; }
    public BigDecimal get年間元利返済額＿県市町村() { return 年間元利返済額＿県市町村; }
    public void set年間元利返済額＿県市町村(BigDecimal v) { this.年間元利返済額＿県市町村 = v; }
    public BigDecimal get年間元利返済額＿勤務先制度() { return 年間元利返済額＿勤務先制度; }
    public void set年間元利返済額＿勤務先制度(BigDecimal v) { this.年間元利返済額＿勤務先制度 = v; }
    public BigDecimal get年間元利返済額＿その他() { return 年間元利返済額＿その他; }
    public void set年間元利返済額＿その他(BigDecimal v) { this.年間元利返済額＿その他 = v; }
    public BigDecimal get借入金年間返済額合計() { return 借入金年間返済額合計; }
    public void set借入金年間返済額合計(BigDecimal v) { this.借入金年間返済額合計 = v; }
    public BigDecimal get一年前加算者年収() { return 一年前加算者年収; }
    public void set一年前加算者年収(BigDecimal v) { this.一年前加算者年収 = v; }
    public String get購入物件区分() { return 購入物件区分; }
    public void set購入物件区分(String v) { this.購入物件区分 = v; }
    public String get購入物件所在地＿漢字() { return 購入物件所在地＿漢字; }
    public void set購入物件所在地＿漢字(String v) { this.購入物件所在地＿漢字 = v; }
    public String get購入物件土地地目() { return 購入物件土地地目; }
    public void set購入物件土地地目(String v) { this.購入物件土地地目 = v; }
    public BigDecimal get購入物件土地面積() { return 購入物件土地面積; }
    public void set購入物件土地面積(BigDecimal v) { this.購入物件土地面積 = v; }
    public BigDecimal get所要資金＿土地() { return 所要資金＿土地; }
    public void set所要資金＿土地(BigDecimal v) { this.所要資金＿土地 = v; }
    public BigDecimal get所要資金＿建物() { return 所要資金＿建物; }
    public void set所要資金＿建物(BigDecimal v) { this.所要資金＿建物 = v; }
    public BigDecimal get所要資金＿経費() { return 所要資金＿経費; }
    public void set所要資金＿経費(BigDecimal v) { this.所要資金＿経費 = v; }
    public BigDecimal get所要資金＿合計() { return 所要資金＿合計; }
    public void set所要資金＿合計(BigDecimal v) { this.所要資金＿合計 = v; }
    public String get購入物件建物構造() { return 購入物件建物構造; }
    public void set購入物件建物構造(String v) { this.購入物件建物構造 = v; }
    public String get購入物件建物屋根() { return 購入物件建物屋根; }
    public void set購入物件建物屋根(String v) { this.購入物件建物屋根 = v; }
    public BigDecimal get購入物件建物延面積() { return 購入物件建物延面積; }
    public void set購入物件建物延面積(BigDecimal v) { this.購入物件建物延面積 = v; }
    public String get購入物件土地資金支払先() { return 購入物件土地資金支払先; }
    public void set購入物件土地資金支払先(String v) { this.購入物件土地資金支払先 = v; }
    public String get購入物件土地資金支払先その他() { return 購入物件土地資金支払先その他; }
    public void set購入物件土地資金支払先その他(String v) { this.購入物件土地資金支払先その他 = v; }
    public String get購入物件建物資金支払先() { return 購入物件建物資金支払先; }
    public void set購入物件建物資金支払先(String v) { this.購入物件建物資金支払先 = v; }
    public String get購入物件建物資金支払先その他() { return 購入物件建物資金支払先その他; }
    public void set購入物件建物資金支払先その他(String v) { this.購入物件建物資金支払先その他 = v; }
    public BigDecimal get当初借入額＿自己資金() { return 当初借入額＿自己資金; }
    public void set当初借入額＿自己資金(BigDecimal v) { this.当初借入額＿自己資金 = v; }
    public String get保証会社条件1() { return 保証会社条件1; }
    public void set保証会社条件1(String v) { this.保証会社条件1 = v; }
    public String get保証会社条件2() { return 保証会社条件2; }
    public void set保証会社条件2(String v) { this.保証会社条件2 = v; }
    public String get保証会社条件3() { return 保証会社条件3; }
    public void set保証会社条件3(String v) { this.保証会社条件3 = v; }
    public String get保証会社条件4() { return 保証会社条件4; }
    public void set保証会社条件4(String v) { this.保証会社条件4 = v; }
    public String get保証会社条件5() { return 保証会社条件5; }
    public void set保証会社条件5(String v) { this.保証会社条件5 = v; }
    public String get保証会社条件6() { return 保証会社条件6; }
    public void set保証会社条件6(String v) { this.保証会社条件6 = v; }
    public String get保証会社条件7() { return 保証会社条件7; }
    public void set保証会社条件7(String v) { this.保証会社条件7 = v; }
    public String get保証会社条件8() { return 保証会社条件8; }
    public void set保証会社条件8(String v) { this.保証会社条件8 = v; }
    public String get保証会社条件9() { return 保証会社条件9; }
    public void set保証会社条件9(String v) { this.保証会社条件9 = v; }
    public String get保証会社条件10() { return 保証会社条件10; }
    public void set保証会社条件10(String v) { this.保証会社条件10 = v; }
    public String getスコアリングセグメントコード() { return スコアリングセグメントコード; }
    public void setスコアリングセグメントコード(String v) { this.スコアリングセグメントコード = v; }
    public String getスコアリング一次判定コード() { return スコアリング一次判定コード; }
    public void setスコアリング一次判定コード(String v) { this.スコアリング一次判定コード = v; }
    public BigDecimal get年間返済比率() { return 年間返済比率; }
    public void set年間返済比率(BigDecimal v) { this.年間返済比率 = v; }
    public String get連帯債務者店番() { return 連帯債務者店番; }
    public void set連帯債務者店番(String v) { this.連帯債務者店番 = v; }
    public String get連帯債務者ＣＩＦ番号() { return 連帯債務者ＣＩＦ番号; }
    public void set連帯債務者ＣＩＦ番号(String v) { this.連帯債務者ＣＩＦ番号 = v; }
    public String get連帯債務者漢字氏名姓() { return 連帯債務者漢字氏名姓; }
    public void set連帯債務者漢字氏名姓(String v) { this.連帯債務者漢字氏名姓 = v; }
    public String get連帯債務者漢字氏名名() { return 連帯債務者漢字氏名名; }
    public void set連帯債務者漢字氏名名(String v) { this.連帯債務者漢字氏名名 = v; }
    public BigDecimal get本人団信付保割合() { return 本人団信付保割合; }
    public void set本人団信付保割合(BigDecimal v) { this.本人団信付保割合 = v; }
    public BigDecimal get連帯債務者団信付保割合() { return 連帯債務者団信付保割合; }
    public void set連帯債務者団信付保割合(BigDecimal v) { this.連帯債務者団信付保割合 = v; }
    public String get段階保証料率コード() { return 段階保証料率コード; }
    public void set段階保証料率コード(String v) { this.段階保証料率コード = v; }
    public String get連帯債務者有無() { return 連帯債務者有無; }
    public void set連帯債務者有無(String v) { this.連帯債務者有無 = v; }
    public BigDecimal get保証料() { return 保証料; }
    public void set保証料(BigDecimal v) { this.保証料 = v; }
    public BigDecimal get取扱手数料() { return 取扱手数料; }
    public void set取扱手数料(BigDecimal v) { this.取扱手数料 = v; }
    public String get実行前条件1() { return 実行前条件1; }
    public void set実行前条件1(String v) { this.実行前条件1 = v; }
    public String get実行前条件2() { return 実行前条件2; }
    public void set実行前条件2(String v) { this.実行前条件2 = v; }
    public String get実行前条件3() { return 実行前条件3; }
    public void set実行前条件3(String v) { this.実行前条件3 = v; }
    public String get実行前条件4() { return 実行前条件4; }
    public void set実行前条件4(String v) { this.実行前条件4 = v; }
    public String get実行前条件5() { return 実行前条件5; }
    public void set実行前条件5(String v) { this.実行前条件5 = v; }
    public String get実行前条件6() { return 実行前条件6; }
    public void set実行前条件6(String v) { this.実行前条件6 = v; }
    public String get実行前条件7() { return 実行前条件7; }
    public void set実行前条件7(String v) { this.実行前条件7 = v; }
    public String get実行前条件8() { return 実行前条件8; }
    public void set実行前条件8(String v) { this.実行前条件8 = v; }
    public String get実行前条件9() { return 実行前条件9; }
    public void set実行前条件9(String v) { this.実行前条件9 = v; }
    public String get実行前条件10() { return 実行前条件10; }
    public void set実行前条件10(String v) { this.実行前条件10 = v; }
    public String get実行予定日() { return 実行予定日; }
    public void set実行予定日(String v) { this.実行予定日 = v; }
    public String get分割実行フラグ() { return 分割実行フラグ; }
    public void set分割実行フラグ(String v) { this.分割実行フラグ = v; }
    public String get分割実行予定日() { return 分割実行予定日; }
    public void set分割実行予定日(String v) { this.分割実行予定日 = v; }
    public String getその他借入先1() { return その他借入先1; }
    public void setその他借入先1(String v) { this.その他借入先1 = v; }
    public BigDecimal getその他借入金1金額() { return その他借入金1金額; }
    public void setその他借入金1金額(BigDecimal v) { this.その他借入金1金額 = v; }
    public String getその他借入先2() { return その他借入先2; }
    public void setその他借入先2(String v) { this.その他借入先2 = v; }
    public BigDecimal getその他借入金2金額() { return その他借入金2金額; }
    public void setその他借入金2金額(BigDecimal v) { this.その他借入金2金額 = v; }
}
