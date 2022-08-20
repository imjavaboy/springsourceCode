package com.gbq.sourceCode.day09.service;


import org.springframework.stereotype.Service;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/14
 * @Copyright 总有一天，会见到成功
 */
@Service
public class MyService {
    public void foo(){
        System.out.println("fo0()方法");
        bar();
    }
    private void bar() {
        System.out.println("bar()");
    }
}
