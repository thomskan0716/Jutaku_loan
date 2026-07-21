package migration.mapper.source;

import migration.domain.source.保証結果メインアルヒSource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface 保証結果メインアルヒSourceMapper {

    List<保証結果メインアルヒSource> selectByApplicationIdAndPurpose(
            @Param("申込番号") String 申込番号,
            @Param("申込目的") String 申込目的);
}
