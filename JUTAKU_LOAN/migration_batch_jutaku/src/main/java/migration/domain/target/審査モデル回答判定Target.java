package migration.domain.target;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Target: 審査モデル回答判定 (Scoring model answer judgement) - ITF_SMS.
 * 申込番号 first digit 2→3; 申込目的 converted by service. Other columns pass through.
 */
public class 審査モデル回答判定Target {

    private String 申込番号;
    private String 申込目的;
    private String イベント;
    private Date イベント日時;
    private BigDecimal 融資倍率;
    private String 規定外項目;
    private BigDecimal 担保掛目;
    private BigDecimal 上限融資金額;
    private String 判定;

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String v) { this.申込番号 = v; }

    public String get申込目的() { return 申込目的; }
    public void set申込目的(String v) { this.申込目的 = v; }

    public String getイベント() { return イベント; }
    public void setイベント(String v) { this.イベント = v; }

    public Date getイベント日時() { return イベント日時; }
    public void setイベント日時(Date v) { this.イベント日時 = v; }

    public BigDecimal get融資倍率() { return 融資倍率; }
    public void set融資倍率(BigDecimal v) { this.融資倍率 = v; }

    public String get規定外項目() { return 規定外項目; }
    public void set規定外項目(String v) { this.規定外項目 = v; }

    public BigDecimal get担保掛目() { return 担保掛目; }
    public void set担保掛目(BigDecimal v) { this.担保掛目 = v; }

    public BigDecimal get上限融資金額() { return 上限融資金額; }
    public void set上限融資金額(BigDecimal v) { this.上限融資金額 = v; }

    public String get判定() { return 判定; }
    public void set判定(String v) { this.判定 = v; }
}
