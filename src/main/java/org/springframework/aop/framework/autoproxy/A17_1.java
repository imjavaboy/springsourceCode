package org.springframework.aop.framework.autoproxy;


import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

import javax.annotation.PostConstruct;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/22
 * @Copyright 总有一天，会见到成功
 */

public class A17_1 {
    /**
     * 代理的创建时机
     * 1. 初始化之后（没用循环依赖的时候），并且暂存于二级缓存
     * 2. 有循环依赖时候，在实例创建之后
     *
     * */
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean(ConfigurationClassPostProcessor.class);
        context.registerBean(Config.class);
        context.refresh();
        context.close();

    }

    @Configuration
    static class Config{
        @Bean//解析@Aspect,产生代理
        public AnnotationAwareAspectJAutoProxyCreator annotationAwareAspectJAutoProxyCreator(){
            return new AnnotationAwareAspectJAutoProxyCreator();
        }
        @Bean//解析@Autowired
        public AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor(){
            return new AutowiredAnnotationBeanPostProcessor();
        }
        @Bean//解析@PostConsutruct
        public CommonAnnotationBeanPostProcessor commonAnnotationBeanPostProcessor(){
            return new CommonAnnotationBeanPostProcessor();
        }
        @Bean
        public Advisor advisor(MethodInterceptor advice){
            AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
            pointcut.setExpression("execution(* foo())");
            return new DefaultPointcutAdvisor(pointcut,advice);
        }
        @Bean
        public MethodInterceptor advice(){
            return invocation -> {
                System.out.println("advice beofore.....");

                Object result = invocation.proceed();
                return result;
            };
        }
        @Bean
        public Bean1 bean1(){
            return new Bean1();
        }
        @Bean
        public Bean2 bean2(){
            return new Bean2();
        }

    }
    static class Bean1{
        public void foo(){}

        public Bean1() {
            System.out.println("bean1()");
        }
        @Autowired
        public void setBean2(Bean2 bean2){
            System.out.println("Bean1(bean2):"+bean2.getClass());
        }
        @PostConstruct
        public void init(){
            System.out.println("Bean1 init()");
        }
    }
    static class Bean2{
        private Bean1 bean1;

        public Bean2() {
            System.out.println("bean2()");
        }
        @Autowired
        public void setBean1(Bean1 bean1){
            this.bean1 = bean1;
            System.out.println("Bean2(bean1):"+bean1.getClass());
        }
        @PostConstruct
        public void init(){
            System.out.println("Bean2 init()");
        }
    }
}
