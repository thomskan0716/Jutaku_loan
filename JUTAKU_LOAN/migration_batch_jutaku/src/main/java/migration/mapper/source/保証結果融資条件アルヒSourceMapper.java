package migration.mapper.source;

import migration.domain.source.保証結果融資条件アルヒSource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface 保証結果融資条件アルヒSourceMapper {

    List<保証結果融資条件アルヒSource> selectByApplicationIdAndPurpose(
            @Param("申込番号") String 申込番号,
            @Param("申込目的") String 申込目的);
}
