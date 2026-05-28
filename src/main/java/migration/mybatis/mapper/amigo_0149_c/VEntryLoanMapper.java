package migration.mybatis.mapper.amigo_0149_c;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import migration.mybatis.domain.amigo_0149_c.VEntryLoan;

@Transactional(transactionManager = "amigoCManager")
public interface VEntryLoanMapper {

    List<VEntryLoan> selectByExample(@Param("start") int start, @Param("end") int end);

}
