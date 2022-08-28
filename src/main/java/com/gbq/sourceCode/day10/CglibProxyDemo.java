package com.gbq.sourceCode.day10;


import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author 郭本琪
 * @description cglib对象
 * @date 2022/8/20
 * @Copyright 总有一天，会见到成功
 */

public class CglibProxyDemo {
    /**、
     * 动态代理，运行期间扩展java的类和接口。jdk动态代理只能适用于接口
     * cglib动态代理被aop广泛使用，如interception等
     * cglib是动态生成被代理类的子类，该子类重写被代理类的所有不是final修饰的方法，在子类种采用方法拦截的技术拦截弗雷所以方法的调用
     * jdk动态代理只能对接口代理，由于其父类是proxy，而java不支撑多类集成
     * cglib能够代理类和接口，但是被代理的类不难被final修饰，并且接口的方法不难被final修饰
     * jd东动态代理采用了反射技术进行操作，在生成类方面更搞笑
     * cglib动态代理采用了asm框架直接对字节码进行修改，使用了fastclass的特性，执行更搞笑
     *
     * */



    @Test
    public void testCglibProxy1(){
        Target1 target1 = new Target1();
       FUCK fooproxy = (FUCK)  Enhancer.create(Target1.class, (MethodInterceptor) (o, method, objects, methodProxy) -> {

            System.out.println("berfore");
            Object invoke = method.invoke(target1, objects);//用方法反射调用目标
            System.out.println("after");
            return invoke;

        });
        System.out.println(fooproxy.getClass());
        fooproxy.fuck();
    }
    @Test
    public void testCglibProxy2() {
        // 目标对象
        Target1 target1 = new Target1();
        FUCK fooProxy = (FUCK) Enhancer.create(Target1.class, (MethodInterceptor) (obj, method, args, proxy) -> {
            System.out.println("before...");
            // proxy 它可以避免反射调用
            Object result = proxy.invoke(target1, args); // 需要传目标类 （spring用的是这种）
            System.out.println("after...");
            return result;
        });
        System.out.println(fooProxy.getClass());

        fooProxy.fuck();
    }
    @Test
    public void testCglibProxy3() {
        // 目标对象
        FUCK fooProxy = (FUCK) Enhancer.create(Target1.class, (MethodInterceptor) (obj, method, args, proxy) -> {
            System.out.println("before...");
            // proxy 它可以避免反射调用
            Object result = proxy.invokeSuper(obj, args); // 需要传目标类 （spring用的是这种）
            System.out.println("after...");
            return result;
        });
        System.out.println(fooProxy.getClass());

        fooProxy.fuck();
    }
}
interface FUCK{
    void fuck();
}
 class Target1 implements FUCK{
    @Override
    public void fuck() {
        System.out.println("target fuck");
    }
}
