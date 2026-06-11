package migration.mapper.移行管理テーブル;

import migration.domain.移行管理テーブル.移行管理テーブル;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;

@Mapper
public interface 移行管理テーブルMapper {

    移行管理テーブル claimNextRange(@Param("システム") String システム);

    int updateStatusToRunning(@Param("システム") String システム,
                               @Param("処理FROM") long 処理FROM,
                               @Param("開始日時") Timestamp 開始日時);

    int updateStatusToDone(@Param("システム") String システム,
                            @Param("処理FROM") long 処理FROM,
                            @Param("終了日時") Timestamp 終了日時);

    int updateStatusToError(@Param("システム") String システム,
                             @Param("処理FROM") long 処理FROM,
                             @Param("終了日時") Timestamp 終了日時,
                             @Param("備考") String 備考);
}
