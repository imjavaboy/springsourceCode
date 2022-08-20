package com.gbq.sourceCode.proxyMock;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/20
 * @Copyright 总有一天，会见到成功
 */

public class A13 {
    /**
     * 有两个方法实现的动态代理，需要去判断那个方法的调用
     * */
    interface FOO1{
        void foo();
       int bar();
    }
    static class Target implements FOO1{
        @Override
        public void foo() {
            System.out.println("foo方法");
        }

        @Override
        public int bar() {
            System.out.println("bar方法");
            return 1;
        }
    }
    interface invokeHandler{
        Object invoke(Object proxy,Method method, Object[] args) throws InvocationTargetException, IllegalAccessException;
    }

    public static void main(String[] args) {
        $procy0 proxy = new $procy0(new invokeHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
                System.out.println("before");
                return method.invoke(new Target(),args);
            }
        });
        proxy.foo();
        proxy.bar();

    }
}
