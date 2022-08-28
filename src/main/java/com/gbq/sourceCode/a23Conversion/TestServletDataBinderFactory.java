package com.gbq.sourceCode.a23Conversion;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestParameterPropertyValues;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.InvocableHandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ServletRequestDataBinderFactory;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/28
 * @Copyright 总有一天，会见到成功
 */

public class TestServletDataBinderFactory {
    public static void main(String[] args) throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("birthday", "1999|01|02");
        request.setParameter("address.name", "西安");

        User user = new User();

//        //工厂方式，无扩展
//        ServletRequestDataBinderFactory factory = new ServletRequestDataBinderFactory(null, null);
//        WebDataBinder dataBinder = factory.createBinder(new ServletWebRequest(request), user, "user");
//        ServletRequestDataBinder dataBinder = new ServletRequestDataBinder(user);


//        //用INitBinder转换
//        InvocableHandlerMethod method = new InvocableHandlerMethod(new Controller(), Controller.class.getMethod("aaa", WebDataBinder.class));
//        ArrayList lists = new ArrayList();
//        lists.add(method);
//
//
//        ServletRequestDataBinderFactory factory = new ServletRequestDataBinderFactory(lists, null);
//        WebDataBinder dataBinder = factory.createBinder(new ServletWebRequest(request), user, "user");

        ///用ConversionService转换
        FormattingConversionService service = new FormattingConversionService();
        service.addFormatter(new MyDateFormatter("用Service方法扩展"));
        ConfigurableWebBindingInitializer initializer = new ConfigurableWebBindingInitializer();
        initializer.setConversionService(service);
        ServletRequestDataBinderFactory factory = new ServletRequestDataBinderFactory(null, initializer);
        WebDataBinder dataBinder = factory.createBinder(new ServletWebRequest(request), user, "user");

        dataBinder.bind(new ServletRequestParameterPropertyValues(request));
        System.out.println(user);

    }

    static class Controller{
        @InitBinder
        public void aaa(WebDataBinder dataBinder){
            //扩展dataBinder的转换器
            dataBinder.addCustomFormatter(new MyDateFormatter("@InitBinder方式扩展"));

        }
    }

    static class User {
        @DateTimeFormat(pattern = "yyyy|MM|dd")
        private Date birthday;
        private Address address;

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }

        @Override
        public String toString() {
            return "User{" +
                    "birthday=" + birthday +
                    ", address=" + address +
                    '}';
        }
    }

     static class Address {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Address{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
