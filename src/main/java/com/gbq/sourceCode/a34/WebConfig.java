package com.gbq.sourceCode.a34;


import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.function.*;
import org.springframework.web.servlet.function.support.HandlerFunctionAdapter;
import org.springframework.web.servlet.function.support.RouterFunctionMapping;

import static org.springframework.web.servlet.function.RequestPredicates.GET;
import static org.springframework.web.servlet.function.RouterFunctions.route;
import static org.springframework.web.servlet.function.ServerResponse.ok;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/28
 * @Copyright 总有一天，会见到成功
 */

public class WebConfig {

    @Bean // ⬅️内嵌 web 容器工厂
    public TomcatServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory(8080);
    }

    @Bean // ⬅️创建 DispatcherServlet
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    @Bean // ⬅️注册 DispatcherServlet, Spring MVC 的入口
    public DispatcherServletRegistrationBean servletRegistrationBean(DispatcherServlet dispatcherServlet) {
        return new DispatcherServletRegistrationBean(dispatcherServlet, "/");
    }

    @Bean
    public RouterFunctionMapping routerFunctionMapping(){
        return new RouterFunctionMapping();
    }
    @Bean
    public HandlerFunctionAdapter handlerFunctionAdapter(){
        return new HandlerFunctionAdapter();
    }
    @Bean
    public RouterFunction<ServerResponse> r1(){
       return   route(GET("/r1"), request -> ok().body("this is r1"));
    }
    @Bean
    public RouterFunction<ServerResponse> r2(){
        return   route(GET("/r2"), request -> ok().body("this is r2"));
    }
}
