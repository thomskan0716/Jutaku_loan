package migration.mybatis.mapper.amigo_0149_c;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import migration.mybatis.domain.amigo_0149_c.VEntryEtc;

@Transactional(transactionManager = "amigoCManager")
public interface VEntryEtcMapper {

    List<VEntryEtc> selectByExample(@Param("start") int start, @Param("end") int end);

}
