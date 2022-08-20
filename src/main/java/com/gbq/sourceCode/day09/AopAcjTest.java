package com.gbq.sourceCode.day09;


import com.gbq.sourceCode.day09.service.MyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/14
 * @Copyright 总有一天，会见到成功
 */
@SpringBootApplication
public class AopAcjTest {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AopAcjTest.class, args);

        MyService service = context.getBean(MyService.class);
        System.out.println("Service的类型"+service.getClass());
        service.foo();
        context.close();
//        new MyService().foo();
    }
}
