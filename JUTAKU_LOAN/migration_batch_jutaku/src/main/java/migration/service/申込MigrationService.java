package migration.service;

import migration.common.szh_sms.E申込目的;
import migration.domain.source.申込Source;
import migration.domain.target.申込Target;
import migration.mapper.source.申込SourceMapper;
import migration.mapper.target.申込TargetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class 申込MigrationService {
    
    @Autowired
    private 申込SourceMapper sourceMapper;

    @Autowired
    private 申込TargetMapper targetMapper;

    @Autowired
    private 申込審査状況MigrationService 申込審査状況MigrationService;

    private static final int BATCH_SIZE = 10; 
    private static final int TEST_LIMIT = 100;
    private String currentMinId = null;
    private String currentMaxId = null;
    private int totalProcessed = 0;
    
    public void processAll() {
        // Clear target tables before migration (for testing)
        申込審査状況MigrationService.resetTestState();
        申込審査状況MigrationService.deleteAll();
        targetMapper.deleteAll();
        System.out.println("申込 Migration - Target tables cleared");

        long totalCount = sourceMapper.count();
        currentMinId = sourceMapper.findMinId();
        currentMaxId = sourceMapper.findMaxId();
        totalProcessed = 0;

        System.out.println("申込 Migration - Process All Started");
        System.out.println("Total Records: " + totalCount);
        System.out.println("ID Range: " + currentMinId + " ~ " + currentMaxId);
        System.out.println("TEST LIMIT: " + TEST_LIMIT + " records");

        // TODO: Load master data into HashMap cache
    }
    
    /**
     * @return Next range start record, or null if no more ranges
     */
    @Transactional
    public 申込Source claimNextRange() {
        // Check TEST_LIMIT
        if (totalProcessed >= TEST_LIMIT) {
            System.out.println("TEST LIMIT reached: " + totalProcessed + " records processed. Stopping.");
            return null;
        }

        // Check if we've exceeded the max ID
        if (currentMinId != null && currentMaxId != null) {
            if (currentMinId.compareTo(currentMaxId) > 0) {
                System.out.println("Reached maxId limit: " + currentMaxId);
                return null;  // No more ranges in test dataset
            }
        }
        
        List<申込Source> candidates = sourceMapper.selectByRange(
            currentMinId != null ? currentMinId : "000000000001",
            currentMaxId != null ? currentMaxId : "999999999999" 
        );
        
        if (candidates.isEmpty()) {
            System.out.println("No more records found in range " + currentMinId + " ~ " + currentMaxId);
            return null;
        }
        
        return candidates.get(0);
    }
    
    public void markDone(申込Source range, String actualMaxId) {
        // Advance currentMinId to ACTUAL next ID (one more than last processed)
        long nextId = Long.parseLong(actualMaxId) + 1;
        currentMinId = String.format("%012d", nextId);
        
        System.out.println("DONE: Range starting from " + range.get申込番号() + 
            " (last processed: " + actualMaxId + ") → Next start: " + currentMinId);
    }
    
    public void markError(申込Source range, String errorMessage) {
        System.err.println("ERROR: Range starting from " + range.get申込番号() + " - " + errorMessage);
    }
    
    /**
       Process One Range (Main Logic)
     * @param startId Range start ID
     * @param endId Range end ID
     * @return Last processed ID (for markDone)
     */
    @Transactional
    public String processOneRange(String startId, String endId) {
        System.out.println("Processing range: " + startId + " ~ " + endId);
        
        List<申込Source> sourceList = sourceMapper.selectByRange(startId, endId);
        
        int processedCount = 0;
        int skippedCount = 0;
        String lastProcessedId = startId;  // Track actual last processed ID
        
        for (申込Source source : sourceList) {
            try {
                if (!isMigrationTarget(source)) {
                    System.out.println("SKIP: " + source.get申込番号() + " - Not migration target");
                    skippedCount++;
                    lastProcessedId = source.get申込番号();  // Update even for skipped
                    continue;
                }
                
                申込Target target = transform(source);
                targetMapper.insert(target);

                int reviewCount = 申込審査状況MigrationService.migrateByApplicationId(source.get申込番号(), source.get申込目的());
                System.out.println("  申込審査状況: " + reviewCount + " records for " + source.get申込番号());

                processedCount++;
                lastProcessedId = source.get申込番号();
                
            } catch (Exception e) {
                System.err.println("ERROR processing 申込番号=" + source.get申込番号() + ": " + e.getMessage());
                throw e;
            }
        }
        
        totalProcessed += processedCount + skippedCount;
        System.out.println("Range completed: Processed=" + processedCount + ", Skipped=" + skippedCount + ", Total so far=" + totalProcessed);
        return lastProcessedId;  // Return for markDone
    }
    
    
    /**
     * Migrate a single application by 申込番号 (used by 移行管理 parallel flow)
     * @return 1 if migrated, 0 if skipped
     */
    @Transactional
    public int migrateOneApplication(String 申込番号) {
        申込Source source = sourceMapper.selectById(申込番号);

        if (source == null) {
            System.out.println("SKIP: " + 申込番号 + " - Not found in source");
            return 0;
        }

        if (!isMigrationTarget(source)) {
            System.out.println("SKIP: " + 申込番号 + " - Not migration target");
            return 0;
        }

        申込Target target = transform(source);
        targetMapper.insert(target);

        int reviewCount = 申込審査状況MigrationService.migrateByApplicationId(
                source.get申込番号(), source.get申込目的());
        System.out.println("  申込審査状況: " + reviewCount + " records for " + 申込番号);

        return 1;
    }

    private boolean isMigrationTarget(申込Source source) {
        
        String 申込目的 = source.get申込目的();
        if (!E申込目的.shouldMigrate(申込目的)) {
            return false;
        }
        
        if (source.get申込番号() == null || source.get申込番号().isEmpty()) {
            return false;
        }
        
        return true;
    }
    private 申込Target transform(申込Source source) {
        申込Target target = new 申込Target();
        
        // 申込番号 (PK) - No conversion
        target.set申込番号(source.get申込番号());
        
        // 申込目的 - E-level conversion (10/15→10, 20/30→20, 90→null)
        String old申込目的 = source.get申込目的();
        String new申込目的 = E申込目的.convert(old申込目的);
        target.set申込目的(new申込目的);
        
        return target;
    }
}
