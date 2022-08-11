package com.gbq.sourceCode.sourceTest;


import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/11
 * @Copyright 总有一天，会见到成功
 */

public class ApplicationTest {

    public static void main(String[] args) {
//        testClassPathXmlApplicationContext();
//        testFileSystemXmlApplicationContext();
//        testMockClassPathAndFileSystemXmlApplicationContext();
//        testAnnotationConfigApplicationContext();
        testAnnotationConfigServletWebServerApplicationContext();
    }



    /**
     *  测试ClassPathXmlApplicationContext
     * 基础classpath下的xml格式配置文件创建
     */
    private static void testClassPathXmlApplicationContext(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean01.xml");

        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
        System.out.println(context.getBean(Bean2.class).getBean1());
    }

    /**
     *  基于磁盘路径下的xml格式文件配置来创建
    *
     */
    private static void testFileSystemXmlApplicationContext(){
        FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("G:\\ShiyanshiProject\\SpringSourceCode\\src\\main\\resources\\bean01.xml");

        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
        System.out.println(context.getBean(Bean2.class).getBean1());
    }


    /**
     *  模拟ClassPathXmlApplication 和FileSystemXmlApplication读取数据的一些底层操作
     */
    private static void testMockClassPathAndFileSystemXmlApplicationContext(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        System.out.println("读取之前的数据");
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
        System.out.println("================");
        System.out.println("读取数据之后");
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
//        reader.loadBeanDefinitions("bean01.xml");//通过普通方式
//        reader.loadBeanDefinitions(new ClassPathResource("bean01.xml"));
        reader.loadBeanDefinitions(new FileSystemResource("src/main/resources/bean01.xml"));
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }


    }


    /**
     *  基于java配置类的bean容器
     */
    private static void testAnnotationConfigApplicationContext(){
        /**默认会加上五个后置处理器
         * org.springframework.context.annotation.internalConfigurationAnnotationProcessor
         * org.springframework.context.annotation.internalAutowiredAnnotationProcessor
         * org.springframework.context.annotation.internalCommonAnnotationProcessor
         * org.springframework.context.event.internalEventListenerProcessor
         * org.springframework.context.event.internalEventListenerFactory
         * */
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
        System.out.println(context.getBean(Bean2.class).getBean1());

    }

    /**
     *  基于配置类实现，用于web服务器
     */
    private static void testAnnotationConfigServletWebServerApplicationContext(){
        AnnotationConfigServletWebServerApplicationContext context = new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);

    }


    @Configuration
    static class WebConfig{
        @Bean
        /**web的tomcat工厂*/
        public ServletWebServerFactory servletWebServerFactory(){
            return new TomcatServletWebServerFactory();
        }
        @Bean
        /**路径分发器*/
        public DispatcherServlet dispatcherServlet(){
            return new DispatcherServlet();
        }
        @Bean
        /**将dispatcherServlet主从到Webserver上*/
        public DispatcherServletRegistrationBean dispatcherServletRegistrationBean(DispatcherServlet dispatcherServlet){
            return new DispatcherServletRegistrationBean(dispatcherServlet,"/");
        }
        @Bean("/hello")
        public Controller controller1(){
            return (httpServletRequest, httpServletResponse) -> {
                httpServletResponse.getWriter().println("hello,world");
                return null;
            };
        }

    }


    /**单元测试的过程中如果要解析一些Spring注解，
     * 比如@Configuration的时候不要把相关类定义到写单元测试类的内部类，会读取不到
     */

    @Configuration
    static class Config {
        @Bean
        public Bean1 bean1() {
            return new Bean1();
        }

        @Bean
        public Bean2 bean2(Bean1 bean1) {
            Bean2 bean2 = new Bean2();
            bean2.setBean1(bean1);
            return bean2;
        }
    }






    static class Bean1{

    }
    static class Bean2{
        private Bean1 bean1;

        public Bean1 getBean1() {
            return bean1;
        }

        public void setBean1(Bean1 bean1) {
            this.bean1 = bean1;
        }
    }

}
