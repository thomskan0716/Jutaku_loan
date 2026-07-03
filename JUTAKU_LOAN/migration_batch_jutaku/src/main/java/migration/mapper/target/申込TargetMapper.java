package migration.mapper.target;

import migration.domain.target.申込Target;


public interface 申込TargetMapper {
    

    int insert(申込Target target);

    //For testing
    int deleteAll();
}
