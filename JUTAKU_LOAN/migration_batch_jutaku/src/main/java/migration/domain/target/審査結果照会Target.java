package migration.domain.target;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Target: 審査結果照会 (Review result inquiry) - ITF_SMS.
 * 申込番号 first digit 2→3; 申込目的 converted by service. Other columns pass through.
 */
public class 審査結果照会Target {

    private String 申込番号;
    private String 申込目的;
    private String イベント;
    private Date イベント日時;
    private String 状態;
    private String 状態説明;
    private BigDecimal 優先度;
    private String 自動判定基準;

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

    public String get自動判定基準() { return 自動判定基準; }
    public void set自動判定基準(String v) { this.自動判定基準 = v; }
}
