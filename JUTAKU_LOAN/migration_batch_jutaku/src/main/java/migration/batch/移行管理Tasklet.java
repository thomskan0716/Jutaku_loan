package migration.batch;

import migration.domain.management.移行管理テーブル;
import migration.service.移行管理Service;
import migration.service.JutakuLoanService;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 移行管理 Tasklet
 * Parallel processing via 移行管理テーブル (range-based, FOR UPDATE NOWAIT).
 *
 * Flow per iteration:
 *   claimNextRange()  → ① SELECT ROWNUM=1 FOR UPDATE NOWAIT, UPDATE to RUNNING
 *   processOneRange() → ② Migrate all 申込 records in range (ROW_NUMBER fromNo~toNo)
 *   markDone/Error()  → ③ UPDATE range to DONE or ERROR
 */
@Component("managementBasedTasklet")
@StepScope
@Slf4j
public class 移行管理Tasklet implements Tasklet {

    @Autowired
    private 移行管理Service managementService;

    @Autowired
    private JutakuLoanService migrationService;

    @Value("${migration.process.id:0}")
    private long processId;

    @Value("${migration.lock-retry-wait-ms:50}")
    private long lockRetryWaitMs;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        String p = "[P" + processId + "]";
        log.info("=== {} Migration Tasklet Started ===", p);

        int totalRanges = 0;

        while (true) {
            移行管理テーブル range = null;
            try {
                range = managementService.claimNextRange(移行管理テーブル.SYSTEM_JUTAKU);
            } catch (Exception e) {
                if (isOracleResourceBusy(e)) {
                    // Another process locked the same ROWNUM=1 row — it will be RUNNING after commit, retry gets next row
                    log.info("  {} Lock conflict (ORA-00054), waiting {}ms then retrying...", p, lockRetryWaitMs);
                    sleepQuietly(lockRetryWaitMs);
                    continue;
                }
                throw new RuntimeException("Fatal error claiming range", e);
            }

            if (range == null) {
                log.info("{} No more TODO ranges.", p);
                break;
            }

            log.info("--- {} Processing range: {} ~ {} ---", p, range.get処理FROM(), range.get処理TO());

            try {
                migrationService.processOneRange(range.get処理FROM(), range.get処理TO());
                managementService.markDone(移行管理テーブル.SYSTEM_JUTAKU, range.get処理FROM());
                totalRanges++;
            } catch (Exception e) {
                log.error("  {} ERROR in range {}~{}: {}", p, range.get処理FROM(), range.get処理TO(), e.getMessage());
                managementService.markError(移行管理テーブル.SYSTEM_JUTAKU, range.get処理FROM(), e.getMessage());
            }
        }

        log.info("=== {} Migration Tasklet Completed. Ranges processed: {} ===", p, totalRanges);
        return RepeatStatus.FINISHED;
    }

    private void sleepQuietly(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private boolean isOracleResourceBusy(Exception e) {
        Throwable cause = e;
        while (cause != null) {
            if (cause.getMessage() != null && cause.getMessage().contains("ORA-00054")) {
                return true;
            }
            cause = cause.getCause();
        }
        return false;
    }
}
