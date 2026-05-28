package migration.mybatis.mapper.amigo_0149_c;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import migration.mybatis.domain.amigo_0149_c.VEntrySub;

@Transactional(transactionManager = "amigoCManager")
public interface VEntrySubMapper {

    List<VEntrySub> selectByExample(@Param("start") int start, @Param("end") int end);

}
