# 住宅ローン審査システム Data Migration Project Design
# Housing Loan Review System → Scope Migration

**Project Name:** MORTGAGE_MIGRATION_BATCH (Housing Loan / Mortgage Migration)  
**Target Service-In:** October 2026  
**Design Date:** May 21, 2026  
**Based on:** MIGRATION_BATCH_C architecture

---

## 📋 Project Overview

### Migration Scope
**Source System:** 住宅ローン審査システム (Housing Loan Review System)  
**Target System:** Scope/SMS (New integrated loan management system)  
**Reference Project:** MIGRATION_BATCH_C (担保・収穫 → Scope migration)

### Key Statistics
| Item | Count | Notes |
|------|-------|-------|
| Source Tables | 267 | Total in housing loan system |
| Target Migration Tables | 137 | ~51% selected for migration |
| Target System Tables | 552 | Total in Scope system |
| Total Columns to Map | 5,705 | All columns in target tables |
| Image Types | 12 | TIFF, PDF, GIF formats |
| Migration Complexity | 60% Simple<br>40% Complex | Simple: direct mapping<br>Complex: transformation needed |

### Design Status

| Classification Level | Status | Strategy |
|---------------------|--------|----------|
| **F分類レベル** (Processing Procedures) | ✅ **100% Defined** | Copy from MIGRATION_BATCH_C |
| **E分類レベル** (Code Mappings) | ⏳ **65-70% Complete** | Define as specs are finalized |

**Approach:** Start implementation using F classification patterns from MIGRATION_BATCH_C, while incrementally adding E classification enums as mapping specifications are completed. This allows parallel work without waiting for 100% specification completion.

---

## 🎯 Migration Requirements

### 1. Data Scope (移行対象断面)

#### **IN SCOPE - Migrate These:**

| Review Type | Status | Condition | Post-Migration Action |
|-------------|--------|-----------|----------------------|
| **事前審査 (Preliminary Review)** | 完了 (Completed) | Approved | → Customer applies for formal review |
| **事前審査 (Preliminary Review)** | 完了 (Completed) | Rejected/Withdrawn | No further action |
| **正式審査 (Formal Review)** | 完了 (Completed) | Approved | → Continue execution in 基調決済システム |
| **正式審査 (Formal Review)** | 完了 (Completed) | Rejected/Withdrawn | No further action |

#### **OUT OF SCOPE - Do NOT Migrate:**

| Review Type | Status | Reason | Handling |
|-------------|--------|--------|----------|
| **事前審査** | 審査中 (In Progress) | Incomplete | Customer re-applies after cutover |
| **正式審査** | 審査中 (In Progress) | Incomplete | Customer re-applies after cutover |
| **画像データ** | 未紐付け (Unlinked images) | No application link | Discard |

### 2. Data Types

#### A. DB Data (DBデータ)
- **Source Format:** Oracle Dump file
- **Migration Method:** 
  1. Load dump to staging schema (移行用スキマ)
  2. Transform via batch program
  3. Insert to production schema (本番スキマ)

#### B. Image Data (イメージデータ)
- **Source Formats:** TIFF, GIF, PDF
- **Target Format:** JPEG (conversion required)
- **Migration Method:**
  1. Read from source file path
  2. Convert format (TIFF/GIF/PDF → JPEG)
  3. Copy to target file path
  4. Update DB reference

### 3. Master Data Strategy

| Category | Migration | Strategy |
|----------|-----------|----------|
| マスター (Master) | 1 table only | Pre-configured in Scope, migration not needed |
| トラン (Transaction) | 137 tables | Migrate completed reviews |
| ログ (Log) | All log tables | Full migration |

---

## 🏗 Project Structure Design

### Directory Layout

```
MORTGAGE_MIGRATION_BATCH/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── migration/
│   │   │       ├── MigrationBatchApplication.java
│   │   │       ├── batch/
│   │   │       │   ├── HousingMigrationTasklet.java
│   │   │       │   └── ImageConversionTasklet.java
│   │   │       ├── config/
│   │   │       │   ├── BatchConfig.java
│   │   │       │   ├── HousingLoanDataSourceConfig.java    # Source DB
│   │   │       │   ├── ScopeDataSourceConfig.java           # Target DB
│   │   │       │   └── MyBatchConfigurer.java
│   │   │       ├── service/
│   │   │       │   ├── HousingLoanService.java              # Main migration service
│   │   │       │   ├── ImageConversionService.java
│   │   │       │   └── CodeMappingService.java
│   │   │       ├── common/
│   │   │       │   ├── Constants.java
│   │   │       │   ├── ConvertibleEnum.java
│   │   │       │   ├── EnumConverter.java
│   │   │       │   ├── DateUtil.java
│   │   │       │   ├── MyUtil.java
│   │   │       │   ├── ImageUtil.java                       # NEW: Image conversion
│   │   │       │   └── housing_loan/                        # E classification enums
│   │   │       │       ├── E申込目的.java
│   │   │       │       ├── E審査種別.java
│   │   │       │       ├── E審査ステータス.java
│   │   │       │       ├── E商品区分.java
│   │   │       │       ├── E担保種別.java
│   │   │       │       ├── E返済方法.java
│   │   │       │       ├── E金利区分.java
│   │   │       │       ├── E借入目的.java
│   │   │       │       ├── E住宅種別.java
│   │   │       │       ├── E建物構造.java
│   │   │       │       ├── E資金使途.java
│   │   │       │       ├── E承認区分.java
│   │   │       │       ├── E実行ステータス.java
│   │   │       │       ├── E信用情報照会区分.java
│   │   │       │       ├── E勤務先形態.java
│   │   │       │       ├── E勤続年数区分.java
│   │   │       │       └── ... (50+ more enums)
│   │   │       └── mybatis/
│   │   │           ├── domain/
│   │   │           │   ├── housing_loan/                   # Source entities (267 tables)
│   │   │           │   │   ├── 申込情報.java
│   │   │           │   │   ├── 事前審査情報.java
│   │   │           │   │   ├── 正式審査情報.java
│   │   │           │   │   ├── 担保情報.java
│   │   │           │   │   ├── 担保物件情報.java
│   │   │           │   │   ├── 借入人情報.java
│   │   │           │   │   ├── 連帯保証人情報.java
│   │   │           │   │   ├── 収入情報.java
│   │   │           │   │   ├── 負債情報.java
│   │   │           │   │   ├── 審査結果.java
│   │   │           │   │   ├── 承認条件.java
│   │   │           │   │   ├── 信用情報照会.java
│   │   │           │   │   ├── 物件評価情報.java
│   │   │           │   │   ├── 実行管理.java
│   │   │           │   │   ├── 画像管理.java
│   │   │           │   │   └── ...
│   │   │           │   └── scope/                          # Target entities (552 tables)
│   │   │           │       ├── 申込.java
│   │   │           │       ├── 申込進捗.java
│   │   │           │       ├── 申込審査状況.java
│   │   │           │       ├── 申込審査段階.java
│   │   │           │       ├── 申込審査履歴.java
│   │   │           │       ├── 申込担保情報.java
│   │   │           │       ├── 担保物件.java
│   │   │           │       ├── 担保評価.java
│   │   │           │       ├── 借入人.java
│   │   │           │       ├── 連帯債務者.java
│   │   │           │       ├── 連帯保証人.java
│   │   │           │       ├── 年収情報.java
│   │   │           │       ├── 既存借入.java
│   │   │           │       ├── 審査結果.java
│   │   │           │       ├── 審査コメント.java
│   │   │           │       ├── 承認条件管理.java
│   │   │           │       ├── 信用情報.java
│   │   │           │       ├── 画像情報.java
│   │   │           │       ├── 顛末管理.java
│   │   │           │       ├── 移行管理テーブル.java
│   │   │           │       └── ...
│   │   │           └── mapper/
│   │   │               ├── housing_loan/                   # Source mappers
│   │   │               └── scope/                          # Target mappers
│   │   └── resources/
│   │       ├── application.yml
│   │       ├── application-batch1.yml
│   │       ├── application-batch2.yml
│   │       ├── ... (15 batch profiles)
│   │       └── migration/mybatis/mapper/
│   │           ├── housing_loan/                           # Source SQL XMLs
│   │           └── scope/                                  # Target SQL XMLs
│   └── test/
│       └── java/
│           └── migration/
│               ├── service/
│               │   ├── HousingLoanServiceTest.java
│               │   └── ImageConversionServiceTest.java
│               └── common/
│                   └── EnumConverterTest.java
├── gen/                                                    # MyBatis generated code
├── batch/                                                  # Batch execution scripts
│   ├── batch01.bat  ~ batch15.bat                         # 15 parallel batches
├── libs/                                                   # External JARs
├── docs/                                                   # Documentation
│   ├── 詳細設計書_住宅ローンデータ移行.xlsx                  # Your spec document
│   ├── 住宅ローン審査システムER図.a5er                        # ER diagram
│   ├── テーブルマッピング一覧.xlsx
│   ├── カラムマッピング一覧.xlsx
│   └── 編集仕様詳細/                                        # Detailed mapping specs
│       ├── 申込目的マッピング.xlsx
│       ├── 縦横変換仕様.xlsx
│       └── コード変換一覧.xlsx
├── build.gradle
├── settings.gradle
├── HousingLoanGenerationConfig.xml                         # MyBatis generator config
└── README.md
```

---

## 🔄 Core Service Design: HousingLoanService.java

### Service Methods

```java
@Service
@RequiredArgsConstructor
@Slf4j
public class HousingLoanService {

    // ========================================
    // 1. Range Management (MIGRATION_BATCH_C pattern)
    // ========================================
    
    /**
     * Initialize master data cache
     * Load user masters, product masters, code masters into HashMap
     */
    public void processAll() {
        // Load reference data
        List<商品マスター> productList = productMapper.selectAll();
        List<ユーザマスター> userList = userMapper.selectAll();
        List<金融機関マスター> bankList = bankMapper.selectAll();
        
        // Cache in HashMap
        productMap = productList.stream()
            .collect(Collectors.toMap(商品マスター::get商品コード, p -> p));
        // ... other caching
    }
    
    /**
     * Claim next range with pessimistic lock
     * @return Next range to process, or null if none
     */
    @Transactional
    public 移行管理テーブル claimNextRange() {
        移行管理テーブル range = manageMapper.selectNextRangeForUpdate(); // FOR UPDATE SKIP LOCKED
        if (range == null) return null;
        
        range.setステータス("RUNNING");
        range.set開始日時(Timestamp.valueOf(LocalDateTime.now()));
        manageMapper.updateStatusStart(range);
        return range;
    }
    
    /**
     * Process one range (main migration logic)
     */
    @Transactional
    public void processOneRange(移行管理テーブル range) {
        // 1. Load primary keys in range
        List<Long> applicationIds = housingLoanMapper
            .selectApplicationIdsByRange(range.get処理from(), range.get処理to());
        
        log.info("Range from={} to={}: {} records", 
            range.get処理from(), range.get処理to(), applicationIds.size());
        
        if (applicationIds.isEmpty()) return;
        
        // 2. Execute migration
        migrateApplicationsInBatch(range);
    }
    
    @Transactional
    public void markDone(移行管理テーブル range) {
        range.setステータス("DONE");
        range.set終了日時(Timestamp.valueOf(LocalDateTime.now()));
        manageMapper.updateStatusEnd(range);
    }
    
    @Transactional
    public void markError(移行管理テーブル range) {
        range.setステータス("ERROR");
        range.set終了日時(Timestamp.valueOf(LocalDateTime.now()));
        manageMapper.updateStatusEnd(range);
    }

    // ========================================
    // 2. Main Migration Logic
    // ========================================
    
    private void migrateApplicationsInBatch(移行管理テーブル range) {
        // Step 1: Load source data into HashMaps (avoid N+1)
        HashMap<Long, 申込情報> applicationMap = loadApplications(range);
        HashMap<Long, 事前審査情報> preliminaryMap = loadPreliminaryReviews(range);
        HashMap<Long, 正式審査情報> formalMap = loadFormalReviews(range);
        HashMap<Long, List<担保情報>> collateralMap = loadCollaterals(range);
        HashMap<Long, List<借入人情報>> borrowerMap = loadBorrowers(range);
        HashMap<Long, List<審査結果>> reviewResultMap = loadReviewResults(range);
        HashMap<Long, List<画像管理>> imageMap = loadImages(range);
        // ... load all related data
        
        // Step 2: Process each application
        for (Map.Entry<Long, 申込情報> entry : applicationMap.entrySet()) {
            Long appId = entry.getKey();
            申込情報 sourceApp = entry.getValue();
            
            try {
                // A. Check migration criteria (審査完了 only)
                if (!isMigrationTarget(sourceApp, preliminaryMap.get(appId), formalMap.get(appId))) {
                    log.debug("Skip application: appId={}, status not completed", appId);
                    continue;
                }
                
                // B. Transform data via convXXX() methods
                申込 targetApp = convApplication(sourceApp);
                申込進捗 progress = convApplicationProgress(sourceApp);
                申込審査状況 reviewStatus = convReviewStatus(sourceApp, preliminaryMap.get(appId), formalMap.get(appId));
                申込審査段階 reviewStage = convReviewStage(sourceApp);
                List<申込審査履歴> reviewHistory = convReviewHistory(reviewResultMap.get(appId));
                申込担保情報 collateralInfo = convCollateralInfo(collateralMap.get(appId));
                // ... all transformations
                
                // C. Insert to target DB
                insertApplication(targetApp, progress, reviewStatus, reviewStage, reviewHistory, collateralInfo);
                
                // D. Migrate images if configured
                if ("0".equals(PROCESS) && "1".equals(kind)) {
                    migrateImages(appId, imageMap.get(appId));
                }
                
                log.info("Migrated application: appId={}", appId);
                
            } catch (Exception e) {
                log.error("Failed to migrate application: appId={}", appId, e);
                // Continue processing other records
            }
        }
    }
    
    // ========================================
    // 3. Migration Criteria Check
    // ========================================
    
    /**
     * Check if application should be migrated
     * Criteria: 
     * - Preliminary review completed (事前審査完了) OR
     * - Formal review completed (正式審査完了)
     * Exclude:
     * - In-progress reviews (審査中)
     */
    private boolean isMigrationTarget(申込情報 app, 事前審査情報 prelim, 正式審査情報 formal) {
        // Preliminary review completed
        boolean prelimCompleted = (prelim != null && "完了".equals(prelim.get審査ステータス()));
        
        // Formal review completed
        boolean formalCompleted = (formal != null && "完了".equals(formal.get審査ステータス()));
        
        return prelimCompleted || formalCompleted;
    }
    
    // ========================================
    // 4. Transformation Functions (convXXX)
    // ========================================
    
    /**
     * Transform: 申込情報 (source) → 申込 (target)
     */
    private 申込 convApplication(申込情報 source) {
        申込 target = new 申込();
        
        // Direct mapping
        target.set申込番号(MyUtil.convString(source.get申込番号()));
        target.set申込日時(source.get申込日時());
        target.set申込チャネル(source.get申込チャネル());
        target.set借入希望金額(source.get借入希望金額());
        target.set借入期間(source.get借入期間());
        
        // Code conversion via E enum
        try {
            E申込目的 purpose = E申込目的.fromOldCode(source.get申込目的());
            target.set申込目的(purpose.getNewCode());
        } catch (IllegalArgumentException e) {
            log.warn("Cannot convert 申込目的: {}", source.get申込目的());
            throw e; // Skip this record
        }
        
        try {
            E商品区分 product = E商品区分.fromOldCode(source.get商品コード());
            target.set商品コード(product.getNewCode());
        } catch (IllegalArgumentException e) {
            log.warn("Cannot convert 商品コード: {}", source.get商品コード());
            throw e;
        }
        
        // ... more mappings
        
        return target;
    }
    
    /**
     * Transform: 担保情報 (source) → 申込担保情報 (target)
     * Complexity: 中 (Structure conversion + code mapping)
     */
    private 申込担保情報 convCollateralInfo(List<担保情報> sourceList) {
        if (sourceList == null || sourceList.isEmpty()) return null;
        
        申込担保情報 target = new 申込担保情報();
        
        // Aggregate multiple source records into one target record
        for (担保情報 source : sourceList) {
            // Map担保種別
            E担保種別 collateralType = E担保種別.fromOldCode(source.get担保種別());
            
            if ("01".equals(collateralType.getNewCode())) { // 土地
                target.set土地評価額(source.get評価額());
                target.set土地面積(source.get土地面積());
            } else if ("02".equals(collateralType.getNewCode())) { // 建物
                target.set建物評価額(source.get評価額());
                target.set建物面積(source.get建物面積());
                
                // Structure type conversion
                E建物構造 structure = E建物構造.fromOldCode(source.get建物構造());
                target.set建物構造(structure.getNewCode());
            }
        }
        
        return target;
    }
    
    /**
     * Transform: Vertical to Horizontal pivot
     * Example: Multiple 収入情報 records → Single 年収情報 record with columns
     */
    private 年収情報 convIncomeInfo(List<収入情報> sourceList) {
        年収情報 target = new 年収情報();
        
        for (収入情報 source : sourceList) {
            String incomeType = source.get収入種別();
            
            switch (incomeType) {
                case "01": // 給与収入
                    target.set給与収入(source.get年収金額());
                    break;
                case "02": // 事業収入
                    target.set事業収入(source.get年収金額());
                    break;
                case "03": // 不動産収入
                    target.set不動産収入(source.get年収金額());
                    break;
                case "04": // その他収入
                    target.setその他収入(source.get年収金額());
                    break;
            }
        }
        
        // Calculate total
        target.set年収合計(
            nvl(target.get給与収入(), 0) +
            nvl(target.get事業収入(), 0) +
            nvl(target.get不動産収入(), 0) +
            nvl(target.getその他収入(), 0)
        );
        
        return target;
    }
    
    /**
     * Transform: Horizontal to Vertical pivot
     * Example: Single record with columns → Multiple records
     */
    private List<返済予定> convRepaymentSchedule(返済計画 source) {
        List<返済予定> targetList = new ArrayList<>();
        
        // Explode horizontal columns to vertical records
        for (int month = 1; month <= source.get返済回数(); month++) {
            返済予定 target = new 返済予定();
            target.set申込番号(source.get申込番号());
            target.set返済回(month);
            
            // Calculate scheduled payment for this month
            if (isBonus Month(month, source)) {
                target.set返済予定額(source.get月次返済額() + source.getボーナス返済額());
            } else {
                target.set返済予定額(source.get月次返済額());
            }
            
            targetList.add(target);
        }
        
        return targetList;
    }
    
    // ========================================
    // 5. Image Migration
    // ========================================
    
    private void migrateImages(Long appId, List<画像管理> imageList) {
        if (imageList == null || imageList.isEmpty()) return;
        
        for (画像管理 image : imageList) {
            try {
                // A. Read source image file
                String sourcePath = buildSourceImagePath(image);
                File sourceFile = new File(sourcePath);
                
                if (!sourceFile.exists()) {
                    log.warn("Image file not found: {}", sourcePath);
                    continue;
                }
                
                // B. Convert format (TIFF/GIF/PDF → JPEG)
                byte[] sourceBytes = Files.readAllBytes(sourceFile.toPath());
                byte[] jpegBytes = imageUtil.convertToJpeg(sourceBytes, image.getファイル形式());
                
                // C. Write to target path
                String targetPath = buildTargetImagePath(image);
                Files.write(Paths.get(targetPath), jpegBytes);
                
                // D. Insert image metadata to DB
                画像情報 targetImage = new 画像情報();
                targetImage.set申込番号(MyUtil.convString(appId));
                targetImage.setファイルパス(targetPath);
                targetImage.set画像種別(image.get画像種別());
                targetImage.setページ番号(image.getページ番号());
                imageInfoMapper.insert(targetImage);
                
                log.info("Migrated image: appId={}, path={}", appId, targetPath);
                
            } catch (Exception e) {
                log.error("Failed to migrate image: appId={}, imageId={}", appId, image.get画像ID(), e);
            }
        }
    }
    
    // ========================================
    // Helper Methods
    // ========================================
    
    private Integer nvl(Integer value, Integer defaultValue) {
        return value != null ? value : defaultValue;
    }
    
    private boolean isBonus Month(int month, 返済計画 plan) {
        int bonusMonth1 = plan.getボーナス返済月1();
        int bonusMonth2 = plan.getボーナス返済月2();
        int monthInYear = month % 12;
        if (monthInYear == 0) monthInYear = 12;
        return monthInYear == bonusMonth1 || monthInYear == bonusMonth2;
    }
}
```

---

## � Classification Levels: E and F

### Overview

The migration design follows two classification levels based on MIGRATION_BATCH_C patterns:

| Level | Japanese | Meaning | What It Defines | Status |
|-------|----------|---------|-----------------|--------|
| **F Classification** | F分類レベル | Processing Procedures | **How/When** to process | ✅ 100% reusable from MIGRATION_BATCH_C |
| **E Classification** | E分類レベル | Code Mapping | **What** codes to convert | ⏳ 65-70% defined, to be completed |

### F Classification Level (F分類レベル)

**Definition:** Processing flow, execution order, transformation logic structure

**What F Classification Covers:**
- 🔄 **Processing trajectory** (軌道): Which path to follow
- 📋 **Procedure order** (手続きの順番): Sequence of operations
- 🏗️ **Architecture pattern**: How to structure the batch
- 🔧 **Transformation logic**: How to orchestrate data conversion

**F Classification Elements (Learned from MIGRATION_BATCH_C):**

1. **Range-Based Processing Pattern**
   ```
   processAll() → claimNextRange() → processOneRange()
   ```

2. **Parallel Execution Control**
   - FOR UPDATE SKIP LOCKED
   - Status flow: TODO → RUNNING → DONE/ERROR
   - 15 parallel batch instances

3. **Master Data Caching Strategy**
   - Load master data into HashMap
   - Prevent N+1 query problems
   - In-memory lookups

4. **Transformation Orchestration**
   - Batch processing order
   - Transaction boundaries
   - Error handling flow
   - Rollback strategy

5. **Data Processing Sequence**
   ```
   Load cache → Filter target → Transform → Validate → Insert → Update status
   ```

**Key Point from Meeting:**
> "基本的にF分レベルというかどういう軌道、どういう手続きの順番でやってみたいなものは、こっちのバッチを模倣すれば割と補完できる"
> 
> "The F classification level - what trajectory to take, what order of procedures - can be largely covered by imitating MIGRATION_BATCH_C"

**Action:** Copy F classification patterns from MIGRATION_BATCH_C and apply to housing loan migration

---

### E Classification Level (E分類レベル)

**Definition:** Enum-based code conversion from old system codes to new system codes

**What E Classification Covers:**
- 🔢 **Code mapping**: Old code → New code conversion
- 📖 **Value lookup**: Dictionary-style transformations
- ✏️ **Simple 1:1 mapping**: No business logic

**E Classification Characteristics:**
- Implemented as Java enums
- Implements `ConvertibleEnum` interface
- Methods: `getOldCode()`, `getOldName()`, `getNewCode()`, `getNewName()`
- Used by `EnumConverter.fromCode()` for lookups

**Example:**
```java
public enum E申込目的 implements ConvertibleEnum {
    新規借入_新築("10", "新規借入（新築）", "10", "新規借入"),
    新規借入_中古("15", "新規借入（中古）", "10", "新規借入"),  // Merge
    借換("20", "借換", "20", "借換");
    
    // Old system    →    New system
    // oldCode: "10"  →   newCode: "10"
    // oldCode: "15"  →   newCode: "10" (consolidated)
}
```

**Current Status:**
- ⏳ Mapping design: 65-70% complete
- ⏳ Detailed specifications: In progress
- ✅ Can start implementation without waiting for 100% completion

**Action:** Define 50+ E classification enums as specifications are finalized

---

### Analogy: Cooking Recipe

Think of F and E classifications like a cooking recipe:

| Classification | Cooking Analogy | Migration Example |
|----------------|-----------------|-------------------|
| **F Classification** | **Recipe steps** (順序・手順) | 1. Load master data<br>2. Filter completed reviews<br>3. Apply E classification<br>4. Insert to target<br>5. Update status |
| **E Classification** | **Ingredients list** (材料リスト) | Old code "01" = New code "1"<br>Old code "承認" = New code "Approved" |

**F = How to cook**  
**E = What ingredients to use**

---

## �📐 E Classification Design (Code Mapping Enums)

### Enum Categories

Based on your housing loan system, create **50+ E classification enums**:

#### Application & Product
- `E申込目的` - Application purpose (10, 15, 20, 30 → 10, 20)
- `E商品区分` - Product category
- `E商品種別` - Product type
- `E資金使途` - Fund usage
- `E申込チャネル` - Application channel

#### Review Process
- `E審査種別` - Review type (事前/正式)
- `E審査ステータス` - Review status (申込受付/審査中/完了)
- `E審査区分` - Review classification
- `E承認区分` - Approval classification (承認/条件付承認/否認/取下)
- `E否認理由` - Rejection reason

#### Collateral & Property
- `E担保種別` - Collateral type (土地/建物/マンション)
- `E住宅種別` - Housing type (新築/中古/建売)
- `E建物構造` - Building structure (木造/鉄骨/RC/SRC)
- `E住宅用途` - Housing use (自己居住/賃貸/セカンドハウス)
- `E所有権種別` - Ownership type (所有権/借地権)

#### Borrower Information
- `E借入人続柄` - Borrower relationship (本人/配偶者/親/子)
- `E勤務先形態` - Employment type (正社員/契約社員/自営業)
- `E勤続年数区分` - Employment duration category
- `E年収区分` - Annual income category
- `E健康保険種類` - Health insurance type

#### Loan Terms
- `E返済方法` - Repayment method (元利均等/元金均等)
- `E金利区分` - Interest rate type (固定/変動/固定期間選択)
- `E金利優遇区分` - Interest rate discount category
- `E借入期間区分` - Loan period category
- `E繰上返済手数料` - Prepayment fee type

#### Execution & Settlement
- `E実行ステータス` - Execution status
- `E実行区分` - Execution classification (一括/分割)
- `E振込先区分` - Transfer destination type
- `E決済方法` - Settlement method

#### Credit Information
- `E信用情報照会区分` - Credit inquiry type
- `E信用情報機関` - Credit bureau (CIC/JICC/KSC)
- `E照会結果区分` - Inquiry result classification
- `E延滞有無` - Delinquency flag

#### Document & Image
- `E画像種別` - Image type (本人確認/収入証明/物件資料)
- `E書類種別` - Document type
- `E提出方法` - Submission method (アップロード/FAX/郵送)

### Example Enum Implementation

```java
package migration.common.housing_loan;

import migration.common.ConvertibleEnum;

/**
 * 申込目的 (Application Purpose) code conversion
 * 
 * From meeting notes:
 * Old system: 10, 15, 20, 30
 * New system: 10, 20
 * Mapping: 10→10, 15→10, 20→20, 30→20
 */
public enum E申込目的 implements ConvertibleEnum {
    新規借入_新築("10", "新規借入（新築）", "10", "新規借入"),
    新規借入_中古("15", "新規借入（中古）", "10", "新規借入"),  // Merge to 10
    借換("20", "借換", "20", "借換"),
    借換_諸費用込("30", "借換（諸費用込）", "20", "借換");      // Merge to 20
    
    private final String oldCode;
    private final String oldName;
    private final String newCode;
    private final String newName;
    
    E申込目的(String oldCode, String oldName, String newCode, String newName) {
        this.oldCode = oldCode;
        this.oldName = oldName;
        this.newCode = newCode;
        this.newName = newName;
    }
    
    @Override public String getOldCode() { return oldCode; }
    @Override public String getOldName() { return oldName; }
    @Override public String getNewCode() { return newCode; }
    @Override public String getNewName() { return newName; }
    
    public static E申込目的 fromOldCode(String code) {
        for (E申込目的 e : values()) {
            if (e.oldCode.equals(code)) return e;
        }
        throw new IllegalArgumentException("Unknown 申込目的 code: " + code);
    }
}
```

---

## ⚙️ Configuration Design

### application.yml

```yaml
spring:
  application:
    name: MORTGAGE_MIGRATION_BATCH

  # Source: Housing Loan Review System
  datasource:
    housing-loan:
      url: jdbc:oracle:thin:@housing-loan-db:1521:HOUSING
      username: housing_loan_user
      password: ${HOUSING_LOAN_PASSWORD}
      driver-class-name: oracle.jdbc.OracleDriver
      hikari:
        maximum-pool-size: 20
        minimum-idle: 5

    # Target: Scope System
    scope:
      url: jdbc:oracle:thin:@scope-db:1521:SCOPE
      username: scope_user
      password: ${SCOPE_PASSWORD}
      driver-class-name: oracle.jdbc.OracleDriver
      hikari:
        maximum-pool-size: 20
        minimum-idle: 5

  batch:
    job:
      enabled: false   # Prevent auto-run
    jdbc:
      initialize-schema: always

mybatis:
  mapper-locations: 
    - classpath:migration/mybatis/mapper/housing_loan/**/*.xml
    - classpath:migration/mybatis/mapper/scope/**/*.xml
  type-aliases-package: migration.mybatis.domain
  configuration:
    map-underscore-to-camel-case: true

# Batch configuration
batch:
  process: "0"     # 0=All, 1=Core, 2=History, 3=Optional
  kind: "1"        # 1=Bank (with images), 2=Guarantee (no images)
  
  # Image directories
  source-image-dir: "\\\\housing-loan-server\\images\\審査書類"
  target-image-dir: "\\\\scope-server\\images\\申込画像"
  
  # Range size
  range-size: 1000   # Process 1000 applications per range
  
  # Parallel execution
  parallel-count: 15  # 15 batch instances

logging:
  level:
    migration: DEBUG
    org.springframework.batch: INFO
  file:
    name: logs/migration-batch-h.log
  pattern:
    file: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"
```

### batch01.bat ~ batch15.bat

```batch
@echo off
cd %~dp0
set CLASSPATH="bin/main;libs\*.jar"

REM Batch 1: Range 1-1000
java.exe -Xmx2g -Xms2g ^
  -Dspring.profiles.active=local,batch1 ^
  -Dbatch.range.from=1 ^
  -Dbatch.range.to=1000 ^
  -classpath %CLASSPATH% ^
  migration.MigrationBatchApplication

REM ... batch02.bat handles range 1001-2000, etc.
```

---

## 🗃 Database Design

### Migration Control Table (移行管理テーブル)

```sql
CREATE TABLE 移行管理テーブル (
    移行ID                NUMBER(10)      PRIMARY KEY,
    処理from              NUMBER(10)      NOT NULL,
    処理to                NUMBER(10)      NOT NULL,
    ステータス            VARCHAR2(20)    NOT NULL,  -- TODO/RUNNING/DONE/ERROR
    開始日時              TIMESTAMP,
    終了日時              TIMESTAMP,
    処理件数              NUMBER(10),
    エラー件数            NUMBER(10),
    エラーメッセージ      VARCHAR2(4000),
    登録日時              TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
    更新日時              TIMESTAMP       DEFAULT CURRENT_TIMESTAMP
);

-- Index for claiming next range
CREATE INDEX idx_migration_status ON 移行管理テーブル(ステータス, 処理from);
```

### Sample Data

```sql
-- Initialize ranges (example: 10,000 applications, 1000 per range)
INSERT INTO 移行管理テーブル (移行ID, 処理from, 処理to, ステータス)
VALUES (1, 1, 1000, 'TODO');

INSERT INTO 移行管理テーブル (移行ID, 処理from, 処理to, ステータス)
VALUES (2, 1001, 2000, 'TODO');

-- ... repeat for all ranges

INSERT INTO 移行管理テーブル (移行ID, 処理from, 処理to, ステータス)
VALUES (10, 9001, 10000, 'TODO');
```

---

## 📊 Table Mapping Overview

### High-Level Mapping (Examples)

| Source Table (住宅ローン審査) | Target Table (Scope) | Mapping Type | Complexity |
|---------------------------|---------------------|--------------|------------|
| 申込情報 | 申込 | 1:1 | Simple |
| 事前審査情報 | 申込審査段階 | 1:1 | Medium (code mapping) |
| 正式審査情報 | 申込審査段階 | 1:1 | Medium (code mapping) |
| 担保情報 | 申込担保情報 | N:1 | Complex (aggregation) |
| 担保物件情報 | 担保物件 | 1:1 | Medium |
| 物件評価情報 | 担保評価 | 1:1 | Simple |
| 借入人情報 | 借入人 | 1:1 | Simple |
| 連帯保証人情報 | 連帯保証人 | 1:1 | Simple |
| 収入情報 | 年収情報 | N:1 | Complex (vertical→horizontal) |
| 負債情報 | 既存借入 | 1:1 | Simple |
| 審査結果 | 審査結果 | 1:1 | Medium (code mapping) |
| 承認条件 | 承認条件管理 | 1:1 | Simple |
| 信用情報照会 | 信用情報 | 1:1 | Medium |
| 画像管理 | 画像情報 | 1:1 | Complex (file conversion) |
| 実行管理 | 顛末管理 | 1:1 | Medium (status mapping) |

**Total:** 137 source tables → 552 target tables (some target tables receive no migration data)

---

## 🎨 Transformation Patterns

### Pattern 1: Simple Direct Mapping (60% of tables)

```java
// Source → Target (same structure, minimal code conversion)
private 借入人 convBorrower(借入人情報 source) {
    借入人 target = new 借入人();
    target.set申込番号(source.get申込番号());
    target.set氏名(source.get氏名());
    target.set生年月日(source.get生年月日());
    target.set電話番号(source.get電話番号());
    // ... direct field copy
    return target;
}
```

### Pattern 2: Code Conversion (E Enum Mapping)

```java
// Code transformation via enum
private 申込審査段階 convReviewStage(事前審査情報 prelim, 正式審査情報 formal) {
    申込審査段階 target = new 申込審査段階();
    
    // Map 審査種別
    if (formal != null) {
        target.set審査種別(E審査種別.正式審査.getNewCode());
    } else {
        target.set審査種別(E審査種別.事前審査.getNewCode());
    }
    
    // Map 審査ステータス
    String oldStatus = (formal != null) ? formal.get審査ステータス() : prelim.get審査ステータス();
    E審査ステータス statusEnum = E審査ステータス.fromOldCode(oldStatus);
    target.set審査ステータス(statusEnum.getNewCode());
    
    return target;
}
```

### Pattern 3: N:1 Aggregation (Merge Multiple Records)

```java
// Multiple担保情報 → Single 申込担保情報
private 申込担保情報 convCollateralInfo(List<担保情報> sourceList) {
    申込担保情報 target = new 申込担保情報();
    
    BigDecimal totalValue = BigDecimal.ZERO;
    for (担保情報 source : sourceList) {
        totalValue = totalValue.add(source.get評価額());
        
        // Categorize by type
        E担保種別 type = E担保種別.fromOldCode(source.get担保種別());
        switch (type.getNewCode()) {
            case "01": // Land
                target.set土地評価額(source.get評価額());
                break;
            case "02": // Building
                target.set建物評価額(source.get評価額());
                break;
        }
    }
    
    target.set担保評価額合計(totalValue);
    return target;
}
```

### Pattern 4: Vertical → Horizontal Pivot

```java
// Multiple 収入情報 (vertical) → Single 年収情報 (horizontal columns)
private 年収情報 convIncomeInfo(List<収入情報> verticalList) {
    年収情報 horizontal = new 年収情報();
    
    for (収入情報 v : verticalList) {
        switch (v.get収入種別()) {
            case "01": horizontal.set給与収入(v.get年収金額()); break;
            case "02": horizontal.set事業収入(v.get年収金額()); break;
            case "03": horizontal.set不動産収入(v.get年収金額()); break;
            case "04": horizontal.setその他収入(v.get年収金額()); break;
        }
    }
    
    return horizontal;
}
```

### Pattern 5: Horizontal → Vertical Expansion

```java
// Single 返済計画 (horizontal) → Multiple 返済予定 records (vertical)
private List<返済予定> convRepaymentSchedule(返済計画 horizontal) {
    List<返済予定> verticalList = new ArrayList<>();
    
    for (int i = 1; i <= horizontal.get返済回数(); i++) {
        返済予定 v = new 返済予定();
        v.set返済回(i);
        v.set返済予定額(calculatePayment(horizontal, i));
        verticalList.add(v);
    }
    
    return verticalList;
}
```

### Pattern 6: Primary Key Transformation

```java
// Old PK: 申込番号 (8 digits) → New PK: 申込番号 (12 digits with prefix)
private String transformApplicationNo(String oldAppNo) {
    // Add prefix "HL" + pad to 12 digits
    return "HL" + StringUtils.leftPad(oldAppNo, 10, '0');
    // Example: "12345678" → "HL0012345678"
}
```

---

## 🚀 Execution Plan

### Phase 1: Setup (Week 1-2)

**Day 1-2: Environment**
- [ ] Connect to development VM
- [ ] SVN checkout
- [ ] Eclipse setup
- [ ] Database connection test
- [ ] Review MIGRATION_BATCH_C code

**Day 3-5: Understanding**
- [ ] Read detailed specification (詳細設計書)
- [ ] Study ER diagram (A5:SQL Mk-2)
- [ ] Review table mapping spreadsheet (137 tables)
- [ ] Review column mapping spreadsheet (5705 columns)
- [ ] Identify E classification requirements (50+ enums)

**Week 2: Design**
- [ ] Create project structure
- [ ] Design E classification enums
- [ ] Design convXXX() method signatures
- [ ] Plan range strategy (range size, parallel count)
- [ ] Design error handling approach

### Phase 2: Implementation (Week 3-8)

**Week 3-4: Foundation**
- [ ] Implement dual DataSource configuration
- [ ] Implement range management (processAll, claimNextRange, markDone/Error)
- [ ] Implement migration control table CRUD
- [ ] Create 50+ E classification enums (start with high-frequency ones)
- [ ] Implement EnumConverter utility

**Week 5-6: Core Transformation (Simple → Medium)**
- [ ] Implement 60% simple mappings (direct copy)
- [ ] Implement medium complexity transformations (code conversion)
- [ ] Unit test E enum conversions
- [ ] Integration test simple convXXX() methods

**Week 7-8: Complex Transformation**
- [ ] Implement aggregation (N:1) transformations
- [ ] Implement pivot (vertical↔horizontal) transformations
- [ ] Implement primary key transformations
- [ ] Handle null/missing data scenarios

### Phase 3: Image Migration (Week 9)

- [ ] Implement ImageUtil (TIFF/GIF/PDF → JPEG conversion)
- [ ] Implement file copy logic
- [ ] Test format conversion
- [ ] Handle large files (>10MB)
- [ ] Test parallel file operations

### Phase 4: Testing (Week 10-12)

**Unit Testing**
- [ ] Test each convXXX() method with sample data
- [ ] Test E enum conversions (all codes)
- [ ] Test edge cases (null, empty, invalid codes)

**Integration Testing**
- [ ] Test full migration flow (1 range)
- [ ] Test parallel execution (15 batches)
- [ ] Test error recovery (retry logic)
- [ ] Test image migration (all formats)

**Performance Testing**
- [ ] Measure throughput (records/minute)
- [ ] Optimize slow queries
- [ ] Tune batch size, range size
- [ ] Test with production-size data volume

### Phase 5: UAT & Cutover (Week 13-16)

- [ ] UAT with business users
- [ ] Data validation (count, sum, sample check)
- [ ] Fix defects
- [ ] Prepare cutover runbook
- [ ] Rehearsal migration
- [ ] Production cutover (October)

---

## ✅ Success Criteria

### Data Quality
- [ ] 100% of completed reviews migrated (事前審査完了 + 正式審査完了)
- [ ] 0% data loss (validate record counts)
- [ ] All code conversions successful (E enum mapping)
- [ ] All images converted and accessible
- [ ] Primary key integrity maintained

### Performance
- [ ] Process 10,000 applications in < 4 hours
- [ ] Parallel execution (15 batches) with no deadlocks
- [ ] Memory usage < 2GB per batch instance
- [ ] Image conversion < 2 seconds per file

### Error Handling
- [ ] Failed records logged with details
- [ ] Failed ranges marked as ERROR
- [ ] Successful ranges continue despite individual record failures
- [ ] Retry capability for ERROR ranges

---

## 📝 Key Differences from MIGRATION_BATCH_C

| Aspect | MIGRATION_BATCH_C (Unsecured) | MORTGAGE_MIGRATION_BATCH (Housing) |
|--------|------------------------------|------------------------------------|
| **F Classification** | Range-based processing, HashMap cache, parallel execution | **✅ 100% REUSABLE** - Copy architecture patterns |
| **E Classification** | ~100 enums for unsecured loans | **50+ new enums** for housing loans (65-70% defined) |
| **Source Tables** | ~30 tables | **137 tables** (4x more) |
| **Target Tables** | ~15 tables | **Scope reuses same 552** |
| **Complexity** | Medium | **High** (more complex mappings) |
| **Images** | FAX + Image PR | **12 types + format conversion** |
| **Pivot Operations** | Minimal | **Multiple vertical↔horizontal** |
| **Aggregations** | Few | **Many N:1 consolidations** |
| **Migration Criteria** | All records | **Only completed reviews** |
| **PK Transformation** | Direct copy | **Add prefix + padding** |

---

## 🎯 Next Steps

### For Today's Meeting
1. **Present this design document**
2. **Discuss timeline**: 16-week plan realistic?
3. **Clarify unclear mappings**: Review yellow-highlighted items in Excel
4. **Confirm scope**: Any additional requirements?
5. **Resource allocation**: Team size, availability?

### Immediate Actions (This Week)

**Primary Focus: Learn F Classification Level (F分類レベル) from MIGRATION_BATCH_C**

As discussed in meeting: 
> "F分レベルというかどういう軌道、どういう手続きの順番でやってみたいなものは、こっちのバッチを模倣すれば割と補完できる"

**Week 1 Tasks (2-3 days):**

1. ✅ **Understand MIGRATION_BATCH_C Architecture (F Classification)**
   - Read `MutanpoService.java` - understand processing flow
   - Study `processAll()` → `claimNextRange()` → `processOneRange()` pattern
   - Learn HashMap caching strategy to prevent N+1 queries
   - Understand FOR UPDATE SKIP LOCKED parallel execution control
   - Review transaction boundaries and error handling

2. ✅ **Learn Transformation Patterns (F Classification)**
   - How `convXXX()` methods are structured
   - Where E classification enums are applied
   - How related records are aggregated
   - How images are migrated

3. ✅ **Environment Setup**
   - Connect via VMware Horizon (dual-factor auth)
   - SVN checkout of data migration folder
   - Open project in Eclipse
   - Verify build with Gradle

4. ⏳ **Review Specifications (E Classification - 65-70% complete)**
   - Study 詳細設計書_住宅ローンデータ移行.xlsx
   - Review yellow-highlighted items (clarifications needed)
   - Note E classification code mappings
   - Identify unclear transformations

5. 💬 **Ask Questions via Slack**
   - Contact: おいだ (Oida-san)
   - Don't hesitate to ask about unclear specs
   - Request access to any missing documents

**Key Point:** You can start implementing F classification patterns (architecture, flow, procedures) WITHOUT waiting for 100% complete E classification (code mappings). E classifications can be added incrementally as specs are finalized.

### Open Questions to Clarify
1. **Range size:** 1000 records per range OK? (Total ~10,000 applications estimated?)
2. **Image volume:** How many GB of images? Need to estimate conversion time.
3. **Code mapping completeness:** Are all E enum mappings defined in Excel, or do we need to discover more?
4. **Master data:** Which 1 master table is migrated? (Spec says only 1 master)
5. **Error tolerance:** What % of failed records is acceptable? (Target: <1%?)
6. **Cutover window:** How many hours available for production migration?
7. **Rollback plan:** If migration fails, what's the rollback procedure?

---

## 📚 Reference Documents

1. **詳細設計書_住宅ローンデータ移行.xlsx** - Main specification
2. **住宅ローン審査システムER図.a5er** - Source system ER diagram  
3. **MIGRATION_BATCH_C source code** - Reference implementation
4. **Meeting notes (2026/05/21)** - Business context and requirements
5. **Scope ER diagram** - Target system structure (552 tables)

---

**Document Version:** 1.0  
**Last Updated:** 2026-05-21  
**Author:** Migration Team  
**Status:** Draft for Review

---

## 💡 Tips from MIGRATION_BATCH_C Experience

### F Classification Learning (Start Here!)
1. **Focus on F classification first** - Learn architecture, flow, procedures from MIGRATION_BATCH_C
2. **Read MutanpoService.java thoroughly** - This shows you the complete F classification pattern
3. **Understand the processing flow** - processAll() → claimNextRange() → processOneRange() is your blueprint
4. **HashMap caching is critical** - Master this pattern to avoid N+1 queries at all costs
5. **FOR UPDATE SKIP LOCKED is key** - This enables parallel execution without conflicts

### E Classification Implementation
6. **Start with E enums early** - They're used everywhere in convXXX() methods, but can be added incrementally
7. **Don't wait for 100% specs** - Implement F classification patterns first, add E enums as mappings are defined
8. **Use placeholder enums** - Create stub enums with "TODO" comments for incomplete mappings

### Testing & Quality
9. **Log everything** - You'll need detailed logs to debug production issues
10. **Test with real data** - Edge cases only appear with production-like data
11. **Test parallel execution thoroughly** - Deadlocks and race conditions are hard to reproduce
12. **Code review essential** - Complex transformations need peer review

### Production Readiness
13. **Image migration is slow** - Consider separate batch job or increase parallelism
14. **Don't batch completion updates** - Mark ranges DONE immediately to avoid reruns
15. **Monitor memory usage** - HashMap caching can consume lots of memory with large datasets
16. **Plan for rollback** - Have a clear rollback strategy before production cutover

**Remember:** F classification (how to process) can be learned from MIGRATION_BATCH_C today. E classification (what codes to map) will be completed incrementally. Don't block on specs!

Good luck with your migration project! 🚀
