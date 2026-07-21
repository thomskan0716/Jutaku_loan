package migration.mapper.source;

import migration.domain.source.審査ＳＮＡＶＩ連携イベントSource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface 審査ＳＮＡＶＩ連携イベントSourceMapper {

    List<審査ＳＮＡＶＩ連携イベントSource> selectByApplicationIdAndPurpose(
            @Param("申込番号") String 申込番号,
            @Param("申込目的") String 申込目的);
}
