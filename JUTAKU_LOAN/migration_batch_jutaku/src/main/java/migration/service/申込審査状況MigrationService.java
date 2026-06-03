package migration.service;

import migration.common.szh_sms.E申込目的;
import migration.domain.source.申込審査状況Source;
import migration.domain.target.申込審査状況Target;
import migration.mapper.source.申込審査状況SourceMapper;
import migration.mapper.target.申込審査状況TargetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class 申込審査状況MigrationService {

    @Autowired
    private 申込審査状況SourceMapper sourceMapper;

    @Autowired
    private 申込審査状況TargetMapper targetMapper;

    /**
     * Migrate all 申込審査状況 records for a given 申込番号.
     * Called once per 申込 record inside processOneRange().
     *
     * @param 申込番号 the application number whose review statuses to migrate
     * @return number of records inserted
     */
    public int migrateByApplicationId(String 申込番号) {
        List<申込審査状況Source> sourceList = sourceMapper.selectByApplicationId(申込番号);

        int insertedCount = 0;
        for (申込審査状況Source source : sourceList) {
            if (!isMigrationTarget(source)) {
                System.out.println("SKIP 申込審査状況: " + 申込番号
                        + " 申込目的=" + source.get申込目的());
                continue;
            }

            申込審査状況Target target = transform(source);
            targetMapper.insert(target);
            insertedCount++;
        }

        return insertedCount;
    }

    
    private boolean isMigrationTarget(申込審査状況Source source) {
        return E申込目的.shouldMigrate(source.get申込目的());
    }

    
    private 申込審査状況Target transform(申込審査状況Source source) {
        申込審査状況Target target = new 申込審査状況Target();

        target.set申込番号(source.get申込番号());

        target.set申込目的(E申込目的.convert(source.get申込目的()));

        target.set回数(source.get回数());

        return target;
    }

    
    public void deleteAll() {
        targetMapper.deleteAll();
    }
}
