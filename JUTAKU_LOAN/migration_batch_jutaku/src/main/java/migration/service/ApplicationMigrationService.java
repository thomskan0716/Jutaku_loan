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
    
    private static final int BATCH_SIZE = 100; // Records per range
    private String currentMinId = null;
    private String currentMaxId = null;
    
    public void processAll() {
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
        List<ApplicationSource> candidates = sourceMapper.selectByRange(
            currentMinId != null ? currentMinId : "000000000001",
            currentMaxId != null ? currentMaxId : "999999999999"
        );
        
        if (candidates.isEmpty()) {
            return null;
        }
        
        return candidates.get(0);
    }
    
    public void markDone(ApplicationSource range) {
        System.out.println("DONE: Range starting from " + range.get申込番号());
    }
    
    public void markError(ApplicationSource range, String errorMessage) {
        System.err.println("ERROR: Range starting from " + range.get申込番号() + " - " + errorMessage);
    }
    
    /**
       Process One Range (Main Logic)
     * @param startId Range start ID
     * @param endId Range end ID
     * @return Number of processed records
     */
    @Transactional
    public int processOneRange(String startId, String endId) {
        System.out.println("Processing range: " + startId + " ~ " + endId);
        
        List<ApplicationSource> sourceList = sourceMapper.selectByRange(startId, endId);
        
        int processedCount = 0;
        int skippedCount = 0;
        
        for (ApplicationSource source : sourceList) {
            try {
                if (!isMigrationTarget(source)) {
                    System.out.println("SKIP: " + source.get申込番号() + " - Not migration target");
                    skippedCount++;
                    continue;
                }
                
                ApplicationTarget target = transform(source);
                
                targetMapper.insert(target);
                
                processedCount++;
                
            } catch (Exception e) {
                System.err.println("ERROR processing 申込番号=" + source.get申込番号() + ": " + e.getMessage());
                throw e; // Let transaction rollback
            }
        }
        
        System.out.println("Range completed: Processed=" + processedCount + ", Skipped=" + skippedCount);
        return processedCount;
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
        
        // TODO: Add more criteria
        // - Check 審査ステータス = completed
        
        return true;
    }
    
    //Transform: Apply E-Level Conversions
    private ApplicationTarget transform(ApplicationSource source) {
        ApplicationTarget target = new ApplicationTarget();
        

        target.set申込番号(source.get申込番号());
        
        
        target.set申込日(source.get申込日());
        target.set融資申込番号(source.get融資申込番号());
        target.set関連番(source.get関連番());
        target.set経済記(source.get経済記());
        target.set申込履歴号(source.get申込履歴号());
        target.set申込日付(source.get申込日付());
        target.set審査履歴(source.get審査履歴());
        target.setイベント(source.getイベント());
        
        
        String old連絡コード = source.get連絡コード();
        String new連絡コード = E連絡コード.convert(old連絡コード);
        target.set連絡コード(new連絡コード);
        
        
        String old日付 = source.get日付();
        String new日付 = E申込目的.convert(old日付);
        target.set日付(new日付);
        
        return target;
    }
}
