package migration.domain.source;

import java.util.Date;

/**
 * Source: 保証結果融資条件じぶん (Guarantee result financing conditions - au Jibun external linkage) - SZB_SMS.
 * 外部連携. 1:N per (申込番号, 申込目的), keyed by イベント/イベント日時/連番.
 *
 * <p>All columns are VARCHAR2 (String) except イベント日時 / 出力日時 (DATE) and 連番 (NUMBER(9,0)).</p>
 */
public class 保証結果融資条件じぶんSource {

    private String 申込番号;
    private String 申込目的;
    private String イベント;
    private Date イベント日時;
    private Integer 連番;
    private String 状態;
    private Date 出力日時;
    private String 外部案件番号;
    private String 融資条件通番;
    private String 融資条件コード;
    private String 融資条件内容;

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
    public String get状態() { return 状態; }
    public void set状態(String v) { this.状態 = v; }
    public Date get出力日時() { return 出力日時; }
    public void set出力日時(Date v) { this.出力日時 = v; }
    public String get外部案件番号() { return 外部案件番号; }
    public void set外部案件番号(String v) { this.外部案件番号 = v; }
    public String get融資条件通番() { return 融資条件通番; }
    public void set融資条件通番(String v) { this.融資条件通番 = v; }
    public String get融資条件コード() { return 融資条件コード; }
    public void set融資条件コード(String v) { this.融資条件コード = v; }
    public String get融資条件内容() { return 融資条件内容; }
    public void set融資条件内容(String v) { this.融資条件内容 = v; }
}
