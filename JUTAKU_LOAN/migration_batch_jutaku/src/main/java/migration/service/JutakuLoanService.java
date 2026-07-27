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
import migration.domain.target.履歴申込審査段階Target;
import migration.mybatis.domain.itf_sms.SMS履歴申込＿業者＿住宅;
import migration.mybatis.mapper.itf_sms.SMS履歴申込＿業者＿住宅Mapper;
import migration.domain.target.履歴保証人Target;
import migration.domain.target.履歴保証検討表補足Target;
import migration.domain.target.保証人Target;
import migration.domain.target.保証検討表補足Target;
import migration.mybatis.domain.itf_sms.SMS申込＿業者＿住宅;
import migration.mybatis.domain.szb_sms.SZB申込;
import migration.mybatis.domain.szb_sms.SZB申込Key;
import migration.mybatis.domain.szb_sms.SZB販売業者マスター;
import migration.mybatis.mapper.itf_sms.SMS申込＿業者＿住宅Mapper;
import migration.mybatis.mapper.szb_sms.SZB申込Mapper;
import migration.mybatis.mapper.szb_sms.SZB販売業者マスターMapper;
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
import migration.mapper.target.履歴申込審査段階TargetMapper;
import migration.mapper.target.履歴保証人TargetMapper;
import migration.mapper.target.履歴保証検討表補足TargetMapper;
import migration.mapper.target.保証人TargetMapper;
import migration.mapper.target.保証検討表補足TargetMapper;
import migration.mapper.target.申込担保情報ＰＤＦTargetMapper;
import migration.mapper.target.申込審査履歴TargetMapper;
import migration.domain.source.審査チェック照会Source;
import migration.domain.source.審査ＫＳＣ照会Source;
import migration.domain.source.審査ＪＩＣＣ照会Source;
import migration.domain.source.審査ＣＩＣ照会Source;
import migration.domain.source.個信類似照会管理Source;
import migration.domain.source.個信類似照会明細Source;
import migration.domain.source.個信類似明細Source;
import migration.domain.source.個信データ編集管理Source;
import migration.domain.source.返済比率計算Source;
import migration.domain.source.返済比率計算結果Source;
import migration.domain.source.返済比率計算結果明細Source;
import migration.domain.source.審査モデル回答Source;
import migration.domain.source.審査モデル回答ＳSource;
import migration.domain.source.審査モデル回答判定Source;
import migration.domain.source.審査モデル回答判定ＳSource;
import migration.domain.source.審査モデル回答明細Source;
import migration.domain.source.審査モデル回答明細ＳSource;
import migration.domain.source.審査モデル照会Source;
import migration.domain.source.審査モデル照会ＳSource;
import migration.domain.source.審査モデル照会基本Source;
import migration.domain.source.審査モデル照会基本ＳSource;
import migration.domain.source.システム判定結果Source;
import migration.domain.source.システム判定結果明細Source;
import migration.domain.source.システム判定照会Source;
import migration.domain.source.住宅ローン不正検知結果Source;
import migration.domain.source.住宅ローン不正検知照会Source;
import migration.domain.source.審査結果照会Source;
import migration.domain.source.審査コメントSource;
import migration.domain.source.審査データ送信Source;
import migration.domain.source.審査ＳＮＡＶＩ連携イベントSource;
import migration.domain.source.審査ＳＮＡＶＩ連携内容Source;
import migration.domain.source.審査契約書出力連携内容Source;
import migration.domain.source.契約書連携イベントSource;
import migration.domain.source.審査預保照会Source;
import migration.domain.source.保証結果メインじぶんSource;
import migration.domain.source.保証結果メインアルヒSource;
import migration.domain.source.保証結果融資条件じぶんSource;
import migration.domain.source.保証結果融資条件アルヒSource;
import migration.domain.source.担当者別操作管理Source;
import migration.domain.source.審査ＪＩＣＣ信用情報詳細Source;
import migration.domain.source.審査ＣＩＣ信用情報詳細Source;
import migration.domain.source.審査ＫＳＣ信用情報Source;
import migration.domain.source.審査ＫＳＣ信用情報明細Source;
import migration.domain.source.審査ＫＳＣ信用情報詳細Source;
import migration.domain.source.担保評価回答Source;
import migration.domain.source.担保評価連携結果ファイルSource;
import migration.domain.target.審査チェック照会Target;
import migration.domain.target.審査ＫＳＣ照会Target;
import migration.domain.target.審査ＪＩＣＣ照会Target;
import migration.domain.target.審査ＣＩＣ照会Target;
import migration.domain.target.個信類似照会管理Target;
import migration.domain.target.個信類似照会明細Target;
import migration.domain.target.個信類似明細Target;
import migration.domain.target.個信データ編集管理Target;
import migration.domain.target.返済比率計算Target;
import migration.domain.target.返済比率計算結果Target;
import migration.domain.target.返済比率計算結果明細Target;
import migration.domain.target.審査モデル回答Target;
import migration.domain.target.審査モデル回答ＳTarget;
import migration.domain.target.審査モデル回答判定Target;
import migration.domain.target.審査モデル回答判定ＳTarget;
import migration.domain.target.審査モデル回答明細Target;
import migration.domain.target.審査モデル回答明細ＳTarget;
import migration.domain.target.審査モデル照会Target;
import migration.domain.target.審査モデル照会ＳTarget;
import migration.domain.target.審査モデル照会基本Target;
import migration.domain.target.審査モデル照会基本ＳTarget;
import migration.domain.target.システム判定結果Target;
import migration.domain.target.システム判定結果明細Target;
import migration.domain.target.システム判定照会Target;
import migration.domain.target.住宅ローン不正検知結果Target;
import migration.domain.target.住宅ローン不正検知照会Target;
import migration.domain.target.審査結果照会Target;
import migration.domain.target.審査コメントTarget;
import migration.domain.target.審査データ送信Target;
import migration.domain.target.審査ＳＮＡＶＩ連携イベントTarget;
import migration.domain.target.審査ＳＮＡＶＩ連携内容Target;
import migration.domain.target.ＩＦ＿契約書送信Target;
import migration.domain.target.契約書連携イベントTarget;
import migration.domain.target.審査預保照会Target;
import migration.domain.target.保証結果メインじぶんTarget;
import migration.domain.target.保証結果メインアルヒTarget;
import migration.domain.target.保証結果融資条件じぶんTarget;
import migration.domain.target.保証結果融資条件アルヒTarget;
import migration.domain.target.担当者別操作管理Target;
import migration.domain.target.審査ＪＩＣＣ信用情報詳細Target;
import migration.domain.target.審査ＣＩＣ信用情報詳細Target;
import migration.domain.target.審査ＫＳＣ信用情報Target;
import migration.domain.target.審査ＫＳＣ信用情報明細Target;
import migration.domain.target.審査ＫＳＣ信用情報詳細Target;
import migration.domain.target.ＩＦ＿担保評価連携結果Target;
import migration.domain.target.ＩＦ＿担保評価連携結果＿ファイルTarget;
import migration.mapper.source.審査チェック照会SourceMapper;
import migration.mapper.source.審査ＫＳＣ照会SourceMapper;
import migration.mapper.source.審査ＪＩＣＣ照会SourceMapper;
import migration.mapper.source.審査ＣＩＣ照会SourceMapper;
import migration.mapper.source.個信類似照会管理SourceMapper;
import migration.mapper.source.個信類似照会明細SourceMapper;
import migration.mapper.source.個信類似明細SourceMapper;
import migration.mapper.source.個信データ編集管理SourceMapper;
import migration.mapper.source.返済比率計算SourceMapper;
import migration.mapper.source.返済比率計算結果SourceMapper;
import migration.mapper.source.返済比率計算結果明細SourceMapper;
import migration.mapper.source.審査モデル回答SourceMapper;
import migration.mapper.source.審査モデル回答ＳSourceMapper;
import migration.mapper.source.審査モデル回答判定SourceMapper;
import migration.mapper.source.審査モデル回答判定ＳSourceMapper;
import migration.mapper.source.審査モデル回答明細SourceMapper;
import migration.mapper.source.審査モデル回答明細ＳSourceMapper;
import migration.mapper.source.審査モデル照会SourceMapper;
import migration.mapper.source.審査モデル照会ＳSourceMapper;
import migration.mapper.source.審査モデル照会基本SourceMapper;
import migration.mapper.source.審査モデル照会基本ＳSourceMapper;
import migration.mapper.source.システム判定結果SourceMapper;
import migration.mapper.source.システム判定結果明細SourceMapper;
import migration.mapper.source.システム判定照会SourceMapper;
import migration.mapper.source.住宅ローン不正検知結果SourceMapper;
import migration.mapper.source.住宅ローン不正検知照会SourceMapper;
import migration.mapper.source.審査結果照会SourceMapper;
import migration.mapper.source.審査コメントSourceMapper;
import migration.mapper.source.審査データ送信SourceMapper;
import migration.mapper.source.審査ＳＮＡＶＩ連携イベントSourceMapper;
import migration.mapper.source.審査ＳＮＡＶＩ連携内容SourceMapper;
import migration.mapper.source.審査契約書出力連携内容SourceMapper;
import migration.mapper.source.契約書連携イベントSourceMapper;
import migration.mapper.source.審査預保照会SourceMapper;
import migration.mapper.source.保証結果メインじぶんSourceMapper;
import migration.mapper.source.保証結果メインアルヒSourceMapper;
import migration.mapper.source.保証結果融資条件じぶんSourceMapper;
import migration.mapper.source.保証結果融資条件アルヒSourceMapper;
import migration.mapper.source.担当者別操作管理SourceMapper;
import migration.mapper.source.審査ＪＩＣＣ信用情報詳細SourceMapper;
import migration.mapper.source.審査ＣＩＣ信用情報詳細SourceMapper;
import migration.mapper.source.審査ＫＳＣ信用情報SourceMapper;
import migration.mapper.source.審査ＫＳＣ信用情報明細SourceMapper;
import migration.mapper.source.審査ＫＳＣ信用情報詳細SourceMapper;
import migration.mapper.source.担保評価回答SourceMapper;
import migration.mapper.source.担保評価連携結果ファイルSourceMapper;
import migration.mapper.target.審査チェック照会TargetMapper;
import migration.mapper.target.審査ＫＳＣ照会TargetMapper;
import migration.mapper.target.審査ＪＩＣＣ照会TargetMapper;
import migration.mapper.target.審査ＣＩＣ照会TargetMapper;
import migration.mapper.target.個信類似照会管理TargetMapper;
import migration.mapper.target.個信類似照会明細TargetMapper;
import migration.mapper.target.個信類似明細TargetMapper;
import migration.mapper.target.個信データ編集管理TargetMapper;
import migration.mapper.target.返済比率計算TargetMapper;
import migration.mapper.target.返済比率計算結果TargetMapper;
import migration.mapper.target.返済比率計算結果明細TargetMapper;
import migration.mapper.target.審査モデル回答TargetMapper;
import migration.mapper.target.審査モデル回答ＳTargetMapper;
import migration.mapper.target.審査モデル回答判定TargetMapper;
import migration.mapper.target.審査モデル回答判定ＳTargetMapper;
import migration.mapper.target.審査モデル回答明細TargetMapper;
import migration.mapper.target.審査モデル回答明細ＳTargetMapper;
import migration.mapper.target.審査モデル照会TargetMapper;
import migration.mapper.target.審査モデル照会ＳTargetMapper;
import migration.mapper.target.審査モデル照会基本TargetMapper;
import migration.mapper.target.審査モデル照会基本ＳTargetMapper;
import migration.mapper.target.システム判定結果TargetMapper;
import migration.mapper.target.システム判定結果明細TargetMapper;
import migration.mapper.target.システム判定照会TargetMapper;
import migration.mapper.target.住宅ローン不正検知結果TargetMapper;
import migration.mapper.target.住宅ローン不正検知照会TargetMapper;
import migration.mapper.target.審査結果照会TargetMapper;
import migration.mapper.target.審査コメントTargetMapper;
import migration.mapper.target.審査データ送信TargetMapper;
import migration.mapper.target.審査ＳＮＡＶＩ連携イベントTargetMapper;
import migration.mapper.target.審査ＳＮＡＶＩ連携内容TargetMapper;
import migration.mapper.target.ＩＦ＿契約書送信TargetMapper;
import migration.mapper.target.契約書連携イベントTargetMapper;
import migration.mapper.target.審査預保照会TargetMapper;
import migration.mapper.target.保証結果メインじぶんTargetMapper;
import migration.mapper.target.保証結果メインアルヒTargetMapper;
import migration.mapper.target.保証結果融資条件じぶんTargetMapper;
import migration.mapper.target.保証結果融資条件アルヒTargetMapper;
import migration.mapper.target.担当者別操作管理TargetMapper;
import migration.mapper.target.審査ＪＩＣＣ信用情報詳細TargetMapper;
import migration.mapper.target.審査ＣＩＣ信用情報詳細TargetMapper;
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
    @Autowired private 審査ＪＩＣＣ照会SourceMapper reviewJiccSourceMapper;
    @Autowired private 審査ＣＩＣ照会SourceMapper reviewCicSourceMapper;
    @Autowired private 個信類似照会管理SourceMapper kosinSimilarInquiryMgmtSourceMapper;
    @Autowired private 個信類似照会明細SourceMapper kosinSimilarInquiryDetailSourceMapper;
    @Autowired private 個信類似明細SourceMapper kosinSimilarDetailSourceMapper;
    @Autowired private 個信データ編集管理SourceMapper kosinDataEditMgmtSourceMapper;
    @Autowired private 返済比率計算SourceMapper repaymentRatioCalcSourceMapper;
    @Autowired private 返済比率計算結果SourceMapper repaymentRatioResultSourceMapper;
    @Autowired private 返済比率計算結果明細SourceMapper repaymentRatioResultDetailSourceMapper;
    @Autowired private 審査モデル回答SourceMapper scoringAnswerSourceMapper;
    @Autowired private 審査モデル回答ＳSourceMapper scoringAnswerSSourceMapper;
    @Autowired private 審査モデル回答判定SourceMapper scoringJudgeSourceMapper;
    @Autowired private 審査モデル回答判定ＳSourceMapper scoringJudgeSSourceMapper;
    @Autowired private 審査モデル回答明細SourceMapper scoringDetailSourceMapper;
    @Autowired private 審査モデル回答明細ＳSourceMapper scoringDetailSSourceMapper;
    @Autowired private 審査モデル照会SourceMapper scoringInquirySourceMapper;
    @Autowired private 審査モデル照会ＳSourceMapper scoringInquirySSourceMapper;
    @Autowired private 審査モデル照会基本SourceMapper scoringInquiryBasicSourceMapper;
    @Autowired private 審査モデル照会基本ＳSourceMapper scoringInquiryBasicSSourceMapper;
    @Autowired private システム判定結果SourceMapper systemJudgeResultSourceMapper;
    @Autowired private システム判定結果明細SourceMapper systemJudgeResultDetailSourceMapper;
    @Autowired private システム判定照会SourceMapper systemJudgeInquirySourceMapper;
    @Autowired private 住宅ローン不正検知結果SourceMapper fraudDetectionResultSourceMapper;
    @Autowired private 住宅ローン不正検知照会SourceMapper fraudDetectionInquirySourceMapper;
    @Autowired private 審査結果照会SourceMapper reviewResultInquirySourceMapper;
    @Autowired private 審査コメントSourceMapper reviewCommentSourceMapper;
    @Autowired private 審査データ送信SourceMapper reviewDataSendSourceMapper;
    @Autowired private 審査ＳＮＡＶＩ連携イベントSourceMapper reviewSnaviLinkEventSourceMapper;
    @Autowired private 審査ＳＮＡＶＩ連携内容SourceMapper reviewSnaviLinkContentSourceMapper;
    @Autowired private 審査契約書出力連携内容SourceMapper contractDocOutputContentSourceMapper;
    @Autowired private 契約書連携イベントSourceMapper contractLinkEventSourceMapper;
    @Autowired private 審査預保照会SourceMapper reviewDepositGuaranteeInquirySourceMapper;
    @Autowired private 保証結果メインじぶんSourceMapper guaranteeResultMainJibunSourceMapper;
    @Autowired private 保証結果メインアルヒSourceMapper guaranteeResultMainAruhiSourceMapper;
    @Autowired private 保証結果融資条件じぶんSourceMapper guaranteeResultFinanceJibunSourceMapper;
    @Autowired private 保証結果融資条件アルヒSourceMapper guaranteeResultFinanceAruhiSourceMapper;
    @Autowired private 担当者別操作管理SourceMapper operationMgmtByPersonSourceMapper;
    @Autowired private 審査ＪＩＣＣ信用情報詳細SourceMapper reviewJiccCreditDetailSourceMapper;
    @Autowired private 審査ＣＩＣ信用情報詳細SourceMapper reviewCicCreditDetailSourceMapper;
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
    @Autowired private SMS履歴申込＿業者＿住宅Mapper historyApplicationVendorHousingTargetMapper;
    @Autowired private 保証人TargetMapper guarantorTargetMapper;
    @Autowired private SMS申込＿業者＿住宅Mapper applicationVendorHousingTargetMapper;
    @Autowired private SZB申込Mapper vendorApplicationSourceMapper;
    @Autowired private SZB販売業者マスターMapper vendorMasterSourceMapper;
    @Autowired private 保証検討表補足TargetMapper guaranteeReviewSupplementTargetMapper;
    @Autowired private 履歴保証人TargetMapper historyGuarantorTargetMapper;
    @Autowired private 履歴保証検討表補足TargetMapper historyGuaranteeReviewSupplementTargetMapper;
    @Autowired private 申込担保情報ＰＤＦTargetMapper collateralInfoPdfTargetMapper;
    @Autowired private 申込審査履歴TargetMapper reviewHistoryTargetMapper;
    @Autowired private 申込関連申込TargetMapper relatedApplicationTargetMapper;
    @Autowired private 審査チェック照会TargetMapper reviewCheckTargetMapper;
    @Autowired private 審査ＫＳＣ照会TargetMapper reviewKscTargetMapper;
    @Autowired private 審査ＪＩＣＣ照会TargetMapper reviewJiccTargetMapper;
    @Autowired private 審査ＣＩＣ照会TargetMapper reviewCicTargetMapper;
    @Autowired private 個信類似照会管理TargetMapper kosinSimilarInquiryMgmtTargetMapper;
    @Autowired private 個信類似照会明細TargetMapper kosinSimilarInquiryDetailTargetMapper;
    @Autowired private 個信類似明細TargetMapper kosinSimilarDetailTargetMapper;
    @Autowired private 個信データ編集管理TargetMapper kosinDataEditMgmtTargetMapper;
    @Autowired private 返済比率計算TargetMapper repaymentRatioCalcTargetMapper;
    @Autowired private 返済比率計算結果TargetMapper repaymentRatioResultTargetMapper;
    @Autowired private 返済比率計算結果明細TargetMapper repaymentRatioResultDetailTargetMapper;
    @Autowired private 審査モデル回答TargetMapper scoringAnswerTargetMapper;
    @Autowired private 審査モデル回答ＳTargetMapper scoringAnswerSTargetMapper;
    @Autowired private 審査モデル回答判定TargetMapper scoringJudgeTargetMapper;
    @Autowired private 審査モデル回答判定ＳTargetMapper scoringJudgeSTargetMapper;
    @Autowired private 審査モデル回答明細TargetMapper scoringDetailTargetMapper;
    @Autowired private 審査モデル回答明細ＳTargetMapper scoringDetailSTargetMapper;
    @Autowired private 審査モデル照会TargetMapper scoringInquiryTargetMapper;
    @Autowired private 審査モデル照会ＳTargetMapper scoringInquirySTargetMapper;
    @Autowired private 審査モデル照会基本TargetMapper scoringInquiryBasicTargetMapper;
    @Autowired private 審査モデル照会基本ＳTargetMapper scoringInquiryBasicSTargetMapper;
    @Autowired private システム判定結果TargetMapper systemJudgeResultTargetMapper;
    @Autowired private システム判定結果明細TargetMapper systemJudgeResultDetailTargetMapper;
    @Autowired private システム判定照会TargetMapper systemJudgeInquiryTargetMapper;
    @Autowired private 住宅ローン不正検知結果TargetMapper fraudDetectionResultTargetMapper;
    @Autowired private 住宅ローン不正検知照会TargetMapper fraudDetectionInquiryTargetMapper;
    @Autowired private 審査結果照会TargetMapper reviewResultInquiryTargetMapper;
    @Autowired private 審査コメントTargetMapper reviewCommentTargetMapper;
    @Autowired private 審査データ送信TargetMapper reviewDataSendTargetMapper;
    @Autowired private 審査ＳＮＡＶＩ連携イベントTargetMapper reviewSnaviLinkEventTargetMapper;
    @Autowired private 審査ＳＮＡＶＩ連携内容TargetMapper reviewSnaviLinkContentTargetMapper;
    @Autowired private ＩＦ＿契約書送信TargetMapper contractDocSendTargetMapper;
    @Autowired private 契約書連携イベントTargetMapper contractLinkEventTargetMapper;
    @Autowired private 審査預保照会TargetMapper reviewDepositGuaranteeInquiryTargetMapper;
    @Autowired private 保証結果メインじぶんTargetMapper guaranteeResultMainJibunTargetMapper;
    @Autowired private 保証結果メインアルヒTargetMapper guaranteeResultMainAruhiTargetMapper;
    @Autowired private 保証結果融資条件じぶんTargetMapper guaranteeResultFinanceJibunTargetMapper;
    @Autowired private 保証結果融資条件アルヒTargetMapper guaranteeResultFinanceAruhiTargetMapper;
    @Autowired private 担当者別操作管理TargetMapper operationMgmtByPersonTargetMapper;
    @Autowired private 審査ＪＩＣＣ信用情報詳細TargetMapper reviewJiccCreditDetailTargetMapper;
    @Autowired private 審査ＣＩＣ信用情報詳細TargetMapper reviewCicCreditDetailTargetMapper;
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

    // Fixed 無担保集中＿申込書番号 for every 申込 row (設計書No.656): excludes migrated data
    // from 口座開設ディテッカー連携 processing. No source column exists for this value.
    private static final String FIXED_MUTAN_SHUCHU_APPLICATION_NUMBER = "99999999999";

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

        List<申込進捗Source> progressRecords = emptyIfNull(applicationProgressSourceMapper.selectByRowRange(fromRowNumber, toRowNumber));

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
        if (progress == null) {
            log.warn("SKIP: null 申込進捗 record received");
            return false;
        }
        String sourceApplicationNumber = progress.get申込番号();
        String targetApplicationNumber = convertApplicationNumber(sourceApplicationNumber);

        List<申込審査段階Source> allReviewStages =
                emptyIfNull(reviewStageSourceMapper.selectByApplicationId(sourceApplicationNumber));
        List<申込審査段階Source> completedReviewStages = allReviewStages.stream()
                .filter(java.util.Objects::nonNull)
                .filter(stage -> "1".equals(stage.get審査完了区分()))
                .sorted(Comparator.comparing(申込審査段階Source::get申込目的,
                        Comparator.nullsLast(Comparator.naturalOrder())))
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
        for (申込関連申込Source relatedApplication : emptyIfNull(relatedApplicationSourceMapper.selectByApplicationId(sourceApplicationNumber))) {
            if (relatedApplication == null) {
                continue;
            }
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
        if (sourceApplicationNumber == null || targetApplicationNumber == null
                || reviewStages == null || reviewStages.isEmpty()) {
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

            // ①-a 申込_業者_住宅 main (MAX only) — FK to 申込. Implemented via auto-generated
            // gen-folder entities (Nakamura's generator), not the old hand-written classes.
            // 業者名カナ/業者名 come via JOIN: 販売業者マスター.販売業者コード = 申込.宅建業者コード.
            // Most other target columns have no source ("-" in design doc) and stay null.
            SZB申込Key szbApplicationKey = new SZB申込Key();
            szbApplicationKey.set申込番号(sourceApplicationNumber);
            szbApplicationKey.set申込目的(maxSourcePurpose);
            SZB申込 szbApplication = vendorApplicationSourceMapper.selectByPrimaryKey(szbApplicationKey);
            String vendorCode = szbApplication != null ? szbApplication.get宅建業者コード() : null;

            SMS申込＿業者＿住宅 vendorHousingTarget = new SMS申込＿業者＿住宅();
            vendorHousingTarget.set申込番号(targetApplicationNumber);
            vendorHousingTarget.set申込目的(convertedPurpose);
            vendorHousingTarget.set業者コード(vendorCode);
            if (vendorCode != null) {
                SZB販売業者マスター vendor = vendorMasterSourceMapper.selectByPrimaryKey(vendorCode);
                if (vendor != null) {
                    vendorHousingTarget.set業者名カナ(vendor.get販売業者名カナ());
                    vendorHousingTarget.set業者名(vendor.get販売業者名());
                }
            }
            // insert() (not insertSelective) — OGNL used by insertSelective's <if> checks cannot
            // parse property names containing full-width underscore (＿), e.g. 業者担当者＿カナ姓.
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
                emptyIfNull(guarantorSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
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
                emptyIfNull(collateralAnswerPdfSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
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
                emptyIfNull(reviewHistorySourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (申込審査履歴Source reviewHistory : reviewHistories) {
            申込審査履歴Target reviewHistoryTarget = new 申込審査履歴Target();
            reviewHistoryTarget.set申込番号(targetApplicationNumber);
            reviewHistoryTarget.set申込目的(convertedPurpose);
            reviewHistoryTarget.setイベント(reviewHistory.getイベント());
            reviewHistoryTarget.setイベント日時(reviewHistory.getイベント日時());
            reviewHistoryTarget.set進捗コード(convertProgressCode(reviewHistory.get進捗コード()));
            reviewHistoryTarget.setユーザＩＤ(reviewHistory.getユーザＩＤ());
            reviewHistoryTarget.setユーザ名(reviewHistory.getユーザ名());
            reviewHistoryTarget.set回数(reviewHistory.get回数());
            reviewHistoryTargetMapper.insert(reviewHistoryTarget);
        }

        // ③-d 審査チェック照会 (MAX only) — 1:N event log per (申込番号, 申込目的).
        // 一連番号 fixed '99999'; other columns pass through from source.
        List<審査チェック照会Source> reviewChecks =
                emptyIfNull(reviewCheckSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
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
                emptyIfNull(reviewKscSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
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
                emptyIfNull(reviewKscCreditSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
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
                emptyIfNull(reviewKscCreditLineSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
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
                emptyIfNull(reviewKscCreditDetailSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
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

        // ③-d6 審査ＪＩＣＣ照会 (MAX only) — 1:N event log per (申込番号, 申込目的).
        // 申込番号 2→3 and 申込目的 converted; other columns pass through from source.
        List<審査ＪＩＣＣ照会Source> reviewJiccs =
                emptyIfNull(reviewJiccSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (審査ＪＩＣＣ照会Source reviewJicc : reviewJiccs) {
            審査ＪＩＣＣ照会Target reviewJiccTarget = new 審査ＪＩＣＣ照会Target();
            reviewJiccTarget.set申込番号(targetApplicationNumber);
            reviewJiccTarget.set申込目的(convertedPurpose);
            reviewJiccTarget.setイベント(reviewJicc.getイベント());
            reviewJiccTarget.setイベント日時(reviewJicc.getイベント日時());
            reviewJiccTarget.set連番(reviewJicc.get連番());
            reviewJiccTarget.set別名連番(reviewJicc.get別名連番());
            reviewJiccTarget.set受付日時(reviewJicc.get受付日時());
            reviewJiccTarget.set受付番号(reviewJicc.get受付番号());
            reviewJiccTarget.setコメント(reviewJicc.getコメント());
            reviewJiccTargetMapper.insert(reviewJiccTarget);
        }

        // ③-d7 審査ＣＩＣ照会 (MAX only) — 1:N event log per (申込番号, 申込目的).
        // 申込番号 2→3 and 申込目的 converted; other columns pass through from source.
        List<審査ＣＩＣ照会Source> reviewCics =
                emptyIfNull(reviewCicSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (審査ＣＩＣ照会Source reviewCic : reviewCics) {
            審査ＣＩＣ照会Target reviewCicTarget = new 審査ＣＩＣ照会Target();
            reviewCicTarget.set申込番号(targetApplicationNumber);
            reviewCicTarget.set申込目的(convertedPurpose);
            reviewCicTarget.setイベント(reviewCic.getイベント());
            reviewCicTarget.setイベント日時(reviewCic.getイベント日時());
            reviewCicTarget.set連番(reviewCic.get連番());
            reviewCicTarget.set別名連番(reviewCic.get別名連番());
            reviewCicTarget.set受付日時(reviewCic.get受付日時());
            reviewCicTarget.set受付番号(reviewCic.get受付番号());
            reviewCicTarget.setコメント(reviewCic.getコメント());
            reviewCicTargetMapper.insert(reviewCicTarget);
        }

        // ③-d8a 個信類似照会管理 (MAX only) — 1:N per (申込番号, 申込目的), pass-through.
        List<個信類似照会管理Source> kosinSimilarInquiryMgmts =
                emptyIfNull(kosinSimilarInquiryMgmtSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (個信類似照会管理Source kosinSimilarInquiryMgmt : kosinSimilarInquiryMgmts) {
            個信類似照会管理Target kosinSimilarInquiryMgmtTarget = new 個信類似照会管理Target();
            kosinSimilarInquiryMgmtTarget.set申込番号(targetApplicationNumber);
            kosinSimilarInquiryMgmtTarget.set申込目的(convertedPurpose);
            kosinSimilarInquiryMgmtTarget.setイベント(kosinSimilarInquiryMgmt.getイベント());
            kosinSimilarInquiryMgmtTarget.setイベント日時(kosinSimilarInquiryMgmt.getイベント日時());
            kosinSimilarInquiryMgmtTarget.set状態(kosinSimilarInquiryMgmt.get状態());
            kosinSimilarInquiryMgmtTarget.set状態説明(kosinSimilarInquiryMgmt.get状態説明());
            kosinSimilarInquiryMgmtTarget.set優先度(kosinSimilarInquiryMgmt.get優先度());
            kosinSimilarInquiryMgmtTargetMapper.insert(kosinSimilarInquiryMgmtTarget);
        }

        // ③-d8 個信類似照会明細 (MAX only) — 1:N per (申込番号, 申込目的), pass-through.
        List<個信類似照会明細Source> kosinSimilarInquiryDetails =
                emptyIfNull(kosinSimilarInquiryDetailSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (個信類似照会明細Source kosinSimilarInquiryDetail : kosinSimilarInquiryDetails) {
            個信類似照会明細Target kosinSimilarInquiryDetailTarget = new 個信類似照会明細Target();
            kosinSimilarInquiryDetailTarget.set申込番号(targetApplicationNumber);
            kosinSimilarInquiryDetailTarget.set申込目的(convertedPurpose);
            kosinSimilarInquiryDetailTarget.setイベント(kosinSimilarInquiryDetail.getイベント());
            kosinSimilarInquiryDetailTarget.setイベント日時(kosinSimilarInquiryDetail.getイベント日時());
            kosinSimilarInquiryDetailTarget.set受付番号(kosinSimilarInquiryDetail.get受付番号());
            kosinSimilarInquiryDetailTarget.set受付日時(kosinSimilarInquiryDetail.get受付日時());
            kosinSimilarInquiryDetailTarget.set情報区分(kosinSimilarInquiryDetail.get情報区分());
            kosinSimilarInquiryDetailTarget.set氏名カナ(kosinSimilarInquiryDetail.get氏名カナ());
            kosinSimilarInquiryDetailTarget.set氏名漢字(kosinSimilarInquiryDetail.get氏名漢字());
            kosinSimilarInquiryDetailTarget.set性別(kosinSimilarInquiryDetail.get性別());
            kosinSimilarInquiryDetailTarget.set生年月日(kosinSimilarInquiryDetail.get生年月日());
            kosinSimilarInquiryDetailTarget.set電話番号1(kosinSimilarInquiryDetail.get電話番号1());
            kosinSimilarInquiryDetailTarget.set電話番号2(kosinSimilarInquiryDetail.get電話番号2());
            kosinSimilarInquiryDetailTarget.set郵便番号(kosinSimilarInquiryDetail.get郵便番号());
            kosinSimilarInquiryDetailTarget.set住所カナ(kosinSimilarInquiryDetail.get住所カナ());
            kosinSimilarInquiryDetailTarget.set住所漢字(kosinSimilarInquiryDetail.get住所漢字());
            kosinSimilarInquiryDetailTarget.set勤務先名(kosinSimilarInquiryDetail.get勤務先名());
            kosinSimilarInquiryDetailTarget.set勤務先電話番号(kosinSimilarInquiryDetail.get勤務先電話番号());
            kosinSimilarInquiryDetailTarget.set本人識別コード(kosinSimilarInquiryDetail.get本人識別コード());
            kosinSimilarInquiryDetailTarget.set情報特定コード(kosinSimilarInquiryDetail.get情報特定コード());
            kosinSimilarInquiryDetailTarget.set再照会＿情報区分(kosinSimilarInquiryDetail.get再照会＿情報区分());
            kosinSimilarInquiryDetailTarget.set再照会＿受付番号(kosinSimilarInquiryDetail.get再照会＿受付番号());
            kosinSimilarInquiryDetailTarget.set再照会＿受付日時(kosinSimilarInquiryDetail.get再照会＿受付日時());
            kosinSimilarInquiryDetailTarget.set再照会＿会員名(kosinSimilarInquiryDetail.get再照会＿会員名());
            kosinSimilarInquiryDetailTarget.set再照会＿回答通番(kosinSimilarInquiryDetail.get再照会＿回答通番());
            kosinSimilarInquiryDetailTarget.set再照会＿電文種別(kosinSimilarInquiryDetail.get再照会＿電文種別());
            kosinSimilarInquiryDetailTarget.set顧客コード(kosinSimilarInquiryDetail.get顧客コード());
            kosinSimilarInquiryDetailTargetMapper.insert(kosinSimilarInquiryDetailTarget);
        }

        // ③-d9 個信類似明細 (MAX only) — 1:N per (申込番号, 申込目的), pass-through.
        List<個信類似明細Source> kosinSimilarDetails =
                emptyIfNull(kosinSimilarDetailSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (個信類似明細Source kosinSimilarDetail : kosinSimilarDetails) {
            個信類似明細Target kosinSimilarDetailTarget = new 個信類似明細Target();
            kosinSimilarDetailTarget.set申込番号(targetApplicationNumber);
            kosinSimilarDetailTarget.set申込目的(convertedPurpose);
            kosinSimilarDetailTarget.setイベント(kosinSimilarDetail.getイベント());
            kosinSimilarDetailTarget.setイベント日時(kosinSimilarDetail.getイベント日時());
            kosinSimilarDetailTarget.set連番(kosinSimilarDetail.get連番());
            kosinSimilarDetailTarget.set情報区分(kosinSimilarDetail.get情報区分());
            kosinSimilarDetailTarget.set受付番号(kosinSimilarDetail.get受付番号());
            kosinSimilarDetailTarget.set受付日時(kosinSimilarDetail.get受付日時());
            kosinSimilarDetailTarget.set会員名(kosinSimilarDetail.get会員名());
            kosinSimilarDetailTarget.set回答通番(kosinSimilarDetail.get回答通番());
            kosinSimilarDetailTarget.set電文種別(kosinSimilarDetail.get電文種別());
            kosinSimilarDetailTarget.set親回答通番(kosinSimilarDetail.get親回答通番());
            kosinSimilarDetailTarget.set結付通番(kosinSimilarDetail.get結付通番());
            kosinSimilarDetailTarget.set照会回答区分(kosinSimilarDetail.get照会回答区分());
            kosinSimilarDetailTarget.set照会データ種別(kosinSimilarDetail.get照会データ種別());
            kosinSimilarDetailTarget.set債権分類(kosinSimilarDetail.get債権分類());
            kosinSimilarDetailTarget.set状態(kosinSimilarDetail.get状態());
            kosinSimilarDetailTarget.set一致タイプ(kosinSimilarDetail.get一致タイプ());
            kosinSimilarDetailTarget.set氏名カナ(kosinSimilarDetail.get氏名カナ());
            kosinSimilarDetailTarget.set氏名漢字(kosinSimilarDetail.get氏名漢字());
            kosinSimilarDetailTarget.set性別(kosinSimilarDetail.get性別());
            kosinSimilarDetailTarget.set生年月日(kosinSimilarDetail.get生年月日());
            kosinSimilarDetailTarget.set電話番号1(kosinSimilarDetail.get電話番号1());
            kosinSimilarDetailTarget.set電話番号2(kosinSimilarDetail.get電話番号2());
            kosinSimilarDetailTarget.set郵便番号(kosinSimilarDetail.get郵便番号());
            kosinSimilarDetailTarget.set住所カナ(kosinSimilarDetail.get住所カナ());
            kosinSimilarDetailTarget.set住所漢字(kosinSimilarDetail.get住所漢字());
            kosinSimilarDetailTarget.set勤務先名(kosinSimilarDetail.get勤務先名());
            kosinSimilarDetailTarget.set勤務先電話番号(kosinSimilarDetail.get勤務先電話番号());
            kosinSimilarDetailTarget.set公的資料1区分(kosinSimilarDetail.get公的資料1区分());
            kosinSimilarDetailTarget.set公的資料1番号(kosinSimilarDetail.get公的資料1番号());
            kosinSimilarDetailTarget.set公的資料2区分(kosinSimilarDetail.get公的資料2区分());
            kosinSimilarDetailTarget.set公的資料2番号(kosinSimilarDetail.get公的資料2番号());
            kosinSimilarDetailTarget.set情報識別区分(kosinSimilarDetail.get情報識別区分());
            kosinSimilarDetailTarget.set本人識別コード(kosinSimilarDetail.get本人識別コード());
            kosinSimilarDetailTarget.set情報特定コード(kosinSimilarDetail.get情報特定コード());
            kosinSimilarDetailTarget.set顧客コード(kosinSimilarDetail.get顧客コード());
            kosinSimilarDetailTargetMapper.insert(kosinSimilarDetailTarget);
        }

        // ③-d10 審査ＪＩＣＣ信用情報詳細 (MAX only) — 1:N per (申込番号, 申込目的), pass-through.
        List<審査ＪＩＣＣ信用情報詳細Source> reviewJiccCreditDetails =
                emptyIfNull(reviewJiccCreditDetailSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (審査ＪＩＣＣ信用情報詳細Source reviewJiccCreditDetail : reviewJiccCreditDetails) {
            if (reviewJiccCreditDetail == null) {
                continue;
            }
            審査ＪＩＣＣ信用情報詳細Target reviewJiccCreditDetailTarget = new 審査ＪＩＣＣ信用情報詳細Target();
            reviewJiccCreditDetailTarget.set申込番号(targetApplicationNumber);
            reviewJiccCreditDetailTarget.set申込目的(convertedPurpose);
            reviewJiccCreditDetailTarget.setイベント(reviewJiccCreditDetail.getイベント());
            reviewJiccCreditDetailTarget.setイベント日時(reviewJiccCreditDetail.getイベント日時());
            reviewJiccCreditDetailTarget.set連番(reviewJiccCreditDetail.get連番());
            reviewJiccCreditDetailTarget.set別名連番(reviewJiccCreditDetail.get別名連番());
            reviewJiccCreditDetailTarget.set詳細連番(reviewJiccCreditDetail.get詳細連番());
            reviewJiccCreditDetailTarget.set受付日時(reviewJiccCreditDetail.get受付日時());
            reviewJiccCreditDetailTarget.set受付番号(reviewJiccCreditDetail.get受付番号());
            reviewJiccCreditDetailTarget.set会員名(reviewJiccCreditDetail.get会員名());
            reviewJiccCreditDetailTarget.set親回答通番(reviewJiccCreditDetail.get親回答通番());
            reviewJiccCreditDetailTarget.set回答通番(reviewJiccCreditDetail.get回答通番());
            reviewJiccCreditDetailTarget.set照会回答区分(reviewJiccCreditDetail.get照会回答区分());
            reviewJiccCreditDetailTarget.set債権分類(reviewJiccCreditDetail.get債権分類());
            reviewJiccCreditDetailTarget.set照会データ種別(reviewJiccCreditDetail.get照会データ種別());
            reviewJiccCreditDetailTarget.setテーブル名(reviewJiccCreditDetail.getテーブル名());
            reviewJiccCreditDetailTarget.set項目名(reviewJiccCreditDetail.get項目名());
            reviewJiccCreditDetailTarget.setコード番号(reviewJiccCreditDetail.getコード番号());
            reviewJiccCreditDetailTarget.setコード(reviewJiccCreditDetail.getコード());
            reviewJiccCreditDetailTarget.setコード名称(reviewJiccCreditDetail.getコード名称());
            reviewJiccCreditDetailTarget.set氏名(reviewJiccCreditDetail.get氏名());
            reviewJiccCreditDetailTarget.set氏名カナ(reviewJiccCreditDetail.get氏名カナ());
            reviewJiccCreditDetailTarget.set種類(reviewJiccCreditDetail.get種類());
            reviewJiccCreditDetailTarget.set信用情報判断(reviewJiccCreditDetail.get信用情報判断());
            reviewJiccCreditDetailTarget.set信用情報(reviewJiccCreditDetail.get信用情報());
            reviewJiccCreditDetailTarget.set判断項目名1(reviewJiccCreditDetail.get判断項目名1());
            reviewJiccCreditDetailTarget.set判断項目1(reviewJiccCreditDetail.get判断項目1());
            reviewJiccCreditDetailTarget.set判断項目名2(reviewJiccCreditDetail.get判断項目名2());
            reviewJiccCreditDetailTarget.set判断項目2(reviewJiccCreditDetail.get判断項目2());
            reviewJiccCreditDetailTarget.set判断項目名3(reviewJiccCreditDetail.get判断項目名3());
            reviewJiccCreditDetailTarget.set判断項目3(reviewJiccCreditDetail.get判断項目3());
            reviewJiccCreditDetailTarget.set判断項目名4(reviewJiccCreditDetail.get判断項目名4());
            reviewJiccCreditDetailTarget.set判断項目4(reviewJiccCreditDetail.get判断項目4());
            reviewJiccCreditDetailTarget.set判断項目名5(reviewJiccCreditDetail.get判断項目名5());
            reviewJiccCreditDetailTarget.set判断項目5(reviewJiccCreditDetail.get判断項目5());
            reviewJiccCreditDetailTarget.setブラック判断(reviewJiccCreditDetail.getブラック判断());
            reviewJiccCreditDetailTarget.set発生日(reviewJiccCreditDetail.get発生日());
            reviewJiccCreditDetailTarget.set契約日(reviewJiccCreditDetail.get契約日());
            reviewJiccCreditDetailTargetMapper.insert(reviewJiccCreditDetailTarget);
        }

        // ③-d11 審査ＣＩＣ信用情報詳細 (MAX only) — 1:N per (申込番号, 申込目的), pass-through.
        List<審査ＣＩＣ信用情報詳細Source> reviewCicCreditDetails =
                emptyIfNull(reviewCicCreditDetailSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (審査ＣＩＣ信用情報詳細Source reviewCicCreditDetail : reviewCicCreditDetails) {
            if (reviewCicCreditDetail == null) {
                continue;
            }
            審査ＣＩＣ信用情報詳細Target reviewCicCreditDetailTarget = new 審査ＣＩＣ信用情報詳細Target();
            reviewCicCreditDetailTarget.set申込番号(targetApplicationNumber);
            reviewCicCreditDetailTarget.set申込目的(convertedPurpose);
            reviewCicCreditDetailTarget.setイベント(reviewCicCreditDetail.getイベント());
            reviewCicCreditDetailTarget.setイベント日時(reviewCicCreditDetail.getイベント日時());
            reviewCicCreditDetailTarget.set連番(reviewCicCreditDetail.get連番());
            reviewCicCreditDetailTarget.set別名連番(reviewCicCreditDetail.get別名連番());
            reviewCicCreditDetailTarget.set詳細連番(reviewCicCreditDetail.get詳細連番());
            reviewCicCreditDetailTarget.set受付日時(reviewCicCreditDetail.get受付日時());
            reviewCicCreditDetailTarget.set受付番号(reviewCicCreditDetail.get受付番号());
            reviewCicCreditDetailTarget.set会員名(reviewCicCreditDetail.get会員名());
            reviewCicCreditDetailTarget.set回答通番(reviewCicCreditDetail.get回答通番());
            reviewCicCreditDetailTarget.set電文種別(reviewCicCreditDetail.get電文種別());
            reviewCicCreditDetailTarget.setテーブル名(reviewCicCreditDetail.getテーブル名());
            reviewCicCreditDetailTarget.set項目名(reviewCicCreditDetail.get項目名());
            reviewCicCreditDetailTarget.setコード番号(reviewCicCreditDetail.getコード番号());
            reviewCicCreditDetailTarget.setコード(reviewCicCreditDetail.getコード());
            reviewCicCreditDetailTarget.setコード名称(reviewCicCreditDetail.getコード名称());
            reviewCicCreditDetailTarget.set氏名(reviewCicCreditDetail.get氏名());
            reviewCicCreditDetailTarget.set氏名カナ(reviewCicCreditDetail.get氏名カナ());
            reviewCicCreditDetailTarget.set種類(reviewCicCreditDetail.get種類());
            reviewCicCreditDetailTarget.set信用情報判断(reviewCicCreditDetail.get信用情報判断());
            reviewCicCreditDetailTarget.set信用情報(reviewCicCreditDetail.get信用情報());
            reviewCicCreditDetailTarget.set判断項目名1(reviewCicCreditDetail.get判断項目名1());
            reviewCicCreditDetailTarget.set判断項目1(reviewCicCreditDetail.get判断項目1());
            reviewCicCreditDetailTarget.set判断項目名2(reviewCicCreditDetail.get判断項目名2());
            reviewCicCreditDetailTarget.set判断項目2(reviewCicCreditDetail.get判断項目2());
            reviewCicCreditDetailTarget.set判断項目名3(reviewCicCreditDetail.get判断項目名3());
            reviewCicCreditDetailTarget.set判断項目3(reviewCicCreditDetail.get判断項目3());
            reviewCicCreditDetailTarget.set判断項目名4(reviewCicCreditDetail.get判断項目名4());
            reviewCicCreditDetailTarget.set判断項目4(reviewCicCreditDetail.get判断項目4());
            reviewCicCreditDetailTarget.set判断項目名5(reviewCicCreditDetail.get判断項目名5());
            reviewCicCreditDetailTarget.set判断項目5(reviewCicCreditDetail.get判断項目5());
            reviewCicCreditDetailTarget.setブラック判断(reviewCicCreditDetail.getブラック判断());
            reviewCicCreditDetailTarget.set発生日(reviewCicCreditDetail.get発生日());
            reviewCicCreditDetailTarget.set契約日(reviewCicCreditDetail.get契約日());
            reviewCicCreditDetailTargetMapper.insert(reviewCicCreditDetailTarget);
        }

        // ③-d12 個信データ編集管理 (MAX only) — 1:N per (申込番号, 申込目的), pass-through.
        List<個信データ編集管理Source> kosinDataEditMgmts =
                emptyIfNull(kosinDataEditMgmtSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (個信データ編集管理Source kosinDataEditMgmt : kosinDataEditMgmts) {
            if (kosinDataEditMgmt == null) {
                continue;
            }
            個信データ編集管理Target kosinDataEditMgmtTarget = new 個信データ編集管理Target();
            kosinDataEditMgmtTarget.set申込番号(targetApplicationNumber);
            kosinDataEditMgmtTarget.set申込目的(convertedPurpose);
            kosinDataEditMgmtTarget.setイベント(kosinDataEditMgmt.getイベント());
            kosinDataEditMgmtTarget.setイベント日時(kosinDataEditMgmt.getイベント日時());
            kosinDataEditMgmtTarget.set状態(kosinDataEditMgmt.get状態());
            kosinDataEditMgmtTarget.set状態説明(kosinDataEditMgmt.get状態説明());
            kosinDataEditMgmtTarget.set優先度(kosinDataEditMgmt.get優先度());
            kosinDataEditMgmtTargetMapper.insert(kosinDataEditMgmtTarget);
        }

        // ③-d13 返済比率計算 (MAX only) — 1:N per (申込番号, 申込目的), pass-through.
        List<返済比率計算Source> repaymentRatioCalcs =
                emptyIfNull(repaymentRatioCalcSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (返済比率計算Source repaymentRatioCalc : repaymentRatioCalcs) {
            if (repaymentRatioCalc == null) {
                continue;
            }
            返済比率計算Target repaymentRatioCalcTarget = new 返済比率計算Target();
            repaymentRatioCalcTarget.set申込番号(targetApplicationNumber);
            repaymentRatioCalcTarget.set申込目的(convertedPurpose);
            repaymentRatioCalcTarget.setイベント(repaymentRatioCalc.getイベント());
            repaymentRatioCalcTarget.setイベント日時(repaymentRatioCalc.getイベント日時());
            repaymentRatioCalcTarget.set状態(repaymentRatioCalc.get状態());
            repaymentRatioCalcTarget.set状態説明(repaymentRatioCalc.get状態説明());
            repaymentRatioCalcTarget.set優先度(repaymentRatioCalc.get優先度());
            repaymentRatioCalcTargetMapper.insert(repaymentRatioCalcTarget);
        }

        // ③-d14 返済比率計算結果 (MAX only) — 1:N per (申込番号, 申込目的), pass-through.
        List<返済比率計算結果Source> repaymentRatioResults =
                emptyIfNull(repaymentRatioResultSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (返済比率計算結果Source src : repaymentRatioResults) {
            if (src == null) {
                continue;
            }
            返済比率計算結果Target t = new 返済比率計算結果Target();
            t.set申込番号(targetApplicationNumber);
            t.set申込目的(convertedPurpose);
            t.setイベント(src.getイベント());
            t.setイベント日時(src.getイベント日時());
            t.set計算年収(src.get計算年収());
            t.set借入総額(src.get借入総額());
            t.set無担保借入額(src.get無担保借入額());
            t.set年間返済額(src.get年間返済額());
            t.set総額借入比率(src.get総額借入比率());
            t.set無担保借入比率(src.get無担保借入比率());
            t.set総額返済比率(src.get総額返済比率());
            t.set無担保年間返済額(src.get無担保年間返済額());
            t.set申告借入総額(src.get申告借入総額());
            t.set申告無担保借入額(src.get申告無担保借入額());
            t.set申告年間返済額(src.get申告年間返済額());
            t.set申告総額借入比率(src.get申告総額借入比率());
            t.set申告無担保借入比率(src.get申告無担保借入比率());
            t.set申告総額返済比率(src.get申告総額返済比率());
            t.set申告無担保年間返済額(src.get申告無担保年間返済額());
            t.set今回借入総額(src.get今回借入総額());
            t.set今回無担保借入額(src.get今回無担保借入額());
            t.set今回年間返済額(src.get今回年間返済額());
            t.set今回総額借入比率(src.get今回総額借入比率());
            t.set今回無担保借入比率(src.get今回無担保借入比率());
            t.set今回総額返済比率(src.get今回総額返済比率());
            t.set今回無担保年間返済額(src.get今回無担保年間返済額());
            t.set自行借入総額(src.get自行借入総額());
            t.set自行無担保借入額(src.get自行無担保借入額());
            t.set自行年間返済額(src.get自行年間返済額());
            t.set自行総額借入比率(src.get自行総額借入比率());
            t.set自行無担保借入比率(src.get自行無担保借入比率());
            t.set自行総額返済比率(src.get自行総額返済比率());
            t.set自行無担保年間返済額(src.get自行無担保年間返済額());
            t.setＫＳＣ借入総額(src.getＫＳＣ借入総額());
            t.setＫＳＣ無担保借入額(src.getＫＳＣ無担保借入額());
            t.setＫＳＣ年間返済額(src.getＫＳＣ年間返済額());
            t.setＫＳＣ総額借入比率(src.getＫＳＣ総額借入比率());
            t.setＫＳＣ無担保借入比率(src.getＫＳＣ無担保借入比率());
            t.setＫＳＣ総額返済比率(src.getＫＳＣ総額返済比率());
            t.setＫＳＣ無担保年間返済額(src.getＫＳＣ無担保年間返済額());
            t.setＣＩＣ借入総額(src.getＣＩＣ借入総額());
            t.setＣＩＣ無担保借入額(src.getＣＩＣ無担保借入額());
            t.setＣＩＣ年間返済額(src.getＣＩＣ年間返済額());
            t.setＣＩＣ総額借入比率(src.getＣＩＣ総額借入比率());
            t.setＣＩＣ無担保借入比率(src.getＣＩＣ無担保借入比率());
            t.setＣＩＣ総額返済比率(src.getＣＩＣ総額返済比率());
            t.setＣＩＣ無担保年間返済額(src.getＣＩＣ無担保年間返済額());
            t.setＣＣＢ借入総額(src.getＣＣＢ借入総額());
            t.setＣＣＢ無担保借入額(src.getＣＣＢ無担保借入額());
            t.setＣＣＢ年間返済額(src.getＣＣＢ年間返済額());
            t.setＣＣＢ総額借入比率(src.getＣＣＢ総額借入比率());
            t.setＣＣＢ無担保借入比率(src.getＣＣＢ無担保借入比率());
            t.setＣＣＢ総額返済比率(src.getＣＣＢ総額返済比率());
            t.setＣＣＢ無担保年間返済額(src.getＣＣＢ無担保年間返済額());
            t.setＪＩＣＣ借入総額(src.getＪＩＣＣ借入総額());
            t.setＪＩＣＣ無担保借入額(src.getＪＩＣＣ無担保借入額());
            t.setＪＩＣＣ年間返済額(src.getＪＩＣＣ年間返済額());
            t.setＪＩＣＣ総額借入比率(src.getＪＩＣＣ総額借入比率());
            t.setＪＩＣＣ無担保借入比率(src.getＪＩＣＣ無担保借入比率());
            t.setＪＩＣＣ総額返済比率(src.getＪＩＣＣ総額返済比率());
            t.setＪＩＣＣ無担保年間返済額(src.getＪＩＣＣ無担保年間返済額());
            repaymentRatioResultTargetMapper.insert(t);
        }

        // ③-d15 返済比率計算結果明細 (MAX only) — 1:N per (申込番号, 申込目的) keyed with 連番, pass-through.
        List<返済比率計算結果明細Source> repaymentRatioResultDetails =
                emptyIfNull(repaymentRatioResultDetailSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (返済比率計算結果明細Source src : repaymentRatioResultDetails) {
            if (src == null) {
                continue;
            }
            返済比率計算結果明細Target t = new 返済比率計算結果明細Target();
            t.set申込番号(targetApplicationNumber);
            t.set申込目的(convertedPurpose);
            t.setイベント(src.getイベント());
            t.setイベント日時(src.getイベント日時());
            t.set連番(src.get連番());
            t.set計算年収(src.get計算年収());
            t.set借入総額(src.get借入総額());
            t.set無担保借入額(src.get無担保借入額());
            t.set年間返済額(src.get年間返済額());
            t.set総額借入比率(src.get総額借入比率());
            t.set無担保借入比率(src.get無担保借入比率());
            t.set総額返済比率(src.get総額返済比率());
            t.set無担保年間返済額(src.get無担保年間返済額());
            t.set申告借入総額(src.get申告借入総額());
            t.set申告無担保借入額(src.get申告無担保借入額());
            t.set申告年間返済額(src.get申告年間返済額());
            t.set申告総額借入比率(src.get申告総額借入比率());
            t.set申告無担保借入比率(src.get申告無担保借入比率());
            t.set申告総額返済比率(src.get申告総額返済比率());
            t.set申告無担保年間返済額(src.get申告無担保年間返済額());
            t.set今回借入総額(src.get今回借入総額());
            t.set今回無担保借入額(src.get今回無担保借入額());
            t.set今回年間返済額(src.get今回年間返済額());
            t.set今回総額借入比率(src.get今回総額借入比率());
            t.set今回無担保借入比率(src.get今回無担保借入比率());
            t.set今回総額返済比率(src.get今回総額返済比率());
            t.set今回無担保年間返済額(src.get今回無担保年間返済額());
            t.set自行借入総額(src.get自行借入総額());
            t.set自行無担保借入額(src.get自行無担保借入額());
            t.set自行年間返済額(src.get自行年間返済額());
            t.set自行総額借入比率(src.get自行総額借入比率());
            t.set自行無担保借入比率(src.get自行無担保借入比率());
            t.set自行総額返済比率(src.get自行総額返済比率());
            t.set自行無担保年間返済額(src.get自行無担保年間返済額());
            t.setＫＳＣ借入総額(src.getＫＳＣ借入総額());
            t.setＫＳＣ無担保借入額(src.getＫＳＣ無担保借入額());
            t.setＫＳＣ年間返済額(src.getＫＳＣ年間返済額());
            t.setＫＳＣ総額借入比率(src.getＫＳＣ総額借入比率());
            t.setＫＳＣ無担保借入比率(src.getＫＳＣ無担保借入比率());
            t.setＫＳＣ総額返済比率(src.getＫＳＣ総額返済比率());
            t.setＫＳＣ無担保年間返済額(src.getＫＳＣ無担保年間返済額());
            t.setＣＩＣ借入総額(src.getＣＩＣ借入総額());
            t.setＣＩＣ無担保借入額(src.getＣＩＣ無担保借入額());
            t.setＣＩＣ年間返済額(src.getＣＩＣ年間返済額());
            t.setＣＩＣ総額借入比率(src.getＣＩＣ総額借入比率());
            t.setＣＩＣ無担保借入比率(src.getＣＩＣ無担保借入比率());
            t.setＣＩＣ総額返済比率(src.getＣＩＣ総額返済比率());
            t.setＣＩＣ無担保年間返済額(src.getＣＩＣ無担保年間返済額());
            t.setＣＣＢ借入総額(src.getＣＣＢ借入総額());
            t.setＣＣＢ無担保借入額(src.getＣＣＢ無担保借入額());
            t.setＣＣＢ年間返済額(src.getＣＣＢ年間返済額());
            t.setＣＣＢ総額借入比率(src.getＣＣＢ総額借入比率());
            t.setＣＣＢ無担保借入比率(src.getＣＣＢ無担保借入比率());
            t.setＣＣＢ総額返済比率(src.getＣＣＢ総額返済比率());
            t.setＣＣＢ無担保年間返済額(src.getＣＣＢ無担保年間返済額());
            t.setＪＩＣＣ借入総額(src.getＪＩＣＣ借入総額());
            t.setＪＩＣＣ無担保借入額(src.getＪＩＣＣ無担保借入額());
            t.setＪＩＣＣ年間返済額(src.getＪＩＣＣ年間返済額());
            t.setＪＩＣＣ総額借入比率(src.getＪＩＣＣ総額借入比率());
            t.setＪＩＣＣ無担保借入比率(src.getＪＩＣＣ無担保借入比率());
            t.setＪＩＣＣ総額返済比率(src.getＪＩＣＣ総額返済比率());
            t.setＪＩＣＣ無担保年間返済額(src.getＪＩＣＣ無担保年間返済額());
            repaymentRatioResultDetailTargetMapper.insert(t);
        }

        // ③-d16 審査モデル回答 (MAX only) — 1:N per (申込番号, 申込目的), pass-through.
        List<審査モデル回答Source> scoringAnswers =
                emptyIfNull(scoringAnswerSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (審査モデル回答Source src : scoringAnswers) {
            if (src == null) {
                continue;
            }
            審査モデル回答Target t = new 審査モデル回答Target();
            t.set申込番号(targetApplicationNumber);
            t.set申込目的(convertedPurpose);
            t.setイベント(src.getイベント());
            t.setイベント日時(src.getイベント日時());
            t.setセグメント(src.getセグメント());
            t.setスコア(src.getスコア());
            scoringAnswerTargetMapper.insert(t);
        }

        // ③-d17 審査モデル回答Ｓ (MAX only) — 1:N per (申込番号, 申込目的), pass-through.
        List<審査モデル回答ＳSource> scoringAnswerS =
                emptyIfNull(scoringAnswerSSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (審査モデル回答ＳSource src : scoringAnswerS) {
            if (src == null) {
                continue;
            }
            審査モデル回答ＳTarget t = new 審査モデル回答ＳTarget();
            t.set申込番号(targetApplicationNumber);
            t.set申込目的(convertedPurpose);
            t.setイベント(src.getイベント());
            t.setイベント日時(src.getイベント日時());
            t.setセグメント(src.getセグメント());
            t.setスコア(src.getスコア());
            scoringAnswerSTargetMapper.insert(t);
        }

        // ③-d18 審査モデル回答判定 (MAX only) — 1:N per (申込番号, 申込目的), pass-through.
        List<審査モデル回答判定Source> scoringJudges =
                emptyIfNull(scoringJudgeSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (審査モデル回答判定Source src : scoringJudges) {
            if (src == null) {
                continue;
            }
            審査モデル回答判定Target t = new 審査モデル回答判定Target();
            t.set申込番号(targetApplicationNumber);
            t.set申込目的(convertedPurpose);
            t.setイベント(src.getイベント());
            t.setイベント日時(src.getイベント日時());
            t.set融資倍率(src.get融資倍率());
            t.set規定外項目(src.get規定外項目());
            t.set担保掛目(src.get担保掛目());
            t.set上限融資金額(src.get上限融資金額());
            t.set判定(src.get判定());
            scoringJudgeTargetMapper.insert(t);
        }

        // ③-d19 審査モデル回答判定Ｓ (MAX only) — 1:N per (申込番号, 申込目的), pass-through.
        List<審査モデル回答判定ＳSource> scoringJudgeS =
                emptyIfNull(scoringJudgeSSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (審査モデル回答判定ＳSource src : scoringJudgeS) {
            if (src == null) {
                continue;
            }
            審査モデル回答判定ＳTarget t = new 審査モデル回答判定ＳTarget();
            t.set申込番号(targetApplicationNumber);
            t.set申込目的(convertedPurpose);
            t.setイベント(src.getイベント());
            t.setイベント日時(src.getイベント日時());
            t.set融資倍率(src.get融資倍率());
            t.set規定外項目(src.get規定外項目());
            t.set担保掛目(src.get担保掛目());
            t.set上限融資金額(src.get上限融資金額());
            t.set判定(src.get判定());
            scoringJudgeSTargetMapper.insert(t);
        }

        // ③-d20 審査モデル回答明細 (MAX only) — 1:N per (申込番号, 申込目的), pass-through.
        List<審査モデル回答明細Source> scoringDetails =
                emptyIfNull(scoringDetailSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (審査モデル回答明細Source src : scoringDetails) {
            if (src == null) {
                continue;
            }
            審査モデル回答明細Target t = new 審査モデル回答明細Target();
            t.set申込番号(targetApplicationNumber);
            t.set申込目的(convertedPurpose);
            t.setイベント(src.getイベント());
            t.setイベント日時(src.getイベント日時());
            t.set勤続年数評点(src.get勤続年数評点());
            t.set勤続年数係数(src.get勤続年数係数());
            t.set勤続年数スコア(src.get勤続年数スコア());
            t.set借入年数評点(src.get借入年数評点());
            t.set借入年数係数(src.get借入年数係数());
            t.set借入年数スコア(src.get借入年数スコア());
            t.set資本金評点(src.get資本金評点());
            t.set資本金係数(src.get資本金係数());
            t.set資本金スコア(src.get資本金スコア());
            t.set合算返済比率評点(src.get合算返済比率評点());
            t.set合算返済比率係数(src.get合算返済比率係数());
            t.set合算返済比率スコア(src.get合算返済比率スコア());
            t.set合算返済比率(src.get合算返済比率());
            t.set自己資金比率評点(src.get自己資金比率評点());
            t.set自己資金比率係数(src.get自己資金比率係数());
            t.set自己資金比率スコア(src.get自己資金比率スコア());
            t.set自己資金比率(src.get自己資金比率());
            t.set調整定数(src.get調整定数());
            t.set若年単身者評点(src.get若年単身者評点());
            t.set若年単身者係数(src.get若年単身者係数());
            t.set若年単身者スコア(src.get若年単身者スコア());
            scoringDetailTargetMapper.insert(t);
        }

        // ③-d21 審査モデル回答明細Ｓ (MAX only) — 1:N per (申込番号, 申込目的), pass-through.
        List<審査モデル回答明細ＳSource> scoringDetailS =
                emptyIfNull(scoringDetailSSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (審査モデル回答明細ＳSource src : scoringDetailS) {
            if (src == null) {
                continue;
            }
            審査モデル回答明細ＳTarget t = new 審査モデル回答明細ＳTarget();
            t.set申込番号(targetApplicationNumber);
            t.set申込目的(convertedPurpose);
            t.setイベント(src.getイベント());
            t.setイベント日時(src.getイベント日時());
            t.set勤続年数評点(src.get勤続年数評点());
            t.set勤続年数係数(src.get勤続年数係数());
            t.set勤続年数スコア(src.get勤続年数スコア());
            t.set借入年数評点(src.get借入年数評点());
            t.set借入年数係数(src.get借入年数係数());
            t.set借入年数スコア(src.get借入年数スコア());
            t.set資本金評点(src.get資本金評点());
            t.set資本金係数(src.get資本金係数());
            t.set資本金スコア(src.get資本金スコア());
            t.set合算返済比率評点(src.get合算返済比率評点());
            t.set合算返済比率係数(src.get合算返済比率係数());
            t.set合算返済比率スコア(src.get合算返済比率スコア());
            t.set合算返済比率(src.get合算返済比率());
            t.set自己資金比率評点(src.get自己資金比率評点());
            t.set自己資金比率係数(src.get自己資金比率係数());
            t.set自己資金比率スコア(src.get自己資金比率スコア());
            t.set自己資金比率(src.get自己資金比率());
            t.set調整定数(src.get調整定数());
            t.set若年単身者評点(src.get若年単身者評点());
            t.set若年単身者係数(src.get若年単身者係数());
            t.set若年単身者スコア(src.get若年単身者スコア());
            scoringDetailSTargetMapper.insert(t);
        }

        // ③-d22 審査モデル照会 (MAX only) — 1:N per (申込番号, 申込目的), pass-through.
        List<審査モデル照会Source> scoringInquiries =
                emptyIfNull(scoringInquirySourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (審査モデル照会Source src : scoringInquiries) {
            if (src == null) {
                continue;
            }
            審査モデル照会Target t = new 審査モデル照会Target();
            t.set申込番号(targetApplicationNumber);
            t.set申込目的(convertedPurpose);
            t.setイベント(src.getイベント());
            t.setイベント日時(src.getイベント日時());
            t.set状態(src.get状態());
            t.set状態説明(src.get状態説明());
            t.set優先度(src.get優先度());
            scoringInquiryTargetMapper.insert(t);
        }

        // ③-d23 審査モデル照会Ｓ (MAX only) — 1:N per (申込番号, 申込目的), pass-through.
        List<審査モデル照会ＳSource> scoringInquiryS =
                emptyIfNull(scoringInquirySSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (審査モデル照会ＳSource src : scoringInquiryS) {
            if (src == null) {
                continue;
            }
            審査モデル照会ＳTarget t = new 審査モデル照会ＳTarget();
            t.set申込番号(targetApplicationNumber);
            t.set申込目的(convertedPurpose);
            t.setイベント(src.getイベント());
            t.setイベント日時(src.getイベント日時());
            t.set状態(src.get状態());
            t.set状態説明(src.get状態説明());
            t.set優先度(src.get優先度());
            scoringInquirySTargetMapper.insert(t);
        }

        // ③-d24 審査モデル照会基本 (MAX only) — 1:N per (申込番号, 申込目的), pass-through.
        List<審査モデル照会基本Source> scoringInquiryBasics =
                emptyIfNull(scoringInquiryBasicSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (審査モデル照会基本Source src : scoringInquiryBasics) {
            if (src == null) {
                continue;
            }
            審査モデル照会基本Target t = new 審査モデル照会基本Target();
            t.set申込番号(targetApplicationNumber);
            t.set申込目的(convertedPurpose);
            t.setイベント(src.getイベント());
            t.setイベント日時(src.getイベント日時());
            t.set申込時年齢(src.get申込時年齢());
            t.set実行時年齢(src.get実行時年齢());
            t.set本人年収(src.get本人年収());
            t.set合算者年収(src.get合算者年収());
            t.set合算方法(src.get合算方法());
            t.set申込金額(src.get申込金額());
            t.set借入期間(src.get借入期間());
            t.set資金使途(src.get資金使途());
            t.set勤続年数(src.get勤続年数());
            t.set資本金区分(src.get資本金区分());
            t.set年間返済額(src.get年間返済額());
            t.set総借入額(src.get総借入額());
            t.set自己資金(src.get自己資金());
            t.set所要資金(src.get所要資金());
            t.set規定外項目(src.get規定外項目());
            t.set担保評価額(src.get担保評価額());
            t.set保証料区分(src.get保証料区分());
            t.set先順位控除額(src.get先順位控除額());
            t.set控除前担保評価額(src.get控除前担保評価額());
            t.set同居家族数(src.get同居家族数());
            scoringInquiryBasicTargetMapper.insert(t);
        }

        // ③-d25 審査モデル照会基本Ｓ (MAX only) — 1:N per (申込番号, 申込目的), pass-through.
        List<審査モデル照会基本ＳSource> scoringInquiryBasicS =
                emptyIfNull(scoringInquiryBasicSSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (審査モデル照会基本ＳSource src : scoringInquiryBasicS) {
            if (src == null) {
                continue;
            }
            審査モデル照会基本ＳTarget t = new 審査モデル照会基本ＳTarget();
            t.set申込番号(targetApplicationNumber);
            t.set申込目的(convertedPurpose);
            t.setイベント(src.getイベント());
            t.setイベント日時(src.getイベント日時());
            t.set申込時年齢(src.get申込時年齢());
            t.set実行時年齢(src.get実行時年齢());
            t.set本人年収(src.get本人年収());
            t.set合算者年収(src.get合算者年収());
            t.set合算方法(src.get合算方法());
            t.set申込金額(src.get申込金額());
            t.set借入期間(src.get借入期間());
            t.set資金使途(src.get資金使途());
            t.set勤続年数(src.get勤続年数());
            t.set資本金区分(src.get資本金区分());
            t.set年間返済額(src.get年間返済額());
            t.set総借入額(src.get総借入額());
            t.set自己資金(src.get自己資金());
            t.set所要資金(src.get所要資金());
            t.set規定外項目(src.get規定外項目());
            t.set担保評価額(src.get担保評価額());
            t.set保証料区分(src.get保証料区分());
            t.set先順位控除額(src.get先順位控除額());
            t.set控除前担保評価額(src.get控除前担保評価額());
            t.set同居家族数(src.get同居家族数());
            scoringInquiryBasicSTargetMapper.insert(t);
        }

        // ③-d26 システム判定結果 (MAX only) — 1:N per (申込番号, 申込目的), pass-through.
        List<システム判定結果Source> systemJudgeResults =
                emptyIfNull(systemJudgeResultSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (システム判定結果Source src : systemJudgeResults) {
            if (src == null) {
                continue;
            }
            システム判定結果Target t = new システム判定結果Target();
            t.set申込番号(targetApplicationNumber);
            t.set申込目的(convertedPurpose);
            t.setイベント(src.getイベント());
            t.setイベント日時(src.getイベント日時());
            t.setシステム判定結果(src.getシステム判定結果());
            systemJudgeResultTargetMapper.insert(t);
        }

        // ③-d27 システム判定結果明細 (MAX only) — 1:N per (申込番号, 申込目的), pass-through.
        List<システム判定結果明細Source> systemJudgeResultDetails =
                emptyIfNull(systemJudgeResultDetailSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (システム判定結果明細Source src : systemJudgeResultDetails) {
            if (src == null) {
                continue;
            }
            システム判定結果明細Target t = new システム判定結果明細Target();
            t.set申込番号(targetApplicationNumber);
            t.set申込目的(convertedPurpose);
            t.setイベント(src.getイベント());
            t.setイベント日時(src.getイベント日時());
            t.set判定コード(src.get判定コード());
            t.set値(src.get値());
            t.set備考(src.get備考());
            systemJudgeResultDetailTargetMapper.insert(t);
        }

        // ③-d28 システム判定照会 (MAX only) — 1:N per (申込番号, 申込目的), pass-through.
        List<システム判定照会Source> systemJudgeInquiries =
                emptyIfNull(systemJudgeInquirySourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (システム判定照会Source src : systemJudgeInquiries) {
            if (src == null) {
                continue;
            }
            システム判定照会Target t = new システム判定照会Target();
            t.set申込番号(targetApplicationNumber);
            t.set申込目的(convertedPurpose);
            t.setイベント(src.getイベント());
            t.setイベント日時(src.getイベント日時());
            t.set状態(src.get状態());
            t.set状態説明(src.get状態説明());
            t.set優先度(src.get優先度());
            systemJudgeInquiryTargetMapper.insert(t);
        }

        // ③-d29 住宅ローン不正検知結果 (MAX only) — 1:N per (申込番号, 申込目的), pass-through.
        List<住宅ローン不正検知結果Source> fraudDetectionResults =
                emptyIfNull(fraudDetectionResultSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (住宅ローン不正検知結果Source src : fraudDetectionResults) {
            if (src == null) {
                continue;
            }
            住宅ローン不正検知結果Target t = new 住宅ローン不正検知結果Target();
            t.set申込番号(targetApplicationNumber);
            t.set申込目的(convertedPurpose);
            t.setイベント(src.getイベント());
            t.setイベント日時(src.getイベント日時());
            t.setリクエストＩＤ(src.getリクエストＩＤ());
            t.set得点(src.get得点());
            t.setエラーコード(src.getエラーコード());
            t.setメッセージ(src.getメッセージ());
            fraudDetectionResultTargetMapper.insert(t);
        }

        // ③-d30 住宅ローン不正検知照会 (MAX only) — 1:N per (申込番号, 申込目的), pass-through.
        List<住宅ローン不正検知照会Source> fraudDetectionInquiries =
                emptyIfNull(fraudDetectionInquirySourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (住宅ローン不正検知照会Source src : fraudDetectionInquiries) {
            if (src == null) {
                continue;
            }
            住宅ローン不正検知照会Target t = new 住宅ローン不正検知照会Target();
            t.set申込番号(targetApplicationNumber);
            t.set申込目的(convertedPurpose);
            t.setイベント(src.getイベント());
            t.setイベント日時(src.getイベント日時());
            t.set状態(src.get状態());
            t.set状態説明(src.get状態説明());
            t.set優先度(src.get優先度());
            t.setリクエストＩＤ(src.getリクエストＩＤ());
            t.set事前＿申込金額(src.get事前＿申込金額());
            t.set事前＿借入期間(src.get事前＿借入期間());
            t.set年齢(src.get年齢());
            t.set勤務先従業員数(src.get勤務先従業員数());
            t.set勤続年数(src.get勤続年数());
            t.set歩合給区分(src.get歩合給区分());
            t.set年収(src.get年収());
            t.set自己資金(src.get自己資金());
            t.set家賃(src.get家賃());
            t.set居住年数(src.get居住年数());
            t.set同居予定合計(src.get同居予定合計());
            t.set同居予定配偶者(src.get同居予定配偶者());
            t.set土地担保時価額(src.get土地担保時価額());
            t.set建物担保時価額(src.get建物担保時価額());
            t.set借入総額(src.get借入総額());
            t.set年収倍率(src.get年収倍率());
            t.set返済比率(src.get返済比率());
            t.set合算年収(src.get合算年収());
            t.set年間返済額(src.get年間返済額());
            t.set融資倍率(src.get融資倍率());
            t.set総借入額＿総借入額(src.get総借入額＿総借入額());
            t.set職業区分コード(src.get職業区分コード());
            t.set職種役職コード(src.get職種役職コード());
            t.set勤務先資本金区分(src.get勤務先資本金区分());
            t.set資金使途(src.get資金使途());
            t.set住居区分名称コード(src.get住居区分名称コード());
            t.setセグメント(src.getセグメント());
            t.set住所郵便番号(src.get住所郵便番号());
            t.set勤務先郵便番号(src.get勤務先郵便番号());
            t.set物件郵便番号(src.get物件郵便番号());
            fraudDetectionInquiryTargetMapper.insert(t);
        }

        // ③-d31 審査結果照会 (MAX only) — 1:N per (申込番号, 申込目的), pass-through.
        List<審査結果照会Source> reviewResultInquiries =
                emptyIfNull(reviewResultInquirySourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (審査結果照会Source src : reviewResultInquiries) {
            if (src == null) {
                continue;
            }
            審査結果照会Target t = new 審査結果照会Target();
            t.set申込番号(targetApplicationNumber);
            t.set申込目的(convertedPurpose);
            t.setイベント(src.getイベント());
            t.setイベント日時(src.getイベント日時());
            t.set状態(src.get状態());
            t.set状態説明(src.get状態説明());
            t.set優先度(src.get優先度());
            t.set自動判定基準(src.get自動判定基準());
            reviewResultInquiryTargetMapper.insert(t);
        }

        // ③-d32 審査コメント (MAX only) — 1:N per (申込番号, 申込目的), pass-through.
        List<審査コメントSource> reviewComments =
                emptyIfNull(reviewCommentSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (審査コメントSource src : reviewComments) {
            if (src == null) {
                continue;
            }
            審査コメントTarget t = new 審査コメントTarget();
            t.set申込番号(targetApplicationNumber);
            t.set申込目的(convertedPurpose);
            t.setイベント(src.getイベント());
            t.setイベント日時(src.getイベント日時());
            t.setコメント区分(src.getコメント区分());
            t.set結果種類(src.get結果種類());
            t.setコメント(src.getコメント());
            reviewCommentTargetMapper.insert(t);
        }

        // ③-d33 審査データ送信 (MAX only) — 1:N per (申込番号, 申込目的), pass-through.
        List<審査データ送信Source> reviewDataSends =
                emptyIfNull(reviewDataSendSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (審査データ送信Source src : reviewDataSends) {
            if (src == null) {
                continue;
            }
            審査データ送信Target t = new 審査データ送信Target();
            t.set申込番号(targetApplicationNumber);
            t.set申込目的(convertedPurpose);
            t.setイベント(src.getイベント());
            t.setイベント日時(src.getイベント日時());
            t.set状態(src.get状態());
            t.set状態説明(src.get状態説明());
            t.set優先度(src.get優先度());
            t.set次進捗コード(src.get次進捗コード());
            t.set次状態(src.get次状態());
            reviewDataSendTargetMapper.insert(t);
        }

        // ③-d34 審査ＳＮＡＶＩ連携 -> 審査ＳＮＡＶＩ連携イベント (MAX only) — 1:N per (申込番号, 申込目的), pass-through.
        List<審査ＳＮＡＶＩ連携イベントSource> reviewSnaviLinkEvents =
                emptyIfNull(reviewSnaviLinkEventSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (審査ＳＮＡＶＩ連携イベントSource src : reviewSnaviLinkEvents) {
            if (src == null) {
                continue;
            }
            審査ＳＮＡＶＩ連携イベントTarget t = new 審査ＳＮＡＶＩ連携イベントTarget();
            t.set申込番号(targetApplicationNumber);
            t.set申込目的(convertedPurpose);
            t.setイベント(src.getイベント());
            t.setイベント日時(src.getイベント日時());
            t.set状態(src.get状態());
            t.set状態説明(src.get状態説明());
            t.set優先度(src.get優先度());
            reviewSnaviLinkEventTargetMapper.insert(t);
        }

        // ③-d35 審査ＳＮＡＶＩ連携内容 (MAX only) — 105 cols, 1:N per (申込番号, 申込目的).
        // 上乗せ保証料 (source) -> 段階保証料率コード (target) aliased in the source SQL.
        List<審査ＳＮＡＶＩ連携内容Source> reviewSnaviLinkContents =
                emptyIfNull(reviewSnaviLinkContentSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (審査ＳＮＡＶＩ連携内容Source src : reviewSnaviLinkContents) {
            if (src == null) {
                continue;
            }
            審査ＳＮＡＶＩ連携内容Target t = new 審査ＳＮＡＶＩ連携内容Target();
            t.set申込番号(targetApplicationNumber);
            t.set申込目的(convertedPurpose);
            t.setイベント(src.getイベント());
            t.setイベント日時(src.getイベント日時());
            t.set保証番号(src.get保証番号());
            t.set静銀信用保証番号(src.get静銀信用保証番号());
            t.set審査結果通知年月日(src.get審査結果通知年月日());
            t.set店番(src.get店番());
            t.set取扱店番(src.get取扱店番());
            t.setＣＩＦ番号(src.getＣＩＦ番号());
            t.set漢字氏名姓(src.get漢字氏名姓());
            t.set漢字氏名名(src.get漢字氏名名());
            t.set借入希望額＿極度額(src.get借入希望額＿極度額());
            t.set返済期間年(src.get返済期間年());
            t.set返済期間月(src.get返済期間月());
            t.set集中審査＿返済方法コード(src.get集中審査＿返済方法コード());
            t.set団信付保コード＿ローン(src.get団信付保コード＿ローン());
            t.set勤続年月数＿年数(src.get勤続年月数＿年数());
            t.set勤務先漢字名称(src.get勤務先漢字名称());
            t.set一年前所得年収(src.get一年前所得年収());
            t.set資産土地建物(src.get資産土地建物());
            t.set資産預貯金(src.get資産預貯金());
            t.set資産有価証券(src.get資産有価証券());
            t.set資産その他(src.get資産その他());
            t.set負債銀行借入(src.get負債銀行借入());
            t.set負債クレジット信販(src.get負債クレジット信販());
            t.set負債その他(src.get負債その他());
            t.set負債その他借入(src.get負債その他借入());
            t.set内訳＿毎月返済総額(src.get内訳＿毎月返済総額());
            t.set一回の元利支払＿毎月(src.get一回の元利支払＿毎月());
            t.setボーナス返済月1(src.getボーナス返済月1());
            t.setボーナス返済月2(src.getボーナス返済月2());
            t.set内訳＿ボーナス総額(src.get内訳＿ボーナス総額());
            t.set一回の元利支払＿ボーナス(src.get一回の元利支払＿ボーナス());
            t.set当初借入額＿住宅金融公庫(src.get当初借入額＿住宅金融公庫());
            t.set当初借入額＿年金併せ(src.get当初借入額＿年金併せ());
            t.set当初借入額＿年金その他(src.get当初借入額＿年金その他());
            t.set当初借入額＿県市町村(src.get当初借入額＿県市町村());
            t.set当初借入額＿勤務先制度(src.get当初借入額＿勤務先制度());
            t.set当初借入額＿その他(src.get当初借入額＿その他());
            t.set借入金残高合計(src.get借入金残高合計());
            t.set年間元利返済額＿住宅金融公庫(src.get年間元利返済額＿住宅金融公庫());
            t.set年間元利返済額＿年金併せ(src.get年間元利返済額＿年金併せ());
            t.set年間元利返済額＿年金その他(src.get年間元利返済額＿年金その他());
            t.set年間元利返済額＿県市町村(src.get年間元利返済額＿県市町村());
            t.set年間元利返済額＿勤務先制度(src.get年間元利返済額＿勤務先制度());
            t.set年間元利返済額＿その他(src.get年間元利返済額＿その他());
            t.set借入金年間返済額合計(src.get借入金年間返済額合計());
            t.set一年前加算者年収(src.get一年前加算者年収());
            t.set購入物件区分(src.get購入物件区分());
            t.set購入物件所在地＿漢字(src.get購入物件所在地＿漢字());
            t.set購入物件土地地目(src.get購入物件土地地目());
            t.set購入物件土地面積(src.get購入物件土地面積());
            t.set所要資金＿土地(src.get所要資金＿土地());
            t.set所要資金＿建物(src.get所要資金＿建物());
            t.set所要資金＿経費(src.get所要資金＿経費());
            t.set所要資金＿合計(src.get所要資金＿合計());
            t.set購入物件建物構造(src.get購入物件建物構造());
            t.set購入物件建物屋根(src.get購入物件建物屋根());
            t.set購入物件建物延面積(src.get購入物件建物延面積());
            t.set購入物件土地資金支払先(src.get購入物件土地資金支払先());
            t.set購入物件土地資金支払先その他(src.get購入物件土地資金支払先その他());
            t.set購入物件建物資金支払先(src.get購入物件建物資金支払先());
            t.set購入物件建物資金支払先その他(src.get購入物件建物資金支払先その他());
            t.set当初借入額＿自己資金(src.get当初借入額＿自己資金());
            t.set保証会社条件1(src.get保証会社条件1());
            t.set保証会社条件2(src.get保証会社条件2());
            t.set保証会社条件3(src.get保証会社条件3());
            t.set保証会社条件4(src.get保証会社条件4());
            t.set保証会社条件5(src.get保証会社条件5());
            t.set保証会社条件6(src.get保証会社条件6());
            t.set保証会社条件7(src.get保証会社条件7());
            t.set保証会社条件8(src.get保証会社条件8());
            t.set保証会社条件9(src.get保証会社条件9());
            t.set保証会社条件10(src.get保証会社条件10());
            t.setスコアリングセグメントコード(src.getスコアリングセグメントコード());
            t.setスコアリング一次判定コード(src.getスコアリング一次判定コード());
            t.set年間返済比率(src.get年間返済比率());
            t.set連帯債務者店番(src.get連帯債務者店番());
            t.set連帯債務者ＣＩＦ番号(src.get連帯債務者ＣＩＦ番号());
            t.set連帯債務者漢字氏名姓(src.get連帯債務者漢字氏名姓());
            t.set連帯債務者漢字氏名名(src.get連帯債務者漢字氏名名());
            t.set本人団信付保割合(src.get本人団信付保割合());
            t.set連帯債務者団信付保割合(src.get連帯債務者団信付保割合());
            t.set段階保証料率コード(src.get段階保証料率コード());
            t.set連帯債務者有無(src.get連帯債務者有無());
            t.set保証料(src.get保証料());
            t.set取扱手数料(src.get取扱手数料());
            t.set実行前条件1(src.get実行前条件1());
            t.set実行前条件2(src.get実行前条件2());
            t.set実行前条件3(src.get実行前条件3());
            t.set実行前条件4(src.get実行前条件4());
            t.set実行前条件5(src.get実行前条件5());
            t.set実行前条件6(src.get実行前条件6());
            t.set実行前条件7(src.get実行前条件7());
            t.set実行前条件8(src.get実行前条件8());
            t.set実行前条件9(src.get実行前条件9());
            t.set実行前条件10(src.get実行前条件10());
            t.set実行予定日(src.get実行予定日());
            t.set分割実行フラグ(src.get分割実行フラグ());
            t.set分割実行予定日(src.get分割実行予定日());
            t.setその他借入先1(src.getその他借入先1());
            t.setその他借入金1金額(src.getその他借入金1金額());
            t.setその他借入先2(src.getその他借入先2());
            t.setその他借入金2金額(src.getその他借入金2金額());
            reviewSnaviLinkContentTargetMapper.insert(t);
        }

        // ③-d36 審査契約書出力連携内容 -> ＩＦ＿契約書送信 (MAX only) — 143 cols, 1:N per (申込番号, 申込目的).
        // Renames handled via SQL aliases: ＣＩＦ->ＣＩＦ番号, 債務者甲名->債務者甲＿名,
        // 債務者甲名カナ->債務者甲＿名カナ, 標準金利パーセント->標準金利,
        // 全期間乖離幅＿数値->全期間乖離幅, 毎月返済２回目以降元金返済年月->毎月返済＿２回目以降元金返済年月日.
        List<審査契約書出力連携内容Source> contractDocOutputContents =
                emptyIfNull(contractDocOutputContentSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (審査契約書出力連携内容Source src : contractDocOutputContents) {
            if (src == null) {
                continue;
            }
            ＩＦ＿契約書送信Target t = new ＩＦ＿契約書送信Target();
            t.set申込番号(targetApplicationNumber);
            t.set申込目的(convertedPurpose);
            t.setイベント(src.getイベント());
            t.setイベント日時(src.getイベント日時());
            t.setレコード区分(src.getレコード区分());
            t.set作成基準日時(src.get作成基準日時());
            t.set取上店番(src.get取上店番());
            t.set勘定店番(src.get勘定店番());
            t.setＣＩＦ番号(src.getＣＩＦ番号());
            t.set債務者甲＿名(src.get債務者甲＿名());
            t.set債務者甲＿名カナ(src.get債務者甲＿名カナ());
            t.set顧客住所(src.get顧客住所());
            t.set法人区分(src.get法人区分());
            t.set業種コード(src.get業種コード());
            t.set職業(src.get職業());
            t.setデータ区分(src.getデータ区分());
            t.set処理区分コード(src.get処理区分コード());
            t.set取扱番号(src.get取扱番号());
            t.set枝番(src.get枝番());
            t.set融資科目コード(src.get融資科目コード());
            t.set融資分類コード(src.get融資分類コード());
            t.set貸出種類コード(src.get貸出種類コード());
            t.set貸出日(src.get貸出日());
            t.set貸出金額(src.get貸出金額());
            t.set貸出方法区分(src.get貸出方法区分());
            t.set当初借入利率(src.get当初借入利率());
            t.set資金使途コード(src.get資金使途コード());
            t.set資金使途備考(src.get資金使途備考());
            t.set現在残高(src.get現在残高());
            t.set最終返済日(src.get最終返済日());
            t.set発行日(src.get発行日());
            t.set実行予定日(src.get実行予定日());
            t.set徴求日(src.get徴求日());
            t.set実行日(src.get実行日());
            t.set否決日(src.get否決日());
            t.set連携区分(src.get連携区分());
            t.set承認ステータス(src.get承認ステータス());
            t.set極度額(src.get極度額());
            t.set抵当権設定日＿原契約日(src.get抵当権設定日＿原契約日());
            t.set根抵当権契約設定日(src.get根抵当権契約設定日());
            t.set債務承認開始日(src.get債務承認開始日());
            t.set債務承認終了日(src.get債務承認終了日());
            t.set保証委託契約日(src.get保証委託契約日());
            t.set印紙税額(src.get印紙税額());
            t.set稟議種別(src.get稟議種別());
            t.set保証番号(src.get保証番号());
            t.set金利種類区分(src.get金利種類区分());
            t.set金利型名称備考(src.get金利型名称備考());
            t.set返済方法(src.get返済方法());
            t.set弁済方法名称備考(src.get弁済方法名称備考());
            t.set基準金利区分(src.get基準金利区分());
            t.set標準金利(src.get標準金利());
            t.set固定金利再選択時の利幅＿符号(src.get固定金利再選択時の利幅＿符号());
            t.set固定金利再選択時の利幅＿数値(src.get固定金利再選択時の利幅＿数値());
            t.set貸付期間＿年(src.get貸付期間＿年());
            t.set貸付期間＿月(src.get貸付期間＿月());
            t.set債務者＿甲＿団信(src.get債務者＿甲＿団信());
            t.set利息支払日(src.get利息支払日());
            t.set利息払込済日(src.get利息払込済日());
            t.set利息約定日(src.get利息約定日());
            t.set利率方式(src.get利率方式());
            t.setその他期日(src.getその他期日());
            t.set休日補正区分(src.get休日補正区分());
            t.set返済用預金口座店番(src.get返済用預金口座店番());
            t.set返済用預金口座種目(src.get返済用預金口座種目());
            t.set返済用預金口座番号(src.get返済用預金口座番号());
            t.set返済用口座名義(src.get返済用口座名義());
            t.set据置ＣＤ(src.get据置ＣＤ());
            t.set据置備考(src.get据置備考());
            t.set元金据置期限(src.get元金据置期限());
            t.set毎月返済＿内訳＿貸出金額(src.get毎月返済＿内訳＿貸出金額());
            t.set毎月返済＿初回返済日(src.get毎月返済＿初回返済日());
            t.set毎月返済＿返済日(src.get毎月返済＿返済日());
            t.set毎回返済＿返済間隔(src.get毎回返済＿返済間隔());
            t.set毎回返済＿返済回数(src.get毎回返済＿返済回数());
            t.set毎月返済＿初回返済金(src.get毎月返済＿初回返済金());
            t.set毎月返済＿返済金(src.get毎月返済＿返済金());
            t.set毎月返済＿最終返済金(src.get毎月返済＿最終返済金());
            t.set半年毎返済＿内訳＿貸出金額(src.get半年毎返済＿内訳＿貸出金額());
            t.set半年毎返済＿初回返済日(src.get半年毎返済＿初回返済日());
            t.set半年毎返済＿返済月1(src.get半年毎返済＿返済月1());
            t.set半年毎返済＿返済月2(src.get半年毎返済＿返済月2());
            t.set半年毎返済＿返済日(src.get半年毎返済＿返済日());
            t.set半年毎増額＿返済回数(src.get半年毎増額＿返済回数());
            t.set半年毎＿初回返済金(src.get半年毎＿初回返済金());
            t.set半年毎＿返済金(src.get半年毎＿返済金());
            t.set半年毎増額＿返済金額2(src.get半年毎増額＿返済金額2());
            t.set半年毎＿最終返済金(src.get半年毎＿最終返済金());
            t.set担保コード(src.get担保コード());
            t.set担保明細コード(src.get担保明細コード());
            t.set保証先(src.get保証先());
            t.set順位(src.get順位());
            t.set手形サイト(src.get手形サイト());
            t.set全期間乖離幅(src.get全期間乖離幅());
            t.set利率サイクル(src.get利率サイクル());
            t.set毎月返済＿2回目以降元金返済年月日(src.get毎月返済＿2回目以降元金返済年月日());
            t.set債務者＿甲＿電話番号(src.get債務者＿甲＿電話番号());
            t.set初回利払日(src.get初回利払日());
            t.set当初固定金利期間(src.get当初固定金利期間());
            t.set返済回数(src.get返済回数());
            t.set据置回数(src.get据置回数());
            t.set連帯債務者勘定店番(src.get連帯債務者勘定店番());
            t.set連帯債務者ＣＩＦ(src.get連帯債務者ＣＩＦ());
            t.set連帯債務者＿乙＿名(src.get連帯債務者＿乙＿名());
            t.set連帯債務者＿乙＿住所(src.get連帯債務者＿乙＿住所());
            t.set債務者甲負担割合＿分母(src.get債務者甲負担割合＿分母());
            t.set債務者甲負担割合＿分子(src.get債務者甲負担割合＿分子());
            t.set連帯債務者乙＿分母(src.get連帯債務者乙＿分母());
            t.set連帯債務者乙＿分子(src.get連帯債務者乙＿分子());
            t.set連帯債務者＿乙＿団信(src.get連帯債務者＿乙＿団信());
            t.set連帯保証人1＿対象区分(src.get連帯保証人1＿対象区分());
            t.set連帯保証人1＿氏名(src.get連帯保証人1＿氏名());
            t.set連帯保証人1＿氏名2(src.get連帯保証人1＿氏名2());
            t.set連帯保証人1＿氏名3(src.get連帯保証人1＿氏名3());
            t.set連帯保証人1＿保証期日(src.get連帯保証人1＿保証期日());
            t.set連帯保証人1＿限度額(src.get連帯保証人1＿限度額());
            t.set連帯保証人1＿科目(src.get連帯保証人1＿科目());
            t.set連帯保証人1＿住所(src.get連帯保証人1＿住所());
            t.set連帯保証人1＿生年月日(src.get連帯保証人1＿生年月日());
            t.set連帯保証人1＿勤務先(src.get連帯保証人1＿勤務先());
            t.set連帯保証人1＿法人(src.get連帯保証人1＿法人());
            t.set連帯保証人2＿対象区分(src.get連帯保証人2＿対象区分());
            t.set連帯保証人2＿氏名(src.get連帯保証人2＿氏名());
            t.set連帯保証人2＿氏名2(src.get連帯保証人2＿氏名2());
            t.set連帯保証人2＿氏名3(src.get連帯保証人2＿氏名3());
            t.set連帯保証人2＿保証期日(src.get連帯保証人2＿保証期日());
            t.set連帯保証人2＿限度額(src.get連帯保証人2＿限度額());
            t.set連帯保証人2＿科目(src.get連帯保証人2＿科目());
            t.set連帯保証人2＿住所(src.get連帯保証人2＿住所());
            t.set連帯保証人2＿生年月日(src.get連帯保証人2＿生年月日());
            t.set連帯保証人2＿勤務先(src.get連帯保証人2＿勤務先());
            t.set連帯保証人2＿法人(src.get連帯保証人2＿法人());
            t.set連帯保証人3＿対象区分(src.get連帯保証人3＿対象区分());
            t.set連帯保証人3＿氏名(src.get連帯保証人3＿氏名());
            t.set連帯保証人3＿氏名2(src.get連帯保証人3＿氏名2());
            t.set連帯保証人3＿氏名3(src.get連帯保証人3＿氏名3());
            t.set連帯保証人3＿保証期日(src.get連帯保証人3＿保証期日());
            t.set連帯保証人3＿限度額(src.get連帯保証人3＿限度額());
            t.set連帯保証人3＿科目(src.get連帯保証人3＿科目());
            t.set連帯保証人3＿住所(src.get連帯保証人3＿住所());
            t.set連帯保証人3＿生年月日(src.get連帯保証人3＿生年月日());
            t.set連帯保証人3＿勤務先(src.get連帯保証人3＿勤務先());
            t.set連帯保証人3＿法人(src.get連帯保証人3＿法人());
            contractDocSendTargetMapper.insert(t);
        }

        // ③-d37 審査契約書出力連携 -> 契約書連携イベント (MAX only) — 1:N per (申込番号, 申込目的), pass-through.
        List<契約書連携イベントSource> contractLinkEvents =
                emptyIfNull(contractLinkEventSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (契約書連携イベントSource src : contractLinkEvents) {
            if (src == null) {
                continue;
            }
            契約書連携イベントTarget t = new 契約書連携イベントTarget();
            t.set申込番号(targetApplicationNumber);
            t.set申込目的(convertedPurpose);
            t.setイベント(src.getイベント());
            t.setイベント日時(src.getイベント日時());
            t.set状態(src.get状態());
            t.set状態説明(src.get状態説明());
            t.set優先度(src.get優先度());
            contractLinkEventTargetMapper.insert(t);
        }

        // ③-d38 審査預保照会 -> 審査預保照会 (MAX only) — 1:N per (申込番号, 申込目的), pass-through.
        List<審査預保照会Source> reviewDepositGuaranteeInquiries =
                emptyIfNull(reviewDepositGuaranteeInquirySourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (審査預保照会Source src : reviewDepositGuaranteeInquiries) {
            if (src == null) {
                continue;
            }
            審査預保照会Target t = new 審査預保照会Target();
            t.set申込番号(targetApplicationNumber);
            t.set申込目的(convertedPurpose);
            t.setイベント(src.getイベント());
            t.setイベント日時(src.getイベント日時());
            t.set照会依頼番号(src.get照会依頼番号());
            reviewDepositGuaranteeInquiryTargetMapper.insert(t);
        }

        // ③-d39 保証結果メインじぶん -> 保証結果メインじぶん (MAX only) — 外部連携, 1:N per (申込番号, 申込目的), pass-through.
        List<保証結果メインじぶんSource> guaranteeResultMainJibuns =
                emptyIfNull(guaranteeResultMainJibunSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (保証結果メインじぶんSource src : guaranteeResultMainJibuns) {
            if (src == null) {
                continue;
            }
            保証結果メインじぶんTarget t = new 保証結果メインじぶんTarget();
            t.set申込番号(targetApplicationNumber);
            t.set申込目的(convertedPurpose);
            t.setイベント(src.getイベント());
            t.setイベント日時(src.getイベント日時());
            t.set状態(src.get状態());
            t.set出力日時(src.get出力日時());
            t.set保証会社案件番号(src.get保証会社案件番号());
            t.set外部案件番号(src.get外部案件番号());
            t.set申込人姓名カナ(src.get申込人姓名カナ());
            t.set申込人姓名(src.get申込人姓名());
            t.set申込人性別(src.get申込人性別());
            t.set申込人生年月日(src.get申込人生年月日());
            t.set申込人郵便番号(src.get申込人郵便番号());
            t.set申込人住所(src.get申込人住所());
            t.set申込人現住所電話番号(src.get申込人現住所電話番号());
            t.set申込人携帯電話番号(src.get申込人携帯電話番号());
            t.set申込人勤務先名称(src.get申込人勤務先名称());
            t.set申込人勤務先郵便番号(src.get申込人勤務先郵便番号());
            t.set申込人勤務先住所(src.get申込人勤務先住所());
            t.set申込人勤務先電話番号(src.get申込人勤務先電話番号());
            t.set申込人勤続年数(src.get申込人勤続年数());
            t.set申込人雇用形態(src.get申込人雇用形態());
            t.set申込人業種(src.get申込人業種());
            t.set申込人勤務先資本金(src.get申込人勤務先資本金());
            t.set申込人勤務先従業員数(src.get申込人勤務先従業員数());
            t.set申込人勤務先職種役職(src.get申込人勤務先職種役職());
            t.set申込人勤務先所属部署(src.get申込人勤務先所属部署());
            t.set国家資格(src.get国家資格());
            t.set申込本人前年年収(src.get申込本人前年年収());
            t.set保有資産(src.get保有資産());
            t.setペアローン有無(src.getペアローン有無());
            t.setペアローン案件番号(src.getペアローン案件番号());
            t.set保証人1債務区分(src.get保証人1債務区分());
            t.set保証人1収入合算有無(src.get保証人1収入合算有無());
            t.set保証人1郵便番号(src.get保証人1郵便番号());
            t.set保証人1住所(src.get保証人1住所());
            t.set保証人1姓名カナ(src.get保証人1姓名カナ());
            t.set保証人1姓名(src.get保証人1姓名());
            t.set保証人1生年月日(src.get保証人1生年月日());
            t.set保証人1性別(src.get保証人1性別());
            t.set保証人1現住所電話番号(src.get保証人1現住所電話番号());
            t.set保証人1携帯電話番号(src.get保証人1携帯電話番号());
            t.set保証人1続柄(src.get保証人1続柄());
            t.set保証人1勤務先名称(src.get保証人1勤務先名称());
            t.set保証人1前年年収(src.get保証人1前年年収());
            t.set保証人2債務区分(src.get保証人2債務区分());
            t.set保証人2収入合算有無(src.get保証人2収入合算有無());
            t.set保証人2郵便番号(src.get保証人2郵便番号());
            t.set保証人2住所(src.get保証人2住所());
            t.set保証人2姓名カナ(src.get保証人2姓名カナ());
            t.set保証人2姓名(src.get保証人2姓名());
            t.set保証人2生年月日(src.get保証人2生年月日());
            t.set保証人2性別(src.get保証人2性別());
            t.set保証人2現住所電話番号(src.get保証人2現住所電話番号());
            t.set保証人2携帯電話番号(src.get保証人2携帯電話番号());
            t.set保証人2続柄(src.get保証人2続柄());
            t.set保証人2勤務先名称(src.get保証人2勤務先名称());
            t.set保証人2前年年収(src.get保証人2前年年収());
            t.set保証人3債務区分(src.get保証人3債務区分());
            t.set保証人3収入合算有無(src.get保証人3収入合算有無());
            t.set保証人3郵便番号(src.get保証人3郵便番号());
            t.set保証人3住所(src.get保証人3住所());
            t.set保証人3姓名カナ(src.get保証人3姓名カナ());
            t.set保証人3姓名(src.get保証人3姓名());
            t.set保証人3生年月日(src.get保証人3生年月日());
            t.set保証人3性別(src.get保証人3性別());
            t.set保証人3現住所電話番号(src.get保証人3現住所電話番号());
            t.set保証人3携帯電話番号(src.get保証人3携帯電話番号());
            t.set保証人3続柄(src.get保証人3続柄());
            t.set保証人3勤務先名称(src.get保証人3勤務先名称());
            t.set保証人3前年年収(src.get保証人3前年年収());
            t.set保証人4債務区分(src.get保証人4債務区分());
            t.set保証人4収入合算有無(src.get保証人4収入合算有無());
            t.set保証人4郵便番号(src.get保証人4郵便番号());
            t.set保証人4住所(src.get保証人4住所());
            t.set保証人4姓名カナ(src.get保証人4姓名カナ());
            t.set保証人4姓名(src.get保証人4姓名());
            t.set保証人4生年月日(src.get保証人4生年月日());
            t.set保証人4性別(src.get保証人4性別());
            t.set保証人4現住所電話番号(src.get保証人4現住所電話番号());
            t.set保証人4携帯電話番号(src.get保証人4携帯電話番号());
            t.set保証人4続柄(src.get保証人4続柄());
            t.set保証人4勤務先名称(src.get保証人4勤務先名称());
            t.set保証人4前年年収(src.get保証人4前年年収());
            t.set保証人5債務区分(src.get保証人5債務区分());
            t.set保証人5収入合算有無(src.get保証人5収入合算有無());
            t.set保証人5郵便番号(src.get保証人5郵便番号());
            t.set保証人5住所(src.get保証人5住所());
            t.set保証人5姓名カナ(src.get保証人5姓名カナ());
            t.set保証人5姓名(src.get保証人5姓名());
            t.set保証人5生年月日(src.get保証人5生年月日());
            t.set保証人5性別(src.get保証人5性別());
            t.set保証人5現住所電話番号(src.get保証人5現住所電話番号());
            t.set保証人5携帯電話番号(src.get保証人5携帯電話番号());
            t.set保証人5続柄(src.get保証人5続柄());
            t.set保証人5勤務先名称(src.get保証人5勤務先名称());
            t.set保証人5前年年収(src.get保証人5前年年収());
            t.set資金使途(src.get資金使途());
            t.set建設地郵便番号(src.get建設地郵便番号());
            t.set建設地住所(src.get建設地住所());
            t.set売買契約予定年月日(src.get売買契約予定年月日());
            t.set建物新築年月日(src.get建物新築年月日());
            t.set婚姻区分(src.get婚姻区分());
            t.set同居家族配偶者(src.get同居家族配偶者());
            t.set同居家族子供人数(src.get同居家族子供人数());
            t.set同居家族その他人数(src.get同居家族その他人数());
            t.set住居区分(src.get住居区分());
            t.set必要資金土地(src.get必要資金土地());
            t.set必要資金建物(src.get必要資金建物());
            t.set必要資金借替(src.get必要資金借替());
            t.set必要資金諸費用(src.get必要資金諸費用());
            t.set必要資金その他(src.get必要資金その他());
            t.set融資額(src.get融資額());
            t.set返済方法(src.get返済方法());
            t.set希望返済期間(src.get希望返済期間());
            t.set希望額ボーナス分(src.get希望額ボーナス分());
            t.set希望額毎月分(src.get希望額毎月分());
            t.set融資実行希望年月日(src.get融資実行希望年月日());
            t.setその他借入1借入金額(src.getその他借入1借入金額());
            t.setその他借入1借入先(src.getその他借入1借入先());
            t.setその他借入1返済期間(src.getその他借入1返済期間());
            t.setその他借入1金利(src.getその他借入1金利());
            t.setその他借入2借入金額(src.getその他借入2借入金額());
            t.setその他借入2借入先(src.getその他借入2借入先());
            t.setその他借入2返済期間(src.getその他借入2返済期間());
            t.setその他借入2金利(src.getその他借入2金利());
            t.set資産売却(src.get資産売却());
            t.set自己資金(src.get自己資金());
            t.set自己資金うち贈与資金(src.get自己資金うち贈与資金());
            t.set借入利用先1(src.get借入利用先1());
            t.set借入利用種類1(src.get借入利用種類1());
            t.set借入利用残高1(src.get借入利用残高1());
            t.set借入年間返済額1(src.get借入年間返済額1());
            t.set借入利用限度額1(src.get借入利用限度額1());
            t.set借入解約予定1(src.get借入解約予定1());
            t.set借入利用先2(src.get借入利用先2());
            t.set借入利用種類2(src.get借入利用種類2());
            t.set借入利用残高2(src.get借入利用残高2());
            t.set借入年間返済額2(src.get借入年間返済額2());
            t.set借入利用限度額2(src.get借入利用限度額2());
            t.set借入解約予定2(src.get借入解約予定2());
            t.set借入利用先3(src.get借入利用先3());
            t.set借入利用種類3(src.get借入利用種類3());
            t.set借入利用残高3(src.get借入利用残高3());
            t.set借入年間返済額3(src.get借入年間返済額3());
            t.set借入利用限度額3(src.get借入利用限度額3());
            t.set借入解約予定3(src.get借入解約予定3());
            t.set業者会社名(src.get業者会社名());
            t.set業者所在地(src.get業者所在地());
            t.set物件種類(src.get物件種類());
            t.set戸建地積延床面積(src.get戸建地積延床面積());
            t.set戸建構造(src.get戸建構造());
            t.setマンション名称(src.getマンション名称());
            t.setマンション建物構造(src.getマンション建物構造());
            t.setマンション完成年月(src.getマンション完成年月());
            t.setマンション専有面積(src.getマンション専有面積());
            t.set事前審査申請日(src.get事前審査申請日());
            t.set本審査申請日(src.get本審査申請日());
            t.set返済負担率(src.get返済負担率());
            t.set担保評価額(src.get担保評価額());
            t.setＬＴＶ(src.getＬＴＶ());
            t.setセグメント(src.getセグメント());
            t.set保証承認番号(src.get保証承認番号());
            t.set保証承認日(src.get保証承認日());
            t.set保証料率(src.get保証料率());
            t.set事前審査回答日(src.get事前審査回答日());
            t.set事前審査結果(src.get事前審査結果());
            t.set事前審査担当者(src.get事前審査担当者());
            t.set本審査回答日当初(src.get本審査回答日当初());
            t.set本審査結果当初(src.get本審査結果当初());
            t.set本審査担当者(src.get本審査担当者());
            t.set本審査回答日最新(src.get本審査回答日最新());
            t.set本審査結果最新(src.get本審査結果最新());
            t.set申込目的＿出力用(src.get申込目的＿出力用());
            t.set保証会社意見欄(src.get保証会社意見欄());
            guaranteeResultMainJibunTargetMapper.insert(t);
        }

        // ③-d40 保証結果メインアルヒ -> 保証結果メインアルヒ (MAX only) — 外部連携, 1:N per (申込番号, 申込目的), pass-through.
        List<保証結果メインアルヒSource> guaranteeResultMainAruhis =
                emptyIfNull(guaranteeResultMainAruhiSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (保証結果メインアルヒSource src : guaranteeResultMainAruhis) {
            if (src == null) {
                continue;
            }
            保証結果メインアルヒTarget t = new 保証結果メインアルヒTarget();
            t.set申込番号(targetApplicationNumber);
            t.set申込目的(convertedPurpose);
            t.setイベント(src.getイベント());
            t.setイベント日時(src.getイベント日時());
            t.set状態(src.get状態());
            t.set出力日時(src.get出力日時());
            t.set保証会社案件番号(src.get保証会社案件番号());
            t.setＡＲＵＨＩ証書番号(src.getＡＲＵＨＩ証書番号());
            t.set申込人姓名カナ(src.get申込人姓名カナ());
            t.set申込人姓名(src.get申込人姓名());
            t.set申込人性別(src.get申込人性別());
            t.set申込人国籍(src.get申込人国籍());
            t.set申込人生年月日(src.get申込人生年月日());
            t.set申込人郵便番号(src.get申込人郵便番号());
            t.set申込人住所(src.get申込人住所());
            t.set申込人現住所電話番号(src.get申込人現住所電話番号());
            t.set申込人携帯電話番号(src.get申込人携帯電話番号());
            t.set申込人勤務先名称カナ(src.get申込人勤務先名称カナ());
            t.set申込人勤務先名称(src.get申込人勤務先名称());
            t.set申込人勤務先郵便番号(src.get申込人勤務先郵便番号());
            t.set申込人勤務先住所(src.get申込人勤務先住所());
            t.set申込人勤務先電話番号(src.get申込人勤務先電話番号());
            t.set申込人勤続年数(src.get申込人勤続年数());
            t.set申込人勤続月数(src.get申込人勤続月数());
            t.set申込人雇用形態(src.get申込人雇用形態());
            t.set申込人業種(src.get申込人業種());
            t.set申込人勤務先事業内容(src.get申込人勤務先事業内容());
            t.set申込人勤務先所属部署(src.get申込人勤務先所属部署());
            t.set申込人職種(src.get申込人職種());
            t.set申込人勤務先役職(src.get申込人勤務先役職());
            t.set申込人勤務先資本金(src.get申込人勤務先資本金());
            t.set申込人収入種類(src.get申込人収入種類());
            t.set申込人前年年収(src.get申込人前年年収());
            t.set申込人前年年収内ボーナス(src.get申込人前年年収内ボーナス());
            t.set申込人前々年年収(src.get申込人前々年年収());
            t.set連帯債務者有無(src.get連帯債務者有無());
            t.set連債者理由1(src.get連債者理由1());
            t.set連債者理由2(src.get連債者理由2());
            t.set連債者理由3(src.get連債者理由3());
            t.set連債者続柄(src.get連債者続柄());
            t.set連債者続柄その他(src.get連債者続柄その他());
            t.set連債者姓名カナ(src.get連債者姓名カナ());
            t.set連債者姓名(src.get連債者姓名());
            t.set連債者性別(src.get連債者性別());
            t.set連債者国籍(src.get連債者国籍());
            t.set連債者生年月日(src.get連債者生年月日());
            t.set連債者郵便番号(src.get連債者郵便番号());
            t.set連債者住所(src.get連債者住所());
            t.set連債者自宅電話番号(src.get連債者自宅電話番号());
            t.set連債者携帯電話番号(src.get連債者携帯電話番号());
            t.set連債者勤務先名カナ(src.get連債者勤務先名カナ());
            t.set連債者勤務先名(src.get連債者勤務先名());
            t.set連債者勤務先郵便番号(src.get連債者勤務先郵便番号());
            t.set連債者勤務先住所(src.get連債者勤務先住所());
            t.set連債者勤務先電話番号(src.get連債者勤務先電話番号());
            t.set連債者勤続年数(src.get連債者勤続年数());
            t.set連債者勤続月数(src.get連債者勤続月数());
            t.set連債者雇用形態(src.get連債者雇用形態());
            t.set連債者業種(src.get連債者業種());
            t.set連債者勤務先事業内容(src.get連債者勤務先事業内容());
            t.set連債者勤務先所属部署(src.get連債者勤務先所属部署());
            t.set連債者勤務先資本金(src.get連債者勤務先資本金());
            t.set収入合算者前年年収(src.get収入合算者前年年収());
            t.set連債者前年年収(src.get連債者前年年収());
            t.set収入合算者前年年収内ボーナス(src.get収入合算者前年年収内ボーナス());
            t.set収入合算者前々年年収(src.get収入合算者前々年年収());
            t.set物件種類(src.get物件種類());
            t.set建設地郵便番号(src.get建設地郵便番号());
            t.set建設地住所(src.get建設地住所());
            t.set敷地面積(src.get敷地面積());
            t.set敷地権利区分1(src.get敷地権利区分1());
            t.set敷地権利区分2(src.get敷地権利区分2());
            t.set敷地権利区分3(src.get敷地権利区分3());
            t.set敷地権利区分4(src.get敷地権利区分4());
            t.set物件構造(src.get物件構造());
            t.set建物床面積＿住宅部分(src.get建物床面積＿住宅部分());
            t.set建物床面積＿非住宅部分(src.get建物床面積＿非住宅部分());
            t.set売買契約予定年月日(src.get売買契約予定年月日());
            t.set建物新築年月日(src.get建物新築年月日());
            t.set申込人物件通勤時間(src.get申込人物件通勤時間());
            t.set連債者物件通勤時間(src.get連債者物件通勤時間());
            t.set工事請負会社名称カナ(src.get工事請負会社名称カナ());
            t.set工事請負会社名称(src.get工事請負会社名称());
            t.set工事請負会社担当者(src.get工事請負会社担当者());
            t.set工事請負会社郵便番号(src.get工事請負会社郵便番号());
            t.set工事請負会社住所(src.get工事請負会社住所());
            t.set工事請負業者電話番号(src.get工事請負業者電話番号());
            t.set販売代理業者名カナ(src.get販売代理業者名カナ());
            t.set販売代理業者名称(src.get販売代理業者名称());
            t.set販売代理会社担当者(src.get販売代理会社担当者());
            t.set販売代理会社郵便番号(src.get販売代理会社郵便番号());
            t.set販売代理会社住所(src.get販売代理会社住所());
            t.set販売代理業者電話番号(src.get販売代理業者電話番号());
            t.setリフォーム会社名称カナ(src.getリフォーム会社名称カナ());
            t.setリフォーム会社名称(src.getリフォーム会社名称());
            t.setリフォーム会社担当者(src.getリフォーム会社担当者());
            t.setリフォーム会社郵便番号(src.getリフォーム会社郵便番号());
            t.setリフォーム会社住所(src.getリフォーム会社住所());
            t.setリフォーム業者電話番号(src.getリフォーム業者電話番号());
            t.set現在住宅の建て方(src.get現在住宅の建て方());
            t.set現在住宅種類(src.get現在住宅種類());
            t.set現在住宅面積(src.get現在住宅面積());
            t.set住宅必要理由(src.get住宅必要理由());
            t.set物件共有予定土地(src.get物件共有予定土地());
            t.set物件共有予定建物(src.get物件共有予定建物());
            t.set担保提供者人数(src.get担保提供者人数());
            t.set担保提供者1建物土地(src.get担保提供者1建物土地());
            t.set担保提供者1続柄(src.get担保提供者1続柄());
            t.set担保提供者1続柄その他(src.get担保提供者1続柄その他());
            t.set担保提供者1姓名カナ(src.get担保提供者1姓名カナ());
            t.set担保提供者1姓名(src.get担保提供者1姓名());
            t.set担保提供者1生年月日(src.get担保提供者1生年月日());
            t.set担保提供者1郵便番号(src.get担保提供者1郵便番号());
            t.set担保提供者1住所(src.get担保提供者1住所());
            t.set担保提供者2建物土地(src.get担保提供者2建物土地());
            t.set担保提供者2続柄(src.get担保提供者2続柄());
            t.set担保提供者2続柄その他(src.get担保提供者2続柄その他());
            t.set担保提供者2姓名カナ(src.get担保提供者2姓名カナ());
            t.set担保提供者2姓名(src.get担保提供者2姓名());
            t.set担保提供者2生年月日(src.get担保提供者2生年月日());
            t.set担保提供者2郵便番号(src.get担保提供者2郵便番号());
            t.set担保提供者2住所(src.get担保提供者2住所());
            t.set担保提供者3建物土地(src.get担保提供者3建物土地());
            t.set担保提供者3続柄(src.get担保提供者3続柄());
            t.set担保提供者3続柄その他(src.get担保提供者3続柄その他());
            t.set担保提供者3姓名カナ(src.get担保提供者3姓名カナ());
            t.set担保提供者3姓名(src.get担保提供者3姓名());
            t.set担保提供者3生年月日(src.get担保提供者3生年月日());
            t.set担保提供者3郵便番号(src.get担保提供者3郵便番号());
            t.set担保提供者3住所(src.get担保提供者3住所());
            t.set担保提供者4建物土地(src.get担保提供者4建物土地());
            t.set担保提供者4続柄(src.get担保提供者4続柄());
            t.set担保提供者4続柄その他(src.get担保提供者4続柄その他());
            t.set担保提供者4姓名カナ(src.get担保提供者4姓名カナ());
            t.set担保提供者4姓名(src.get担保提供者4姓名());
            t.set担保提供者4生年月日(src.get担保提供者4生年月日());
            t.set担保提供者4郵便番号(src.get担保提供者4郵便番号());
            t.set担保提供者4住所(src.get担保提供者4住所());
            t.set物件入居予定者数(src.get物件入居予定者数());
            t.set物件入居家族構成(src.get物件入居家族構成());
            t.set居住区分(src.get居住区分());
            t.set建築購入費(src.get建築購入費());
            t.set土地取得費(src.get土地取得費());
            t.set融資額(src.get融資額());
            t.set予備1(src.get予備1());
            t.set返済方法(src.get返済方法());
            t.set希望返済期間(src.get希望返済期間());
            t.setボーナス払希望有無(src.getボーナス払希望有無());
            t.setボーナス払月(src.getボーナス払月());
            t.set希望額ボーナス分(src.get希望額ボーナス分());
            t.set融資実行希望年月日(src.get融資実行希望年月日());
            t.set借入公的(src.get借入公的());
            t.set借入公的＿借入先(src.get借入公的＿借入先());
            t.set借入公的＿返済期間(src.get借入公的＿返済期間());
            t.set借入公的＿金利(src.get借入公的＿金利());
            t.set借入公的＿毎月返済額(src.get借入公的＿毎月返済額());
            t.set借入民間(src.get借入民間());
            t.set借入民間＿借入先(src.get借入民間＿借入先());
            t.set借入民間＿返済期間(src.get借入民間＿返済期間());
            t.set借入民間＿金利(src.get借入民間＿金利());
            t.set借入民間＿毎月返済額(src.get借入民間＿毎月返済額());
            t.set借入勤務先(src.get借入勤務先());
            t.set借入勤務先＿返済期間(src.get借入勤務先＿返済期間());
            t.set借入勤務先＿毎月返済額(src.get借入勤務先＿毎月返済額());
            t.set借入親等(src.get借入親等());
            t.set借入親等＿借入先(src.get借入親等＿借入先());
            t.set借入親等＿返済期間(src.get借入親等＿返済期間());
            t.set借入親等＿毎月返済額(src.get借入親等＿毎月返済額());
            t.set借入返済土地(src.get借入返済土地());
            t.set借入返済土地＿返済期間(src.get借入返済土地＿返済期間());
            t.set借入返済土地＿毎月返済額(src.get借入返済土地＿毎月返済額());
            t.set手持金(src.get手持金());
            t.set住宅取得以外＿借入件数(src.get住宅取得以外＿借入件数());
            t.set住宅取得以外＿借入金額(src.get住宅取得以外＿借入金額());
            t.set住宅取得以外＿毎月返済額(src.get住宅取得以外＿毎月返済額());
            t.set事前審査申請日(src.get事前審査申請日());
            t.set本審査申請日(src.get本審査申請日());
            t.set申込受理日(src.get申込受理日());
            t.set定期借地権(src.get定期借地権());
            t.set前払賃料(src.get前払賃料());
            t.set買戻権(src.get買戻権());
            t.set保留地物件サイン(src.get保留地物件サイン());
            t.set金消契約年月日(src.get金消契約年月日());
            t.set審査金利(src.get審査金利());
            t.set返済負担率(src.get返済負担率());
            t.set担保評価額(src.get担保評価額());
            t.setアルヒ審査担当者(src.getアルヒ審査担当者());
            t.setＬＴＶ(src.getＬＴＶ());
            t.set保証承認番号(src.get保証承認番号());
            t.set保証承認日(src.get保証承認日());
            t.set保証料率(src.get保証料率());
            t.set事前審査回答日(src.get事前審査回答日());
            t.set事前審査結果(src.get事前審査結果());
            t.set事前審査担当者(src.get事前審査担当者());
            t.set本審査回答日当初(src.get本審査回答日当初());
            t.set本審査結果当初(src.get本審査結果当初());
            t.set本審査担当者(src.get本審査担当者());
            t.set本審査回答日最新(src.get本審査回答日最新());
            t.set本審査結果最新(src.get本審査結果最新());
            t.set申込目的＿出力用(src.get申込目的＿出力用());
            t.set保証会社意見欄(src.get保証会社意見欄());
            guaranteeResultMainAruhiTargetMapper.insert(t);
        }

        // ③-d41 保証結果融資条件じぶん -> 保証結果融資条件じぶん (MAX only) — 外部連携, 1:N per (申込番号, 申込目的), pass-through.
        List<保証結果融資条件じぶんSource> guaranteeResultFinanceJibuns =
                emptyIfNull(guaranteeResultFinanceJibunSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (保証結果融資条件じぶんSource src : guaranteeResultFinanceJibuns) {
            if (src == null) {
                continue;
            }
            保証結果融資条件じぶんTarget t = new 保証結果融資条件じぶんTarget();
            t.set申込番号(targetApplicationNumber);
            t.set申込目的(convertedPurpose);
            t.setイベント(src.getイベント());
            t.setイベント日時(src.getイベント日時());
            t.set連番(src.get連番());
            t.set状態(src.get状態());
            t.set出力日時(src.get出力日時());
            t.set外部案件番号(src.get外部案件番号());
            t.set融資条件通番(src.get融資条件通番());
            t.set融資条件コード(src.get融資条件コード());
            t.set融資条件内容(src.get融資条件内容());
            guaranteeResultFinanceJibunTargetMapper.insert(t);
        }

        // ③-d42 保証結果融資条件アルヒ -> 保証結果融資条件アルヒ (MAX only) — 外部連携, 1:N per (申込番号, 申込目的), pass-through.
        List<保証結果融資条件アルヒSource> guaranteeResultFinanceAruhis =
                emptyIfNull(guaranteeResultFinanceAruhiSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (保証結果融資条件アルヒSource src : guaranteeResultFinanceAruhis) {
            if (src == null) {
                continue;
            }
            保証結果融資条件アルヒTarget t = new 保証結果融資条件アルヒTarget();
            t.set申込番号(targetApplicationNumber);
            t.set申込目的(convertedPurpose);
            t.setイベント(src.getイベント());
            t.setイベント日時(src.getイベント日時());
            t.set連番(src.get連番());
            t.set状態(src.get状態());
            t.set出力日時(src.get出力日時());
            t.setＡＲＵＨＩ証書番号(src.getＡＲＵＨＩ証書番号());
            t.set融資条件コード(src.get融資条件コード());
            t.set融資条件内容(src.get融資条件内容());
            guaranteeResultFinanceAruhiTargetMapper.insert(t);
        }

        // ③-d43 担当者別操作管理 -> 担当者別操作管理 (MAX only) — ログ, 1:N per (申込番号, 申込目的).
        // 進捗コード converted via the 編集仕様詳細 code table; other columns pass through.
        List<担当者別操作管理Source> operationMgmtByPersons =
                emptyIfNull(operationMgmtByPersonSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
        for (担当者別操作管理Source src : operationMgmtByPersons) {
            if (src == null) {
                continue;
            }
            担当者別操作管理Target t = new 担当者別操作管理Target();
            t.set申込番号(targetApplicationNumber);
            t.set申込目的(convertedPurpose);
            t.setユーザＩＤ(src.getユーザＩＤ());
            t.set進捗コード(convertProgressCode(src.get進捗コード()));
            t.set開始日時(src.get開始日時());
            t.set終了日時(src.get終了日時());
            operationMgmtByPersonTargetMapper.insert(t);
        }

        // ③-e ＩＦ＿担保評価連携結果 (MAX only) — 1:N per (申込番号, 申込目的) from 担保評価回答.
        // 一連番号 fixed '99999'; valuation columns pass through.
        List<担保評価回答Source> collateralValuations =
                emptyIfNull(collateralValuationSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
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
                emptyIfNull(collateralValuationFileSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, maxSourcePurpose));
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

                // ⑤-a 履歴申込_業者_住宅 — FK to 履歴申込. Same gen-entity JOIN logic as 申込_業者_住宅,
                // but keyed per completed stage (sourcePurpose) instead of maxSourcePurpose, with 回数 set.
                SZB申込Key szbHistoryApplicationKey = new SZB申込Key();
                szbHistoryApplicationKey.set申込番号(sourceApplicationNumber);
                szbHistoryApplicationKey.set申込目的(sourcePurpose);
                SZB申込 szbHistoryApplication = vendorApplicationSourceMapper.selectByPrimaryKey(szbHistoryApplicationKey);
                String historyVendorCode = szbHistoryApplication != null ? szbHistoryApplication.get宅建業者コード() : null;

                SMS履歴申込＿業者＿住宅 historyVendorHousingTarget = new SMS履歴申込＿業者＿住宅();
                historyVendorHousingTarget.set申込番号(targetApplicationNumber);
                historyVendorHousingTarget.set申込目的(convertedPurpose);
                historyVendorHousingTarget.set回数((short) occurrenceNumber);
                historyVendorHousingTarget.set業者コード(historyVendorCode);
                if (historyVendorCode != null) {
                    SZB販売業者マスター historyVendor = vendorMasterSourceMapper.selectByPrimaryKey(historyVendorCode);
                    if (historyVendor != null) {
                        historyVendorHousingTarget.set業者名カナ(historyVendor.get販売業者名カナ());
                        historyVendorHousingTarget.set業者名(historyVendor.get販売業者名());
                    }
                }
                // insert() (not insertSelective) — same OGNL/full-width-underscore issue as above.
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
                    emptyIfNull(guarantorSourceMapper.selectByApplicationIdAndPurpose(sourceApplicationNumber, sourcePurpose));
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

    // Returns an empty list when the given list is null, so 検索結果 (search results)
    // can be iterated safely without a NullPointerException.
    private static <T> List<T> emptyIfNull(List<T> list) {
        return list == null ? java.util.Collections.emptyList() : list;
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

    /** 借入有無 (コード設定「1960」): vendor name blank → 2:なし, otherwise 1:あり. */
    private static String convertHasBorrowing(String vendorName) {
        return (vendorName == null || vendorName.isEmpty()) ? "2" : "1";
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
        if (source == null || target == null) {
            return;
        }
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
        if (source == null || target == null) {
            return;
        }
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
        target.set金融機関1借入有無(convertHasBorrowing(source.get借入＿利用先名1())); // コード設定「1960:借入有無」
        // 金融機関 2
        target.set金融機関2名称(truncateToByteLimit(source.get借入＿利用先名2(), 30));
        target.set金融機関2借入種類(convertBorrowingType(source.get借入＿利用種類2()));
        target.set金融機関2残高(source.get借入＿利用残高2());
        target.set金融機関2借入期間(source.get借入＿残存期間2());
        target.set金融機関2借入時完済解約予定(convertPayoffPlanAtBorrowing(source.get借入＿解約予定2()));
        target.set金融機関2利用限度額(source.get借入＿利用限度額2());
        target.set金融機関2借入年間返済額(source.get借入＿年間支払額2());
        target.set金融機関2借入有無(convertHasBorrowing(source.get借入＿利用先名2())); // コード設定「1960:借入有無」
        // 金融機関 3
        target.set金融機関3名称(truncateToByteLimit(source.get借入＿利用先名3(), 30));
        target.set金融機関3借入種類(convertBorrowingType(source.get借入＿利用種類3()));
        target.set金融機関3残高(source.get借入＿利用残高3());
        target.set金融機関3借入期間(source.get借入＿残存期間3());
        target.set金融機関3借入時完済解約予定(convertPayoffPlanAtBorrowing(source.get借入＿解約予定3()));
        target.set金融機関3利用限度額(source.get借入＿利用限度額3());
        target.set金融機関3借入年間返済額(source.get借入＿年間支払額3());
        target.set金融機関3借入有無(convertHasBorrowing(source.get借入＿利用先名3())); // コード設定「1960:借入有無」

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

        // 同居予定家族＿子供人数 = count of non-null 子供年齢1〜4 (source has no single child-count column)
        int childCount = 0;
        if (source.get同居＿子供年齢1() != null) childCount++;
        if (source.get同居＿子供年齢2() != null) childCount++;
        if (source.get同居＿子供年齢3() != null) childCount++;
        if (source.get同居＿子供年齢4() != null) childCount++;
        target.set同居予定家族＿子供人数(new BigDecimal(childCount));

        // 同居予定家族＿合計人数 = 1 (本人) + 配偶者 + 父 + 母 + 子供人数 + その他人数
        int cohabitantTotal = 1;
        if ("1".equals(source.get同居＿配偶者())) cohabitantTotal++;
        if ("1".equals(source.get同居＿父()))    cohabitantTotal++;
        if ("1".equals(source.get同居＿母()))    cohabitantTotal++;
        cohabitantTotal += childCount;
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
        // 収入がある同居の配偶者: コード設定「0112」. [申込ワイド.配偶者年収] > 0 → 1:あり, else 2:なし.
        BigDecimal spouseIncome = source.get配偶者年収();
        target.set収入がある同居の配偶者(spouseIncome != null && spouseIncome.compareTo(BigDecimal.ZERO) > 0 ? "1" : "2");

        target.set無担保集中＿申込書番号(FIXED_MUTAN_SHUCHU_APPLICATION_NUMBER); // 設計書No.656: 固定値、口座開設ディテッカー連携除外用

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
