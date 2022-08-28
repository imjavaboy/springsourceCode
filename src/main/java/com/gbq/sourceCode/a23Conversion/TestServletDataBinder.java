package com.gbq.sourceCode.a23Conversion;


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

public class TestServletDataBinder {

    public static void main(String[] args) {
        //web环境下的数据绑定
        MyBean myBean = new MyBean();
        ServletRequestDataBinder dataBinder = new ServletRequestDataBinder(myBean);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("a","10");
        request.setParameter("b","hello");
        request.setParameter("c","1998/09/08");
        dataBinder.bind(new ServletRequestParameterPropertyValues(request));
        System.out.println(myBean);
    }

    static class MyBean {
        private int a;
        private String b;
        private Date c;

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        public Date getC() {
            return c;
        }

        public void setC(Date c) {
            this.c = c;
        }

        @Override
        public String toString() {
            return "MyBean{" +
                    "a=" + a +
                    ", b='" + b + '\'' +
                    ", c=" + c +
                    '}';
        }
    }
}
