package com.gbq.sourceCode.springDev;


import com.gbq.sourceCode.springDev.pojo.User;
import com.gbq.sourceCode.springDev.service.UserServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/22
 * @Copyright 总有一天，会见到成功
 */

public class App {
    /**
     * Spring核心容器
     * 1. Bean模块：框架基础的,包括控制反转和依赖注入
     * 2.core模块，底层，包括资源访问
     * 3. context 包括数据资源
     * */

    /**
     * IOC的控制反转，容器，配置方式：Xml，java,注解，生命周期，
     * 依赖注入：从容器中获取bean,并注入到程序中
     * 依赖注入的方式：构造器，set注入，设值注入。
     * */



    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("aspects.xml", "dao.xml", "serviceImpl.xml");

        UserServiceImpl userService = context.getBean("userService", UserServiceImpl.class);
        List<User> userList = userService.findUserList();

        System.out.println(userList);
    }
}
