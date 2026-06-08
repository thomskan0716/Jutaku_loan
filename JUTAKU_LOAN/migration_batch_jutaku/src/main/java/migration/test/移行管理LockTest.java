package migration.test;

import migration.domain.移行管理.移行管理;
import migration.mapper.移行管理.移行管理Mapper;
import migration.service.移行管理Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 移行管理 Lock Test
 *
 * Run in 3 terminals simultaneously:
 *   ./gradlew bootRun --args='--spring.profiles.active=test --test.process.id=1'
 *   ./gradlew bootRun --args='--spring.profiles.active=test --test.process.id=2'
 *   ./gradlew bootRun --args='--spring.profiles.active=test --test.process.id=3'
 *
 * Verify each process claims different records (no overlap in logs)
 */
@Component
@Profile("test")
public class 移行管理LockTest implements CommandLineRunner {

    @Autowired
    private 移行管理Service managementService;

    @Autowired
    private 移行管理Mapper managementMapper;

    private static final int BATCH_SIZE = 10;
    private static final int MAX_ITERATIONS = 5;

    @Override
    public void run(String... args) throws Exception {
        String processId = getProcessId(args);

        System.out.println("\n=== Locking Mechanism Test - Process " + processId + " ===\n");

        System.out.println("--- Initial Status ---");
        managementService.printStatusSummary();

        int iteration = 0;
        int totalClaimed = 0;

        while (iteration < MAX_ITERATIONS) {
            iteration++;
            System.out.println("\n[Process " + processId + "] Iteration " + iteration + "/" + MAX_ITERATIONS);

            List<移行管理> claimed = managementService.claimRecords(BATCH_SIZE, "PROC-" + processId);

            if (claimed.isEmpty()) {
                System.out.println("[Process " + processId + "] No more records to claim");
                break;
            }

            totalClaimed += claimed.size();
            System.out.println("[Process " + processId + "] Claimed " + claimed.size() + " records:");
            for (移行管理 record : claimed) {
                System.out.println("  - " + record.get申込番号());
            }

            System.out.println("[Process " + processId + "] Simulating processing (2 seconds)...");
            Thread.sleep(2000);

            for (移行管理 record : claimed) {
                managementService.markDone(record.get申込番号());
            }

            System.out.println("[Process " + processId + "] Marked " + claimed.size() + " records as DONE");

            Thread.sleep(500);
        }

        System.out.println("\n=== Process " + processId + " Test Complete ===");
        System.out.println("Total Claimed: " + totalClaimed + " records");

        System.out.println("\n--- Final Status (Process " + processId + " view) ---");
        managementService.printStatusSummary();

        verifyNoDuplicates(processId);

        System.out.println("\nProcess " + processId + " exiting...\n");
    }

    private String getProcessId(String[] args) {
        for (String arg : args) {
            if (arg.startsWith("--test.process.id=")) {
                return arg.substring("--test.process.id=".length());
            }
        }
        return "UNKNOWN";
    }

    private void verifyNoDuplicates(String processId) {
        System.out.println("\n--- Verification Queries ---");

        long todoCount = managementMapper.countByStatus("TODO");
        long runningCount = managementMapper.countByStatus("RUNNING");
        long doneCount = managementMapper.countByStatus("DONE");
        long errorCount = managementMapper.countByStatus("ERROR");
        long total = todoCount + runningCount + doneCount + errorCount;

        System.out.println("Total records: " + total);
        System.out.println("TODO:    " + todoCount);
        System.out.println("RUNNING: " + runningCount);
        System.out.println("DONE:    " + doneCount);
        System.out.println("ERROR:   " + errorCount);

        if (runningCount > 0) {
            System.out.println("\nWARNING: " + runningCount + " records still in RUNNING state");
            System.out.println("   This could indicate:");
            System.out.println("   1. Another process is still running");
            System.out.println("   2. Process crashed during processing");
        }

        System.out.println("\n--- Verification Complete ---");
    }
}
