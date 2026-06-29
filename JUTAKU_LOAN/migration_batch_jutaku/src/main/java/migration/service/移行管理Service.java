package migration.service;

import migration.domain.management.移行管理テーブル;
import migration.mapper.target.移行管理テーブルMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;

@Service
@Slf4j
public class 移行管理Service {

    @Autowired
    private 移行管理テーブルMapper managementMapper;

    /**
     * Claim 1 TODO range row: SELECT ROWNUM=1 FOR UPDATE NOWAIT → UPDATE to RUNNING → COMMIT.
     * Returns null if no TODO rows remain.
     * Throws exception on ORA-00054 lock conflict — caller should retry.
     */
    @Transactional
    public 移行管理テーブル claimNextRange(String システム) {
        移行管理テーブル range = managementMapper.claimNextRange(システム);
        if (range == null) {
            return null;
        }
        Timestamp now = new Timestamp(System.currentTimeMillis());
        managementMapper.updateStatusToRunning(システム, range.get処理FROM(), now);
        log.info("  Claimed range: {} ~ {} [RUNNING]", range.get処理FROM(), range.get処理TO());
        return range;
    }

    /**
     * Mark range as DONE (separate transaction — independent of migration rollback).
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void markDone(String システム, long 処理FROM) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        managementMapper.updateStatusToDone(システム, 処理FROM, now);
        log.info("  Marked DONE: FROM={}", 処理FROM);
    }

    /**
     * Mark range as ERROR (separate transaction — independent of migration rollback).
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void markError(String システム, long 処理FROM, String errorMessage) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        String truncated = errorMessage != null && errorMessage.length() > 60
                ? errorMessage.substring(0, 60)
                : errorMessage;
        managementMapper.updateStatusToError(システム, 処理FROM, now, truncated);
        log.error("  Marked ERROR: FROM={} - {}", 処理FROM, errorMessage);
    }
}
