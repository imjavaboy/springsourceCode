package com.gbq.sourceCode.day16_pointcut;


import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/21
 * @Copyright 总有一天，会见到成功
 */

public class A16 {
    public static void main(String[] args) throws NoSuchMethodException {
        AspectJExpressionPointcut pt1 = new AspectJExpressionPointcut();
        pt1.setExpression("execution(* bar())");
        //匹配方法
        System.out.println(pt1.matches(T1.class.getMethod("foo"),T1.class));
        System.out.println(pt1.matches(T1.class.getMethod("bar"),T1.class));

        AspectJExpressionPointcut pt2 = new AspectJExpressionPointcut();
        //根据注解匹配
        pt2.setExpression("@annotation(org.springframework.transaction.annotation.Transactional)");
        System.out.println(pt2.matches(T1.class.getMethod("foo"),T1.class));
        System.out.println(pt2.matches(T1.class.getMethod("bar"),T1.class));

        //
        StaticMethodMatcherPointcut pt3 = new StaticMethodMatcherPointcut() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                Annotation[] annotations = method.getAnnotations();
                HashSet<Annotation> hashSet = new HashSet<Annotation>(Arrays.asList(annotations));
                System.out.println(hashSet);
                if (hashSet.contains(Transactional.class)){
                    return true;
                }
                Annotation[] annotations1 = targetClass.getAnnotations();
                for (Annotation annotation : annotations) {
                    System.out.println(annotation.getClass());
                }
                HashSet<Annotation> hashSet1 = new HashSet<Annotation>(Arrays.asList(annotations1));
                System.out.println(hashSet1);
                if (hashSet1.contains(Transactional.class)){
                    return true;
                }
                return false;
            }
        };
        System.out.println(pt3.matches(T1.class.getMethod("foo"),T1.class));
    }

    static class T1{

        @Transactional
        public void foo(){

        }
        public void bar(){

        }
    }
    @Transactional
    static class T2{
        public void foo(){}

    }
    @Transactional
    interface  I3{
        void foo();
    }
    static class T3 implements I3{

        @Override
        public void foo() {

        }
    }

}
