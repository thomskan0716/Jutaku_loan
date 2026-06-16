package migration.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Source Database Configuration (Old System)
 * E00197SV0203/SZH_SMS
 */
@Configuration
@MapperScan(basePackages = "migration.mapper.source", sqlSessionFactoryRef = "sourceSqlSessionFactory")
public class SourceDataSourceConfig {

    @Bean(name = "sourceDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.source")
    public DataSourceProperties sourceDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "sourceDataSource")
    public DataSource sourceDataSource(@Qualifier("sourceDataSourceProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "sourceTxManager")
    public PlatformTransactionManager sourceTxManager(@Qualifier("sourceDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sourceSqlSessionFactory")
    public SqlSessionFactory sourceSqlSessionFactory(@Qualifier("sourceDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(
            new PathMatchingResourcePatternResolver().getResources("classpath:migration/mybatis/mapper/source/*.xml")
        );
        return factoryBean.getObject();
    }

    @Bean(name = "sourceSqlSessionTemplate")
    public SqlSessionTemplate sourceSqlSessionTemplate(
            @Qualifier("sourceSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
