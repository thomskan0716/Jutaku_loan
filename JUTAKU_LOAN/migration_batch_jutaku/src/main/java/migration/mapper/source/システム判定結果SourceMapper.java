package migration.mapper.source;

import migration.domain.source.システム判定結果Source;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface システム判定結果SourceMapper {

    List<システム判定結果Source> selectByApplicationIdAndPurpose(
            @Param("申込番号") String 申込番号,
            @Param("申込目的") String 申込目的);
}
