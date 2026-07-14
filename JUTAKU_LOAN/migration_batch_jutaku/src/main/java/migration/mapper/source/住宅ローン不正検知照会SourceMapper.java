package migration.mapper.source;

import migration.domain.source.住宅ローン不正検知照会Source;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface 住宅ローン不正検知照会SourceMapper {

    List<住宅ローン不正検知照会Source> selectByApplicationIdAndPurpose(
            @Param("申込番号") String 申込番号,
            @Param("申込目的") String 申込目的);
}
