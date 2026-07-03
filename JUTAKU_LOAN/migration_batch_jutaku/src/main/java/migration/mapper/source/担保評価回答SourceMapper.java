package migration.mapper.source;

import migration.domain.source.担保評価回答Source;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface 担保評価回答SourceMapper {

    List<担保評価回答Source> selectByApplicationIdAndPurpose(
            @Param("申込番号") String 申込番号,
            @Param("申込目的") String 申込目的);
}
