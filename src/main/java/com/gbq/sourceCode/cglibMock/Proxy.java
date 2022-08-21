package com.gbq.sourceCode.cglibMock;



import org.springframework.cglib.proxy.MethodInterceptor;

import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * @author 郭本琪
 * @description 模拟cglib的底层实现
 * @date 2022/8/21
 * @Copyright 总有一天，会见到成功
 */

public class Proxy extends Target {
    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    private MethodInterceptor methodInterceptor;

    static Method save0;
    static Method save1;
    static Method save2;

    static {
        try {
            save0 = Target.class.getMethod("save");
            save1 = Target.class.getMethod("save",int.class);
            save2 = Target.class.getMethod("save",long.class);
        } catch (NoSuchMethodException e) {
           throw new NoSuchMethodError(e.getMessage());
        }

    }
    @Override
    public void save() {
        try {
            methodInterceptor.intercept(this,save0,new Object[0],null);
        } catch (Throwable throwable) {
           throw new UndeclaredThrowableException(throwable);
        }
    }

    @Override
    public void save(int i) {
        try {
            methodInterceptor.intercept(this,save1,new Object[]{i},null);
        } catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    @Override
    public void save(long j) {
        try {
            methodInterceptor.intercept(this,save2,new Object[]{j},null);
        } catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }
}
