package com.gbq.sourceCode.springProxy;

import com.gbq.sourceCode.cglibMock.Target;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/21
 * @Copyright 总有一天，会见到成功
 */

public class A15 {

    /**
     * //切面
        @Aspect
        static class MyAspect{

            //切点 execution表达式 *代表返回值，任意包有foo()的
            @Before("execution(* foo())")
           public void before(){
                System.out.println("前置增强");
            }
            @After("execution(* foo())")
            public void after(){
                System.out.println("后置增强");
            }
    }*/

    /**
     * 创建代理选择那种
     * 1 ProxyTargetClass = false 目标类实现接口 则jdk
     * 2 ProxyTargetClass = false 目标类木有实现接口 则cglib
     * 2 ProxyTargetClass = trure 则cglib
     * */
    public static void main(String[] args) {
        //1. 准备切点
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* foo())");

        //备好通知
        MethodInterceptor adicvice = new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                System.out.println("beofre");
                Object res = invocation.proceed();
                System.out.println("after");
                return res;
            }
        };

        //备好切面
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, adicvice);

        //创建代理
        Target2 target2 = new Target2();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target2);
        proxyFactory.setInterfaces(target2.getClass().getInterfaces());
        proxyFactory.addAdvisor(advisor);
        I1 proxy = (I1)proxyFactory.getProxy();//class com.gbq.sourceCode.springProxy.A15$Target1$$EnhancerBySpringCGLIB$$e4eda574

        System.out.println(proxy.getClass());
        proxy.foo();
        proxy.bar();
        /**/
    }


    interface I1{
        void foo();
        void bar();
    }

    static class Target1 implements I1{
        @Override
        public void foo() {
            System.out.println("target1 foo()");
        }

        @Override
        public void bar() {
            System.out.println("target1 bar()");
        }
    }
    static class Target2 {

        public void foo() {
            System.out.println("target2 foo()");
        }


        public void bar() {
            System.out.println("target2 bar()");
        }
    }

}
