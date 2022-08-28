package com.gbq.sourceCode.a34;


import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/28
 * @Copyright 总有一天，会见到成功
 */

public class A34 {
    public static void main(String[] args) {
        AnnotationConfigServletWebServerApplicationContext context =
                new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);
        /*
            学到了什么
            函数式控制器
                a. RouterFunctionMapping, 通过 RequestPredicate 映射
                b. handler 要实现 HandlerFunction 接口
                c. HandlerFunctionAdapter, 调用 handler

            对比
                a. RequestMappingHandlerMapping, 以 @RequestMapping 作为映射路径
                b. 控制器的具体方法会被当作 handler
                c. RequestMappingHandlerAdapter, 调用 handler
         */
    }
}
