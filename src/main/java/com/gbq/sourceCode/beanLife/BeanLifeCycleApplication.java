package com.gbq.sourceCode.beanLife;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/11
 * @Copyright 总有一天，会见到成功
 */
@SpringBootApplication
public class BeanLifeCycleApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BeanLifeCycleApplication.class, args);
        context.close();
    }
}
