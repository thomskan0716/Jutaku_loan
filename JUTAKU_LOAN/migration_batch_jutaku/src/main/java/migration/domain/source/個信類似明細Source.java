package migration.domain.source;

import java.util.Date;

/**
 * Source: 個信類似明細 (Personal-credit similar detail) - SZB_SMS
 * 1:N per (申込番号, 申込目的). DB uses full-width digits (電話番号１, 公的資料１区分, ...);
 * Java fields keep half-width digits.
 */
public class 個信類似明細Source {

    private String 申込番号;         // VARCHAR2(12)
    private String 申込目的;         // VARCHAR2(2)
    private String イベント;         // VARCHAR2(50)
    private Date イベント日時;       // DATE
    private Integer 連番;            // NUMBER(1,0)
    private String 情報区分;         // VARCHAR2(2)
    private String 受付番号;         // CHAR(20)
    private String 受付日時;         // CHAR(14)
    private String 会員名;           // CHAR(4)
    private Integer 回答通番;        // NUMBER(3,0)
    private String 電文種別;         // CHAR(2)
    private Integer 親回答通番;      // NUMBER(3,0)
    private Integer 結付通番;        // NUMBER(3,0)
    private String 照会回答区分;     // VARCHAR2(2)
    private String 照会データ種別;   // VARCHAR2(2)
    private String 債権分類;         // VARCHAR2(2)
    private String 状態;             // VARCHAR2(20)
    private String 一致タイプ;       // VARCHAR2(2)
    private String 氏名カナ;         // VARCHAR2(30)
    private String 氏名漢字;         // VARCHAR2(32)
    private String 性別;             // VARCHAR2(2)
    private String 生年月日;         // VARCHAR2(8)
    private String 電話番号1;        // VARCHAR2(16)  DB: 電話番号１
    private String 電話番号2;        // VARCHAR2(16)  DB: 電話番号２
    private String 郵便番号;         // VARCHAR2(7)
    private String 住所カナ;         // VARCHAR2(60)
    private String 住所漢字;         // VARCHAR2(80)
    private String 勤務先名;         // VARCHAR2(40)
    private String 勤務先電話番号;   // VARCHAR2(16)
    private String 公的資料1区分;    // VARCHAR2(1)   DB: 公的資料１区分
    private String 公的資料1番号;    // VARCHAR2(12)  DB: 公的資料１番号
    private String 公的資料2区分;    // VARCHAR2(1)   DB: 公的資料２区分
    private String 公的資料2番号;    // VARCHAR2(40)  DB: 公的資料２番号
    private String 情報識別区分;     // VARCHAR2(2)
    private String 本人識別コード;   // VARCHAR2(3)
    private String 情報特定コード;   // VARCHAR2(12)
    private String 顧客コード;       // VARCHAR2(10)

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String v) { this.申込番号 = v; }

    public String get申込目的() { return 申込目的; }
    public void set申込目的(String v) { this.申込目的 = v; }

    public String getイベント() { return イベント; }
    public void setイベント(String v) { this.イベント = v; }

    public Date getイベント日時() { return イベント日時; }
    public void setイベント日時(Date v) { this.イベント日時 = v; }

    public Integer get連番() { return 連番; }
    public void set連番(Integer v) { this.連番 = v; }

    public String get情報区分() { return 情報区分; }
    public void set情報区分(String v) { this.情報区分 = v; }

    public String get受付番号() { return 受付番号; }
    public void set受付番号(String v) { this.受付番号 = v; }

    public String get受付日時() { return 受付日時; }
    public void set受付日時(String v) { this.受付日時 = v; }

    public String get会員名() { return 会員名; }
    public void set会員名(String v) { this.会員名 = v; }

    public Integer get回答通番() { return 回答通番; }
    public void set回答通番(Integer v) { this.回答通番 = v; }

    public String get電文種別() { return 電文種別; }
    public void set電文種別(String v) { this.電文種別 = v; }

    public Integer get親回答通番() { return 親回答通番; }
    public void set親回答通番(Integer v) { this.親回答通番 = v; }

    public Integer get結付通番() { return 結付通番; }
    public void set結付通番(Integer v) { this.結付通番 = v; }

    public String get照会回答区分() { return 照会回答区分; }
    public void set照会回答区分(String v) { this.照会回答区分 = v; }

    public String get照会データ種別() { return 照会データ種別; }
    public void set照会データ種別(String v) { this.照会データ種別 = v; }

    public String get債権分類() { return 債権分類; }
    public void set債権分類(String v) { this.債権分類 = v; }

    public String get状態() { return 状態; }
    public void set状態(String v) { this.状態 = v; }

    public String get一致タイプ() { return 一致タイプ; }
    public void set一致タイプ(String v) { this.一致タイプ = v; }

    public String get氏名カナ() { return 氏名カナ; }
    public void set氏名カナ(String v) { this.氏名カナ = v; }

    public String get氏名漢字() { return 氏名漢字; }
    public void set氏名漢字(String v) { this.氏名漢字 = v; }

    public String get性別() { return 性別; }
    public void set性別(String v) { this.性別 = v; }

    public String get生年月日() { return 生年月日; }
    public void set生年月日(String v) { this.生年月日 = v; }

    public String get電話番号1() { return 電話番号1; }
    public void set電話番号1(String v) { this.電話番号1 = v; }

    public String get電話番号2() { return 電話番号2; }
    public void set電話番号2(String v) { this.電話番号2 = v; }

    public String get郵便番号() { return 郵便番号; }
    public void set郵便番号(String v) { this.郵便番号 = v; }

    public String get住所カナ() { return 住所カナ; }
    public void set住所カナ(String v) { this.住所カナ = v; }

    public String get住所漢字() { return 住所漢字; }
    public void set住所漢字(String v) { this.住所漢字 = v; }

    public String get勤務先名() { return 勤務先名; }
    public void set勤務先名(String v) { this.勤務先名 = v; }

    public String get勤務先電話番号() { return 勤務先電話番号; }
    public void set勤務先電話番号(String v) { this.勤務先電話番号 = v; }

    public String get公的資料1区分() { return 公的資料1区分; }
    public void set公的資料1区分(String v) { this.公的資料1区分 = v; }

    public String get公的資料1番号() { return 公的資料1番号; }
    public void set公的資料1番号(String v) { this.公的資料1番号 = v; }

    public String get公的資料2区分() { return 公的資料2区分; }
    public void set公的資料2区分(String v) { this.公的資料2区分 = v; }

    public String get公的資料2番号() { return 公的資料2番号; }
    public void set公的資料2番号(String v) { this.公的資料2番号 = v; }

    public String get情報識別区分() { return 情報識別区分; }
    public void set情報識別区分(String v) { this.情報識別区分 = v; }

    public String get本人識別コード() { return 本人識別コード; }
    public void set本人識別コード(String v) { this.本人識別コード = v; }

    public String get情報特定コード() { return 情報特定コード; }
    public void set情報特定コード(String v) { this.情報特定コード = v; }

    public String get顧客コード() { return 顧客コード; }
    public void set顧客コード(String v) { this.顧客コード = v; }
}
