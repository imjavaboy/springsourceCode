package com.gbq.sourceCode.a23Conversion;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestParameterPropertyValues;

import java.util.Date;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/28
 * @Copyright 总有一天，会见到成功
 */

public class TestServletDataBinderFactory {
    public static void main(String[] args) {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("birthday", "1999/01/02");
        request.setParameter("address.name", "西安");

        User user = new User();
        ServletRequestDataBinder dataBinder = new ServletRequestDataBinder(user);
        dataBinder.bind(new ServletRequestParameterPropertyValues(request));
        System.out.println(user);

    }


    static class User {
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
