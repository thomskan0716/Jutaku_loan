package migration.mapper.source;

import migration.domain.source.システム判定照会Source;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface システム判定照会SourceMapper {

    List<システム判定照会Source> selectByApplicationIdAndPurpose(
            @Param("申込番号") String 申込番号,
            @Param("申込目的") String 申込目的);
}
