package migration.mapper.source;

import migration.domain.source.担保評価連携結果ファイルSource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface 担保評価連携結果ファイルSourceMapper {

    List<担保評価連携結果ファイルSource> selectByApplicationIdAndPurpose(
            @Param("申込番号") String 申込番号,
            @Param("申込目的") String 申込目的);
}
