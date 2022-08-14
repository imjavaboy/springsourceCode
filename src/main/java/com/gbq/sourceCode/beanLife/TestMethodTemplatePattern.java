package com.gbq.sourceCode.beanLife;
import org.apache.naming.factory.BeanFactory;

import java.util.*;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/13
 * @Copyright 总有一天，会见到成功
 */


public class TestMethodTemplatePattern {

    /**
     *模板方法模式的学习，整体流程不变，接口不变，可扩展性更高。
     * 在模板方法里面做一个抽象的模型，具体实现到具体类里面去实现
     * */
    public static void main(String[] args) {
        MyBeanFactory myBeanFactory = new MyBeanFactory();
        myBeanFactory.getBean();
        System.out.println("+++++++++++++++++");
        myBeanFactory.addBeanPostProcessor(new BeanPostProcess() {
            @Override
            public void inject(Object bean) {
                System.out.println("@Autore");
            }
        });
        myBeanFactory.addBeanPostProcessor(new BeanPostProcess() {
            @Override
            public void inject(Object bean) {
                System.out.println("hello");
            }
        });
        myBeanFactory.getBean();
    }


    static class MyBeanFactory{
        public Object getBean(){
            Object bean = new Object();
            System.out.println("构造"+bean);
            System.out.println("依赖注入"+bean);
            for (BeanPostProcess process : processes) {

                process.inject(bean);
            }
            System.out.println("初始化"+bean);
            return bean;
        }
        public void addBeanPostProcessor(BeanPostProcess proc) {
            this.processes.add(proc);
        }

        private List<BeanPostProcess> processes = new ArrayList<>();
    }


}
interface BeanPostProcess{
    void inject(Object bean);
}
