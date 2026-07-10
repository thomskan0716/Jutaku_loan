package migration.mapper.source;

import migration.domain.source.審査モデル回答明細ＳSource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface 審査モデル回答明細ＳSourceMapper {

    List<審査モデル回答明細ＳSource> selectByApplicationIdAndPurpose(
            @Param("申込番号") String 申込番号,
            @Param("申込目的") String 申込目的);
}
