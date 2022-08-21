package com.gbq.sourceCode.cglibMock;


import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/21
 * @Copyright 总有一天，会见到成功
 */

public class A14 {
    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        Target target = new Target();
        proxy.setMethodInterceptor(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("before");
//              return    method.invoke(target,objects);//反射调用

//                return methodProxy.invoke(target,objects);//内部无反射，结合目标类使用

                return methodProxy.invokeSuper(o,objects);//内部无反射，结合代理使用
            }
        });
        proxy.save();
        proxy.save(1);
        proxy.save(2L);
    }
}
