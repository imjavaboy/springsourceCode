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
        void bar();
    }
    static class Target implements FOO1{
        @Override
        public void foo() {
            System.out.println("foo方法");
        }

        @Override
        public void bar() {
            System.out.println("bar方法");
        }
    }
    interface invokeHandler{
        void invoke(Method method, Object[] args) throws InvocationTargetException, IllegalAccessException;
    }

    public static void main(String[] args) {
        $procy0 proxy = new $procy0(new invokeHandler() {
            @Override
            public void invoke(Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
                System.out.println("before");
              method.invoke(new Target(),args);
                System.out.println("afetr");
            }
        });
        proxy.foo();
        proxy.bar();

    }
}
