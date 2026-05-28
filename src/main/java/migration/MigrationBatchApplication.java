package migration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@MapperScan("migration.mybatis.mapper")
public class MigrationBatchApplication {

    public static void main(String[] args) {
        log.info("MigrationBatchApplication run start");
        SpringApplication.run(MigrationBatchApplication.class, args);
        log.info("MigrationBatchApplication run end");
    }

}
