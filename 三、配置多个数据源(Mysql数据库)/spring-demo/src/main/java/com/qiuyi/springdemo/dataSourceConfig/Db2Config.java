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
@MapperScan(basePackages = "com.qiuyi.springdemo.dao.db2", sqlSessionTemplateRef = "db2SqlSessionTemplate")
public class Db2Config {
    @Value("${spring.datasource.db2.jdbc-url}")
    private String db2JdbcUrl;

    /**
     * 是application.properties中的spring.datasource.db2配置生效
     * @return
     */
    @Bean(name = "db2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    @Primary
    public DataSource db2DataSource() {
        log.info(db2JdbcUrl);
        return DataSourceBuilder.create().build();
    }

    /**
     * 事务管理器，在实例化时注入主库db2
     * @param dataSource
     * @return
     */
    @Bean(name = "db2TransactionManager")
    @Primary
    public DataSourceTransactionManager db2TransactionManager(@Qualifier("db2DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 将配置信息注入到SqlSessionFactoryBean中
     * @param dataSource    数据库连接信息
     * @return
     * @throws Exception
     */
    @Bean(name = "db2SqlSessionFactory")
    @Primary
    public SqlSessionFactory db2SqlSessionFactory(@Qualifier("db2DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/db2/*.xml"));

        // 数据库字段映射到类字段，下划线风格转驼峰风格
        Objects.requireNonNull(bean.getObject()).getConfiguration().setMapUnderscoreToCamelCase(true);
        return bean.getObject();
    }

    /**
     * SqlSessionTemplate具有线程安全性
     * @param sqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean(name = "db2SqlSessionTemplate")
    @Primary
    public SqlSessionTemplate db2SqlSessionTemplate(@Qualifier("db2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
