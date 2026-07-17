package migration.mapper.source;

import migration.domain.source.契約書連携イベントSource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface 契約書連携イベントSourceMapper {

    List<契約書連携イベントSource> selectByApplicationIdAndPurpose(
            @Param("申込番号") String 申込番号,
            @Param("申込目的") String 申込目的);
}
