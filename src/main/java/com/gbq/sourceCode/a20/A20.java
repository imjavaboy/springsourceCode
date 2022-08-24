package com.gbq.sourceCode.a20;


import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/23
 * @Copyright 总有一天，会见到成功
 */

public class A20 {
    public static void main(String[] args) {

        AnnotationConfigServletWebServerApplicationContext context = new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);

    }
}
