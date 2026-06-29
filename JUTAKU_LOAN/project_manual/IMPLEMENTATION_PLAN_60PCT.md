# JUTAKU_LOAN Migration — Implementation Plan
## Goal: 60% Progress Presentation on July 22, 2026
## Implementation Deadline: July 17, 2026

---

## 1. Project Context

| Item | Detail |
|---|---|
| Source DB schema | SZB_SMS (old 住宅ローン審査システム, Oracle) |
| Target DB schema | ITF_SMS (new SCOPE system, Oracle) |
| Source tables in scope | 132 (marked 対象となる in (別紙)移行対象テーブル整理) |
| Target tables in scope | 137 (new system side) |
| Total column mappings | 5,731 rows in (別紙)カラムマッピング |
| Project timeline | 16 weeks: May 21 – Sep 10, 2026 |
| Service-in | October 2026 |
| Parallel execution | 15 batch instances (Oracle FOR UPDATE SKIP LOCKED) |

**Working days Jun 25 – Jul 17 (Jun 25 = Thursday):**

| Period | Days |
|---|---|
| Jun 25 (Thu) – Jun 26 (Fri) | 2 days |
| Jun 29 (Mon) – Jul 3 (Fri) | 5 days |
| Jul 6 (Mon) – Jul 10 (Fri) | 5 days |
| Jul 13 (Mon) – Jul 17 (Fri) | 5 days |
| **Total** | **17 working days** |

---

## 2. Migration Types

From the (別紙)テーブルマッピング sheet (Col G 難易度):

| Difficulty | Count | Description | What it means in code |
|---|---|---|---|
| 易 | ~84 (57%) | 単純移送 — same structure, direct copy | Simple: Source.java → Target.java → XML mappers → service insert |
| 中 | ~66 (38%) | 構造変換 (分割/統合), 他テーブル参照, 代替値設定 | Requires JOIN to another table, or split source into multiple targets |
| 難 | ~5 (5%) | 縦持ち↔横持ち conversion | Requires collecting multiple rows and pivoting |

**60% target = implement all 易 tables (~84) plus the 申込 group (already started, classified 中/難).**

---

## 3. How the Migration Batch Works

All table groups are driven by **申込番号** range from the 移行管理 table:

```
① Script creates 処理範囲レコード in 移行管理 (status=TODO)
② Batch picks up one range (status TODO→RUNNING)
③ For each 申込番号 in the range that matches 移行対象条件 (審査完了):
   → INSERT into all target tables for that 申込番号
④ Commit on success → DONE. Rollback on error → ERROR.
```

**Exception — tables that do NOT link to 申込番号:**
- **セットアップ** tables (属性、契約、貸出取引解消先、延滞中明細、延滞回収履歴): **全件移行** — separate bulk copy service, no filter
- **ログ** tables (担当者別操作管理、担当者別操作ログ、EUC操作ログ、WEB作業展歴): **保持期間内のデータ** — separate service with date range

All other 申込-linked tables are added to `JutakuLoanService.java` inside the existing `申込番号` loop.

---

## 4. How to Read the 詳細設計書_住宅ローンデータ移行.xlsx

### Sheet overview

| Sheet | When to use |
|---|---|
| (別紙)移行対象テーブル整理 | Confirm whether a source table is in scope (Col F = 対象となる) |
| (別紙)テーブルマッピング | Find source table(s) for each target table; check difficulty (Col G) and special notes (Col F) |
| (別紙)カラムマッピング | **Main reference while coding** — column-level mapping for every table |
| (別紙)編集仕様詳細 | Detailed conversion rules for complex columns referenced from Col N |
| 処理概要 | Migration criteria (which data states are in scope) |
| 処理詳細 | Special processing rules per table group (e.g., 申込・申込履歴の対応) |
| 課題管理 | Open questions — check before coding any yellow-highlighted column |

### Column layout of (別紙)カラムマッピング

```
新システム（新） side              移行元システム（旧） side
────────────────────────────────  ──────────────────────────────────────
Col C: テーブル名 (target table)  Col H: テーブル名 (source table)
Col D: 項目名 (target column)     Col I: 項目名 (source column)
Col E: データ型                   Col J: データ型
Col F: 桁数                       Col K: 桁数
Col G: 必須 (○ = NOT NULL)        Col L: 必須
                                  Col M: 書式/コード値 (code mappings)
                                  Col N: 特記事項 / 《》【】 references
```

### Rules for each column row

| Col G (必須) | Col H (source table) | What to do |
|---|---|---|
| ○ | filled | **Must implement** — NOT NULL, must have a value |
| ○ | empty | **Must research** — check Col N, 処理詳細, or 課題管理 for how to get a value |
| empty | filled | Implement — nullable, but should be mapped |
| empty | empty | **Skip** — leave NULL (no source data, no rule) |

**Yellow cells** in Col M or Col N = not yet decided. Check 課題管理 sheet, or ask Oida.

### Col M / Col N common patterns

| Pattern | Meaning | How to implement |
|---|---|---|
| Code list (e.g. 10:事前審査, 15:事前再審査) | Old code → new code conversion | `if/switch` in service, or enum class |
| `《テーブル名:項目名》` | References a new system field for cross-check | Read (別紙)編集仕様詳細 |
| `【テーブル名:項目名】` | References an old system field | Use as source in SourceMapper.xml |
| `(別紙)編集仕様詳細「X」参照` | Complex rule in separate sheet | Open 編集仕様詳細, find section X |
| `宅建業者コードによる販売業者マスターの参照` | JOIN to 販売業者マスター | Add JOIN in SourceMapper.xml |
| `全件を移行対象とする` | No 審査完了 filter | Bulk copy — separate service |
| `保持期間内のデータを対象とする` | Date range filter | Separate service with date condition |

---

## 5. Current Implementation Status (as of June 25)

### Infrastructure (done — do not re-implement)
- Parallel batch framework (15 instances, FOR UPDATE SKIP LOCKED) ✅
- 移行管理 table processing (TODO → RUNNING → DONE/ERROR) ✅
- 申込番号 conversion (first digit 2 → 3) ✅
- 申込目的 grouping (10/15 → 事前 new='10', 20/30 → 正式 new='20') ✅
- Main vs history split pattern ✅

### Tables with code (partial — columns need filling)

| Target table | Type | Status | What is missing |
|---|---|---|---|
| 申込進捗 | Main | ✅ Full | — |
| 申込 | Main | ⬜ PKs only | All columns except 申込番号、申込目的 |
| 申込審査段階 | Main | 🟡 Partial | All columns except 申込番号、申込目的、審査完了区分 |
| 保証人 | Main | ⬜ PKs only | All columns except 申込番号、申込目的、連番 |
| 申込＿業者＿住宅 | Main | ⬜ PKs only | All columns except 申込番号、申込目的 |
| 保証検討表補足 | Main | ⬜ PKs only | All columns except 申込番号、申込目的 |
| 申込審査状況 | History parent | ⬜ PKs only | All columns except 申込番号、申込目的、回数 |
| 履歴申込 | History | ⬜ PKs only | All columns except 申込番号、申込目的、回数 |
| 履歴申込審査段階 | History | 🟡 Partial | All columns except 申込番号、申込目的、回数、審査完了区分 |
| 履歴保証人 | History | ⬜ PKs only | All columns except 申込番号、申込目的、回数、連番 |
| 履歴申込＿業者＿住宅 | History | ⬜ PKs only | All columns except 申込番号、申込目的、回数 |
| 履歴保証検討表補足 | History | ✅ Full | — |

### Not yet started (0 source tables coded outside 申込 group)
132 source tables total — only the 申込 group (9 sources) is partially coded.

---

## 6. How to Implement a Table — Step-by-Step

### Pattern A — Simple (易) table, direct column copy

**Step 1: Read テーブルマッピング**
- Open (別紙)テーブルマッピング
- Find the target table row → note source table name in Col E, confirm difficulty 易 in Col G

**Step 2: Read カラムマッピング**
- Open (別紙)カラムマッピング, filter Col C by the target table name
- For each row: note target column (Col D), source table (Col H), source column (Col I), 必須 ○ (Col G), code rules (Col M)
- Count how many 必須 ○ columns have NO source mapping → these need special handling

**Step 3: Create SourceMapper.xml**
```xml
<select id="selectByApplicationId" parameterType="String"
        resultType="migration.domain.source.XxxSource">
    SELECT
        col1, col2, col3   -- only columns that map to target
    FROM source_table
    WHERE 申込番号 = #{申込番号}
    -- add AND 申込目的 = #{申込目的} if table has 申込目的 column
</select>
```

**Step 4: Create Source.java domain**
```java
public class XxxSource {
    private String 申込番号;
    private String col1;
    // getter/setter for each field
}
```

**Step 5: Create TargetMapper.xml**
```xml
<insert id="insert" parameterType="migration.domain.target.XxxTarget">
    INSERT INTO ターゲットテーブル名 (
        申込番号, 申込目的, col1, col2
    ) VALUES (
        #{申込番号}, #{申込目的}, #{col1}, #{col2}
    )
</insert>
```

**Step 6: Create Target.java domain** — same pattern as Source, but with target columns

**Step 7: Create Mapper interfaces**
```java
// XxxSourceMapper.java
public interface XxxSourceMapper {
    XxxSource selectByApplicationId(String 申込番号);
    // or List<XxxSource> if multiple rows per 申込番号
}

// XxxTargetMapper.java
public interface XxxTargetMapper {
    int insert(XxxTarget target);
}
```

**Step 8: Add to JutakuLoanService.java**
```java
// In processGroup() or processOneRange() depending on whether table
// has 申込目的 or not:
XxxSource src = xxxSourceMapper.selectByApplicationId(srcNo);
if (src != null) {
    XxxTarget tgt = new XxxTarget();
    tgt.set申込番号(tgtNo);
    tgt.set申込目的(newMokuteki);   // if table has 申込目的
    tgt.setCol1(src.getCol1());
    // apply code conversion if Col M has mapping rules
    xxxTargetMapper.insert(tgt);
}
```

**Step 9: Register @Autowired in JutakuLoanService**
```java
@Autowired private XxxSourceMapper xxxSourceMapper;
@Autowired private XxxTargetMapper xxxTargetMapper;
```

---

### Pattern B — Hard (中/難) table: split (main + history)

This is already implemented for the 申込 group. The pattern is:

- **Main table**: insert MAX 申込目的 record only (once per group)
- **History table**: insert once per 回数 (one per completed stage in the group)
- History has 回数 as an extra PK column
- FK order must be respected: parent must be inserted before child

Reference: `JutakuLoanService.processGroup()` — this is the template.  
See also: 処理概要 sheet → 「(2)データ移行方式 - DBデータ - 申込・申込履歴の対応」

---

### Pattern C — Bulk copy table (全件移行, no 申込番号 filter)

For **セットアップ** tables (属性、契約 etc.):
- Create a separate service class (e.g., `セットアップMigrationService.java`)
- Driving key: row number range (ROW_NUMBER() or ROWID range)
- No 審査完了 filter — migrate all rows
- SourceMapper: `SELECT * FROM source_table WHERE ROWNUM BETWEEN #{from} AND #{to}`
- TargetMapper: direct INSERT

---

### Pattern D — Log table (保持期間内データ)

For **ログ** tables (担当者別操作管理 etc.):
- Create a separate service class
- Driving key: date range or row range
- Filter: WHERE 登録日時 >= (current date - retention period)
- Ask Oida for exact retention period definition

---

## 7. Priority Order for Implementation

### Priority 1 — Complete 申込 group (already partially coded)
These are in `JutakuLoanService.java`. Fill in missing columns first before adding new table groups.

| Target table | Source table(s) | Difficulty | Key notes from テーブルマッピング |
|---|---|---|---|
| 申込 | 申込, 申込ワイド | 中 | 申込番号 2→3, 申込目的 conversion |
| 申込審査段階 | 申込審査段階 | 中 | 顧客未情報以外のマッピング |
| 保証人 | 保証人, 保証人ワイド | 中 | 構造変換(分割/統合) |
| 申込＿業者＿住宅 | 申込 + 販売業者マスター | 中 | JOIN: 宅建業者コード → 販売業者マスター |
| 保証検討表補足 | 保証検討表補足, 保証検討表所見 | 中 | 構造変換(分割/統合) |
| 申込審査状況 | 申込審査段階 | 中 | History parent, see 処理詳細 |
| 履歴申込 | 申込 | 中 | see 処理概要「申込・申込履歴の対応」 |
| 履歴申込審査段階 | 申込審査段階 | 中 | Same as 申込審査段階 + 回数 |
| 履歴保証人 | 保証人, 保証人ワイド | 中 | Same as 保証人 + 回数 |
| 履歴申込＿業者＿住宅 | 申込 + 販売業者マスター | 中 | Same JOIN + 回数 |

### Priority 2 — 易 table groups (tackle in order below)

For each group: check the difficulty column in (別紙)テーブルマッピング, confirm 易, then implement all tables in that group together since they share similar structure and the same source FK.

| Group | Source tables | Target tables | Notes |
|---|---|---|---|
| 審査判定 | 審査結果、審査コメント、審査結果事由コード、保証決裁進捗、条件履行確認事由、審査結果照会 | TBD from テーブルマッピング | Check which are 易 |
| 審査チェック | 審査チェック照会、審査チェック回答、審査チェック回答無効 | TBD | Likely 易 |
| 返済比率計算 | 返済比率計算、返済比率計算明細、返済比率計算結果、返済比率計算結果明細 | TBD | Likely 易 |
| スコアリング | 審査モデル照会、審査モデル照会基本、審査モデル回答、審査モデル回答明細、審査モデル回答判定 (+ S variants) | TBD | Likely 易 |
| システム判定 | システム判定照会、システム判定結果、システム判定結果明細 | TBD | Likely 易 |
| 住宅ローン不正検知 | 住宅ローン不正検知照会、住宅ローン不正検知結果 | TBD | Likely 易 |
| S-Navi連携 | 審査SNAVI連携、審査SNAVI連携内容 | TBD | Likely 易 |
| データ送信 | 審査データ送信 | TBD | Likely 易 |
| 書類作成 | 審査FAX送信、FAX送信 | TBD | Likely 易 |
| 契約書出力連携 | 審査契約書出力連携、審査契約書出力連携内容 | TBD | Likely 易 |
| セットアップ | 属性、契約、貸出取引解消先、延滞中明細、延滞回収履歴、実行管理 | TBD | 全件移行 — Pattern C |

### Priority 3 — Medium complexity groups

| Group | Source tables | Notes |
|---|---|---|
| 審査時情報 | 審査時情報取得、審査時属性、審査時契約、etc. (7 tables) | Verify difficulty in テーブルマッピング |
| 担保評価 | 担保評価照会、担保評価回答、etc. (5 tables) | MAX(イベント日時) selection per 申込番号 |
| KSC | 25 tables | Credit bureau data, verify if 易 or 中 |
| JICC / CIC | 2+2 tables | Likely 易 but verify |
| 外部信用情報 | 10 tables | Verify difficulty |
| 類似照会 | 3 tables | Verify difficulty |
| 外部連携 | 10 tables | Verify difficulty |
| 預保照会 | 5 tables | Verify difficulty |
| ログ | 4 tables | Pattern D — date range filter |

---

## 8. Week-by-Week Schedule

### Week 0 — Jun 25 (Thu) – Jun 26 (Fri): 2 days — Confirm setup + complete 申込 columns

**Jun 25 (Thu)**
1. Connect to VM, verify batch runs on existing test data without errors
2. Open (別紙)カラムマッピング → filter Col C = `申込` → list all 必須 columns and their source columns
3. Identify which columns in 申込Source.java and 申込Target.java are missing
4. Start filling in 申込Source.java and 申込SourceMapper.xml

**Jun 26 (Fri)**
1. Complete 申込Target.java and 申込TargetMapper.xml
2. Update `processGroup()` in JutakuLoanService to map all 申込 columns
3. Apply code conversions from Col M (e.g., 商品大分類: 1=住宅ローン, etc.)
4. Run batch on extract/ test data, verify 申込 rows have real column values (not just PKs)

---

### Week 1 — Jun 29 (Mon) – Jul 3 (Fri): 5 days — Complete 申込 group

**Jun 29 (Mon) — 申込審査段階 + 申込審査状況**
1. Filter カラムマッピング by `申込審査段階` → add missing columns to 申込審査段階Source/Target
2. Filter by `申込審査状況` → implement all columns for this history parent table
3. Update service for both

**Jun 30 (Tue) — 保証人 + 履歴保証人**
1. Filter カラムマッピング by `保証人` → note: source is 保証人 + 保証人ワイド (see テーブルマッピング)
2. Check テーブルマッピング Col F 特記事項 for 保証人 → implement any JOIN or merge logic
3. Implement same columns for 履歴保証人 (add 回数)
4. Update service

**Jul 1 (Wed) — 保証検討表補足 + 履歴申込**
1. Filter by `保証検討表補足` → source is 保証検討表補足 + 保証検討表所見 (verify in テーブルマッピング)
2. Add all columns to main 保証検討表補足Target
3. Filter by `履歴申込` → same source as 申込 + 回数
4. Add all columns to 履歴申込Target

**Jul 2 (Thu) — 申込＿業者＿住宅 + 履歴申込＿業者＿住宅**
1. Confirm columns from カラムマッピング (filter `申込＿業者＿住宅` with full-width ＿)
2. Source: 申込 JOIN 販売業者マスター ON 申込.宅建業者コード = 販売業者マスター.販売業者コード
3. Implement JOIN in 申込_業者_住宅SourceMapper.xml
4. Same for 履歴 version (add 回数)

**Jul 3 (Fri) — 履歴申込審査段階 + integration test of 申込 group**
1. Complete 履歴申込審査段階 columns (same source as 申込審査段階 + 回数)
2. Run batch on full TestData → verify all 申込 group tables have real column values
3. Fix any NULL violations (必須 ○ columns must never be NULL)

---

### Week 2 — Jul 6 (Mon) – Jul 10 (Fri): 5 days — 易 table groups

**Jul 6 (Mon) — Check テーブルマッピング for 易 groups**
1. Open (別紙)テーブルマッピング, filter by 難易度 = 易
2. List all 易 target tables and their source tables
3. Group by データ内容 (same group = similar structure = faster to code together)
4. Pick 3 groups for this week based on table count and similarity

**Jul 7 (Tue) – Jul 9 (Thu) — Implement 易 groups (3 days)**
- For each group: follow Pattern A (Steps 1–9 above)
- Aim: 2–3 tables per day (each table ~2–3 hours: read カラムマッピング → code → test)
- Commit after each table: `feat: implement migration for [テーブル名]`

**Jul 10 (Fri) — Test + buffer for fixes**
1. Run full batch with all 易 tables added
2. Verify row counts in each target table
3. Fix any missing 必須 columns

---

### Week 3 — Jul 13 (Mon) – Jul 17 (Fri): 5 days — More 易 groups + final test

**Jul 13 (Mon) – Jul 15 (Wed) — Continue 易 groups**
- Continue implementing remaining 易 table groups
- Same Pattern A approach
- If any group turns out to be 中 (harder than expected), check テーブルマッピング 特記事項 and 処理詳細 sheet

**Jul 16 (Thu) — Full integration test**
1. Run batch with all implemented tables on full TestData
2. Run count queries for all target tables
3. Spot-check column values source vs target

**Jul 17 (Fri) — Demo prep**
1. Write the verification query showing migrated counts per table group
2. Prepare before/after comparison SQL for the presentation demo
3. Note clearly which table groups are done vs pending

---

## 9. What to Show at the July 22 Presentation

**Target by July 17:**
- 申込 group (~12 target tables): all columns mapped ✅
- 易 table groups: ~60–70 target tables fully implemented
- Batch running with 15 parallel instances, no errors

**Demo queries to prepare:**

```sql
-- Count per table group (run in target DB)
SELECT '申込' grp, COUNT(*) n FROM 申込
UNION ALL SELECT '申込審査段階', COUNT(*) FROM 申込審査段階
UNION ALL SELECT '保証人', COUNT(*) FROM 保証人
UNION ALL SELECT '申込＿業者＿住宅', COUNT(*) FROM "申込＿業者＿住宅"
-- ... add all implemented tables
ORDER BY 1;

-- Source vs target comparison for 申込番号 conversion check
SELECT src.申込番号 src_no, tgt.申込番号 tgt_no, tgt.申込目的
FROM SZB_SMS.申込 src
JOIN ITF_SMS.申込 tgt ON '3' || SUBSTR(src.申込番号, 2) = tgt.申込番号
WHERE ROWNUM <= 5;
```

---

## 10. Files Reference

| File | Purpose |
|---|---|
| TestData/SZB_SMS.申込.sql | Source test data for 申込 tables |
| TestData/SZB_SMS.保証人.sql | Source test data for 保証人 |
| TestData/ITF_SMS.移行管理テーブル.sql | Load migration management table |
| TestData/extract/*.sql | Small dataset — use for fast test cycles |
| TestData/log/確認用.sql | Verification queries after each migration run |
| 詳細設計書_住宅ローンデータ移行.xlsx → (別紙)移行対象テーブル整理 | Confirm table is in scope (Col F = 対象となる) |
| 詳細設計書_住宅ローンデータ移行.xlsx → (別紙)テーブルマッピング | Source table, difficulty, special notes for each target table |
| 詳細設計書_住宅ローンデータ移行.xlsx → (別紙)カラムマッピング | **Open this for every table you implement** |
| 詳細設計書_住宅ローンデータ移行.xlsx → (別紙)編集仕様詳細 | Complex conversion rules referenced from カラムマッピング Col N |
| 詳細設計書_住宅ローンデータ移行.xlsx → 処理詳細 | Special processing logic for 申込・履歴 split pattern |
| 詳細設計書_住宅ローンデータ移行.xlsx → 課題管理 | Open items — check before coding yellow-highlighted columns |

---

## 11. Key Rules

1. **必須 ○ in Col G** = NOT NULL in target DB → must have a value or batch will throw a constraint error
2. **Col H empty** = no source mapping → leave NULL, unless Col N or 処理詳細 says otherwise
3. **Yellow cells** in カラムマッピング = not decided → skip for now, come back after confirming with Oida
4. **Code conversions** (Col M) → implement in Java service, not in XML
5. **Test on extract/ first**, full TestData only for final verification
6. **Commit after each table** → `feat: implement migration for [テーブル名]`
7. **Full-width underscores** in Oracle: 申込＿業者＿住宅 uses ＿ (U+FF3F) → must double-quote in SQL: `"申込＿業者＿住宅"`
