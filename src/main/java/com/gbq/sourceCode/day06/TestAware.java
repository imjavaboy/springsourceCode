package com.gbq.sourceCode.day06;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.Aware;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author 郭本琪
 * @description Aware接口测试
 * @date 2022/8/14
 * @Copyright 总有一天，会见到成功
 */

public class TestAware {
    /**
     * Aware 接口用于注入一些与容器相关信息，例如：
     *
     * a. BeanNameAware 注入 Bean 的名字
     *
     * b. BeanFactoryAware 注入 BeanFactory 容器
     *
     * c. ApplicationContextAware 注入 ApplicationContext 容器
     *
     * d. EmbeddedValueResolverAware 注入 解析器，解析${}

     * */
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("myBean",MyBean.class);
        context.registerBean(AutowiredAnnotationBeanPostProcessor.class);
        context.registerBean(CommonAnnotationBeanPostProcessor.class);
        context.refresh();
        context.close();
    }
    @Test
    public void testMyConfig(){
        /**
         *
         * BeanFactoryPostProcessor会在Java配置类初始化之前执行，
         * 而Java配置类里面却定义了一个BeanFactoryPostProcessor，
         * 要创建其中的 BeanFactoryPostProcessor ，
         * 必须提前创建 Java 配置类，这样BeanFactoryPostProcessor就会在Java配置类初始化后执行了
         * 而此时的 BeanPostProcessor 还未准备好，导致 @Autowired 等注解失效。
         * */



        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("myConfig1",MyConfig1.class);
        context.registerBean(AutowiredAnnotationBeanPostProcessor.class);
        context.registerBean(CommonAnnotationBeanPostProcessor.class);
        context.registerBean(ConfigurationClassPostProcessor.class);
        context.refresh();
        context.close();
    }
    @Test
    public void testMyConfig2(){
        /**
         *
         * BeanFactoryPostProcessor会在Java配置类初始化之前执行，
         * 而Java配置类里面却定义了一个BeanFactoryPostProcessor，
         * 要创建其中的 BeanFactoryPostProcessor ，
         * 必须提前创建 Java 配置类，这样BeanFactoryPostProcessor就会在Java配置类初始化后执行了
         * 而此时的 BeanPostProcessor 还未准备好，导致 @Autowired 等注解失效。
         * */

        /**
         *
         * 用 @Autowired注解就能实现啊，为啥还要用 Aware 接口呢？
         * InititalizingBean 接口可以用 @PostConstruct注解实现，为啥还要用InititalizingBean呢？
         * 简单地说：
         *
         * @Autowired 和@PostConstruct注解的解析需要用到 Bean 后处理器，属于扩展功能，而 Aware 接口属于内置功能，不加任何扩展，Spring就能识别；
         *
         * 某些情况下，扩展功能会失效，而内置功能不会失效
         * */



        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("myConfig2",MyConfig2.class);
        context.registerBean(AutowiredAnnotationBeanPostProcessor.class);
        context.registerBean(CommonAnnotationBeanPostProcessor.class);
        context.registerBean(ConfigurationClassPostProcessor.class);
        context.refresh();
        context.close();
    }
}
