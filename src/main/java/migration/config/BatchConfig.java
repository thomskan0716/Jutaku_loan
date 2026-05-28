/**
 *
 */
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

import migration.batch.MigrationTasklet;

/**
 * @author hi-takahashi
 */
@EnableBatchProcessing
@Configuration
public class BatchConfig {

    @Autowired
    private final JobBuilderFactory jobBuilderFactory;

    @Autowired
    private final StepBuilderFactory stepBuilderFactory;
    //
    //  @Autowired
    //  private final migrationTasklet migrationTasklet;
    //
    //  @Autowired
    //  private final DeleteTasklet deleteTasklet;
    //

    @Autowired
    private final migration.batch.MigrationTasklet migrationTasklet;

    public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
            MigrationTasklet migrationTasklet) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.migrationTasklet = migrationTasklet;
    }

    @Bean
    public Step migrationStep() {
        return stepBuilderFactory.get("migrationStep") //
                .tasklet(migrationTasklet) //
                .build();
    }

    @Bean
    public Job migrationJob(Step migrationStep) {
        return jobBuilderFactory.get("migrationStep") //
                .incrementer(new RunIdIncrementer()) //
                .start(migrationStep) //
                .build(); //
    }

    //  @Bean
    //  public Step migrationStep() {
    //      return stepBuilderFactory.get("migrationStep") //
    //              .tasklet(migrationTasklet) //
    //              .build();
    //  }
    //
    //  @Bean
    //  public Job migrationJob(Step migrationStep) {
    //      return jobBuilderFactory.get("migrationStep") //
    //              .incrementer(new RunIdIncrementer()) //
    //              .start(migrationStep) //
    //              .build(); //
    //  }
    //
    //  @Bean
    //  public Step deleteStep() {
    //      return stepBuilderFactory.get("deleteStep") //
    //              .tasklet(deleteTasklet) //
    //              .build();
    //  }
    //
    //  @Bean
    //  public Job deleteJob(Step deleteStep) {
    //      return jobBuilderFactory.get("deleteStep") //
    //              .incrementer(new RunIdIncrementer()) //
    //              .start(deleteStep) //
    //              .build(); //
    //  }
}
