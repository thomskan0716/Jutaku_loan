package migration.domain.source;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Source: 審査モデル回答Ｓ (Scoring model answer, S-segment) - SZB_SMS.
 * Same shape as 審査モデル回答.
 */
public class 審査モデル回答ＳSource {

    private String 申込番号;      // VARCHAR2(12)
    private String 申込目的;      // VARCHAR2(2)
    private String イベント;      // VARCHAR2(50)
    private Date イベント日時;    // DATE
    private String セグメント;    // VARCHAR2(2)
    private BigDecimal スコア;    // NUMBER(13,10)

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
