package migration.mapper.target;

import migration.domain.target.ApplicationTarget;


public interface ApplicationTargetMapper {
    

    int insert(ApplicationTarget target);
    
    //For testing
    int deleteAll();
}
