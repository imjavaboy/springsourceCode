package com.gbq.sourceCode.beanprocessor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/13
 * @Copyright 总有一天，会见到成功
 */

public class Bean1 {
    private Bean2 bean2;
    private String java_home;
    private Bean3 bean3;

    @Autowired
    public void setBean2(Bean2 bean2){
        System.out.println("@Autowired生效"+bean2);
        this.bean2 = bean2;
    }
    @Autowired
    public void setJava_home(@Value("${JAVA_HOME}") String java_home){
        this.java_home = java_home;
        System.out.println("@Value生效"+java_home);

    }
    @Resource
    public void SetBean3(Bean3 bean3){
        this.bean3 = bean3;
        System.out.println("@Resource生效"+bean3);
    }
    @PostConstruct
    public void init(){
        System.out.println("@PostConstruct注解生效");

    }
    @PreDestroy
    public void destory(){
        System.out.println("@destotry注解生效");
    }

    @Override
    public String toString() {
        return "Bean1{" +
                "bean2=" + bean2 +
                ", java_home='" + java_home + '\'' +
                ", bean3=" + bean3 +
                '}';
    }
}
class Bean2{

}
class Bean3{

}
//@ConfigurationProperties(prefix = "java")
class Bean4 {
    private String home;
    private String version;

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Bean4{" +
                "home='" + home + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
