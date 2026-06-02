package migration.mapper.source;

import migration.domain.source.申込Source;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface 申込SourceMapper {
    
    List<申込Source> selectByRange(@Param("startId") String startId, @Param("endId") String endId);
    
    long count();
    
    
    String findMinId();
    

    String findMaxId();
}
