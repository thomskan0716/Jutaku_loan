package migration.batch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import migration.mybatis.domain.sms.Vイメージ;
import migration.service.MutanpoService;

/**
 */
@Component
@StepScope
@Slf4j
public class MigrationTasklet implements Tasklet {

    @Autowired
    private MutanpoService mutanpoService;

    //  @Value("${batch.migration}")
    //  public boolean batchmigration;
    //
    //  @Value("${batch.delete}")
    //  public boolean batchDelete;
    @Value("${batch.togo}")
    public boolean batchTogo;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {

        log.info("migrationTasklet run start");
        try {
            mutanpoService.processAll();
            while (true) {
                try {
                    Vイメージ range = mutanpoService.claimNextRange(); // 1000m
                    if (range == null) {
                        break;
                    }

                    try {
                        mutanpoService.processOneRange(range); // 1000W=1g UONV
                        mutanpoService.markDone(range);
                    } catch (Exception e) {
                        log.error("s from={} to={}", range.get採番from(), range.get採番to(), e);
                        mutanpoService.markError(range);
                    }
                } catch(Exception e) {
                    log.info("No処理対象レコードが存在しない場合");
                }
            }

        } catch (Exception ex) {
            log.error("ex:", ex);
        }

        log.info("migrationTasklet run end");
        return RepeatStatus.FINISHED;
    }
}
