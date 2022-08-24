package org.springframework.aop.framework.autoproxy;


import org.aspectj.lang.annotation.Before;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectInstanceFactory;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.aspectj.AspectJMethodBeforeAdvice;
import org.springframework.aop.aspectj.SingletonAspectInstanceFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/22
 * @Copyright 总有一天，会见到成功
 */

public class A18 {

    @org.aspectj.lang.annotation.Aspect
    static class Aspect{
        @Before("execution(* foo())")
        public void before1(){
            System.out.println("aspect before1...");
        }
        @Before("execution(* foo())")
        public void  before2(){
            System.out.println("aspect before2...");
        }
        public void  after(){
            System.out.println("aspect after...");
        }
        public void  afterRunning(){
            System.out.println("aspect afterRunning...");
        }
        public void  afterThrowing(){
            System.out.println("aspect afterThrowing...");
        }
    }
    static class Target{
        public void foo(){
            System.out.println("target foo");
        }
    }
    public static void main(String[] args) {
        AspectInstanceFactory factory = new SingletonAspectInstanceFactory(new Aspect());
        //模拟高级切面转成低级切面’
        List<Advisor> list = new ArrayList<>();
        for (Method method : Aspect.class.getDeclaredMethods()) {

            if (method.isAnnotationPresent(Before.class)){
                //解析切点
                String expression = method.getAnnotation(Before.class).value();
                //创建切点
                AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
                pointcut.setExpression(expression);
                //通知
                AspectJMethodBeforeAdvice advice = new AspectJMethodBeforeAdvice(method,pointcut,factory);
                //切面
                Advisor advisor = new DefaultPointcutAdvisor(pointcut,advice);
                list.add(advisor);
            }
        }
        for (Advisor advisor : list) {
            System.out.println(advisor);
        }
    }
}
