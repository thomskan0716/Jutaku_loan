package migration.mybatis.mapper.sms;

import org.springframework.transaction.annotation.Transactional;

@Transactional(transactionManager = "smsIxManager")
public interface Vイメージ番号採番Mapper {

    int getImageNextSequence();

    long getFaxNextSequence();

}
