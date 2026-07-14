package migration.mapper.source;

import migration.domain.source.審査コメントSource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface 審査コメントSourceMapper {

    List<審査コメントSource> selectByApplicationIdAndPurpose(
            @Param("申込番号") String 申込番号,
            @Param("申込目的") String 申込目的);
}
