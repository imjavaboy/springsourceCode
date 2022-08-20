package com.gbq.sourceCode.proxyMock;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * @author 郭本琪
 * @description 动态代理的实现
 * @date 2022/8/20
 * @Copyright 总有一天，会见到成功
 */

public class  $procy0 implements A13.FOO1 {

    static Method foo;
    static Method bar;

    static {
        try {
            foo = A13.FOO1.class.getDeclaredMethod("foo");
            bar = A13.FOO1.class.getDeclaredMethod("bar");
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodError(e.getMessage());
        }
    }
    private A13.invokeHandler invokeHandler;

    public $procy0(A13.invokeHandler invokeHandler) {
        this.invokeHandler = invokeHandler;
    }


    @Override
    public void foo() {
        //抽离出去
        try {
            invokeHandler.invoke(this,foo,new Object[0]);
        } catch (RuntimeException e){
            throw e;
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    @Override
    public int bar() {
        try {

           return (int)  invokeHandler.invoke(this,bar,new Object[0]);
        }catch (RuntimeException e){
            throw e;
        } catch (Throwable e) {
           throw new UndeclaredThrowableException(e);
        }
    }

}
