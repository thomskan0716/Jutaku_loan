package migration.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class MigrationJobRunner implements ApplicationRunner {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job migrationJob;

    @Value("${test.process.id:0}")
    private long processId;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addLong("processId", processId)
                .addLong("launchTime", System.currentTimeMillis())
                .toJobParameters();

        System.out.println("=== [Process " + processId + "] Launching migrationJob ===");
        jobLauncher.run(migrationJob, params);
        System.out.println("=== [Process " + processId + "] migrationJob finished ===");
    }
}
