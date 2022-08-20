package com.gbq.sourceCode.day09.aop;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/14
 * @Copyright 总有一天，会见到成功
 */
@Aspect
public class MyAspect {
    @Before("execution(* com.gbq.sourceCode.day09.service.MyService.*())")
    public void before(){
        System.out.println("before");

    }


}
