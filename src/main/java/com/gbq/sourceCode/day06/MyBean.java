package com.gbq.sourceCode.day06;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/14
 * @Copyright 总有一天，会见到成功
 */

public class MyBean implements BeanNameAware, ApplicationContextAware, InitializingBean {
    @Override
    public void setBeanName(String name) {
        System.out.println("当前bean "+this + "名称："+name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(("当前bean：" + this + "，实现 ApplicationContextAware 调用的方法，容器叫：" + applicationContext));

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(("当前bean：" + this + "，实现 InitializingBean 调用的方法，初始化"));
    }
    @Autowired
    public void aaa(ApplicationContext applicationContext){
        System.out.println(("当前bean：" + this + "，使用 @Autowired 容器是：" + applicationContext));

    }
    @PostConstruct
    public void init() {
        System.out.println(("当前bean：" + this + "，使用 @PostConstruct 初始化"));
    }
}
