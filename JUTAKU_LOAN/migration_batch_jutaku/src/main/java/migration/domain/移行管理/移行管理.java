package migration.domain.移行管理;

import java.sql.Timestamp;

/**
 * 移行管理テーブル Domain Tracks individual application records for parallel processing
 */
public class 移行管理 {
    
    /** 申込番号 (Primary Key) */
    private String 申込番号;
    
    /** 申込目的 */
    private Integer 申込目的;
    
    /** ステータス: TODO, RUNNING, DONE, ERROR */
    private String ステータス;
    
    /** 処理開始日時 */
    private Timestamp 処理開始日時;
    
    /** 処理終了日時 */
    private Timestamp 処理終了日時;
    
    /** 処理プロセスID - Identifies which parallel process handled this record */
    private String 処理プロセスID;

    // Getters and Setters
    public String get申込番号() {
        return 申込番号;
    }

    public void set申込番号(String 申込番号) {
        this.申込番号 = 申込番号;
    }

    public Integer get申込目的() {
        return 申込目的;
    }

    public void set申込目的(Integer 申込目的) {
        this.申込目的 = 申込目的;
    }

    public String getステータス() {
        return ステータス;
    }

    public void setステータス(String ステータス) {
        this.ステータス = ステータス;
    }

    public Timestamp get処理開始日時() {
        return 処理開始日時;
    }

    public void set処理開始日時(Timestamp 処理開始日時) {
        this.処理開始日時 = 処理開始日時;
    }

    public Timestamp get処理終了日時() {
        return 処理終了日時;
    }

    public void set処理終了日時(Timestamp 処理終了日時) {
        this.処理終了日時 = 処理終了日時;
    }

    public String get処理プロセスID() {
        return 処理プロセスID;
    }

    public void set処理プロセスID(String 処理プロセスID) {
        this.処理プロセスID = 処理プロセスID;
    }

    // Status constants
    public static final String STATUS_TODO = "TODO";
    public static final String STATUS_RUNNING = "RUNNING";
    public static final String STATUS_DONE = "DONE";
    public static final String STATUS_ERROR = "ERROR";

    @Override
    public String toString() {
        return "移行管理{" +
                "申込番号='" + 申込番号 + '\'' +
                ", 申込目的=" + 申込目的 +
                ", ステータス='" + ステータス + '\'' +
                ", 処理開始日時=" + 処理開始日時 +
                ", 処理終了日時=" + 処理終了日時 +
                ", 処理プロセスID='" + 処理プロセスID + '\'' +
                '}';
    }
}
