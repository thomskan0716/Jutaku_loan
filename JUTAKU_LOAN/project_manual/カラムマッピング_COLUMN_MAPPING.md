# Column Mapping (カラムマッピング / Column Mapping)

Durable transcription of the `(別紙)カラムマッピング` and `(別紙)移行対象テーブル整理`
sheets of `詳細設計書_住宅ローンデータ移行`. Chat images are **not** reloaded in later
sessions, so anything shared as an image must live here.

Documentation style: prose in English; table/column names stay Japanese with the
reading + English gloss on first use. Code values stay as-is. DB identifiers use
full-width digits (`０-９`) and full-width underscore (`＿`) per `column-naming.mdc`.

---

## KSC (信用情報 / Credit Information) group

### Scope — from `(別紙)移行対象テーブル整理`
All rows below are **対象となる (in scope)**. Source table names == target table names.
`審査ＫＳＣ照会` (No.98) is the **bridge** and is already implemented (service block ③-d2).

| No. | テーブル名 (Table) | Pattern |
|----:|---|---|
| 98  | 審査ＫＳＣ照会 (Review KSC Inquiry) — **bridge, done** | supplies 受付番号 |
| 99  | ＫＳＣ照会管理 (KSC Inquiry Mgmt) | A (受付番号) |
| 100 | ＫＳＣ２回答情報 | A |
| 101 | ＫＳＣ２取引 | A |
| 102 | ＫＳＣ２取引属性 | A |
| 103 | ＫＳＣ２取引停止個人 | A |
| 104 | ＫＳＣ２不渡 | A |
| 105 | ＫＳＣ２本人申告 | A |
| 106 | ＫＳＣ２本人申告属性 | A |
| 107 | ＫＳＣ２全情連 | A |
| 109 | ＫＳＣ２ＣＩＣ | A |
| 111 | ＫＳＣ２官報個人 | A |
| 112 | ＫＳＣ２官報法人 | A |
| 113 | ＫＳＣ２自社不渡 | A |
| 114 | ＫＳＣ２自社取引 | A |
| 115 | ＫＳＣ２自社取引属性 | A |
| 116 | ＫＳＣ２自社正規化取引 | A |
| 117 | ＫＳＣ２自社正規化取引属性 | A |
| 118 | ＫＳＣ２自社正規化照会記録 | A |
| 119 | ＫＳＣ２自社照会記録 | A |
| 120 | ＫＳＣ２項目エラー | A |
| 121 | ＫＳＣ２サービス状態エラー | A |
| 122 | ＫＳＣ２マスター (KSC2 Master) | **B (run-once master, コード番号 key)** |
| 123 | ＫＳＣ２受付管理 (KSC2 Reception Mgmt) | A (受付番号 parent) |
| 125 | ＫＳＣ２照会記録 | A |

Gaps in the No. sequence (108/110/124, and `ＫＳＣ２照会回答` / `ＫＳＣ２全債連回答`)
are 対象外 (新システム未使用) per `テーブルマッピング`.

### Migration patterns
- **Pattern A — 受付番号 bridge (per application).** Driven by the existing
  `申込進捗` loop. For each application, `審査ＫＳＣ照会` (already loaded as `reviewKscs`)
  yields `受付番号 (うけつけばんごう / reception number)`; select the KSC table BY
  `受付番号` and copy. `受付番号` is **1:N** per application (event log), so iterate.
  `受付番号` is copied **as-is** (it is a bureau number, unrelated to the 申込番号 2→3 rule).
- **Pattern B — run-once master.** `ＫＳＣ２マスター` has **no 受付番号 / 申込番号**; it is a
  code master keyed by `コード番号`. It must be copied **once** (full copy), outside the
  per-application loop.

### Audit columns not in the mapping sheet
The physical tables also carry `作成日時 (creation datetime)` and `更新日時 (update
datetime)` which are NOT listed in `(別紙)カラムマッピング`. They exist on both source and
target, so the generic same-name copy carries them through automatically (pass-through,
same as `申込決裁進捗`). No special handling needed.

### Coded columns
`(別紙)編集仕様詳細` contains KSC/CIC **審査項目コード (check-item code)** conversions
(e.g. `501:KSCネガ→801:KSCブラック`, `504:KSC照会件数→604`). Those belong to the
**審査チェック / 審査項目** columns, NOT (as far as verified) to these raw KSC2 bureau
tables. **TODO(編集仕様詳細): confirm per KSC2 column that none carry a coded value**
before treating a column as pass-through.

---

### ＫＳＣ２マスター (KSC2 Master) — No.122
Key: `コード番号`. Straight copy; target widths >= source (no truncation). Pattern B.

**Implemented** as `migrateＫＳＣ２マスター()`: run once before the per-application loop,
guarded by `countByExample == 0` so re-runs / later ranges do not duplicate. Columns are
copied via `copyLikeNamedProperties` (source and target share names). Cleanup is a full
`DELETE FROM ITF_SMS.ＫＳＣ２マスター`. TODO(architecture): relocate to a dedicated run-once
step once Amano decides the mechanism.

| 新 項目名 (Target) | 新 型 | 移行元 項目名 (Source) | 旧 型 | 必須 |
|---|---|---|---|:--:|
| コード番号 | VARCHAR2(3) | コード番号 | VARCHAR2(3) | ○ |
| コード | VARCHAR2(3) | コード | VARCHAR2(2) | ○ |
| 名称 | VARCHAR2(60) | 名称 | VARCHAR2(40) | |
| ブラック判断 | VARCHAR2(10) | ブラック判断 | VARCHAR2(10) | |
| カードローン判断 | VARCHAR2(12) | カードローン判断 | VARCHAR2(12) | |
| 有担保基準額 | NUMBER(8,0) | 有担保基準額 | NUMBER(8,0) | |
| 利率 | VARCHAR2(7) | 利率 | VARCHAR2(7) | |
| 延滞判断 | VARCHAR2(10) | 延滞判断 | VARCHAR2(10) | |

---

### ＫＳＣ２ＣＩＣ — No.109
Key: `受付番号` + `該当者通番` (both 必須). Straight copy (all names identical, source
type == target type). Pattern A.

| 新 項目名 (Target) | 型 | 移行元 項目名 (Source) | 必須 |
|---|---|---|:--:|
| 受付日時 | DATE | 受付日時 | ○ |
| 受付番号 | CHAR(12) | 受付番号 | ○ |
| 回答種別コード | CHAR(2) | 回答種別コード | |
| 該当者通番 | CHAR(4) | 該当者通番 | ○ |
| 氏名カナ | CHAR(25) | 氏名カナ | |
| 氏名漢字 | CHAR(40) | 氏名漢字 | |
| 生年月日 | CHAR(8) | 生年月日 | |
| 性別 | CHAR(1) | 性別 | |
| 郵便番号 | CHAR(7) | 郵便番号 | |
| 住所カナ | CHAR(75) | 住所カナ | |
| 住所漢字 | CHAR(100) | 住所漢字 | |
| 本人電話番号 | CHAR(14) | 本人電話番号 | |
| 勤務先名カナ | CHAR(30) | 勤務先名カナ | |
| 勤務先電話番号 | CHAR(14) | 勤務先電話番号 | |
| 取引種類 | CHAR(2) | 取引種類 | |
| 成約日実行日 | CHAR(8) | 成約日実行日 | |
| 限度額当初貸出額 | CHAR(7) | 限度額当初貸出額 | |
| 支払回数 | CHAR(3) | 支払回数 | |
| 残債額 | CHAR(7) | 残債額 | |
| ... (No.2264 region not captured) ... | | | |
| 理由 | CHAR(2) | 理由 | |
| 事故日 | CHAR(8) | 事故日 | |
| 延滞月数 | CHAR(2) | 延滞月数 | |
| 苦情受付コード | CHAR(1) | 苦情受付コード | |
| 本人申告区分 | VARCHAR2(3) | 本人申告区分 | |
| 登録日 | CHAR(8) | 登録日 | |
| ＭＩＣＳ更新日時 | DATE | ＭＩＣＳ更新日時 | |

---

### ＫＳＣ２回答情報 (KSC2 Answer Info) — No.100
Key: `受付番号` (1:N per 受付番号). **~154 columns, every one identical source→target**
(names + types; target widths >= source, incl. CHAR→VARCHAR2 widening on a few like
`会員コード`, `生年月日`). Required (○): `受付日時`, `受付番号`. Implemented via the generic
same-name copy — no per-column list is transcribed here because it is a pure 1:1 pass-through.
Column families: 基本 (受付/利用者/会員/店/顧客), 主債務者*, 訂正後*, 官報情報(個人/法人)*,
自社*, 全情連情報*, ＣＩＣ情報*, 照会記録*, and many `*回答件数` / `*打切区分` / `*予備N` counters.
No coded conversion (編集仕様詳細 has no rows for this table). Pattern A.

### ＫＳＣ２官報個人 (KSC2 Gazette Individual) — No.111 / ＫＳＣ２官報法人 (Corporate) — No.112
Key: `受付番号` + `該当者通番` (both ○, plus `受付日時` ○). Straight 1:1 copy (all CHAR,
target width >= source). 個人 has 氏名N漢字/注記; 法人 has 法人名N漢字/注記 + 設立生年月日;
otherwise same shape (住所N漢字/注記, 官報公告項目, 事故発生日, 事件番号, 掲載日, 回答時刻,
ＭＩＣＳ更新日時). Note `回答時刻` is VARCHAR2(6) in source, CHAR(6) in target (String, safe).
No coded conversion. Implemented via generic same-name copy. Pattern A.

### ＫＳＣ２サービス状態エラー — No.121
Key: `受付番号`. Straight copy. Pattern A.

| 新 項目名 (Target) | 型 | 移行元 項目名 (Source) | 必須 |
|---|---|---|:--:|
| 受付日時 | DATE | 受付日時 | ○ |
| 受付番号 | CHAR(12) | 受付番号 | ○ |
| 依頼レコード種別 | CHAR(2) | 依頼レコード種別 | |
| センターサービス | CHAR(1) | センターサービス | |
| 全情連サービス | CHAR(1) | 全情連サービス | |
| ＣＩＣサービス | CHAR(1) | ＣＩＣサービス | |
| 照会 | CHAR(1) | 照会 | |
| ＭＩＣＳ更新日時 | DATE | ＭＩＣＳ更新日時 | |
