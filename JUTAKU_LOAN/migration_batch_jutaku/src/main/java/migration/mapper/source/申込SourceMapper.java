package migration.mapper.source;

import migration.domain.source.申込Source;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface 申込SourceMapper {

    List<申込Source> selectByRowRange(@Param("fromNo") long fromNo, @Param("toNo") long toNo);

    long count();
}
