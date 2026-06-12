package migration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class MigrationBatchApplication {

    public static void main(String[] args) {
        log.info("JUTAKU_LOAN Migration Batch Started");
        SpringApplication.run(MigrationBatchApplication.class, args);
        log.info("JUTAKU_LOAN Migration Batch Completed");
    }

}
