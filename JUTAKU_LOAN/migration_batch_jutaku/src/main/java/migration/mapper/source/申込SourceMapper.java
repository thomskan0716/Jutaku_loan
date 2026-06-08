package migration.mapper.source;

import migration.domain.source.申込Source;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface 申込SourceMapper {
    
    /**
     * Select single record by 申込番号 (for 移行管理 parallel processing)
     */
    申込Source selectById(@Param("申込番号") String 申込番号);
    
    List<申込Source> selectByRange(@Param("startId") String startId, @Param("endId") String endId);
    
    long count();
    
    
    String findMinId();
    

    String findMaxId();
}
