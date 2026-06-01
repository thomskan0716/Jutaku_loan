package migration.service;

import migration.common.szh_sms.E申込目的;
import migration.common.szh_sms.E連絡コード;
import migration.domain.source.ApplicationSource;
import migration.domain.target.ApplicationTarget;
import migration.mapper.source.ApplicationSourceMapper;
import migration.mapper.target.ApplicationTargetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApplicationMigrationService {
    
    @Autowired
    private ApplicationSourceMapper sourceMapper;
    
    @Autowired
    private ApplicationTargetMapper targetMapper;
    
    private static final int BATCH_SIZE = 10; // Records per range (TEST: 10 for testing)
    private String currentMinId = null;
    private String currentMaxId = null;
    
    public void processAll() {
        // Clear target table before migration (for testing)
        targetMapper.deleteAll();
        System.out.println("申込 Migration - Target table cleared");
        
        long totalCount = sourceMapper.count();
        currentMinId = sourceMapper.findMinId();
        currentMaxId = sourceMapper.findMaxId();
        
        System.out.println("申込 Migration - Process All Started");
        System.out.println("Total Records: " + totalCount);
        System.out.println("ID Range: " + currentMinId + " ~ " + currentMaxId);
        
        // TODO: Load master data into HashMap cache
        // Example: Load 保証会社 master, etc.
    }
    
    /**
     * @return Next range start record, or null if no more ranges
     */
    @Transactional
    public ApplicationSource claimNextRange() {
        // Check if we've exceeded the max ID (from first 100 records)
        if (currentMinId != null && currentMaxId != null) {
            if (currentMinId.compareTo(currentMaxId) > 0) {
                System.out.println("Reached maxId limit: " + currentMaxId);
                return null;  // No more ranges in test dataset
            }
        }
        
        // Query for next BATCH_SIZE records starting from currentMinId, limited by currentMaxId
        List<ApplicationSource> candidates = sourceMapper.selectByRange(
            currentMinId != null ? currentMinId : "000000000001",
            currentMaxId != null ? currentMaxId : "999999999999"  // Don't exceed test limit
        );
        
        if (candidates.isEmpty()) {
            System.out.println("No more records found in range " + currentMinId + " ~ " + currentMaxId);
            return null;
        }
        
        // Return first record as range start marker
        return candidates.get(0);
    }
    
    public void markDone(ApplicationSource range, String actualMaxId) {
        // Advance currentMinId to ACTUAL next ID (one more than last processed)
        long nextId = Long.parseLong(actualMaxId) + 1;
        currentMinId = String.format("%012d", nextId);
        
        System.out.println("DONE: Range starting from " + range.get申込番号() + 
            " (last processed: " + actualMaxId + ") → Next start: " + currentMinId);
    }
    
    public void markError(ApplicationSource range, String errorMessage) {
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
        
        List<ApplicationSource> sourceList = sourceMapper.selectByRange(startId, endId);
        
        int processedCount = 0;
        int skippedCount = 0;
        String lastProcessedId = startId;  // Track actual last processed ID
        
        for (ApplicationSource source : sourceList) {
            try {
                if (!isMigrationTarget(source)) {
                    System.out.println("SKIP: " + source.get申込番号() + " - Not migration target");
                    skippedCount++;
                    lastProcessedId = source.get申込番号();  // Update even for skipped
                    continue;
                }
                
                ApplicationTarget target = transform(source);
                
                targetMapper.insert(target);
                
                processedCount++;
                lastProcessedId = source.get申込番号();  // Track last processed
                
            } catch (Exception e) {
                System.err.println("ERROR processing 申込番号=" + source.get申込番号() + ": " + e.getMessage());
                throw e;
            }
        }
        
        System.out.println("Range completed: Processed=" + processedCount + ", Skipped=" + skippedCount);
        return lastProcessedId;  // Return for markDone
    }
    
    
    //Migration Target Check
    private boolean isMigrationTarget(ApplicationSource source) {
        
        String 申込目的 = source.get申込目的();
        if (!E申込目的.shouldMigrate(申込目的)) {
            return false;
        }
        
        if (source.get申込番号() == null || source.get申込番号().isEmpty()) {
            return false;
        }
        
        return true;
    }
    private ApplicationTarget transform(ApplicationSource source) {
        ApplicationTarget target = new ApplicationTarget();
        
        // 1. 申込番号 (PK) - No conversion
        target.set申込番号(source.get申込番号());
        
        // 2. 申込目的 - E-level conversion (10/15→10, 20/30→20, 90→null)
        String old申込目的 = source.get申込目的();
        String new申込目的 = E申込目的.convert(old申込目的);
        target.set申込目的(new申込目的);
        
        return target;
    }
}
