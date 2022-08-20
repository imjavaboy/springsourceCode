package com.gbq.sourceCode.day07;


import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/14
 * @Copyright 总有一天，会见到成功
 */

public class Bean1 implements InitializingBean {
    @PostConstruct
    public void init1(){
        System.out.println("初始化1，@postConstruct");
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化2，InitializingBean接口");
    }
    public void init3(){
        System.out.println("初始化3，@Bean里面的initMethod方法");
    }
}
