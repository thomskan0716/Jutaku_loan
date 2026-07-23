package migration.domain.target;

import java.util.Date;

/**
 * Target: 担当者別操作管理 (Operation management by person in charge) - ITF_SMS.
 * ログ (log). 1:N per (申込番号, 申込目的). 申込番号 2->3, 申込目的 and 進捗コード converted by the service.
 */
public class 担当者別操作管理Target {

    private String 申込番号;
    private String 申込目的;
    private String ユーザＩＤ;
    private String 進捗コード;
    private Date 開始日時;
    private Date 終了日時;

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String v) { this.申込番号 = v; }
    public String get申込目的() { return 申込目的; }
    public void set申込目的(String v) { this.申込目的 = v; }
    public String getユーザＩＤ() { return ユーザＩＤ; }
    public void setユーザＩＤ(String v) { this.ユーザＩＤ = v; }
    public String get進捗コード() { return 進捗コード; }
    public void set進捗コード(String v) { this.進捗コード = v; }
    public Date get開始日時() { return 開始日時; }
    public void set開始日時(Date v) { this.開始日時 = v; }
    public Date get終了日時() { return 終了日時; }
    public void set終了日時(Date v) { this.終了日時 = v; }
}
