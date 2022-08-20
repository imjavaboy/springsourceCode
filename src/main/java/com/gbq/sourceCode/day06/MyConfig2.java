package com.gbq.sourceCode.day06;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/14
 * @Copyright 总有一天，会见到成功
 */

public class MyConfig2 implements ApplicationContextAware, InitializingBean {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        System.out.println("注入 ApplicationContext");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化");
    }

    @Bean
    public BeanFactoryPostProcessor processor1() {
        return beanFactory -> {
            System.out.println("执行 processor1");
        };
    }
}
