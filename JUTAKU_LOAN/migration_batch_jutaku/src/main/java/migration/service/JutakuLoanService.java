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

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@Slf4j
public class JutakuLoanService {

    //  申込 
    @Autowired
    private 申込SourceMapper sourceMapper;

    @Autowired
    private 申込TargetMapper targetMapper;

    @Value("${migration.range.batch-size:100}")
    private int batchSize;

    /** When true (test profile), simulate work instead of real migration — used to verify parallel processing in isolation. */
    @Value("${migration.simulate:false}")
    private boolean simulate;

    @Value("${migration.simulate-sleep-ms:500}")
    private long simulateSleepMs;

    /**
     * Process One Range (Main Logic)
     * Fetches all 申込 records where ROW_NUMBER() BETWEEN fromNo AND toNo, migrates each.
     * TODO: selectByRowRange query must be updated to include JOIN 申込審査状況 + 審査完了区分 filter.
     */
    @Transactional
    public void processOneRange(long fromNo, long toNo) {
        if (simulate) {
            log.info("  [SIMULATE] Processing range {} ~ {} (sleep {}ms)", fromNo, toNo, simulateSleepMs);
            try {
                Thread.sleep(simulateSleepMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            log.info("  [SIMULATE] Done range {} ~ {}", fromNo, toNo);
            return;
        }

        log.info("Processing range: {} ~ {}", fromNo, toNo);

        List<申込Source> sourceList = sourceMapper.selectByRowRange(fromNo, toNo);

        int processedCount = 0;
        int skippedCount = 0;

        for (申込Source source : sourceList) {
            try {
                if (!isMigrationTarget(source)) {
                    log.info("SKIP: {} - Not migration target", source.get申込番号());
                    skippedCount++;
                    continue;
                }

                writeSubsInBatch(source);
                processedCount++;

            } catch (Exception e) {
                log.error("ERROR processing 申込番号={}: {}", source.get申込番号(), e.getMessage());
                throw e;
            }
        }

        log.info("Range completed: Processed={}, Skipped={}", processedCount, skippedCount);
    }

    /**
     * Insert all target tables for one 申込 record (equivalent to parent writeSubsInBatch)
     */
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
