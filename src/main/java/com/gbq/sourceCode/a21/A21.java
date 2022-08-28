package com.gbq.sourceCode.a21;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ExpressionValueMethodArgumentResolver;
import org.springframework.web.method.annotation.RequestHeaderMapMethodArgumentResolver;
import org.springframework.web.method.annotation.RequestParamMapMethodArgumentResolver;
import org.springframework.web.method.annotation.RequestParamMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolverComposite;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.PathVariableMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.ServletCookieValueMethodArgumentResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/26
 * @Copyright 总有一天，会见到成功
 */

public class A21 {
    public static void main(String[] args) throws NoSuchMethodException {
        AnnotationConfigServletWebServerApplicationContext context =
                new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);

        //控制器方法呗装为HandlerMethod
        HandlerMethod handlerMethod = new HandlerMethod(new Controller(), Controller.class.getMethod("test", String.class, String.class, int.class, String.class, MultipartFile.class, int.class, String.class, String.class, String.class, HttpServletRequest.class, User.class, User.class, User.class));


        ModelAndViewContainer container = new ModelAndViewContainer();


        //打印每个参数值
        for (MethodParameter methodParameter : handlerMethod.getMethodParameters()) {
            //多个解析器
            HandlerMethodArgumentResolverComposite composite = new HandlerMethodArgumentResolverComposite();
            composite.addResolvers(
                 new RequestParamMethodArgumentResolver(context.getBeanFactory(), true),
                    new PathVariableMethodArgumentResolver(),
                    new RequestHeaderMapMethodArgumentResolver(),
                    new ServletCookieValueMethodArgumentResolver(context.getBeanFactory()),
                    new ExpressionValueMethodArgumentResolver(context.getBeanFactory())

            );


            String annotions = Arrays.stream(methodParameter.getParameterAnnotations()).map(annotation ->
                    annotation.annotationType().getSimpleName()).collect(Collectors.joining());
            //解析器requestparam的，false代表不必须有
            RequestParamMethodArgumentResolver resolver = new RequestParamMethodArgumentResolver(null, false);
            String str =  annotions.length() > 0 ? "@ "+annotions : "";

            //将请求进行解析
          //  resolver.resolveArgument(methodParameter,container,new ServletWebRequest())
            methodParameter.initParameterNameDiscovery(new DefaultParameterNameDiscoverer());
            System.out.println("["+methodParameter.getParameterIndex()+"   :   "+ str +methodParameter.getParameterType().getSimpleName()+"||"+methodParameter.getParameterName()+"]");
        }
    }

    static class Controller{
        public void test(
                @RequestParam("name1") String name1,
                String name2,
                @RequestParam("age") int age,
                @RequestParam(name="home",defaultValue = "${JAVA_HOME}") String home1,
                @RequestParam("file")MultipartFile file,
                @PathVariable("id") int id,
                @RequestHeader("Content-Type") String header,
                @CookieValue("token") String token,
                @Value("${JAVA_HOME}")String home2,
                HttpServletRequest request,
                @ModelAttribute User user1,
                User user2,
                @RequestBody User user3
                ){

        }
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
