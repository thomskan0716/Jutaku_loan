/**
 *
 */
package migration.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import migration.common.MyUtil;
import migration.common.amigo_0149_c.Eセゾン審査結果;
import migration.common.amigo_0149_c.E不動産収入;
import migration.common.amigo_0149_c.E事業収入;
import migration.common.amigo_0149_c.E住居形態0__通常申込書;
import migration.common.amigo_0149_c.E住居形態0__通常申込書_BQL;
import migration.common.amigo_0149_c.E住居形態1__セレカ申込書;
import migration.common.amigo_0149_c.E住居形態2__オリックス申込書;
import migration.common.amigo_0149_c.E住居形態3__ジャックス申込書;
import migration.common.amigo_0149_c.E住居形態4__集中実行申込書;
import migration.common.amigo_0149_c.E住居形態5__SDC申込書;
import migration.common.amigo_0149_c.E住居形態6__セレカミニ;
import migration.common.amigo_0149_c.E保証会社側審査結果;
import migration.common.amigo_0149_c.E保証会社統合;
import migration.common.amigo_0149_c.E借入担保;
import migration.common.amigo_0149_c.E借入用途;
import migration.common.amigo_0149_c.E健康保険証種類;
import migration.common.amigo_0149_c.E勤務先職業0__通常申込書;
import migration.common.amigo_0149_c.E勤務先職業1__セレカ申込書;
import migration.common.amigo_0149_c.E勤務先職業2__オリックス申込書;
import migration.common.amigo_0149_c.E勤務先職業3__ジャックス申込書;
import migration.common.amigo_0149_c.E勤務先職業4__集中実行申込書;
import migration.common.amigo_0149_c.E勤務先職業5__SDC申込書;
import migration.common.amigo_0149_c.E勤務先職業6__セレカミニ;
import migration.common.amigo_0149_c.E勤務先職種;
import migration.common.amigo_0149_c.E勤務先職種__セレカ;
import migration.common.amigo_0149_c.E勤務先資本金;
import migration.common.amigo_0149_c.E受付チャネル;
import migration.common.amigo_0149_c.E合格状況;
import migration.common.amigo_0149_c.E商品性区分;
import migration.common.amigo_0149_c.E商品統合;
import migration.common.amigo_0149_c.E団体信用生命保険加入;
import migration.common.amigo_0149_c.E外国PEPs;
import migration.common.amigo_0149_c.E審査結果;
import migration.common.amigo_0149_c.E就学同居有無;
import migration.common.amigo_0149_c.E就学続柄;
import migration.common.amigo_0149_c.E就学進学先;
import migration.common.amigo_0149_c.E役職0__通常申込書;
import migration.common.amigo_0149_c.E役職1__セレカ申込書;
import migration.common.amigo_0149_c.E役職2__オリックス申込書;
import migration.common.amigo_0149_c.E役職3__ジャックス申込書;
import migration.common.amigo_0149_c.E役職4__集中実行申込書;
import migration.common.amigo_0149_c.E役職5__SDC申込書;
import migration.common.amigo_0149_c.E役職6__セレカミニ;
import migration.common.amigo_0149_c.E従業員数0__通常申込書;
import migration.common.amigo_0149_c.E従業員数1__セレカ申込書;
import migration.common.amigo_0149_c.E従業員数2__オリックス申込書;
import migration.common.amigo_0149_c.E従業員数3__ジャックス申込書;
import migration.common.amigo_0149_c.E従業員数4__集中実行申込書;
import migration.common.amigo_0149_c.E従業員数5__SDC申込書;
import migration.common.amigo_0149_c.E従業員数6__セレカミニ;
import migration.common.amigo_0149_c.E性別;
import migration.common.amigo_0149_c.E成年後見制度の利用;
import migration.common.amigo_0149_c.E業種0__通常申込書;
import migration.common.amigo_0149_c.E業種1__セレカ申込書;
import migration.common.amigo_0149_c.E業種2__オリックス申込書;
import migration.common.amigo_0149_c.E業種3__ジャックス申込書;
import migration.common.amigo_0149_c.E業種4__集中実行申込書;
import migration.common.amigo_0149_c.E業種5__SDC申込書;
import migration.common.amigo_0149_c.E業種6__セレカミニ;
import migration.common.amigo_0149_c.E申込目的;
import migration.common.amigo_0149_c.E自動車保険提案の同意;
import migration.common.amigo_0149_c.E資金使途;
import migration.common.amigo_0149_c.E資金使途0__通常申込書;
import migration.common.amigo_0149_c.E資金使途1__セレカ申込書;
import migration.common.amigo_0149_c.E資金使途2__オリックス申込書;
import migration.common.amigo_0149_c.E資金使途3__ジャックス申込書;
import migration.common.amigo_0149_c.E資金使途4__集中実行申込書;
import migration.common.amigo_0149_c.E資金使途5__SDC申込書;
import migration.common.amigo_0149_c.E資金使途6__セレカミニ;
import migration.common.amigo_0149_c.E返済用口座科目;
import migration.common.amigo_0149_c.E進歩;
import migration.common.amigo_0149_c.E進歩__BQL__正式;
import migration.common.amigo_0149_c.E進歩__アイフルローン__事前;
import migration.common.amigo_0149_c.E進歩__アイフルローン__正式;
import migration.common.amigo_0149_c.E進歩__カードローン__正式;
import migration.common.amigo_0149_c.E進歩__セレカ__事前;
import migration.common.amigo_0149_c.E進歩__セレカ__正式;
import migration.common.amigo_0149_c.E進歩__極壇__正式;
import migration.common.amigo_0149_c.E進歩__無担保__事前;
import migration.common.amigo_0149_c.E進歩__無担保__正式;
import migration.common.amigo_0149_c.E希望連絡先;
import migration.common.amigo_0149_c.E配偶者有無;
import migration.common.amigo_0149_c.E端末区分;
import migration.mybatis.domain.amigo_0149_c.MGood;
import migration.mybatis.domain.amigo_0149_c.MUser;
import migration.mybatis.domain.amigo_0149_c.VBanktrade;
import migration.mybatis.domain.amigo_0149_c.VEntryData;
import migration.mybatis.domain.amigo_0149_c.VEntryEtc;
import migration.mybatis.domain.amigo_0149_c.VEntryLoan;
import migration.mybatis.domain.amigo_0149_c.VEntryMain;
import migration.mybatis.domain.amigo_0149_c.VEntryOffice;
import migration.mybatis.domain.amigo_0149_c.VEntrySub;
import migration.mybatis.domain.amigo_0149_c.VImgpr;
import migration.mybatis.domain.amigo_0149_c.VJudgeGuarant;
import migration.mybatis.domain.amigo_0149_c.VJudgeMain;
import migration.mybatis.domain.amigo_0149_c.VJudgeResult;
import migration.mybatis.domain.amigo_0149_c.VSaisonResult;
import migration.mybatis.domain.amigo_0149_c.VSendfaxExt;
import migration.mybatis.domain.amigo_0149_c.VWebdata;
import migration.mybatis.domain.sms.Vイメージ;
import migration.mybatis.domain.sms.商品マスター;
import migration.mybatis.domain.sms.審査コメント;
import migration.mybatis.domain.sms.審査コメント表示ユーザ区分;
import migration.mybatis.domain.sms.審査結果;
import migration.mybatis.domain.sms.申込;
import migration.mybatis.domain.sms.申込審査履歴;
import migration.mybatis.domain.sms.申込審査段階;
import migration.mybatis.domain.sms.申込審査状況;
import migration.mybatis.domain.sms.申込徴求資料;
import migration.mybatis.domain.sms.申込進捗;
import migration.mybatis.domain.sms.申込顛末管理;
import migration.mybatis.domain.sms.申込__振込先__無担保;
import migration.mybatis.domain.sms.申込__無担保__教育;
import migration.mybatis.domain.sms.移行管理テーブル;
import migration.mybatis.domain.sms.Ｆａｘ受信振分;
import migration.mybatis.mapper.amigo_0149_c.MGoodMapper;
import migration.mybatis.mapper.amigo_0149_c.MUserMapper;
import migration.mybatis.mapper.amigo_0149_c.VBanktradeMapper;
import migration.mybatis.mapper.amigo_0149_c.VEntryEtcMapper;
import migration.mybatis.mapper.amigo_0149_c.VEntryLoanMapper;
import migration.mybatis.mapper.amigo_0149_c.VEntryMainMapper;
import migration.mybatis.mapper.amigo_0149_c.VEntryOfficeMapper;
import migration.mybatis.mapper.amigo_0149_c.VEntrySubMapper;
import migration.mybatis.mapper.amigo_0149_c.VImgprMapper;
import migration.mybatis.mapper.amigo_0149_c.VJudgeGuarantMapper;
import migration.mybatis.mapper.amigo_0149_c.VJudgeMainMapper;
import migration.mybatis.mapper.amigo_0149_c.VJudgeResultMapper;
import migration.mybatis.mapper.amigo_0149_c.VSaisonResultMapper;
import migration.mybatis.mapper.amigo_0149_c.VSdcResultMapper;
import migration.mybatis.mapper.amigo_0149_c.VSendfaxExtMapper;
import migration.mybatis.mapper.amigo_0149_c.VWebdataMapper;
import migration.mybatis.mapper.sms.Vイメージ番号採番Mapper;
import migration.mybatis.mapper.sms.V移行管理テーブルMapper;
import migration.mybatis.mapper.sms.商品マスターMapper;
import migration.mybatis.mapper.sms.審査コメントMapper;
import migration.mybatis.mapper.sms.審査コメント表示ユーザ区分Mapper;
import migration.mybatis.mapper.sms.審査結果Mapper;
import migration.mybatis.mapper.sms.申込Mapper;
import migration.mybatis.mapper.sms.申込審査履歴Mapper;
import migration.mybatis.mapper.sms.申込審査段階Mapper;
import migration.mybatis.mapper.sms.申込審査状況Mapper;
import migration.mybatis.mapper.sms.申込徴求資料Mapper;
import migration.mybatis.mapper.sms.申込進捗Mapper;
import migration.mybatis.mapper.sms.申込顛末管理Mapper;
import migration.mybatis.mapper.sms.申込__振込先__無担保Mapper;
import migration.mybatis.mapper.sms.申込__無担保__教育Mapper;
import migration.mybatis.mapper.sms.Ｆａｘ受信振分Mapper;

/**
 * <p>
 * 無担保ローン申込データ移行サービス
 * </p>
 * <p>
 * AMIGO_C（無担保集中システム）から新システム(Scope)への無担保ローン申込データの移行処理を行います。
 * 申込審査状況、申込基本情報、審査結果、画像データ等の変換・登録処理を実行します。
 * </p>
 *
 * @author sun.mkashiyama
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MutanpoService {

    @Value("${batch.faximagedir}")
    public String FAX_BASE_DIR;

    @Value("${batch.imgbasedir}")
    public String IMG_BASE_DIR;

    @Value("${batch.imgoutdir}")
    public String IMG_OUT_DIR;

    @Value("${batch.faxoutdir}")
    public String FAX_OUT_DIR;

    @Value("${batch.process}")
    public String PROCESS;

    // 1:銀行 2:保証会社
    // 銀行の場合は保証会社コメントを登録しない
    // 保証外車の場合は画像を登録しない。
    @Value("${batch.kind}")
    public String kind;

    // amigo_c
    @Autowired
    private VEntryMainMapper vEntryMainMapper;
    @Autowired
    private VEntryEtcMapper vEntryEtcMapper;
    @Autowired
    private VEntryLoanMapper vEntryLoanMapper;
    @Autowired
    private VEntryOfficeMapper vEntryOfficeMapper;
    @Autowired
    private VEntrySubMapper vEntrySubMapper;
    @Autowired
    private VWebdataMapper vWebdataMapper;
    @Autowired
    private VJudgeMainMapper vJudgeMainMapper;
    @Autowired
    private VJudgeResultMapper vJudgeResultMapper;
    @Autowired
    private VSaisonResultMapper vSaisonResultMapper;
    @Autowired
    private VSdcResultMapper vSdcResultMapper;
    @Autowired
    private VSendfaxExtMapper vSendfaxExtMapper;
    @Autowired
    private VImgprMapper vImgprMapper;
    @Autowired
    private MUserMapper mUserMapper;
    @Autowired
    private MGoodMapper mGoodMapper;
    @Autowired
    private VBanktradeMapper vBanktradeMapper;
    @Autowired
    private VJudgeGuarantMapper vJudgeGuarantMapper;

    @Autowired
    private 申込Mapper moushikomiMapper;
    @Autowired
    private 申込__振込先__無担保Mapper moushikomiFurikomisakiMapper;
    @Autowired
    private 申込__無担保__教育Mapper moushikomiKyoikuMapper;
    @Autowired
    private 申込審査段階Mapper moushikomiShinsadankaiMapper;
    @Autowired
    private 申込審査段階Mapper moushikomiShinsadankaiMapper2;
    @Autowired
    private 申込審査履歴Mapper moushikomiShinsarirekiMapper;
    @Autowired
    private 申込審査状況Mapper moushikomiShinsaJyokyoMapper;
    @Autowired
    private 申込進捗Mapper moushikomiShinchokuMapper;
    @Autowired
    private 申込顛末管理Mapper moushikomiTenmatsukanriMapper;
    @Autowired
    private 審査結果Mapper shinsaKekkaMapper;
    @Autowired
    private Ｆａｘ受信振分Mapper faxJyusinfuriwakeMapper;
    @Autowired
    private 申込徴求資料Mapper moushikomichokyushiryoMapper;
    @Autowired
    private 商品マスターMapper shohinMapper;
    @Autowired
    private Vイメージ番号採番Mapper imageNoSequenceMapper;
    @Autowired
    private final V移行管理テーブルMapper manageMapper;
    @Autowired
    private 審査コメントMapper shinsaCommentMapper;
    @Autowired
    private 審査コメント表示ユーザ区分Mapper shinsaCommentHyojiUserKubunMapper;

    private static final Logger logger = LoggerFactory.getLogger(MutanpoService.class);

    private HashMap<Integer, MUser> mUserMap = new HashMap<Integer, MUser>();
    private HashMap<Integer, MGood> mGoodsMap = new HashMap<Integer, MGood>();
    private HashMap<String, 商品マスター> mShohinMap = new HashMap<String, 商品マスター>();

    /** 全レンジを処理（TODO/ERRORのみ） */
    public void processAll() {
        List<MUser> userList = mUserMapper.selectByExample(null);
        for (MUser userRecord : userList) {
            mUserMap.put(userRecord.getChargeCd(), userRecord);
        }
        List<MGood> goodList = mGoodMapper.selectByExample(null);
        for (MGood goodRecord : goodList) {
            mGoodsMap.put(goodRecord.getGoodsCd(), goodRecord);
        }
        List<商品マスター> shohinList = shohinMapper.selectByExample(null);
        for (商品マスター shohinRecord : shohinList) {
            mShohinMap.put(shohinRecord.get商品コード(), shohinRecord);
        }
    }

    /** 次のレンジをロックして 'RUNNING' に */
    @Transactional
    public 移行管理テーブル claimNextRange() {
        移行管理テーブル r = manageMapper.selectNextRangeForUpdate(); // FOR UPDATE SKIP LOCKED
        if (r == null)
            return null;
        LocalDateTime now = LocalDateTime.now();
        Timestamp ts = Timestamp.valueOf(now);
        r.setステータス("RUNNING");
        r.set開始日時(ts);
        manageMapper.updateStatusStart(r);
        return r;
    }

    /** 1レンジを処理（独立トランザクション推奨） */
    @Transactional
    public void processOneRange(移行管理テーブル r) {
        // 1) メインIDを 5000件 取得
        List<Long> mainIds = vJudgeMainMapper.selectMainIdsByRowWindow(r.get処理from(), r.get処理to());
        log.info("レンジ from={} to={} : {}件", r.get処理from(), r.get処理to(), r.get処理to() - r.get処理from());
        if (mainIds.isEmpty()) {
            return;
        }

        writeSubsInBatch(r);
    }

    /** サブデータの書き込み（JDBCバッチを使う方法の一例） */
    private void writeSubsInBatch(移行管理テーブル r) {

        int cnt = 0;

        HashMap<Long, VJudgeMain> judgeMainMap = createJudgemain(r.get処理from(), r.get処理to());
        HashMap<Long, VEntryData> entryMainMap = createEntryData(r.get処理from(), r.get処理to());
        // 審査結果情報Mapを取得する
        // （Key：申込書番号, Value：審査結果Dtoリスト）
        HashMap<Long, List<VJudgeResult>> judgeResultMap = createJudgeResult(r.get処理from(), r.get処理to());

        // セゾン審査結果Mapを取得する
        // （Key：申込書番号, Value：セゾン審査結果Dto）
        HashMap<Long, VSaisonResult> saisonResultMap = createSaisonResult(r.get処理from(), r.get処理to());

        // 振込先金融機関Mapを取得する
        // （Key：申込書番号, Value：セゾン審査結果Dto）
        HashMap<Long, List<VBanktrade>> bankTradeMap = createBankTrade(r.get処理from(), r.get処理to());

        // SDC審査結果Mapを取得する
        // （Key：申込書番号, Value：SDC審査結果Dtoリスト）
        HashMap<Long, List<VJudgeResult>> sdcResultMap = createSdcResult(r.get処理from(), r.get処理to());

        // イメージPRMapを取得する
        // （Key：申込書番号, Value：イメージ未処理Dto）
        HashMap<Long, VImgpr> imgPrMap = createImgpr(r.get処理from(), r.get処理to());

        // 外部Faxi送信Mapを取得する
        // （Key：申込書番号, Value：FAX送信情報Dto）
        HashMap<Long, VSendfaxExt> imgFaxMap = createSendFax(r.get処理from(), r.get処理to());

        // 保証会社コメント取得する
        HashMap<Long, VJudgeGuarant> vJudgeGuarantMap = createJudgeGuarant(r.get処理from(), r.get処理to());

        for (Map.Entry<Long, VJudgeMain> s : judgeMainMap.entrySet()) {
            cnt = cnt + 1;

            VJudgeMain judgeMainRecord = judgeMainMap.get(s.getKey());
            VEntryData entryMainRecord = entryMainMap.get(s.getKey());
            List<VJudgeResult> judgeResultList = judgeResultMap.get(s.getKey());
            VSaisonResult saisonResultRecord = saisonResultMap.get(s.getKey());
            List<VJudgeResult> sdcResultList = sdcResultMap.get(s.getKey());
            List<VBanktrade> bankTradeList = bankTradeMap.get(s.getKey());
            VSendfaxExt vSendfaxExt = imgFaxMap.get(s.getKey());
            VJudgeGuarant vJudgeGuarant = vJudgeGuarantMap.get(s.getKey());
            VImgpr imgPr = imgPrMap.get(s.getKey());

            String requestNo = MyUtil.convString(entryMainRecord.getVEntryMain().getRequestNo());

            // 申込目的
            String moushikomiMokuteki = null;
            try {
                E申込目的 eMoushimokumokuteki = E申込目的.fromOldCode(entryMainRecord.getVEntryMain().getRequestId());
                moushikomiMokuteki = eMoushimokumokuteki.getNewCode();
            } catch (IllegalArgumentException e) {
                logger.debug("[申込番号={} {}の{}が変換できませんでした。 ", requestNo, "E申込目的",
                        entryMainRecord.getVEntryMain().getRequestId());
                continue;
            }

            // 商品統合
            String shohinCd = null;
            try {
                E商品統合 eShohinTogo = E商品統合
                        .fromOldCode(MyUtil.convString(entryMainRecord.getVEntryMain().getGoodsCd()));
                shohinCd = eShohinTogo.getNewCode();
            } catch (IllegalArgumentException e) {
                logger.debug("[申込番号={} {}の{}が変換できませんでした。 ", requestNo, "E商品統合",
                        MyUtil.convString(entryMainRecord.getVEntryMain().getGoodsCd()));
                continue;
            }

            // 商品コード
            商品マスター ShohinMaster = mShohinMap.get(shohinCd);

            進捗商品情報 shinchokuSyohin = new 進捗商品情報(moushikomiMokuteki, ShohinMaster);
            MGood mGood = mGoodsMap.get(entryMainRecord.getVEntryMain().getGoodsCd());
            E保証会社統合 eHosyokaisyaCd = null;
            try {
                String hsCode = String.format("%04d", Integer.valueOf(mGood.getHsCode())); // "0001"
                eHosyokaisyaCd = E保証会社統合.fromOldCode(hsCode);

            } catch (IllegalArgumentException e) {
                if (mGood.getHsCode() != null && eHosyokaisyaCd == null) {
                    eHosyokaisyaCd = E保証会社統合.旧保証会社;
                }
                logger.debug("[保証会社統合={} {}の{}が変換できませんでした。 ", mGood.getHsCode(), "E保証会社統合");
            }

            申込審査状況 moushikomiJyokyo = null;
            申込進捗 moushikomiShinchoku = null;
            申込 moushikomi = null;
            申込審査段階 moushikomiDankai = null;
            List<申込審査履歴> moushikomiShinsaRirekiList = null;
            List<審査結果> shinsaKekkaList = null;
            審査コメント shinsaComment = null;
            審査コメント表示ユーザ区分 shinsaCommentHhyojiKubun = null;

            // 必須系テーブルの登録
            try {
                moushikomiJyokyo = convMoushikomiShinsajyoukyou(judgeMainRecord, requestNo, moushikomiMokuteki);
                moushikomiShinchoku = convMoushikomiShinchoku(judgeMainRecord, requestNo, shinchokuSyohin);
                moushikomi = convMoushikomi(entryMainRecord, bankTradeList, requestNo, moushikomiMokuteki, mGood,
                        ShohinMaster, judgeMainRecord);
                moushikomiDankai = convMoushikomiShinsadankai(judgeMainRecord, judgeResultList,
                        saisonResultRecord, sdcResultList, requestNo, moushikomiMokuteki, eHosyokaisyaCd);
                // 必須系テーブルがnullの場合は、処理終了
                if (moushikomiJyokyo == null || moushikomiShinchoku == null || moushikomi == null
                        || moushikomiDankai == null) {
                    continue;
                }
            } catch (Exception e) {
                logger.error("申込番号={}の変換に失敗しました。{}", moushikomiShinchoku.get申込番号(), e.getMessage());
                continue;
            }

            moushikomiShinsaRirekiList = convMoushikomiShinsarireki(judgeMainRecord, judgeResultList,
                    sdcResultList, saisonResultRecord, requestNo, moushikomiMokuteki, shinchokuSyohin, vJudgeGuarant);

            shinsaKekkaList = convShinsakekka(judgeResultList, sdcResultList, saisonResultRecord,
                    judgeMainRecord, requestNo, moushikomiMokuteki, shohinCd, eHosyokaisyaCd);

            shinsaComment = convShinsaComment(judgeMainRecord, requestNo, moushikomiMokuteki, vJudgeGuarant);
            shinsaCommentHhyojiKubun = convShinsaCommentHyojiUserKubun(judgeMainRecord, requestNo, moushikomiMokuteki, vJudgeGuarant);

            if ("0".equals(PROCESS)) {
                try {
                    // 申込進捗
                    moushikomiShinchokuMapper.insert(moushikomiShinchoku);
                    logger.info("申込進捗:申込番号={}を登録しました。 ", moushikomiShinchoku.get申込番号());
                } catch (Exception e) {
                    logger.error("申込進捗:申込番号={}が登録できませんでした。{}", moushikomiShinchoku.get申込番号(), e.getMessage());
                    continue;
                }
            }

            if ("1".equals(PROCESS) || "0".equals(PROCESS)) {
                try {
                    // 申込
                    moushikomiMapper.insert(moushikomi);
                    logger.info("申込:申込番号={},申込目的={}を登録しました。 ", moushikomi.get申込番号(), moushikomi.get申込目的());
                } catch (Exception e) {
                    logger.error("申込:申込番号={},申込目的={}が登録できませんでした。{}", moushikomi.get申込番号(), moushikomi.get申込目的(),
                            e.getMessage());
                    continue;
                }

                try {
                    // 申込審査状況
                    moushikomiShinsaJyokyoMapper.insert(moushikomiJyokyo);
                    logger.info("申込審査状況:申込番号={},申込目的={}を登録しました。 ", moushikomiJyokyo.get申込番号(),
                            moushikomiJyokyo.get申込目的());
                } catch (Exception e) {
                    logger.error("申込審査状況:申込番号={},申込目的={}が登録できませんでした。{}", moushikomiJyokyo.get申込番号(),
                            moushikomiJyokyo.get申込目的(), e.getMessage());
                    continue;
                }

                try {
                    // 申込審査段階
                    moushikomiShinsadankaiMapper.insert(moushikomiDankai);
                    logger.info("申込審査段階:申込番号={},申込目的={}を登録しました。 ", moushikomiDankai.get申込番号(),
                            moushikomiDankai.get申込目的());
                } catch (Exception e) {
                    logger.error("申込審査段階:申込番号={},申込目的={}が登録できませんでした。{}", moushikomiDankai.get申込番号(),
                            moushikomiDankai.get申込目的(), e.getMessage());
                    continue;
                }
            }

            if ("2".equals(PROCESS) || "0".equals(PROCESS) || "1".equals(PROCESS)) {
                // 申込審査履歴
                for (申込審査履歴 record : moushikomiShinsaRirekiList) {
                    try {
                        moushikomiShinsarirekiMapper.insert(record);
                        logger.info("申込審査履歴:申込番号={},申込目的={},イベント= {},イベント日時={}を登録しました。 ", record.get申込番号(),
                                record.get申込目的(), record.getイベント(), record.getイベント日時());
                    } catch (Exception e) {
                        logger.error("申込審査履歴:申込番号={},申込目的={},イベント= {},イベント日時={}が登録できませんでした。{}", record.get申込番号(),
                                record.get申込目的(), record.getイベント(), record.getイベント日時(), e.getMessage());
                        continue;
                    }
                }

                // 審査結果
                for (審査結果 record : shinsaKekkaList) {
                    try {
                        shinsaKekkaMapper.insert(record);
                        logger.info("審査結果:申込番号={},申込目的={},イベント= {},イベント日時={}を登録しました。 ", record.get申込番号(),
                                record.get申込目的(), record.getイベント(), record.getイベント日時());
                    } catch (Exception e) {
                        logger.error("審査結果:申込番号={},申込目的={},イベント= {},イベント日時={}が登録できませんでした。{}", record.get申込番号(),
                                record.get申込目的(), record.getイベント(), record.getイベント日時(), e.getMessage());
                        continue;
                    }
                }

                if ("2".equals(kind)) {
                    // 審査コメント
                    if (shinsaComment != null) {
                        try {
                            shinsaCommentMapper.insert(shinsaComment);
                            logger.info("審査コメント:申込番号={},申込目的={},イベント= {},イベント日時={}を登録しました。 ", shinsaComment.get申込番号(),
                                    shinsaComment.get申込目的(), shinsaComment.getイベント(), shinsaComment.getイベント日時());
                        } catch (Exception e) {
                            logger.error("審査結果:申込番号={},申込目的={},イベント= {},イベント日時={}が登録できませんでした。{}", shinsaComment.get申込番号(),
                                    shinsaComment.get申込目的(), shinsaComment.getイベント(), shinsaComment.getイベント日時(), e.getMessage());
                            continue;
                        }
                    }

                    // 審査コメント表示ユーザ区分
                    if (shinsaCommentHhyojiKubun != null) {
                        try {
                            shinsaCommentHyojiUserKubunMapper.insert(shinsaCommentHhyojiKubun);
                            logger.info("審査コメント表示ユーザ区分:申込番号={},申込目的={},イベント= {},イベント日時={}を登録しました。 ", shinsaCommentHhyojiKubun.get申込番号(),
                                    shinsaCommentHhyojiKubun.get申込目的(), shinsaCommentHhyojiKubun.getイベント(), shinsaCommentHhyojiKubun.getイベント日時());
                        } catch (Exception e) {
                            logger.error("審査結果:申込番号={},申込目的={},イベント= {},イベント日時={}が登録できませんでした。{}", shinsaCommentHhyojiKubun.get申込番号(),
                                    shinsaCommentHhyojiKubun.get申込目的(), shinsaCommentHhyojiKubun.getイベント(), shinsaCommentHhyojiKubun.getイベント日時(), e.getMessage());
                            continue;
                        }
                    }
                }
            }

            // 任意系テーブルの登録
            申込__振込先__無担保 moushikomiFurikomisaki = convMoushikomiFurikomisaki(entryMainRecord, requestNo, moushikomiMokuteki);
            申込__無担保__教育 moushikomiKyoiku = convMoushikomiKyoiku(entryMainRecord, requestNo, moushikomiMokuteki);
            申込顛末管理 moushikomiTenmatsukanri = convMoushikomiTenmatsukanri(judgeMainRecord, requestNo);

            if ("3".equals(PROCESS) || "0".equals(PROCESS) || "1".equals(PROCESS)) {
                try {
                    // 申込__振込先__無担保
                    if (moushikomiFurikomisaki != null) {
                        moushikomiFurikomisakiMapper.insert(moushikomiFurikomisaki);
                        logger.info("申込__振込先__無担保:申込番号={},申込目的={}を登録しました。 ", moushikomiFurikomisaki.get申込番号(),
                                moushikomiFurikomisaki.get申込目的());
                    }
                } catch (Exception e) {
                    logger.error("申込__振込先__無担保:申込番号={},申込目的={}が登録できませんでした。{}", moushikomiFurikomisaki.get申込番号(),
                            moushikomiFurikomisaki.get申込目的(), e.getMessage());
                }

                try {
                    // 申込__無担保__教育
                    if (moushikomiKyoiku != null) {
                        moushikomiKyoikuMapper.insert(moushikomiKyoiku);
                        logger.info("申込__無担保__教育:申込番号={},申込目的={}を登録しました。 ", moushikomiKyoiku.get申込番号(),
                                moushikomiKyoiku.get申込目的());
                    }
                } catch (Exception e) {
                    logger.error("申込__無担保__教育:申込番号={},申込目的={}が登録できませんでした。{}", moushikomiKyoiku.get申込番号(),
                            moushikomiKyoiku.get申込目的(), e.getMessage());
                }
            }

            try {
                // 申込顛末管理
                if (moushikomiTenmatsukanri != null) {
                    moushikomiTenmatsukanriMapper.insert(moushikomiTenmatsukanri);
                    logger.info("申込顛末管理:申込番号={}を登録しました。 ", moushikomiTenmatsukanri.get申込番号());
                }
            } catch (Exception e) {
                logger.error("申込顛末管理:申込番号={}が登録できませんでした。{}", moushikomiTenmatsukanri.get申込番号(), e.getMessage());
            }

            if ("0".equals(PROCESS)) {
                // イメージ
                // ＦＡＸ受信振分・申込徴求資料
                Integer pageNo = 0; // イメージ・fax送信画像共通ページ番号
                List<Vイメージ> vImageList = convImagepr(imgPr, requestNo, moushikomiMokuteki);
                for (Vイメージ record : vImageList) {
                    pageNo += 1;

                    try {
                        // ＦＡＸ受信振分
                        if (record.getFaxJyushinfuriwake() != null) {
                            faxJyusinfuriwakeMapper.insert(record.getFaxJyushinfuriwake());
                            logger.info("ＦＡＸ受信振分:ＦＡＸ受信番号={}ＦＡＸ受信日時={}を登録しました。 ",
                                    record.getFaxJyushinfuriwake().getＦａｘ受信番号(),
                                    record.getFaxJyushinfuriwake().getＦａｘ受信日時());
                        }
                    } catch (Exception e) {
                        logger.error("ＦＡＸ受信振分:ＦＡＸ受信番号={}ＦＡＸ受信日時={}が登録できませんでした。 ",
                                record.getFaxJyushinfuriwake().getＦａｘ受信番号(),
                                record.getFaxJyushinfuriwake().getＦａｘ受信日時());
                    }

                    try {
                        // 申込徴求資料
                        if (record.getMoushikomiChokyushiryo() != null) {
                            record.getMoushikomiChokyushiryo().setページ番号(MyUtil.convShort(pageNo));
                            moushikomichokyushiryoMapper.insert(record.getMoushikomiChokyushiryo());
                            logger.info("申込徴求資料申込番号={}を登録しました。 ", record.getMoushikomiChokyushiryo().get申込番号());
                        }
                    } catch (Exception e) {
                        logger.error("申込徴求資料:申込番号={}が登録できませんでした。{}", record.getMoushikomiChokyushiryo().get申込番号(),
                                e.getMessage());
                    }

                    // 画像ファイルの移動
                    moveImage(record, IMG_OUT_DIR);
                }

                // fax送信画像
                // ＦＡＸ受信振分・申込徴求資料
                vImageList = convFaxJyushinFuriwake(vSendfaxExt, requestNo, moushikomiMokuteki);
                for (Vイメージ record : vImageList) {
                    pageNo += 1;

                    try {
                        // ＦＡＸ受信振分
                        if (record.getFaxJyushinfuriwake() != null) {
                            faxJyusinfuriwakeMapper.insert(record.getFaxJyushinfuriwake());
                            logger.info("ＦＡＸ受信振分:ＦＡＸ受信番号={}ＦＡＸ受信日時={}を登録しました。 ",
                                    record.getFaxJyushinfuriwake().getＦａｘ受信番号(),
                                    record.getFaxJyushinfuriwake().getＦａｘ受信日時());
                        }
                    } catch (Exception e) {
                        logger.error("ＦＡＸ受信振分:ＦＡＸ受信番号={}ＦＡＸ受信日時={}が登録できませんでした。 ",
                                record.getFaxJyushinfuriwake().getＦａｘ受信番号(),
                                record.getFaxJyushinfuriwake().getＦａｘ受信日時());
                    }

                    try {
                        // 申込徴求資料
                        if (record.getMoushikomiChokyushiryo() != null) {
                            record.getMoushikomiChokyushiryo().setページ番号(MyUtil.convShort(pageNo));
                            moushikomichokyushiryoMapper.insert(record.getMoushikomiChokyushiryo());
                            logger.info("申込徴求資料申込番号={}を登録しました。 ", record.getMoushikomiChokyushiryo().get申込番号());
                        }
                    } catch (Exception e) {
                        logger.error("申込徴求資料:申込番号={}が登録できませんでした。{}", record.getMoushikomiChokyushiryo().get申込番号(),
                                e.getMessage());
                    }

                    // 画像ファイルの移動
                    moveImage(record, FAX_OUT_DIR);
                }
                }
            }
        }
    }

    @Transactional
    public void markDone(移行管理テーブル r) {
        r.setステータス("DONE");
        LocalDateTime now = LocalDateTime.now();
        Timestamp ts = Timestamp.valueOf(now);
        r.set終了日時(ts);
        manageMapper.updateStatusEnd(r);
    }

    @Transactional
    public void markError(移行管理テーブル r) {
        r.setステータス("ERROR");
        LocalDateTime now = LocalDateTime.now();
        Timestamp ts = Timestamp.valueOf(now);
        r.set終了日時(ts);
        manageMapper.updateStatusEnd(r);
    }

    // ── 審査状況取得 ──────────────────────────────────────────────────

    /** 申込審査状況Map */
    private HashMap<Long, VJudgeMain> createJudgemain(int startRow, int endRow) {
        List<VJudgeMain> judgeMainList = vJudgeMainMapper.selectByExample(startRow, endRow);
        HashMap<Long, VJudgeMain> judgeMainMap = new HashMap<Long, VJudgeMain>();
        for (VJudgeMain judgeMainRecord : judgeMainList) {
            judgeMainMap.put(judgeMainRecord.getRequestNo(), judgeMainRecord);
        }
        return judgeMainMap;
    }

    // ── 申込本情報取得 ────────────────────────────────────────────────

    /** 申込基本情報Map */
    private HashMap<Long, VEntryData> createEntryData(int startRow, int endRow) {
        HashMap<Long, VEntryData> entryDataMap = new HashMap<Long, VEntryData>();

        List<VEntryMain> entryMainList = vEntryMainMapper.selectByExample(startRow, endRow);
        List<VEntryLoan> entryLoanList = VEntryLoanMapper.selectByExample(startRow, endRow);
        HashMap<Long, VEntryLoan> entryLoanMap = new HashMap<Long, VEntryLoan>();
        for (VEntryLoan VEntryLoan : entryLoanList) {
            entryLoanMap.put(VEntryLoan.getRequestNo(), VEntryLoan);
        }

        List<VEntryEtc> entryEtcList = vEntryEtcMapper.selectByExample(startRow, endRow);
        HashMap<Long, VEntryEtc> entryEtcMap = new HashMap<Long, VEntryEtc>();
        for (VEntryEtc VEntryEtc : entryEtcList) {
            entryEtcMap.put(VEntryEtc.getRequestNo(), VEntryEtc);
        }

        List<VEntrySub> entrySubLisf = vEntrySubMapper.selectByExample(startRow, endRow);
        HashMap<Long, VEntrySub> entrySubMap = new HashMap<Long, VEntrySub>();
        for (VEntrySub VEntrySub : entrySubLisf) {
            entrySubMap.put(VEntrySub.getRequestNo(), VEntrySub);
        }

        List<VEntryOffice> entryOfficeList = vEntryOfficeMapper.selectByExample(startRow, endRow);
        HashMap<Long, VEntryOffice> entryOfficeMap = new HashMap<Long, VEntryOffice>();
        for (VEntryOffice VEntryOffice : entryOfficeList) {
            entryOfficeMap.put(VEntryOffice.getRequestNo(), VEntryOffice);
        }

        List<VWebdata> webdataList = vWebdataMapper.selectByExample(startRow, endRow);
        HashMap<Long, VWebdata> webdataMap = new HashMap<Long, VWebdata>();
        for (VWebdata webdata : webdataList) {
            webdataMap.put(webdata.getRequestNo(), webdata);
        }

        for (VEntryMain entryMain : entryMainList) {
            VEntryData entryData = new VEntryData();
            entryData.setVEntryMain(entryMain);

            VEntryLoan entryLoan = entryLoanMap.get(entryMain.getRequestNo()) == null ? new VEntryLoan()
                    : entryLoanMap.get(entryMain.getRequestNo());
            entryData.setVEntryLoan(entryLoan);

            VEntryEtc entryEtc = entryEtcMap.get(entryMain.getRequestNo()) == null ? new VEntryEtc()
                    : entryEtcMap.get(entryMain.getRequestNo());
            entryData.setVEntryEtc(entryEtc);

            VEntryOffice entryOffice = entryOfficeMap.get(entryMain.getRequestNo()) == null ? new VEntryOffice()
                    : entryOfficeMap.get(entryMain.getRequestNo());
            entryData.setVEntryOffice(entryOffice);

            VEntrySub entrySub = entrySubMap.get(entryMain.getRequestNo()) == null ? new VEntrySub()
                    : entrySubMap.get(entryMain.getRequestNo());
            entryData.setVEntrySub(entrySub);

            VWebdata vWebdata = webdataMap.get(entryMain.getRequestNo()) == null ? new VWebdata()
                    : webdataMap.get(entryMain.getRequestNo());
            entryData.setVWebdata(vWebdata);

            entryDataMap.put(entryMain.getRequestNo(), entryData);
        }

        return entryDataMap;
    }

    /**
     * 審査結果情報取得
     * @return 審査結果情報Map
     */
    private HashMap<Long, List<VJudgeResult>> createJudgeResult(int startRow, int endRow) {
        HashMap<Long, List<VJudgeResult>> judgeResultMap = new HashMap<Long, List<VJudgeResult>>();
        List<VJudgeResult> judgeResultList = vJudgeResultMapper.selectByExample(startRow, endRow);
        if (judgeResultList != null) {
            for (VJudgeResult judgeResultRecord : judgeResultList) {
                List<VJudgeResult> result = null;
                if (!judgeResultMap.containsKey(judgeResultRecord.getRequestNo())) {
                    result = new ArrayList<VJudgeResult>();
                } else {
                    result = judgeResultMap.get(judgeResultRecord.getRequestNo());
                }
                result.add(judgeResultRecord);
                judgeResultMap.put(judgeResultRecord.getRequestNo(), result);
            }
        }
        return judgeResultMap;
    }

    /**
     * セゾン審査結果情報取得
     *
     * @return セゾン審査結果情報Map
     */
    private HashMap<Long, VSaisonResult> createSaisonResult(int startRow, int endRow) {
        HashMap<Long, VSaisonResult> saisonResultMap = new HashMap<Long, VSaisonResult>();
        List<VSaisonResult> saisonResultList = vSaisonResultMapper.selectByExample(startRow, endRow);
        if (saisonResultList != null) {
            for (VSaisonResult saisonResultRecord : saisonResultList) {
                saisonResultMap.put(saisonResultRecord.getRequestNo(), saisonResultRecord);
            }
        }
        return saisonResultMap;
    }

    /**
     * SDC審査結果情報取得
     *
     * @return SDC審査結果情報Map
     */
    private HashMap<Long, List<VJudgeResult>> createSdcResult(int startRow, int endRow) {
        HashMap<Long, List<VJudgeResult>> sdcResultMap = new HashMap<Long, List<VJudgeResult>>();
        List<VJudgeResult> sdcResultList = vSdcResultMapper.selectByExample(startRow, endRow);
        if (sdcResultList != null) {
            for (VJudgeResult sdcResultRecord : sdcResultList) {
                List<VJudgeResult> list = new ArrayList<VJudgeResult>();
                if (!sdcResultMap.containsKey(sdcResultRecord.getRequestNo())) {
                    list.add(sdcResultRecord);
                } else {
                    list = sdcResultMap.get(sdcResultRecord.getRequestNo());
                    list.add(sdcResultRecord);
                }
                sdcResultMap.put(sdcResultRecord.getRequestNo(), list);
            }
        }
        return sdcResultMap;
    }

    /**
     * 振込先金融機関取得
     *
     * @return 振込先金融機関Map
     */
    private HashMap<Long, List<VBanktrade>> createBankTrade(int startRow, int endRow) {
        HashMap<Long, List<VBanktrade>> banktradeMap = new HashMap<Long, List<VBanktrade>>();
        List<VBanktrade> banktradeList = vBanktradeMapper.selectByExample(startRow, endRow);
        if (banktradeList != null) {
            for (VBanktrade banktradeRecord : banktradeList) {
                List<VBanktrade> list = new ArrayList<VBanktrade>();
                if (!banktradeMap.containsKey(banktradeRecord.getRequestNo())) {
                    list.add(banktradeRecord);
                } else {
                    list = banktradeMap.get(banktradeRecord.getRequestNo());
                    list.add(banktradeRecord);
                }
                banktradeMap.put(banktradeRecord.getRequestNo(), list);
            }
        }
        return banktradeMap;
    }

    /**
     * 審査コメント取得
     *
     * @return 審査コメントMap
     */
    private HashMap<Long, VJudgeGuarant> createJudgeGuarant(int startRow, int endRow) {
        HashMap<Long, VJudgeGuarant> judgeGuarantMap = new HashMap<Long, VJudgeGuarant>();
        List<VJudgeGuarant> judgeGuarantList = vJudgeGuarantMapper.selectByExample(startRow, endRow);
        for (VJudgeGuarant judgeGuarantRecord : judgeGuarantList) {
            judgeGuarantMap.put(judgeGuarantRecord.getRequestNo(), judgeGuarantRecord);
        }
        return judgeGuarantMap;
    }

    // amano
    private Map<String, List<Path>> createMap(List<VImgpr> imgprList) {
        HashSet<String> hashYyyymm = new HashSet<String>();
        Map<String, List<Path>> map = new HashMap<>();
        if (imgprList == null) {
            return map;
        }
        for (VImgpr imgprRecord : imgprList) {
            if (imgprRecord.getReceptionDate() == null
                    || imgprRecord.getReceptionDate().toString().length() < 6) {
                continue;
            }
            String yyyymm = imgprRecord.getReceptionDate().toString().substring(0, 6);
            if (hashYyyymm.contains(yyyymm)) {
                continue;
            }
            hashYyyymm.add(yyyymm);

            File folder = new File(IMG_BASE_DIR + yyyymm);
            if (folder != null && folder.listFiles() != null) {
                // サブフォルダ内を捜査
                for (File file : folder.listFiles()) {
                    // 202510\20251031_000001_page1.jpg
                    // → key:202510\20251031_000001
                    String[] parts = file.getName().split("_");
                    String key = String.format("%s/%s_%s", yyyymm, parts[0], parts[1]);
                    if (!map.containsKey(key)) {
                        // キーが存在しない場合、map内に空Listを作成
                        map.put(key, new ArrayList<Path>());
                    }
                    map.get(key).add(file.toPath());
                }
            }
        }
        return map;
    }

    /**
     * イメージ未処理
     *
     * @return イメージ未処理Map
     */
    private HashMap<Long, VImgpr> createImgpr(int startRow, int endRow) {
        HashMap<Long, VImgpr> imageprMap = new HashMap<Long, VImgpr>();
        if ("1".equals(kind)) {
            List<VImgpr> imgprList = vImgprMapper.selectByExample(startRow, endRow);
            Map<String, List<Path>> fileMap = createMap(imgprList);
            if (imgprList != null) {
                for (VImgpr imgprRecord : imgprList) {
                    if (imgprRecord.getReceptionDate() == null
                            || imgprRecord.getReceptionDate().toString().length() < 6) {
                        continue;
                    }
                    String yyyymm = imgprRecord.getReceptionDate().toString().substring(0, 6);
                    String key = String.format("%s/%s_%s", yyyymm, imgprRecord.getReceptionDate().toString(),
                            imgprRecord.getReceptionNo().toString());
                    if (!fileMap.containsKey(key)) {
                        System.out.println("指定されたファイルが存在しません: " + key);
                        continue;
                    }
                    imgprRecord.setImageList(fileMap.get(key));
                    imageprMap.put(imgprRecord.getRequestNo(), imgprRecord);
                }
            }
        }
        return imageprMap;
    }

    /**
     * fax送信画像
     *
     * @return fax送信画像Map
     */
    private HashMap<Long, VSendfaxExt> createSendFax(int startRow, int endRow) {
        HashMap<Long, VSendfaxExt> imgFaxMap = new HashMap<Long, VSendfaxExt>();
        if ("1".equals(kind)) {
            List<VSendfaxExt> sendFaxExtList = vSendfaxExtMapper.selectByExample(startRow, endRow);
            if (sendFaxExtList != null) {
                for (VSendfaxExt sendFaxExtRecord : sendFaxExtList) {
                    String yyyymm = MyUtil.convString(sendFaxExtRecord.getRequestNo()).substring(0, 6);
                    List<Path> matchedPaths = new ArrayList<Path>();
                    File folder = new File(FAX_BASE_DIR + yyyymm);
                    Pattern pattern = Pattern
                            .compile(sendFaxExtRecord.getRequestNo().toString() + "_page\\d+\\.jpg");
                    if (!folder.exists() || !folder.isDirectory()) {
                        System.out.println("指定されたフォルダが存在しません: " + folder.getAbsolutePath());
                        continue;
                    }
                    File[] files = folder.listFiles();
                    for (File file : files) {
                        if (pattern.matcher(file.getName()).matches()) {
                            matchedPaths.add(file.toPath());
                        }
                    }
                    sendFaxExtRecord.setImageList(matchedPaths);
                    imgFaxMap.put(sendFaxExtRecord.getRequestNo(), sendFaxExtRecord);
                }
            }
        }
        return imgFaxMap;
    }

    /**
     * 申込審査状況へ変換する
     *
     * @param vJudgeMain            審査状況
     * @param requestNo             登録する申込書番号
     * @param moushikomiMokuteki    登録する申込目的
     * @return
     */
    private 申込審査状況 convMoushikomiShinsajyoukyou(VJudgeMain vJudgeMain, String requestNo,
            String moushikomiMokuteki) {
        logger.debug("convMoushikomiShinsajyoukyou start");

        if (vJudgeMain == null) {
            return null;
        }

        申込審査状況 shinsajokyo = new 申込審査状況();

        shinsajokyo.set作成日時(MyUtil.convDate(vJudgeMain.getCreateDate(), vJudgeMain.getCreateTime()));
        shinsajokyo.set更新日時(MyUtil.convDate(vJudgeMain.getUpdateDate(), vJudgeMain.getUpdateTime()));
        shinsajokyo.set申込番号(requestNo);
        shinsajokyo.set申込目的(moushikomiMokuteki);
        shinsajokyo.set回数((short) 1);

        logger.debug("convMoushikomiShinsajyoukyou end");

        return shinsajokyo;
    }

    private 申込進捗 convMoushikomiShinchoku(VJudgeMain judgeMainRecord, String requestNo, Object shinchokuSyohin) {
        // TODO: implement
        return null;
    }

    private 申込 convMoushikomi(VEntryData entryMainRecord, List<VBanktrade> bankTradeList, String requestNo,
            String moushikomiMokuteki, MGood mGood, 商品マスター shohinMaster, VJudgeMain judgeMainRecord) {

        VEntryData ventryData = entryMainRecord;
        MGood mGoods = mGood;

        申込 moushikomi = new 申込();
        
        String hokensyo = null;
        // E健康保険証種類
        try {
            E健康保険証種類 eHokensyo = E健康保険証種類.fromOldCode(ventryData.getVWebdata().getHealthInsCd());
            hokensyo = eHokensyo.getNewCode();
        } catch (IllegalArgumentException e) {
            logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E健康保険証種類",
                    ventryData.getVWebdata().getHealthInsCd());
        }

        String yakusyoku = null;
        String gyosyu = null;
        String jukyokeitai = null;
        String jyugyoin = null;
        String syokugyo = null;
        String shikinshito = null;
        String shikinshitoEtc = null;

        if (E商品性区分.通常申込書.getOldCode().equals(MyUtil.convString(mGoods.getCondition03Ck()))) {
            // 役職0
            if (ventryData.getVEntryOffice().getOfficePostCd() != null) {
                try {
                    E役職0__通常申込書 eYakusoku = E役職0__通常申込書.fromOldCode(ventryData.getVEntryOffice().getOfficePostCd());
                    yakusyoku = eYakusoku.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E役職0__通常申込書",
                            ventryData.getVEntryOffice().getOfficePostCd());
                }
            }
            // 業種0
            if (ventryData.getVEntryOffice().getIndustryCd() != null) {
                try {
                    E業種0__通常申込書 eGyosyu = E業種0__通常申込書.fromOldCode(ventryData.getVEntryOffice().getIndustryCd());
                    gyosyu = eGyosyu.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E業種0__通常申込書",
                            ventryData.getVEntryOffice().getIndustryCd());
                }
            }
            // E住居形態0
            if (ventryData.getVEntryMain().getHouseFormCd() != null) {
                try {
                    if ("7".equals(shohinMaster.get商品大分類())) {
                        E住居形態0__通常申込書_BQL eJukyokeitai = E住居形態0__通常申込書_BQL.fromOldCode(ventryData.getVEntryMain().getHouseFormCd());
                        jukyokeitai = eJukyokeitai.getNewCode();
                    } else {
                        E住居形態0__通常申込書 eJukyokeitai = E住居形態0__通常申込書.fromOldCode(ventryData.getVEntryMain().getHouseFormCd());
                        jukyokeitai = eJukyokeitai.getNewCode();
                    }
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E住居形態0__通常申込書",
                            ventryData.getVEntryMain().getHouseFormCd());
                }
            }
            // 従業員数0
            if (ventryData.getVEntryOffice().getEmployeeCd() != null) {
                try {
                    E従業員数0__通常申込書 eJyugyoin = E従業員数0__通常申込書.fromOldCode(ventryData.getVEntryOffice().getEmployeeCd());
                    jyugyoin = eJyugyoin.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E従業員数0__通常申込書",
                            ventryData.getVEntryOffice().getEmployeeCd());
                }
            }
            // 職業0
            if (ventryData.getVEntryOffice().getOccupationCd() != null) {
                try {
                    E勤務先職業0__通常申込書 eJyukyo = E勤務先職業0__通常申込書.fromOldCode(ventryData.getVEntryOffice().getOccupationCd());
                    syokugyo = eJyukyo.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E勤務先職業0__通常申込書",
                            ventryData.getVEntryOffice().getOccupationCd());
                }
            }
        } else if (E商品性区分.セレカ申込書.getOldCode().equals(MyUtil.convString(mGoods.getCondition03Ck()))) {
            // 役職1
            if (ventryData.getVEntryOffice().getOfficePostCd() != null) {
                try {
                    E役職1__セレカ申込書 eYakusoku = E役職1__セレカ申込書.fromOldCode(ventryData.getVEntryOffice().getOfficePostCd());
                    yakusyoku = eYakusoku.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E役職1__セレカ申込書",
                            ventryData.getVEntryOffice().getOfficePostCd());
                }
            }
            // 業種1
            if (ventryData.getVEntryOffice().getIndustryCd() != null) {
                try {
                    E業種1__セレカ申込書 eGyosyu = E業種1__セレカ申込書.fromOldCode(ventryData.getVEntryOffice().getIndustryCd());
                    gyosyu = eGyosyu.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E業種1__セレカ申込書",
                            ventryData.getVEntryOffice().getIndustryCd());
                }
            }
            // E住居形態1
            if (ventryData.getVEntryMain().getHouseFormCd() != null) {
                try {
                    E住居形態1__セレカ申込書 eJyukyo = E住居形態1__セレカ申込書.fromOldCode(ventryData.getVEntryMain().getHouseFormCd());
                    jukyokeitai = eJyukyo.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E住居形態1__セレカ申込書",
                            ventryData.getVEntryMain().getHouseFormCd());
                }
            }
            // 従業員数1
            if (ventryData.getVEntryOffice().getEmployeeCd() != null) {
                try {
                    E従業員数1__セレカ申込書 eJyugyoin = E従業員数1__セレカ申込書.fromOldCode(ventryData.getVEntryOffice().getEmployeeCd());
                    jyugyoin = eJyugyoin.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E従業員数1__セレカ申込書",
                            ventryData.getVEntryOffice().getEmployeeCd());
                }
            }
            // 職業1
            if (ventryData.getVEntryOffice().getOccupationCd() != null) {
                if("6".equals(shohinMaster.get商品大分類())) {
                    try {
                        E勤務先職業0__通常申込書 eJyukyo = E勤務先職業0__通常申込書.fromOldCode(ventryData.getVEntryOffice().getOccupationCd());
                        syokugyo = eJyukyo.getNewCode();
                    } catch (IllegalArgumentException e) {
                        logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E勤務先職業0__通常申込書",
                                ventryData.getVEntryOffice().getOccupationCd());
                    }
                }else {
                    try {
                        E勤務先職業1__セレカ申込書 eJyukyo = E勤務先職業1__セレカ申込書.fromOldCode(ventryData.getVEntryOffice().getOccupationCd());
                        syokugyo = eJyukyo.getNewCode();
                    } catch (IllegalArgumentException e) {
                        logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E勤務先職業1__セレカ申込書",
                                ventryData.getVEntryOffice().getOccupationCd());
                    }
                }
            }
        } else if (E商品性区分.オリックス申込書.getOldCode().equals(MyUtil.convString(mGoods.getCondition03Ck()))) {
            if(ventryData.getVEntryOffice().getOfficePostCd() != null) {
                // 役職0
                try {
                    E役職2__オリックス申込書 eYakusoku = E役職2__オリックス申込書.fromOldCode(ventryData.getVEntryOffice().getOfficePostCd());
                    yakusyoku = eYakusoku.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E役職2__オリックス申込書",
                            ventryData.getVEntryOffice().getOfficePostCd());
                }
            }
            if(ventryData.getVEntryOffice().getIndustryCd() != null) {
                try {
                    E業種2__オリックス申込書 eGyosyu = E業種2__オリックス申込書.fromOldCode(ventryData.getVEntryOffice().getIndustryCd());
                    gyosyu = eGyosyu.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E業種2__オリックス申込書",
                            ventryData.getVEntryOffice().getIndustryCd());
                }
            }
            if(ventryData.getVEntryMain().getHouseFormCd() != null) {
                // E住居形態0
                try {
                    E住居形態2__オリックス申込書 eJyukyo = E住居形態2__オリックス申込書.fromOldCode(ventryData.getVEntryMain().getHouseFormCd());
                    jukyokeitai = eJyukyo.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E住居形態2__オリックス申込書",
                            ventryData.getVEntryMain().getHouseFormCd());
                }
            }
            if(ventryData.getVEntryOffice().getEmployeeCd() != null) {
                // 従業員数0
                try {
                    E従業員数2__オリックス申込書 eJyugyoin = E従業員数2__オリックス申込書.fromOldCode(ventryData.getVEntryOffice().getEmployeeCd());
                    jyugyoin = eJyugyoin.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E従業員数2__オリックス申込書",
                            ventryData.getVEntryOffice().getEmployeeCd());
                }
            }
            if(ventryData.getVEntryOffice().getOccupationCd() != null) {
                // 職業0
                try {
                    E勤務先職業2__オリックス申込書 eJyukyo = E勤務先職業2__オリックス申込書.fromOldCode(ventryData.getVEntryOffice().getOccupationCd());
                    syokugyo = eJyukyo.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E勤務先職業2__オリックス申込書",
                            ventryData.getVEntryOffice().getOccupationCd());
                }
            }
        } else if (E商品性区分.ジャックス申込書.getOldCode().equals(MyUtil.convString(mGoods.getCondition03Ck()))) {
            if(ventryData.getVEntryOffice().getOfficePostCd() != null) {
                // 役職0
                try {
                    E役職3__ジャックス申込書 eYakusoku = E役職3__ジャックス申込書.fromOldCode(ventryData.getVEntryOffice().getOfficePostCd());
                    yakusyoku = eYakusoku.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E役職3__ジャックス申込書",
                            ventryData.getVEntryOffice().getOfficePostCd());
                }
            }
            if(ventryData.getVEntryOffice().getIndustryCd() != null) {
                // 業種0
                try {
                    E業種3__ジャックス申込書 eGyosyu = E業種3__ジャックス申込書.fromOldCode(ventryData.getVEntryOffice().getIndustryCd());
                    gyosyu = eGyosyu.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E業種3__ジャックス申込書",
                            ventryData.getVEntryOffice().getIndustryCd());
                }
            }
            if(ventryData.getVEntryMain().getHouseFormCd() != null) {
                // E住居形態0
                try {
                    E住居形態3__ジャックス申込書 eJyukyo = E住居形態3__ジャックス申込書.fromOldCode(ventryData.getVEntryMain().getHouseFormCd());
                    jukyokeitai = eJyukyo.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E住居形態3__ジャックス申込書",
                            ventryData.getVEntryMain().getHouseFormCd());
                }
            }
            if(ventryData.getVEntryOffice().getEmployeeCd() != null) {
                // 従業員数0
                try {
                    E従業員数3__ジャックス申込書 eJyugyoin = E従業員数3__ジャックス申込書.fromOldCode(ventryData.getVEntryOffice().getEmployeeCd());
                    jyugyoin = eJyugyoin.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E従業員数3__ジャックス申込書",
                            ventryData.getVEntryOffice().getEmployeeCd());
                }
            }
            if(ventryData.getVEntryOffice().getOccupationCd() != null) {
                // 職業0
                try {
                    E勤務先職業3__ジャックス申込書 eJyukyo = E勤務先職業3__ジャックス申込書.fromOldCode(ventryData.getVEntryOffice().getOccupationCd());
                    syokugyo = eJyukyo.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E勤務先職業3__ジャックス申込書",
                            ventryData.getVEntryOffice().getOccupationCd());
                }
            }
        } else if (E商品性区分.集中実行申込書.getOldCode().equals(MyUtil.convString(mGoods.getCondition03Ck()))) {
            if(ventryData.getVEntryOffice().getOfficePostCd() != null) {
                // 役職0
                try {
                    E役職4__集中実行申込書 eYakusoku = E役職4__集中実行申込書.fromOldCode(ventryData.getVEntryOffice().getOfficePostCd());
                    yakusyoku = eYakusoku.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E役職4__集中実行申込書",
                            ventryData.getVEntryOffice().getOfficePostCd());
                }
            }
            if(ventryData.getVEntryOffice().getIndustryCd() != null) {
                // 業種0
                try {
                    E業種4__集中実行申込書 eGyosyu = E業種4__集中実行申込書.fromOldCode(ventryData.getVEntryOffice().getIndustryCd());
                    gyosyu = eGyosyu.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E業種4__集中実行申込書",
                            ventryData.getVEntryOffice().getIndustryCd());
                }
            }
            if(ventryData.getVEntryMain().getHouseFormCd() != null) {
                // E住居形態0
                try {
                    E住居形態4__集中実行申込書 eJyukyo = E住居形態4__集中実行申込書.fromOldCode(ventryData.getVEntryMain().getHouseFormCd());
                    jukyokeitai = eJyukyo.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E住居形態4__集中実行申込書",
                            ventryData.getVEntryMain().getHouseFormCd());
                }
            }
            if(ventryData.getVEntryOffice().getEmployeeCd() != null) {
                // 従業員数0
                try {
                    E従業員数4__集中実行申込書 eJyugyoin = E従業員数4__集中実行申込書.fromOldCode(ventryData.getVEntryOffice().getEmployeeCd());
                    jyugyoin = eJyugyoin.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E従業員数4__集中実行申込書",
                            ventryData.getVEntryOffice().getEmployeeCd());
                }
            }
            if(ventryData.getVEntryOffice().getOccupationCd() != null) {
                // 職業0
                try {
                    E勤務先職業4__集中実行申込書 eJyukyo = E勤務先職業4__集中実行申込書.fromOldCode(ventryData.getVEntryOffice().getOccupationCd());
                    syokugyo = eJyukyo.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E勤務先職業4__集中実行申込書",
                            ventryData.getVEntryOffice().getOccupationCd());
                }
            }
        } else if (E商品性区分.ＳＤＣ申込書.getOldCode().equals(MyUtil.convString(mGoods.getCondition03Ck()))) {
            if(ventryData.getVEntryOffice().getOfficePostCd() != null) {
                // 役職0
                try {
                    E役職5__ＳＤＣ申込書 eYakusoku = E役職5__ＳＤＣ申込書.fromOldCode(ventryData.getVEntryOffice().getOfficePostCd());
                    yakusyoku = eYakusoku.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E役職5__ＳＤＣ申込書",
                            ventryData.getVEntryOffice().getOfficePostCd());
                }
            }
            if(ventryData.getVEntryOffice().getIndustryCd() != null) {
                // 業種0
                try {
                    E業種5__SDC申込書 eGyosyu = E業種5__SDC申込書.fromOldCode(ventryData.getVEntryOffice().getIndustryCd());
                    gyosyu = eGyosyu.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E業種5__SDC申込書",
                            ventryData.getVEntryOffice().getIndustryCd());
                }
            }
            if(ventryData.getVEntryMain().getHouseFormCd() != null) {
                // E住居形態0
                try {
                    E住居形態5__SDC申込書 eJyukyo = E住居形態5__SDC申込書.fromOldCode(ventryData.getVEntryMain().getHouseFormCd());
                    jukyokeitai = eJyukyo.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E住居形態5__SDC申込書",
                            ventryData.getVEntryMain().getHouseFormCd());
                }
            }
            if(ventryData.getVEntryOffice().getEmployeeCd() != null) {
                // 従業員数0
                try {
                    E従業員数5__SDC申込書 eJyugyoin = E従業員数5__SDC申込書.fromOldCode(ventryData.getVEntryOffice().getEmployeeCd());
                    jyugyoin = eJyugyoin.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E従業員数5__SDC申込書",
                            ventryData.getVEntryOffice().getEmployeeCd());
                }
            }
            if(ventryData.getVEntryOffice().getOccupationCd() != null) {
                // 職業0
                try {
                    E勤務先職業5__SDC申込書 eJyukyo = E勤務先職業5__SDC申込書.fromOldCode(ventryData.getVEntryOffice().getOccupationCd());
                    syokugyo = eJyukyo.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E勤務先職業5__SDC申込書",
                            ventryData.getVEntryOffice().getOccupationCd());
                }
            }
        } else if (E商品性区分.セレカミニ.getOldCode().equals(MyUtil.convString(mGoods.getCondition03Ck()))) {
            if(ventryData.getVEntryOffice().getOfficePostCd() != null) {
                // 役職0
                try {
                    E役職6__セレカミニ eYakusoku = E役職6__セレカミニ.fromOldCode(ventryData.getVEntryOffice().getOfficePostCd());
                    yakusyoku = eYakusoku.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E役職6__セレカミニ",
                            ventryData.getVEntryOffice().getOfficePostCd());
                }
            }
            if(ventryData.getVEntryOffice().getIndustryCd() != null) {
                // 業種0
                try {
                    E業種6__セレカミニ eGyosyu = E業種6__セレカミニ.fromOldCode(ventryData.getVEntryOffice().getIndustryCd());
                    gyosyu = eGyosyu.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E業種6__セレカミニ",
                            ventryData.getVEntryOffice().getIndustryCd());
                }
            }
            if(ventryData.getVEntryMain().getHouseFormCd() != null) {
                // E住居形態0
                try {
                    E住居形態6__セレカミニ eJyukyo = E住居形態6__セレカミニ.fromOldCode(ventryData.getVEntryMain().getHouseFormCd());
                    jukyokeitai = eJyukyo.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E住居形態6__セレカミニ",
                            ventryData.getVEntryMain().getHouseFormCd());
                }
            }
            if(ventryData.getVEntryOffice().getEmployeeCd() != null) {
                // 従業員数0
                try {
                    E従業員数6__セレカミニ eJyugyoin = E従業員数6__セレカミニ.fromOldCode(ventryData.getVEntryOffice().getEmployeeCd());
                    jyugyoin = eJyugyoin.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E従業員数6__セレカミニ",
                            ventryData.getVEntryOffice().getEmployeeCd());
                }
            }
            if(ventryData.getVEntryOffice().getOccupationCd() != null) {
                // 職業0
                try {
                    E勤務先職業6__セレカミニ eJyukyo = E勤務先職業6__セレカミニ.fromOldCode(ventryData.getVEntryOffice().getOccupationCd());
                    syokugyo = eJyukyo.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E勤務先職業6__セレカミニ",
                            ventryData.getVEntryOffice().getOccupationCd());
                }
            }
        }

        // 資金使途
        try {
            //商品毎の「その他」を選択するように
            E資金使途 eShikinshito = E資金使途.fromOldCode(shohinMaster.get商品分類());
            shikinshito = eShikinshito.getNewCode();
        } catch (IllegalArgumentException e) {
            logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E資金使途6__セレカミニ",
                    ventryData.getVEntryLoan().getSpentCd());
        }

        String oldGoodName = "";
        if (E商品性区分.通常申込書.getOldCode().equals(MyUtil.convString(mGoods.getCondition03Ck()))) {
            if (MyUtil.isNotBlank(ventryData.getVEntryLoan().getSpentCd())) {
                try {
                    E資金使途0__通常申込書 eGoodShikinshito = E資金使途0__通常申込書.fromOldCode(ventryData.getVEntryLoan().getSpentCd());
                    oldGoodName = eGoodShikinshito.getNewName();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E資金使途0__通常申込書",
                            ventryData.getVEntryOffice().getOccupationCd());
                }
            }
        } else if (E商品性区分.セレカ申込書.getOldCode().equals(MyUtil.convString(mGoods.getCondition03Ck()))) {
            if (MyUtil.isNotBlank(ventryData.getVEntryLoan().getSpentCd())) {
                try {
                    E資金使途1__セレカ申込書 eGoodShikinshito = E資金使途1__セレカ申込書.fromOldCode(ventryData.getVEntryLoan().getSpentCd());
                    oldGoodName = eGoodShikinshito.getNewName();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E資金使途1__セレカ申込書",
                            ventryData.getVEntryLoan().getSpentCd());
                }
            }
        } else if (E商品性区分.オリックス申込書.getOldCode().equals(MyUtil.convString(mGoods.getCondition03Ck()))) {
            if (MyUtil.isNotBlank(ventryData.getVEntryLoan().getSpentCd())) {
                try {
                    E資金使途2__オリックス申込書 eGoodShikinshito = E資金使途2__オリックス申込書.fromOldCode(ventryData.getVEntryLoan().getSpentCd());
                    oldGoodName = eGoodShikinshito.getNewName();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E資金使途2__オリックス申込書",
                            ventryData.getVEntryLoan().getSpentCd());
                }
            }
        } else if (E商品性区分.ジャックス申込書.getOldCode().equals(MyUtil.convString(mGoods.getCondition03Ck()))) {
            if (MyUtil.isNotBlank(ventryData.getVEntryLoan().getSpentCd())) {
                try {
                    E資金使途3__ジャックス申込書 eGoodShikinshito = E資金使途3__ジャックス申込書.fromOldCode(ventryData.getVEntryLoan().getSpentCd());
                    oldGoodName = eGoodShikinshito.getNewName();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E資金使途3__ジャックス申込書",
                            ventryData.getVEntryLoan().getSpentCd());
                }
            }
        } else if (E商品性区分.集中実行申込書.getOldCode().equals(MyUtil.convString(mGoods.getCondition03Ck()))) {
            if (MyUtil.isNotBlank(ventryData.getVEntryLoan().getSpentCd())) {
                try {
                    E資金使途4__集中実行申込書 eGoodShikinshito = E資金使途4__集中実行申込書.fromOldCode(ventryData.getVEntryLoan().getSpentCd());
                    oldGoodName = eGoodShikinshito.getNewName();
                } catch (IllegalArgumentException e) {
                    logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E資金使途4__集中実行申込書",
                            ventryData.getVEntryLoan().getSpentCd());
                }
            }
        } else if (E商品性区分.ＳＤＣ申込書.getOldCode().equals(MyUtil.convString(mGoods.getCondition03Ck()))) {
            if (MyUtil.isNotBlank(ventryData.getVEntryLoan().getSpentCd())) {
                try {
                    E資金使途5__ＳＤＣ申込書 eGoodShikinshito = E資金使途5__ＳＤＣ申込書.fromOldCode(ventryData.getVEntryLoan().getSpentCd());
                    oldGoodName = eGoodShikinshito.getNewName();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E資金使途5__ＳＤＣ申込書",
                            ventryData.getVEntryLoan().getSpentCd());
                }
            }
        } else if (E商品性区分.セレカミニ.getOldCode().equals(MyUtil.convString(mGoods.getCondition03Ck()))) {
            if (MyUtil.isNotBlank(ventryData.getVEntryLoan().getSpentCd())) {
                try {
                    E資金使途6__セレカミニ eGoodShikinshito = E資金使途6__セレカミニ.fromOldCode(ventryData.getVEntryLoan().getSpentCd());
                    oldGoodName = eGoodShikinshito.getNewName();
                } catch (IllegalArgumentException e) {
                    logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E資金使途6__セレカミニ",
                            ventryData.getVEntryLoan().getSpentCd());
                }
            }
        }

        if (MyUtil.isNotBlank(oldGoodName)) {
            if (MyUtil.isNotBlank(ventryData.getVEntryLoan().getSpentEtc())) {
                shikinshitoEtc = oldGoodName + "(" + ventryData.getVEntryLoan().getSpentEtc() + ")";
            } else {
                shikinshitoEtc = oldGoodName;
            }
        } else {
            if (MyUtil.isNotBlank(ventryData.getVEntryLoan().getSpentEtc())) {
                shikinshitoEtc = ventryData.getVEntryLoan().getSpentEtc();
            }
        }

        String shihonkin = null;
        if (ventryData.getVEntryOffice().getCapitalCd() != null) {
            try {
                E勤務先資本金 eKinmusakishihonkinKubun = E勤務先資本金.fromOldCode(ventryData.getVEntryOffice().getCapitalCd());
                shihonkin = eKinmusakishihonkinKubun.getNewCode();
            } catch (IllegalArgumentException e) {
                logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E勤務先資本金",
                        ventryData.getVEntryOffice().getCapitalCd());
            }
        }

        String hudosan = null;
        if (ventryData.getVEntryOffice().getIncomeEstate() != null) {
            try {
                E不動産収入 eHudosan = E不動産収入.fromOldCode(ventryData.getVEntryOffice().getIncomeEstate());
                hudosan = eHudosan.getNewCode();
            } catch (IllegalArgumentException e) {
                logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E不動産収入",
                        ventryData.getVEntryOffice().getIncomeEstate());
            }
        }

        String jigyou = null;
        if (ventryData.getVEntryOffice().getIncomeBusiness() != null) {
            try {
                E事業収入 eJigyou = E事業収入.fromOldCode(ventryData.getVEntryOffice().getIncomeBusiness());
                jigyou = eJigyou.getNewCode();
            } catch (IllegalArgumentException e) {
                logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E事業収入",
                        ventryData.getVEntryOffice().getIncomeBusiness());
            }
        }

        String doui = null;
        if (ventryData.getVEntrySub().getRevVarc04() != null) {
            try {
                E自動車保険提案の同意 eDoui = E自動車保険提案の同意.fromOldCode(ventryData.getVEntrySub().getRevVarc04());
                doui = eDoui.getNewCode();
            } catch (IllegalArgumentException e) {
                logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, " E自動車保険提案の同意",
                        ventryData.getVEntrySub().getRevVarc04());
            }
        }

        String renrakusaki = null;
        if (ventryData.getVEntryEtc().getContactId() != null) {
            try {
                E希望連絡先 eRenrakusaki = E希望連絡先.fromOldCode(ventryData.getVEntryEtc().getContactId());
                renrakusaki = eRenrakusaki.getNewCode();
            } catch (IllegalArgumentException e) {
                logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E希望連絡先", ventryData.getVEntryEtc().getContactId());
            }
        }

        String seimeihoken = null;
        if(ventryData.getVWebdata().getGroupCreditLifeInsurance() != null) {
            try {
                E団体信用生命保険加入 eSeimeihoken = E団体信用生命保険加入.fromOldCode(ventryData.getVWebdata().getGroupCreditLifeInsurance());
                seimeihoken = eSeimeihoken.getNewCode();
            } catch (IllegalArgumentException e) {
                logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E団体信用生命保険加入",
                        ventryData.getVWebdata().getGroupCreditLifeInsurance());
            }
        }

        String peps = null;
        if(ventryData.getVWebdata().getForeignPeps() != null) {
            try {
                E外国PEPs ePeps = E外国PEPs.fromOldCode(ventryData.getVWebdata().getForeignPeps());
                peps = ePeps.getNewCode();
            } catch (IllegalArgumentException e) {
                logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E外国PEPs", ventryData.getVWebdata().getForeignPeps());
            }
        }

        String kouken = null;
        if(ventryData.getVWebdata().getGuardSysAn() != null) {
            try {
                E成年後見制度の利用 eKouken = E成年後見制度の利用.fromOldCode(ventryData.getVWebdata().getGuardSysAn());
                kouken = eKouken.getNewCode();
            } catch (IllegalArgumentException e) {
                logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E成年後見制度の利用",
                        ventryData.getVWebdata().getGuardSysAn());
            }
        }

        String hensaikouzaKamoku = null;
        if (ventryData.getVEntryLoan().getPayAccountCd() != null) {
            try {
                E返済用口座科目 eHensaikouzaKamoku = E返済用口座科目.fromOldCode(ventryData.getVEntryLoan().getPayAccountCd());
                hensaikouzaKamoku = eHensaikouzaKamoku.getNewCode();
            } catch (IllegalArgumentException e) {
                logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E返済用口座科目",
                        ventryData.getVEntryLoan().getPayAccountCd());
            }
        }

        String hosyokaisyaCd = null;
        String oldHosyokaisyaMei = null;
        if (mGoods.getHsCode() != null) {
            try {
                String hsCode = String.format("%04d", Integer.valueOf(mGoods.getHsCode()));
                E保証会社統合 eHosyokaisyaCd = E保証会社統合.fromOldCode(hsCode);
                hosyokaisyaCd = eHosyokaisyaCd.getNewCode();
                oldHosyokaisyaMei = eHosyokaisyaCd.getOldName();
            } catch (IllegalArgumentException e) {
                if (mGoods.getHsCode() != null && eHosyokaisyaCd == null) {
                    eHosyokaisyaCd = E保証会社統合.保証会社統合.旧保証会社統合;
                }
                logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E保証会社統合", 
                        mGoods.getHsCode());
            }
        }

        String haigusvaCd = null;
        if (ventryData.getVEntryMain().getLodgerIdCd() != null) {
            try {
                E配偶者有無 eHaigusva = E配偶者有無.fromOldCode(ventryData.getVEntryMain().getLodgerIdCd());
                haigusvaCd = eHaigusva.getNewCode();
            } catch (IllegalArgumentException e) {
                logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E配偶者有無",
                        ventryData.getVEntryMain().getLodgerIdCd());
            }
        }

        Short bounas = ventryData.getVEntryLoan().getRepayBonus1();
        if (MyUtil.isBlank(bounas)) {
            moushikomi.setボーナス返済有無("1");
        } else {
            moushikomi.setボーナス返済有無("2");
        }

        Integer jutakuloan = ventryData.getVWebdata().getHouseRentMonth();
        if (MyUtil.isBlank(jutakuloan)) {
            moushikomi.set家賃_住宅ローンの有無("0");
        } else {
            moushikomi.set家賃_住宅ローンの有無("1");
        }

        String campaign = ventryData.getVEntrySub().getRevVarc1();
        if (MyUtil.isBlank(campaign)) {
            moushikomi.setキャンペーンコード有無("2");
        } else {
            moushikomi.setキャンペーンコード有無("1");
        }

        // 扶養家族
        String fuyo = null;
        Short deepNum = ventryData.getVEntryMain().getDependNum() != null ? ventryData.getVEntryMain().getDependNum() : 0;
        if (ventryData.getVEntryMain().getDependNum() != null) {
            if (deepNum > 9) {
                fuyo = "9";
            } else {
                fuyo = MyUtil.convString(deepNum.intValue());
            }
        }

        // 同居家族
        String doukyo = null;
        Short doukyoNum = ventryData.getVWebdata().getLodgerNum() != null ? ventryData.getVWebdata().getLodgerNum() : 0;
        if (ventryData.getVWebdata().getLodgerNum() != null) {
            if (doukyoNum > 9) {
                doukyo = "9";
            } else {
                doukyo = MyUtil.convString(doukyoNum.intValue());
            }
        }

        // /03ckが"0"の場合大分類7商品
        String hiagusyaDokyo = null;
        if ((E商品性区分.通常申込書.getOldCode().equals(MyUtil.convString(mGoods.getCondition03Ck()))
                && "7".equals(shohinMaster.get商品大分類())) {

//			//BQL子供以外・非BQL出で出せないので空設定。不要人数は不要入数では存在しない（lodger_id2子供人数は不要人数が正式）
//			if (E配偶者有無.持参.getNewCode().equals(haigusvaCd)) {
//				if (deepNum > 0) {
//					//性能・子供有
//					hiagusyaDokyo ="3";
//				} else {
//					//既婚・親と同居
//					hiagusyaDokyo ="1";
//				}
//			} else {
//				//未既婚・親と同居
//				hiagusyaDokyo ="2";
//			}
//		} else {

            //BQL以外
            // 配偶者同居
            if (E配偶者有無.持参.getNewCode().equals(haigusvaCd)) {
                if (doukyoNum > 0) {
                    //性能・親と同居
                    hiagusyaDokyo ="1";
                } else {
                    //性能・親と別居
                    hiagusyaDokyo ="2";
                }
            } else {
                if (doukyoNum > 0) {
                    //未既婚・親と同居
                    hiagusyaDokyo ="3";
                } else {
                    //未既婚・親と別居
                    hiagusyaDokyo ="4";
                }
            }
        }
        // 世帯収入
        Long setaisyunyu = null;
        if (!MyUtil.isBlank(ventryData.getVWebdata().getIncome1yAdd())) {
            setaisyunyu = ventryData.getVWebdata().getIncome1yAdd() * 10000;
        }

        Long haigusvaSyunyu = null;
        if (!MyUtil.isBlank(ventryData.getVWebdata().getAnnualIncome1yAdd())) {
            haigusvaSyunyu = ventryData.getVWebdata().getAnnualIncome1yAdd() * 10000;
        }

        moushikomi.set成約日時(MyUtil.convDate(ventryData.getVEntryMain().getCreateDate(),
                ventryData.getVEntryMain().getCreateTime()));
        moushikomi.set更新日時(MyUtil.convDate(ventryData.getVEntryMain().getUpdateDate(),
                ventryData.getVEntryMain().getUpdateTime()));
        moushikomi.set申込番号(requestNo);
        moushikomi.set申込目的(moushikomiMokuteki);
        moushikomi.set商品大分類(shohinMaster.get商品大分類());
        moushikomi.set商品分類(shohinMaster.get商品分類());
        moushikomi.set商品コード(shohinMaster.get商品コード());
        moushikomi.set取次業績縦(torisugikеii);

        moushikomi.set受付店番(ventryData.getVEntryMain().getStoreNoManage());
        String kanjyotenban  = null;
        if (ventryData.getVEntryMain().getStoreNoManage() != null) {
            kanjyotenban  = String.format("%04d", ventryData.getVEntryMain().getStoreNoManage());
        }
        moushikomi.set勘定店番(kanjyotenban);
        moushikomi.set店舗(miseban);

        //moushikomi.set仮CI T番号(ventryData.getVEntryMain().getCustomerCd());
        moushikomi.setCIT番号(ventryData.getVEntryMain().getCustomerCd());
        moushikomi.set｢｢｣｣氏名（MyUtil.concatName(ventryData.getVEntryMain().getNameKFamily(),ventryData.getVEntryMain().getNameKFirst()), 60));
        moushikomi.set｢｢｣｣氏名カナ（MyUtil.substringByByte(ventryData.getVEntryMain().getNameKFamily(), 30));
        moushikomi.set｢｢｣｣氏名力ナ（MyUtil.substringByByte(ventryData.getVEntryMain().getNameKFirst(), 30));
        moushikomi.set｢｢｣｣氏名関数（MyUtil.concatName(ventryData.getVEntryMain().getNameJFamily(),ventryData.getVEntryMain().getNameJFirst()), 50));
        moushikomi.set｢｢｣｣氏名出所（MyUtil.substringByByte(MyUtil.concatName(ventryData.getVEntryMain().getNameFamily(),ventryData.getVEntryMain().getNameFirst()),60));
        moushikomi.set薄字氏名力ナ（MyUtil.substringByByte(ventryData.getVEntryMain().getNameKFamily(),30));
        moushikomi.set薄字氏名出所（MyUtil.substringByByte(ventryData.getVEntryMain().getNameKFirst(),30));
        moushikomi.set薄字生年月日(MyUtil.concatPost(ventryData.getVEntryMain().getPostalCd1(),ventryData.getVEntryMain().getPostalCd2()));
        moushikomi.set｢｢｣｣都道府県（ventryData.getVEntryMain().getAddreskk());
        moushikomi.set｢｢｣｣住所力ナ（MyUtil.substringByByte(ventryData.getVEntryMain().getAddressJ(), 50));
        moushikomi.set｢｢｣｣市区町村（MyUtil.substringByByte(MyUtil.concatName(ventryData.getVEntryMain().getAddresssK(),ventryData.getVEntryMain().getAddressHouse()), 50));
        moushikomi.set｢｢｣｣住所力ナ出所（MyUtil.substringByByte(ventryData.getVEntryMain().getAddressSub(),120));
        moushikomi.set生年月日(MyUtil.convString(ventryData.getVEntryMain().getBirthDate()));
        moushikomi.set年齢(ventryData.getVEntryMain().getAgeRequest());
        moushikomi.set性別(seibetu);
        moushikomi.set自宅電話番号(MyUtil.conneTel(ventryData.getVEntryMain().getTel1(),ventryData.getVEntryMain().getTel2(), ventryData.getVEntryMain().getTel3()));
        moushikomi.set自宅電話番号有無("2");
        if (!MyUtil.isBlank(moushikomi.get自宅電話番号())) {
            moushikomi.set自宅電話番号有無("1");
        }
        moushikomi.set携帯電話番号(MyUtil.conneTel(ventryData.getVEntryMain().getTel1Portable(),ventryData.getVEntryMain().getTel2Portable()));
        moushikomi.set携帯電話番号使(hokensyo);
        moushikomi.set携帯電話在宅(yakusyoku);
        moushikomi.set携帯電話ゴ在宅(gyosyu);
        moushikomi.set携帯電話ゴ在宅使(oxaku);
        moushikomi.set携帯先電話番号(MyUtil.conneTel(ventryData.getVEntryOffice().getOfficeTel1(),ventryData.getVEntryOffice().getOfficeTel2(),ventryData.getVEntryOffice().getOfficeTel3()));
        moushikomi.set携帯先正社員(MyUtil.substringByByte(ventryData.getVEntryOffice().getOfficeName(),30));
        moushikomi.set携帯先正社員力ナ(yakusyoku);
        moushikomi.set携帯先工場業(ventryData.getVEntryOffice().getOfficelAttach(),30));
        moushikomi.set携帯先所在地（MyUtil.substringByByte(ventryData.getVEntryOffice().getOfficeNameK(), 30));
        moushikomi.set携帯先住所可セキュア（MyUtil.substringByByte(MyUtil.concatName(ventryData.getVEntryOffice().getOfficeAddr(),ventryData.getVEntryOffice().getOfficeAddr2()),ventryData.getVEntryOffice().getOfficeAddr3()));
        moushikomi.set携帯先住所方程式（MyUtil.concatPost(ventryData.getVEntryOffice().getOfficePostalCd1(),ventryData.getVEntryOffice().getOfficePostalCd2()));
        moushikomi.set携帯先郵便番号(MyUtil.substringByByte(ventryData.getVEntryOffice().getOfficeTel1(),ventryData.getVEntryOffice().getOfficeTel2(),ventryData.getVEntryOffice().getOfficeTel3()));
        moushikomi.set勤務先電話番号(yakusyoku);
        moushikomi.set勤務先退職者(syokugyou);
        moushikomi.set勤務先薬結論(ventryData.getVEntryOffice().getBefServiceYear(),ventryData.getVEntryOffice().getBefServiceMonth()));
        moushikomi.set勤務先在宅合意（syokusyu);
        moushikomi.set勤務先職務(shihonkin);
        moushikomi.set｢｢勤務先人数（MyUtil.calcMonth(ventryData.getVEntryMain().getResideYear(),
                MyUtil.convShort(ventryData.getVEntryMain().getResideMonth()))));
        moushikomi.set従属期間合意（MyUtil.convString(ventryData.getVEntryMain().getMoveYm()));
        moushikomi.set修正月(jukyokeitai);
        moushikomi.set居住形態(jukyokeitai);
        int bonus = ventryData.getVWebdata().getHouseRentBonus() == null ? 0
                : ventryData.getVWebdata().getHouseRentBonus() * 1000;
        int rent = ventryData.getVWebdata().getHouseRentMonth() == null ? 0
                : ventryData.getVWebdata().getHouseRentMonth() * 1000;
        moushikomi.set家賃等月額(MyUtil.convLong(rent));
        moushikomi.set家賃等_住宅ローン年間返済額(MyUtil.convLong(bonus));
        moushikomi.set居住年数(MyUtil.convShort(ventryData.getVWebdata().getHouseYear()));

        // 銀行取引情報処理
        if (bankTradeList != null && !bankTradeList.isEmpty()) {
            // 金融機関1
            if (bankTradeList.size() > 0) {
                VBanktrade banktrade = bankTradeList.get(0);
                String kariireyoto = null;
                try {
                    E借入用途 eKariireyoto = E借入用途.fromOldCode(banktrade.getSpentCd());
                    kariireyoto = eKariireyoto.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E借入用途", banktrade.getSpentCd());
                }
                String tanpo = null;
                try {
                    E借入担保 eKariiretanpo = E借入担保.fromOldCode(banktrade.getSecurityAn());
                    tanpo = eKariiretanpo.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E借入担保", banktrade.getSecurityAn());
                }
                moushikomi.set金融機関1借入有無("1");
                moushikomi.set金融機関1名称(MyUtil.substringByByte(banktrade.getBankName(), 30));
                moushikomi.set金融機関1種類(kariireyoto);
                moushikomi.set金融機関1種類その他(MyUtil.substringByByte(banktrade.getSpentEtc(), 50));
                moushikomi.set金融機関1担保有無(tanpo);
                moushikomi.set金融機関1期間(MyUtil.convShort(MyUtil.calcMonth(banktrade.getRepayYear(), banktrade.getRepayMonth())));
                moushikomi.set金融機関1年間返済額(MyUtil.convLong(banktrade.getRepay()));
                moushikomi.set金融機関1残高(MyUtil.convLong(banktrade.getBorrowAmnt()));
            }

            // 金融機関2
            if (bankTradeList.size() > 1) {
                VBanktrade banktrade = bankTradeList.get(1);
                String kariireyoto = null;
                try {
                    E借入用途 eKariireyoto = E借入用途.fromOldCode(banktrade.getSpentCd());
                    kariireyoto = eKariireyoto.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E借入用途", banktrade.getSpentCd());
                }
                String tanpo = null;
                try {
                    E借入担保 eKariiretanpo = E借入担保.fromOldCode(banktrade.getSecurityAn());
                    tanpo = eKariiretanpo.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E借入担保", banktrade.getSecurityAn());
                }
                moushikomi.set金融機関2借入有無("1");
                moushikomi.set金融機関2名称(MyUtil.substringByByte(banktrade.getBankName(), 30));
                moushikomi.set金融機関2種類(kariireyoto);
                moushikomi.set金融機関2種類その他(MyUtil.substringByByte(banktrade.getSpentEtc(), 50));
                moushikomi.set金融機関2担保有無(tanpo);
                moushikomi.set金融機関2期間(MyUtil.convShort(MyUtil.calcMonth(banktrade.getRepayYear(), banktrade.getRepayMonth())));
                moushikomi.set金融機関2年間返済額(MyUtil.convLong(banktrade.getRepay()));
                moushikomi.set金融機関2残高(MyUtil.convLong(banktrade.getBorrowAmnt()));
            }

            // 金融機関3
            if (bankTradeList.size() > 2) {
                VBanktrade banktrade = bankTradeList.get(2);
                String kariireyoto = null;
                try {
                    E借入用途 eKariireyoto = E借入用途.fromOldCode(banktrade.getSpentCd());
                    kariireyoto = eKariireyoto.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E借入用途", banktrade.getSpentCd());
                }
                String tanpo = null;
                try {
                    E借入担保 eKariiretanpo = E借入担保.fromOldCode(banktrade.getSecurityAn());
                    tanpo = eKariiretanpo.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E借入担保", banktrade.getSecurityAn());
                }
                moushikomi.set金融機関3借入有無("1");
                moushikomi.set金融機関3名称(MyUtil.substringByByte(banktrade.getBankName(), 30));
                moushikomi.set金融機関3種類(kariireyoto);
                moushikomi.set金融機関3種類その他(MyUtil.substringByByte(banktrade.getSpentEtc(), 50));
                moushikomi.set金融機関3担保有無(tanpo);
                moushikomi.set金融機関3期間(MyUtil.convShort(MyUtil.calcMonth(banktrade.getRepayYear(), banktrade.getRepayMonth())));
                moushikomi.set金融機関3年間返済額(MyUtil.convLong(banktrade.getRepay()));
                moushikomi.set金融機関3残高(MyUtil.convLong(banktrade.getBorrowAmnt()));
            }

            // 金融機関4
            if (bankTradeList.size() > 3) {
                VBanktrade banktrade = bankTradeList.get(3);
                String kariireyoto = null;
                try {
                    E借入用途 eKariireyoto = E借入用途.fromOldCode(banktrade.getSpentCd());
                    kariireyoto = eKariireyoto.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E借入用途", banktrade.getSpentCd());
                }
                String tanpo = null;
                try {
                    E借入担保 eKariiretanpo = E借入担保.fromOldCode(banktrade.getSecurityAn());
                    tanpo = eKariiretanpo.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E借入担保", banktrade.getSecurityAn());
                }
                moushikomi.set金融機関4借入有無("1");
                moushikomi.set金融機関4名称(MyUtil.substringByByte(banktrade.getBankName(), 30));
                moushikomi.set金融機関4種類(kariireyoto);
                moushikomi.set金融機関4種類その他(MyUtil.substringByByte(banktrade.getSpentEtc(), 50));
                moushikomi.set金融機関4担保有無(tanpo);
                moushikomi.set金融機関4期間(MyUtil.convShort(MyUtil.calcMonth(banktrade.getRepayYear(), banktrade.getRepayMonth())));
                moushikomi.set金融機関4年間返済額(MyUtil.convLong(banktrade.getRepay()));
                moushikomi.set金融機関4残高(MyUtil.convLong(banktrade.getBorrowAmnt()));
            }

            // 金融機関5
            if (bankTradeList.size() > 4) {
                VBanktrade banktrade = bankTradeList.get(4);
                String kariireyoto = null;
                try {
                    E借入用途 eKariireyoto = E借入用途.fromOldCode(banktrade.getSpentCd());
                    kariireyoto = eKariireyoto.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E借入用途", banktrade.getSpentCd());
                }
                String tanpo = null;
                try {
                    E借入担保 eKariiretanpo = E借入担保.fromOldCode(banktrade.getSecurityAn());
                    tanpo = eKariiretanpo.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E借入担保", banktrade.getSecurityAn());
                }
                moushikomi.set金融機関5借入有無("1");
                moushikomi.set金融機関5名称(MyUtil.substringByByte(banktrade.getBankName(), 30));
                moushikomi.set金融機関5種類(kariireyoto);
                moushikomi.set金融機関5種類その他(MyUtil.substringByByte(banktrade.getSpentEtc(), 50));
                moushikomi.set金融機関5担保有無(tanpo);
                moushikomi.set金融機関5期間(MyUtil.convShort(MyUtil.calcMonth(banktrade.getRepayYear(), banktrade.getRepayMonth())));
                moushikomi.set金融機関5年間返済額(MyUtil.convLong(banktrade.getRepay()));
                moushikomi.set金融機関5残高(MyUtil.convLong(banktrade.getBorrowAmnt()));
            }

            // 金融機関6
            if (bankTradeList.size() > 5) {
                VBanktrade banktrade = bankTradeList.get(5);
                String kariireyoto = null;
                try {
                    E借入用途 eKariireyoto = E借入用途.fromOldCode(banktrade.getSpentCd());
                    kariireyoto = eKariireyoto.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E借入用途", banktrade.getSpentCd());
                }
                String tanpo = null;
                try {
                    E借入担保 eKariiretanpo = E借入担保.fromOldCode(banktrade.getSecurityAn());
                    tanpo = eKariiretanpo.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E借入担保", banktrade.getSecurityAn());
                }
                moushikomi.set金融機関6借入有無("1");
                moushikomi.set金融機関6名称(MyUtil.substringByByte(banktrade.getBankName(), 30));
                moushikomi.set金融機関6種類(kariireyoto);
                moushikomi.set金融機関6種類その他(MyUtil.substringByByte(banktrade.getSpentEtc(), 50));
                moushikomi.set金融機関6担保有無(tanpo);
                moushikomi.set金融機関6期間(MyUtil.convShort(MyUtil.calcMonth(banktrade.getRepayYear(), banktrade.getRepayMonth())));
                moushikomi.set金融機関6年間返済額(MyUtil.convLong(banktrade.getRepay()));
                moushikomi.set金融機関6残高(MyUtil.convLong(banktrade.getBorrowAmnt()));
            }

            // 金融機関7
            if (bankTradeList.size() > 6) {
                VBanktrade banktrade = bankTradeList.get(6);
                String kariireyoto = null;
                try {
                    E借入用途 eKariireyoto = E借入用途.fromOldCode(banktrade.getSpentCd());
                    kariireyoto = eKariireyoto.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E借入用途", banktrade.getSpentCd());
                }
                String tanpo = null;
                try {
                    E借入担保 eKariiretanpo = E借入担保.fromOldCode(banktrade.getSecurityAn());
                    tanpo = eKariiretanpo.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E借入担保", banktrade.getSecurityAn());
                }
                moushikomi.set金融機関7借入有無("1");
                moushikomi.set金融機関7名称(MyUtil.substringByByte(banktrade.getBankName(), 30));
                moushikomi.set金融機関7種類(kariireyoto);
                moushikomi.set金融機関7種類その他(MyUtil.substringByByte(banktrade.getSpentEtc(), 50));
                moushikomi.set金融機関7担保有無(tanpo);
                moushikomi.set金融機関7期間(MyUtil.convShort(MyUtil.calcMonth(banktrade.getRepayYear(), banktrade.getRepayMonth())));
                moushikomi.set金融機関7年間返済額(MyUtil.convLong(banktrade.getRepay()));
                moushikomi.set金融機関7残高(MyUtil.convLong(banktrade.getBorrowAmnt()));
            }

            // 金融機関8
            if (bankTradeList.size() > 7) {
                VBanktrade banktrade = bankTradeList.get(7);
                String kariireyoto = null;
                try {
                    E借入用途 eKariireyoto = E借入用途.fromOldCode(banktrade.getSpentCd());
                    kariireyoto = eKariireyoto.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E借入用途", banktrade.getSpentCd());
                }
                String tanpo = null;
                try {
                    E借入担保 eKariiretanpo = E借入担保.fromOldCode(banktrade.getSecurityAn());
                    tanpo = eKariiretanpo.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E借入担保", banktrade.getSecurityAn());
                }
                moushikomi.set金融機関8借入有無("1");
                moushikomi.set金融機関8名称(MyUtil.substringByByte(banktrade.getBankName(), 30));
                moushikomi.set金融機関8種類(kariireyoto);
                moushikomi.set金融機関8種類その他(MyUtil.substringByByte(banktrade.getSpentEtc(), 50));
                moushikomi.set金融機関8担保有無(tanpo);
                moushikomi.set金融機関8期間(MyUtil.convShort(MyUtil.calcMonth(banktrade.getRepayYear(), banktrade.getRepayMonth())));
                moushikomi.set金融機関8年間返済額(MyUtil.convLong(banktrade.getRepay()));
                moushikomi.set金融機関8残高(MyUtil.convLong(banktrade.getBorrowAmnt()));
            }
        }

        // 最終的なフィールド設定
        moushikomi.set資金使途(shikinshito);
        moushikomi.set資金使途その他(shikinshitoEtc);
        moushikomi.set借入希望額(MyUtil.convLong(mGoods.getLimitBal()));
        moushikomi.set借入希望期間(MyUtil.convShort(mGoods.getRevolvingMonth()));

        return moushikomi;
    }

    /**
     * 申込□振込先_無担保へ変換する
     *
     * @param vEntryData            申込データ
     * @param requestNo             申込番号
     * @return 申込□振込先_無担保
     */
    private 申込□振込先_無担保 convMoushikomiHurikomisaki(VEntryData vEntryData, String requestNo) {
        
        申込□振込先_無担保 hurikomisaki = new 申込□振込先_無担保();
        
        // 返済用口座科目
        String hensaiyoukouzakamoku = "";
        if (vEntryData.getVWebdata().getTransferAccountItem() != null) {
            try {
                E返済用口座科目 eHensaiyoukouzakamoku = E返済用口座科目.fromOldCode(vEntryData.getVWebdata().getTransferAccountItem());
                hensaiyoukouzakamoku = eHensaiyoukouzakamoku.getNewCode();
            } catch (IllegalArgumentException e) {
                logger.info("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E返済用口座科目",
                        vEntryData.getVWebdata().getTransferAccountItem());
            }
        }
        
        hurikomisaki.set作成日時(MyUtil.convDate(vEntryData.getVEntryEtc().getCreateDate(), vEntryData.getVEntryEtc().getCreateTime()));
        hurikomisaki.set更新日時(MyUtil.convDate(vEntryData.getVEntryEtc().getUpdateDate(), vEntryData.getVEntryEtc().getUpdateTime()));
        hurikomisaki.set申込番号(requestNo);
        hurikomisaki.set振込先1_金融機関店舗名(vEntryData.getVWebdata().getTransferBankName());
        hurikomisaki.set振込先1_店番名(MyUtil.substringByByte(vEntryData.getVWebdata().getTransferBranchName(), 30));
        hurikomisaki.set振込先1_口座種類(hensaiyoukouzakamoku);
        hurikomisaki.set振込先1_口座番号(vEntryData.getVWebdata().getTransferAccountNo());
        hurikomisaki.set振込先1_口座名義人氏名(MyUtil.substringByByte(vEntryData.getVWebdata().getTransferAccountName(), 10));
        hurikomisaki.set振込先1_振込金額(MyUtil.convLong(vEntryData.getVWebdata().getTransferTransferAmount()));
        
        logger.debug("convMoushikomiHurikomisaki end");
        
        return hurikomisaki;
    }

    /**
     * 申込□無担保_教育へ変換する
     *
     * @param vEntryData            申込データ
     * @param requestNo             申込番号
     * @param moushikomiMokuteki    申込目的
     * @return 申込□無担保_教育
     */
    private 申込□無担保_教育 convMoushikomiKyoiku(VEntryData vEntryData, String requestNo, String moushikomiMokuteki) {
        
        logger.debug("convMoushikomiKyoiku start");
        
        if (vEntryData == null) {
            return null;
        }
        
        申込□無担保_教育 kyoiku = new 申込□無担保_教育();
        
        // 就学続柄1
        String tsuzukigara1 = "";
        try {
            E就学続柄 eTsuzukigara1 = E就学続柄.fromOldCode(vEntryData.getVWebdata().getSchool1FamilyRelationship());
            tsuzukigara1 = eTsuzukigara1.getNewCode();
        } catch (IllegalArgumentException e) {
            logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E就学続柄",
                    vEntryData.getVWebdata().getSchool1FamilyRelationship());
        }
        
        String sex1 = "";
        try {
            E性別 eSex1 = E性別.fromOldCode(vEntryData.getVWebdata().getSchool1Sex());
            sex1 = eSex1.getNewCode();
        } catch (IllegalArgumentException e) {
            logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E性別", vEntryData.getVWebdata().getSchool1Sex());
        }
        
        String nschool1 = vEntryData.getVWebdata().getSchool1NameJFamily();
        
        String schooltype1 = "";
        try {
            E就学進学先 eSchooltype1 = E就学進学先.fromOldCode(vEntryData.getVWebdata().getSchoolType1());
            schooltype1 = eSchooltype1.getNewCode();
        } catch (IllegalArgumentException e) {
            logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E就学進学先",
                    vEntryData.getVWebdata().getSchoolType1());
        }
        
        String doukyo1 = "";
        try {
            E就学同居有無 eDoukyo1 = E就学同居有無.fromOldCode(vEntryData.getVWebdata().getLivingStyle1());
            doukyo1 = eDoukyo1.getNewCode();
        } catch (IllegalArgumentException e) {
            logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E就学同居有無",
                    vEntryData.getVWebdata().getLivingStyle1());
        }
        
        String goukaku1 = "";
        try {
            E合格状況 eGoukaku1 = E合格状況.fromOldCode(vEntryData.getVWebdata().getAdmissionState1());
            goukaku1 = eGoukaku1.getNewCode();
        } catch (IllegalArgumentException e) {
            logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E合格状況",
                    vEntryData.getVWebdata().getAdmissionState1());
        }
        
        // E就学続柄2
        String tsuzukigara2 = "";
        try {
            E就学続柄 eTsuzukigara2 = E就学続柄.fromOldCode(vEntryData.getVWebdata().getSchool2FamilyRelationship());
            tsuzukigara2 = eTsuzukigara2.getNewCode();
        } catch (IllegalArgumentException e) {
            logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E就学続柄2",
                    vEntryData.getVWebdata().getSchool2FamilyRelationship());
        }
        
        String sex2 = "";
        try {
            E性別 eSex2 = E性別.fromOldCode(vEntryData.getVWebdata().getSchool2Sex());
            sex2 = eSex2.getNewCode();
        } catch (IllegalArgumentException e) {
            logger.debug("[申込番号={} {}が変換できませんでした。", requestNo, "E性別2", vEntryData.getVWebdata().getSchool2Sex());
        }
        
        String nschool2 = vEntryData.getVWebdata().getSchool2NameJFamily();
        
        String schooltype2 = "";
        try {
            E就学進学先 eSchooltype2 = E就学進学先.fromOldCode(vEntryData.getVWebdata().getSchoolType2());
            schooltype2 = eSchooltype2.getNewCode();
        } catch (IllegalArgumentException e) {
            logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E就学進学先2", vEntryData.getVWebdata().getSchoolType2());
        }
        
        String doukyo2 = "";
        try {
            E就学同居有無 eDoukyo2 = E就学同居有無.fromOldCode(vEntryData.getVWebdata().getLivingStyle2());
            doukyo2 = eDoukyo2.getNewCode();
        } catch (IllegalArgumentException e) {
            logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E就学同居有無2",
                    vEntryData.getVWebdata().getLivingStyle2());
        }
        
        String goukaku2 = "";
        try {
            E合格状況 eGoukaku2 = E合格状況.fromOldCode(vEntryData.getVWebdata().getAdmissionState2());
            goukaku2 = eGoukaku2.getNewCode();
        } catch (IllegalArgumentException e) {
            logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E合格状況2",
                    vEntryData.getVWebdata().getAdmissionState2());
        }
        
        Integer ninzu = 0;
        if (MyUtil.isNotBlank(nschool1)) {
            ninzu = ninzu + 1;
        }
        ;
        if (MyUtil.isNotBlank(nschool2)) {
            ninzu = ninzu + 1;
        }
        
        kyoiku.set作成日時(MyUtil.convDate(vEntryData.getVEntryMain().getCreateDate(),
                vEntryData.getVEntryMain().getCreateTime()));
        kyoiku.set更新日時(MyUtil.convDate(vEntryData.getVEntryMain().getUpdateDate(),
                vEntryData.getVEntryMain().getUpdateTime()));
        kyoiku.set申込番号(vEntryData.getVEntryMain().getRequestNo().toString());
        kyoiku.set申込目的(moushikomiMokuteki);
        kyoiku.set就学予定者の人数(MyUtil.convString(ninzu));
        
        kyoiku.set就学予定者1_進学先学校名(vEntryData.getVWebdata().getSchool1NameJFamily());
        kyoiku.set就学予定者1_進学先名(vEntryData.getVWebdata().getSchool1NameJFirst());
        kyoiku.set就学予定者1_カナ姓(vEntryData.getVWebdata().getSchool1NameKFamily());
        kyoiku.set就学予定者1_カナ名(vEntryData.getVWebdata().getSchool1NameKFirst());
        kyoiku.set就学予定者1_続柄(tsuzukigara1);
        kyoiku.set就学予定者1_性別(sex1);
        if (!StringUtils.isEmpty(vEntryData.getVWebdata().getSchool1BirthDateG())) {
            kyoiku.set就学予定者1_生年月日(
                    MyUtil.connatBirthday(vEntryData.getVWebdata().getSchool1BirthDateG(),
                            vEntryData.getVWebdata().getSchool1BirthDateY(),
                            vEntryData.getVWebdata().getSchool1BirthDateM(),
                            vEntryData.getVWebdata().getSchool1BirthDateD())); // 生年月日の元号、年月日をYYMMDDに変換???
        }
        kyoiku.set就学予定者1_進学先の学校種類(schooltype1);
        kyoiku.set就学予定者1_就学予定者の居住形態(doukyo1);
        kyoiku.set就学予定者1_合格状況(goukaku1);
        kyoiku.set就学予定者1_学校名(MyUtil.substringByByte(vEntryData.getVWebdata().getSchool1SchoolName(), 120));
        kyoiku.set就学予定者1_学部名(MyUtil.substringByByte(vEntryData.getVWebdata().getSchool1DepartmentName(), 120));
        kyoiku.set就学予定者1_入学年月(MyUtil.convString(vEntryData.getVWebdata().getSchool1EnterDate()));
        kyoiku.set就学予定者1_卒業年月(MyUtil.convString(vEntryData.getVWebdata().getSchool1GuraduationDate()));
        
        kyoiku.set就学予定者2_進学先学校名(vEntryData.getVWebdata().getSchool2NameJFamily());
        kyoiku.set就学予定者2_進学先名(vEntryData.getVWebdata().getSchool2NameJFirst());
        kyoiku.set就学予定者2_カナ姓(vEntryData.getVWebdata().getSchool2NameKFamily());
        kyoiku.set就学予定者2_カナ名(vEntryData.getVWebdata().getSchool2NameKFirst());
        kyoiku.set就学予定者2_続柄(tsuzukigara2);
        kyoiku.set就学予定者2_性別(sex2);
        if (!StringUtils.isEmpty(vEntryData.getVWebdata().getSchool2BirthDateG())) {
            kyoiku.set就学予定者2_生年月日(
                    MyUtil.connatBirthday(vEntryData.getVWebdata().getSchool2BirthDateG(),
                            vEntryData.getVWebdata().getSchool2BirthDateY(),
                            vEntryData.getVWebdata().getSchool2BirthDateM(),
                            vEntryData.getVWebdata().getSchool2BirthDateD()));
        }
        kyoiku.set就学予定者2_進学先の学校種類(schooltype2);
        kyoiku.set就学予定者2_就学予定者の居住形態(doukyo2);
        kyoiku.set就学予定者2_合格状況(goukaku2);
        kyoiku.set就学予定者2_学校名(MyUtil.substringByByte(vEntryData.getVWebdata().getSchool2SchoolName(), 120));
        kyoiku.set就学予定者2_学部名(MyUtil.substringByByte(vEntryData.getVWebdata().getSchool2DepartmentName(), 120));
        kyoiku.set就学予定者2_入学年月(MyUtil.convString(vEntryData.getVWebdata().getSchool2EnterDate()));
        kyoiku.set就学予定者2_卒業年月(MyUtil.convString(vEntryData.getVWebdata().getSchool2GuraduationDate()));
        
        return kyoiku;
    }

    /**
     * FAX送信画像データをFAX受信振分・申込徴求資料に変換
     *
     * @param vSendfaxExt           FAX送信データ
     * @param requestNo             申込番号
     * @param moushikomiMokuteki    申込目的
     * @return 変換後のイメージデータリスト
     */
    private List<Vイメージ> convFaxJyushinfuriwake(VSendfaxExt vSendfaxExt, String requestNo, String moushikomiMokuteki) {
        if (vSendfaxExt == null) {
            return Collections.emptyList();
        }
        
        List<Vイメージ> vImageList = new ArrayList<>();
        
        Long faxNo = imageNoSequenceMapper.getFaxNextSequence();
        
        int i = 1;
        if (vSendfaxExt.getImageList() != null) {
            for (Path strPath : vSendfaxExt.getImageList()) {
                Vイメージ vImage = new Vイメージ();
                vImage.setPath(strPath);
                
                Integer imageNo = imageNoSequenceMapper.getImageNextSequence();
                Timestamp nowDate = new Timestamp(System.currentTimeMillis());
                
                String yyyymmdd = MyUtil.convString(vSendfaxExt.getRequestDate());
                String yyyymm = MyUtil.convString(vSendfaxExt.getRequestDate()).substring(0, 6);
                String hhmmss = String.format("%06d", vSendfaxExt.getRequestTime());
                String uketsukeNo = String.format("%09d", faxNo);
                String fileImageNo = String.format("%09d", imageNo);
                String pageNo = String.format("%04d", i);
                String faxDataPath = MessageFormat.format("{0}\\{1}{2}-{3}-{4}.jpg", yyyymm, yyyymm, hhmmss, uketsukeNo, pageNo);
                String imgDataPath = MessageFormat.format("{0}\\{1}{2}{3}.jpg", yyyymm, yyyymmdd, hhmmss, fileImageNo);
                vImage.setImagePath(imgDataPath);
                
                // Path
                vImage.setPath(strPath);
                
                Fax受信振分 faxJyushinfuriwake = new Fax受信振分();
                faxJyushinfuriwake.set作成日時(MyUtil.convDate(vSendfaxExt.getRequestDate(), vSendfaxExt.getRequestTime()));
                faxJyushinfuriwake.set更新日時(MyUtil.convDate(vSendfaxExt.getRequestDate(), vSendfaxExt.getRequestTime()));
                faxJyushinfuriwake.setFax受信日時(MyUtil.convDate(vSendfaxExt.getRequestDate(), vSendfaxExt.getRequestTime()));
                faxJyushinfuriwake.setFax受信番号(faxNo);
                faxJyushinfuriwake.setページ番号(MyUtil.convShort(i));
                faxJyushinfuriwake.setデータ形式("jpg");
                faxJyushinfuriwake.setイメージ番号(imageNo);
                faxJyushinfuriwake.setイメージ作成日時(nowDate);
                faxJyushinfuriwake.setイメージ番号(imageNo);
                faxJyushinfuriwake.setデータファイル名(faxDataPath);
                faxJyushinfuriwake.setFax分類((short) 2);
                vImage.setFaxJyushinfuriwake(faxJyushinfuriwake);
                
                申込徴求資料 moushikomiChokyushiryo = new 申込徴求資料();
                moushikomiChokyushiryo.set作成日時(MyUtil.convDate(vSendfaxExt.getRequestDate(), vSendfaxExt.getRequestTime()));
                moushikomiChokyushiryo.set更新日時(MyUtil.convDate(vSendfaxExt.getRequestDate(), vSendfaxExt.getRequestTime()));
                moushikomiChokyushiryo.setFax受信日時(MyUtil.convDate(vSendfaxExt.getRequestDate(), vSendfaxExt.getRequestTime()));
                moushikomiChokyushiryo.setFax受信番号(faxNo);
                moushikomiChokyushiryo.setFax受信ページ番号(MyUtil.convShort(i));
                moushikomiChokyushiryo.set申込番号(requestNo);
                moushikomiChokyushiryo.setページ番号(MyUtil.convShort(i));
                moushikomiChokyushiryo.set文書種類コード("M6");
                moushikomiChokyushiryo.setイメージ番号(imageNo);
                moushikomiChokyushiryo.setイメージ作成日時(nowDate);
                moushikomiChokyushiryo.setデータファイル名(imgDataPath);
                vImage.setMoushikomiChokyushiryo(moushikomiChokyushiryo);
                
                vImageList.add(vImage);
                i++;
            }
        }
        
        return vImageList;
    }

    /**
     * イメージ画像データをFAX受信振分・申込徴求資料に変換
     *
     * @param vImagePr              FAX送信データ
     * @param requestNo             申込番号
     * @param moushikomiMokuteki    申込目的
     * @return 変換後のイメージデータリスト
     */
    private List<Vイメージ> convImageepr(VImagepr vImagePr, String requestNo, String moushikomiMokuteki) {
        if (vImagePr == null) {
            return Collections.emptyList();
        }
        
        List<Vイメージ> vImageList = new ArrayList<>();
        Long faxNo = imageNoSequenceMapper.getFaxNextSequence();
        
        Timestamp nowDate = new Timestamp(System.currentTimeMillis());
        int i = 1;
        if (vImagePr.getImageList() != null) {
            for (Path strPath : vImagePr.getImageList()) {
                Vイメージ vImage = new Vイメージ();
                vImage.setPath(strPath);
                Integer imageNo = imageNoSequenceMapper.getImageNextSequence();
                
                String yyyymm = MyUtil.convString(vImagePr.getReceptionDate()).substring(0, 6);
                String yyyymmdd = String.format("%08d", vImagePr.getReceptionDate());
                String hhmmss = "000000";
                if (vImagePr.getUpdateTime() != null) {
                    hhmmss = String.format("%06d", vImagePr.getUpdateTime());
                }
                String uketsukeNo = String.format("%09d", faxNo);
                String fileImageNo = String.format("%09d", imageNo);
                String pageNo = String.format("%04d", i);
                String faxDataPath = MessageFormat.format("{0}\\{1}{2}-{3}-{4}.jpg", yyyymm, yyyymm, hhmmss, uketsukeNo, pageNo);
                String imgDataPath = MessageFormat.format("{0}\\{1}{2}{3}.jpg", yyyymm, yyyymmdd, hhmmss, fileImageNo);
                vImage.setImagePath(imgDataPath);
                
                Fax受信振分 faxJyushinfuriwake = new Fax受信振分();
                faxJyushinfuriwake.set作成日時(MyUtil.convDate(vImagePr.getCreateDate(), vImagePr.getCreateTime()));
                faxJyushinfuriwake.set更新日時(MyUtil.convDate(vImagePr.getUpdateDate(), vImagePr.getUpdateTime()));
                faxJyushinfuriwake.setFax受信日時(MyUtil.convDate(vImagePr.getReceptionDate()));
                faxJyushinfuriwake.setFax受信番号(faxNo);
                faxJyushinfuriwake.setページ番号(MyUtil.convShort(i));
                faxJyushinfuriwake.setデータ形式("jpg");
                faxJyushinfuriwake.setイメージ番号(imageNo);
                faxJyushinfuriwake.setイメージ作成日時(nowDate);
                faxJyushinfuriwake.setデータファイル名(faxDataPath);
                faxJyushinfuriwake.setFax分類((short) 2);
                vImage.setFaxJyushinfuriwake(faxJyushinfuriwake);
                
                申込徴求資料 moushikomiChokyushiryo = new 申込徴求資料();
                moushikomiChokyushiryo.set作成日時(MyUtil.convDate(vImagePr.getCreateDate(), vImagePr.getCreateTime()));
                moushikomiChokyushiryo.set更新日時(MyUtil.convDate(vImagePr.getUpdateDate(), vImagePr.getUpdateTime()));
                moushikomiChokyushiryo.setFax受信日時(MyUtil.convDate(vImagePr.getReceptionDate()));
                moushikomiChokyushiryo.setFax受信番号(faxNo);
                moushikomiChokyushiryo.setFax受信ページ番号(MyUtil.convShort(i));
                moushikomiChokyushiryo.set申込番号(requestNo);
                moushikomiChokyushiryo.setページ番号(MyUtil.convShort(i));
                moushikomiChokyushiryo.set文書種類コード("M6");
                moushikomiChokyushiryo.setイメージ番号(imageNo);
                moushikomiChokyushiryo.setイメージ作成日時(nowDate);
                moushikomiChokyushiryo.setデータファイル名(imgDataPath);
                vImage.setMoushikomiChokyushiryo(moushikomiChokyushiryo);
                
                vImageList.add(vImage);
                i++;
            }
        }
        
        return vImageList;
    }

    /**
     * 申込審査結果段階へ変換する
     *
     * @param vJudgeMain            審査状況
     * @param vJudgeResultList      審査結果
     * @param vSaisonReuslt         セゾン保証審査結果
     * @param vSdcResult            SDC審査結果
     * @param requestNo             登録する申込書番号
     * @param moushikomiMokuteki    登録する申込目的
     * @param eHosyokaisyaCd        保証会社情報
     * @return 申込審査段階
     */
    private 申込審査段階 convMoushikomiShinsadankai(VJudgeMain vJudgeMain, List<VJudgeResult> vJudgeResultList,
            VSaisonResult vSaisonReuslt, List<VJudgeResult> vSdcResultList, String requestNo,
            String moushikomiMokuteki, E保証会社統合 eHosyokaisyaCd) {
        申込審査段階 shinsadankai = new 申込審査段階();
        logger.debug("convMoushikomiShinsadankai start");

        String shinsaKekka = "";
        if (vJudgeResultList != null) {
            for (VJudgeResult vJudgeResult : vJudgeResultList) {
                // 審査結果取得
                try {
                    E審査結果 eNumShinsKekka = E審査結果.fromOldCode(vJudgeResult.getLastUpRes());
                    shinsaKekka = eNumShinsKekka.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E審査結果", vJudgeResult.getLastUpRes());
                    // 審査結果が存在しない場合は登録しない
                    continue;
                }

                Timestamp ichiji1 = null;
                Timestamp ichiji2 = null;
                Timestamp niji = null;

                // レコードによって設定項目が異なる
                try {
                    if (E進歩.一次審査1.getOldCode().equals(vJudgeResult.getStatus())) {
                        if (ichiji1 == null || ichiji1.after(MyUtil.convDate(vJudgeResult.getLastUpDate(), vJudgeResult.getLastUpTime()))) {
                            shinsadankai.set銀行続行日付(MyUtil.convDate(vJudgeResult.getLastUpDate(), vJudgeResult.getLastUpTime()));
                            shinsadankai.set銀行続行ユーザ名(vJudgeResult.getLastUpName());
                            shinsadankai.set銀行続行結果(shinsaKekka);
                            ichiji1 = MyUtil.convDate(vJudgeResult.getLastUpDate(), vJudgeResult.getLastUpTime());
                        }
                    } else if (E進歩.一次審査2.getOldCode().equals(vJudgeResult.getStatus())) {
                        if (ichiji2 == null || ichiji2.after(MyUtil.convDate(vJudgeResult.getLastUpDate(), vJudgeResult.getLastUpTime()))) {
                            shinsadankai.set銀行審査日付(MyUtil.convDate(vJudgeResult.getLastUpDate(), vJudgeResult.getLastUpTime()));
                            shinsadankai.set銀行審査ユーザ名(vJudgeResult.getLastUpName());
                            shinsadankai.set銀行審査結果(shinsaKekka);
                            ichiji2 = MyUtil.convDate(vJudgeResult.getLastUpDate(), vJudgeResult.getLastUpTime());
                        }
                    } else if (E進歩.二次審査.getOldCode().equals(vJudgeResult.getStatus())) {
                        if (niji == null || niji.after(MyUtil.convDate(vJudgeResult.getLastUpDate(), vJudgeResult.getLastUpTime()))) {
                            shinsadankai.set銀行決裁日付(MyUtil.convDate(vJudgeResult.getLastUpDate(), vJudgeResult.getLastUpTime()));
                            shinsadankai.set銀行決裁ユーザ名(vJudgeResult.getLastUpName());
                            shinsadankai.set銀行決裁結果(shinsaKekka);
                            shinsadankai.set最終審査結果日付(MyUtil.convDate(vJudgeResult.getLastUpDate(), vJudgeResult.getLastUpTime()));
                        }
                    }
                } catch (IllegalArgumentException e) {
                    logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E進捗", vJudgeResult.getStatus());
                }
            }
        }
        // TODO: SDC and Saison result processing for shinsadankai (more screenshots needed)

        String userName = "";
        if (mUserMap.containsKey(vJudgeMain.getGetCd())) {
            userName = mUserMap.get(vJudgeMain.getGetCd()).getChargeName();
        }
        shinsadankai.set申込ユーザ名(userName);

        if (vSaisonReuslt != null) {
            shinsaKekka = "";
            // 審査結果取得
            try {
                E審査結果 eNumShinsKekka = E審査結果.fromOldCode(vSaisonReuslt.getKekka());
                shinsaKekka = eNumShinsKekka.getNewCode();
            } catch (IllegalArgumentException e) {
                logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E審査結果", vSaisonReuslt.getKekka());
            }
            shinsadankai.set保証審査日付(MyUtil.convDate(vSaisonReuslt.getMakeDate(), vSaisonReuslt.getMakeTime()));
            shinsadankai.set保証審査結果(shinsaKekka);
        }

        if (vSdcResultList != null) {
            for (VJudgeResult vJudgeResult : vSdcResultList) {
                // レコードによって設定項目が異なる
                if ("1".equals(vJudgeResult.getNewFlg())) {
                    shinsaKekka = "";
                    // 審査結果取得
                    try {
                        E保証会社側審査結果 eNumShinsKekka = E保証会社側審査結果.fromOldCode(vJudgeResult.getLastUpRes());
                        shinsaKekka = eNumShinsKekka.getNewCode();
                    } catch (IllegalArgumentException e) {
                        logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E保証会社側審査結果",
                                vJudgeResult.getLastUpRes());
                    }
                    shinsadankai.set保証審査日付(MyUtil.convDate(vJudgeResult.getLastUpDate()));
                    shinsadankai.set保証審査ユーザ名(vJudgeResult.getLastUpName());
                    shinsadankai.set保証審査結果(shinsaKekka);
                }
            }
        }

        shinsadankai.set申込番号(requestNo);
        shinsadankai.set申込目的(moushikomiMokuteki);
        shinsadankai.set申込入力日付(MyUtil.convDate(vJudgeMain.getReceptionDate(), vJudgeMain.getReceptionTime()));
        shinsadankai.set作成日時(MyUtil.convDate(vJudgeMain.getCreateDate(), vJudgeMain.getCreateTime()));
        shinsadankai.set更新日時(MyUtil.convDate(vJudgeMain.getUpdateDate(), vJudgeMain.getUpdateTime()));
        shinsadankai.set審査完了区分("1");
        shinsadankai.set審査段階("銀行決裁");
        shinsadankai.set保証番号(MyUtil.substringByByte(vJudgeMain.getGuarantNo(), 16));
        if (eHosyokaisyaCd != null) {
            shinsadankai.set保証会社コード(eHosyokaisyaCd.getNewCode());
            shinsadankai.set保証会社名(eHosyokaisyaCd.getNewName());
        }

        return shinsadankai;
    }

    /**
     * 進捗コード取得
     * 1: 住宅ローン、2: 無担保ローン、3: カードローン、4: アイフルフリーローン、
     * 5: セレカ、6: 極度増額、7: BQL、8: 外部ローン、
     * @param shinchokuSyohin
     * @return
     */
    private String getShinchokuCode(進捗商品情報 shinchokuSyohin, String oldShinchoku) throws IllegalArgumentException {
        String shohinCode = "";

        if ("3".equals(shinchokuSyohin.shohinMaster.get商品大分類())) {
            //カードローン
            E進歩__カードローン__正式 shinchoku = E進歩__カードローン__正式.fromOldCode(oldShinchoku);
            shohinCode = shinchoku.getNewCode();
        } else if ("4".equals(shinchokuSyohin.shohinMaster.get商品大分類())) {
            //アイフルローン
            if (E申込目的.仮.getNewCode().equals(shinchokuSyohin.moshikomimokuteki)) {
                E進歩__アイフルローン__事前 shinchoku = E進歩__アイフルローン__事前.fromOldCode(oldShinchoku);
                shohinCode = shinchoku.getNewCode();
            } else {
                E進歩__アイフルローン__正式 shinchoku = E進歩__アイフルローン__正式.fromOldCode(oldShinchoku);
                shohinCode = shinchoku.getNewCode();
            }
        } else if ("5".equals(shinchokuSyohin.shohinMaster.get商品大分類())) {
            //セレカ
            if (E申込目的.仮.getNewCode().equals(shinchokuSyohin.moshikomimokuteki)) {
                E進歩__セレカ__事前 shinchoku = E進歩__セレカ__事前.fromOldCode(oldShinchoku);
                shohinCode = shinchoku.getNewCode();
            } else {
                E進歩__セレカ__正式 shinchoku = E進歩__セレカ__正式.fromOldCode(oldShinchoku);
                shohinCode = shinchoku.getNewCode();
            }
        } else if ("7".equals(shinchokuSyohin.shohinMaster.get商品大分類())) {
            //BQL
            E進歩__BQL__正式 shinchoku = E進歩__BQL__正式.fromOldCode(oldShinchoku);
            shohinCode = shinchoku.getNewCode();
        } else if ("6".equals(shinchokuSyohin.shohinMaster.get商品大分類())) {
            //極壇
            E進歩__極壇__正式 shinchoku = E進歩__極壇__正式.fromOldCode(oldShinchoku);
            shohinCode = shinchoku.getNewCode();
        } else {
            //無担保ローン
            if (E申込目的.仮.getNewCode().equals(shinchokuSyohin.moshikomimokuteki)) {
                E進歩__無担保__事前 shinchoku = E進歩__無担保__事前.fromOldCode(oldShinchoku);
                shohinCode = shinchoku.getNewCode();
            } else {
                E進歩__無担保__正式 shinchoku = E進歩__無担保__正式.fromOldCode(oldShinchoku);
                shohinCode = shinchoku.getNewCode();
            }
        }
        return shohinCode;
    }

    /**
     * 申込審査履歴へ変換
     *
     * @param judgeMainRecord       審査状況
     * @param vJudgeResultList      審査結果
     * @param vSdcResultList        SDC保証会社審査結果
     * @param saisonResultRecord    セゾン保証審査結果
     * @param requestNo             登録する申込書番号
     * @param moushikomiMokuteki    登録する申込目的
     * @return 申込審査履歴
     */
    private List<申込審査履歴> convMoushikomiShinsarireki(VJudgeMain judgeMainRecord, List<VJudgeResult> vJudgeResultList,
            List<VJudgeResult> vSdcResultList, VSaisonResult saisonResultRecord, String requestNo,
            String moushikomiMokuteki, 進捗商品情報 shinchokuSyohin, VJudgeGuarant vJudgeGuarant) {
        logger.debug("convMoushikomiShinsarireki start");

        HashSet<String> hashEvent = new HashSet<String>();
        Timestamp judgeMainCreateTime = MyUtil.convDate(judgeMainRecord.getCreateDate(), judgeMainRecord.getCreateTime());
        if (judgeMainCreateTime == null) {
            judgeMainCreateTime = MyUtil.convDate(judgeMainRecord.getLastUpDate(), judgeMainRecord.getLastUpTime());
        }
        Timestamp judgeMainUpdateTime = MyUtil.convDate(judgeMainRecord.getUpdateDate(), judgeMainRecord.getUpdateTime());
        if (judgeMainUpdateTime == null) {
            judgeMainUpdateTime = MyUtil.convDate(judgeMainRecord.getLastUpDate(), judgeMainRecord.getLastUpTime());
        }
        Timestamp createTime = null;
        Timestamp updateTime = null;

        List<申込審査履歴> shinsarirekiList = new ArrayList<申込審査履歴>();
        if (vJudgeResultList != null) {
            for (VJudgeResult judgeResult : vJudgeResultList) {
                申込審査履歴 shinsarireki = new 申込審査履歴();
                String status = judgeResult.getStatus();
                try {
                    shinsarireki.set進捗コード(getShinchokuCode(shinchokuSyohin, status));
                } catch (IllegalArgumentException e) {
                    logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E進捗", judgeResult.getStatus());
                }

                if (hashEvent.contains(requestNo + judgeResult.getLastUpDate() + judgeResult.getLastUpTime())) {
                    //イベント番号が重複したらスキップ
                    logger.info("[申込番号={} {}が重複してます。 ", requestNo, MyUtil.convDate(judgeResult.getLastUpDate(), judgeResult.getLastUpTime()));
                    continue;
                }
                hashEvent.add(requestNo + judgeResult.getLastUpDate() + judgeResult.getLastUpTime());

                createTime = MyUtil.convDate(judgeResult.getCreateDate(), judgeResult.getCreateTime());
                if (createTime == null) {
                    createTime = judgeMainCreateTime;
                }
                updateTime = MyUtil.convDate(judgeResult.getUpdateDate(), judgeResult.getUpdateTime());
                if (updateTime == null) {
                    updateTime = judgeMainUpdateTime;
                }
                shinsarireki.set作成日時(createTime);
                shinsarireki.set更新日時(updateTime);
                shinsarireki.set申込番号(requestNo);
                shinsarireki.set申込目的(moushikomiMokuteki);
                shinsarireki.setイベント("審査結果");
                shinsarireki.setイベント日時(MyUtil.convDate(judgeResult.getLastUpDate(), judgeResult.getLastUpTime()));
                shinsarireki.setユーザ名(judgeResult.getLastUpName());
                shinsarireki.setユーザid(MyUtil.convString(judgeResult.getLastUpCd()));
                shinsarireki.set回数(MyUtil.convShort(1));
                shinsarirekiList.add(shinsarireki);
            }
        }

        if (vSdcResultList != null) {
            for (VJudgeResult judgeResult : vSdcResultList) {
                申込審査履歴 shinsarireki = new 申込審査履歴();
                String status = judgeResult.getStatus();
                try {
                    if (E進歩.保証会社審査.getOldCode().equals(status)) {
                        //  shinsarireki.set進捗コード(E進歩.保証会社審査.getNewCode());
                        shinsarireki.set進捗コード(getShinchokuCode(shinchokuSyohin, status));
                    }
                } catch (IllegalArgumentException e) {
                    logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E進捗", judgeResult.getStatus());
                }

                if (hashEvent.contains(requestNo + judgeResult.getLastUpDate() + judgeResult.getLastUpTime())) {
                    //イベント番号が重複したらスキップ
                    logger.info("[申込番号={} {}が重複してます。 ", requestNo, MyUtil.convDate(judgeResult.getLastUpDate(), judgeResult.getLastUpTime()));
                    continue;
                }
                hashEvent.add(requestNo + judgeResult.getLastUpDate() + judgeResult.getLastUpTime());

                createTime = MyUtil.convDate(judgeResult.getCreateDate(), judgeResult.getCreateTime());
                if (createTime == null) {
                    createTime = judgeMainCreateTime;
                }
                updateTime = MyUtil.convDate(judgeResult.getUpdateDate(), judgeResult.getUpdateTime());
                if (updateTime == null) {
                    updateTime = judgeMainUpdateTime;
                }
                shinsarireki.set作成日時(createTime);
                shinsarireki.set更新日時(updateTime);
                shinsarireki.set申込番号(requestNo);
                shinsarireki.set申込目的(moushikomiMokuteki);
                shinsarireki.set回数(MyUtil.convShort(1));
                shinsarireki.setイベント("審査結果");
                shinsarireki.setイベント日時(MyUtil.convDate(judgeResult.getLastUpDate(), judgeResult.getLastUpTime()));
                shinsarireki.setユーザ名(judgeResult.getLastUpName());
                shinsarireki.setユーザid(MyUtil.convString(judgeResult.getLastUpCd()));
                shinsarirekiList.add(shinsarireki);
            }
        }

        if (saisonResultRecord != null) {
            申込審査履歴 shinsarireki = new 申込審査履歴();
            boolean checkFlg = true;
            try {
                if (E申込目的.仮.getNewCode().equals(moushikomiMokuteki)) {
                    E進歩__セレカ__事前 shinchoku = E進歩__セレカ__事前.fromOldCode(E進歩__セレカ__事前.保証会社審査.getOldCode());
                    shinsarireki.set進捗コード(shinchoku.getNewCode());
                } else {
                    E進歩__セレカ__正式 shinchoku = E進歩__セレカ__正式.fromOldCode(E進歩__セレカ__正式.保証会社審査.getOldCode());
                    shinsarireki.set進捗コード(shinchoku.getNewCode());
                }
            } catch (IllegalArgumentException e) {
                logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E進捗", E進歩.保証会社審査.getOldCode());
            }

            if (hashEvent.contains(requestNo + saisonResultRecord.getMakeDate() + saisonResultRecord.getMakeTime())) {
                //イベント番号が重複したらスキップ
                logger.info("[申込番号={} {}が重複してます。 ", requestNo, MyUtil.convDate(saisonResultRecord.getMakeDate(), saisonResultRecord.getMakeTime()));
                checkFlg = false;
            }
            hashEvent.add(requestNo + saisonResultRecord.getMakeDate() + saisonResultRecord.getMakeTime());

            if (checkFlg) {
                createTime = MyUtil.convDate(saisonResultRecord.getMakeDate(), saisonResultRecord.getMakeTime());
                if (createTime == null) {
                    createTime = judgeMainCreateTime;
                }
                updateTime = MyUtil.convDate(saisonResultRecord.getMakeDate(), saisonResultRecord.getMakeTime());
                if (updateTime == null) {
                    updateTime = judgeMainUpdateTime;
                }
                shinsarireki.set申込番号(requestNo);
                shinsarireki.set申込目的(moushikomiMokuteki);
                shinsarireki.set作成日時(createTime);
                shinsarireki.set更新日時(updateTime);
                shinsarireki.set回数(MyUtil.convShort("1"));
                shinsarireki.setイベント("審査結果");
                shinsarireki.setイベント日時(MyUtil.convDate(saisonResultRecord.getMakeDate(), saisonResultRecord.getMakeTime()));
                shinsarirekiList.add(shinsarireki);
            }
        }

        // データがぞんざいする且つ保証会社の場合のみ登録（審査コメントのレコード）
        if (vJudgeGuarant != null && "2".equals(kind)) {
            申込審査履歴 shinsarireki = new 申込審査履歴();
            try {
                if (E申込目的.仮.getNewCode().equals(moushikomiMokuteki)) {
                    E進歩__セレカ__事前 shinchoku = E進歩__セレカ__事前.fromOldCode(E進歩__セレカ__事前.保証会社審査.getOldCode());
                    shinsarireki.set進捗コード(shinchoku.getNewCode());
                } else {
                    E進歩__セレカ__正式 shinchoku = E進歩__セレカ__正式.fromOldCode(E進歩__セレカ__正式.保証会社審査.getOldCode());
                    shinsarireki.set進捗コード(shinchoku.getNewCode());
                }
            } catch (IllegalArgumentException e) {
                logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E進捗", E進歩.保証会社審査.getOldCode());
            }

            createTime = MyUtil.convDate(vJudgeGuarant.getCreateDate(), vJudgeGuarant.getCreateTime());
            if (createTime == null) {
                createTime = judgeMainCreateTime;
            }
            updateTime = MyUtil.convDate(vJudgeGuarant.getUpdateDate(), vJudgeGuarant.getUpdateTime());
            if (updateTime == null) {
                updateTime = judgeMainUpdateTime;
            }
            shinsarireki.set申込番号(requestNo);
            shinsarireki.set申込目的(moushikomiMokuteki);
            shinsarireki.set作成日時(createTime);
            shinsarireki.set更新日時(updateTime);
            shinsarireki.set回数(MyUtil.convShort("1"));
            shinsarireki.setイベント("審査コメント");
            shinsarireki.setイベント日時(updateTime);
            shinsarirekiList.add(shinsarireki);
        }

        logger.debug("convMoushikomiShinsarireki end");
        return shinsarirekiList;
    }

    /**
     * 審査結果情報へ変換
     *
     * @param vJudgeResultList      審査結果
     * @param sdcResultRecordList   SDC保証審査結果
     * @param saisonResultRecord    セゾン保証審査結果
     * @param judgeMainRecord       審査状況
     * @param requestNo             登録する申込書番号
     * @param moushikomiMokuteki    登録する申込目的
     * @param shohinCd              商品コード
     * @param eHosyokaisyaCd        保証会社情報
     * @return 審査結果情報
     */
    private List<審査結果> convShinsakekka(List<VJudgeResult> vJudgeResultList, List<VJudgeResult> sdcResultRecordList,
            VSaisonResult saisonResultRecord, VJudgeMain judgeMainRecord, String requestNo,
            String moushikomiMokuteki, String shohinCd, E保証会社統合 eHosyokaisyaCd) {
        logger.debug("convShinsakekka start");
        // 銀行審査の審査結果
        List<審査結果> shinsakekkaList = new ArrayList<審査結果>();

        Timestamp judgeMainCreateTime = MyUtil.convDate(judgeMainRecord.getCreateDate(), judgeMainRecord.getCreateTime());
        if (judgeMainCreateTime == null) {
            judgeMainCreateTime = MyUtil.convDate(judgeMainRecord.getLastUpDate(), judgeMainRecord.getLastUpTime());
        }
        Timestamp judgeMainUpdateTime = MyUtil.convDate(judgeMainRecord.getUpdateDate(), judgeMainRecord.getUpdateTime());
        if (judgeMainUpdateTime == null) {
            judgeMainUpdateTime = MyUtil.convDate(judgeMainRecord.getLastUpDate(), judgeMainRecord.getLastUpTime());
        }
        Timestamp createTime = null;
        Timestamp updateTime = null;

        HashSet<String> hashEvent = new HashSet<String>();
        String hosyoShinsaKekka = "";
        if (vJudgeResultList != null) {
            for (VJudgeResult judgeResultRecord : vJudgeResultList) {
                審査結果 shinsakekka = new 審査結果();
                logger.debug("judgeResultRecord({}) ", judgeResultRecord.toString());

                // 審査結果取得
                String shinsaKekka = "";
                try {
                    E審査結果 eNumShinsKekka = E審査結果.fromOldCode(judgeResultRecord.getLastUpRes());
                    shinsaKekka = eNumShinsKekka.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E保証会社側審査結果",
                            judgeResultRecord.getLastUpRes());
                    //以下処理はスキップ
                    continue;
                }

                String shinsaDankai = "";
                try {
                    if (E進歩.一次審査1.getOldCode().equals(judgeResultRecord.getStatus())) {
                        shinsaDankai = E進歩.一次審査1.getNewName();
                    } else if (E進歩.一次審査2.getOldCode().equals(judgeResultRecord.getStatus())) {
                        shinsaDankai = E進歩.一次審査2.getNewName();
                    } else if (E進歩.二次審査.getOldCode().equals(judgeResultRecord.getStatus())) {
                        shinsaDankai = E進歩.二次審査.getNewName();
                    }
                } catch (IllegalArgumentException e) {
                    logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E進捗", judgeResultRecord.getStatus());
                }

                if (hashEvent.contains(requestNo + judgeResultRecord.getLastUpDate() + judgeResultRecord.getLastUpTime())) {
                    //イベント番号が重複したらスキップ
                    logger.info("[申込番号={} {}が重複してます。 ", requestNo, MyUtil.convDate(judgeResultRecord.getUpdateDate(), judgeResultRecord.getLastUpTime()));
                    continue;
                }
                hashEvent.add(requestNo + judgeResultRecord.getLastUpDate() + judgeResultRecord.getLastUpTime());

                createTime = MyUtil.convDate(judgeResultRecord.getCreateDate(), judgeResultRecord.getCreateTime());
                if (createTime == null) {
                    createTime = judgeMainCreateTime;
                }
                updateTime = MyUtil.convDate(judgeResultRecord.getUpdateDate(), judgeResultRecord.getUpdateTime());
                if (updateTime == null) {
                    updateTime = judgeMainUpdateTime;
                }
                shinsakekka.set作成日時(createTime);
                shinsakekka.set更新日時(updateTime);
                shinsakekka.set申込番号(requestNo);
                shinsakekka.set申込目的(moushikomiMokuteki);
                shinsakekka.setイベント("審査結果");
                shinsakekka.setイベント日時(MyUtil.convDate(judgeResultRecord.getLastUpDate(), judgeResultRecord.getLastUpTime()));
                shinsakekka.set区分("1");
                shinsakekka.set審査段階(shinsaDankai);
                shinsakekka.set審査結果(shinsaKekka);
                shinsakekka.setユーザid(MyUtil.convString(judgeResultRecord.getLastUpCd()));
                shinsakekka.setユーザ名(judgeResultRecord.getLastUpName());
                shinsakekka.set承認商品コード(shohinCd);
                shinsakekka.setコメント(judgeResultRecord.getLastUpComment());
                shinsakekka.set結果日付(MyUtil.convString(judgeResultRecord.getLastUpDate()));

                shinsakekkaList.add(shinsakekka);
            }
        }

        // ＳＤＣの審査結果
        if (sdcResultRecordList != null) {
            for (VJudgeResult judgeResult : sdcResultRecordList) {
                審査結果 shinsakekka = new 審査結果();
                hosyoShinsaKekka = "";
                try {
                    E保証会社側審査結果 eHShinsKekka = E保証会社側審査結果.fromOldCode(judgeResult.getLastUpRes());
                    hosyoShinsaKekka = eHShinsKekka.getNewCode();
                } catch (IllegalArgumentException e) {
                    logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E保証会社側審査結果", judgeResult.getLastUpRes());
                }

                if (hashEvent.contains(requestNo + judgeResult.getLastUpDate() + judgeResult.getLastUpTime())) {
                    //イベント番号が重複したらスキップ
                    logger.info("[申込番号={} {}が重複してます。 ", requestNo, MyUtil.convDate(judgeResult.getLastUpDate(), judgeResult.getLastUpTime()));
                    checkFlg = false;
                }
                hashEvent.add(requestNo + judgeResult.getLastUpDate() + judgeResult.getLastUpTime());

                if (checkFlg) {
                    createTime = MyUtil.convDate(judgeResult.getCreateDate(), judgeResult.getCreateTime());
                    if (createTime == null) {
                        createTime = judgeMainCreateTime;
                    }
                    updateTime = MyUtil.convDate(judgeResult.getUpdateDate(), judgeResult.getUpdateTime());
                    if (updateTime == null) {
                        updateTime = judgeMainUpdateTime;
                    }
                    shinsakekka.set作成日時(createTime);
                    shinsakekka.set更新日時(updateTime);
                    shinsakekka.set申込番号(requestNo);
                    shinsakekka.set申込目的(moushikomiMokuteki);
                    shinsakekka.setイベント("審査結果");
                    shinsakekka.setイベント日時(MyUtil.convDate(judgeResult.getLastUpDate(), judgeResult.getLastUpTime()));
                    shinsakekka.set区分("2");
                    shinsakekka.set審査段階("一次保証審査");
                    shinsakekka.set審査結果(hosyoShinsaKekka);
                    shinsakekka.setユーザid(MyUtil.convString(judgeResult.getLastUpCd()));
                    shinsakekka.setユーザ名(judgeResult.getLastUpName());
                    shinsakekka.set承認商品コード(shohinCd);
                    shinsakekka.set承認借入金額(MyUtil.convLong(judgeMainRecord.getBorrowAmnt()));
                    shinsakekka.setコメント(judgeMainRecord.getGuarantCpComment());
                    shinsakekka.set結果日付(MyUtil.convString(judgeResult.getLastUpDate()));
                    shinsakekka.set外部保証番号(MyUtil.substringByByte(judgeMainRecord.getGuarantNo(), 10));
                    if (eHosyokaisyaCd != null) {
                        shinsakekka.set保証会社コード(eHosyokaisyaCd.getNewCode());
                        shinsakekka.set保証会社名(eHosyokaisyaCd.getNewName());
                    }
                    shinsakekkaList.add(shinsakekka);
                }
            }
        }

        // セゾン審査結果
        if (saisonResultRecord != null) {
            審査結果 shinsakekka = new 審査結果();
            hosyoShinsaKekka = "";
            boolean checkFlg = true;
            try {
                Eセゾン審査結果 eHShinsKekka = Eセゾン審査結果.fromOldCode(saisonResultRecord.getKekka());
                hosyoShinsaKekka = eHShinsKekka.getNewCode();
            } catch (IllegalArgumentException e) {
                logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "Eセゾン審査結果", saisonResultRecord.getKekka());
            }

            if (hashEvent.contains(requestNo + saisonResultRecord.getMakeDate() + saisonResultRecord.getMakeTime())) {
                //イベント番号が重複したらスキップ
                logger.info("[申込番号={} {}が重複してます。 ", requestNo, MyUtil.convDate(saisonResultRecord.getMakeDate(), saisonResultRecord.getMakeTime()));
                checkFlg = false;
            }
            hashEvent.add(requestNo + saisonResultRecord.getMakeDate() + saisonResultRecord.getMakeTime());

            if (checkFlg) {
                createTime = MyUtil.convDate(saisonResultRecord.getMakeDate(), saisonResultRecord.getMakeTime());
                if (createTime == null) {
                    createTime = judgeMainCreateTime;
                }
                updateTime = MyUtil.convDate(saisonResultRecord.getMakeDate(), saisonResultRecord.getMakeTime());
                if (updateTime == null) {
                    updateTime = judgeMainUpdateTime;
                }
                shinsakekka.set作成日時(createTime);
                shinsakekka.set更新日時(updateTime);
                shinsakekka.set申込番号(requestNo);
                shinsakekka.set申込目的(moushikomiMokuteki);
                shinsakekka.setイベント("審査結果");
                shinsakekka.setイベント日時(MyUtil.convDate(saisonResultRecord.getMakeDate(), saisonResultRecord.getMakeTime()));
                shinsakekka.set区分("2");
                shinsakekka.set審査段階("一次保証審査");
                shinsakekka.set審査結果(hosyoShinsaKekka);
                shinsakekka.set承認商品コード(shohinCd);
                shinsakekka.set承認借入金額(MyUtil.convLong(saisonResultRecord.getOkGaku()));
                shinsakekka.setコメント(saisonResultRecord.getApplyBiko());
                shinsakekka.set結果日付(MyUtil.convString(saisonResultRecord.getAcceptDate()));

                if (saisonResultRecord.getHosyouNo() != null) {
                    shinsakekka.set外部保証番号(MyUtil.substringByByte(saisonResultRecord.getHosyouNo(), 10));
                } else {
                    shinsakekka.set外部保証番号(MyUtil.substringByByte(judgeMainRecord.getGuarantNo(), 10));
                }
                if (eHosyokaisyaCd != null) {
                    shinsakekka.set保証会社コード(eHosyokaisyaCd.getNewCode());
                    shinsakekka.set保証会社名(eHosyokaisyaCd.getNewName());
                }
                shinsakekkaList.add(shinsakekka);
            }
        }

        logger.debug("convShinsakekka end");
        return shinsakekkaList;
    }

    /**
     * 申込審査状況へ変換する
     *
     * @param judgeMainRecord       審査状況
     * @param requestNo             登録する申込書番号
     * @param moushikomiMokuteki    登録する申込目的
     * @return
     */
    private 審査コメント convShinsaComment(VJudgeMain judgeMainRecord, String requestNo,
            String moushikomiMokuteki, VJudgeGuarant vJudgeGuarant) {

        審査コメント shinsaComment = null;

        Timestamp judgeMainCreateTime = MyUtil.convDate(judgeMainRecord.getCreateDate(), judgeMainRecord.getCreateTime());
        if (judgeMainCreateTime == null) {
            judgeMainCreateTime = MyUtil.convDate(judgeMainRecord.getLastUpDate(), judgeMainRecord.getLastUpTime());
        }
        Timestamp judgeMainUpdateTime = MyUtil.convDate(judgeMainRecord.getUpdateDate(), judgeMainRecord.getUpdateTime());
        if (judgeMainUpdateTime == null) {
            judgeMainUpdateTime = MyUtil.convDate(judgeMainRecord.getLastUpDate(), judgeMainRecord.getLastUpTime());
        }
        Timestamp createTime = null;
        Timestamp updateTime = null;

        if (vJudgeGuarant != null) {
            shinsaComment = new 審査コメント();

            createTime = MyUtil.convDate(vJudgeGuarant.getUpdateDate(), vJudgeGuarant.getUpdateTime());
            if (createTime == null) {
                createTime = judgeMainCreateTime;
            }
            updateTime = MyUtil.convDate(vJudgeGuarant.getUpdateDate(), vJudgeGuarant.getUpdateTime());
            if (updateTime == null) {
                updateTime = judgeMainUpdateTime;
            }

            shinsaComment.set申込番号(requestNo);
            shinsaComment.set申込目的(moushikomiMokuteki);
            shinsaComment.set作成日時(createTime);
            shinsaComment.set更新日時(updateTime);
            shinsaComment.setイベント("審査コメント");
            shinsaComment.setイベント日時(updateTime);
            shinsaComment.set結果種類("情報");
            shinsaComment.setコメント(vJudgeGuarant.getGuarantOpinion());
        }
        return shinsaComment;
    }

    /**
     * 申込審査状況へ変換する
     *
     * @param judgeMainRecord       審査状況
     * @param requestNo             登録する申込書番号
     * @param moushikomiMokuteki    登録する申込目的
     * @return
     */
    private 審査コメント表示ユーザ区分 convShinsaCommentHyojiUserKubun(VJudgeMain judgeMainRecord, String requestNo,
            String moushikomiMokuteki, VJudgeGuarant vJudgeGuarant) {

        審査コメント表示ユーザ区分 shinsaCommentHyojiUserKubun = null;

        Timestamp judgeMainCreateTime = MyUtil.convDate(judgeMainRecord.getCreateDate(), judgeMainRecord.getCreateTime());
        if (judgeMainCreateTime == null) {
            judgeMainCreateTime = MyUtil.convDate(judgeMainRecord.getLastUpDate(), judgeMainRecord.getLastUpTime());
        }
        Timestamp judgeMainUpdateTime = MyUtil.convDate(judgeMainRecord.getUpdateDate(), judgeMainRecord.getUpdateTime());
        if (judgeMainUpdateTime == null) {
            judgeMainUpdateTime = MyUtil.convDate(judgeMainRecord.getLastUpDate(), judgeMainRecord.getLastUpTime());
        }
        Timestamp createTime = null;
        Timestamp updateTime = null;

        if (vJudgeGuarant != null) {
            shinsaCommentHyojiUserKubun = new 審査コメント表示ユーザ区分();
            createTime = MyUtil.convDate(vJudgeGuarant.getUpdateDate(), vJudgeGuarant.getUpdateTime());
            if (createTime == null) {
                createTime = judgeMainCreateTime;
            }
            updateTime = MyUtil.convDate(vJudgeGuarant.getUpdateDate(), vJudgeGuarant.getUpdateTime());
            if (updateTime == null) {
                updateTime = judgeMainUpdateTime;
            }

            shinsaCommentHyojiUserKubun.set申込番号(requestNo);
            shinsaCommentHyojiUserKubun.set申込目的(moushikomiMokuteki);
            shinsaCommentHyojiUserKubun.setイベント("審査コメント");
            shinsaCommentHyojiUserKubun.setイベント日時(updateTime);
            shinsaCommentHyojiUserKubun.set作成日時(createTime);
            shinsaCommentHyojiUserKubun.set更新日時(updateTime);
            shinsaCommentHyojiUserKubun.setユーザ区分("2");
        }
        return shinsaCommentHyojiUserKubun;
    }

    /**
     * 申込顛末管理へ変換する
     *
     * @param vJudgeMain    審査状況
     * @param requestNo     登録する申込書番号
     * @return 申込顛末管理
     */
    private 申込顛末管理 convMoushikomiTenmatsukanri(VJudgeMain vJudgeMain, String requestNo) {

        if (vJudgeMain == null) {
            return null;
        }

        申込顛末管理 tenmatsu = new 申込顛末管理();

        String tenmatsukbn = "";
        try {
            E端末区分 eTenmatsukbn = E端末区分.fromOldCode(vJudgeMain.getLastUpRes());
            tenmatsukbn = eTenmatsukbn.getNewCode();
        } catch (IllegalArgumentException e) {
            logger.debug("[申込番号={} {}の{}が変換できませんでした。", requestNo, "E顛末区分", vJudgeMain.getLastUpRes());
        }

        Timestamp createDate = MyUtil.convDate(vJudgeMain.getCreateDate(), vJudgeMain.getCreateTime()) == null
                ? new Timestamp(System.currentTimeMillis())
                : MyUtil.convDate(vJudgeMain.getCreateDate(), vJudgeMain.getCreateTime());
        Timestamp updateDate = MyUtil.convDate(vJudgeMain.getUpdateDate(), vJudgeMain.getUpdateTime()) == null
                ? new Timestamp(System.currentTimeMillis())
                : MyUtil.convDate(vJudgeMain.getUpdateDate(), vJudgeMain.getUpdateTime());

        tenmatsu.set作成日時(createDate);
        tenmatsu.set更新日時(updateDate);
        tenmatsu.set申込番号(requestNo);
        tenmatsu.set顛末区分(tenmatsukbn);
        tenmatsu.set顛末状況コメント(vJudgeMain.getTenmatsuStatus());
        tenmatsu.set最終更新日時(updateDate);
        
        return tenmatsu;
    }

    /**
     * イメージを徴求資料のパスへ移動
     *
     * @param vImage    Vイメージ
     * @param destDir   出力先ディレクトリ
     */
    private void moveImage(Vイメージ vImage, String destDir) {
        Path destFile = Paths.get(destDir + vImage.getImagePath());
        Path destDirPath = destFile.getParent(); // ディレクトリ
        
        try {
            if (!Files.exists(vImage.getPath())) {
                logger.error("コピー元ファイルが存在しないかファイルではありません: {}", vImage.getPath());
                return;
            }
            if (!Files.exists(destDirPath)) {
                Files.createDirectories(destDirPath);
                logger.info("コピー先ディレクトリを作成しました: {}", destDirPath);
            }
            
            Files.copy(vImage.getPath(), destFile, StandardCopyOption.REPLACE_EXISTING);
            logger.info("ファイルをコピーしました: {} ⇒ {}", vImage.getPath(), destFile);
        } catch (IOException e) {
            logger.error("ファイルコピー中にエラーが発生しました: {} ⇒ {}", vImage.getPath(), destFile, e);
        } catch (Exception e) {
            logger.error("予期しないエラー: {} ⇒ {}", vImage.getPath(), destFile, e);
        }
    }

    /**
     * 進捗商品情報
     */
    static class 進捗商品情報 {
        String moshikomimokuteki;
        商品マスター shohinMaster;

        進捗商品情報(String moshikomimokuteki, 商品マスター shohinMaster) {
            this.moshikomimokuteki = moshikomimokuteki;
            this.shohinMaster = shohinMaster;
        }
    }
}
