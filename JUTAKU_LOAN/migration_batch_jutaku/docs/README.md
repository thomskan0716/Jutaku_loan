# Migration Batch Jutaku
## 住宅ローン審査システム → SCOPE移行バッチ

⚠️ **VM Environment**: Java 8, NetBeans IDE, No Network Connection  
🏠 **Development**: Home PC (VS Code + AI) → Copy → VM (NetBeans + Run)

---

## 📁 Project Structure

```
migration_batch_jutaku/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── migration/
│   │   │       ├── MigrationBatchApplication.java    ← Main entry point
│   │   │       ├── batch/                             ← Spring Batch layer
│   │   │       │   └── 移行管理Tasklet.java         (Parallel processing via 移行管理 table)
│   │   │       ├── common/
│   │   │       │   └── szh_sms/                      ← E-Level enums
│   │   │       │       ├── E申込目的.java
│   │   │       │       └── E連絡コード.java
│   │   │       ├── config/                            ← Spring configs
│   │   │       │   ├── BatchConfig.java              (Spring Batch Job/Step)
│   │   │       │   ├── MyBatchConfigurer.java        (Batch metadata DB)
│   │   │       │   ├── SourceDataSourceConfig.java   (Old system)
│   │   │       │   └── TargetDataSourceConfig.java   (New system)
│   │   │       ├── domain/                            ← Entity classes
│   │   │       │   ├── source/
│   │   │       │   │   └── ApplicationSource.java    (11 columns)
│   │   │       │   └── target/
│   │   │       │       └── ApplicationTarget.java    (11 ○ columns)
│   │   │       ├── mapper/                            ← MyBatis interfaces
│   │   │       │   ├── source/
│   │   │       │   │   └── ApplicationSourceMapper.java
│   │   │       │   └── target/
│   │   │       │       └── ApplicationTargetMapper.java
│   │   │       └── service/                           ← F-Level service
│   │   │           └── ApplicationMigrationService.java
│   │   └── resources/
│   │       ├── application.yml                        ← Config
│   │       └── migration/mybatis/mapper/
│   │           ├── source/
│   │           │   └── ApplicationSourceMapper.xml
│   │           └── target/
│   │               └── ApplicationTargetMapper.xml
│   └── test/
│       └── java/
│           └── migration/
├── batch/                                             ← Batch scripts
│   ├── run.bat                                       (Single instance)
│   ├── batch01.bat                                   (Parallel template)
│   ├── create_batches.bat                            (Generate 15 instances)
│   ├── compile_for_vm.bat                            (Compile for VM)
│   ├── export_structure.bat                          (Oracle dump export)
│   └── import_structure.bat                          (Oracle dump import)
├── build.gradle                                       ← Gradle config
├── settings.gradle
└── README.md                                          ← This file
```

---

## 🚀 **Development Workflow**

### **Environment**

| Environment | Details |
|-------------|----------|
| **Home PC** | VS Code + GitHub Copilot, Network ✅, Java 17 ✅ |
| **VM** | NetBeans IDE, Network ❌, Java 8 (jdk1.8.0_45) |
| **VM Path** | `C:\Users\sun.ykawakamit\Desktop\静岡銀行_S-plus\branches\新帰属入\050000_データ移行#JTAKU_LOAN\migration_batch_JUTAKU` |
| **libs** | Already exists in VM (~100MB, 40+ JARs) |

---

## **🔄 Development Cycle**

### **Step 1: Code on Home PC** (with Network + AI)

```bash
# D:\SunTrust\tanaka\module\MIGRATION_BATCH_C\JUTAKU_LOAN\migration_batch_jutaku
```

1. Open VS Code
2. Use GitHub Copilot for coding assistance
3. Test syntax and logic
4. Copy files to USB/shared folder:
   - `.java` files
   - `.xml` mappers
   - `application.yml`
   - `.bat` scripts

---

### **Step 2: Paste in VM** (No Network)

```bash
# C:\Users\sun.ykawakamit\Desktop\静岡銀行_S-plus\branches\新帰属入\050000_データ移行#JTAKU_LOAN\migration_batch_JUTAKU
```

1. Paste files to VM project folder
2. Maintain same folder structure

---

### **Step 3: Build in NetBeans** (VM)

**Option A: NetBeans IDE** ⭐ **RECOMMENDED** ⭐

1. Open NetBeans
2. File → Open Project
3. Select migration_batch_JUTAKU folder
4. Properties → Libraries → Add JAR/Folder → Select all `libs\*.jar`
5. Right-click project → Clean and Build

**Option B: Command Line**

```batch
cd batch
compile_for_vm.bat
```

📖 **Detailed Guide**: See `NETBEANS_SETUP.txt` in project root

---

### **Step 4: Configure Database** (VM)

Edit `src\main\resources\application.yml`:

```yaml
spring:
  datasource:
    source:
      password: <actual_vm_password>   ← Change this!
    target:
      password: <actual_vm_password>   ← Change this!
```

---

### **Step 5: Run Migration** (VM)

**Single instance (testing):**
```batch
cd batch
run.bat
```

**15 parallel instances (production):**
```batch
cd batch
create_batches.bat

# Then open 15 Command Prompt windows:
batch01.bat
batch02.bat
...
batch15.bat
```

Each instance processes different ID ranges using `FOR UPDATE SKIP LOCKED`.

---

## 🔧 Library Versions

### VM Environment (Actual Runtime)
```
Java: 8 (jdk1.8.0_45)
Spring Boot: 2.7.10 (from libs folder)
Spring Batch: 4.3.8 (from libs folder)
MyBatis: 3.5.9 (from libs folder)
Oracle JDBC: ojdbc8.jar
```

### build.gradle (Reference Only)
```gradle
Java: 1.8 (sourceCompatibility)
Spring Boot: 3.2.0
MyBatis: 3.0.3
```

**Note**: `build.gradle` is for reference and Home PC testing only. VM uses existing `libs/` folder from parent project.

---

## ✅ Week 3-4 Implementation Status

### ✅ Completed

**Spring Batch Layer** (matches parent MIGRATION_BATCH_C):
- [x] 移行管理Tasklet.java (parallel processing via 移行管理 table)
- [x] BatchConfig.java (@EnableBatchProcessing, Job/Step definition)
- [x] MyBatchConfigurer.java (Spring Batch metadata configuration)

**Service Layer** (F-Level Pattern):
- [x] ApplicationMigrationService.java
  - [x] processAll() - Master data caching
  - [x] claimNextRange() - FOR UPDATE SKIP LOCKED simulation
  - [x] processOneRange() - Main transformation logic
  - [x] markDone() - Success tracking
  - [x] markError() - Error tracking

**E-Level Enums**:
- [x] E申込目的.java (Application Purpose enum)
- [x] E連絡コード.java (Connection Code enum)

**Data Layer**:
- [x] ApplicationSource.java (11 columns)
- [x] ApplicationTarget.java (11 ○ columns only)
- [x] ApplicationSourceMapper.java + XML
- [x] ApplicationTargetMapper.java + XML

**Configuration**:
- [x] Project structure created
- [x] Gradle build configuration (build.gradle, settings.gradle)
- [x] application.yml with dual datasource config
- [x] MigrationBatchApplication.java
- [x] SourceDataSourceConfig.java
- [x] TargetDataSourceConfig.java

**Batch Scripts**:
- [x] run.bat (single instance)
- [x] batch01.bat (parallel template)
- [x] create_batches.bat (generates batch01-15.bat)
- [x] compile_for_vm.bat (compile for VM)
- [x] export_structure.bat
- [x] import_structure.bat

### 📋 Next Steps (Week 3 Day 1-2)
1. Configure database connections in application.yml (VM)
2. Run export_structure.bat on source system (VM)
3. Run import_structure.bat on target system (VM)
4. Verify empty tables created

### 📋 Week 3 Day 3-5
1. Code on Home PC with AI assistance
2. Copy code to VM
3. Build in NetBeans (or compile_for_vm.bat)
4. Run run.bat for testing
5. Verify results in target DB

### 📋 Week 4
1. Implement 申込審査状況 (Review Status) migration
2. Debug and optimize 申込審査状況 migration

---

## 🎯 Confirmed Columns (from Excel)

### 申込 (Application) - 11 columns with ○ on RIGHT side
1. 申込番号 (PK)
2. 申込日
3. 融資申込番号
4. 関連番
5. 連絡コード (E連絡コード conversion)
6. 経済記
7. 申込履歴号
8. 申込日付
9. 審査履歴
10. 日付 (E申込目的 conversion)
11. イベント

**Note**: Row 2011 (イベント日時) has **"-"** mark, NOT migrated

---

## 🔧 Configuration (VM Only)

### Database Connections

Edit `src/main/resources/application.yml` **in VM**:

```yaml
spring:
  datasource:
    source:
      jdbc-url: jdbc:oracle:thin:@E00197SV0203:1521:SZH_SMS
      username: SZH_SMS
      password: <actual_vm_password>    ← Change this in VM!
    target:
      jdbc-url: jdbc:oracle:thin:@E00736SV0001:1521:SZH_SMS
      username: SZH_SMS
      password: <actual_vm_password>    ← Change this in VM!
```

---

## 🧪 How to Test (VM)

### Step 1: Create test data on source DB
```sql
-- E00197SV0203/SZH_SMS
INSERT INTO 申込 (申込番号, 申込日, 申込目的) VALUES ('TEST00000001', '20260603', '10');
INSERT INTO 申込 (申込番号, 申込日, 申込目的) VALUES ('TEST00000002', '20260603', '90'); -- Should skip
COMMIT;
```

### Step 2: Run test batch (VM)
```batch
cd batch
run.bat
```

### Step 3: Verify results on target DB
```sql
-- E00736SV0001/SZH_SMS
SELECT * FROM 申込 WHERE 申込番号 LIKE 'TEST%';
-- Should show TEST00000001 only (TEST00000002 skipped due to 申込目的=90)
```claimNextRange() → processOneRange() → markDone/markError()
5. **Spring Batch Framework**: Uses same architecture as parent MIGRATION_BATCH_C
   - @EnableBatchProcessing triggers Job on startup
   - 移行管理Tasklet implements parallel processing via FOR UPDATE SKIP LOCKED
   - Supports parallel execution with test1.bat, test2.bat, test3.bat

---

## 📚 References

- **NetBeans Setup**: `NETBEANS_SETUP.txt` (VM IDE configuration)
- **Deployment**: `batch/DEPLOYMENT.txt` (Home PC → VM workflow)
- **Batch Scripts**: `batch/BATCH_SCRIPTS.txt` (All scripts overview)
- **Manual**: `../project_manual/WEEK3-4_BASIC_IMPLEMENTATION_MANUAL.md`
- **Project Plan**: `../project_manual/JUTAKU_LOAN_PLAN.md`
- **Excel**: `詳細設計書_住宅ローンデータ移行.xlsx` (Check RIGHT side ○)
- **Parent Code**: `../../MIGRATION_BATCH_C/` (F-Level pattern reference)

---

## 🚨 Important Notes

1. **RIGHT Side Focus**: Only columns with ○ on RIGHT side of Excel are implemented
2. **Migration Criteria**: Skip 申込目的=90 (途上与信) and 審査中 records
3. **E-Level Conversions**: 
   - E申込目的: 10→10, 15→10, 20→20, 30→20, 90→skip
   - E連絡コード: JR prefix handling (TODO: Add actual mappings)
4. **F-Level Pattern**: processAll() → processOneRange() → transform()

---

## 📞 Need Help?

- **Technical questions**: おいだ (Slack)
- **Pattern reference**: MIGRATION_BATCH_C on SVN
- **Excel questions**: Check RIGHT side (移行システム) column

---

**Week 3-4 Goal**: Complete 申込, 申込審査状況 migration  
**Current Status**: Week 3 Day 3 - Project setup completed ✅
