package com.gbq.sourceCode.a20;


import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;
import java.util.Map;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/23
 * @Copyright 总有一天，会见到成功
 */

public class A20 {
    public static void main(String[] args) throws Exception {

        AnnotationConfigServletWebServerApplicationContext context = new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);
        //解析@RequestMapping以及其派生注解，生成路径和控制器方法映射关系，在初始化生成
        RequestMappingHandlerMapping handlerMapping = context.getBean(RequestMappingHandlerMapping.class);
        //获取映射结果
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
        handlerMethods.forEach((k,v)->{
            System.out.println(k+"=="+v);
        });
        //请求来了，获取请求方法
        //返回处理器执行连
//        MockHttpServletRequest request = new MockHttpServletRequest("GET", "test1");
//        request.setParameter("name","zhangsan");
//        MockHttpServletResponse response = new MockHttpServletResponse();
//        HandlerExecutionChain chain = handlerMapping.getHandler(request);
//
//
//
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>");
        MyRequstMappingHandlerAdapter handlerAdapter = context.getBean(MyRequstMappingHandlerAdapter.class);

        //参数解析器
        List<HandlerMethodArgumentResolver> argumentResolvers = handlerAdapter.getArgumentResolvers();
//        handlerAdapter.invokeHandlerMethod(request,response,(HandlerMethod) chain.getHandler());
        for (HandlerMethodArgumentResolver argumentResolver : argumentResolvers) {
            System.out.println(argumentResolver);
        }
        System.out.println("............................");
        //返回值处理器
        for (HandlerMethodReturnValueHandler returnValueHandler : handlerAdapter.getReturnValueHandlers()) {
            System.out.println(returnValueHandler);
        }



    }
}
