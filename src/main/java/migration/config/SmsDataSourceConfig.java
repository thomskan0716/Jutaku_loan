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
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@MapperScan(basePackages = "migration.mybatis.mapper.sms", sqlSessionFactoryRef = "smsSqlSessionFactory")
public class SmsDataSourceConfig {

    @Bean(name = "smsDataSourceProperties")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.sms")
    public DataSourceProperties smsDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "smsDataSource")
    @Primary
    public DataSource smsDataSource(@Qualifier("smsDataSourceProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "smsTxManager")
    @Primary
    public PlatformTransactionManager smsTxManager(DataSource smsDataSource) {
        return new DataSourceTransactionManager(smsDataSource);
    }

    @Bean(name = "smsSqlSessionFactory")
    @Primary
    public SqlSessionFactory smsSqlSessionFactory(@Qualifier("smsDataSource") DataSource smsDataSource)
            throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(smsDataSource);
        // TODO: MyBatis Mapper XML パスの設定
        return factoryBean.getObject();
    }

    @Bean(name = "smsSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate smsSqlSessionTemplate(
            @Qualifier("smsSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
