package migration.mapper.source;

import migration.domain.source.申込担保回答ＰＤＦSource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface 申込担保回答ＰＤＦSourceMapper {

    List<申込担保回答ＰＤＦSource> selectByApplicationIdAndPurpose(
            @Param("申込番号") String 申込番号,
            @Param("申込目的") String 申込目的);
}
