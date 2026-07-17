package migration.domain.source;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Source: 審査契約書出力連携内容 (Review contract-document output linkage content) - SZB_SMS.
 * 143 migrated columns (source cols 3-145), 1:N per (申込番号, 申込目的) keyed by イベント/イベント日時.
 * Target table is ITF_SMS.ＩＦ＿契約書送信.
 *
 * Field (property) names follow the TARGET shape; the source SQL aliases the renamed
 * source columns to these names:
 *   ＣＩＦ                         -> ＣＩＦ番号
 *   債務者甲名                     -> 債務者甲＿名
 *   債務者甲名カナ                 -> 債務者甲＿名カナ
 *   標準金利パーセント             -> 標準金利
 *   全期間乖離幅＿数値             -> 全期間乖離幅
 *   毎月返済２回目以降元金返済年月 -> 毎月返済＿２回目以降元金返済年月日
 *
 * Numbered columns use FULL-WIDTH digits in the DB (e.g. 連帯保証人１＿氏名２); the Java
 * properties keep half-width digits, so property (Java) and column (DB) differ.
 */
public class 審査契約書出力連携内容Source {

    private String 申込番号;
    private String 申込目的;
    private String イベント;
    private Date イベント日時;
    private BigDecimal レコード区分;
    private BigDecimal 作成基準日時;
    private BigDecimal 取上店番;
    private BigDecimal 勘定店番;
    private String ＣＩＦ番号;
    private String 債務者甲＿名;
    private String 債務者甲＿名カナ;
    private String 顧客住所;
    private BigDecimal 法人区分;
    private BigDecimal 業種コード;
    private String 職業;
    private BigDecimal データ区分;
    private BigDecimal 処理区分コード;
    private BigDecimal 取扱番号;
    private BigDecimal 枝番;
    private BigDecimal 融資科目コード;
    private BigDecimal 融資分類コード;
    private BigDecimal 貸出種類コード;
    private BigDecimal 貸出日;
    private BigDecimal 貸出金額;
    private BigDecimal 貸出方法区分;
    private BigDecimal 当初借入利率;
    private String 資金使途コード;
    private String 資金使途備考;
    private BigDecimal 現在残高;
    private BigDecimal 最終返済日;
    private BigDecimal 発行日;
    private BigDecimal 実行予定日;
    private BigDecimal 徴求日;
    private BigDecimal 実行日;
    private BigDecimal 否決日;
    private BigDecimal 連携区分;
    private BigDecimal 承認ステータス;
    private BigDecimal 極度額;
    private BigDecimal 抵当権設定日＿原契約日;
    private BigDecimal 根抵当権契約設定日;
    private BigDecimal 債務承認開始日;
    private BigDecimal 債務承認終了日;
    private BigDecimal 保証委託契約日;
    private BigDecimal 印紙税額;
    private BigDecimal 稟議種別;
    private BigDecimal 保証番号;
    private String 金利種類区分;
    private String 金利型名称備考;
    private String 返済方法;
    private String 弁済方法名称備考;
    private String 基準金利区分;
    private BigDecimal 標準金利;
    private BigDecimal 固定金利再選択時の利幅＿符号;
    private BigDecimal 固定金利再選択時の利幅＿数値;
    private BigDecimal 貸付期間＿年;
    private BigDecimal 貸付期間＿月;
    private BigDecimal 債務者＿甲＿団信;
    private BigDecimal 利息支払日;
    private BigDecimal 利息払込済日;
    private BigDecimal 利息約定日;
    private String 利率方式;
    private BigDecimal その他期日;
    private String 休日補正区分;
    private BigDecimal 返済用預金口座店番;
    private String 返済用預金口座種目;
    private BigDecimal 返済用預金口座番号;
    private String 返済用口座名義;
    private String 据置ＣＤ;
    private String 据置備考;
    private BigDecimal 元金据置期限;
    private BigDecimal 毎月返済＿内訳＿貸出金額;
    private BigDecimal 毎月返済＿初回返済日;
    private BigDecimal 毎月返済＿返済日;
    private BigDecimal 毎回返済＿返済間隔;
    private BigDecimal 毎回返済＿返済回数;
    private BigDecimal 毎月返済＿初回返済金;
    private BigDecimal 毎月返済＿返済金;
    private BigDecimal 毎月返済＿最終返済金;
    private BigDecimal 半年毎返済＿内訳＿貸出金額;
    private BigDecimal 半年毎返済＿初回返済日;
    private BigDecimal 半年毎返済＿返済月1;
    private BigDecimal 半年毎返済＿返済月2;
    private BigDecimal 半年毎返済＿返済日;
    private BigDecimal 半年毎増額＿返済回数;
    private BigDecimal 半年毎＿初回返済金;
    private BigDecimal 半年毎＿返済金;
    private BigDecimal 半年毎増額＿返済金額2;
    private BigDecimal 半年毎＿最終返済金;
    private BigDecimal 担保コード;
    private BigDecimal 担保明細コード;
    private BigDecimal 保証先;
    private BigDecimal 順位;
    private String 手形サイト;
    private BigDecimal 全期間乖離幅;
    private String 利率サイクル;
    private BigDecimal 毎月返済＿2回目以降元金返済年月日;
    private String 債務者＿甲＿電話番号;
    private BigDecimal 初回利払日;
    private BigDecimal 当初固定金利期間;
    private BigDecimal 返済回数;
    private BigDecimal 据置回数;
    private BigDecimal 連帯債務者勘定店番;
    private String 連帯債務者ＣＩＦ;
    private String 連帯債務者＿乙＿名;
    private String 連帯債務者＿乙＿住所;
    private BigDecimal 債務者甲負担割合＿分母;
    private BigDecimal 債務者甲負担割合＿分子;
    private BigDecimal 連帯債務者乙＿分母;
    private BigDecimal 連帯債務者乙＿分子;
    private BigDecimal 連帯債務者＿乙＿団信;
    private BigDecimal 連帯保証人1＿対象区分;
    private String 連帯保証人1＿氏名;
    private String 連帯保証人1＿氏名2;
    private String 連帯保証人1＿氏名3;
    private BigDecimal 連帯保証人1＿保証期日;
    private BigDecimal 連帯保証人1＿限度額;
    private BigDecimal 連帯保証人1＿科目;
    private String 連帯保証人1＿住所;
    private BigDecimal 連帯保証人1＿生年月日;
    private String 連帯保証人1＿勤務先;
    private BigDecimal 連帯保証人1＿法人;
    private BigDecimal 連帯保証人2＿対象区分;
    private String 連帯保証人2＿氏名;
    private String 連帯保証人2＿氏名2;
    private String 連帯保証人2＿氏名3;
    private BigDecimal 連帯保証人2＿保証期日;
    private BigDecimal 連帯保証人2＿限度額;
    private BigDecimal 連帯保証人2＿科目;
    private String 連帯保証人2＿住所;
    private BigDecimal 連帯保証人2＿生年月日;
    private String 連帯保証人2＿勤務先;
    private BigDecimal 連帯保証人2＿法人;
    private BigDecimal 連帯保証人3＿対象区分;
    private String 連帯保証人3＿氏名;
    private String 連帯保証人3＿氏名2;
    private String 連帯保証人3＿氏名3;
    private BigDecimal 連帯保証人3＿保証期日;
    private BigDecimal 連帯保証人3＿限度額;
    private BigDecimal 連帯保証人3＿科目;
    private String 連帯保証人3＿住所;
    private BigDecimal 連帯保証人3＿生年月日;
    private String 連帯保証人3＿勤務先;
    private BigDecimal 連帯保証人3＿法人;

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String v) { this.申込番号 = v; }
    public String get申込目的() { return 申込目的; }
    public void set申込目的(String v) { this.申込目的 = v; }
    public String getイベント() { return イベント; }
    public void setイベント(String v) { this.イベント = v; }
    public Date getイベント日時() { return イベント日時; }
    public void setイベント日時(Date v) { this.イベント日時 = v; }
    public BigDecimal getレコード区分() { return レコード区分; }
    public void setレコード区分(BigDecimal v) { this.レコード区分 = v; }
    public BigDecimal get作成基準日時() { return 作成基準日時; }
    public void set作成基準日時(BigDecimal v) { this.作成基準日時 = v; }
    public BigDecimal get取上店番() { return 取上店番; }
    public void set取上店番(BigDecimal v) { this.取上店番 = v; }
    public BigDecimal get勘定店番() { return 勘定店番; }
    public void set勘定店番(BigDecimal v) { this.勘定店番 = v; }
    public String getＣＩＦ番号() { return ＣＩＦ番号; }
    public void setＣＩＦ番号(String v) { this.ＣＩＦ番号 = v; }
    public String get債務者甲＿名() { return 債務者甲＿名; }
    public void set債務者甲＿名(String v) { this.債務者甲＿名 = v; }
    public String get債務者甲＿名カナ() { return 債務者甲＿名カナ; }
    public void set債務者甲＿名カナ(String v) { this.債務者甲＿名カナ = v; }
    public String get顧客住所() { return 顧客住所; }
    public void set顧客住所(String v) { this.顧客住所 = v; }
    public BigDecimal get法人区分() { return 法人区分; }
    public void set法人区分(BigDecimal v) { this.法人区分 = v; }
    public BigDecimal get業種コード() { return 業種コード; }
    public void set業種コード(BigDecimal v) { this.業種コード = v; }
    public String get職業() { return 職業; }
    public void set職業(String v) { this.職業 = v; }
    public BigDecimal getデータ区分() { return データ区分; }
    public void setデータ区分(BigDecimal v) { this.データ区分 = v; }
    public BigDecimal get処理区分コード() { return 処理区分コード; }
    public void set処理区分コード(BigDecimal v) { this.処理区分コード = v; }
    public BigDecimal get取扱番号() { return 取扱番号; }
    public void set取扱番号(BigDecimal v) { this.取扱番号 = v; }
    public BigDecimal get枝番() { return 枝番; }
    public void set枝番(BigDecimal v) { this.枝番 = v; }
    public BigDecimal get融資科目コード() { return 融資科目コード; }
    public void set融資科目コード(BigDecimal v) { this.融資科目コード = v; }
    public BigDecimal get融資分類コード() { return 融資分類コード; }
    public void set融資分類コード(BigDecimal v) { this.融資分類コード = v; }
    public BigDecimal get貸出種類コード() { return 貸出種類コード; }
    public void set貸出種類コード(BigDecimal v) { this.貸出種類コード = v; }
    public BigDecimal get貸出日() { return 貸出日; }
    public void set貸出日(BigDecimal v) { this.貸出日 = v; }
    public BigDecimal get貸出金額() { return 貸出金額; }
    public void set貸出金額(BigDecimal v) { this.貸出金額 = v; }
    public BigDecimal get貸出方法区分() { return 貸出方法区分; }
    public void set貸出方法区分(BigDecimal v) { this.貸出方法区分 = v; }
    public BigDecimal get当初借入利率() { return 当初借入利率; }
    public void set当初借入利率(BigDecimal v) { this.当初借入利率 = v; }
    public String get資金使途コード() { return 資金使途コード; }
    public void set資金使途コード(String v) { this.資金使途コード = v; }
    public String get資金使途備考() { return 資金使途備考; }
    public void set資金使途備考(String v) { this.資金使途備考 = v; }
    public BigDecimal get現在残高() { return 現在残高; }
    public void set現在残高(BigDecimal v) { this.現在残高 = v; }
    public BigDecimal get最終返済日() { return 最終返済日; }
    public void set最終返済日(BigDecimal v) { this.最終返済日 = v; }
    public BigDecimal get発行日() { return 発行日; }
    public void set発行日(BigDecimal v) { this.発行日 = v; }
    public BigDecimal get実行予定日() { return 実行予定日; }
    public void set実行予定日(BigDecimal v) { this.実行予定日 = v; }
    public BigDecimal get徴求日() { return 徴求日; }
    public void set徴求日(BigDecimal v) { this.徴求日 = v; }
    public BigDecimal get実行日() { return 実行日; }
    public void set実行日(BigDecimal v) { this.実行日 = v; }
    public BigDecimal get否決日() { return 否決日; }
    public void set否決日(BigDecimal v) { this.否決日 = v; }
    public BigDecimal get連携区分() { return 連携区分; }
    public void set連携区分(BigDecimal v) { this.連携区分 = v; }
    public BigDecimal get承認ステータス() { return 承認ステータス; }
    public void set承認ステータス(BigDecimal v) { this.承認ステータス = v; }
    public BigDecimal get極度額() { return 極度額; }
    public void set極度額(BigDecimal v) { this.極度額 = v; }
    public BigDecimal get抵当権設定日＿原契約日() { return 抵当権設定日＿原契約日; }
    public void set抵当権設定日＿原契約日(BigDecimal v) { this.抵当権設定日＿原契約日 = v; }
    public BigDecimal get根抵当権契約設定日() { return 根抵当権契約設定日; }
    public void set根抵当権契約設定日(BigDecimal v) { this.根抵当権契約設定日 = v; }
    public BigDecimal get債務承認開始日() { return 債務承認開始日; }
    public void set債務承認開始日(BigDecimal v) { this.債務承認開始日 = v; }
    public BigDecimal get債務承認終了日() { return 債務承認終了日; }
    public void set債務承認終了日(BigDecimal v) { this.債務承認終了日 = v; }
    public BigDecimal get保証委託契約日() { return 保証委託契約日; }
    public void set保証委託契約日(BigDecimal v) { this.保証委託契約日 = v; }
    public BigDecimal get印紙税額() { return 印紙税額; }
    public void set印紙税額(BigDecimal v) { this.印紙税額 = v; }
    public BigDecimal get稟議種別() { return 稟議種別; }
    public void set稟議種別(BigDecimal v) { this.稟議種別 = v; }
    public BigDecimal get保証番号() { return 保証番号; }
    public void set保証番号(BigDecimal v) { this.保証番号 = v; }
    public String get金利種類区分() { return 金利種類区分; }
    public void set金利種類区分(String v) { this.金利種類区分 = v; }
    public String get金利型名称備考() { return 金利型名称備考; }
    public void set金利型名称備考(String v) { this.金利型名称備考 = v; }
    public String get返済方法() { return 返済方法; }
    public void set返済方法(String v) { this.返済方法 = v; }
    public String get弁済方法名称備考() { return 弁済方法名称備考; }
    public void set弁済方法名称備考(String v) { this.弁済方法名称備考 = v; }
    public String get基準金利区分() { return 基準金利区分; }
    public void set基準金利区分(String v) { this.基準金利区分 = v; }
    public BigDecimal get標準金利() { return 標準金利; }
    public void set標準金利(BigDecimal v) { this.標準金利 = v; }
    public BigDecimal get固定金利再選択時の利幅＿符号() { return 固定金利再選択時の利幅＿符号; }
    public void set固定金利再選択時の利幅＿符号(BigDecimal v) { this.固定金利再選択時の利幅＿符号 = v; }
    public BigDecimal get固定金利再選択時の利幅＿数値() { return 固定金利再選択時の利幅＿数値; }
    public void set固定金利再選択時の利幅＿数値(BigDecimal v) { this.固定金利再選択時の利幅＿数値 = v; }
    public BigDecimal get貸付期間＿年() { return 貸付期間＿年; }
    public void set貸付期間＿年(BigDecimal v) { this.貸付期間＿年 = v; }
    public BigDecimal get貸付期間＿月() { return 貸付期間＿月; }
    public void set貸付期間＿月(BigDecimal v) { this.貸付期間＿月 = v; }
    public BigDecimal get債務者＿甲＿団信() { return 債務者＿甲＿団信; }
    public void set債務者＿甲＿団信(BigDecimal v) { this.債務者＿甲＿団信 = v; }
    public BigDecimal get利息支払日() { return 利息支払日; }
    public void set利息支払日(BigDecimal v) { this.利息支払日 = v; }
    public BigDecimal get利息払込済日() { return 利息払込済日; }
    public void set利息払込済日(BigDecimal v) { this.利息払込済日 = v; }
    public BigDecimal get利息約定日() { return 利息約定日; }
    public void set利息約定日(BigDecimal v) { this.利息約定日 = v; }
    public String get利率方式() { return 利率方式; }
    public void set利率方式(String v) { this.利率方式 = v; }
    public BigDecimal getその他期日() { return その他期日; }
    public void setその他期日(BigDecimal v) { this.その他期日 = v; }
    public String get休日補正区分() { return 休日補正区分; }
    public void set休日補正区分(String v) { this.休日補正区分 = v; }
    public BigDecimal get返済用預金口座店番() { return 返済用預金口座店番; }
    public void set返済用預金口座店番(BigDecimal v) { this.返済用預金口座店番 = v; }
    public String get返済用預金口座種目() { return 返済用預金口座種目; }
    public void set返済用預金口座種目(String v) { this.返済用預金口座種目 = v; }
    public BigDecimal get返済用預金口座番号() { return 返済用預金口座番号; }
    public void set返済用預金口座番号(BigDecimal v) { this.返済用預金口座番号 = v; }
    public String get返済用口座名義() { return 返済用口座名義; }
    public void set返済用口座名義(String v) { this.返済用口座名義 = v; }
    public String get据置ＣＤ() { return 据置ＣＤ; }
    public void set据置ＣＤ(String v) { this.据置ＣＤ = v; }
    public String get据置備考() { return 据置備考; }
    public void set据置備考(String v) { this.据置備考 = v; }
    public BigDecimal get元金据置期限() { return 元金据置期限; }
    public void set元金据置期限(BigDecimal v) { this.元金据置期限 = v; }
    public BigDecimal get毎月返済＿内訳＿貸出金額() { return 毎月返済＿内訳＿貸出金額; }
    public void set毎月返済＿内訳＿貸出金額(BigDecimal v) { this.毎月返済＿内訳＿貸出金額 = v; }
    public BigDecimal get毎月返済＿初回返済日() { return 毎月返済＿初回返済日; }
    public void set毎月返済＿初回返済日(BigDecimal v) { this.毎月返済＿初回返済日 = v; }
    public BigDecimal get毎月返済＿返済日() { return 毎月返済＿返済日; }
    public void set毎月返済＿返済日(BigDecimal v) { this.毎月返済＿返済日 = v; }
    public BigDecimal get毎回返済＿返済間隔() { return 毎回返済＿返済間隔; }
    public void set毎回返済＿返済間隔(BigDecimal v) { this.毎回返済＿返済間隔 = v; }
    public BigDecimal get毎回返済＿返済回数() { return 毎回返済＿返済回数; }
    public void set毎回返済＿返済回数(BigDecimal v) { this.毎回返済＿返済回数 = v; }
    public BigDecimal get毎月返済＿初回返済金() { return 毎月返済＿初回返済金; }
    public void set毎月返済＿初回返済金(BigDecimal v) { this.毎月返済＿初回返済金 = v; }
    public BigDecimal get毎月返済＿返済金() { return 毎月返済＿返済金; }
    public void set毎月返済＿返済金(BigDecimal v) { this.毎月返済＿返済金 = v; }
    public BigDecimal get毎月返済＿最終返済金() { return 毎月返済＿最終返済金; }
    public void set毎月返済＿最終返済金(BigDecimal v) { this.毎月返済＿最終返済金 = v; }
    public BigDecimal get半年毎返済＿内訳＿貸出金額() { return 半年毎返済＿内訳＿貸出金額; }
    public void set半年毎返済＿内訳＿貸出金額(BigDecimal v) { this.半年毎返済＿内訳＿貸出金額 = v; }
    public BigDecimal get半年毎返済＿初回返済日() { return 半年毎返済＿初回返済日; }
    public void set半年毎返済＿初回返済日(BigDecimal v) { this.半年毎返済＿初回返済日 = v; }
    public BigDecimal get半年毎返済＿返済月1() { return 半年毎返済＿返済月1; }
    public void set半年毎返済＿返済月1(BigDecimal v) { this.半年毎返済＿返済月1 = v; }
    public BigDecimal get半年毎返済＿返済月2() { return 半年毎返済＿返済月2; }
    public void set半年毎返済＿返済月2(BigDecimal v) { this.半年毎返済＿返済月2 = v; }
    public BigDecimal get半年毎返済＿返済日() { return 半年毎返済＿返済日; }
    public void set半年毎返済＿返済日(BigDecimal v) { this.半年毎返済＿返済日 = v; }
    public BigDecimal get半年毎増額＿返済回数() { return 半年毎増額＿返済回数; }
    public void set半年毎増額＿返済回数(BigDecimal v) { this.半年毎増額＿返済回数 = v; }
    public BigDecimal get半年毎＿初回返済金() { return 半年毎＿初回返済金; }
    public void set半年毎＿初回返済金(BigDecimal v) { this.半年毎＿初回返済金 = v; }
    public BigDecimal get半年毎＿返済金() { return 半年毎＿返済金; }
    public void set半年毎＿返済金(BigDecimal v) { this.半年毎＿返済金 = v; }
    public BigDecimal get半年毎増額＿返済金額2() { return 半年毎増額＿返済金額2; }
    public void set半年毎増額＿返済金額2(BigDecimal v) { this.半年毎増額＿返済金額2 = v; }
    public BigDecimal get半年毎＿最終返済金() { return 半年毎＿最終返済金; }
    public void set半年毎＿最終返済金(BigDecimal v) { this.半年毎＿最終返済金 = v; }
    public BigDecimal get担保コード() { return 担保コード; }
    public void set担保コード(BigDecimal v) { this.担保コード = v; }
    public BigDecimal get担保明細コード() { return 担保明細コード; }
    public void set担保明細コード(BigDecimal v) { this.担保明細コード = v; }
    public BigDecimal get保証先() { return 保証先; }
    public void set保証先(BigDecimal v) { this.保証先 = v; }
    public BigDecimal get順位() { return 順位; }
    public void set順位(BigDecimal v) { this.順位 = v; }
    public String get手形サイト() { return 手形サイト; }
    public void set手形サイト(String v) { this.手形サイト = v; }
    public BigDecimal get全期間乖離幅() { return 全期間乖離幅; }
    public void set全期間乖離幅(BigDecimal v) { this.全期間乖離幅 = v; }
    public String get利率サイクル() { return 利率サイクル; }
    public void set利率サイクル(String v) { this.利率サイクル = v; }
    public BigDecimal get毎月返済＿2回目以降元金返済年月日() { return 毎月返済＿2回目以降元金返済年月日; }
    public void set毎月返済＿2回目以降元金返済年月日(BigDecimal v) { this.毎月返済＿2回目以降元金返済年月日 = v; }
    public String get債務者＿甲＿電話番号() { return 債務者＿甲＿電話番号; }
    public void set債務者＿甲＿電話番号(String v) { this.債務者＿甲＿電話番号 = v; }
    public BigDecimal get初回利払日() { return 初回利払日; }
    public void set初回利払日(BigDecimal v) { this.初回利払日 = v; }
    public BigDecimal get当初固定金利期間() { return 当初固定金利期間; }
    public void set当初固定金利期間(BigDecimal v) { this.当初固定金利期間 = v; }
    public BigDecimal get返済回数() { return 返済回数; }
    public void set返済回数(BigDecimal v) { this.返済回数 = v; }
    public BigDecimal get据置回数() { return 据置回数; }
    public void set据置回数(BigDecimal v) { this.据置回数 = v; }
    public BigDecimal get連帯債務者勘定店番() { return 連帯債務者勘定店番; }
    public void set連帯債務者勘定店番(BigDecimal v) { this.連帯債務者勘定店番 = v; }
    public String get連帯債務者ＣＩＦ() { return 連帯債務者ＣＩＦ; }
    public void set連帯債務者ＣＩＦ(String v) { this.連帯債務者ＣＩＦ = v; }
    public String get連帯債務者＿乙＿名() { return 連帯債務者＿乙＿名; }
    public void set連帯債務者＿乙＿名(String v) { this.連帯債務者＿乙＿名 = v; }
    public String get連帯債務者＿乙＿住所() { return 連帯債務者＿乙＿住所; }
    public void set連帯債務者＿乙＿住所(String v) { this.連帯債務者＿乙＿住所 = v; }
    public BigDecimal get債務者甲負担割合＿分母() { return 債務者甲負担割合＿分母; }
    public void set債務者甲負担割合＿分母(BigDecimal v) { this.債務者甲負担割合＿分母 = v; }
    public BigDecimal get債務者甲負担割合＿分子() { return 債務者甲負担割合＿分子; }
    public void set債務者甲負担割合＿分子(BigDecimal v) { this.債務者甲負担割合＿分子 = v; }
    public BigDecimal get連帯債務者乙＿分母() { return 連帯債務者乙＿分母; }
    public void set連帯債務者乙＿分母(BigDecimal v) { this.連帯債務者乙＿分母 = v; }
    public BigDecimal get連帯債務者乙＿分子() { return 連帯債務者乙＿分子; }
    public void set連帯債務者乙＿分子(BigDecimal v) { this.連帯債務者乙＿分子 = v; }
    public BigDecimal get連帯債務者＿乙＿団信() { return 連帯債務者＿乙＿団信; }
    public void set連帯債務者＿乙＿団信(BigDecimal v) { this.連帯債務者＿乙＿団信 = v; }
    public BigDecimal get連帯保証人1＿対象区分() { return 連帯保証人1＿対象区分; }
    public void set連帯保証人1＿対象区分(BigDecimal v) { this.連帯保証人1＿対象区分 = v; }
    public String get連帯保証人1＿氏名() { return 連帯保証人1＿氏名; }
    public void set連帯保証人1＿氏名(String v) { this.連帯保証人1＿氏名 = v; }
    public String get連帯保証人1＿氏名2() { return 連帯保証人1＿氏名2; }
    public void set連帯保証人1＿氏名2(String v) { this.連帯保証人1＿氏名2 = v; }
    public String get連帯保証人1＿氏名3() { return 連帯保証人1＿氏名3; }
    public void set連帯保証人1＿氏名3(String v) { this.連帯保証人1＿氏名3 = v; }
    public BigDecimal get連帯保証人1＿保証期日() { return 連帯保証人1＿保証期日; }
    public void set連帯保証人1＿保証期日(BigDecimal v) { this.連帯保証人1＿保証期日 = v; }
    public BigDecimal get連帯保証人1＿限度額() { return 連帯保証人1＿限度額; }
    public void set連帯保証人1＿限度額(BigDecimal v) { this.連帯保証人1＿限度額 = v; }
    public BigDecimal get連帯保証人1＿科目() { return 連帯保証人1＿科目; }
    public void set連帯保証人1＿科目(BigDecimal v) { this.連帯保証人1＿科目 = v; }
    public String get連帯保証人1＿住所() { return 連帯保証人1＿住所; }
    public void set連帯保証人1＿住所(String v) { this.連帯保証人1＿住所 = v; }
    public BigDecimal get連帯保証人1＿生年月日() { return 連帯保証人1＿生年月日; }
    public void set連帯保証人1＿生年月日(BigDecimal v) { this.連帯保証人1＿生年月日 = v; }
    public String get連帯保証人1＿勤務先() { return 連帯保証人1＿勤務先; }
    public void set連帯保証人1＿勤務先(String v) { this.連帯保証人1＿勤務先 = v; }
    public BigDecimal get連帯保証人1＿法人() { return 連帯保証人1＿法人; }
    public void set連帯保証人1＿法人(BigDecimal v) { this.連帯保証人1＿法人 = v; }
    public BigDecimal get連帯保証人2＿対象区分() { return 連帯保証人2＿対象区分; }
    public void set連帯保証人2＿対象区分(BigDecimal v) { this.連帯保証人2＿対象区分 = v; }
    public String get連帯保証人2＿氏名() { return 連帯保証人2＿氏名; }
    public void set連帯保証人2＿氏名(String v) { this.連帯保証人2＿氏名 = v; }
    public String get連帯保証人2＿氏名2() { return 連帯保証人2＿氏名2; }
    public void set連帯保証人2＿氏名2(String v) { this.連帯保証人2＿氏名2 = v; }
    public String get連帯保証人2＿氏名3() { return 連帯保証人2＿氏名3; }
    public void set連帯保証人2＿氏名3(String v) { this.連帯保証人2＿氏名3 = v; }
    public BigDecimal get連帯保証人2＿保証期日() { return 連帯保証人2＿保証期日; }
    public void set連帯保証人2＿保証期日(BigDecimal v) { this.連帯保証人2＿保証期日 = v; }
    public BigDecimal get連帯保証人2＿限度額() { return 連帯保証人2＿限度額; }
    public void set連帯保証人2＿限度額(BigDecimal v) { this.連帯保証人2＿限度額 = v; }
    public BigDecimal get連帯保証人2＿科目() { return 連帯保証人2＿科目; }
    public void set連帯保証人2＿科目(BigDecimal v) { this.連帯保証人2＿科目 = v; }
    public String get連帯保証人2＿住所() { return 連帯保証人2＿住所; }
    public void set連帯保証人2＿住所(String v) { this.連帯保証人2＿住所 = v; }
    public BigDecimal get連帯保証人2＿生年月日() { return 連帯保証人2＿生年月日; }
    public void set連帯保証人2＿生年月日(BigDecimal v) { this.連帯保証人2＿生年月日 = v; }
    public String get連帯保証人2＿勤務先() { return 連帯保証人2＿勤務先; }
    public void set連帯保証人2＿勤務先(String v) { this.連帯保証人2＿勤務先 = v; }
    public BigDecimal get連帯保証人2＿法人() { return 連帯保証人2＿法人; }
    public void set連帯保証人2＿法人(BigDecimal v) { this.連帯保証人2＿法人 = v; }
    public BigDecimal get連帯保証人3＿対象区分() { return 連帯保証人3＿対象区分; }
    public void set連帯保証人3＿対象区分(BigDecimal v) { this.連帯保証人3＿対象区分 = v; }
    public String get連帯保証人3＿氏名() { return 連帯保証人3＿氏名; }
    public void set連帯保証人3＿氏名(String v) { this.連帯保証人3＿氏名 = v; }
    public String get連帯保証人3＿氏名2() { return 連帯保証人3＿氏名2; }
    public void set連帯保証人3＿氏名2(String v) { this.連帯保証人3＿氏名2 = v; }
    public String get連帯保証人3＿氏名3() { return 連帯保証人3＿氏名3; }
    public void set連帯保証人3＿氏名3(String v) { this.連帯保証人3＿氏名3 = v; }
    public BigDecimal get連帯保証人3＿保証期日() { return 連帯保証人3＿保証期日; }
    public void set連帯保証人3＿保証期日(BigDecimal v) { this.連帯保証人3＿保証期日 = v; }
    public BigDecimal get連帯保証人3＿限度額() { return 連帯保証人3＿限度額; }
    public void set連帯保証人3＿限度額(BigDecimal v) { this.連帯保証人3＿限度額 = v; }
    public BigDecimal get連帯保証人3＿科目() { return 連帯保証人3＿科目; }
    public void set連帯保証人3＿科目(BigDecimal v) { this.連帯保証人3＿科目 = v; }
    public String get連帯保証人3＿住所() { return 連帯保証人3＿住所; }
    public void set連帯保証人3＿住所(String v) { this.連帯保証人3＿住所 = v; }
    public BigDecimal get連帯保証人3＿生年月日() { return 連帯保証人3＿生年月日; }
    public void set連帯保証人3＿生年月日(BigDecimal v) { this.連帯保証人3＿生年月日 = v; }
    public String get連帯保証人3＿勤務先() { return 連帯保証人3＿勤務先; }
    public void set連帯保証人3＿勤務先(String v) { this.連帯保証人3＿勤務先 = v; }
    public BigDecimal get連帯保証人3＿法人() { return 連帯保証人3＿法人; }
    public void set連帯保証人3＿法人(BigDecimal v) { this.連帯保証人3＿法人 = v; }
}
