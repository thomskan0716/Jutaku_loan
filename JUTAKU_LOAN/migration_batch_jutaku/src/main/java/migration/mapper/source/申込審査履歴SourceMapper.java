package migration.mapper.source;

import migration.domain.source.申込審査履歴Source;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface 申込審査履歴SourceMapper {

    List<申込審査履歴Source> selectByApplicationIdAndPurpose(
            @Param("申込番号") String 申込番号,
            @Param("申込目的") String 申込目的);
}
