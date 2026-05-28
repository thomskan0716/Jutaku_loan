package migration.config;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class MyBatchConfigurer extends DefaultBatchConfigurer {

    /**
     * @param targetDataSource Target database datasource
     */
    public MyBatchConfigurer(@Qualifier("targetDataSource") DataSource targetDataSource) {
        super(targetDataSource);
    }
}
