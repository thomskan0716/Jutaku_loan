package migration.mapper.source;

import migration.domain.source.申込関連申込Source;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface 申込関連申込SourceMapper {

    List<申込関連申込Source> selectByApplicationId(@Param("申込番号") String 申込番号);
}

