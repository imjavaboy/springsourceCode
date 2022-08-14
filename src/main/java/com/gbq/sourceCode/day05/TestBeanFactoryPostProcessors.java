package com.gbq.sourceCode.day05;


import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/14
 * @Copyright 总有一天，会见到成功
 */

public class TestBeanFactoryPostProcessors {
    public static void main(String[] args) throws IOException {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("config",Config.class);
//
////        // 添加Bean工厂后处理器ConfigurationClassPostProcessor
////        // 解析@ComponentScan、@Bean、@Import、@ImportResource注解
////        context.registerBean(ConfigurationClassPostProcessor.class);
////
////        context.registerBean(MapperScannerConfigurer.class,bd -> {
////            bd.getPropertyValues().add("basePackage","com.gbq.sourceCode.day05.mapper");
////        });
//
//        /**查看某些注解*/
//        ComponentScan componentScan = AnnotationUtils.findAnnotation(Config.class, ComponentScan.class);
//        if (componentScan != null){
//            for (String s : componentScan.basePackages()) {
////                System.out.println(s);
//                s.replace(".","/");
//                String path = "classpath*:" + s.replace('.', '/') + "/**/*.class";
//                System.out.println(path);
//                //读取类的辕信息
//                CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
//                /**查看该路径下的包*/
//                Resource[] resources = context.getResources(path);
//                for (Resource resource : resources) {
//                    System.out.println(resource);
//                    MetadataReader reader = factory.getMetadataReader(resource);
//                    String className = reader.getClassMetadata().getClassName();
////                    System.out.println("类名"+ className);
////                    System.out.println("是否嘉"+reader.getAnnotationMetadata().hasAnnotation(Component.class.getName()));
////                    System.out.println("是否嘉派生"+reader.getAnnotationMetadata().hasMetaAnnotation(Component.class.getName()));
//                    AnnotationBeanNameGenerator gen = new AnnotationBeanNameGenerator();
//                    AnnotationMetadata annotationMetadata = reader.getAnnotationMetadata();
//                    if (annotationMetadata.hasMetaAnnotation(Component.class.getName()) || annotationMetadata.hasMetaAnnotation(Component.class.getName()) ){
//                        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(reader.getClassMetadata().
//                                getClassName()).getBeanDefinition();
//
//                        DefaultListableBeanFactory beanFactory = context.getDefaultListableBeanFactory();
//                        String name = gen.generateBeanName(beanDefinition, beanFactory);
//                        beanFactory.registerBeanDefinition(name,beanDefinition);
//                    }
//
//                }
//            }
//        }

        context.addBeanFactoryPostProcessor(new BqAtBeanPostProcessor());

        //初始化容器
        context.refresh();

        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
        context.destroy();
    }
}
