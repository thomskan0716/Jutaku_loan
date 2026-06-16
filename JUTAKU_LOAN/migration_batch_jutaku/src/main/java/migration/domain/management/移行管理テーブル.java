package migration.domain.management;

import java.sql.Timestamp;

public class 移行管理テーブル {

    private String システム;
    private long 処理FROM;
    private long 処理TO;
    private String ステータス;
    private Timestamp 開始日時;
    private Timestamp 終了日時;
    private String 備考;

    public static final String STATUS_TODO    = "TODO";
    public static final String STATUS_RUNNING = "RUNNING";
    public static final String STATUS_DONE    = "DONE";
    public static final String STATUS_ERROR   = "ERROR";

    public static final String SYSTEM_JUTAKU  = "J";

    public String getシステム() { return システム; }
    public void setシステム(String システム) { this.システム = システム; }

    public long get処理FROM() { return 処理FROM; }
    public void set処理FROM(long 処理FROM) { this.処理FROM = 処理FROM; }

    public long get処理TO() { return 処理TO; }
    public void set処理TO(long 処理TO) { this.処理TO = 処理TO; }

    public String getステータス() { return ステータス; }
    public void setステータス(String ステータス) { this.ステータス = ステータス; }

    public Timestamp get開始日時() { return 開始日時; }
    public void set開始日時(Timestamp 開始日時) { this.開始日時 = 開始日時; }

    public Timestamp get終了日時() { return 終了日時; }
    public void set終了日時(Timestamp 終了日時) { this.終了日時 = 終了日時; }

    public String get備考() { return 備考; }
    public void set備考(String 備考) { this.備考 = 備考; }

    @Override
    public String toString() {
        return "移行管理テーブル{システム='" + システム + "', FROM=" + 処理FROM + ", TO=" + 処理TO + ", ステータス='" + ステータス + "'}";
    }
}
