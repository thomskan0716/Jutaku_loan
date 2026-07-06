package migration.mapper.source;

import migration.domain.source.個信類似照会管理Source;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface 個信類似照会管理SourceMapper {

    List<個信類似照会管理Source> selectByApplicationIdAndPurpose(
            @Param("申込番号") String 申込番号,
            @Param("申込目的") String 申込目的);
}
