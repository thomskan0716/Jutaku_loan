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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@MapperScan(basePackages = "migration.mapper.amigoc", sqlSessionFactoryRef = "amigoCsqlSessionFactory")
public class AmigoCDataSourceConfig {

    @Bean(name = "amigoCDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.amigoc")
    public DataSourceProperties amigoCDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "amigoCDataSource")
    public DataSource amigoCDataSource(@Qualifier("amigoCDataSourceProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "amigoCManager")
    public PlatformTransactionManager smsTxManager(DataSource smsDataSource) {
        return new DataSourceTransactionManager(smsDataSource);
    }

    @Bean(name = "amigoCsqlSessionFactory")
    public SqlSessionFactory amigoCsqlSessionFactory(@Qualifier("amigoCDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        return factoryBean.getObject();
    }

    @Bean(name = "amigoCsqlSessionTemplate")
    public SqlSessionTemplate amigoCsqlSessionTemplate(
            @Qualifier("amigoCsqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
