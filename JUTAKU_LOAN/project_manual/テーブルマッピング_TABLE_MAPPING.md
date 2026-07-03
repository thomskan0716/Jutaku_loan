# テーブルマッピング (てーぶるまっぴんぐ / Table Mapping) — In-Scope Reference

Transcribed from the `(別紙)テーブルマッピング` sheet of `詳細設計書_住宅ローンデータ移行`
(552 rows, shared as screenshots). This file lists **in-scope** target←source table mappings only;
the exhaustive 552-row list (incl. 対象外) lives in the source screenshots and in `JUTAKU_LOAN_PLAN.xlsx`.

## Sheet structure & scope rule
- Col A = データ内容 (Data Content), B = No., C = 新システム (new) table (grey = 新システム未使用 / out of scope),
  E = 移行元システム (old) table (`-` = no source / out of scope), 備考 = 編集仕様・参照ルール.
- Right edge: 難易度 (difficulty: 簡/中/難) and 変換種別 (単純移送 / 構造変換 / 必須項目補完).
- **A row is in scope only when col E has a source table.** Most マスター / システム定義 / `IF_*`
  セットアップ・連携 rows are `-` → out of scope.

> Style: Japanese name `(よみがな / English)` on first use. `←` = target loaded from source.
> Status: ✅ implemented / ⬜ pending.

---

## 申込 (もうしこみ / Application) group — Phase 2 (source of truth = implemented code) ✅
Target ← Source (as implemented in `JutakuLoanService`):

| 新システム (target) | 移行元 (source) | 変換 (transform) |
|---|---|---|
| 申込 (Application) | 申込 + 申込ワイド (Application Wide) | direct; MAX(申込目的) row only |
| 申込_業者_住宅 (Application–Vendor–Housing) | 申込 | 宅建業者コード → 販売業者マスター (Dealer Master) 参照 |
| 申込進捗 (Application Progress) | 申込進捗 | 1:1; 進捗コード conversion |
| 申込関連申込 (Related Applications) | 申込関連申込 | 1:N; 申込番号/関連申込番号 2→3 |
| 申込審査段階 (Application Review Stage) | 申込審査段階 | MAX row; 審査完了区分='1' filter |
| 保証人 (Guarantor) | 保証人 + 保証人ワイド (Guarantor Wide) | N:1 統合 (consolidation) |
| 保証検討表補足 (Guarantee Review Supplement) | 保証検討表補足 | MAX row |
| 申込担保情報ＰＤＦ (Application Collateral Info PDF) | 申込担保回答ＰＤＦ (Application Collateral Answer PDF) | 1:N per (申込番号, 申込目的) |
| 申込審査履歴 (Application Review History) | 申込審査履歴 | 1:N; 進捗コード conversion |
| 申込審査状況 (Application Review Status) | — (history parent) | per 回数 (attempt no.) |
| 履歴申込 (History Application) | 申込 | non-MAX 申込目的 → history (処理概要(2) 参照) |
| 履歴申込_業者_住宅 (History App–Vendor–Housing) | 申込 | history |
| 履歴申込審査段階 (History App Review Stage) | 申込審査段階 | history, per 回数 |
| 履歴保証人 (History Guarantor) | 保証人 | history, per 回数 + 連番 |
| 履歴保証検討表補足 (History Guarantee Review Suppl.) | 保証検討表補足 | history, per 回数 |

Note: `履歴 (りれき / History)` split = the MAX 申込目的 (Application Purpose) row becomes 申込;
all other completed rows become 履歴 records keyed by 回数 (かいすう / attempt number).

---

## Other in-scope groups — pending (⬜), per sheet summary
Detail these against the sheet + `編集仕様詳細_CODE_TABLES.md` when each table is column-mapped.

### 担保評価 (たんぽひょうか / Collateral Valuation)
- 申込担保情報 (Application Collateral Info) ← 担保評価照会 (Collateral Valuation Inquiry) — 直近の担保評価 (most recent).
- IF_担保評価進捗履歴 (Collateral Valuation Progress History) ← 担保評価照会 — イベント抽出 / 連番化 / 縦持ち (vertical). 構造変換(難).

### 信用情報 (しんようじょうほう / Credit Information) — KSC / JICC / CIC
- KSC group (No.155–179): mostly 単純移送 (simple transfer, 簡). KSC2取引 / 官報個人 / 官報法人 / 自社取引 / 正規化照会記録 など direct.
  - 対象外: KSC2照会回答, KSC2全債連回答 (新システム未使用).
- JICC / CIC (No.181–186): 単純移送 (簡).
- 外部信用情報 (External Credit Info, No.187–200): 審査KSC/CIC/JICC信用情報明細 — 列名差異あり. 構造変換 (中〜難).

### スコアリング (Scoring, No.203–222)
- 審査モデル照会 / 回答 (Review Model Inquiry/Answer, incl. S系). 構造変換 (中).

### 審査判定 (しんさはんてい / Review Judgment, No.226–)
- 審査結果 (Review Result) ← … 「(部分やり繰り) で コメント へ統合」.
- 審査結果事由 / 外部連携理由 (Review Result Reason / External-Link Reason) — 構造変換. See 編集仕様詳細 縦持ち→横持ち.

### 預保照会 (よほしょうかい / Deposit-Insurance Inquiry, No.350–352)
- 預保照会管理 ← 預保照会管理; 預保照会基本情報 ← 預保照会基本情報 (+ 人情報); 預保照会イメージ情報 ← 預保照会イメージ情報.

### 外部連携・保証側 (Guaranty-side External Link, No.358–365)
- 保証結果メイン / 融資条件 (じぶん / アルヒ) ← 「移行先テーブル作成」. 構造変換 (中).

---

## Out-of-scope ranges (対象外 / not migrated)
Recorded compactly; source col E = `-` or target = 新システム未使用 (grey):
- No.1–~88: マスター / システム定義 (masters/system defs) — only 販売業者マスター (Dealer Master) is in scope.
- No.~265–407: `IF_*` セットアップ・連携, S-prit連携, WELCOME, OCR, TAB, 審査AI, セレカ, 新生F, パンフ, メッセージ, 旧住宅判定 — nearly all 対象外.
- No.~408–552: 行内照会・個別照会結果・サマリ登録・障害管理・実行(画面/バッチ)・事前照会・帳票還元・人事部店番マスタ — almost all 対象外 (a few 操作ログ / 照会履歴 have a source).

---

## Source images
Screenshots saved under the Cursor project `assets/` folder (`...images_beeimgtmp-20260701-*.png`).
This markdown captures the in-scope mappings; the full 552-row detail is in the images / `JUTAKU_LOAN_PLAN.xlsx`.
