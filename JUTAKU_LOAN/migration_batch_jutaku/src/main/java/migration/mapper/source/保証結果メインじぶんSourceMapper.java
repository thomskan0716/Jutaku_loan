package migration.mapper.source;

import migration.domain.source.保証結果メインじぶんSource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface 保証結果メインじぶんSourceMapper {

    List<保証結果メインじぶんSource> selectByApplicationIdAndPurpose(
            @Param("申込番号") String 申込番号,
            @Param("申込目的") String 申込目的);
}
