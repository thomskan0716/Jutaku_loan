package migration.mapper.source;

import migration.domain.source.返済比率計算Source;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface 返済比率計算SourceMapper {

    List<返済比率計算Source> selectByApplicationIdAndPurpose(
            @Param("申込番号") String 申込番号,
            @Param("申込目的") String 申込目的);
}
