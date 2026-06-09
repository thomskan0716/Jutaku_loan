package migration.batch;

import migration.domain.移行管理.移行管理;
import migration.service.移行管理Service;
import migration.service.申込MigrationService;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 移行管理 Tasklet
 * Uses FOR UPDATE SKIP LOCKED for parallel processing
 *
 * Configuration:
 *   migration.management.batch-size: Records to claim per iteration (default: 100)
 *   migration.management.max-iterations: Max iterations, -1 for unlimited (default: -1)
 */
@Component("managementBasedTasklet")
@StepScope
public class 移行管理Tasklet implements Tasklet {

    @Autowired
    private 移行管理Service managementService;

    @Autowired
    private 申込MigrationService migrationService;

    @Value("${migration.management.batch-size:100}")
    private int batchSize;

    @Value("${migration.management.max-iterations:-1}")
    private int maxIterations;

    private int totalProcessed = 0;

    private final String processId;

    public 移行管理Tasklet() {
        // Generate unique process ID: hostname + timestamp (with Shift-JIS support)
        String hostname = System.getenv().getOrDefault("COMPUTERNAME", "UNKNOWN");
        this.processId = hostname + "-" + System.currentTimeMillis();
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        System.out.println("\n=== Migration Tasklet Started (Management) ===");
        System.out.println("Process ID: " + processId);
        System.out.println("Configuration: batch-size=" + batchSize + ", max-iterations=" + maxIterations + "\n");

        try {
            managementService.printStatusSummary();

            int iteration = 0;
            while (maxIterations == -1 || iteration < maxIterations) {
                iteration++;
                List<移行管理> claimedRecords = managementService.claimRecords(batchSize, processId);
                
                if (claimedRecords.isEmpty()) {
                    System.out.println("\nNo more records to process");
                    break;
                }
                
                System.out.println("\n--- Processing batch of " + claimedRecords.size() + " records ---");
                
                for (移行管理 record : claimedRecords) {
                    processOneRecord(record);
                }
                
                totalProcessed += claimedRecords.size();
                System.out.println("--- Batch complete. Total processed: " + totalProcessed + " ---\n");
                
                if (totalProcessed % 500 == 0) {
                    managementService.printStatusSummary();
                }
            }
            
            System.out.println("\n=== Migration Tasklet Completed Successfully ===");
            managementService.printStatusSummary();
            
        } catch (Exception e) {
            System.err.println("\n=== FATAL ERROR in Migration Tasklet ===");
            e.printStackTrace();
            managementService.printStatusSummary();
            throw new RuntimeException("Migration failed", e);
        }
        
        return RepeatStatus.FINISHED;
    }
    
    /**
     * Process a single record
     * No locks held during this phase
     */
    private void processOneRecord(移行管理 record) {
        String 申込番号 = record.get申込番号();
        
        try {
            System.out.println("  Processing: " + 申込番号);
            
            // process is intentionally empty — lock mechanism test only
            // migrationService.migrateOneApplication(申込番号);
            
            managementService.markDone(申込番号);
            
        } catch (Exception e) {
            System.err.println("  ERROR processing " + 申込番号 + ": " + e.getMessage());
            managementService.markError(申込番号, e.getMessage());
        }
    }
}
