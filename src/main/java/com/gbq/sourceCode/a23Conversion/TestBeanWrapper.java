package com.gbq.sourceCode.a23Conversion;



import org.springframework.beans.BeanWrapperImpl;

import java.util.Date;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/28
 * @Copyright 总有一天，会见到成功
 */

public class TestBeanWrapper {
    public static void main(String[] args) {
        //利用反射的原理，获取bean的属性并赋值/本质上用到set方法
        MybBean mybBean = new MybBean();
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(mybBean);
        beanWrapper.setPropertyValue("a","10");
        beanWrapper.setPropertyValue("b","helllllllo");
        beanWrapper.setPropertyValue("c","1999/08/03");
        System.out.println(mybBean);

    }
    static class MybBean{
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

        @Override
        public String toString() {
            return "MybBean{" +
                    "a=" + a +
                    ", b='" + b + '\'' +
                    ", c=" + c +
                    '}';
        }

        public void setC(Date c) {
            this.c = c;
        }
    }
}
