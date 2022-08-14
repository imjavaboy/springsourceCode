package com.gbq.sourceCode.beanLife;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/11
 * @Copyright 总有一天，会见到成功
 */
@Component
public class LifeCycleBean {
    public LifeCycleBean() {
        System.out.println("构造");
    }

    @Autowired
    public void autowire(@Value("${JAVA_HOME}") String name) {
        System.out.println("依赖注入：{}" + name);
    }

    @PostConstruct
    public void init() {
        System.out.println("初始化");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("销毁");
    }

}
