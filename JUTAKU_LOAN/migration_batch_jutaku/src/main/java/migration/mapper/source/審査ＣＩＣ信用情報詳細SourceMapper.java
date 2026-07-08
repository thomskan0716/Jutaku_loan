package migration.mapper.source;

import migration.domain.source.審査ＣＩＣ信用情報詳細Source;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface 審査ＣＩＣ信用情報詳細SourceMapper {

    List<審査ＣＩＣ信用情報詳細Source> selectByApplicationIdAndPurpose(
            @Param("申込番号") String 申込番号,
            @Param("申込目的") String 申込目的);
}
