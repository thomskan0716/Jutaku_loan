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
import migration.domain.target.履歴申込_業者_住宅Target;
import migration.domain.target.履歴申込審査段階Target;
import migration.domain.target.履歴保証人Target;
import migration.domain.target.履歴保証検討表補足Target;
import migration.domain.target.保証人Target;
import migration.domain.target.申込_業者_住宅Target;
import migration.domain.target.保証検討表補足Target;
import migration.domain.source.保証検討表補足Source;
import migration.mapper.source.申込SourceMapper;
import migration.mapper.source.申込審査段階SourceMapper;
import migration.mapper.source.申込進捗SourceMapper;
import migration.mapper.source.保証検討表補足SourceMapper;
import migration.mapper.source.保証人SourceMapper;
import migration.mapper.target.申込TargetMapper;
import migration.mapper.target.申込審査状況TargetMapper;
import migration.mapper.target.申込審査段階TargetMapper;
import migration.mapper.target.申込進捗TargetMapper;
import migration.mapper.target.履歴申込TargetMapper;
import migration.mapper.target.履歴申込_業者_住宅TargetMapper;
import migration.mapper.target.履歴申込審査段階TargetMapper;
import migration.mapper.target.履歴保証人TargetMapper;
import migration.mapper.target.履歴保証検討表補足TargetMapper;
import migration.mapper.target.保証人TargetMapper;
import migration.mapper.target.申込_業者_住宅TargetMapper;
import migration.mapper.target.保証検討表補足TargetMapper;
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
    @Autowired private 保証検討表補足SourceMapper 保証検討表補足SourceMapper;

    // --- Target mappers ---
    @Autowired private 申込進捗TargetMapper 進捗TargetMapper;
    @Autowired private 申込審査段階TargetMapper 段階TargetMapper;
    @Autowired private 申込審査状況TargetMapper 審査状況TargetMapper;
    @Autowired private 履歴申込審査段階TargetMapper 履歴段階TargetMapper;
    @Autowired private 申込TargetMapper 申込TargetMapper;
    @Autowired private 履歴申込TargetMapper 履歴申込TargetMapper;
    @Autowired private 履歴申込_業者_住宅TargetMapper 履歴申込_業者_住宅TargetMapper;
    @Autowired private 保証人TargetMapper 保証人TargetMapper;
    @Autowired private 申込_業者_住宅TargetMapper 申込_業者_住宅TargetMapper;
    @Autowired private 保証検討表補足TargetMapper 保証検討表補足TargetMapper;
    @Autowired private 履歴保証人TargetMapper 履歴保証人TargetMapper;
    @Autowired private 履歴保証検討表補足TargetMapper 履歴保証検討表補足TargetMapper;

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
            map申込Columns(maxApp, appT);
            申込TargetMapper.insert(appT);

            // ①-a 申込_業者_住宅 main (MAX only) — FK→申込
            申込_業者_住宅Target 業者T = new 申込_業者_住宅Target();
            業者T.set申込番号(tgtNo);
            業者T.set申込目的(newMokuteki);
            申込_業者_住宅TargetMapper.insert(業者T);
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

        // ③-a 保証検討表補足 main (MAX only) — FK→申込
        保証検討表補足Source max補足Src =
                保証検討表補足SourceMapper.selectByApplicationIdAndPurpose(srcNo, maxOldMokuteki);
        if (max補足Src != null) {
            保証検討表補足Target 補足T = new 保証検討表補足Target();
            補足T.set申込番号(tgtNo);
            補足T.set申込目的(newMokuteki);
            保証検討表補足TargetMapper.insert(補足T);
        }

        // ④-⑧ History for every completed record in this group
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

                // ⑤-a 履歴申込_業者_住宅 — FK→履歴申込
                履歴申込_業者_住宅Target hist業者T = new 履歴申込_業者_住宅Target();
                hist業者T.set申込番号(tgtNo);
                hist業者T.set申込目的(newMokuteki);
                hist業者T.set回数(回数);
                履歴申込_業者_住宅TargetMapper.insert(hist業者T);
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

            // ⑧ 履歴保証検討表補足 — FK→履歴申込
            保証検討表補足Source hist補足Src =
                    保証検討表補足SourceMapper.selectByApplicationIdAndPurpose(srcNo, oldMokuteki);
            if (hist補足Src != null) {
                履歴保証検討表補足Target hist補足T = new 履歴保証検討表補足Target();
                hist補足T.set申込番号(tgtNo);
                hist補足T.set申込目的(newMokuteki);
                hist補足T.set回数(回数);
                履歴保証検討表補足TargetMapper.insert(hist補足T);
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

    /** Map all non-PK columns from 申込Source to 申込Target. */
    private void map申込Columns(申込Source s, 申込Target t) {
        t.set商品大分類(s.get商品大分類());
        t.set商品コード(s.get商品コード());
        t.set受付店舗(s.get受付店舗());
        t.set保証番号(s.get保証番号());
        t.set関連案件有無(s.get関連案件有無());
        t.set申込日(s.get申込日());
        t.set店舗(s.get店舗());
        t.setＣＩＦ番号(s.getＣＩＦ番号());

        // カナ氏名: direct + split on half-width space
        String kana = s.getカナ氏名();
        t.setカナ氏名(kana);
        if (kana != null) {
            int i = kana.indexOf(' ');
            t.setカナ氏名姓(i >= 0 ? kana.substring(0, i) : kana);
            t.setカナ氏名名(i >= 0 ? kana.substring(i + 1) : null);
        }

        // 漢字氏名: direct + split on full-width space (U+3000)
        String kanji = s.get漢字氏名();
        t.set漢字氏名(kanji);
        if (kanji != null) {
            int i = kanji.indexOf('　');
            t.set漢字氏名姓(i >= 0 ? kanji.substring(0, i) : kanji);
            t.set漢字氏名名(i >= 0 ? kanji.substring(i + 1) : null);
        }

        t.set自宅郵便番号(s.get自宅郵便番号());
        t.set自宅住所カナ(s.get自宅住所カナ());
        t.set自宅住所漢字(s.get自宅住所漢字());
        t.set生年月日(s.get生年月日());
        t.set性別(s.get性別());
        t.set勤務先郵便番号(s.get勤務先郵便番号());
        t.set携帯電話番号(s.get携帯電話番号());
        t.set建物完成予定日(s.get建物完成予定日());
        t.set検索用カナ氏名(s.get検索用カナ氏名());
        t.set勤務先名漢字(s.get勤務先名漢字());
        t.set勤務先入社年月(s.get勤務先入社年月());
        t.set勤務先勤続年数(s.get勤務先勤続年数());
        t.set勤務先業態区分(s.get上場フラグ());   // 申込ワイド.上場フラグ → 勤務先業態区分
        t.set勤務先勤業(s.get勤務先職業());
        t.set勤務先勤種(s.get勤務先職種役取());   // direct code copy
        t.set勤務先勤種その他(null);               // requires 編集仕様詳細 — set null for now
        t.set勤務先資本金区分(s.get勤務先資本金区分());
        t.set勤務先逐業員数(s.get勤務先従業員数());
        t.set住居形態(s.get住居区分());
        t.set定積(s.get定積());
        t.set展示年数(s.get展示年数());
        t.set定積＿子の他(s.get定積＿子の他());

        // 金融機関 1
        t.set金融機関1名称(s.get借入＿利用先名1());
        t.set金融機関1借入種類(s.get借入＿利用種類1());
        t.set金融機関1残高(s.get借入＿利用残高1());
        t.set金融機関1借入年間返済額(s.get借入＿年間変払額1());
        t.set金融機関1借入期間(s.get借入＿残存期間1());
        t.set金融機関1借入時完済解約予定(s.get借入＿解約予定1());
        t.set金融機関1利用限度額(s.get借入＿利用限度額1());
        // 金融機関 2
        t.set金融機関2名称(s.get借入＿利用先名2());
        t.set金融機関2借入種類(s.get借入＿利用種類2());
        t.set金融機関2残高(s.get借入＿利用残高2());
        t.set金融機関2借入年間返済額(s.get借入＿年間変払額2());
        t.set金融機関2借入期間(s.get借入＿残存期間2());
        t.set金融機関2借入時完済解約予定(s.get借入＿解約予定2());
        t.set金融機関2利用限度額(s.get借入＿利用限度額2());
        // 金融機関 3
        t.set金融機関3名称(s.get借入＿利用先名3());
        t.set金融機関3借入種類(s.get借入＿利用種類3());
        t.set金融機関3残高(s.get借入＿利用残高3());
        t.set金融機関3借入年間返済額(s.get借入＿年間変払額3());
        t.set金融機関3借入期間(s.get借入＿残存期間3());
        t.set金融機関3借入時完済解約予定(s.get借入＿解約予定3());
        t.set金融機関3利用限度額(s.get借入＿利用限度額3());

        t.set資金使途(s.get資金使途());
        t.set借入金額(s.get借入金額());
        t.set借入金額＿毎月(s.get借入金額＿毎月());
        t.set借入金額＿半年額(s.get借入金額＿半年額());
        t.set返済額＿毎月(s.get借入金額＿毎月());
        t.set返済額＿半年毎(s.get借入金額＿半年額());
        t.set借入期間(s.get借入期間());
        t.set借入希望日(s.get借入希望日());
        t.set借入希望日＿建物(s.get借入希望日＿建物());
        t.set返済方法区分(s.get返済方法区分());
        t.set金利区分(s.get金利区分());
        t.set保証料区分(s.get保証料区分());
        t.setボーナス返済月1(s.getボーナス返済月1());
        t.setボーナス返済月2(s.getボーナス返済月2());

        // 同居予定家族
        t.set同居予定家族＿配偶者(s.get同居＿配偶者());
        t.set同居予定家族＿父(s.get同居＿父());
        t.set同居予定家族＿母(s.get同居＿母());
        java.math.BigDecimal otherCount = s.get同居＿その他人数();
        t.set同居予定家族＿その他(otherCount != null && otherCount.compareTo(java.math.BigDecimal.ZERO) > 0 ? "1" : "0");
        t.set同居予定家族＿その他＿人数(otherCount);
        t.set同居予定家族＿子供人数(s.get同居＿子の人数());
        t.set同居予定家族＿子供年齢＿1人目(s.get同居＿子供年齢1());
        t.set同居予定家族＿子供年齢＿2人目(s.get同居＿子供年齢2());
        t.set同居予定家族＿子供年齢＿3人目(s.get同居＿子供年齢3());
        t.set同居予定家族＿子供年齢＿4人目(s.get同居＿子供年齢4());
        t.set同居予定家族＿本人("1");  // fixed: 本人入居予定

        // 同居予定家族＿合計人数 = 1(本人) + 配偶者 + 父 + 母 + その他人数 + 子供人数
        int total = 1;
        if ("1".equals(s.get同居＿配偶者())) total++;
        if ("1".equals(s.get同居＿父()))    total++;
        if ("1".equals(s.get同居＿母()))    total++;
        if (otherCount != null) total += otherCount.intValue();
        if (s.get同居＿子の人数() != null) total += s.get同居＿子の人数().intValue();
        t.set同居予定家族＿合計人数(new java.math.BigDecimal(total));

        t.set婚姻区分(s.get婚姻区分());
        t.set商品分類(s.get申込審区分());
        t.set外部連携受付番号(s.get外部連携受付番号());
        t.set勤務先資本金＿外部ローン(s.get勤務先資本金());
        t.set土地契約予定日(s.get土地契約予定日());

        t.set預金＿金融機関1＿名称(s.get預金＿金融機関名1());
        t.set預金＿金融機関1＿本人預金(s.get預金＿本人預金1());
        t.set預金＿金融機関1＿家族預金(s.get預金＿家族預金1());
        t.set預金＿金融機関2＿名称(s.get預金＿金融機関名2());
        t.set預金＿金融機関2＿本人預金(s.get預金＿本人預金2());
        t.set預金＿金融機関2＿家族預金(s.get預金＿家族預金2());
        t.set預金＿金融機関3＿本人預金(s.get預金＿本人預金3());
        t.set預金＿金融機関3＿家族預金(s.get預金＿家族預金3());
        t.set預金＿金融機関4＿本人預金(s.get預金＿本人預金4());
        t.set預金＿金融機関4＿家族預金(s.get預金＿家族預金4());

        t.set歩合給(s.get勤務先歩合給区分());

        // From 申込ワイド
        t.set国家資格(s.get国家資格());
        t.set国家資格＿その他(s.get国家資格子の他());
        t.set配偶者年収(s.get配偶者年収());

        // 資金使途 derived columns — require 編集仕様詳細 (code tables 2332/2333/2334)
        t.set資金使途＿マンション(null);
        t.set資金使途＿マンション以外(null);
        t.set資金使途＿ワイドローン一般口(null);
        t.set資金使途＿物件種別(null);

        // 必要資金
        t.set必要資金＿土地(s.get必要資金＿土地());
        t.set必要資金＿建物(s.get必要資金＿建物());
        t.set必要資金＿借替(s.get必要資金＿借替());
        t.set必要資金＿諸費用(s.get必要資金＿諸費用());
        t.set必要資金＿その他(s.get必要資金＿その他());
        t.set必要資金＿合計(s.get必要資金＿合計());
        // 調達＿金融機関
        t.set調達＿金融機関1＿名称(s.get調達＿その他1＿借入先());
        t.set調達＿金融機関1＿金額(s.get調達＿その他1());
        t.set調達＿金融機関1＿期間(s.get調達＿その他1＿期間());
        t.set調達＿金融機関2＿名称(s.get調達＿その他2＿借入先());
        t.set調達＿金融機関2＿金額(s.get調達＿その他2());
        t.set調達＿金融機関2＿期間(s.get調達＿その他2＿期間());
        t.set調達＿合計(s.get調達＿合計());
        // 自己資金
        t.set自己資金＿預貯金(s.get自己資金＿預貯金());
        t.set自己資金＿その他(s.get自己資金＿その他());
        t.set自己資金＿贈与(s.get自己資金＿贈与());
        t.set給与振込(s.get勤務先給与振込());
        t.set税込年収＿前々年(s.get年収２());
        t.set税込年収＿３年前(s.get年収３());
    }

}
