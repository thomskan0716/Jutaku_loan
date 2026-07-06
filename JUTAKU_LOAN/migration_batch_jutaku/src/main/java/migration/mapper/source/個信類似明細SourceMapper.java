package migration.mapper.source;

import migration.domain.source.個信類似明細Source;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface 個信類似明細SourceMapper {

    List<個信類似明細Source> selectByApplicationIdAndPurpose(
            @Param("申込番号") String 申込番号,
            @Param("申込目的") String 申込目的);
}
