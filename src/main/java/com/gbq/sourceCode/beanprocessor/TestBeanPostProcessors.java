package com.gbq.sourceCode.beanprocessor;


import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.core.MethodParameter;
import org.springframework.core.env.StandardEnvironment;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/14
 * @Copyright 总有一天，会见到成功
 */

public class TestBeanPostProcessors {
    public static void main(String[] args) throws Throwable {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        /**
         * 这里为了省事就不使用 beanFactory.registerBeanDefinition()方法去添加类的描述信息了
         *        直接使用 beanFactory.registerSingleton可以直接将Bean的单例对象注入进去，
         *        后面调用beanFactory.getBean()方法的时候就不会去根据Bean的定义去创建Bean的实例了，
         *        也不会有懒加载和依赖注入的初始化过
         */
        beanFactory.registerSingleton("bean2",new Bean2());
        beanFactory.registerSingleton("bean3",new Bean3());
        //@Value
        beanFactory.setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
        beanFactory.addEmbeddedValueResolver(new StandardEnvironment()::resolvePlaceholders);

//        // 1. 查找哪些属性、方法加了 @Autowired，这称之为InjectionMetadata
//        // 创建后处理器
//        AutowiredAnnotationBeanPostProcessor proc = new AutowiredAnnotationBeanPostProcessor();
//        // 后处理器在解析@Autowired和@Value的时候需要用到其他Bean，
//        // 而BeanFactory提供了需要的Bean，所以需要把BeanFactory传给这个后处理器
//        proc.setBeanFactory(beanFactory);
        Bean1 bean1 = new Bean1();
//        System.out.println(bean1);
//        proc.postProcessProperties(null,bean1,"bean1");
//        System.out.println(bean1);
//
//
//        Method findAutowiringMetadata = AutowiredAnnotationBeanPostProcessor.class.getDeclaredMethod("findAutowiringMetadata",String.class, Class.class, PropertyValues.class);
//        findAutowiringMetadata.setAccessible(true);
//        // 获取Bean1上加了@Value @Autowired注解的成员变量和方法参数信息
//        InjectionMetadata metadata = null;
//        try {
//            metadata = (InjectionMetadata) findAutowiringMetadata.invoke(proc, "bean1", Bean1.class, null);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        System.out.println(metadata);
//        // 2. 调用 InjectionMetaData 来进行依赖注入，注入时按类型查找值
//        metadata.inject(bean1, "bean1", null);
//        System.out.println(bean1);



        // 3.1 @Autowired加在成员变量上，InjectionMetatadata给Bean1注入Bean3的过程
        // 通过InjectionMetadata把Bean1加了@Autowired注解的属性的BeanName先拿到，这里假设拿到的BeanName就是 bean3
        // 通过BeanName反射获取到这个属性，
        Field bean3Field = Bean1.class.getDeclaredField("bean3");
        // 设置私有属性可以被访问
        bean3Field.setAccessible(true);
        // 将这个属性封装成一个DependencyDescriptor对象
        DependencyDescriptor dd1 = new DependencyDescriptor(bean3Field, false);
        // 再执行beanFactory的doResolveDependency
        Bean3 bean3Value = (Bean3) beanFactory.doResolveDependency(dd1, null, null, null);
        System.out.println(bean3Value);
        // 给Bean1的成员bean3赋值
        bean3Field.set(bean1, bean3Value);
        System.out.println(bean1);

        // 3.2 @Autowired加在方法上，InjectionMetatadata给Bean1注入Bean2的过程
        Method setBean2 = Bean1.class.getDeclaredMethod("setBean2", Bean2.class);
        DependencyDescriptor dd2 = new DependencyDescriptor(new MethodParameter(setBean2, 0), true);
        Bean2 bean2Value = (Bean2) beanFactory.doResolveDependency(dd2, "bean2", null, null);
        System.out.println(bean2Value);
        // 给Bean1的setBean2()方法的参数赋值
        setBean2.invoke(bean1, bean2Value);
        System.out.println(bean1);

        // 3.3 @Autowired加在方法上，方法参数为String类 型，加了@Value，
        // InjectionMetadata给Bean1注入环境变量JAVA_HOME属性的值
        Method setJava_home = Bean1.class.getDeclaredMethod("setJava_home", String.class);
        DependencyDescriptor dd3 = new DependencyDescriptor(new MethodParameter(setJava_home, 0), true);
        String java_home = (String) beanFactory.doResolveDependency(dd3, null, null, null);
        System.out.println(java_home);
        setJava_home.invoke(bean1, java_home);
        System.out.println(bean1);



    }
}
