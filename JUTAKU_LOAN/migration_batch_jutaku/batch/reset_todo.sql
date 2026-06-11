-- Reset all Jutaku (システム='J') ranges back to TODO so the parallel test can be re-run.
-- Run against the target schema (ITF_SMS) where 移行管理テーブル lives.
UPDATE 移行管理テーブル
   SET ステータス = 'TODO',
       開始日時   = NULL,
       終了日時   = NULL,
       備考       = NULL
 WHERE システム = 'J';
COMMIT;

-- Verify:
-- SELECT システム, 処理FROM, 処理TO, ステータス, 開始日時, 終了日時 FROM 移行管理テーブル WHERE システム='J' ORDER BY 処理FROM;
