package com.gbq.sourceCode.day07;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/14
 * @Copyright 总有一天，会见到成功
 */
@Configuration
public class Config {
    @Bean(initMethod = "init3")
    public Bean1 bean1(){
        return new Bean1();
    }
    @Bean(destroyMethod = "destory3")
    public Bean2 bean2(){
        return new Bean2();
    }
}
