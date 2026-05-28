package migration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("migration.mapper")
public class MigrationBatchApplication {

    public static void main(String[] args) {
        System.out.println("JUTAKU_LOAN Migration Batch Started");
        SpringApplication.run(MigrationBatchApplication.class, args);
        System.out.println("JUTAKU_LOAN Migration Batch Completed");
    }

}
