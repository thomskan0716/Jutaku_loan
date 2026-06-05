-- ============================================================
-- 移行管理テーブル DDL
-- Jutaku Loan Project
-- ============================================================
-- 
-- Purpose: Track individual application records for parallel processing
-- Design: Individual record approach (not range-based)
--
-- Status Flow: TODO -> RUNNING -> DONE/ERROR
--
-- Step 1 (Target Extraction): Inserts all target records with STATUS='TODO'
-- Step 2 (Migration): Multiple processes claim TODO records using FOR UPDATE SKIP LOCKED
--
-- ============================================================

CREATE TABLE 移行管理_JUTAKU (
    -- Primary Key
    申込番号 VARCHAR2(20) NOT NULL,
    
    -- Application Purpose
    申込目的 NUMBER(2),
    
    -- Status: TODO, RUNNING, DONE, ERROR
    ステータス VARCHAR2(10) NOT NULL,
    
    -- Processing timestamps
    処理開始日時 TIMESTAMP,
    処理終了日時 TIMESTAMP,
    
    -- Process identifier for parallel processing tracking
    処理プロセスID VARCHAR2(50),
    
    -- Constraints
    CONSTRAINT PK_移行管理_JUTAKU PRIMARY KEY (申込番号),
    CONSTRAINT CHK_STATUS_JUTAKU CHECK (ステータス IN ('TODO', 'RUNNING', 'DONE', 'ERROR'))
);

-- Index for fast TODO queries
CREATE INDEX IDX_移行管理_STATUS_JUTAKU ON 移行管理_JUTAKU(ステータス, 申込番号);

-- ============================================================
-- Sample Test Data
-- ============================================================
-- Insert 1000 test records:

/*
DECLARE
    v_moshikomi_bango VARCHAR2(20);
BEGIN
    FOR i IN 1..1000 LOOP
        v_moshikomi_bango := LPAD(TO_CHAR(i), 12, '0');
        
        INSERT INTO 移行管理_JUTAKU (
            申込番号, 申込目的, ステータス
        ) VALUES (
            v_moshikomi_bango, 1, 'TODO'
        );
    END LOOP;
    
    COMMIT;
END;
/
*/

-- ============================================================
-- Monitoring Queries
-- ============================================================

-- Status summary
/*
SELECT ステータス, COUNT(*) as 件数
FROM 移行管理_JUTAKU
GROUP BY ステータス;
*/

-- Find stuck RUNNING records (running > 30 minutes)
/*
SELECT 
    申込番号,
    ステータス,
    処理開始日時,
    ROUND((SYSDATE - 処理開始日時) * 24 * 60, 2) as 経過分
FROM 移行管理_JUTAKU
WHERE ステータス = 'RUNNING'
  AND 処理開始日時 < SYSDATE - INTERVAL '30' MINUTE
ORDER BY 処理開始日時;
*/

-- View ERROR records
/*
SELECT 
    申込番号,
    処理開始日時,
    処理終了日時
FROM 移行管理_JUTAKU
WHERE ステータス = 'ERROR'
ORDER BY 処理開始日時 DESC;
*/

-- Processing rate (records per minute)
/*
SELECT 
    COUNT(*) as 完了件数,
    ROUND(COUNT(*) / ((SYSDATE - MIN(処理開始日時)) * 24 * 60), 2) as 件_分
FROM 移行管理_JUTAKU
WHERE ステータス = 'DONE';
*/

-- ============================================================
-- Development/Testing Queries
-- ============================================================

-- Reset for re-run (development only)
/*
UPDATE 移行管理_JUTAKU 
SET ステータス = 'TODO',
    処理開始日時 = NULL,
    処理終了日時 = NULL;
COMMIT;

-- Or reset only RUNNING records
UPDATE 移行管理_JUTAKU 
SET ステータス = 'TODO',
    処理開始日時 = NULL
WHERE ステータス = 'RUNNING';
COMMIT;
*/
