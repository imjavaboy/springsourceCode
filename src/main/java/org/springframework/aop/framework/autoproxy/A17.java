package org.springframework.aop.framework.autoproxy;


import com.gbq.sourceCode.cglibMock.Target;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectInstanceFactory;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.aspectj.AspectJMethodBeforeAdvice;
import org.springframework.aop.aspectj.SingletonAspectInstanceFactory;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.support.DefaultBeanFactoryPointcutAdvisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/22
 * @Copyright 总有一天，会见到成功
 */

public class A17 {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("aspect1",Aspect1.class);
        context.registerBean("config",Config.class);
        context.registerBean(ConfigurationClassPostProcessor.class);
        //是依赖注入之前和初始化之后的后处理器
        context.registerBean(AnnotationAwareAspectJAutoProxyCreator.class);
        context.refresh();
//        for (String name : context.getBeanDefinitionNames()) {
//            System.out.println(name);
//        }

        //找到y有资格的切面 .findEligibleAdvisors
        AnnotationAwareAspectJAutoProxyCreator creator = context.getBean(AnnotationAwareAspectJAutoProxyCreator.class);
        List<Advisor> advisors = creator.findEligibleAdvisors(Target2.class, "target2");
        for (Advisor advisor : advisors) {
            System.out.println(advisor);
        }

        /**前面方法返回不为空则表示需要创建代理*/
        Object o1 = creator.wrapIfNecessary(new Target1(), "target1", "target1");
        Object o2 = creator.wrapIfNecessary(new Target2(), "target2", "target2");
        System.out.println(o1.getClass());
        System.out.println(o2.getClass());
        ((Target1)o1).foo();
        ((Target2)o2).bar();

    }


    static class Target1{
        public void foo(){
            System.out.println("target1 foo");
        }
    }
    static class Target2{
        public void bar(){
            System.out.println("target2 bar");
        }
    }

    @Aspect
    static class Aspect1{
        @Before("execution(* foo())")
        public void before(){
            System.out.println("aspect1 before...");
        }
        @After("execution(* foo())")
        public void after(){
            System.out.println("aspect1 after...");
        }
    }
    @Configuration
    static class Config{
        /**
         * 低级切面
         * */
        @Bean
        public Advisor advisor3(MethodInterceptor advice3){
            AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
            pointcut.setExpression("execution(* foo())");
            return new DefaultPointcutAdvisor(pointcut,advice3);
        }
        @Bean
        public MethodInterceptor advice3(){
            return invocation -> {
                System.out.println("advice3 beofore.....");

                Object result = invocation.proceed();
                System.out.println("advice2 after.......");
                return result;
            };
        }
    }

}
