package migration.service;

import migration.domain.source.申込Source;
import migration.domain.source.申込審査段階Source;
import migration.domain.source.申込進捗Source;
import migration.domain.source.申込担保回答ＰＤＦSource;
import migration.domain.source.申込審査履歴Source;
import migration.domain.source.申込関連申込Source;
import migration.domain.target.申込関連申込Target;
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
import migration.domain.target.申込担保情報ＰＤＦTarget;
import migration.domain.target.申込審査履歴Target;
import migration.domain.source.保証検討表補足Source;
import migration.mapper.source.申込SourceMapper;
import migration.mapper.source.申込審査段階SourceMapper;
import migration.mapper.source.申込進捗SourceMapper;
import migration.mapper.source.保証検討表補足SourceMapper;
import migration.mapper.source.保証人SourceMapper;
import migration.mapper.source.申込担保回答ＰＤＦSourceMapper;
import migration.mapper.source.申込審査履歴SourceMapper;
import migration.mapper.source.申込関連申込SourceMapper;
import migration.mapper.target.申込関連申込TargetMapper;
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
import migration.mapper.target.申込担保情報ＰＤＦTargetMapper;
import migration.mapper.target.申込審査履歴TargetMapper;
import migration.domain.source.審査チェック照会Source;
import migration.domain.source.審査ＫＳＣ照会Source;
import migration.domain.source.審査ＫＳＣ信用情報Source;
import migration.domain.source.審査ＫＳＣ信用情報明細Source;
import migration.domain.source.審査ＫＳＣ信用情報詳細Source;
import migration.domain.source.担保評価回答Source;
import migration.domain.source.担保評価連携結果ファイルSource;
import migration.domain.target.審査チェック照会Target;
import migration.domain.target.審査ＫＳＣ照会Target;
import migration.domain.target.審査ＫＳＣ信用情報Target;
import migration.domain.target.審査ＫＳＣ信用情報明細Target;
import migration.domain.target.審査ＫＳＣ信用情報詳細Target;
import migration.domain.target.ＩＦ＿担保評価連携結果Target;
import migration.domain.target.ＩＦ＿担保評価連携結果＿ファイルTarget;
import migration.mapper.source.審査チェック照会SourceMapper;
import migration.mapper.source.審査ＫＳＣ照会SourceMapper;
import migration.mapper.source.審査ＫＳＣ信用情報SourceMapper;
import migration.mapper.source.審査ＫＳＣ信用情報明細SourceMapper;
import migration.mapper.source.審査ＫＳＣ信用情報詳細SourceMapper;
import migration.mapper.source.担保評価回答SourceMapper;
import migration.mapper.source.担保評価連携結果ファイルSourceMapper;
import migration.mapper.target.審査チェック照会TargetMapper;
import migration.mapper.target.審査ＫＳＣ照会TargetMapper;
import migration.mapper.target.審査ＫＳＣ信用情報TargetMapper;
import migration.mapper.target.審査ＫＳＣ信用情報明細TargetMapper;
import migration.mapper.target.審査ＫＳＣ信用情報詳細TargetMapper;
import migration.mapper.target.ＩＦ＿担保評価連携結果TargetMapper;
import migration.mapper.target.ＩＦ＿担保評価連携結果＿ファイルTargetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JutakuLoanService {

    // --- Source mappers (legacy schema reads) ---
    @Autowired private 申込進捗SourceMapper applicationProgressSourceMapper;
    @Autowired private 申込審査段階SourceMapper reviewStageSourceMapper;
    @Autowired private 申込SourceMapper applicationSourceMapper;
    @Autowired private 保証人SourceMapper guarantorSourceMapper;
    @Autowired private 保証検討表補足SourceMapper guaranteeReviewSupplementSourceMapper;
    @Autowired private 申込担保回答ＰＤＦSourceMapper collateralAnswerPdfSourceMapper;
    @Autowired private 申込審査履歴SourceMapper reviewHistorySourceMapper;
    @Autowired private 申込関連申込SourceMapper relatedApplicationSourceMapper;
    @Autowired private 審査チェック照会SourceMapper reviewCheckSourceMapper;
    @Autowired private 審査ＫＳＣ照会SourceMapper reviewKscSourceMapper;
    @Autowired private 審査ＫＳＣ信用情報SourceMapper reviewKscCreditSourceMapper;
    @Autowired private 審査ＫＳＣ信用情報明細SourceMapper reviewKscCreditLineSourceMapper;
    @Autowired private 審査ＫＳＣ信用情報詳細SourceMapper reviewKscCreditDetailSourceMapper;
    @Autowired private 担保評価回答SourceMapper collateralValuationSourceMapper;
    @Autowired private 担保評価連携結果ファイルSourceMapper collateralValuationFileSourceMapper;

    // --- Target mappers (new schema writes) ---
    @Autowired private 申込進捗TargetMapper applicationProgressTargetMapper;
    @Autowired private 申込審査段階TargetMapper reviewStageTargetMapper;
    @Autowired private 申込審査状況TargetMapper reviewStatusTargetMapper;
    @Autowired private 履歴申込審査段階TargetMapper historyReviewStageTargetMapper;
    @Autowired private 申込TargetMapper applicationTargetMapper;
    @Autowired private 履歴申込TargetMapper historyApplicationTargetMapper;
    @Autowired private 履歴申込_業者_住宅TargetMapper historyApplicationVendorHousingTargetMapper;
    @Autowired private 保証人TargetMapper guarantorTargetMapper;
    @Autowired private 申込_業者_住宅TargetMapper applicationVendorHousingTargetMapper;
    @Autowired private 保証検討表補足TargetMapper guaranteeReviewSupplementTargetMapper;
    @Autowired private 履歴保証人TargetMapper historyGuarantorTargetMapper;
    @Autowired private 履歴保証検討表補足TargetMapper historyGuaranteeReviewSupplementTargetMapper;
    @Autowired private 申込担保情報ＰＤＦTargetMapper collateralInfoPdfTargetMapper;
    @Autowired private 申込審査履歴TargetMapper reviewHistoryTargetMapper;
    @Autowired private 申込関連申込TargetMapper relatedApplicationTargetMapper;
    @Autowired private 審査チェック照会TargetMapper reviewCheckTargetMapper;
    @Autowired private 審査ＫＳＣ照会TargetMapper reviewKscTargetMapper;
    @Autowired private 審査ＫＳＣ信用情報TargetMapper reviewKscCreditTargetMapper;
    @Autowired private 審査ＫＳＣ信用情報明細TargetMapper reviewKscCreditLineTargetMapper;
    @Autowired private 審査ＫＳＣ信用情報詳細TargetMapper reviewKscCreditDetailTargetMapper;
    @Autowired private ＩＦ＿担保評価連携結果TargetMapper collateralValuationResultTargetMapper;
    @Autowired private ＩＦ＿担保評価連携結果＿ファイルTargetMapper collateralValuationFileTargetMapper;

    @Value("${migration.simulate:false}")
    private boolean simulate;

    @Value("${migration.simulate-sleep-ms:500}")
    private long simulateSleepMs;

    // Source 申込目的 codes for the preliminary review group, sorted ascending (10, 15).
    private static final List<String> PRELIMINARY_REVIEW_PURPOSE_CODES = Arrays.asList("10", "15");
    // Source 申込目的 codes for the formal review group, sorted ascending (20, 30).
    private static final List<String> FORMAL_REVIEW_PURPOSE_CODES = Arrays.asList("20", "30");

    // Converted target 申込目的 for each group.
    private static final String TARGET_PURPOSE_PRELIMINARY = "10";
    private static final String TARGET_PURPOSE_FORMAL = "20";

    // Fixed 一連番号 for the ＩＦ担保評価連携結果 tables: the source has no 一連番号,
    // so this value fills the new-system key (編集仕様詳細).
    private static final String FIXED_SEQUENCE_NUMBER = "99999";

    // Entry point called by 移行管理Tasklet for each claimed row range.
    // The driving table is 申込進捗 (one row per 申込番号); fromRowNumber/toRowNumber are the
    // inclusive ROW_NUMBER bounds of the range.
    @Transactional
    public void processOneRange(long fromRowNumber, long toRowNumber) {
        if (simulate) {
            log.info("  [SIMULATE] Processing range {} ~ {} (sleep {}ms)", fromRowNumber, toRowNumber, simulateSleepMs);
            try {
                Thread.sleep(simulateSleepMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            log.info("  [SIMULATE] Done range {} ~ {}", fromRowNumber, toRowNumber);
            return;
        }

        log.info("Processing range: {} ~ {}", fromRowNumber, toRowNumber);

        List<申込進捗Source> progressRecords = applicationProgressSourceMapper.selectByRowRange(fromRowNumber, toRowNumber);

        int migratedCount = 0;
        int skippedCount = 0;

        for (申込進捗Source progress : progressRecords) {
            try {
                boolean migrated = migrateSingleApplication(progress);
                if (migrated) {
                    migratedCount++;
                } else {
                    skippedCount++;
                }
            } catch (Exception e) {
                log.error("ERROR processing 申込番号={}: {}", progress.get申込番号(), e.getMessage());
                throw e;
            }
        }

        log.info("Range completed: Processed={}, Skipped={}", migratedCount, skippedCount);
    }

    // Migrates every target table for a single application.
    // Returns true if at least one review group was migrated, false if the application
    // was skipped because it has no completed review stage.
    private boolean migrateSingleApplication(申込進捗Source progress) {
        String sourceApplicationNumber = progress.get申込番号();
        String targetApplicationNumber = convertApplicationNumber(sourceApplicationNumber);

        List<申込審査段階Source> allReviewStages =
                reviewStageSourceMapper.selectByApplicationId(sourceApplicationNumber);
        List<申込審査段階Source> completedReviewStages = allReviewStages.stream()
                .filter(stage -> "1".equals(stage.get審査完了区分()))
                .sorted(Comparator.comparing(申込審査段階Source::get申込目的))
                .collect(Collectors.toList());

        if (completedReviewStages.isEmpty()) {
            log.info("SKIP: 申込番号={} - no completed 審査段階", sourceApplicationNumber);
            return false;
        }

        // 申込進捗: 1:1 copy. 進捗コード/状態 are NOT NULL in the target, so they always pass through.
        申込進捗Target progressTarget = new 申込進捗Target();
        progressTarget.set申込番号(targetApplicationNumber);
        progressTarget.set進捗コード(convertProgressCode(progress.get進捗コード()));
        progressTarget.set状態(progress.get状態());
        progressTarget.set進捗移動日時(progress.get進捗移動日時());
        progressTarget.set表示形式(progress.get表示形式());
        progressTarget.set優先度(progress.get優先度());
        progressTarget.setコメント(progress.getコメント());
        progressTarget.set前進捗コード(progress.get前進捗コード());
        progressTarget.set進捗移動担当者コード(progress.get進捗移動担当者コード());
        applicationProgressTargetMapper.insert(progressTarget);

        // Preliminary review group (申込目的: 10, 15 → target '10')
        List<申込審査段階Source> preliminaryStages = completedReviewStages.stream()
                .filter(stage -> PRELIMINARY_REVIEW_PURPOSE_CODES.contains(stage.get申込目的()))
                .collect(Collectors.toList());

        // Formal review group (申込目的: 20, 30 → target '20')
        List<申込審査段階Source> formalStages = completedReviewStages.stream()
                .filter(stage -> FORMAL_REVIEW_PURPOSE_CODES.contains(stage.get申込目的()))
                .collect(Collectors.toList());

        migrateReviewGroup(sourceApplicationNumber, targetApplicationNumber, preliminaryStages, TARGET_PURPOSE_PRELIMINARY);
        migrateReviewGroup(sourceApplicationNumber, targetApplicationNumber, formalStages, TARGET_PURPOSE_FORMAL);

        // 申込関連申込: 1:N per 申込番号 (no 申込目的). Both application numbers are converted (2→3).
        // Inserted after the 申込 rows exist because of the FK to 申込.
        for (申込関連申込Source relatedApplication : relatedApplicationSourceMapper.selectByApplicationId(sourceApplicationNumber)) {
            申込関連申込Target relatedApplicationTarget = new 申込関連申込Target();
            relatedApplicationTarget.set申込番号(targetApplicationNumber);
            relatedApplicationTarget.set関連区分(relatedApplication.get関連区分());
            relatedApplicationTarget.set関連申込番号(convertApplicationNumber(relatedApplication.get関連申込番号()));
            relatedApplicationTargetMapper.insert(relatedApplicationTarget);
        }

        log.debug("Migrated 申込番号={} → {} (preliminary={}, formal={})",
                sourceApplicationNumber, targetApplicationNumber, preliminaryStages.size(), formalStages.size());
        return true;
    }

    // Inserts every target table for one review group (preliminary or formal).
    //
    // Main tables use only the record with the MAX source 申込目的:
    //   申込, 申込_業者_住宅, 申込審査段階, 保証人, 保証検討表補足,
    //   申込担保情報ＰＤＦ (1:N per application+purpose), 申込審査履歴 (1:N event log).
    //
    // History tables receive every completed record, numbered by 回数 = 1..N ascending:
    //   申込審査状況, 履歴申込, 履歴申込_業者_住宅, 履歴申込審査段階, 履歴保証人, 履歴保証検討表補足.
    //
    // reviewStages is pre-sorted by 申込目的 ascending, so the last element carries the MAX
    // purpose and the highest 回数. convertedPurpose is the target 申込目的 ('10' or '20').
    private void migrateReviewGroup(String sourceApplicationNumber, String targetApplicationNumber,
                                    List<申込審査段階Source> reviewStages, String convertedPurpose) {
        if (reviewStages.isEmpty()) {
            return;
        }

        // The MAX source 申込目的 is the last element of the ascending-sorted list.
        String maxSourcePurpose = reviewStages.get(reviewStages.size() - 1).get申込目的();

        // ① 申込 main (MAX only) — inserted FIRST because 申込審査段階/保証人 both FK to 申込.
        申込Source sourceApplication =
                applicationSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose);
        if (sourceApplication != null) {
            申込Target applicationTarget = new 申込Target();
            applicationTarget.set申込番号(targetApplicationNumber);
            applicationTarget.set申込目的(convertedPurpose);
            mapApplicationColumns(sourceApplication, applicationTarget);
            applicationTargetMapper.insert(applicationTarget);

            // ①-a 申込_業者_住宅 main (MAX only) — FK to 申込.
            申込_業者_住宅Target vendorHousingTarget = new 申込_業者_住宅Target();
            vendorHousingTarget.set申込番号(targetApplicationNumber);
            vendorHousingTarget.set申込目的(convertedPurpose);
            applicationVendorHousingTargetMapper.insert(vendorHousingTarget);
        }

        // ② 申込審査段階 main (MAX only) — FK to 申込.
        申込審査段階Target reviewStageTarget = new 申込審査段階Target();
        reviewStageTarget.set申込番号(targetApplicationNumber);
        reviewStageTarget.set申込目的(convertedPurpose);
        reviewStageTarget.set審査完了区分(reviewStages.get(reviewStages.size() - 1).get審査完了区分());
        reviewStageTargetMapper.insert(reviewStageTarget);

        // ③ 保証人 main (MAX only) — FK to 申込.
        List<保証人Source> mainGuarantors =
                guarantorSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose);
        for (保証人Source guarantor : mainGuarantors) {
            保証人Target guarantorTarget = new 保証人Target();
            guarantorTarget.set申込番号(targetApplicationNumber);
            guarantorTarget.set申込目的(convertedPurpose);
            guarantorTarget.set連番(guarantor.get連番());
            guarantorTargetMapper.insert(guarantorTarget);
        }

        // ③-a 保証検討表補足 main (MAX only) — FK to 申込.
        保証検討表補足Source mainGuaranteeSupplement =
                guaranteeReviewSupplementSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose);
        if (mainGuaranteeSupplement != null) {
            保証検討表補足Target guaranteeSupplementTarget = new 保証検討表補足Target();
            guaranteeSupplementTarget.set申込番号(targetApplicationNumber);
            guaranteeSupplementTarget.set申込目的(convertedPurpose);
            guaranteeReviewSupplementTargetMapper.insert(guaranteeSupplementTarget);
        }

        // ③-b 申込担保情報ＰＤＦ (MAX only) — FK to 申込, 1:N per application + purpose, loaded from 申込担保回答ＰＤＦ.
        // ファイル種別 passes through from ファイル種類; the single source file-name feeds both target file-name columns.
        List<申込担保回答ＰＤＦSource> collateralPdfs =
                collateralAnswerPdfSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose);
        for (申込担保回答ＰＤＦSource collateralPdf : collateralPdfs) {
            申込担保情報ＰＤＦTarget collateralPdfTarget = new 申込担保情報ＰＤＦTarget();
            collateralPdfTarget.set申込番号(targetApplicationNumber);
            collateralPdfTarget.set申込目的(convertedPurpose);
            collateralPdfTarget.setファイル種別(collateralPdf.getファイル種類());
            collateralPdfTarget.setファイル名称(collateralPdf.getファイル名());
            collateralPdfTarget.setデータファイル名(collateralPdf.getファイル名());
            collateralInfoPdfTargetMapper.insert(collateralPdfTarget);
        }

        // ③-c 申込審査履歴 event log (MAX only) — FK to 申込, 1:N per (申込番号, 申込目的).
        // 進捗コード is converted via the 編集仕様詳細 code table; other columns pass through with the source-provided 回数.
        List<申込審査履歴Source> reviewHistories =
                reviewHistorySourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose);
        for (申込審査履歴Source reviewHistory : reviewHistories) {
            申込審査履歴Target reviewHistoryTarget = new 申込審査履歴Target();
            reviewHistoryTarget.set申込番号(targetApplicationNumber);
            reviewHistoryTarget.set申込目的(convertedPurpose);
            reviewHistoryTarget.setイベント(reviewHistory.getイベント());
            reviewHistoryTarget.setイベント日時(reviewHistory.getイベント日時());
            reviewHistoryTarget.set進捗コード(convertProgressCode(reviewHistory.get進捗コード()));
            reviewHistoryTarget.setユーザID(reviewHistory.getユーザID());
            reviewHistoryTarget.setユーザ名(reviewHistory.getユーザ名());
            reviewHistoryTarget.set回数(reviewHistory.get回数());
            reviewHistoryTargetMapper.insert(reviewHistoryTarget);
        }

        // ③-d 審査チェック照会 (MAX only) — 1:N event log per (申込番号, 申込目的).
        // 一連番号 fixed '99999'; other columns pass through from source.
        List<審査チェック照会Source> reviewChecks =
                reviewCheckSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose);
        for (審査チェック照会Source reviewCheck : reviewChecks) {
            審査チェック照会Target reviewCheckTarget = new 審査チェック照会Target();
            reviewCheckTarget.set申込番号(targetApplicationNumber);
            reviewCheckTarget.set申込目的(convertedPurpose);
            reviewCheckTarget.setイベント(reviewCheck.getイベント());
            reviewCheckTarget.setイベント日時(reviewCheck.getイベント日時());
            reviewCheckTarget.set状態(reviewCheck.get状態());
            reviewCheckTarget.set状態説明(reviewCheck.get状態説明());
            reviewCheckTarget.set優先度(reviewCheck.get優先度());
            reviewCheckTargetMapper.insert(reviewCheckTarget);
        }

        // ③-d2 審査ＫＳＣ照会 (MAX only) — 1:N event log per (申込番号, 申込目的).
        // 申込番号 2→3 and 申込目的 converted; other columns pass through from source.
        List<審査ＫＳＣ照会Source> reviewKscs =
                reviewKscSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose);
        for (審査ＫＳＣ照会Source reviewKsc : reviewKscs) {
            審査ＫＳＣ照会Target reviewKscTarget = new 審査ＫＳＣ照会Target();
            reviewKscTarget.set申込番号(targetApplicationNumber);
            reviewKscTarget.set申込目的(convertedPurpose);
            reviewKscTarget.setイベント(reviewKsc.getイベント());
            reviewKscTarget.setイベント日時(reviewKsc.getイベント日時());
            reviewKscTarget.set連番(reviewKsc.get連番());
            reviewKscTarget.set別名連番(reviewKsc.get別名連番());
            reviewKscTarget.set受付日時(reviewKsc.get受付日時());
            reviewKscTarget.set受付番号(reviewKsc.get受付番号());
            reviewKscTarget.setコメント(reviewKsc.getコメント());
            reviewKscTargetMapper.insert(reviewKscTarget);
        }

        // ③-d3 審査ＫＳＣ信用情報 (MAX only) — 1:N per (申込番号, 申込目的).
        // Only columns present in both source and target are copied; target-only
        // columns (ＫＳＣグレー, ＫＳＣ延滞, ＫＳＣ転居歴, etc.) are left null.
        List<審査ＫＳＣ信用情報Source> reviewKscCredits =
                reviewKscCreditSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose);
        for (審査ＫＳＣ信用情報Source reviewKscCredit : reviewKscCredits) {
            審査ＫＳＣ信用情報Target reviewKscCreditTarget = new 審査ＫＳＣ信用情報Target();
            reviewKscCreditTarget.set申込番号(targetApplicationNumber);
            reviewKscCreditTarget.set申込目的(convertedPurpose);
            reviewKscCreditTarget.setイベント(reviewKscCredit.getイベント());
            reviewKscCreditTarget.setイベント日時(reviewKscCredit.getイベント日時());
            reviewKscCreditTarget.set連番(reviewKscCredit.get連番());
            reviewKscCreditTarget.setＫＳＣブラック(reviewKscCredit.getＫＳＣブラック());
            reviewKscCreditTarget.setＫＳＣ経由ＣＩＣブラック(reviewKscCredit.getＫＳＣ経由ＣＩＣブラック());
            reviewKscCreditTarget.setＫＳＣ経由ＪＩＣブラック(reviewKscCredit.getＫＳＣ経由ＪＩＣブラック());
            reviewKscCreditTarget.setＫＳＣ照会件数(reviewKscCredit.getＫＳＣ照会件数());
            reviewKscCreditTarget.setＫＳＣ契約件数(reviewKscCredit.getＫＳＣ契約件数());
            reviewKscCreditTarget.setＫＳＣ極度額オーバー(reviewKscCredit.getＫＳＣ極度額オーバー());
            reviewKscCreditTarget.setＫＳＣレコード数(reviewKscCredit.getＫＳＣレコード数());
            reviewKscCreditTargetMapper.insert(reviewKscCreditTarget);
        }

        // ③-d4 審査ＫＳＣ信用情報明細 (MAX only) — 1:N per (申込番号, 申込目的), pass-through.
        List<審査ＫＳＣ信用情報明細Source> reviewKscCreditLines =
                reviewKscCreditLineSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose);
        for (審査ＫＳＣ信用情報明細Source reviewKscCreditLine : reviewKscCreditLines) {
            審査ＫＳＣ信用情報明細Target reviewKscCreditLineTarget = new 審査ＫＳＣ信用情報明細Target();
            reviewKscCreditLineTarget.set申込番号(targetApplicationNumber);
            reviewKscCreditLineTarget.set申込目的(convertedPurpose);
            reviewKscCreditLineTarget.setイベント(reviewKscCreditLine.getイベント());
            reviewKscCreditLineTarget.setイベント日時(reviewKscCreditLine.getイベント日時());
            reviewKscCreditLineTarget.set連番(reviewKscCreditLine.get連番());
            reviewKscCreditLineTarget.set受付日時(reviewKscCreditLine.get受付日時());
            reviewKscCreditLineTarget.set受付番号(reviewKscCreditLine.get受付番号());
            reviewKscCreditLineTarget.setテーブル名(reviewKscCreditLine.getテーブル名());
            reviewKscCreditLineTarget.set項目名(reviewKscCreditLine.get項目名());
            reviewKscCreditLineTarget.setコード番号(reviewKscCreditLine.getコード番号());
            reviewKscCreditLineTarget.setコード(reviewKscCreditLine.getコード());
            reviewKscCreditLineTarget.setコード名称(reviewKscCreditLine.getコード名称());
            reviewKscCreditLineTarget.setブラック判断(reviewKscCreditLine.getブラック判断());
            reviewKscCreditLineTargetMapper.insert(reviewKscCreditLineTarget);
        }

        // ③-d5 審査ＫＳＣ信用情報詳細 (MAX only) — 1:N per (申込番号, 申込目的), pass-through.
        // Target-only column 延滞回数 has no source and is left null.
        List<審査ＫＳＣ信用情報詳細Source> reviewKscCreditDetails =
                reviewKscCreditDetailSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose);
        for (審査ＫＳＣ信用情報詳細Source reviewKscCreditDetail : reviewKscCreditDetails) {
            審査ＫＳＣ信用情報詳細Target reviewKscCreditDetailTarget = new 審査ＫＳＣ信用情報詳細Target();
            reviewKscCreditDetailTarget.set申込番号(targetApplicationNumber);
            reviewKscCreditDetailTarget.set申込目的(convertedPurpose);
            reviewKscCreditDetailTarget.setイベント(reviewKscCreditDetail.getイベント());
            reviewKscCreditDetailTarget.setイベント日時(reviewKscCreditDetail.getイベント日時());
            reviewKscCreditDetailTarget.set連番(reviewKscCreditDetail.get連番());
            reviewKscCreditDetailTarget.set別名連番(reviewKscCreditDetail.get別名連番());
            reviewKscCreditDetailTarget.set詳細連番(reviewKscCreditDetail.get詳細連番());
            reviewKscCreditDetailTarget.set受付日時(reviewKscCreditDetail.get受付日時());
            reviewKscCreditDetailTarget.set受付番号(reviewKscCreditDetail.get受付番号());
            reviewKscCreditDetailTarget.set該当者通番(reviewKscCreditDetail.get該当者通番());
            reviewKscCreditDetailTarget.setテーブル名(reviewKscCreditDetail.getテーブル名());
            reviewKscCreditDetailTarget.set項目名(reviewKscCreditDetail.get項目名());
            reviewKscCreditDetailTarget.setコード番号(reviewKscCreditDetail.getコード番号());
            reviewKscCreditDetailTarget.setコード(reviewKscCreditDetail.getコード());
            reviewKscCreditDetailTarget.setコード名称(reviewKscCreditDetail.getコード名称());
            reviewKscCreditDetailTarget.set氏名(reviewKscCreditDetail.get氏名());
            reviewKscCreditDetailTarget.set氏名カナ(reviewKscCreditDetail.get氏名カナ());
            reviewKscCreditDetailTarget.set種類(reviewKscCreditDetail.get種類());
            reviewKscCreditDetailTarget.set信用情報判断(reviewKscCreditDetail.get信用情報判断());
            reviewKscCreditDetailTarget.set信用情報(reviewKscCreditDetail.get信用情報());
            reviewKscCreditDetailTarget.set判断項目名1(reviewKscCreditDetail.get判断項目名1());
            reviewKscCreditDetailTarget.set判断項目1(reviewKscCreditDetail.get判断項目1());
            reviewKscCreditDetailTarget.set判断項目名2(reviewKscCreditDetail.get判断項目名2());
            reviewKscCreditDetailTarget.set判断項目2(reviewKscCreditDetail.get判断項目2());
            reviewKscCreditDetailTarget.set判断項目名3(reviewKscCreditDetail.get判断項目名3());
            reviewKscCreditDetailTarget.set判断項目3(reviewKscCreditDetail.get判断項目3());
            reviewKscCreditDetailTarget.set判断項目名4(reviewKscCreditDetail.get判断項目名4());
            reviewKscCreditDetailTarget.set判断項目4(reviewKscCreditDetail.get判断項目4());
            reviewKscCreditDetailTarget.set判断項目名5(reviewKscCreditDetail.get判断項目名5());
            reviewKscCreditDetailTarget.set判断項目5(reviewKscCreditDetail.get判断項目5());
            reviewKscCreditDetailTarget.setブラック判断(reviewKscCreditDetail.getブラック判断());
            reviewKscCreditDetailTarget.set発生日(reviewKscCreditDetail.get発生日());
            reviewKscCreditDetailTarget.set契約日(reviewKscCreditDetail.get契約日());
            reviewKscCreditDetailTargetMapper.insert(reviewKscCreditDetailTarget);
        }

        // ③-e ＩＦ＿担保評価連携結果 (MAX only) — 1:N per (申込番号, 申込目的) from 担保評価回答.
        // 一連番号 fixed '99999'; valuation columns pass through.
        List<担保評価回答Source> collateralValuations =
                collateralValuationSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose);
        for (担保評価回答Source collateralValuation : collateralValuations) {
            ＩＦ＿担保評価連携結果Target valuationTarget = new ＩＦ＿担保評価連携結果Target();
            valuationTarget.set申込番号(targetApplicationNumber);
            valuationTarget.set申込目的(convertedPurpose);
            valuationTarget.set一連番号(FIXED_SEQUENCE_NUMBER);
            valuationTarget.setイベント(collateralValuation.getイベント());
            valuationTarget.setイベント日時(collateralValuation.getイベント日時());
            valuationTarget.set簡易評価額(collateralValuation.get簡易評価額());
            valuationTarget.set簡易土地評価額(collateralValuation.get簡易土地評価額());
            valuationTarget.set簡易建物評価額(collateralValuation.get簡易建物評価額());
            valuationTarget.set土地特記事項(collateralValuation.get土地特記事項());
            valuationTarget.set建物特記事項(collateralValuation.get建物特記事項());
            valuationTarget.set先順位控除額(collateralValuation.get先順位控除額());
            collateralValuationResultTargetMapper.insert(valuationTarget);
        }

        // ③-f ＩＦ＿担保評価連携結果＿ファイル (MAX only) — 担保評価回答 joined with 申込担保回答ＰＤＦ.
        // 一連番号 fixed '99999'; ファイル種別 <- ファイル種類.
        List<担保評価連携結果ファイルSource> valuationFiles =
                collateralValuationFileSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose);
        for (担保評価連携結果ファイルSource valuationFile : valuationFiles) {
            ＩＦ＿担保評価連携結果＿ファイルTarget valuationFileTarget = new ＩＦ＿担保評価連携結果＿ファイルTarget();
            valuationFileTarget.set申込番号(targetApplicationNumber);
            valuationFileTarget.set申込目的(convertedPurpose);
            valuationFileTarget.set一連番号(FIXED_SEQUENCE_NUMBER);
            valuationFileTarget.setイベント(valuationFile.getイベント());
            valuationFileTarget.setイベント日時(valuationFile.getイベント日時());
            valuationFileTarget.setファイル種別(valuationFile.getファイル種別());
            valuationFileTarget.setファイル名称(valuationFile.getファイル名称());
            valuationFileTarget.setデータファイル名(valuationFile.getデータファイル名());
            collateralValuationFileTargetMapper.insert(valuationFileTarget);
        }

        // ④-⑧ History rows for every completed record in this group.
        int occurrenceNumber = 1;
        for (申込審査段階Source stage : reviewStages) {
            String sourcePurpose = stage.get申込目的();

            // ④ 申込審査状況 — FK to 申込.
            申込審査状況Target reviewStatusTarget = new 申込審査状況Target();
            reviewStatusTarget.set申込番号(targetApplicationNumber);
            reviewStatusTarget.set申込目的(convertedPurpose);
            reviewStatusTarget.set回数(occurrenceNumber);
            reviewStatusTargetMapper.insert(reviewStatusTarget);

            // ⑤ 履歴申込 — FK to 申込審査状況 (must precede 履歴申込審査段階/履歴保証人).
            申込Source historyApplicationSource =
                    applicationSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, sourcePurpose);
            if (historyApplicationSource != null) {
                履歴申込Target historyApplicationTarget = new 履歴申込Target();
                historyApplicationTarget.set申込番号(targetApplicationNumber);
                historyApplicationTarget.set申込目的(convertedPurpose);
                historyApplicationTarget.set回数(occurrenceNumber);
                historyApplicationTargetMapper.insert(historyApplicationTarget);

                // ⑤-a 履歴申込_業者_住宅 — FK to 履歴申込.
                履歴申込_業者_住宅Target historyVendorHousingTarget = new 履歴申込_業者_住宅Target();
                historyVendorHousingTarget.set申込番号(targetApplicationNumber);
                historyVendorHousingTarget.set申込目的(convertedPurpose);
                historyVendorHousingTarget.set回数(occurrenceNumber);
                historyApplicationVendorHousingTargetMapper.insert(historyVendorHousingTarget);
            }

            // ⑥ 履歴申込審査段階 — FK to 履歴申込.
            履歴申込審査段階Target historyReviewStageTarget = new 履歴申込審査段階Target();
            historyReviewStageTarget.set申込番号(targetApplicationNumber);
            historyReviewStageTarget.set申込目的(convertedPurpose);
            historyReviewStageTarget.set回数(occurrenceNumber);
            historyReviewStageTarget.set審査完了区分(stage.get審査完了区分());
            historyReviewStageTargetMapper.insert(historyReviewStageTarget);

            // ⑦ 履歴保証人 — FK to 履歴申込.
            List<保証人Source> historyGuarantors =
                    guarantorSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, sourcePurpose);
            for (保証人Source guarantor : historyGuarantors) {
                履歴保証人Target historyGuarantorTarget = new 履歴保証人Target();
                historyGuarantorTarget.set申込番号(targetApplicationNumber);
                historyGuarantorTarget.set申込目的(convertedPurpose);
                historyGuarantorTarget.set回数(occurrenceNumber);
                historyGuarantorTarget.set連番(guarantor.get連番());
                historyGuarantorTargetMapper.insert(historyGuarantorTarget);
            }

            // ⑧ 履歴保証検討表補足 — FK to 履歴申込.
            保証検討表補足Source historyGuaranteeSupplement =
                    guaranteeReviewSupplementSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, sourcePurpose);
            if (historyGuaranteeSupplement != null) {
                履歴保証検討表補足Target historyGuaranteeSupplementTarget = new 履歴保証検討表補足Target();
                historyGuaranteeSupplementTarget.set申込番号(targetApplicationNumber);
                historyGuaranteeSupplementTarget.set申込目的(convertedPurpose);
                historyGuaranteeSupplementTarget.set回数(occurrenceNumber);
                historyGuaranteeReviewSupplementTargetMapper.insert(historyGuaranteeSupplementTarget);
            }

            occurrenceNumber++;
        }
    }

    // ------------------------------------------------------------
    // Utility helpers
    // ------------------------------------------------------------

    // Converts a 申込番号 by replacing its first digit '2' with '3'.
    // Example: "202606017001" becomes "302606017001".
    private String convertApplicationNumber(String sourceApplicationNumber) {
        if (sourceApplicationNumber == null || sourceApplicationNumber.isEmpty()) {
            return sourceApplicationNumber;
        }
        return "3" + sourceApplicationNumber.substring(1);
    }

    // Truncates a value so it fits within maxBytes bytes in MS932 (Shift-JIS) encoding.
    // Use for VARCHAR2(N) columns where N is the BYTE limit in a Shift-JIS Oracle database.
    // Null-safe: returns null for a null input.
    private static String truncateToByteLimit(String value, int maxBytes) {
        if (value == null) {
            return null;
        }
        try {
            if (value.getBytes("MS932").length <= maxBytes) {
                return value;
            }
            int length = value.length();
            while (length > 0 && value.substring(0, length).getBytes("MS932").length > maxBytes) {
                length--;
            }
            return value.substring(0, length);
        } catch (java.io.UnsupportedEncodingException e) {
            return value.length() > maxBytes ? value.substring(0, maxBytes) : value;
        }
    }

    // Splits a full name into [surname, givenName] on the first delimiter.
    // Returns [full, null] when there is no delimiter, and [null, null] for a null input.
    private static String[] splitFullName(String fullName, String delimiter) {
        if (fullName == null) {
            return new String[]{null, null};
        }
        int delimiterIndex = fullName.indexOf(delimiter);
        if (delimiterIndex < 0) {
            return new String[]{fullName, null};
        }
        String surname = fullName.substring(0, delimiterIndex);
        String givenName = fullName.substring(delimiterIndex + delimiter.length());
        return new String[]{surname.isEmpty() ? null : surname, givenName.isEmpty() ? null : givenName};
    }

    // ============================================================
    // 編集仕様詳細 (design-document) code conversions for the 申込 group.
    // Undecided (yellow) source values are returned unchanged and flagged with
    // TODO(編集仕様詳細). Financial codes are never guessed.
    // ============================================================

    /** 商品大分類 (product major category): 1→1, 4→8. 2/3/8 undecided. */
    private static String convertProductMajorCategory(String value) {
        if (value == null) {
            return null;
        }
        switch (value) {
            case "1": return "1";
            case "4": return "8";
            default:  return value; // TODO(編集仕様詳細): 2:ワイド, 3:照会専用, 8:途上与信 undecided
        }
    }

    /** 勤務先企業区分 (employer listing status checkbox): 1→1:上場, 0→2:非上場. */
    private static String convertEmployerCompanyType(String value) {
        if (value == null) {
            return null;
        }
        switch (value) {
            case "1": return "1";
            case "0": return "2";
            default:  return value;
        }
    }

    /**
     * 勤務先職業 → 職業形態 (occupation type).
     * Housing/Wide loans (product major category 1,2) map to the 2080 code set;
     * external loans (4) pass through the 2083 set unchanged.
     */
    private static String convertOccupationType(String productMajorCategory, String value) {
        if (value == null) {
            return null;
        }
        if ("1".equals(productMajorCategory) || "2".equals(productMajorCategory)) {
            switch (value) {
                case "1": return "1";
                case "3": return "4";
                case "4": return "5";
                case "5": return "6";
                case "6": return "7";
                case "7": return "7";
                case "8": return "7";
                case "9": return "8";
                default:  return value; // TODO(編集仕様詳細): 2:公務員 → 2:正規/3:非正規 undecided
            }
        }
        return value; // external loan (4) and others: identity
    }

    /** 住居形態 (residence type): 1→1, 2→4, 3→3, 4→5. 5:その他 undecided. */
    private static String convertResidenceType(String value) {
        if (value == null) {
            return null;
        }
        switch (value) {
            case "1": return "1";
            case "2": return "4";
            case "3": return "3";
            case "4": return "5";
            default:  return value; // TODO(編集仕様詳細): 5:その他 undecided
        }
    }

    /** 借入種類 (borrowing type): 1..4 identity, 5/6→5:収益物件, 7/9→6:その他. */
    private static String convertBorrowingType(String value) {
        if (value == null) {
            return null;
        }
        switch (value) {
            case "1": return "1";
            case "2": return "2";
            case "3": return "3";
            case "4": return "4";
            case "5": return "5";
            case "6": return "5";
            case "7": return "6";
            case "9": return "6";
            default:  return value;
        }
    }

    /** 借入時完済解約予定 (payoff/cancel plan at borrowing): 0:無→2:なし, 1:有→1:あり. */
    private static String convertPayoffPlanAtBorrowing(String value) {
        if (value == null) {
            return null;
        }
        switch (value) {
            case "0": return "2";
            case "1": return "1";
            default:  return value;
        }
    }

    /** 返済方法区分 (repayment method): 1→1, 2→3, 3→2:期日一括. */
    private static String convertRepaymentMethod(String value) {
        if (value == null) {
            return null;
        }
        switch (value) {
            case "1": return "1";
            case "2": return "3";
            case "3": return "2";
            default:  return value;
        }
    }

    /** 金利区分 (interest-rate type): 1→1:変動. 2/3:固定変動ミックス undecided. */
    private static String convertInterestRateType(String value) {
        if (value == null) {
            return null;
        }
        if ("1".equals(value)) {
            return "1";
        }
        return value; // TODO(編集仕様詳細): 2,3:固定変動ミックス型 undecided
    }

    /** 歩合給 (commission pay): 1→1, 2→2, 3:ドライバー→1:あり. */
    private static String convertCommissionPay(String value) {
        if (value == null) {
            return null;
        }
        switch (value) {
            case "1": return "1";
            case "2": return "2";
            case "3": return "1";
            default:  return value;
        }
    }

    /** 国家資格 (national qualification) remap (99:その他→15). */
    private static String convertNationalQualification(String value) {
        if (value == null) {
            return null;
        }
        switch (value) {
            case "1":  return "3";
            case "2":  return "8";
            case "3":  return "2";
            case "4":  return "7";
            case "5":  return "6";
            case "6":  return "10";
            case "7":  return "9";
            case "8":  return "5";
            case "9":  return "13";
            case "10": return "12";
            case "11": return "14";
            case "99": return "15";
            default:   return value;
        }
    }

    /** 同居予定家族＿配偶者 (cohabiting spouse checkbox): 1→2:配偶者同居, 0→1:予定なし. */
    private static String convertCohabitingSpouse(String value) {
        if ("1".equals(value)) {
            return "2";
        }
        if ("0".equals(value)) {
            return "1";
        }
        return value;
    }

    /** 同居予定家族 有無 (cohabiting parent checkbox for 父/母): 1→1:予定あり, 0→2:予定なし. */
    private static String convertCohabitingFamilyPresence(String value) {
        if ("1".equals(value)) {
            return "1";
        }
        if ("0".equals(value)) {
            return "2";
        }
        return value;
    }

    /** 進捗コード (progress code): 1000→JT0010, 9100→JT9100, 9900→JT9200. 9500:振分待ち undecided. */
    private static String convertProgressCode(String value) {
        if (value == null) {
            return null;
        }
        switch (value) {
            case "1000": return "JT0010";
            case "9100": return "JT9100";
            case "9900": return "JT9200";
            default:     return value; // TODO(編集仕様詳細): 9500:振分待ち undecided (ARUHI/external loans only)
        }
    }

    /**
     * Populates the derived 資金使途 columns (2332:物件種別 / 2333:マンション / 2334:マンション以外).
     * Only Housing loans (product major category 1) and Wide loans (2) set these;
     * external loans (4) and others keep the plain 資金使途 identity value only.
     * Source 資金使途 values 7:増改築 and above are undecided (TODO).
     */
    private void mapFundUsageDerivedColumns(申込Source source, 申込Target target) {
        String productMajorCategory = source.get商品大分類();
        if (!"1".equals(productMajorCategory) && !"2".equals(productMajorCategory)) {
            return;
        }
        String fundUsage = source.get資金使途();
        if (fundUsage == null) {
            return;
        }
        switch (fundUsage) {
            case "1": target.set資金使途＿物件種別("2"); target.set資金使途＿マンション以外("6"); break; // 建物新築
            case "2": target.set資金使途＿物件種別("2"); target.set資金使途＿マンション以外("5"); break; // 土地購入
            case "3": target.set資金使途＿物件種別("2"); target.set資金使途＿マンション以外("1"); break; // 戸建購入(新築)
            case "4": target.set資金使途＿物件種別("1"); target.set資金使途＿マンション("1");     break; // マンション購入(新築)
            case "5": target.set資金使途＿物件種別("2"); target.set資金使途＿マンション以外("2"); break; // 中古(戸建)
            case "6": target.set資金使途＿物件種別("1"); target.set資金使途＿マンション("2");     break; // 中古(マンション)
            default:  break; // TODO(編集仕様詳細): 7:増改築, 8:借替, 9, 10, 11 undecided
        }
    }

    // Maps every non-PK column from 申込Source to 申込Target.
    // 編集仕様詳細 code conversions are applied via the convert* helpers, and over-length
    // text columns are trimmed with truncateToByteLimit.
    private void mapApplicationColumns(申込Source source, 申込Target target) {
        target.set商品大分類(convertProductMajorCategory(source.get商品大分類()));
        target.set商品コード(source.get商品コード());   // TODO(編集仕様詳細): 商品コードマッピングは大半が未定
        target.set保証番号(source.get保証番号());
        target.set関連案件有無(source.get関連案件有無());
        target.set申込日(source.get申込日());
        target.setＣＩＦ番号(source.getＣＩＦ番号());
        target.set顧客番号(source.getＣＩＦ番号());
        target.set受付店番(source.get受付店番());
        target.set店番(source.get店番());

        target.set自宅郵便番号(source.get自宅郵便番号());
        target.set自宅住所カナ(source.get自宅住所カナ());
        target.set自宅住所漢字(source.get自宅住所漢字());
        target.set生年月日(source.get生年月日());
        target.set性別(source.get性別());
        target.set年齢(source.get年齢());
        target.set勤務先郵便番号(source.get勤務先郵便番号());
        target.set携帯電話番号(source.get携帯電話番号());
        target.set自宅電話番号(source.get自宅電話番号());
        target.set建物完成予定日(source.get建物完成予定日());
        target.set検索用カナ氏名(source.get検索用カナ氏名());

        // Full name is kept as-is, and 姓/名 are split on the space
        // (kana uses a half-width space, kanji uses a full-width space).
        // Over-length values are auto-trimmed to the target byte size by ColumnFitInterceptor.
        target.setカナ氏名(source.getカナ氏名());
        String[] kanaNameParts = splitFullName(source.getカナ氏名(), " ");
        target.setカナ氏名姓(kanaNameParts[0]);
        target.setカナ氏名名(kanaNameParts[1]);
        // 検索用カナ氏名姓/名 derive from the same split.
        target.set検索用カナ氏名姓(kanaNameParts[0]);
        target.set検索用カナ氏名名(kanaNameParts[1]);
        target.set漢字氏名(source.get漢字氏名());
        String[] kanjiNameParts = splitFullName(source.get漢字氏名(), "\u3000"); // full-width space
        target.set漢字氏名姓(kanjiNameParts[0]);
        target.set漢字氏名名(kanjiNameParts[1]);

        target.set勤務先名漢字(truncateToByteLimit(source.get勤務先名漢字(), 120));
        target.set勤務先住所漢字(source.get勤務先住所漢字());
        target.set勤務先企業区分(convertEmployerCompanyType(source.get上場フラグ()));   // checkbox: 1=上場 / 0=非上場
        target.set勤務先業種名(source.get勤務先業種());
        target.set勤務先職種その他(source.get勤務先職種役職());
        target.set勤務先入社年月(source.get勤務先入社年月());
        target.set勤務先勤続年数(source.get勤務先勤続年数());
        target.set勤務先勤業(convertOccupationType(source.get商品大分類(), source.get勤務先職業()));   // 職業形態 2080/2083
        target.set勤務先資本金区分(source.get勤務先資本金区分());
        target.set勤務先逐業員数(source.get勤務先従業員数());
        target.set住居形態(convertResidenceType(source.get住居区分()));

        // 金融機関 1
        target.set金融機関1名称(truncateToByteLimit(source.get借入＿利用先名1(), 30));
        target.set金融機関1借入種類(convertBorrowingType(source.get借入＿利用種類1()));
        target.set金融機関1残高(source.get借入＿利用残高1());
        target.set金融機関1借入期間(source.get借入＿残存期間1());
        target.set金融機関1借入時完済解約予定(convertPayoffPlanAtBorrowing(source.get借入＿解約予定1()));
        target.set金融機関1利用限度額(source.get借入＿利用限度額1());
        target.set金融機関1借入年間返済額(source.get借入＿年間支払額1());
        // 金融機関 2
        target.set金融機関2名称(truncateToByteLimit(source.get借入＿利用先名2(), 30));
        target.set金融機関2借入種類(convertBorrowingType(source.get借入＿利用種類2()));
        target.set金融機関2残高(source.get借入＿利用残高2());
        target.set金融機関2借入期間(source.get借入＿残存期間2());
        target.set金融機関2借入時完済解約予定(convertPayoffPlanAtBorrowing(source.get借入＿解約予定2()));
        target.set金融機関2利用限度額(source.get借入＿利用限度額2());
        target.set金融機関2借入年間返済額(source.get借入＿年間支払額2());
        // 金融機関 3
        target.set金融機関3名称(truncateToByteLimit(source.get借入＿利用先名3(), 30));
        target.set金融機関3借入種類(convertBorrowingType(source.get借入＿利用種類3()));
        target.set金融機関3残高(source.get借入＿利用残高3());
        target.set金融機関3借入期間(source.get借入＿残存期間3());
        target.set金融機関3借入時完済解約予定(convertPayoffPlanAtBorrowing(source.get借入＿解約予定3()));
        target.set金融機関3利用限度額(source.get借入＿利用限度額3());
        target.set金融機関3借入年間返済額(source.get借入＿年間支払額3());

        target.set資金使途(source.get資金使途());
        target.set借入金額(source.get借入金額());
        target.set借入金額＿毎月(source.get借入金額＿毎月());
        target.set借入金額＿半年毎(source.get借入金額＿半年毎());
        target.set借入期間(source.get借入期間());
        target.set借入希望日(source.get借入希望日());
        target.set借入希望日＿建物(source.get借入希望日＿建物());
        target.set返済方法区分(convertRepaymentMethod(source.get返済方法区分()));
        target.set金利区分(convertInterestRateType(source.get金利区分()));
        target.set保証料区分(source.get保証料区分());
        target.setボーナス返済月1(source.getボーナス返済月1());
        target.setボーナス返済月2(source.getボーナス返済月2());

        // 同居予定家族 (checkbox → 予定あり/なし code)
        target.set同居予定家族＿配偶者(convertCohabitingSpouse(source.get同居＿配偶者()));
        target.set同居予定家族＿父(convertCohabitingFamilyPresence(source.get同居＿父()));
        target.set同居予定家族＿母(convertCohabitingFamilyPresence(source.get同居＿母()));
        BigDecimal otherCohabitantCount = source.get同居＿その他人数();
        target.set同居予定家族＿その他(otherCohabitantCount != null && otherCohabitantCount.compareTo(BigDecimal.ZERO) > 0 ? "1" : "2");
        target.set同居予定家族＿その他＿人数(otherCohabitantCount);
        target.set同居予定家族＿子供年齢＿1人目(source.get同居＿子供年齢1());
        target.set同居予定家族＿子供年齢＿2人目(source.get同居＿子供年齢2());
        target.set同居予定家族＿子供年齢＿3人目(source.get同居＿子供年齢3());
        target.set同居予定家族＿子供年齢＿4人目(source.get同居＿子供年齢4());
        target.set同居予定家族＿本人("1");  // fixed: applicant always lives in

        // 同居予定家族＿合計人数 = 1 (本人) + 配偶者 + 父 + 母 + その他人数
        int cohabitantTotal = 1;
        if ("1".equals(source.get同居＿配偶者())) cohabitantTotal++;
        if ("1".equals(source.get同居＿父()))    cohabitantTotal++;
        if ("1".equals(source.get同居＿母()))    cohabitantTotal++;
        if (otherCohabitantCount != null) cohabitantTotal += otherCohabitantCount.intValue();
        target.set同居予定家族＿合計人数(new BigDecimal(cohabitantTotal));

        target.set婚姻区分(source.get婚姻区分());
        target.set外部連携受付番号(truncateToByteLimit(source.get外部連携受付番号(), 12));
        target.set勤務先資本金＿外部ローン(source.get勤務先資本金());
        target.set土地契約予定日(source.get土地契約予定日());

        target.set預金＿金融機関1＿名称(truncateToByteLimit(source.get預金＿金融機関名1(), 30));
        target.set預金＿金融機関1＿本人預金(source.get預金＿本人預金1());
        target.set預金＿金融機関1＿家族預金(source.get預金＿家族預金1());
        target.set預金＿金融機関2＿名称(truncateToByteLimit(source.get預金＿金融機関名2(), 30));
        target.set預金＿金融機関2＿本人預金(source.get預金＿本人預金2());
        target.set預金＿金融機関2＿家族預金(source.get預金＿家族預金2());
        target.set預金＿金融機関3＿本人預金(source.get預金＿本人預金3());
        target.set預金＿金融機関3＿家族預金(source.get預金＿家族預金3());
        target.set預金＿金融機関4＿本人預金(source.get預金＿本人預金4());
        target.set預金＿金融機関4＿家族預金(source.get預金＿家族預金4());

        target.set歩合給(convertCommissionPay(source.get勤務先歩合給区分()));

        // From 申込ワイド
        target.set国家資格(convertNationalQualification(source.get国家資格()));
        target.set国家資格＿その他(truncateToByteLimit(source.get国家資格子の他(), 30));
        target.set配偶者年収(source.get配偶者年収());

        // 資金使途 derived columns (2332/2333/2334): Housing/Wide only. Plain 資金使途 already set identity above.
        mapFundUsageDerivedColumns(source, target);
        target.set資金使途＿ワイドローン一般口(null);   // TODO(編集仕様詳細): undecided

        // 必要資金
        target.set必要資金＿土地(source.get必要資金＿土地());
        target.set必要資金＿建物(source.get必要資金＿建物());
        target.set必要資金＿諸費用(source.get必要資金＿諸費用());
        target.set必要資金＿その他(source.get必要資金＿その他());
        target.set必要資金＿合計(source.get必要資金＿合計());
        // 調達＿金融機関
        target.set調達＿本件借入＿金額(source.get調達＿本件借入());
        target.set調達＿金融機関1＿名称(truncateToByteLimit(source.get調達＿その他1＿借入先(), 30));
        target.set調達＿金融機関1＿金額(source.get調達＿その他1());
        target.set調達＿金融機関1＿期間(source.get調達＿その他1＿期間());
        target.set調達＿金融機関1＿利率(source.get調達＿その他1＿利率());
        target.set調達＿金融機関2＿名称(truncateToByteLimit(source.get調達＿その他2＿借入先(), 30));
        target.set調達＿金融機関2＿金額(source.get調達＿その他2());
        target.set調達＿金融機関2＿期間(source.get調達＿その他2＿期間());
        target.set調達＿金融機関2＿利率(source.get調達＿その他2＿利率());
        target.set調達＿自己資金(source.get調達＿自己資金());
        target.set調達＿自己資金合計(source.get調達＿自己資金合計());
        target.set調達＿合計(source.get調達＿合計());
        // 自己資金
        target.set自己資金＿預貯金(source.get自己資金＿預貯金());
        target.set自己資金＿預貯金うち当行(source.get自己資金＿預貯金ウチ当行());
        target.set自己資金＿その他(source.get自己資金＿その他());
        target.set自己資金＿贈与(source.get自己資金＿贈与());
        // 税込年収: 前年 = 年収１ / 前々年 = 年収２ / ３年前 = 年収３
        target.set税込年収(source.get年収１());
        target.set税込年収＿前々年(source.get年収２());
        target.set税込年収＿３年前(source.get年収３());
        // Other 申込 items
        target.set適用年収(source.get適用年収());
        target.set家賃等月額(source.get家賃());
        target.set居住年数(source.get居住年数());
        target.set資産＿預金(source.get資産＿本人＿預金());
        target.set資産＿その他(source.get資産＿本人＿その他());
        target.set毎月返済日(source.get毎月返済日());
    }

}
