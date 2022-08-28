package com.gbq.sourceCode.a20;


import com.gbq.sourceCode.springDev.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.yaml.snakeyaml.Yaml;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/24
 * @Copyright 总有一天，会见到成功
 */
@RestController
public class Controller1 {

    @GetMapping("/test1")
    public ModelAndView test1(){
        System.out.println("test1()");
        return null;
    }
    @PostMapping("/test2")
    public ModelAndView test2(@RequestParam("name") String name){
        System.out.println("test2()");
        return null;
    }
    @PutMapping("/test3")
    public ModelAndView test3(@Token String token){
        System.out.println("test3()"+ token);
        return null;
    }
    @RequestMapping("/test4.yml")
//    @ResponseBody
    @YML
    public User test4(){
        System.out.println("test4()");
        return new User("zhangsan",14);
    }
    static class User{
        String name;
        Integer age;

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
    }


}
