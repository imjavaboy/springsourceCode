package com.gbq.sourceCode.cglibMock;



import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

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

    static MethodProxy save0Proxy;
    static MethodProxy save1Proxy;
    static MethodProxy save2Proxy;

    static {
        try {
            save0 = Target.class.getMethod("save");
            save1 = Target.class.getMethod("save",int.class);
            save2 = Target.class.getMethod("save",long.class);
            save0Proxy = MethodProxy.create(Target.class,Proxy.class,"()V","save","saveSuper");
            save1Proxy = MethodProxy.create(Target.class,Proxy.class,"(I)V","save","saveSuper");
            save2Proxy = MethodProxy.create(Target.class,Proxy.class,"(J)V","save","saveSuper");

        } catch (NoSuchMethodException e) {
           throw new NoSuchMethodError(e.getMessage());
        }

    }
    //原始功能
    public void saveSuper() {
     super.save();
    }
    public void saveSuper(int i) {
        super.save(i);
    }
    public void saveSuper(long i) {
        super.save(i);
    }
    @Override
    public void save() {
        try {
            methodInterceptor.intercept(this,save0,new Object[0],save0Proxy);
        } catch (Throwable throwable) {
           throw new UndeclaredThrowableException(throwable);
        }
    }

    @Override
    public void save(int i) {
        try {
            methodInterceptor.intercept(this,save1,new Object[]{i},save1Proxy);
        } catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    @Override
    public void save(long j) {
        try {
            methodInterceptor.intercept(this,save2,new Object[]{j},save2Proxy);
        } catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }
}
