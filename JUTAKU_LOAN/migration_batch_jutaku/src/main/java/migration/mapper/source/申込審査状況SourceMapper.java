package migration.mapper.source;

import migration.domain.source.申込審査状況Source;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface 申込審査状況SourceMapper {

    List<申込審査状況Source> selectByApplicationId(@Param("申込番号") String 申込番号);

    long count();

    String findMinId();

    String findMaxId();
}
