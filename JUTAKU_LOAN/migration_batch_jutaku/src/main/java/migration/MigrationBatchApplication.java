package migration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class MigrationBatchApplication {

    public static void main(String[] args) {
        // Set process ID as System property before logback initializes
        for (String arg : args) {
            if (arg.startsWith("--migration.process.id=")) {
                System.setProperty("MIGRATION_PROCESS_ID", arg.split("=", 2)[1]);
                break;
            }
        }
        log.info("JUTAKU_LOAN Migration Batch Started");
        SpringApplication.run(MigrationBatchApplication.class, args);
        log.info("JUTAKU_LOAN Migration Batch Completed");
    }

}
