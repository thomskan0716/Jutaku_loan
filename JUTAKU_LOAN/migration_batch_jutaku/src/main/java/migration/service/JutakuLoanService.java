package migration.service;

import migration.common.szh_sms.E申込目的;
import migration.domain.source.申込Source;
import migration.domain.target.申込Target;
import migration.mapper.source.申込SourceMapper;
import migration.mapper.target.申込TargetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JutakuLoanService {

    // 申込 
    @Autowired
    private 申込SourceMapper sourceMapper;

    @Autowired
    private 申込TargetMapper targetMapper;


    @Value("${migration.range.batch-size:100}")
    private int batchSize;

    @Value("${migration.range.test-limit:1000}")
    private int testLimit;
    private String currentMinId = null;
    private String currentMaxId = null;
    private int totalProcessed = 0;

    public void processAll() {
        targetMapper.deleteAll();
        System.out.println("申込 Migration - Target tables cleared");

        long totalCount = sourceMapper.count();
        currentMinId = sourceMapper.findMinId();
        currentMaxId = sourceMapper.findMaxId();
        totalProcessed = 0;

        System.out.println("申込 Migration - Process All Started");
        System.out.println("Total Records: " + totalCount);
        System.out.println("ID Range: " + currentMinId + " ~ " + currentMaxId);
        System.out.println("TEST LIMIT: " + testLimit + " records");

    }

    /**
     * @return Next range start record, or null if no more ranges
     */
    @Transactional
    public 申込Source claimNextRange() {
        if (totalProcessed >= testLimit) {
            System.out.println("TEST LIMIT reached: " + totalProcessed + " records processed. Stopping.");
            return null;
        }

        if (currentMinId != null && currentMaxId != null) {
            if (currentMinId.compareTo(currentMaxId) > 0) {
                System.out.println("Reached maxId limit: " + currentMaxId);
                return null;
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
        long nextId = Long.parseLong(actualMaxId) + 1;
        currentMinId = String.format("%012d", nextId);
        System.out.println("DONE: Range starting from " + range.get申込番号() +
            " (last processed: " + actualMaxId + ") → Next start: " + currentMinId);
    }

    public void markError(申込Source range, String errorMessage) {
        System.err.println("ERROR: Range starting from " + range.get申込番号() + " - " + errorMessage);
    }

    /**
     * Process One Range (Main Logic)
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
        String lastProcessedId = startId;

        for (申込Source source : sourceList) {
            try {
                if (!isMigrationTarget(source)) {
                    System.out.println("SKIP: " + source.get申込番号() + " - Not migration target");
                    skippedCount++;
                    lastProcessedId = source.get申込番号();
                    continue;
                }

                writeSubsInBatch(source);

                processedCount++;
                lastProcessedId = source.get申込番号();

            } catch (Exception e) {
                System.err.println("ERROR processing 申込番号=" + source.get申込番号() + ": " + e.getMessage());
                throw e;
            }
        }

        totalProcessed += processedCount + skippedCount;
        System.out.println("Range completed: Processed=" + processedCount + ", Skipped=" + skippedCount + ", Total so far=" + totalProcessed);
        return lastProcessedId;
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

        writeSubsInBatch(source);
        return 1;
    }

    private void writeSubsInBatch(申込Source source) {

        //  申込 
        申込Target target = convApplication(source);
        targetMapper.insert(target);

    }

    //  convApplication 
    private 申込Target convApplication(申込Source source) {
        申込Target target = new 申込Target();

        // 申込番号 (PK) - No conversion
        target.set申込番号(source.get申込番号());

        // 申込目的 - E-level conversion (10/15→10, 20/30→20, 90→null)
        target.set申込目的(E申込目的.convert(source.get申込目的()));

        return target;
    }

    private boolean isMigrationTarget(申込Source source) {
        if (!E申込目的.shouldMigrate(source.get申込目的())) {
            return false;
        }
        if (source.get申込番号() == null || source.get申込番号().isEmpty()) {
            return false;
        }
        return true;
    }
}
