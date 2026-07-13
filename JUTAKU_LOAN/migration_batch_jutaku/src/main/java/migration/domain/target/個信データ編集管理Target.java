package migration.domain.target;

import java.util.Date;

/**
 * Target: 個信データ編集管理 (Personal-credit data edit management) - ITF_SMS
 * 申込番号 first digit 2→3; 申込目的 converted by service. Other columns pass through.
 */
public class 個信データ編集管理Target {

    private String 申込番号;     // VARCHAR2(12)
    private String 申込目的;     // VARCHAR2(2)
    private String イベント;     // VARCHAR2(50)
    private Date イベント日時;   // DATE
    private String 状態;         // VARCHAR2(10)
    private String 状態説明;     // VARCHAR2(100)
    private Integer 優先度;      // NUMBER(1,0)

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

    public Integer get優先度() { return 優先度; }
    public void set優先度(Integer v) { this.優先度 = v; }
}
