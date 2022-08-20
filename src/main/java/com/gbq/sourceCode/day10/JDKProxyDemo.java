package com.gbq.sourceCode.day10;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 郭本琪
 * @description jdk动态代理
 * @date 2022/8/20
 * @Copyright 总有一天，会见到成功
 */

public class JDKProxyDemo {
    /**jdk的动态代理只能对接口代理*/

    /**
     * 动态代理的注意点
     * 1. 用Proxy.newProxyInstance产生代理对象
     * 2. 被代理的对象必须实现接口
     * 3. 内部实现InvocationHandler接口
     * 4. 内部采用asm技术生成字节吗
     * */

    /**
     * Proxy.newProxyInstance的几个参数的含义
     * 1. classLoader，类加载器
     * 2. interfaces；目标对象实现的接口
     * 3. InvocationHandler，真正完成反射代理的处理器
     * 最关键 的步骤就是InvocationHandler种执行目标实现类的方法反射的调用‘
     *
     *
     * */

    /**InvocationHandler三个参数的含义
     * proxy:代表生成的代理对象
     *
     * method:代表目标方法
     *
     * args:代表目标方法的参数
     * */
    public static void main(String[] args) {
        //被代理的类
        Target target = new Target();

        ClassLoader loader = JDKProxyDemo.class.getClassLoader();
        Foo fooProxy  = (Foo) Proxy.newProxyInstance(loader, new Class[]{Foo.class}, (proxy, method, args1) -> {
            System.out.println("before");
            //代理类实现的结果
            Object res = method.invoke(target, args1);
            System.out.println("after");
            return res;
        });
        String foo = fooProxy.foo(1);
        System.out.println("好结果"+foo);
    }

}
interface Foo {
    String foo(int x);
}

class Target implements Foo{
    @Override
    public String foo(int x) {

        System.out.println("target foo");
        return x+"hello";
    }
}
