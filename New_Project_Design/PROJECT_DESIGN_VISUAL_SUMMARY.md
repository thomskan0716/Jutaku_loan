# MORTGAGE_MIGRATION_BATCH
## Housing Loan Review System → SCOPE Migration Project

---

## PROJECT OVERVIEW

MIGRATION FLOW:

Housing Loan System (Old System) → MORTGAGE_MIGRATION_BATCH (16 Week Project) → SCOPE System (Target System)

- 267 source tables
- 137 tables migrated
- 5,705 columns
- 12 image types
- Oracle DB (source)
- F+E Classification
- 552 target tables
- October 2026 Service-In

---

## KEY STATISTICS

| Category | Details |
|----------|---------|
| Data Scope | 267 source tables → 137 target tables (51%) |
| Column Mapping | 5,705 columns to map |
| Images | 12 types (TIFF/PDF/GIF → JPEG conversion) |
| Complexity | 60% Simple / 40% Complex transformations |
| Criteria | Preliminary OR Formal review completed only |
| Timeline | 16 weeks (May 21 - Sep 10, 2026) |
| Service-In | October 2026 |
| Parallel | 15 batch instances |
| Current Status | Week 2 - Setup Phase (IN PROGRESS) |

---

## 16-WEEK TIMELINE

CURRENT STATUS: Week 2 (May 21, 2026) - Setup Phase IN PROGRESS

Week 1-2: Setup & Code Review (CURRENT - IN PROGRESS)
  - Environment setup
  - Study MIGRATION_BATCH_C (F classification)
  - Review specifications

Week 3-4: Foundation Implementation
  - Core tables (Application, Review Status, Collateral)
  - Basic E classification enums
  - Range processing setup

Week 5-6: Complex Transformations
  - Vertical ↔ Horizontal pivots
  - N:1 aggregations
  - Advanced E classifications

Week 7-8: Related Tables
  - Borrower information
  - Credit information
  - Review history

Week 9: Image Migration
  - Format conversion (TIFF/PDF/GIF → JPEG)
  - File path updates
  - Performance optimization

Week 10-11: Testing
  - Unit tests (convXXX methods)
  - Integration tests (full flow)
  - Parallel execution testing

Week 12: Full Migration Test
  - Complete data migration
  - Performance validation
  - Error rate verification

Week 13-14: UAT (User Acceptance Testing)
  - Business validation
  - Data quality checks
  - User training

Week 15-16: Cutover Preparation
  - Production readiness review
  - Rollback plan validation
  - Final deployment preparation

BUFFER: 3 weeks (Sep 10 - Oct 1)
  - Contingency for unexpected issues

Oct 2026: SERVICE-IN (Production Go-Live)

---

## F & E CLASSIFICATION LEVELS

### F Classification (F分類レベル) - Processing Procedures
Status: 100% REUSABLE from MIGRATION_BATCH_C

F Classification = HOW to Process (Trajectory & Procedure Order)

1. processAll()
   Load master data → HashMap cache

2. claimNextRange()
   FOR UPDATE SKIP LOCKED → Parallel execution

3. processOneRange()
   Transform data → Apply E classification

4. markDone() / markError()
   Update status → Continue/Retry

Key Patterns:
  - Range-based processing
  - HashMap caching (prevent N+1 queries)
  - Parallel execution control (15 batches)
  - Transaction boundaries
  - Error handling & rollback

### E Classification (E分類レベル) - Code Mapping
Status: 65-70% Complete (add incrementally)

E Classification = WHAT Codes to Convert (Code Mapping)

Example: E申込目的 (Application Purpose)

Old System → New System
10 (New Construction) → 10 (New Loan)
15 (Existing Home) → 10 (New Loan) ← Merge
20 (Refinance) → 20 (Refinance)
30 (Refinance w/Fees) → 20 (Refinance) ← Merge

50+ Enum Categories:
  - Application Purpose, Review Type, Review Status
  - Collateral Type, Building Structure, Housing Type
  - Repayment Method, Interest Rate Type, Loan Purpose
  - Employment Type, Income Category, Approval Category
  - ... and 40+ more

---

## ARCHITECTURE OVERVIEW

SOURCE SYSTEM (Housing Loan Review System):
  - Application Information
  - Preliminary Review Information
  - Formal Review Information
  - Collateral Information
  - Borrower Information
  - Review Results
  - Image Management (TIFF/PDF/GIF)
  - ... 260 more tables

↓

MORTGAGE_MIGRATION_BATCH (Based on MIGRATION_BATCH_C Pattern):
  HousingLoanService.java (F Classification)
    - processAll() ← Master data caching
    - claimNextRange() ← FOR UPDATE SKIP LOCKED
    - processOneRange() ← Main transformation logic
    - convApplication() ← E classification applied
    - convCollateral() ← Complex transformation
    - convIncomeInfo() ← Vertical ↔ Horizontal pivot
    - migrateImages() ← Format conversion
  
  ImageConversionService.java
    - TIFF/PDF/GIF → JPEG conversion
  
  50+ E Classification Enums
    - Code mapping (old system → new system)
  
  Migration Control Table
    - Status: TODO → RUNNING → DONE/ERROR
  
  Parallel Execution: 15 Batch Instances

↓

TARGET SYSTEM (SCOPE/SMS System):
  - Application
  - Application Progress
  - Review Status
  - Collateral Info
  - Borrower
  - Review Results
  - Image Information (JPEG)
  - ... 545 more tables
  
  Ready for October 2026 Service-In

---

## TRANSFORMATION PATTERNS

| Pattern | Complexity | Example |
|---------|-----------|---------|
| 1. Direct Mapping | Low | Application# → Application# (1:1 copy) |
| 2. Code Conversion | Low | E申込目的.fromCode() → new code |
| 3. N:1 Aggregation | Medium | Multiple Collateral → Single Collateral Info |
| 4. Vertical → Horizontal | High | Income rows → Annual Income columns |
| 5. Horizontal → Vertical | High | Repayment Plan columns → Schedule rows |
| 6. PK Transformation | Medium | Add "HL" prefix + padding |

---

## SUCCESS CRITERIA

| Metric | Target | Status |
|--------|--------|--------|
| Project Status | Week 2 (May 18 - 22, 2026) | IN PROGRESS |
| Data Quality | >99% accuracy | Design Phase |
| Performance | 10,000 apps in <4 hours | Design Phase |
| Error Rate | <1% failed records | Design Phase |
| Image Conversion | <2 sec/file | Design Phase |
| Parallel Execution | 15 batches, no deadlocks | Design Phase |
| Timeline | Complete by Sep 10, 2026 | On Track (Week 2/16) |

---

## MIGRATION CRITERIA

### IN SCOPE (Migrate These)
- Preliminary review completed
- Formal review completed
- Both approved and rejected/withdrawn cases

### OUT OF SCOPE (Do NOT Migrate)
- Reviews in progress
- Unlinked images
- Customer re-applies after cutover

---

## CRITICAL SUCCESS FACTORS

1. START IMMEDIATELY (Week 1: May 21-22, 2026)
   - Connect to VM & SVN
   - Study MIGRATION_BATCH_C code
   - Review specifications

2. F Classification First
   - Copy architecture patterns (100% reusable)
   - Don't wait for complete E classification specs

3. Incremental E Classification
   - Add code mappings as specs are finalized (65-70% → 100%)
   - Use placeholder enums with TODO comments

4. Test Early & Often
   - Test with real data from Week 4
   - Parallel execution testing crucial

5. Minimal Buffer
   - Only 3 weeks buffer before Oct 2026 service-in
   - Stay on schedule - no major delays allowed!

---

## CONTACTS & RESOURCES

| Resource | Contact | Purpose |
|----------|---------|---------|
| Technical Questions | Oida-san via Slack | Specifications, E classification |
| Reference Code | MIGRATION_BATCH_C on SVN | F classification patterns |
| Specifications | Housing Loan Migration Design.xlsx | Table mappings, code conversions |
| ER Diagram | Housing Loan System ER.a5er | Source system structure |
| Environment | VMware Horizon + Dual Auth | Development access |

---

## THIS WEEK'S ACTIONS (May 18-22, 2026)
WEEK 2 - CURRENT ACTIVITIES (IN PROGRESS)

- [ ] Present this design in today's meeting ← TODAY'S MEETING
- [ ] Get approval to proceed
- [ ] Connect to VM via VMware Horizon
- [ ] SVN checkout of MIGRATION_BATCH_C
- [ ] Read MutanpoService.java (F classification reference)
- [ ] Review Design Spec Excel (E classification specs)
- [ ] Ask questions via Slack (contact: Oida-san)

---

## PROJECT STATUS SUMMARY

IN PROGRESS

Current Week: Week 2 of 16 (12% complete)
Current Phase: Setup & Code Review
Status: ON TRACK
Next Milestone: Week 3 - Start Implementation
Timeline: May 21 → Oct 2026 Service-In

Timeline Health: ON SCHEDULE
  - 14 weeks remaining for implementation & testing
  - 3 weeks buffer before production
  - No delays - on target for October 2026!

---

PROJECT STATUS: Week 2/16 - Design & Planning Phase
Ready to start MORTGAGE_MIGRATION_BATCH!
Target: October 2026 Service-In
Let's make it happen!

---

Document Version: 1.0
Last Updated: May 21, 2026
Project: MORTGAGE_MIGRATION_BATCH
