package migration.mapper.source;

import migration.domain.source.申込進捗Source;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface 申込進捗SourceMapper {

    List<申込進捗Source> selectByRowRange(@Param("fromNo") long fromNo, @Param("toNo") long toNo);

    long count();
}
