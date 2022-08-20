package com.gbq.sourceCode.day07;


import org.springframework.beans.factory.DisposableBean;

import javax.annotation.PreDestroy;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/14
 * @Copyright 总有一天，会见到成功
 */

public class Bean2 implements DisposableBean {

    @Override
    public void destroy() throws Exception {
        System.out.println("销毁2，DisposableBean接口");
    }
    @PreDestroy
    public void destory2(){
        System.out.println("销毁1，@PreDestory注解");
    }

    public void destory3(){
        System.out.println("销毁3，@Bean的destoryMethod方法");
    }

}
