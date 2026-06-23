package migration.mapper.source;

import migration.domain.source.申込Source;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface 申込SourceMapper {

    List<申込Source> selectByRowRange(@Param("fromNo") long fromNo, @Param("toNo") long toNo);

    申込Source selectByApplicationIdAndPurpose(
            @Param("申込番号") String 申込番号,
            @Param("申込目的") String 申込目的);

    long count();
}
