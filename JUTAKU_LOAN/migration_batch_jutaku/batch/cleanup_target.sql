-- ============================================================
-- Cleanup of JUTAKU_LOAN migration test data (target schema)
-- ------------------------------------------------------------
-- Deletes ONLY rows inserted by this migration, identified by the
-- converted 申込番号 (2→3 conversion => our rows start with '3').
--
-- !!! WARNING: these target tables are SHARED with other projects.
--     Confirm the WHERE filter matches ONLY your test data before running.
--     If other projects also use '3...' numbers, narrow the prefix
--     (e.g. '3026%') or use an explicit 申込番号 IN (...) list.
--
-- Order = children first (FK-safe). Run top to bottom.
-- ============================================================

-- history detail (FK -> 履歴申込)
DELETE FROM "履歴申込＿業者＿住宅" WHERE 申込番号 LIKE '3%';
DELETE FROM 履歴申込審査段階        WHERE 申込番号 LIKE '3%';
DELETE FROM 履歴保証人              WHERE 申込番号 LIKE '3%';
DELETE FROM 履歴保証検討表補足      WHERE 申込番号 LIKE '3%';

-- history parent (FK -> 申込審査状況)
DELETE FROM 履歴申込                WHERE 申込番号 LIKE '3%';
DELETE FROM 申込審査状況            WHERE 申込番号 LIKE '3%';

-- 申込 children (FK -> 申込)
DELETE FROM "申込＿業者＿住宅"       WHERE 申込番号 LIKE '3%';
DELETE FROM 申込審査段階            WHERE 申込番号 LIKE '3%';
DELETE FROM 保証人                  WHERE 申込番号 LIKE '3%';
DELETE FROM 保証検討表補足          WHERE 申込番号 LIKE '3%';
DELETE FROM 申込担保情報ＰＤＦ       WHERE 申込番号 LIKE '3%';
DELETE FROM 申込審査履歴            WHERE 申込番号 LIKE '3%';
DELETE FROM 申込関連申込            WHERE 申込番号 LIKE '3%';

-- 申込 parent
DELETE FROM 申込                    WHERE 申込番号 LIKE '3%';

-- independent (driving table copy)
DELETE FROM 申込進捗                WHERE 申込番号 LIKE '3%';

COMMIT;
