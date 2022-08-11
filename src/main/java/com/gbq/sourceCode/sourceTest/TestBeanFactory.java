package com.gbq.sourceCode.sourceTest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

/**
 * @author 郭本琪
 * @description 测试bean工厂
 * @date 2022/8/10
 * @Copyright 总有一天，会见到成功
 */

public class TestBeanFactory {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Config.class).setScope("singleton").getBeanDefinition();
        beanFactory.registerBeanDefinition("config",beanDefinition);
        String[] definitionNames = beanFactory.getBeanDefinitionNames();
        for (String name:definitionNames) {
            System.out.println(name);

        }
        //给BeanFacotory添加后处理器
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);

        /**多了后置的内处理器
         * org.springframework.context.annotation.internalConfigurationAnnotationProcessor
         * org.springframework.context.annotation.internalAutowiredAnnotationProcessor
         * org.springframework.context.annotation.internalCommonAnnotationProcessor
         * org.springframework.context.event.internalEventListenerProcessor
         * org.springframework.context.event.internalEventListenerFactory
         *
         * */
        System.out.println("=============");
        for (String name:beanFactory.getBeanDefinitionNames()) {
            System.out.println(name);

        }

        /**
         * BeanFactory后处理器的主要功能，是补充了一些bean的定义，例如加入bean(将bean加入bean工厂)
         * */
        Map<String, BeanFactoryPostProcessor> processorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        processorMap.values().stream().forEach(beanFactoryPostProcessor -> {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        });
        //可以打印bean1
        for (String name:beanFactory.getBeanDefinitionNames()) {
            System.out.println(name);
        }
        /**
         * Bean的后处理器，针对bean的生命周期各个阶段提供扩展，如@Autowired,@Resource
         *
         * */
//        beanFactory.preInstantiateSingletons();//预先实例化所有的单例
        System.out.println("================");
        beanFactory.getBeansOfType(BeanPostProcessor.class).values()
                .stream()
                .sorted(Objects.requireNonNull(beanFactory.getDependencyComparator()))
                .forEach(beanPostProcessor -> {
            System.out.println(">>>"+beanPostProcessor);

            /*** 所以说Autowired比Resource更有优先级
             * org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor@17695df3
             * >>>org.springframework.context.annotation.CommonAnnotationBeanPostProcessor@6c9f5c0d
             * */
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        });
//        System.out.println(beanFactory.getBean(Bean1.class).getBean2());


        /**
         * BeanFactory 不会做
         * 1. 不会主动去调用BeanFactory的后置处理器
         * 2. 不会主动添加Bean的后置处理器
         * 3. 不会主动去实例化单例
         * 4. 不会去解析beanFactory,不会解析${} #{}
         * */

        System.out.println(beanFactory.getBean(Bean1.class).getBean());

    }

    @Configuration
    static class Config{
        @Bean
        public Bean1 bean1(){
            return new Bean1();
        }
        @Bean
        public Bean2 bean2(){
            return new Bean2();
        }
        @Bean
        public Bean3 bean3() {
            return new Bean3();
        }

        @Bean
        public Bean4 bean4() {
            return new Bean4();
        }

    }

    static class Bean1{
        public Bean1() {
            System.out.println("构造bean1");
        }
        @Autowired
        private Bean2 bean2;

        public Bean2 getBean2() {
            return bean2;
        }

        @Autowired
        @Resource(name = "bean4")
        private Iner bean3;

        public Iner getBean() {
            return bean3;
        }
    }
    static class Bean2{
        public Bean2() {
            System.out.println("构造bean2");
        }
    }
    interface Iner{

    }
    static class Bean3 implements Iner{
        public Bean3() {
            System.out.println("构造bEan3");
        }
    }
    static class Bean4 implements Iner{
        public Bean4() {
            System.out.println("构造bEan3");
        }
    }
}
