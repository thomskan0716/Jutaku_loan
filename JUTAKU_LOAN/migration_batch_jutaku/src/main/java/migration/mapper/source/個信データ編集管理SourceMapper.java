package migration.mapper.source;

import migration.domain.source.個信データ編集管理Source;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface 個信データ編集管理SourceMapper {

    List<個信データ編集管理Source> selectByApplicationIdAndPurpose(
            @Param("申込番号") String 申込番号,
            @Param("申込目的") String 申込目的);
}
