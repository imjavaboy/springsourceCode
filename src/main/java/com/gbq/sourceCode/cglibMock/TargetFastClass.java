package com.gbq.sourceCode.cglibMock;


import org.springframework.cglib.core.Signature;

/**
 * @author 郭本琪
 * @description MethodProxy如何避免反射调用方法
 * @date 2022/8/21
 * @Copyright 总有一天，会见到成功
 */
/**
 * 本质尚也是代理类，避免放射调用方法，得到方法签名的编号，根据编号调用原始方法
 * MethodProxy的直接找目标类的
 * */
public class TargetFastClass {
    static Signature s0 = new Signature("save","()V");
    static Signature s1 = new Signature("save","(I)V");
    static Signature s2 = new Signature("save","(J)V");
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
    public Object invoke(int index,Object target,Object[] args){
        if (index == 0){
            ((Target) target).save();
            return null;
        }else if (index == 1){
            ((Target) target).save((int)args[0]);
            return null;
        }else if (index == 2){
          ((Target) target).save((long)args[0]);
            return null;
        }else {
            throw new RuntimeException("无此方法");
        }
    }

    public static void main(String[] args) {
        //第一次运行cglib的方法methodProxyff ,会创建这个对象，根据参数
        //构建Signature对象，并得到index，根据index去进行实际调用
        TargetFastClass fastClass = new TargetFastClass();
        int index = fastClass.getIndex(new Signature("save", "(I)V"));
        System.out.println(index);

        Object invoke = fastClass.invoke(index, new Target(), new Object[]{1});

    }
}
