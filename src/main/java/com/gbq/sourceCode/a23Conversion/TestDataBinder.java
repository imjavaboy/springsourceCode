package com.gbq.sourceCode.a23Conversion;


import org.springframework.beans.MutablePropertyValues;
import org.springframework.validation.DataBinder;

import java.util.Date;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/28
 * @Copyright 总有一天，会见到成功
 */

public class TestDataBinder {
    public static void main(String[] args) {
        //数据绑定
        MyBean myBean = new MyBean();
        DataBinder dataBinder = new DataBinder(myBean);
        dataBinder.initDirectFieldAccess();
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("a","100");
        propertyValues.add("b","hello");
        propertyValues.add("c","1999/08/09");
        dataBinder.bind(propertyValues);
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
