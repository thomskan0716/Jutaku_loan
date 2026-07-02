# 編集仕様詳細 (へんしゅうしようしょうさい / Edit Specification Detail) — Code Conversion Reference

Transcribed from the `(別紙)編集仕様詳細` sheet of `詳細設計書_住宅ローンデータ移行`
(screenshots shared by the user). This is the **source of truth** for code conversions during migration.

- Left = 移行元システム (source), Right = 新システム (target).
- **(未定 / undecided)** = yellow cell in the sheet → leave the source value (or null per spec) with an explicit `TODO(編集仕様詳細)`. Never guess.
- Many conversions **branch on `商品大分類 (しょうひんだいぶんるい / Product Major Category)`**: 1=住宅ローン (Housing Loan), 2=ワイド/アパートローン (Wide/Apartment Loan), 3=照会専用 (Inquiry-only), 4=外部ローン (External Loan), 8=途上与信 (excluded / 移行対象外).

> Legend: `→` maps to; `-` = not set (null). Status: ✅ implemented / ⬜ pending table.
> Naming style: Japanese name `(よみがな / English)` on first use; code values kept as-is.

---

## 申込 (もうしこみ / Application) group — implemented in `map申込Columns` ✅

### 商品大分類 (しょうひんだいぶんるい / Product Major Category)
`1→1`, `2→(未定)`, `3→(未定)`, `4→8`, `8→移行対象外 (excluded)`

### 商品分類 (しょうひんぶんるい / Product Category) — equivalent to source 申込書区分
`1→1`, `2→(未定)`, `3→(未定)`, `4→23`, `8→移行対象外`

### 商品コード (しょうひんコード / Product Code) — references 商品マスター.商品コード
Mostly (未定). Decided: `8001→006001:ARUHI住宅ローン`, `8002→006002:auじぶん銀行住宅ローン`, `8000→移行対象外`.

### 営業店コード (えいぎょうてんコード / Branch Code) — references 営業店マスター.営業店コード
All (未定).

### 勤務先企業区分 (きんむさききぎょうくぶん / Employer Company Type) — from 上場フラグ (checkbox)
`1→1:上場 (Listed)`, `0→2:非上場 (Unlisted)`

### 勤務先勤業 (きんむさききんぎょう / Employer Job) — from 勤務先職業; 職業形態 (Employment Type); branches on 商品大分類
- 住宅/ワイド (code 2080): `1→1`, `2→(未定: 2:正規 / 3:非正規)`, `3→4`, `4→5`, `5→6`, `6→7`, `7→7`, `8→7`, `9→8`
- 外部ローン (code 2083): identity `1→1 … 9→9`

### 勤務先勤種 (きんむさききんしゅ / Employer Job Category) — from 勤務先職種; 職種 (Job Type); branches on 商品大分類 ⬜ (source field TBD)
- 住宅/ワイド (code 2085): `1→1`, `2→2`, `3→3`, `4/5/6/7/8:運転手各種→4:運転士`, `9→5`, `10→6`, `11→7`, `12:国家資格→8:その他(*1)`, `13:一般→8:その他(*1)`
  - (*1) When その他, set 勤務先職種その他 (Employer Job Type Other) to the source job-type name (国家資格/一般).
- 外部ローン (code 2084): identity `1→1 … 13→13`

### 歩合給 (ぶあいきゅう / Commission Pay)
`1→1:あり (yes)`, `2→2:なし (no)`, `3:ドライバー→1:あり`

### 住居形態 (じゅうきょけいたい / Housing Type) — from 住居区分 (Housing Category)
`1→1:持ち家 (owned)`, `2→4:借家 (rented)`, `3→3:社宅 (company housing)`, `4→5:アパート (apartment)`, `5:その他→(未定)`

### 借入種類 (かりいれしゅるい / Borrowing Type) — 金融機関 (Financial Institution) 1–3
`1→1`, `2→2:車のローン (car loan)`, `3→3`, `4→4`, `5/6:アパートローン→5:収益物件 (income property)`, `7:保証債務→6:その他`, `9→6:その他`

### 借入時完済解約予定 (かりいれじかんさいかいやくよてい / Repay-or-Cancel-at-Borrowing Plan) — 金融機関 1–3
`0:無→2:なし (no)`, `1:有→1:あり (yes)`

### 資金使途 (しきんしと / Fund Usage) → derived columns; branches on 商品大分類
Target columns: 資金使途＿物件種別 (ぶっけんしゅべつ / Property Type, code 2332: 1=マンション/Condo, 2=マンション以外/Non-condo), 資金使途＿マンション (Condo, code 2333), 資金使途＿マンション以外 (Non-condo, code 2334).

- 住宅ローン(1) / ワイドローン(2), rows 1–6 (物件種別 / マンション / マンション以外):
  - `1:建物新築 → 2 / - / 6`
  - `2:土地購入 → 2 / - / 5`
  - `3:戸建購入(新築) → 2 / - / 1`
  - `4:マンション購入(新築) → 1 / 1 / -`
  - `5:中古住宅(戸建) → 2 / - / 2`
  - `6:中古住宅(マンション) → 1 / 2 / -`
  - `7:増改築`, `8:借替`, `9:その他`, `10/11:買い替え` → (未定); ワイド `9:中古アパート → 2 / - / (未定)`
- 外部ローン(4): plain 資金使途 identity (code 2510: `1→1 … 11→11`); derived columns not set.
- 資金使途＿ワイドローン一般口 (Wide Loan General): (未定)

### 返済方法区分 (へんさいほうほうくぶん / Repayment Method Type)
`1→1:元利均等 (equal principal+interest)`, `2→3:元金均等 (equal principal)`, `3:期限一括→2:期日一括 (lump-sum)` — Wide loan only

### 金利区分 (きんりくぶん / Interest Rate Type)
`1→1:変動 (variable)`, `2→(未定)`, `3→(未定)` — 固定変動ミックス型 (fixed/variable mix)

### 同居予定家族 (どうきょよていかぞく / Planned Cohabiting Family) — source is checkbox input
- 配偶者 (はいぐうしゃ / Spouse): `1→2:配偶者同居`, `0→1:予定なし (none planned)`
- 父 (ちち / Father) / 母 (はは / Mother): `1→1:予定あり (planned)`, `0→2:予定なし`
- その他 (そのた / Other) — derived from 同居＿その他人数: `>0→1:予定あり`, `=0→2:予定なし`
- 子供人数 (こどもにんずう / Number of Children) — 住宅/ワイド, from 同居＿子供人数: `0→1:0人`, `1→2:1人`, `2→3:2人`, `3→4:3人`, `>=4→5:4人以上`; 外部ローン passes through. ⬜

### 国家資格 (こっかしかく / National Qualification)
`1→3:医師`, `2→8:獣医師`, `3→2:弁護士`, `4→7:公認会計士`, `5→6:税理士`, `6→10:司法書士`, `7→9:社会保険労務士`, `8→5:薬剤師`, `9→13:行政書士`, `10→12:土地家屋調査士`, `11→14:不動産鑑定士`, `99→15:その他`

### 進捗コード (しんちょくコード / Progress Code) — 申込進捗 (Application Progress) + 申込審査履歴 (Application Review History)
`1000→JT0010:文書振分`, `9100→JT9100:審査完了`, `9900→JT9200:完了`, `9500:振分待ち→(未定, 外部ローン/ARUHI only)`

---

## Other tables — implement when the table is column-mapped ⬜

### 審査結果 (しんさけっか / Review Result)
`1→1:承認 (approved)`, `2:条件付承認→1:承認`, `3→3:否認 (rejected)`, `4→4:返却 (returned)`, `5→5:取下 (withdrawn)`, `6:差戻し→(未定)`

### 顧客区分 (こきゃくくぶん / Customer Type) — from 審査完了区分 (Review Completion Type)
`0:未登録→4:実行せずに完了`, `1:実行→1:実行`, `2:取下→2:取下`, `3:謝絶→3:否認`, `4:断念→2:取下`, `9:初期値→4:実行せずに完了`

### 保証人事由 (ほしょうにんじゆう / Guarantor Reason)
`01→01:連帯債務者`, `02:年収加算者兼連帯保証人→05:連帯保証人(年収合算者)`, `03:担保提供者兼連帯保証人→06:担保提供者兼連帯保証人`, `04→08:連帯保証人(年収合算者)兼担保提供者`, `05→02:連帯保証人`, `06:担保提供者→04:担保提供者`

### 続柄 (つづきがら / Relationship) — branches on 商品大分類 + 保証人事由
- a. 商品大分類=1/2 AND 保証人事由∈{01,02,04} → code 2016:連帯債務者_続柄:
  `1:配偶者→01`, `2:実父→03:親`, `3:実母→03`, `4:義父→03`, `5:義母→03`, `6:子→04`, `7:兄弟→(未定)`, `8:祖父母→(未定)`, `9:叔父→(未定)`, `A:叔母→(未定)`, `B:親戚→(未定)`, `C:婚約者→02:婚約者`, `Z:その他→(未定)`
- a-2. 商品大分類=1/2 AND 保証人事由∈{03,05,06} → code 2015:その他契約者_続柄:
  `1→01:配偶者`, `2→02:実父`, `3→03:実母`, `4→04:義父`, `5→05:義母`, `6→06:子`, `7→08:兄弟`, `8→09:祖父母`, `9→10:その他`, `A→10`, `B→10`, `C→07:婚約者`, `Z→10`
- b. 商品大分類=4:外部ローン → code 2018:外部ローン_続柄: identity `1→1 … Z→Z`

### 審査結果事由 (しんさけっかじゆう / Review Result Reason) — 縦持ち→横持ち (vertical→horizontal)
Register each non-null 事由0〜事由23 (じゆう / Reason) from 審査結果 (Review Result) as a vertical detail row.
- 事由内容 ← 審査結果.事由N; 事由コード ← 審査結果.事由コードN; 条件履行 (じょうけんりこう / Condition Fulfillment) ← 審査結果.条件履行1〜10.
- 通番 (つうばん / Serial No) = 1-based sequence within the registered rows. 事由区分 (じゆうくぶん / Reason Type) is set by (審査段階, 審査結果, 申込目的, 事由) via code 9010:コード事由_事由区分.
- Condition-fulfillment reasons (事由11〜23) are looked up by (申込番号 + 事由コード); 確認日=NULL→0:未確認 / ≠NULL→1:確認済.

### 審査結果コメント (しんさけっかコメント / Review Result Comment) — concatenation
Set 審査結果.コメント. Branches for external-loan vs non-external. Concatenate 営業店連携事由/外部連携事由 name(s) with full-width space, then append 審査結果.コメント (0 rows = comment only).

### 審査チェックコード (しんさチェックコード / Review Check Code)
Large remap (e.g. `001:融資倍率→(未定)`, `101:年齢→101`, `102:勤続年数→104`, … `C01:氏名カナ→111`, `C02:氏名漢字→112`, `C03:生年月日→113`, `C04:電話番号→114`). Many (未定). See screenshots.

### システム判定種類 (システムはんていしゅるい / System Judgment Type)
`01:スコアランク→01`, `02:融資倍率→(未定)`, `03:借入申込金額→03`, `04:自動判定→(未定)`, `05→05` … `14→14`, `15:職業区分→15:勤務先役職`, `16:資本金区分→16:勤務先区分`, `17→17`, `18:税込年収→18`, `19:返済比率→19`, `20:借入倍率→20`

### 特殊案件区分 (とくしゅあんけんくぶん / Special Case Type)
Mostly identity (`01→01 … 64→64`). `51:追認記録表→(未定)`.

### 決裁役階 (けっさいやくかい / Approval Rank)
Identity (`00:担当者→00:システム判定 (未定)`, `10→10`, `20→20`, `30→30`, `40→40`, `50→50`, `60→60`, `70→70`).

### 文書種類コード (ぶんしょしゅるいコード / Document Type Code)
Many (未定). Decided examples: `本人確認等確認資料→J1:本人確認資料`, `資金計画チェック表/資金計画→J4:資金計画・見積書`, `担保関係書類/担保状況表/地図・担保関連資料→J3:不動産資料`, `説明資料等→J5:案件説明資料`; others → `J6:その他` / `J7:外部保証FAX` / `J8:収入確認資料` per row. See screenshots.

---

## Source images
Screenshots of this sheet were shared in chat and saved under the Cursor project `assets/` folder
(filenames prefixed `...images_image-*.png`). This markdown is the durable transcription; prefer it.
