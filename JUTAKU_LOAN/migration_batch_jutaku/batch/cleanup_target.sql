-- ============================================================
-- Cleanup of JUTAKU_LOAN migration test data (target schema)
-- ------------------------------------------------------------
-- Deletes ONLY rows inserted by this migration, identified by the
-- converted 申込番号 (2->3 conversion => our rows start with '3').
--
-- Every table is qualified with the ITF_SMS (target) schema so this
-- script works no matter which connection is active in A5:SQL.
-- Tables with full-width underscore (U+FF3F) must be double-quoted.
--
-- !!! WARNING: these target tables are SHARED with other projects.
--     Confirm the WHERE filter matches ONLY your test data before running.
--     If other projects also use '3...' numbers, narrow the prefix
--     (e.g. '3026%') or use an explicit 申込番号 IN (...) list.
--
-- Order = children first (FK-safe). Bridge-keyed tables (受付番号 /
-- 照会依頼番号) are deleted WHILE the bridge row still exists, then
-- the bridge itself is deleted. Run top to bottom, then COMMIT.
-- ============================================================

-- ------------------------------------------------------------
-- KSC2 receipt-number-keyed tables (no 申込番号)
-- Delete via 受付番号 from 審査ＫＳＣ照会 WHILE that bridge still exists.
-- ------------------------------------------------------------
DELETE FROM ITF_SMS.ＫＳＣ２ＣＩＣ
 WHERE 受付番号 IN (SELECT 受付番号 FROM ITF_SMS.審査ＫＳＣ照会 WHERE 申込番号 LIKE '3%');
DELETE FROM ITF_SMS.ＫＳＣ２サービス状態エラー
 WHERE 受付番号 IN (SELECT 受付番号 FROM ITF_SMS.審査ＫＳＣ照会 WHERE 申込番号 LIKE '3%');
DELETE FROM ITF_SMS.ＫＳＣ２回答情報
 WHERE 受付番号 IN (SELECT 受付番号 FROM ITF_SMS.審査ＫＳＣ照会 WHERE 申込番号 LIKE '3%');
DELETE FROM ITF_SMS.ＫＳＣ２官報個人
 WHERE 受付番号 IN (SELECT 受付番号 FROM ITF_SMS.審査ＫＳＣ照会 WHERE 申込番号 LIKE '3%');
DELETE FROM ITF_SMS.ＫＳＣ２官報法人
 WHERE 受付番号 IN (SELECT 受付番号 FROM ITF_SMS.審査ＫＳＣ照会 WHERE 申込番号 LIKE '3%');
DELETE FROM ITF_SMS.ＫＳＣ２項目エラー
 WHERE 受付番号 IN (SELECT 受付番号 FROM ITF_SMS.審査ＫＳＣ照会 WHERE 申込番号 LIKE '3%');
DELETE FROM ITF_SMS.ＫＳＣ２自社取引
 WHERE 受付番号 IN (SELECT 受付番号 FROM ITF_SMS.審査ＫＳＣ照会 WHERE 申込番号 LIKE '3%');
DELETE FROM ITF_SMS.ＫＳＣ２自社取引属性
 WHERE 受付番号 IN (SELECT 受付番号 FROM ITF_SMS.審査ＫＳＣ照会 WHERE 申込番号 LIKE '3%');
DELETE FROM ITF_SMS.ＫＳＣ２自社照会記録
 WHERE 受付番号 IN (SELECT 受付番号 FROM ITF_SMS.審査ＫＳＣ照会 WHERE 申込番号 LIKE '3%');
DELETE FROM ITF_SMS.ＫＳＣ２自社正規化取引
 WHERE 受付番号 IN (SELECT 受付番号 FROM ITF_SMS.審査ＫＳＣ照会 WHERE 申込番号 LIKE '3%');
DELETE FROM ITF_SMS.ＫＳＣ２自社正規化取引属性
 WHERE 受付番号 IN (SELECT 受付番号 FROM ITF_SMS.審査ＫＳＣ照会 WHERE 申込番号 LIKE '3%');
DELETE FROM ITF_SMS.ＫＳＣ２自社正規化照会記録
 WHERE 受付番号 IN (SELECT 受付番号 FROM ITF_SMS.審査ＫＳＣ照会 WHERE 申込番号 LIKE '3%');
DELETE FROM ITF_SMS.ＫＳＣ２自社不渡
 WHERE 受付番号 IN (SELECT 受付番号 FROM ITF_SMS.審査ＫＳＣ照会 WHERE 申込番号 LIKE '3%');
DELETE FROM ITF_SMS.ＫＳＣ２取引
 WHERE 受付番号 IN (SELECT 受付番号 FROM ITF_SMS.審査ＫＳＣ照会 WHERE 申込番号 LIKE '3%');
DELETE FROM ITF_SMS.ＫＳＣ２取引属性
 WHERE 受付番号 IN (SELECT 受付番号 FROM ITF_SMS.審査ＫＳＣ照会 WHERE 申込番号 LIKE '3%');
DELETE FROM ITF_SMS.ＫＳＣ２取引停止個人
 WHERE 受付番号 IN (SELECT 受付番号 FROM ITF_SMS.審査ＫＳＣ照会 WHERE 申込番号 LIKE '3%');
DELETE FROM ITF_SMS.ＫＳＣ２受付管理
 WHERE 受付番号 IN (SELECT 受付番号 FROM ITF_SMS.審査ＫＳＣ照会 WHERE 申込番号 LIKE '3%');
DELETE FROM ITF_SMS.ＫＳＣ２照会記録
 WHERE 受付番号 IN (SELECT 受付番号 FROM ITF_SMS.審査ＫＳＣ照会 WHERE 申込番号 LIKE '3%');
DELETE FROM ITF_SMS.ＫＳＣ２全情連
 WHERE 受付番号 IN (SELECT 受付番号 FROM ITF_SMS.審査ＫＳＣ照会 WHERE 申込番号 LIKE '3%');
DELETE FROM ITF_SMS.ＫＳＣ２不渡
 WHERE 受付番号 IN (SELECT 受付番号 FROM ITF_SMS.審査ＫＳＣ照会 WHERE 申込番号 LIKE '3%');
DELETE FROM ITF_SMS.ＫＳＣ２本人申告
 WHERE 受付番号 IN (SELECT 受付番号 FROM ITF_SMS.審査ＫＳＣ照会 WHERE 申込番号 LIKE '3%');
DELETE FROM ITF_SMS.ＫＳＣ２本人申告属性
 WHERE 受付番号 IN (SELECT 受付番号 FROM ITF_SMS.審査ＫＳＣ照会 WHERE 申込番号 LIKE '3%');
-- ＫＳＣ照会管理 = FK parent of ＫＳＣ２ details: AFTER details, BEFORE bridge.
DELETE FROM ITF_SMS.ＫＳＣ照会管理
 WHERE 受付番号 IN (SELECT 受付番号 FROM ITF_SMS.審査ＫＳＣ照会 WHERE 申込番号 LIKE '3%');
-- ＫＳＣ２マスター = run-once master (no 申込番号/受付番号): full delete.
DELETE FROM ITF_SMS.ＫＳＣ２マスター;

-- ------------------------------------------------------------
-- History detail -> history parent
-- ------------------------------------------------------------
DELETE FROM ITF_SMS."履歴申込＿業者＿住宅" WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.履歴申込審査段階       WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.履歴保証人             WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.履歴保証検討表補足     WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.履歴申込               WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.申込審査状況           WHERE 申込番号 LIKE '3%';

-- ------------------------------------------------------------
-- 申込 children (FK -> 申込). Children first.
-- ------------------------------------------------------------
DELETE FROM ITF_SMS."申込＿業者＿住宅"      WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.申込決裁進捗           WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.申込審査段階           WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.保証人                 WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.保証検討表補足         WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.申込顛末管理           WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.申込担保情報ＰＤＦ      WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.申込担保情報           WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.申込関連申込           WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.実行管理               WHERE 申込番号 LIKE '3%';

-- Medium (Nakamura): 審査時* / 審査チェック回答* / 担保評価*
DELETE FROM ITF_SMS.審査時延滞明細         WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査時回収履歴         WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査時契約             WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査時情報取得         WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査時申込             WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査時属性             WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査チェック回答確認   WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査チェック回答       WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.担保評価連携イベント   WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS."ＩＦ＿担保評価連携結果＿ファイル" WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS."ＩＦ＿担保評価連携結果"        WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS."ＩＦ＿担保評価連携依頼"        WHERE 申込番号 LIKE '3%';

-- Credit-info children BEFORE their 照会 bridge
DELETE FROM ITF_SMS.審査ＫＳＣ信用情報詳細 WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査ＫＳＣ信用情報明細 WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査ＫＳＣ信用情報     WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査チェック照会       WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査ＫＳＣ照会         WHERE 申込番号 LIKE '3%';

-- JICC: credit children -> 照会管理 (via bridge) -> bridge
DELETE FROM ITF_SMS.審査ＪＩＣＣ信用情報詳細 WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査ＪＩＣＣ信用情報明細 WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査ＪＩＣＣ信用情報   WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.ＪＩＣＣ照会管理
 WHERE 受付番号 IN (SELECT 受付番号 FROM ITF_SMS.審査ＪＩＣＣ照会 WHERE 申込番号 LIKE '3%');
DELETE FROM ITF_SMS.審査ＪＩＣＣ照会       WHERE 申込番号 LIKE '3%';

-- CIC: credit children -> 照会管理 (via bridge) -> bridge
DELETE FROM ITF_SMS.審査ＣＩＣ信用情報詳細 WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査ＣＩＣ信用情報明細 WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査ＣＩＣ信用情報     WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.ＣＩＣ照会管理
 WHERE 受付番号 IN (SELECT 受付番号 FROM ITF_SMS.審査ＣＩＣ照会 WHERE 申込番号 LIKE '3%');
DELETE FROM ITF_SMS.審査ＣＩＣ照会         WHERE 申込番号 LIKE '3%';

DELETE FROM ITF_SMS.個信類似照会明細       WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.個信類似明細           WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.個信類似照会管理       WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.個信データ編集管理     WHERE 申込番号 LIKE '3%';

-- 返済比率: detail/result-detail BEFORE parents
DELETE FROM ITF_SMS.返済比率計算結果明細   WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.返済比率計算明細       WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.返済比率計算結果       WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.返済比率計算           WHERE 申込番号 LIKE '3%';

DELETE FROM ITF_SMS.審査モデル回答明細     WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査モデル回答明細Ｓ   WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査モデル回答判定     WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査モデル回答判定Ｓ   WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査モデル回答         WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査モデル回答Ｓ       WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査モデル照会基本     WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査モデル照会基本Ｓ   WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査モデル照会         WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査モデル照会Ｓ       WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.システム判定結果明細   WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.システム判定結果       WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.システム判定照会       WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.住宅ローン不正検知結果 WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.住宅ローン不正検知照会 WHERE 申込番号 LIKE '3%';

-- Medium (Nakamura): 審査結果* (No.217/218) + 審査コメント表示ユーザ区分 (No.223)
DELETE FROM ITF_SMS.審査結果事由           WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査結果               WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査コメント表示ユーザ区分 WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査結果照会           WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査コメント           WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査データ送信         WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査ＳＮＡＶＩ連携内容   WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査ＳＮＡＶＩ連携イベント WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS."ＩＦ＿契約書送信"              WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.契約書連携イベント     WHERE 申込番号 LIKE '3%';

-- 預保: bridge-keyed children WHILE 審査預保照会 still exists, then bridge
DELETE FROM ITF_SMS.預保照会イメージ情報
 WHERE 照会依頼番号 IN (SELECT 照会依頼番号 FROM ITF_SMS.審査預保照会 WHERE 申込番号 LIKE '3%');
DELETE FROM ITF_SMS.預保照会人情報
 WHERE 照会依頼番号 IN (SELECT 照会依頼番号 FROM ITF_SMS.審査預保照会 WHERE 申込番号 LIKE '3%');
DELETE FROM ITF_SMS.預保照会基本情報
 WHERE 照会依頼番号 IN (SELECT 照会依頼番号 FROM ITF_SMS.審査預保照会 WHERE 申込番号 LIKE '3%');
DELETE FROM ITF_SMS.預保照会管理
 WHERE 照会依頼番号 IN (SELECT 照会依頼番号 FROM ITF_SMS.審査預保照会 WHERE 申込番号 LIKE '3%');
DELETE FROM ITF_SMS.審査預保照会           WHERE 申込番号 LIKE '3%';

DELETE FROM ITF_SMS.保証結果融資条件じぶん WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.保証結果融資条件アルヒ WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.保証結果メインじぶん   WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.保証結果メインアルヒ   WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.担当者別操作管理       WHERE 申込番号 LIKE '3%';

-- 申込審査履歴 (bridge parent for some review events): after its children
DELETE FROM ITF_SMS.申込審査履歴           WHERE 申込番号 LIKE '3%';

-- 申込 parent
DELETE FROM ITF_SMS.申込                   WHERE 申込番号 LIKE '3%';

-- independent driving table
DELETE FROM ITF_SMS.申込進捗               WHERE 申込番号 LIKE '3%';

COMMIT;
