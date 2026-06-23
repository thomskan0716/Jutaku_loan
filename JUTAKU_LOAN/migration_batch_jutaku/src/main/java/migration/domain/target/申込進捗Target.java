package migration.domain.target;

/**
 * Target: 申込進捗 (Application Progress) - ITF_SMS
 * 1:1 copy from source. 申込番号 first digit converted: 2→3.
 */
public class 申込進捗Target {

    private String 申込番号;    // VARCHAR2(12) PK NOT NULL
    private String 進捗コード;  // VARCHAR2(6)  NOT NULL
    private String 状態;        // VARCHAR2(30) NOT NULL

    public String get申込番号() { return 申込番号; }
    public void set申込番号(String 申込番号) { this.申込番号 = 申込番号; }

    public String get進捗コード() { return 進捗コード; }
    public void set進捗コード(String 進捗コード) { this.進捗コード = 進捗コード; }

    public String get状態() { return 状態; }
    public void set状態(String 状態) { this.状態 = 状態; }
}
