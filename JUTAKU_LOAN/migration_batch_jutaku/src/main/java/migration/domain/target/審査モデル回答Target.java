package migration.domain.target;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Target: 審査モデル回答 (Scoring model answer) - ITF_SMS.
 * 申込番号 first digit 2→3; 申込目的 converted by service. Other columns pass through.
 */
public class 審査モデル回答Target {

    private String 申込番号;
    private String 申込目的;
    private String イベント;
    private Date イベント日時;
    private String セグメント;
    private BigDecimal スコア;

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String v) { this.申込番号 = v; }

    public String get申込目的() { return 申込目的; }
    public void set申込目的(String v) { this.申込目的 = v; }

    public String getイベント() { return イベント; }
    public void setイベント(String v) { this.イベント = v; }

    public Date getイベント日時() { return イベント日時; }
    public void setイベント日時(Date v) { this.イベント日時 = v; }

    public String getセグメント() { return セグメント; }
    public void setセグメント(String v) { this.セグメント = v; }

    public BigDecimal getスコア() { return スコア; }
    public void setスコア(BigDecimal v) { this.スコア = v; }
}
