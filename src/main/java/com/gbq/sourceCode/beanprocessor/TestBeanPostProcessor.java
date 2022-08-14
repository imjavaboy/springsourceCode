package com.gbq.sourceCode.beanprocessor;


import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;

import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/13
 * @Copyright 总有一天，会见到成功
 *
 * @Autowired注解对应的后处理器是AutowiredAnnotationBeanPostProcessor；
 * @Value注解需要配合@Autowired注解一起使用，所以也用到了AutowiredAnnotationBeanPostProcessor后处理器，然后@Value注解还需要再用到ContextAnnotationAutowireCandidateResolver解析器，否则会报错；
 * @Resource、@PostConstruct、@PreDestroy注解对应的后处理器是CommonAnnotationBeanPostProcessor；
 * @ConfigurationProperties注解对应的后处理器是ConfigurationPropertiesBindingPostProcessor。

 */

public class TestBeanPostProcessor {
    public static void main(String[] args) {
        /**GenericApplicationContext不会添加任何后处理器*/
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("bean1",Bean1.class);
        context.registerBean("bean2",Bean2.class);
        context.registerBean("bean3",Bean3.class);
        context.registerBean("bean4",Bean4.class);

        //解析@Value注解的解析器
        context.getDefaultListableBeanFactory().setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
        //解析@Autowired和@Value注解的后处理器
        context.registerBean(AutowiredAnnotationBeanPostProcessor.class);

        //解析 @Resource @PostConstruct @PreDestroy
        context.registerBean(CommonAnnotationBeanPostProcessor.class);

//        ConfigurationPropertiesBindingPostProcessor.registerNatives(context.getDefaultListableBeanFactory());

        context.refresh();
        System.out.println(context.getBean(Bean4.class));
        context.destroy();
    }
}
