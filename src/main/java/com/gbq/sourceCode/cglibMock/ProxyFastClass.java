package com.gbq.sourceCode.cglibMock;


import org.springframework.cglib.core.Signature;

/**
 * @author 郭本琪实现形式
 * @description MethodProxy的直接找代理类的
 * @date 2022/8/21
 * @Copyright 总有一天，会见到成功
 */

public class ProxyFastClass {
    static Signature s0 = new Signature("saveSuper","()V");
    static Signature s1 = new Signature("saveSuper","(I)V");
    static Signature s2 = new Signature("saveSuper","(J)V");
    /**
     * 获取目标方法的编号
     *
     * */
    public int getIndex(Signature signature){
        if (s0.equals(signature)){
            return 0;
        }else if (s1.equals(signature)) {
            return 1;
        }else if (s2.equals(signature)){
            return 2;
        }else {
            return -1;
        }
    }
    /**
     * 根据方法编号。正常调用目标对象方法
     * */
    public Object invoke(int index,Object proxy,Object[] args){
        if (index == 0){
            ((Proxy) proxy).saveSuper();
            return null;
        }else if (index == 1){
            ((Proxy) proxy).saveSuper((int)args[0]);
            return null;
        }else if (index == 2){
            ((Proxy) proxy).saveSuper((long)args[0]);
            return null;
        }else {
            throw new RuntimeException("无此方法");
        }
    }

    public static void main(String[] args) {
        //第一次运行cglib的方法methodProxyff ,会创建这个对象，根据参数
        //构建Signature对象，并得到index，根据index去进行实际调用
        ProxyFastClass fastClass = new ProxyFastClass();
        int index = fastClass.getIndex(new Signature("saveSuper", "(I)V"));
        System.out.println(index);

        Object invoke = fastClass.invoke(index, new Proxy(), new Object[]{1});

    }
}
