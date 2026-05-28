# MIGRATION_BATCH_C - Data Migration Overview

## 📊 Migration Summary
This project migrates **unsecured loan application data (無担保ローン申込データ)** from the legacy **AMIGO_C system** to the new **Scope/SMS system**.

---

## 🔄 Data Flow: From Where to Where

### Source System: AMIGO_C (移行元)
**Database:** AMIGO_C (Legacy unsecured loan centralized system)

**Input Tables:**
- `VJudgeMain` (審査状況) - Application review status
- `VEntryMain` (申込基本) - Application basic information
- `VEntryLoan` (ローン情報) - Loan information
- `VEntryEtc` (その他情報) - Other information
- `VEntrySub` (補助情報) - Supplementary information
- `VEntryOffice` (勤務先情報) - Office information
- `VJudgeResult` (審査結果) - Review results
- `VSaisonResult` (セゾン結果) - Saison results
- `VBanktrade` (振込先) - Bank transfer information
- `VJudgeGuarant` (保証コメント) - Guarantee comments
- `VImgpr` (イメージ) - Image data
- `VSendfaxExt` (FAX送信) - FAX transmission
- `VWebdata` (Web申込) - Web applications
- `MUser` (ユーザーマスタ) - User master
- `MGood` (商品マスター) - Product master

### Target System: Scope/SMS (移行先)
**Database:** Scope/SMS (New loan management system)

**Output Tables:**
- `申込審査状況` - Application review status
- `申込進捗` - Application progress
- `申込` - Application
- `申込審査段階` - Application review stage
- `申込審査履歴` - Application review history
- `審査結果` - Review results
- `審査コメント` - Review comments
- `審査コメント表示ユーザ区分` - Review comment display user classification
- `申込__振込先__無担保` - Application transfer destination (unsecured)
- `申込__無担保__教育` - Application unsecured (education)
- `申込顛末管理` - Application outcome management
- `Fax受信振分` - FAX reception distribution
- `申込徴求資料` - Application required documents
- `商品マスター` - Product master

### Migration Control
**Control Table:** `移行管理テーブル` (Migration Control Table)
- Manages migration ranges and status
- Tracks processing from/to ranges
- Status: TODO → RUNNING → DONE/ERROR

---

## 🛠 Technologies Used

### Framework & Libraries
- **Spring Boot** - Application framework
- **Spring Batch** - Batch processing framework
- **MyBatis** - Database ORM (NOT JPA, despite your mention)
- **Oracle JDBC Driver** - Database connectivity
- **Lombok** - Reduces boilerplate code
- **SLF4J + Logback** - Logging

### Architecture Pattern
- **Tasklet-based Batch Processing** (not Chunk-based)
- **Dual DataSource Configuration** (multi-database)
- **Transaction Management** with `@Transactional`
- **Range-based Processing** with optimistic locking

---

## 🏗 Batch Design Architecture

### Processing Flow

```
1. MigrationBatchApplication (START)
   ↓
2. MigrationTasklet.execute()
   ↓
3. MutanpoService.processAll()
   - Load MUser, MGood, 商品マスター into memory cache
   ↓
4. LOOP: While (未処理レコードあり)
   ↓
5. MutanpoService.claimNextRange()
   - SELECT FOR UPDATE SKIP LOCKED (status=TODO)
   - UPDATE status=RUNNING
   - Return range (処理from, 処理to)
   ↓
6. MutanpoService.processOneRange(range)
   - SELECT data from AMIGO_C by range
   - Create HashMap collections for related data
   - FOR EACH 申込番号 in range:
     ├─ Load all related records
     ├─ Call convXXX() transformation functions
     ├─ INSERT into Scope/SMS tables
     └─ Handle errors (continue on failure)
   ↓
7. markDone(range) or markError(range)
   - UPDATE status=DONE/ERROR
   ↓
8. END LOOP
```

### Range-based Processing Strategy
- **Range Size:** Configurable (e.g., 1000-5000 records per range)
- **Parallel Processing:** Multiple batch instances can run simultaneously
- **Lock Strategy:** `SELECT FOR UPDATE SKIP LOCKED` prevents race conditions
- **Error Isolation:** Failed range marked as ERROR, others continue

### Status Flow
```
TODO (未処理) → RUNNING (処理中) → DONE (正常終了) / ERROR (エラー)
```

---

## 🗺 Data Mapping Design

### Transformation Functions (convXXX)
Located in `MutanpoService.java`, these methods transform old data to new format:

**Core Transformation Methods:**
- `convMoushikomiShinsajyoukyou()` - Application review status
- `convMoushikomiShinchoku()` - Application progress  
- `convMoushikomi()` - Main application data
- `convMoushikomiShinsadankai()` - Review stage
- `convMoushikomiShinsarireki()` - Review history
- `convShinsakekka()` - Review results
- `convShinsaComment()` - Review comments
- `convMoushikomiFurikomisaki()` - Transfer destination
- `convMoushikomiKyoiku()` - Education loan data
- `convImagepr()` - Image data
- `convFaxJyushinFuriwake()` - FAX reception data

### Mapping Pattern
```java
// Load related data into HashMaps (Key: RequestNo)
HashMap<Long, VJudgeMain> judgeMainMap
HashMap<Long, VEntryData> entryMainMap
HashMap<Long, List<VJudgeResult>> judgeResultMap

// For each application:
for (申込番号 in range) {
    // 1. Load old records
    VJudgeMain oldRecord = judgeMainMap.get(申込番号);
    VEntryData oldEntry = entryMainMap.get(申込番号);
    
    // 2. Apply E classification conversion
    String newCode = E商品統合.fromOldCode(oldCode).getNewCode();
    
    // 3. Create new records
    申込 newRecord = convMoushikomi(oldEntry, ...);
    
    // 4. Insert to target
    moushikomiMapper.insert(newRecord);
}
```

---

## 📐 E Classification Level - What is "E" and Its Role?

### What is "E" Classification?

**E = Enum (Enumeration)** - Code conversion mapping classes

**Purpose:** Transform old system codes to new system codes

### E Class Structure
```java
public enum E商品統合 implements ConvertibleEnum {
    商品_A("OLD01", "旧商品A", "NEW01", "新商品A"),
    商品_B("OLD02", "旧商品B", "NEW02", "新商品B");
    
    private final String oldCode;  // AMIGO_C code
    private final String oldName;  // AMIGO_C name
    private final String newCode;  // Scope/SMS code
    private final String newName;  // Scope/SMS name
}
```

### E Classification Categories

Based on your project structure, E classes handle:

**Application Type Classifications:**
- `E申込目的` - Application purpose
- `E商品統合` - Product consolidation
- `E商品性区分` - Product nature classification
- `E資金使途` - Fund usage

**Personal Information Classifications:**
- `E住居形態` - Residence type (0-6 variants by form type)
- `E勤務先職業` - Occupation (0-6 variants)
- `E役職` - Position/Title (0-6 variants)
- `E従業員数` - Number of employees (0-6 variants)
- `E業種` - Industry type (0-6 variants)

**Review & Result Classifications:**
- `E審査結果` - Review result
- `E審査区分` - Review classification
- `E保証会社統合` - Guarantee company consolidation
- `E進歩` - Progress status (multiple variants)

**Financial Classifications:**
- `E借入担保` - Loan collateral
- `E借入用途` - Loan purpose
- `E返済用口座科目` - Repayment account type

**Others:**
- `E希望連絡先` - Preferred contact
- `E受付チャネル` - Reception channel
- `E健康保険証種類` - Health insurance type

### Form Type Variants (0-6 Suffix)

Many E classes have 7 variants corresponding to different application forms:

| Suffix | Form Type |
|--------|-----------|
| 0 | 通常申込書 (Normal Application) / BQL |
| 1 | セレカ申込書 (Sereca Application) |
| 2 | オリックス申込書 (ORIX Application) |
| 3 | ジャックス申込書 (JACCS Application) |
| 4 | 集中実行申込書 (Centralized Execution) |
| 5 | SDC申込書 (SDC Application) |
| 6 | セレカミニ (Sereca Mini) |

### Conversion Pattern
```java
// Example: Convert product code
try {
    E商品統合 eShohin = E商品統合.fromOldCode(oldProductCode);
    String newProductCode = eShohin.getNewCode();
    String newProductName = eShohin.getNewName();
} catch (IllegalArgumentException e) {
    // Handle unmapped code
    logger.error("Cannot convert product code: {}", oldProductCode);
}
```

---

## 🔄 Processing Turn & Order

### Batch Execution Order

**15 Batch Scripts** (`batch01.bat` ~ `batch15.bat`)

Each batch script:
- Sets different Spring profiles (`-Dspring.profiles.active=local,batch1`)
- Can process different data ranges or types
- Runs independently or in parallel

### Processing Stages (PROCESS Configuration)

Controlled by `batch.process` property:

| PROCESS | Stage | Tables Processed |
|---------|-------|------------------|
| 0 | **Full Process** | All tables including images |
| 1 | **Core Tables** | 申込進捗, 申込, 申込審査状況, 申込審査段階 |
| 2 | **History & Results** | 申込審査履歴, 審査結果, 審査コメント |
| 3 | **Optional Tables** | 申込__振込先__無担保, 申込__無担保__教育 |

### Kind Configuration

Controlled by `batch.kind` property:

| KIND | Type | Behavior |
|------|------|----------|
| 1 | **Bank** | Skip guarantee company comments, process images |
| 2 | **Guarantee Company** | Process comments, skip images |

### Execution Turn Logic

```java
// Stage 0: Process progress
if ("0".equals(PROCESS)) {
    moushikomiShinchokuMapper.insert(moushikomiShinchoku);
}

// Stage 1: Core tables
if ("1".equals(PROCESS) || "0".equals(PROCESS)) {
    moushikomiMapper.insert(moushikomi);
    moushikomiShinsaJyokyoMapper.insert(moushikomiJyokyo);
    moushikomiShinsadankaiMapper.insert(moushikomiDankai);
}

// Stage 2: History & results
if ("2".equals(PROCESS) || "0".equals(PROCESS) || "1".equals(PROCESS)) {
    moushikomiShinsarirekiMapper.insert(record);
    shinsaKekkaMapper.insert(record);
    
    if ("2".equals(kind)) { // Guarantee company only
        shinsaCommentMapper.insert(shinsaComment);
    }
}

// Stage 3: Optional tables
if ("3".equals(PROCESS) || "0".equals(PROCESS) || "1".equals(PROCESS)) {
    moushikomiFurikomisakiMapper.insert(moushikomiFurikomisaki);
    moushikomiKyoikuMapper.insert(moushikomiKyoiku);
}

// Images (PROCESS=0 only)
if ("0".equals(PROCESS)) {
    // Process images and FAX data
}
```

---

## 📂 Project Structure Overview

```
MIGRATION_BATCH_C/
├── src/main/java/migration/
│   ├── MigrationBatchApplication.java      # Main entry point
│   ├── batch/
│   │   └── MigrationTasklet.java           # Batch tasklet executor
│   ├── service/
│   │   └── MutanpoService.java             # Core migration service
│   ├── config/
│   │   ├── BatchConfig.java                # Spring Batch configuration
│   │   ├── AmigoCDataSourceConfig.java     # Source DB config
│   │   └── SmsDataSourceConfig.java        # Target DB config
│   ├── common/
│   │   ├── ConvertibleEnum.java            # Enum interface
│   │   ├── EnumConverter.java              # Enum conversion utility
│   │   ├── DateUtil.java                   # Date utilities
│   │   ├── MyUtil.java                     # General utilities
│   │   └── amigo_0149_c/                   # E classification enums
│   │       ├── E商品統合.java
│   │       ├── E審査結果.java
│   │       └── ... (100+ enum classes)
│   └── mybatis/
│       ├── domain/                         # Entity classes
│       │   ├── amigo_0149_c/               # Source entities
│       │   └── sms/                        # Target entities
│       └── mapper/                         # MyBatis mappers
│           ├── amigo_0149_c/               # Source DB mappers
│           └── sms/                        # Target DB mappers
├── src/main/resources/
│   ├── application.yml                     # Configuration
│   └── migration/mybatis/mapper/           # SQL mapper XMLs
│       ├── amigo_0149_c/
│       └── sms/
├── gen/                                    # MyBatis generated code
├── batch/                                  # Batch execution scripts
│   ├── batch01.bat ~ batch15.bat
└── libs/                                   # External JAR files
```

---

## 🎯 Key Design Patterns

### 1. **Dual DataSource Pattern**
- Separate `DataSource` for source and target
- `@Primary` on target (SMS) for default operations
- `@Qualifier` to distinguish datasources

### 2. **Range-based Batch Processing**
- Divide large dataset into manageable ranges
- Process ranges independently
- Support parallel execution

### 3. **HashMap Caching Strategy**
- Load related data into HashMap once per range
- Avoid N+1 query problem
- Key: RequestNo (申込番号)

### 4. **Enum-based Code Conversion**
- Type-safe code mapping
- Centralized conversion logic
- Easy to maintain and extend

### 5. **Error Handling Strategy**
- **Continue on Error:** Log and skip failed records
- **Range-level Error:** Mark entire range as ERROR
- **Transaction Isolation:** Each range is independent

### 6. **File Migration Pattern**
- Copy image files during data migration
- Maintain file path references
- Organize by date folders (YYYYMM)

---

## 🚀 Execution Example

### Command Line Execution
```batch
cd %~dp0
set CLASSPATH="bin/main;libs\*.jar"
java.exe -Xmx512m -Xms512m ^
  -Dspring.profiles.active=local,batch1 ^
  -classpath %CLASSPATH% ^
  migration.MigrationBatchApplication ^
  --spring.output.ansi.enabled=always
```

### Configuration (application.yml)
```yaml
spring:
  datasource:
    amigoc:                            # Source DB
      url: jdbc:oracle:thin:@host:port:SID
      username: amigoc_user
      password: ***
    sms:                               # Target DB
      url: jdbc:oracle:thin:@host:port:SID
      username: sms_user
      password: ***

batch:
  process: "0"                         # 0=All, 1=Core, 2=History, 3=Optional
  kind: "1"                            # 1=Bank, 2=Guarantee
  faximagedir: "C:/images/fax/"
  imgbasedir: "C:/images/img/"
  imgoutdir: "C:/output/img/"
  faxoutdir: "C:/output/fax/"
```

---

## 📊 Migration Statistics Tracking

The system tracks:
- **Range Status:** TODO/RUNNING/DONE/ERROR
- **Processing Time:** 開始日時 (start), 終了日時 (end)
- **Record Count:** 処理from, 処理to
- **Success/Failure:** Per record and per range

---

## 🔍 For Your New Task

When starting your new data migration task, you should:

1. **Understand Source & Target**
   - Identify source database and tables
   - Identify target database schema
   - Map entity relationships

2. **Create E Classifications**
   - Identify all code fields requiring conversion
   - Create enum classes implementing `ConvertibleEnum`
   - Populate old code → new code mappings

3. **Design Range Strategy**
   - Determine range size (balance between throughput and error isolation)
   - Create migration control table
   - Plan parallel execution strategy

4. **Implement convXXX() Methods**
   - One method per target table
   - Handle data type conversions
   - Apply business rules
   - Handle null/missing data

5. **Configure Batch Jobs**
   - Set up dual datasources
   - Configure transaction boundaries
   - Plan error handling strategy

6. **Test & Validate**
   - Unit test conversion functions
   - Integration test with sample data
   - Validate data completeness
   - Check referential integrity

---

## 📌 Summary

| Aspect | Details |
|--------|---------|
| **From** | AMIGO_C (legacy unsecured loan system) |
| **To** | Scope/SMS (new loan management system) |
| **Method** | Spring Batch + MyBatis range-based processing |
| **Technology** | Spring Boot, MyBatis, Oracle JDBC |
| **Design** | Tasklet-based batch with dual datasource |
| **Mapping** | convXXX() transformation functions |
| **E Classification** | Enum-based code conversion (100+ enums) |
| **Processing** | Range-based with parallel execution support |
| **Turn** | Configurable stages (PROCESS 0-3, KIND 1-2) |
| **Control** | 移行管理テーブル (TODO→RUNNING→DONE/ERROR) |

---

Good luck with your new data migration task! 🎯
