package org.example.springbootmultidatasource.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "org.example.springbootmultidatasource.mapper.dev",sqlSessionFactoryRef = "devSqlSessionFactory")
public class DevDataSourceConfig {

    @Bean(name = "devDataSource")
    @ConfigurationProperties("spring.datasource.dev")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "devSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("devDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
//        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResource("classpath:mapper/dev/*.xml"));
        return sessionFactoryBean.getObject();

    }
}
