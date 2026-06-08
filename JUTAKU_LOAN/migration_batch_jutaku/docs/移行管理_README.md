# 移行管理テーブル Integration

## Overview

Migration architecture using 移行管理 table for parallel processing with FOR UPDATE SKIP LOCKED.

## Components

### Domain & Mapper
- `移行管理.java` - Domain model (申込番号, ステータス, etc.)
- `移行管理Mapper.java/xml` - Database operations with FOR UPDATE SKIP LOCKED

### Service & Batch
- `移行管理Service.java` - Claim/mark operations
- `移行管理Tasklet.java` - New batch tasklet

### Database
- `移行管理_TABLE_DDL.sql` - Table definition and monitoring queries

### Testing
- `移行管理LockTest.java` - Parallel execution test utility

## Architecture

### Processing Flow

```
Phase 1: Claim Records (Fast - Single Transaction)
  1. SELECT ... FOR UPDATE SKIP LOCKED
  2. Fetch N records (lock acquired)
  3. UPDATE status TODO -> RUNNING
  4. COMMIT (locks released)

Phase 2: Process Records (Slow - No Locks)
  For each claimed record:
    - Read source data by 申込番号
    - Transform and migrate
    - Individual transaction per record

Phase 3: Mark Complete
  Success: UPDATE status -> DONE
  Failure: UPDATE status -> ERROR (rollback migration)
```

### Parallel Processing

Multiple processes run simultaneously:
- Process 1 locks records 1-100, updates to RUNNING, commits
- Process 2 skips locked rows, takes 101-200
- Process 3 skips locked rows, takes 201-300
- Each processes independently without conflicts

## Testing

### 1. Create Table and Test Data

```sql
-- Run DDL
@移行管理_TABLE_DDL.sql

-- Insert 1000 test records (see SQL file for script)
```

### 2. Test Locking Mechanism

Run in 3 terminals simultaneously:

```bash
# Terminal 1
./gradlew bootRun --args='--spring.profiles.active=test --test.process.id=1'

# Terminal 2
./gradlew bootRun --args='--spring.profiles.active=test --test.process.id=2'

# Terminal 3
./gradlew bootRun --args='--spring.profiles.active=test --test.process.id=3'
```

Expected: Each process claims different record ranges, no duplicates

### 3. Verify Results

```sql
-- Check status distribution
SELECT ステータス, COUNT(*) FROM 移行管理_JUTAKU GROUP BY ステータス;

-- Should show: DONE = 1000, no RUNNING or ERROR
```

## Configuration

In `BatchConfig.java`:

```java
@Autowired
private 移行管理Tasklet managementBasedTasklet;

@Bean
public Step migrationStep() {
    return stepBuilderFactory.get("migrationStep")
        .tasklet(managementBasedTasklet)
        .build();
}
```

Adjust batch size in `移行管理Tasklet.java`:

```java
private static final int BATCH_SIZE = 100;  // Records per claim iteration
```

## Integration with Existing Migration

Add method to `申込MigrationService.java`:

```java
@Transactional
public void migrateOneApplication(String 申込番号) {
    申込Source source = sourceMapper.selectBy申込番号(申込番号);
    申込Target target = transform(source);
    targetMapper.insert(target);
    
    // Migrate related tables
    申込審査状況MigrationService.migrateBy申込番号(申込番号);
}
```

Update `移行管理Tasklet.processOneRecord()`:

```java
// Replace:
Thread.sleep(10);

// With:
migrationService.migrateOneApplication(申込番号);
```

## Monitoring

### Status Summary

```sql
SELECT ステータス, COUNT(*) as 件数
FROM 移行管理_JUTAKU
GROUP BY ステータス;
```

### Processing Rate

```sql
SELECT 
    COUNT(*) as 完了件数,
    ROUND(COUNT(*) / ((SYSDATE - MIN(処理開始日時)) * 24 * 60), 2) as 件_分
FROM 移行管理_JUTAKU
WHERE ステータス = 'DONE';
```

### Stuck Records

```sql
SELECT 申込番号, 処理開始日時,
    ROUND((SYSDATE - 処理開始日時) * 24 * 60, 1) as 経過分
FROM 移行管理_JUTAKU
WHERE ステータス = 'RUNNING'
  AND 処理開始日時 < SYSDATE - INTERVAL '30' MINUTE;
```

### Error Records

```sql
SELECT 申込番号, 処理開始日時, 処理終了日時
FROM 移行管理_JUTAKU
WHERE ステータス = 'ERROR'
ORDER BY 処理開始日時 DESC;
```

## Troubleshooting

### Problem: Stuck RUNNING Records

Reset stuck records back to TODO:

```sql
UPDATE 移行管理_JUTAKU 
SET ステータス = 'TODO', 処理開始日時 = NULL
WHERE ステータス = 'RUNNING'
  AND 処理開始日時 < SYSDATE - INTERVAL '30' MINUTE;
COMMIT;
```

### Problem: Duplicate Processing

Check for duplicate records in target:

```sql
SELECT 申込番号, COUNT(*) 
FROM SZH_SMS.申込
GROUP BY 申込番号 
HAVING COUNT(*) > 1;
```

Verify FOR UPDATE SKIP LOCKED syntax and transaction boundaries.

### Problem: All Processes Pick Same Records

Verify @Transactional annotation on claimRecords() method.
Check that commit happens after status update.

## Transition Strategy

1. Keep both old and new tasklets during testing
2. Use configuration to switch between implementations
3. Run parallel tests to verify no conflicts
4. Compare results with old approach
5. Delete old tasklet only after successful production runs

## Status Constants

```java
移行管理.STATUS_TODO      = "TODO"
移行管理.STATUS_RUNNING   = "RUNNING"
移行管理.STATUS_DONE      = "DONE"
移行管理.STATUS_ERROR     = "ERROR"
```

## Key Design Points

- Individual record tracking (not range-based)
- FOR UPDATE SKIP LOCKED prevents lock contention
- Short transactions for claim, long operations without locks
- Separate transactions for status updates
- Error handling with rollback of migration but status preserved
