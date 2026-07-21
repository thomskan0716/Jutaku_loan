package migration.domain.source;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Source: 審査契約書出力連携 (Contract document output linkage event) - SZB_SMS.
 * DB table name is 審査契約書出力連携 (event rows); target is 契約書連携イベント.
 * 1:N per (申込番号, 申込目的), keyed by イベント/イベント日時.
 */
public class 契約書連携イベントSource {

    private String 申込番号;      // VARCHAR2(12)
    private String 申込目的;      // VARCHAR2(2)
    private String イベント;      // VARCHAR2(50)
    private Date イベント日時;    // DATE
    private String 状態;          // VARCHAR2(10)
    private String 状態説明;      // VARCHAR2(100)
    private BigDecimal 優先度;    // NUMBER(1,0)

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
}
