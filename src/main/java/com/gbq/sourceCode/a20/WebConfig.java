package com.gbq.sourceCode.a20;


import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author 郭本琪
 * @description 三个必须的组件
 * @date 2022/8/23
 * @Copyright 总有一天，会见到成功
 */
@Configuration
@ComponentScan

@PropertySource("classpath:application.properties")
@EnableConfigurationProperties({WebMvcProperties.class, ServerProperties.class})
public class WebConfig {
    //内嵌web容器工厂
    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory(ServerProperties serverProperties){
        return new TomcatServletWebServerFactory(serverProperties.getPort());
    }
    /**创建DispatcherServlet*/
    @Bean
    public DispatcherServlet dispatcherServlet(){
        return new DispatcherServlet();
    }

    /**SpringMvc的入口*/
    @Bean
    public DispatcherServletRegistrationBean dispatcherServletRegistrationBean(DispatcherServlet dispatcherServlet,WebMvcProperties webMvcProperties){

        int loadOnStartup = webMvcProperties.getServlet().getLoadOnStartup();
        System.out.println("库萨克库萨克"+loadOnStartup);
        DispatcherServletRegistrationBean registrationBean = new DispatcherServletRegistrationBean(dispatcherServlet, "/");
        registrationBean.setLoadOnStartup(webMvcProperties.getServlet().getLoadOnStartup());
        return registrationBean;
    }


}
