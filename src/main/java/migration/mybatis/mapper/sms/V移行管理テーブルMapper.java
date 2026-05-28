package migration.mybatis.mapper.sms;

import migration.mybatis.domain.sms.移行管理テーブル;

public interface V移行管理テーブルMapper {

    移行管理テーブル selectNextRangeForUpdate(); // 1件 or null

    int updateStatus(移行管理テーブル range); // TODO/RUNNING/DONE/ERROR

    int updateStatusStart(移行管理テーブル range); // TODO/RUNNING/DONE/ERROR

    int updateStatusEnd(移行管理テーブル range); // TODO/RUNNING/DONE/ERROR

}
