package migration.service;

import migration.domain.source.申込Source;
import migration.domain.source.申込審査段階Source;
import migration.domain.source.申込進捗Source;
import migration.domain.source.保証人Source;
import migration.domain.target.申込Target;
import migration.domain.target.申込審査状況Target;
import migration.domain.target.申込審査段階Target;
import migration.domain.target.申込進捗Target;
import migration.domain.target.履歴申込Target;
import migration.domain.target.履歴申込審査段階Target;
import migration.domain.target.履歴保証人Target;
import migration.domain.target.保証人Target;
import migration.mapper.source.申込SourceMapper;
import migration.mapper.source.申込審査段階SourceMapper;
import migration.mapper.source.申込進捗SourceMapper;
import migration.mapper.source.保証人SourceMapper;
import migration.mapper.target.申込TargetMapper;
import migration.mapper.target.申込審査状況TargetMapper;
import migration.mapper.target.申込審査段階TargetMapper;
import migration.mapper.target.申込進捗TargetMapper;
import migration.mapper.target.履歴申込TargetMapper;
import migration.mapper.target.履歴申込審査段階TargetMapper;
import migration.mapper.target.履歴保証人TargetMapper;
import migration.mapper.target.保証人TargetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JutakuLoanService {

    // --- Source mappers ---
    @Autowired private 申込進捗SourceMapper 進捗SourceMapper;
    @Autowired private 申込審査段階SourceMapper 段階SourceMapper;
    @Autowired private 申込SourceMapper 申込SourceMapper;
    @Autowired private 保証人SourceMapper 保証人SourceMapper;

    // --- Target mappers ---
    @Autowired private 申込進捗TargetMapper 進捗TargetMapper;
    @Autowired private 申込審査段階TargetMapper 段階TargetMapper;
    @Autowired private 申込審査状況TargetMapper 審査状況TargetMapper;
    @Autowired private 履歴申込審査段階TargetMapper 履歴段階TargetMapper;
    @Autowired private 申込TargetMapper 申込TargetMapper;
    @Autowired private 履歴申込TargetMapper 履歴申込TargetMapper;
    @Autowired private 保証人TargetMapper 保証人TargetMapper;
    @Autowired private 履歴保証人TargetMapper 履歴保証人TargetMapper;

    @Value("${migration.simulate:false}")
    private boolean simulate;

    @Value("${migration.simulate-sleep-ms:500}")
    private long simulateSleepMs;

    // 事前審査グループ: old codes, sorted ascending (10 < 15)
    private static final List<String> 事前GROUP = Arrays.asList("10", "15");
    // 正式審査グループ: old codes, sorted ascending (20 < 30)
    private static final List<String> 正式GROUP = Arrays.asList("20", "30");

    /**
     * Called by 移行管理Tasklet for each claimed range.
     * Driving table is 申込進捗 (one row per 申込番号).
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

        List<申込進捗Source> 進捗List = 進捗SourceMapper.selectByRowRange(fromNo, toNo);

        int processed = 0;
        int skipped = 0;

        for (申込進捗Source 進捗 : 進捗List) {
            try {
                boolean migrated = migrateSingleApplication(進捗);
                if (migrated) {
                    processed++;
                } else {
                    skipped++;
                }
            } catch (Exception e) {
                log.error("ERROR processing 申込番号={}: {}", 進捗.get申込番号(), e.getMessage());
                throw e;
            }
        }

        log.info("Range completed: Processed={}, Skipped={}", processed, skipped);
    }

    /**
     * Migrates all target tables for one 申込番号.
     * Returns true if at least one group was migrated, false if entirely skipped.
     *
     * Logic:
     *  1. Fetch all 申込審査段階 rows for srcNo, keep only 審査完了区分='1'
     *  2. If none → skip
     *  3. Copy 申込進捗 1:1 (申込番号 first digit 2→3)
     *  4. Group completed rows into 事前 (10/15) and 正式 (20/30)
     *  5. For each non-empty group, insert into 8 target tables via processGroup()
     */
    private boolean migrateSingleApplication(申込進捗Source 進捗) {
        String srcNo = 進捗.get申込番号();
        String tgtNo = convertApplicationNumber(srcNo);

        List<申込審査段階Source> allStages = 段階SourceMapper.selectByApplicationId(srcNo);
        List<申込審査段階Source> completed = allStages.stream()
                .filter(s -> "1".equals(s.get審査完了区分()))
                .sorted(Comparator.comparing(申込審査段階Source::get申込目的))
                .collect(Collectors.toList());

        if (completed.isEmpty()) {
            log.info("SKIP: 申込番号={} - no completed 審査段階", srcNo);
            return false;
        }

        // 申込進捗: 1:1 copy (申込番号 converted; 進捗コード/状態 passed through — NOT NULL in target)
        申込進捗Target 進捗Target = new 申込進捗Target();
        進捗Target.set申込番号(tgtNo);
        進捗Target.set進捗コード(進捗.get進捗コード());
        進捗Target.set状態(進捗.get状態());
        進捗TargetMapper.insert(進捗Target);

        // 事前審査グループ (申込目的: 10, 15 → new '10')
        List<申込審査段階Source> 事前 = completed.stream()
                .filter(s -> 事前GROUP.contains(s.get申込目的()))
                .collect(Collectors.toList());

        // 正式審査グループ (申込目的: 20, 30 → new '20')
        List<申込審査段階Source> 正式 = completed.stream()
                .filter(s -> 正式GROUP.contains(s.get申込目的()))
                .collect(Collectors.toList());

        processGroup(srcNo, tgtNo, 事前, "10");
        processGroup(srcNo, tgtNo, 正式, "20");

        log.debug("Migrated 申込番号={} → {} (事前={}, 正式={})", srcNo, tgtNo, 事前.size(), 正式.size());
        return true;
    }

    /**
     * Inserts into all 7 target tables for one 事前/正式 group.
     *
     * Main tables (MAX 申込目的 only):
     *   - 申込審査段階   (converted 申込目的: always '10' or '20')
     *   - 申込           (converted 申込目的)
     *   - 保証人         (converted 申込目的)
     *
     * History tables (all completed rows, 回数 = 1..N ascending):
     *   - 申込審査状況       (parent history, '10'/'20', 回数)
     *   - 履歴申込審査段階   (detail,         '10'/'20', 回数)
     *   - 履歴申込           (                '10'/'20', 回数)
     *   - 履歴保証人         (                '10'/'20', 回数, 連番)
     *
     * group is pre-sorted by 申込目的 ASC → MAX = last element → highest 回数.
     */
    private void processGroup(String srcNo, String tgtNo,
                               List<申込審査段階Source> group, String newMokuteki) {
        if (group.isEmpty()) {
            return;
        }

        // MAX old 申込目的 is the last in ascending-sorted list
        String maxOldMokuteki = group.get(group.size() - 1).get申込目的();

        // ① 申込 main (MAX only) — inserted FIRST: 申込審査段階/保証人 both FK→申込
        申込Source maxApp = 申込SourceMapper.selectByApplicationIdAndPurpose(srcNo, maxOldMokuteki);
        if (maxApp != null) {
            申込Target appT = new 申込Target();
            appT.set申込番号(tgtNo);
            appT.set申込目的(newMokuteki);
            申込TargetMapper.insert(appT);
        }

        // ② 申込審査段階 main (MAX only) — FK→申込
        申込審査段階Target 段階T = new 申込審査段階Target();
        段階T.set申込番号(tgtNo);
        段階T.set申込目的(newMokuteki);
        段階T.set審査完了区分(group.get(group.size() - 1).get審査完了区分());
        段階TargetMapper.insert(段階T);

        // ③ 保証人 main (MAX only) — FK→申込
        List<保証人Source> maxGuarantors =
                保証人SourceMapper.selectByApplicationIdAndPurpose(srcNo, maxOldMokuteki);
        for (保証人Source g : maxGuarantors) {
            保証人Target gT = new 保証人Target();
            gT.set申込番号(tgtNo);
            gT.set申込目的(newMokuteki);
            gT.set連番(g.get連番());
            保証人TargetMapper.insert(gT);
        }

        // ④-⑦ History for every completed record in this group
        int 回数 = 1;
        for (申込審査段階Source stage : group) {
            String oldMokuteki = stage.get申込目的();

            // ④ 申込審査状況 — FK→申込
            申込審査状況Target 状況T = new 申込審査状況Target();
            状況T.set申込番号(tgtNo);
            状況T.set申込目的(newMokuteki);
            状況T.set回数(回数);
            審査状況TargetMapper.insert(状況T);

            // ⑤ 履歴申込 — FK→申込審査状況 (must be before 履歴申込審査段階/履歴保証人)
            申込Source histApp = 申込SourceMapper.selectByApplicationIdAndPurpose(srcNo, oldMokuteki);
            if (histApp != null) {
                履歴申込Target hist申込T = new 履歴申込Target();
                hist申込T.set申込番号(tgtNo);
                hist申込T.set申込目的(newMokuteki);
                hist申込T.set回数(回数);
                履歴申込TargetMapper.insert(hist申込T);
            }

            // ⑥ 履歴申込審査段階 — FK→履歴申込
            履歴申込審査段階Target hist段階T = new 履歴申込審査段階Target();
            hist段階T.set申込番号(tgtNo);
            hist段階T.set申込目的(newMokuteki);
            hist段階T.set回数(回数);
            hist段階T.set審査完了区分(stage.get審査完了区分());
            履歴段階TargetMapper.insert(hist段階T);

            // ⑦ 履歴保証人 — FK→履歴申込
            List<保証人Source> histGuarantors =
                    保証人SourceMapper.selectByApplicationIdAndPurpose(srcNo, oldMokuteki);
            for (保証人Source g : histGuarantors) {
                履歴保証人Target hist保証人T = new 履歴保証人Target();
                hist保証人T.set申込番号(tgtNo);
                hist保証人T.set申込目的(newMokuteki);
                hist保証人T.set回数(回数);
                hist保証人T.set連番(g.get連番());
                履歴保証人TargetMapper.insert(hist保証人T);
            }

            回数++;
        }
    }

    /**
     * Convert 申込番号 first digit from '2' to '3'.
     * e.g. "202606017001" → "302606017001"
     */
    private String convertApplicationNumber(String src) {
        if (src == null || src.isEmpty()) {
            return src;
        }
        return "3" + src.substring(1);
    }
}
