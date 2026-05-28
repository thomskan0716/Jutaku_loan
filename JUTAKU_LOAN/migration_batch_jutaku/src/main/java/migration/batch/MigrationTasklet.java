package migration.batch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import migration.domain.source.ApplicationSource;
import migration.service.ApplicationMigrationService;

@Component
@StepScope
public class MigrationTasklet implements Tasklet {

    @Autowired
    private ApplicationMigrationService applicationMigrationService;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        System.out.println("MigrationTasklet Started");
        
        try {
            applicationMigrationService.processAll();
            
            while (true) {
                try {
                    ApplicationSource range = applicationMigrationService.claimNextRange();
                    if (range == null) {
                        System.out.println("No more ranges to process");
                        break;
                    }
                    
                    try {
                        String startId = range.get申込番号();
                        // Calculate endId (add 100 for batch size)
                        String endId = String.format("%012d", 
                            Long.parseLong(startId) + 100);
                        
                        int processedCount = applicationMigrationService.processOneRange(startId, endId);
                        
                        // Step 4: Mark as done
                        applicationMigrationService.markDone(range);
                        
                        System.out.println("Range processed: " + startId + " ~ " + endId + 
                            " (" + processedCount + " records)");
                        
                    } catch (Exception e) {
                        System.err.println("ERROR processing range: " + range.get申込番号());
                        e.printStackTrace();
                        applicationMigrationService.markError(range, e.getMessage());
                    }
                    
                } catch (Exception e) {
                    System.err.println("ERROR claiming next range: " + e.getMessage());
                    e.printStackTrace();
                    break;
                }
            }
            
            System.out.println("MigrationTasklet Completed Successfully");
            
        } catch (Exception e) {
            System.err.println("FATAL ERROR in MigrationTasklet");
            e.printStackTrace();
            throw new RuntimeException("Migration failed", e);
        }
        
        return RepeatStatus.FINISHED;
    }
}
