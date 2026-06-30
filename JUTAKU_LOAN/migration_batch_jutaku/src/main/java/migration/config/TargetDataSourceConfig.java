package migration.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Target Database Configuration (New System) E00736SV0001/ITF_GMS
 */
@Configuration
@MapperScan(basePackages = {"migration.mapper.target"}, sqlSessionFactoryRef = "targetSqlSessionFactory")
public class TargetDataSourceConfig {

    @Bean(name = "targetDataSourceProperties")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.target")
    public DataSourceProperties targetDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "targetDataSource")
    @Primary
    public DataSource targetDataSource(@Qualifier("targetDataSourceProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "targetTxManager")
    @Primary
    public PlatformTransactionManager targetTxManager(@Qualifier("targetDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "targetSqlSessionFactory")
    @Primary
    public SqlSessionFactory targetSqlSessionFactory(@Qualifier("targetDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] allXmls = resolver.getResources("classpath*:migration/mybatis/mapper/target/*.xml");
        factoryBean.setMapperLocations(allXmls);
        org.apache.ibatis.session.Configuration mybatisConfig = new org.apache.ibatis.session.Configuration();
        mybatisConfig.setJdbcTypeForNull(JdbcType.NULL);
        // Auto-fit every INSERT value to the real target column (truncate strings / clamp numbers).
        mybatisConfig.addInterceptor(new ColumnFitInterceptor(dataSource));
        factoryBean.setConfiguration(mybatisConfig);
        return factoryBean.getObject();
    }

    @Bean(name = "targetSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate targetSqlSessionTemplate(
            @Qualifier("targetSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
