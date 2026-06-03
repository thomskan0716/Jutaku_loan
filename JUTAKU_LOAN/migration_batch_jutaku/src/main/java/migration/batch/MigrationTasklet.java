package migration.batch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import migration.domain.source.申込Source;
import migration.service.申込MigrationService;

@Component
@StepScope
public class MigrationTasklet implements Tasklet {

    @Autowired
    private 申込MigrationService applicationMigrationService;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        System.out.println("MigrationTasklet Started");
        
        try {
            applicationMigrationService.processAll();
            
            while (true) {
                try {
                    申込Source range = applicationMigrationService.claimNextRange();
                    if (range == null) {
                        System.out.println("No more ranges to process");
                        break;
                    }
                    
                    try {
                        String startId = range.get申込番号();
                        // Calculate endId (add 10 for batch size TEST)
                        String endId = String.format("%012d", 
                            Long.parseLong(startId) + 10);
                        
                        String lastProcessedId = applicationMigrationService.processOneRange(startId, endId);
                        
                        
                        applicationMigrationService.markDone(range, lastProcessedId);
                        
                        System.out.println("Range processed: " + startId + " ~ " + lastProcessedId);
                        
                    } catch (Exception e) {
                        System.err.println("ERROR processing range: " + range.get申込番号());
                        e.printStackTrace();
                        applicationMigrationService.markError(range, e.getMessage());
                        // STOP on error to prevent infinite loop
                        throw new RuntimeException("Migration stopped due to error in range: " + range.get申込番号(), e);
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
