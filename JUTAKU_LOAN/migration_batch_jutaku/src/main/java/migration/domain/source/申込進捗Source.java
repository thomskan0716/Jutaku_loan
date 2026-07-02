package migration.domain.source;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Source: 申込進捗 (Application Progress) - SZB_SMS
 * Driving table: one row per 申込番号, used to generate ROW_NUMBER ranges.
 */
public class 申込進捗Source {

    private String 申込番号;    // VARCHAR2(12) PK
    private String 進捗コード;  // VARCHAR2(4)
    private String 状態;        // VARCHAR2(30)
    private Date 進捗移動日時;   // DATE
    private String 表示形式;     // VARCHAR2(10)
    private BigDecimal 優先度;   // NUMBER(1,0)
    private String コメント;     // VARCHAR2(100)
    private String 前進捗コード; // VARCHAR2(8)
    private String 進捗移動担当者コード; // VARCHAR2(10)

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String 申込番号) { this.申込番号 = 申込番号; }

    public String get進捗コード() { return 進捗コード; }
    public void set進捗コード(String 進捗コード) { this.進捗コード = 進捗コード; }

    public String get状態() { return 状態; }
    public void set状態(String 状態) { this.状態 = 状態; }

    public Date get進捗移動日時() { return 進捗移動日時; }
    public void set進捗移動日時(Date v) { this.進捗移動日時 = v; }

    public String get表示形式() { return 表示形式; }
    public void set表示形式(String v) { this.表示形式 = v; }

    public BigDecimal get優先度() { return 優先度; }
    public void set優先度(BigDecimal v) { this.優先度 = v; }

    public String getコメント() { return コメント; }
    public void setコメント(String v) { this.コメント = v; }

    public String get前進捗コード() { return 前進捗コード; }
    public void set前進捗コード(String v) { this.前進捗コード = v; }

    public String get進捗移動担当者コード() { return 進捗移動担当者コード; }
    public void set進捗移動担当者コード(String v) { this.進捗移動担当者コード = v; }
}
