package com.gbq.sourceCode.a23Conversion;


import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.DirectFieldAccessor;

import java.util.Date;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/28
 * @Copyright 总有一天，会见到成功
 */

public class TestFieldAccessor {
    public static void main(String[] args) {
        //利用反射的原理，获取bean的属性并赋
      MyBean myBean = new MyBean();
        DirectFieldAccessor accessor = new DirectFieldAccessor(myBean);
        accessor.setPropertyValue("a","10");
        accessor.setPropertyValue("b","helllllllo");
        accessor.setPropertyValue("c","1999/08/03");
        System.out.println(myBean);
    }
    static class MyBean {
        private int a;
        private String b;
        private Date c;
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
