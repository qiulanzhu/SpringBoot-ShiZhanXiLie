package com.qiuyi.springdemo.dataSourceConfig;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;

@Slf4j
@Configuration
@MapperScan(basePackages = "com.qiuyi.springdemo.dao.db1", sqlSessionTemplateRef = "db1SqlSessionTemplate")
public class Db1Config {
    @Value("${spring.datasource.db1.jdbc-url}")
    private String db1JdbcUrl;

    /**
     * 是application.properties中的spring.datasource.db1配置生效
     * @return
     */
    @Bean(name = "db1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    @Primary
    public DataSource db1DataSource() {
        log.info(db1JdbcUrl);
        return DataSourceBuilder.create().build();
    }

    /**
     * 事务管理器，在实例化时注入主库db1
     * @param dataSource
     * @return
     */
    @Bean(name = "db1TransactionManager")
    @Primary
    public DataSourceTransactionManager db1TransactionManager(@Qualifier("db1DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 将配置信息注入到SqlSessionFactoryBean中
     * @param dataSource    数据库连接信息
     * @return
     * @throws Exception
     */
    @Bean(name = "db1SqlSessionFactory")
    @Primary
    public SqlSessionFactory db1SqlSessionFactory(@Qualifier("db1DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/db1/*.xml"));
        Objects.requireNonNull(bean.getObject()).getConfiguration().setMapUnderscoreToCamelCase(true);
        return bean.getObject();
    }

    /**
     * SqlSessionTemplate具有线程安全性
     * @param sqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean(name = "db1SqlSessionTemplate")
    @Primary
    public SqlSessionTemplate db1SqlSessionTemplate(@Qualifier("db1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
