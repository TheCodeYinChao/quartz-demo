package cn.quartz.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by Admin on 2018/6/16.
 */
@Configuration
@MapperScan(basePackages = "cn.quartz.dao", sqlSessionTemplateRef  = "sqlSessionTemplate")
public class DataSourceConfig {

        @Bean(name = "dataSource")
        @ConfigurationProperties(prefix = "datasource.default")
        public DataSource dataSource() {
            return DataSourceBuilder.create().build();
        }

        @Bean(name = "sqlSessionFactory")
        public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            bean.setDataSource(dataSource);
            bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybaties/quartz/*.xml"));
            bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybaties/mybaties-config.xml"));
            return bean.getObject();
        }

        @Bean(name = "transactionManager")
        public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }

        @Bean(name = "sqlSessionTemplate")
        public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
            return new SqlSessionTemplate(sqlSessionFactory);
        }
}
