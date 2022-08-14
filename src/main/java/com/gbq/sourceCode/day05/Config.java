package com.gbq.sourceCode.day05;

import com.alibaba.druid.pool.DruidDataSource;

import com.gbq.sourceCode.day05.component.Bean2;
import com.gbq.sourceCode.day05.component.Bean3;
import com.gbq.sourceCode.day05.mapper.Mapper1;
import com.gbq.sourceCode.day05.mapper.Mapper2;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


import javax.sql.DataSource;

/**
 * @Author CandyWall
 * @Date 2022/3/29--12:27
 * @Description
 */
@Configuration
@ComponentScan("com.gbq.sourceCode.day05.component")
public class Config {
    @Bean
    public Bean3 bean3() {
        return new Bean3();
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    @Bean(initMethod = "init")
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/springboot_test");
        dataSource.setUsername("root");
        dataSource.setPassword("123456789g");
        return dataSource;
    }

    public Bean2 bean2() {
        return new Bean2();
    }

    @Bean
    public MapperFactoryBean<Mapper1> mapperFactoryBean1(SqlSessionFactory sqlSessionFactory) {
        MapperFactoryBean<Mapper1> mapperFactoryBean = new MapperFactoryBean<>(Mapper1.class);
        mapperFactoryBean.setSqlSessionFactory(sqlSessionFactory);
        return mapperFactoryBean;
    }

    @Bean
    public MapperFactoryBean<Mapper2> mapperFactoryBean2(SqlSessionFactory sqlSessionFactory) {
        MapperFactoryBean<Mapper2> mapperFactoryBean = new MapperFactoryBean<>(Mapper2.class);
        mapperFactoryBean.setSqlSessionFactory(sqlSessionFactory);
        return mapperFactoryBean;
    }
}
