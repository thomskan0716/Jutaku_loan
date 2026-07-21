package migration.mapper.source;

import migration.domain.source.審査ＳＮＡＶＩ連携内容Source;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface 審査ＳＮＡＶＩ連携内容SourceMapper {

    List<審査ＳＮＡＶＩ連携内容Source> selectByApplicationIdAndPurpose(
            @Param("申込番号") String 申込番号,
            @Param("申込目的") String 申込目的);
}
