# Migration Batch Configuration Guide

## Overview
All batch sizes and iteration limits are now fully configurable via `application.yml`.

---

## Configuration Properties

### 移行管理 Parallel Processing (Current Approach)

#### `migration.management.batch-size`
**Default:** `100`  
**Description:** Number of records to claim per iteration.  
**Recommended values:**
- Small batches (10-50): Better for testing, easier to monitor
- Medium batches (100-500): Balanced performance
- Large batches (500-1000): Maximum throughput, but longer transactions

#### `migration.management.max-iterations`
**Default:** `-1` (unlimited)  
**Description:** Maximum number of claim iterations before stopping.  
**Values:**
- `-1`: Process all TODO records until none remain (recommended for production)
- `1-N`: Stop after N iterations (useful for testing)

### Range-Based Processing (Old Approach, Not Used)

#### `migration.range.batch-size`
**Default:** `100`  
**Description:** Batch size for old range-based processing.

#### `migration.range.test-limit`
**Default:** `1000`  
**Description:** Test limit for old range-based processing.

---

## What About `fetchSize` in the XML Files?

**`fetchSize="100"` in 移行管理Mapper.xml is intentionally hard-coded.**

This is a **JDBC fetch buffer size** — it controls how many rows Oracle buffers in memory when streaming cursor results. It's a low-level JDBC tuning parameter and **is NOT the same as `batch-size`**:

- `fetchSize=100`: Oracle sends 100 rows per network round trip (JDBC tuning)
- `batch-size=100`: Application claims 100 records to process (business logic)

**Leave `fetchSize` at 100** — it's a reasonable default for cursor streaming.

---

## Configuration Properties

### `migration.management.batch-size`
**Default:** `100`  
**Description:** Number of records to claim per iteration.  
**Recommended values:**
- Small batches (10-50): Better for testing, easier to monitor
- Medium batches (100-500): Balanced performance
- Large batches (500-1000): Maximum throughput, but longer transactions

### `migration.management.max-iterations`
**Default:** `-1` (unlimited)  
**Description:** Maximum number of claim iterations before stopping.  
**Values:**
- `-1`: Process all TODO records until none remain (recommended for production)
- `1-N`: Stop after N iterations (useful for testing)

---

## Configuration Examples

### Example 1: Production (Process All Records)
```yaml
migration:
  management:
    batch-size: 100
    max-iterations: -1
```

### Example 2: Testing (Limited Processing)
```yaml
migration:
  management:
    batch-size: 10
    max-iterations: 5
```
→ Each process will claim 10 records × 5 iterations = **50 records max**

### Example 3: High Throughput
```yaml
migration:
  management:
    batch-size: 500
    max-iterations: -1
```

### Example 4: Quick Smoke Test
```yaml
migration:
  management:
    batch-size: 5
    max-iterations: 1
```
→ Each process claims only **5 records once**

---

## Usage

### 1. Via `application.yml`
Edit `src/main/resources/application.yml`:
```yaml
migration:
  management:
    batch-size: 200
    max-iterations: -1
```

### 2. Via Command Line (Override)
```bash
java -cp "build\classes;libs\*;lib\*" migration.MigrationBatchApplication \
  --spring.profiles.active=test \
  --test.process.id=1 \
  --migration.management.batch-size=50 \
  --migration.management.max-iterations=10
```

### 3. Via Test Profile
Create `application-test.yml` or use the test profile section in `application.yml`:
```yaml
---
spring:
  config:
    activate:
      on-profile: test

migration:
  management:
    batch-size: 10
    max-iterations: 5
```

---

## How Many Records Will Be Migrated?

### Formula
```
Total per process = batch-size × max-iterations
```

### Examples
| batch-size | max-iterations | Records per Process | 3 Processes Total |
|------------|----------------|---------------------|-------------------|
| 10         | 5              | 50                  | 150               |
| 100        | -1 (unlimited) | All TODO records    | All TODO records  |
| 100        | 10             | 1,000               | 3,000             |
| 500        | -1 (unlimited) | All TODO records    | All TODO records  |
| 50         | 20             | 1,000               | 3,000             |

**Note:** With `max-iterations=-1`, all processes will continue until **all TODO records** in the `移行管理` table are processed (DONE or ERROR).

---

## Monitoring

The logs will show the configuration at startup:
```
=== Locking Mechanism Test - Process 1 ===
Configuration: batch-size=100, max-iterations=-1
```

---

## Recommendations

### Testing
- `batch-size: 10-50`
- `max-iterations: 5-10`
- Total: 50-500 records to verify locking mechanism

### Production
- `batch-size: 100-500`
- `max-iterations: -1`
- Total: Process all TODO records until complete

### Reprocessing Errors
```sql
-- Reset ERROR records back to TODO
UPDATE 移行管理 SET ステータス = 'TODO' WHERE ステータス = 'ERROR';
COMMIT;
```

Then rerun with production config to process only the failed records.
