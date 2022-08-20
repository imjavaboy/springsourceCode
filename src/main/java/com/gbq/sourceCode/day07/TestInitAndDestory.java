package com.gbq.sourceCode.day07;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/14
 * @Copyright 总有一天，会见到成功
 */

public class TestInitAndDestory {
    /**初始化顺序
     * 初始化1，@postConstruct
     * 初始化2，InitializingBean接口
     * 初始化3，@Bean里面的initMethod方法
     *
     * 销毁顺序
     * 销毁1，@PreDestory注解
     * 销毁2，DisposableBean接口
     * 销毁3，@Bean的destoryMethod方法
     * */
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("config", Config.class);
        // 解析@PostConstruct注解的bean后处理器
        context.registerBean(CommonAnnotationBeanPostProcessor.class);
        // 解析@Configuration、@Component、@Bean注解的bean工厂后处理器
        context.registerBean(ConfigurationClassPostProcessor.class);
        context.refresh();
        context.close();
    }

}
