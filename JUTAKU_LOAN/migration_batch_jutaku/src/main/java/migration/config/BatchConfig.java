package migration.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import migration.batch.移行管理Tasklet;


@EnableBatchProcessing
@Configuration
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final 移行管理Tasklet migrationTasklet;

    @Autowired
    public BatchConfig(
            JobBuilderFactory jobBuilderFactory,
            StepBuilderFactory stepBuilderFactory,
            移行管理Tasklet migrationTasklet) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.migrationTasklet = migrationTasklet;
    }

    
    @Bean
    public Step migrationStep() {
        return stepBuilderFactory.get("migrationStep")
                .tasklet(migrationTasklet)
                .build();
    }

    
    @Bean
    public Job migrationJob() {
        return jobBuilderFactory.get("migrationJob")
                .incrementer(new RunIdIncrementer())
                .start(migrationStep())
                .build();
    }
}
