package migration.mapper.source;

import migration.domain.source.保証検討表補足Source;
import org.apache.ibatis.annotations.Param;

public interface 保証検討表補足SourceMapper {

    保証検討表補足Source selectByApplicationIdAndPurpose(
            @Param("申込番号") String 申込番号,
            @Param("申込目的") String 申込目的);
}
