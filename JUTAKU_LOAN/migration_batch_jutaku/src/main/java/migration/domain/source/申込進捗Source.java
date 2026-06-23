package migration.domain.source;

/**
 * Source: 申込進捗 (Application Progress) - SZB_SMS
 * Driving table: one row per 申込番号, used to generate ROW_NUMBER ranges.
 */
public class 申込進捗Source {

    private String 申込番号;    // VARCHAR2(12) PK
    private String 進捗コード;  // VARCHAR2(4)
    private String 状態;        // VARCHAR2(30)

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String 申込番号) { this.申込番号 = 申込番号; }

    public String get進捗コード() { return 進捗コード; }
    public void set進捗コード(String 進捗コード) { this.進捗コード = 進捗コード; }

    public String get状態() { return 状態; }
    public void set状態(String 状態) { this.状態 = 状態; }
}
