package migration.mapper.source;

import migration.domain.source.担当者別操作管理Source;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface 担当者別操作管理SourceMapper {

    List<担当者別操作管理Source> selectByApplicationIdAndPurpose(
            @Param("申込番号") String 申込番号,
            @Param("申込目的") String 申込目的);
}
