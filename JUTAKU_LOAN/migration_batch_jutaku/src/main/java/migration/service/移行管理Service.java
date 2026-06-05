package migration.service;

import migration.domain.移行管理.移行管理;
import migration.mapper.移行管理.移行管理Mapper;
import org.apache.ibatis.cursor.Cursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 移行管理 Service Implements claim/process/mark cycle for parallel processing
 */
@Service
public class 移行管理Service {
    
    @Autowired
    private 移行管理Mapper managementMapper;
    
    /**
     * Claim records for processing (single transaction)
     * 1. SELECT with FOR UPDATE SKIP LOCKED
     * 2. Fetch batchSize records
     * 3. UPDATE status TODO to RUNNING
     * 4. COMMIT (releases locks)
     * 
     * @param batchSize Number of records to claim
     * @param processId Identifier for this parallel process (e.g., "PROC-1", "PROC-2")
     */
    @Transactional
    public List<移行管理> claimRecords(int batchSize, String processId) {
        System.out.println("=== Claiming up to " + batchSize + " records (Process: " + processId + ") ===");
        
        List<移行管理> claimed = new ArrayList<>();
        
        try (Cursor<移行管理> cursor = managementMapper.selectUnprocessedForUpdate()) {
            Iterator<移行管理> iterator = cursor.iterator();
            
            while (iterator.hasNext() && claimed.size() < batchSize) {
                移行管理 record = iterator.next();
                claimed.add(record);
                System.out.println("  Fetched: " + record.get申込番号() + " [LOCKED]");
            }
            
            if (claimed.isEmpty()) {
                System.out.println("  No more TODO records found");
                return claimed;
            }
            
            Timestamp startTime = new Timestamp(System.currentTimeMillis());
            int updated = managementMapper.updateStatusToRunning(claimed, startTime, processId);
            
            System.out.println("  Updated " + updated + " records to RUNNING (Process: " + processId + ")");
            System.out.println("=== Claim transaction will commit (locks released) ===");
            
        } catch (Exception e) {
            System.err.println("ERROR claiming records: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to claim records", e);
        }
        
        return claimed;
    }
    
    /**
     * Mark record as DONE (separate transaction)
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void markDone(String 申込番号) {
        Timestamp endTime = new Timestamp(System.currentTimeMillis());
        int updated = managementMapper.updateStatusToDone(申込番号, endTime);
        
        if (updated == 0) {
            System.err.println("WARNING: Failed to update status to DONE for 申込番号: " + 申込番号);
        } else {
            System.out.println("  Marked DONE: " + 申込番号);
        }
    }
    
    /**
     * Mark record as ERROR (separate transaction)
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void markError(String 申込番号, String errorMessage) {
        Timestamp endTime = new Timestamp(System.currentTimeMillis());
        
        int updated = managementMapper.updateStatusToError(申込番号, endTime);
        
        if (updated == 0) {
            System.err.println("WARNING: Failed to update status to ERROR for 申込番号: " + 申込番号);
        } else {
            System.err.println("  Marked ERROR: " + 申込番号 + " - " + errorMessage);
        }
    }
    
    /**
     * Print current status summary
     */
    public void printStatusSummary() {
        long todoCount = managementMapper.countByStatus(移行管理.STATUS_TODO);
        long runningCount = managementMapper.countByStatus(移行管理.STATUS_RUNNING);
        long doneCount = managementMapper.countByStatus(移行管理.STATUS_DONE);
        long errorCount = managementMapper.countByStatus(移行管理.STATUS_ERROR);
        
        System.out.println("\n=== Migration Status Summary ===");
        System.out.println("TODO:    " + todoCount);
        System.out.println("RUNNING: " + runningCount);
        System.out.println("DONE:    " + doneCount);
        System.out.println("ERROR:   " + errorCount);
        System.out.println("================================\n");
    }
}
