-- ============================================================
-- Cleanup of JUTAKU_LOAN migration test data (target schema)
-- ------------------------------------------------------------
-- Deletes ONLY rows inserted by this migration, identified by the
-- converted 申込番号 (2->3 conversion => our rows start with '3').
--
-- Every table is qualified with the ITF_SMS (target) schema so this
-- script works no matter which connection is active in A5:SQL.
-- The two 業者 tables use full-width underscores (U+FF3F) and must be
-- double-quoted, exactly as their INSERT mappers do.
--
-- !!! WARNING: these target tables are SHARED with other projects.
--     Confirm the WHERE filter matches ONLY your test data before running.
--     If other projects also use '3...' numbers, narrow the prefix
--     (e.g. '3026%') or use an explicit 申込番号 IN (...) list.
--
-- Order = children first (FK-safe). Run top to bottom, then COMMIT.
-- ============================================================

-- history detail (FK -> 履歴申込)
DELETE FROM ITF_SMS."履歴申込＿業者＿住宅" WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.履歴申込審査段階       WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.履歴保証人             WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.履歴保証検討表補足     WHERE 申込番号 LIKE '3%';

-- history parent (FK -> 申込審査状況)
DELETE FROM ITF_SMS.履歴申込               WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.申込審査状況           WHERE 申込番号 LIKE '3%';

-- 申込 children (FK -> 申込)
DELETE FROM ITF_SMS."申込＿業者＿住宅"      WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.申込審査段階           WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.保証人                 WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.保証検討表補足         WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.申込担保情報ＰＤＦ      WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.申込審査履歴           WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.申込関連申込           WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査チェック照会       WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査ＫＳＣ照会         WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査ＫＳＣ信用情報     WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査ＫＳＣ信用情報明細 WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査ＫＳＣ信用情報詳細 WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査ＪＩＣＣ照会       WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査ＣＩＣ照会         WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.個信類似照会管理       WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.個信類似照会明細       WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.個信類似明細           WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査ＪＩＣＣ信用情報詳細 WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査ＣＩＣ信用情報詳細 WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.個信データ編集管理     WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.返済比率計算           WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.返済比率計算結果       WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.返済比率計算結果明細   WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査モデル回答         WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査モデル回答Ｓ       WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査モデル回答判定     WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査モデル回答判定Ｓ   WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査モデル回答明細     WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS.審査モデル回答明細Ｓ   WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS."ＩＦ＿担保評価連携結果"        WHERE 申込番号 LIKE '3%';
DELETE FROM ITF_SMS."ＩＦ＿担保評価連携結果＿ファイル" WHERE 申込番号 LIKE '3%';

-- 申込 parent
DELETE FROM ITF_SMS.申込                   WHERE 申込番号 LIKE '3%';

-- independent (driving table copy)
DELETE FROM ITF_SMS.申込進捗               WHERE 申込番号 LIKE '3%';

COMMIT;
