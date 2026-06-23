package migration.mapper.source;

import migration.domain.source.保証人Source;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface 保証人SourceMapper {

    List<保証人Source> selectByApplicationIdAndPurpose(
            @Param("申込番号") String 申込番号,
            @Param("申込目的") String 申込目的);
}
