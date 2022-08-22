package com.gbq.sourceCode.springDev.log;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/22
 * @Copyright 总有一天，会见到成功
 */
@Aspect
public class LogAspect {

    @Around("execution(* com.gbq.sourceCode.springDev.*.*(..))")
    public Object businessService(ProceedingJoinPoint pjd) throws Throwable {
        Method method = ((MethodSignature) pjd.getSignature()).getMethod();
        System.out.println("execute Method" + method.getName());
        return pjd.proceed();
    }
}
