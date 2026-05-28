# Week 3-4 Basic Implementation Manual
## 基礎実装 (Core Tables Migration)

**Period**: Week 3-4 (June 3-14, 2026)  
**Goal**: Implement migration for 2 core tables (申込, 申込審査状況) with basic E-level enums  
**Status**: ✅ Excel columns confirmed, ready for implementation

---

## 📊 Confirmed Columns Summary

### 申込 (Application) - 11 columns with ○ on RIGHT side
- ✅ 申込番号, 申込日, 融資申込番号
- ✅ 関連番, 連絡コード, 経済記
- ✅ 申込履歴号, 申込日付, 審査履歴, 日付, イベント

### E-Level Enums Required
- ✅ E申込目的 (Application Purpose) - for 日付 field conversion
- ✅ E連絡コード (Connection Code) - for 連絡コード field conversion

---

## 📋 Week Overview

### Week 3 (June 3-7)
- **Day 1-2**: Oracle Dump Export/Import (table structure creation)
- **Day 3**: Project setup and E-level enum implementation
- **Day 4-5**: 申込 (Application) table migration
- **Day 6-7**: Testing and debugging

### Week 4 (June 10-14)
- **Day 1-2**: 申込審査状況 (Review Status) implementation
- **Day 3-4**: 申込審査状況 testing and debugging
- **Day 5**: Integration testing (申込 + 申込審査状況)
- **Day 6-7**: Code review and documentation

---

## 📊 Target Tables for Week 3-4

| Table Name | Japanese | Old System Rows | New System Rows | Priority |
|------------|----------|-----------------|-----------------|----------|
| 申込 | Application | ~200 columns | Check RIGHT side ○ | P1 - Week 3 |
| 申込審査状況 | Review Status | ~50 columns | Check RIGHT side ○ | P2 - Week 4 |

**Important**: Only implement columns with ○ checkmarks on **RIGHT side** (移行システム/New System)

---

# Week 3 Day 1-2: Table Structure Setup

## Step 1: Verify Source Tables

```sql
-- Connect to E00197SV0203/SZH_SMS (Old System)

-- Check table existence
SELECT table_name, num_rows 
FROM user_tables 
WHERE table_name IN ('申込', '申込審査状況')
ORDER BY table_name;

-- Check column count
SELECT table_name, COUNT(*) as column_count
FROM user_tab_columns
WHERE table_name IN ('申込', '申込審査状況')
GROUP BY table_name
ORDER BY table_name;
```

---

## Step 2: Export Table Structure

**File**: `batch\export_structure.bat`

```batch
@echo off
REM ===============================================
REM Export Table Structure Only (METADATA_ONLY)
REM Source: E00197SV0203/SZH_SMS
REM Date: 2026-06-03
REM ===============================================

setlocal

set ORACLE_HOME=C:\oracle\product\12.2.0\dbhome_1
set ORACLE_SID=E00197SV0203
set PATH=%ORACLE_HOME%\bin;%PATH%

set DUMP_DIR=D:\SunTrust\tanaka\dumps
set DATE_STR=%DATE:~0,4%%DATE:~5,2%%DATE:~8,2%
set DUMP_FILE=JUTAKU_STRUCTURE_%DATE_STR%.dmp
set LOG_FILE=JUTAKU_EXPORT_%DATE_STR%.log

echo ================================================
echo Exporting Table Structure (Week 3-4 Core Tables)
echo ================================================

expdp userid=SZH_SMS/password@E00197SV0203 ^
    directory=DATA_PUMP_DIR ^
    dumpfile=%DUMP_FILE% ^
    logfile=%LOG_FILE% ^
    tables=申込,申込審査状況 ^
    content=METADATA_ONLY ^
    reuse_dumpfiles=YES

if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Export failed
    pause
    exit /b %ERRORLEVEL%
)

echo ================================================
echo Export completed successfully!
echo Dump file: %DUMP_FILE%
echo ================================================
pause

endlocal
```

**Execute**:
```
cd D:\SunTrust\tanaka\module\MORTGAGE_MIGRATION_BATCH\batch
export_structure.bat
```

---

## Step 3: Import to Target System

**File**: `batch\import_structure.bat`

```batch
@echo off
REM ===============================================
REM Import Table Structure to Target System
REM Target: E00736SV0001/SZH_SMS
REM Date: 2026-06-03
REM ===============================================

setlocal

set ORACLE_HOME=C:\oracle\product\12.2.0\dbhome_1
set ORACLE_SID=E00736SV0001
set PATH=%ORACLE_HOME%\bin;%PATH%

set DUMP_DIR=D:\SunTrust\tanaka\dumps
set DATE_STR=%DATE:~0,4%%DATE:~5,2%%DATE:~8,2%
set DUMP_FILE=JUTAKU_STRUCTURE_%DATE_STR%.dmp
set LOG_FILE=JUTAKU_IMPORT_%DATE_STR%.log

echo ================================================
echo Importing Table Structure to Target System
echo ================================================

impdp userid=SZH_SMS/password@E00736SV0001 ^
    directory=DATA_PUMP_DIR ^
    dumpfile=%DUMP_FILE% ^
    logfile=%LOG_FILE% ^
    tables=申込,申込審査状況 ^
    table_exists_action=REPLACE

if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Import failed
    pause
    exit /b %ERRORLEVEL%
)

echo ================================================
echo Import completed successfully!
echo ================================================
pause

endlocal
```

**Execute**:
```
cd D:\SunTrust\tanaka\module\MORTGAGE_MIGRATION_BATCH\batch
import_structure.bat
```

---

## Step 4: Verify Target Tables

```sql
-- Connect to E00736SV0001/SZH_SMS (New System)

-- Verify tables created
SELECT table_name, num_rows 
FROM user_tables 
WHERE table_name IN ('申込', '申込審査状況')
ORDER BY table_name;

-- Verify tables are empty
SELECT '申込' as table_name, COUNT(*) as record_count FROM 申込
UNION ALL
SELECT '申込審査状況', COUNT(*) FROM 申込審査状況;

-- Expected: all counts = 0
```

---

# Week 3 Day 3: Project Setup

## Step 1: Create Project Structure

```batch
cd D:\SunTrust\tanaka\module
mkdir MORTGAGE_MIGRATION_BATCH
cd MORTGAGE_MIGRATION_BATCH

REM Create folder structure
mkdir src\main\java\migration\batch
mkdir src\main\java\migration\common\szh_sms
mkdir src\main\java\migration\config
mkdir src\main\java\migration\domain\source
mkdir src\main\java\migration\domain\target
mkdir src\main\java\migration\mapper\source
mkdir src\main\java\migration\mapper\target
mkdir src\main\java\migration\service
mkdir src\main\resources
mkdir batch
mkdir libs

REM Copy dependencies from MIGRATION_BATCH_C
xcopy D:\SunTrust\tanaka\module\MIGRATION_BATCH_C\libs\* libs\ /E /I
copy D:\SunTrust\tanaka\module\MIGRATION_BATCH_C\build.gradle .
copy D:\SunTrust\tanaka\module\MIGRATION_BATCH_C\settings.gradle .
```

---

## Step 2: Create application.yml

**File**: `src\main\resources\application.yml`

```yaml
spring:
  datasource:
    source:
      jdbc-url: jdbc:oracle:thin:@E00197SV0203:1521:ORCL
      username: SZH_SMS
      password: ${SOURCE_DB_PASSWORD}
      driver-class-name: oracle.jdbc.OracleDriver
    target:
      jdbc-url: jdbc:oracle:thin:@E00736SV0001:1521:ORCL
      username: SZH_SMS
      password: ${TARGET_DB_PASSWORD}
      driver-class-name: oracle.jdbc.OracleDriver

mybatis:
  mapper-locations: classpath*:migration/mapper/**/*.xml
  type-aliases-package: migration.domain

logging:
  level:
    migration: DEBUG
    org.springframework.jdbc: DEBUG
```

---

## Step 3: Implement E-Level Enums

### E申込目的.java (Application Purpose)

**File**: `src\main\java\migration\common\szh_sms\E申込目的.java`

**Excel Reference**: Check annotation column (○) on RIGHT side showing conversion rules:
- 10: 事前審査 → 10: 事前審査
- 15: 事前簡易審査 → 10: 事前審査 (merge)
- 20: 正式審査 → 20: 正式審査
- 30: 正式簡易審査 → 20: 正式審査 (merge)
- 90: 途上与信 → NOT MIGRATED

```java
package migration.common.szh_sms;

/**
 * 申込目的 (Application Purpose) Code Conversion
 * 
 * Excel Reference: RIGHT side annotation showing:
 * - 10: 事前審査 → 10
 * - 15: 事前簡易審査 → 10 (merge)
 * - 20: 正式審査 → 20
 * - 30: 正式簡易審査 → 20 (merge)
 * - 90: 途上与信 → null (not migrated)
 */
public enum E申込目的 {
    
    事前審査("10", "10", true),
    事前簡易審査("15", "10", true),   // Merge to 10
    正式審査("20", "20", true),
    正式簡易審査("30", "20", true),   // Merge to 20
    途上与信("90", null, false);      // Not migrated
    
    private final String oldCode;
    private final String newCode;
    private final boolean migrationTarget;
    
    E申込目的(String oldCode, String newCode, boolean migrationTarget) {
        this.oldCode = oldCode;
        this.newCode = newCode;
        this.migrationTarget = migrationTarget;
    }
    
    public String getOldCode() {
        return oldCode;
    }
    
    public String getNewCode() {
        return newCode;
    }
    
    public boolean isMigrationTarget() {
        return migrationTarget;
    }
    
    /**
     * Convert old code to new code
     */
    public static String convert(String oldCode) {
        if (oldCode == null || oldCode.isEmpty()) {
            return null;
        }
        
        for (E申込目的 e : values()) {
            if (e.getOldCode().equals(oldCode)) {
                return e.getNewCode();
            }
        }
        
        System.err.println("WARNING: 申込目的 mapping not found: " + oldCode);
        return oldCode;
    }
    
    /**
     * Check if code should be migrated
     */
    public static boolean shouldMigrate(String oldCode) {
        if (oldCode == null || oldCode.isEmpty()) {
            return false;
        }
        
        for (E申込目的 e : values()) {
            if (e.getOldCode().equals(oldCode)) {
                return e.isMigrationTarget();
            }
        }
        
        return true; // Unknown codes are migrated by default
    }
}
```

---

### E連絡コード.java (Connection Code)

**File**: `src\main\java\migration\common\szh_sms\E連絡コード.java`

**Excel Reference**: Row 820 showing 連絡コード conversion with annotation about JR codes

```java
package migration.common.szh_sms;

/**
 * 連絡コード (Connection Code) Code Conversion
 * 
 * Excel Reference: Row 820 annotation
 * - Standard codes: Direct mapping
 * - JR codes: Special handling
 */
public enum E連絡コード {
    
    // Standard codes (add as needed based on Excel)
    // TODO: Add actual code mappings from Excel annotation
    
    ;
    
    private final String oldCode;
    private final String newCode;
    private final boolean migrationTarget;
    
    E連絡コード(String oldCode, String newCode, boolean migrationTarget) {
        this.oldCode = oldCode;
        this.newCode = newCode;
        this.migrationTarget = migrationTarget;
    }
    
    public String getOldCode() {
        return oldCode;
    }
    
    public String getNewCode() {
        return newCode;
    }
    
    public boolean isMigrationTarget() {
        return migrationTarget;
    }
    
    /**
     * Convert old code to new code
     */
    public static String convert(String oldCode) {
        if (oldCode == null || oldCode.isEmpty()) {
            return null;
        }
        
        // Check for JR codes (special handling based on Excel note)
        if (oldCode.startsWith("JR")) {
            // TODO: Implement JR code logic based on Excel annotation
            System.out.println("INFO: JR code detected: " + oldCode);
        }
        
        for (E連絡コード e : values()) {
            if (e.getOldCode().equals(oldCode)) {
                return e.getNewCode();
            }
        }
        
        // Default: return original code
        System.err.println("WARNING: 連絡コード mapping not found: " + oldCode);
        return oldCode;
    }
    
    /**
     * Check if code should be migrated
     */
    public static boolean shouldMigrate(String oldCode) {
        if (oldCode == null || oldCode.isEmpty()) {
            return false;
        }
        
        for (E連絡コード e : values()) {
            if (e.getOldCode().equals(oldCode)) {
                return e.isMigrationTarget();
            }
        }
        
        return true; // Unknown codes are migrated by default
    }
}
```

---

# Week 3 Day 4-5: 申込 (Application) Migration

## Step 1: Identify Target Columns

**Action Required**: Open Excel and check **RIGHT side** (移行システム) for columns with ○

From your image, I can see rows 2008-2009 have ○ marks. You need to:

1. Go through Excel rows for 申込 table
2. Note down all rows with ○ on RIGHT side
3. List column names and data types

**Confirmed from Excel - 申込 Table Columns**:

```markdown
## 申込 Table - Target Columns (RIGHT side ○)

| No | Column Name | Data Type | Length | Conversion | Notes |
|----|-------------|-----------|--------|------------|-------|
| 51 | 申込番号 | VARCHAR2 | 12 | Direct | PK |
| 52 | 申込日 | VARCHAR2 | 2 | Direct | Date |
| 750 | 融資申込番号 | VARCHAR2 | 12 | Direct | |
| 752 | 融資申込番号 | VARCHAR2 | 12 | Direct | Duplicate? |
| 761 | (Related to 申込審査状況) | - | - | - | See 申込審査状況 table |
| 762 | 申込日付 | VARCHAR2 | 2 | E申込目的 | 10→10, 15→10, 20→20, 30→20 |
| 763 | 関連番 | NUMBER | 3,0 | Direct | |
| 820 | 連絡コード | VARCHAR2 | 6 | E連絡コード | JR codes, conversion rules |
| 822 | 経済記 | VARCHAR2 | 20 | Direct | |
| 1131 | 申込履歴号 | VARCHAR2 | 12 | Direct | |
| 1132 | 申込日付 | VARCHAR2 | 2 | Direct | |
| 2008 | 審査履歴 | VARCHAR2 | 12 | Direct | |
| 2009 | 日付 | VARCHAR2 | 2 | E申込目的 | 10:事前審査, 15:事前簡易審査, etc. |
| 2010 | イベント | VARCHAR2 | 50 | Direct | |

**Note**: Row 2011 (イベント日時 DATE) has **-** mark, not ○, so NOT migrated to target.
```

---

## Step 2: Create Domain Classes

### ApplicationSource.java

**File**: `src\main\java\migration\domain\source\ApplicationSource.java`

```java
package migration.domain.source;

/**
 * Source: 申込 (Application) - Old System
 * DB: E00197SV0203/SZH_SMS
 * 
 * Contains ALL columns from LEFT side (新システム/Old System)
 * Based on Excel column mapping sheet
 */
public class ApplicationSource {
    
    // Primary Key (Row 51)
    private String 申込番号;           // VARCHAR2(12) PK
    
    // Basic Information (Row 52)
    private String 申込日;             // VARCHAR2(2)
    private String 申込目的;           // VARCHAR2(2) - Needs E申込目的 conversion
    
    // Loan Information (Row 750, 752)
    private String 融資申込番号;       // VARCHAR2(12)
    
    // Related Number (Row 763)
    private Integer 関連番;            // NUMBER(3,0)
    
    // Communication (Row 820, 822)
    private String 連絡コード;         // VARCHAR2(6) - Needs E連絡コード conversion
    private String 経済記;             // VARCHAR2(20)
    
    // History Information (Row 1131, 1132)
    private String 申込履歴号;         // VARCHAR2(12)
    private String 申込日付;           // VARCHAR2(2)
    
    // Review History (Row 2008, 2009, 2010)
    private String 審査履歴;           // VARCHAR2(12)
    private String 日付;               // VARCHAR2(2)
    private String イベント;           // VARCHAR2(50)
    
    // Note: イベント日時 (Row 2011, DATE) has "-" mark, NOT in target system
    
    // TODO: Add remaining columns from LEFT side as you identify them
    
    // Getters and Setters
    public String get申込番号() { return 申込番号; }
    public void set申込番号(String 申込番号) { this.申込番号 = 申込番号; }
    
    public String get申込日() { return 申込日; }
    public void set申込日(String 申込日) { this.申込日 = 申込日; }
    
    public String get申込目的() { return 申込目的; }
    public void set申込目的(String 申込目的) { this.申込目的 = 申込目的; }
    
    public String get融資申込番号() { return 融資申込番号; }
    public void set融資申込番号(String 融資申込番号) { this.融資申込番号 = 融資申込番号; }
    
    public Integer get関連番() { return 関連番; }
    public void set関連番(Integer 関連番) { this.関連番 = 関連番; }
    
    public String get連絡コード() { return 連絡コード; }
    public void set連絡コード(String 連絡コード) { this.連絡コード = 連絡コード; }
    
    public String get経済記() { return 経済記; }
    public void set経済記(String 経済記) { this.経済記 = 経済記; }
    
    public String get申込履歴号() { return 申込履歴号; }
    public void set申込履歴号(String 申込履歴号) { this.申込履歴号 = 申込履歴号; }
    
    public String get申込日付() { return 申込日付; }
    public void set申込日付(String 申込日付) { this.申込日付 = 申込日付; }
    
    public String get審査履歴() { return 審査履歴; }
    public void set審査履歴(String 審査履歴) { this.審査履歴 = 審査履歴; }
    
    public String get日付() { return 日付; }
    public void set日付(String 日付) { this.日付 = 日付; }
    
    public String getイベント() { return イベント; }
    public void setイベント(String イベント) { this.イベント = イベント; }
}
```

---

### ApplicationTarget.java

**File**: `src\main\java\migration\domain\target\ApplicationTarget.java`

**IMPORTANT**: Only include columns with ○ on RIGHT side!

```java
package migration.domain.target;

/**
 * Target: 申込 (Application) - New System
 * DB: E00736SV0001/SZH_SMS
 * 
 * Contains ONLY columns with ○ on RIGHT side (移行システム/Target System)
 * Based on confirmed Excel mapping
 */
public class ApplicationTarget {
    
    // Primary Key (Row 51) ○
    private String 申込番号;           // VARCHAR2(12) PK
    
    // Basic Information (Row 52) ○
    private String 申込日;             // VARCHAR2(2)
    
    // Loan Information (Row 750, 752) ○
    private String 融資申込番号;       // VARCHAR2(12)
    
    // Related Number (Row 763) ○
    private Integer 関連番;            // NUMBER(3,0)
    
    // Communication (Row 820, 822) ○
    private String 連絡コード;         // VARCHAR2(6) - Converted from E連絡コード
    private String 経済記;             // VARCHAR2(20)
    
    // History Information (Row 1131, 1132) ○
    private String 申込履歴号;         // VARCHAR2(12)
    private String 申込日付;           // VARCHAR2(2)
    
    // Review History (Row 2008, 2009, 2010) ○
    private String 審査履歴;           // VARCHAR2(12)
    private String 日付;               // VARCHAR2(2) - Converted from E申込目的
    private String イベント;           // VARCHAR2(50)
    
    // Note: Row 2011 イベント日時 has "-" mark, NOT included in target
    
    // Getters and Setters
    public String get申込番号() { return 申込番号; }
    public void set申込番号(String 申込番号) { this.申込番号 = 申込番号; }
    
    public String get申込日() { return 申込日; }
    public void set申込日(String 申込日) { this.申込日 = 申込日; }
    
    public String get融資申込番号() { return 融資申込番号; }
    public void set融資申込番号(String 融資申込番号) { this.融資申込番号 = 融資申込番号; }
    
    public Integer get関連番() { return 関連番; }
    public void set関連番(Integer 関連番) { this.関連番 = 関連番; }
    
    public String get連絡コード() { return 連絡コード; }
    public void set連絡コード(String 連絡コード) { this.連絡コード = 連絡コード; }
    
    public String get経済記() { return 経済記; }
    public void set経済記(String 経済記) { this.経済記 = 経済記; }
    
    public String get申込履歴号() { return 申込履歴号; }
    public void set申込履歴号(String 申込履歴号) { this.申込履歴号 = 申込履歴号; }
    
    public String get申込日付() { return 申込日付; }
    public void set申込日付(String 申込日付) { this.申込日付 = 申込日付; }
    
    public String get審査履歴() { return 審査履歴; }
    public void set審査履歴(String 審査履歴) { this.審査履歴 = 審査履歴; }
    
    public String get日付() { return 日付; }
    public void set日付(String 日付) { this.日付 = 日付; }
    
    public String getイベント() { return イベント; }
    public void setイベント(String イベント) { this.イベント = イベント; }
}
```

---

## Step 3: MyBatis Mapper

### ApplicationSourceMapper.xml

**File**: `src\main\java\migration\mapper\source\ApplicationSourceMapper.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
    "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="migration.mapper.source.ApplicationSourceMapper">

    <resultMap id="applicationSourceMap" type="migration.domain.source.ApplicationSource">
        <id property="申込番号" column="申込番号"/>
        <result property="申込日" column="申込日"/>
        <result property="申込目的" column="申込目的"/>
        <result property="融資申込番号" column="融資申込番号"/>
        <result property="関連番" column="関連番"/>
        <result property="連絡コード" column="連絡コード"/>
        <result property="経済記" column="経済記"/>
        <result property="申込履歴号" column="申込履歴号"/>
        <result property="申込日付" column="申込日付"/>
        <result property="審査履歴" column="審査履歴"/>
        <result property="日付" column="日付"/>
        <result property="イベント" column="イベント"/>
    </resultMap>

    <!-- Select by Range (F-Level Pattern) -->
    <select id="selectByRange" resultMap="applicationSourceMap">
        SELECT
            申込番号,
            申込日,
            申込目的,
            融資申込番号,
            関連番,
            連絡コード,
            経済記,
            申込履歴号,
            申込日付,
            審査履歴,
            日付,
            イベント
            /* Add more columns as needed */
        FROM 申込
        WHERE 申込番号 BETWEEN #{startId} AND #{endId}
        ORDER BY 申込番号
        FOR UPDATE SKIP LOCKED
    </select>

    <!-- Count total records -->
    <select id="count" resultType="long">
        SELECT COUNT(*) FROM 申込
    </select>

    <!-- Find min/max IDs for range processing -->
    <select id="findMinId" resultType="string">
        SELECT MIN(申込番号) FROM 申込
    </select>

    <select id="findMaxId" resultType="string">
        SELECT MAX(申込番号) FROM 申込
    </select>

</mapper>
```

---

### ApplicationTargetMapper.xml

**File**: `src\main\java\migration\mapper\target\ApplicationTargetMapper.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
    "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="migration.mapper.target.ApplicationTargetMapper">

    <!-- Insert to target (ONLY columns with ○ on RIGHT side) -->
    <insert id="insert" parameterType="migration.domain.target.ApplicationTarget">
        INSERT INTO 申込 (
            申込番号,
            申込日,
            融資申込番号,
            関連番,
            連絡コード,
            経済記,
            申込履歴号,
            申込日付,
            審査履歴,
            日付,
            イベント
        ) VALUES (
            #{申込番号},
            #{申込日},
            #{融資申込番号},
            #{関連番},
            #{連絡コード},
            #{経済記},
            #{申込履歴号},
            #{申込日付},
            #{審査履歴},
            #{日付},
            #{イベント}
        )
    </insert>

    <!-- Delete all (for testing) -->
    <delete id="deleteAll">
        DELETE FROM 申込
    </delete>

</mapper>
```

---

## Step 4: Service Layer (F-Level Pattern)

### ApplicationMigrationService.java

**File**: `src\main\java\migration\service\ApplicationMigrationService.java`

```java
package migration.service;

import migration.common.szh_sms.E申込目的;
import migration.domain.source.ApplicationSource;
import migration.domain.target.ApplicationTarget;
import migration.mapper.source.ApplicationSourceMapper;
import migration.mapper.target.ApplicationTargetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 申込 (Application) Migration Service
 * 
 * F-Level Pattern:
 * 1. processAll() - Master data caching
 * 2. claimNextRange() - FOR UPDATE SKIP LOCKED
 * 3. processOneRange() - Main transformation
 * 4. markDone/markError() - Status management
 */
@Service
public class ApplicationMigrationService {
    
    @Autowired
    private ApplicationSourceMapper sourceMapper;
    
    @Autowired
    private ApplicationTargetMapper targetMapper;
    
    /**
     * Step 1: Process All (Master Data Caching)
     */
    public void processAll() {
        long totalCount = sourceMapper.count();
        String minId = sourceMapper.findMinId();
        String maxId = sourceMapper.findMaxId();
        
        System.out.println("==========================================");
        System.out.println("申込 Migration - Process All Started");
        System.out.println("Total Records: " + totalCount);
        System.out.println("ID Range: " + minId + " ~ " + maxId);
        System.out.println("==========================================");
        
        // TODO: Load master data into HashMap cache
        // Example: Load 保証会社 master, etc.
    }
    
    /**
     * Step 3: Process One Range (Main Logic)
     * 
     * @param startId Range start ID
     * @param endId Range end ID
     * @return Number of processed records
     */
    @Transactional
    public int processOneRange(String startId, String endId) {
        System.out.println("Processing range: " + startId + " ~ " + endId);
        
        // Read from source (already locked by FOR UPDATE SKIP LOCKED)
        List<ApplicationSource> sourceList = sourceMapper.selectByRange(startId, endId);
        
        int processedCount = 0;
        int skippedCount = 0;
        
        for (ApplicationSource source : sourceList) {
            try {
                // Migration target check
                if (!isMigrationTarget(source)) {
                    System.out.println("SKIP: " + source.get申込番号() + " - Not migration target");
                    skippedCount++;
                    continue;
                }
                
                // Transform (apply E-Level conversions)
                ApplicationTarget target = transform(source);
                
                // Insert to target
                targetMapper.insert(target);
                
                processedCount++;
                
            } catch (Exception e) {
                System.err.println("ERROR processing 申込番号=" + source.get申込番号() + ": " + e.getMessage());
                throw e; // Let transaction rollback
            }
        }
        
        System.out.println("Range completed: Processed=" + processedCount + ", Skipped=" + skippedCount);
        return processedCount;
    }
    
    /**
     * Migration Target Check
     * 
     * Criteria:
     * 1. 申込目的 must be migration target (not 90:途上与信)
     * 2. Review must be completed (審査完了のみ)
     * 3. Required fields must not be NULL
     */
    private boolean isMigrationTarget(ApplicationSource source) {
        // Check 申込目的
        String 申込目的 = source.get申込目的();
        if (!E申込目的.shouldMigrate(申込目的)) {
            return false;
        }
        
        // Check required fields
        if (source.get申込番号() == null || source.get申込番号().isEmpty()) {
            return false;
        }
        
        // TODO: Add more criteria
        // - Check 審査ステータス = completed
        
        return true;
    }
    
    /**
     * Transform: Apply E-Level Conversions
     */
    private ApplicationTarget transform(ApplicationSource source) {
        ApplicationTarget target = new ApplicationTarget();
        
        // Direct copy (PK)
        target.set申込番号(source.get申込番号());
        
        // Direct copy - Basic Information
        target.set申込日(source.get申込日());
        target.set融資申込番号(source.get融資申込番号());
        target.set関連番(source.get関連番());
        target.set経済記(source.get経済記());
        target.set申込履歴号(source.get申込履歴号());
        target.set申込日付(source.get申込日付());
        target.set審査履歴(source.get審査履歴());
        target.setイベント(source.getイベント());
        
        // E-Level conversion: 連絡コード (Row 820)
        // Apply E連絡コード conversion (JR codes handling)
        String old連絡コード = source.get連絡コード();
        String new連絡コード = E連絡コード.convert(old連絡コード);
        target.set連絡コード(new連絡コード);
        
        // E-Level conversion: 日付 field (Row 2009)
        // Based on annotation: 10:事前審査, 15:事前簡易審査, etc.
        String old日付 = source.get日付();
        String new日付 = E申込目的.convert(old日付);
        target.set日付(new日付);
        
        return target;
    }
}
```

---

## Step 5: Testing

### Test with Small Data Set

```sql
-- On source DB: Create test data (if needed)
INSERT INTO 申込 (申込番号, 申込日, 申込目的) 
VALUES ('TEST00000001', '20260603', '10');

INSERT INTO 申込 (申込番号, 申込日, 申込目的) 
VALUES ('TEST00000002', '20260603', '90'); -- Should be skipped

COMMIT;
```

### Run Migration Test

**File**: `batch\test_batch01.bat`

```batch
@echo off
REM Test batch for 申込 migration

set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_281
set PROJECT_HOME=D:\SunTrust\tanaka\module\MORTGAGE_MIGRATION_BATCH

REM Setup classpath
set CLASSPATH=%PROJECT_HOME%\build\classes\java\main
set CLASSPATH=%CLASSPATH%;%PROJECT_HOME%\src\main\resources
for %%i in (%PROJECT_HOME%\libs\*.jar) do set CLASSPATH=!CLASSPATH!;%%i

echo ==========================================
echo Test: 申込 Migration
echo ==========================================

java -cp "%CLASSPATH%" ^
    -Dspring.profiles.active=test ^
    migration.MortgageMigrationApplication ^
    table=申込 ^
    startId=TEST00000001 ^
    endId=TEST00000010

pause
```

### Verify Results

```sql
-- On target DB: Check migrated data
SELECT * FROM 申込 WHERE 申込番号 LIKE 'TEST%';

-- Should show:
-- TEST00000001 (申込目的=10, migrated)
-- TEST00000002 NOT present (申込目的=90, skipped)
```

---

# Week 4: 申込審査状況 Implementation

## TODO for Week 4

1. **Day 1-2**: Implement 申込審査状況 (Review Status)
   - Check Excel Row 70 (RIGHT side ○, マッピング済)
   - Create ReviewStatusSource/Target.java
   - Create ReviewStatusMapper.xml
   - Implement ReviewStatusMigrationService.java

2. **Day 3-4**: Testing & Debugging
   - Test 申込審査状況 migration
   - Debug any issues
   - Verify E-level conversions

3. **Day 5**: Integration Testing
   - Test 申込 + 申込審査状況 together
   - Verify foreign key relationships
   - Check data consistency

4. **Day 6-7**: Code Review & Documentation
   - Review code with おいだ
   - Update documentation
   - Prepare Week 3-4 completion report
   - Prepare for Week 5-6 (Complex conversions)

---

## ✅ Week 3-4 Checklist

### Week 3 Day 1-2
- [ ] Export source table structures (expdp)
- [ ] Import to target system (impdp)
- [ ] Verify tables created and empty

### Week 3 Day 3
- [ ] Create project folders
- [ ] Copy libs from MIGRATION_BATCH_C
- [ ] Create application.yml
- [ ] Implement E申込目的.java
- [ ] Implement E連絡コード.java

### Week 3 Day 4-5
- [x] Identify 申込 RIGHT side ○ columns (DONE - 11 columns confirmed)
- [ ] Create ApplicationSource.java (11 columns)
- [ ] Create ApplicationTarget.java (11 columns with ○)
- [ ] Create ApplicationSourceMapper.xml
- [ ] Create ApplicationTargetMapper.xml
- [ ] Implement ApplicationMigrationService.java (with E連絡コード, E申込目的 conversions)
- [ ] Test with small dataset

### Week 3 Day 6-7
- [ ] Debug and fix issues
- [ ] Document findings
- [ ] Prepare for Week 4

### Week 4 Day 1-2
- [ ] Implement 申込審査状況 migration (Row 70)
- [ ] Test 申込審査状況

### Week 4 Day 3-4
- [ ] Debug and optimize 申込審査状況
- [ ] Add E-level enums for 申込審査状況

### Week 4 Day 5
- [ ] Integration testing (申込 + 申込審査状況)
- [ ] Performance verification

### Week 4 Day 6-7
- [ ] Code review with おいだ
- [ ] Documentation update
- [ ] Week 3-4 completion report

---

## 🚨 Critical Points

1. **RIGHT Side Focus**: Only implement columns with ○ on RIGHT side of Excel
2. **E-Level Conversions**: Apply code conversions from annotations
3. **Migration Criteria**: Skip records where 申込目的=90 or 審査中
4. **F-Level Pattern**: Follow MIGRATION_BATCH_C pattern exactly
5. **Testing**: Test with small dataset first before full migration

---

## 📞 Need Help?

- **Technical questions**: おいだ (Slack)
- **Excel questions**: Check RIGHT side (移行システム) column
- **Pattern reference**: MIGRATION_BATCH_C on SVN

---

**Next Step**: Execute Week 3 Day 1-2 tasks (Table structure export/import)

**After Week 3-4 completion**: Move to Week 5-6 (Complex transformations)
