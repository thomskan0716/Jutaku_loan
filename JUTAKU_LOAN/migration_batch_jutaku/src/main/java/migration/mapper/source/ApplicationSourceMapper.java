package migration.mapper.source;

import migration.domain.source.ApplicationSource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApplicationSourceMapper {
    
    List<ApplicationSource> selectByRange(@Param("startId") String startId, @Param("endId") String endId);
    
    long count();
    
    
    String findMinId();
    

    String findMaxId();
}
