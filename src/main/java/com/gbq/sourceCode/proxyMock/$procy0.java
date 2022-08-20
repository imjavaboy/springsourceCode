package com.gbq.sourceCode.proxyMock;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 郭本琪
 * @description 动态代理的实现
 * @date 2022/8/20
 * @Copyright 总有一天，会见到成功
 */

public class  $procy0 implements A13.FOO1 {
    private A13.invokeHandler invokeHandler;

    public $procy0(A13.invokeHandler invokeHandler) {
        this.invokeHandler = invokeHandler;
    }

    @Override
    public void foo() {
        //抽离出去
        try {
            Method foo = A13.FOO1.class.getMethod("foo");
            invokeHandler.invoke(foo,new Object[0]);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void bar() {
        try {
            Method foo = A13.FOO1.class.getMethod("bar");
            invokeHandler.invoke(foo,new Object[0]);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
