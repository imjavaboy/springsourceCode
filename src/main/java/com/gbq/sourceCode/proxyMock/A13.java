package com.gbq.sourceCode.proxyMock;


/**
 * @author 郭本琪
 * @description
 * @date 2022/8/20
 * @Copyright 总有一天，会见到成功
 */

public class A13 {
    interface FOO1{
        void foo();
    }
    static class Target implements FOO1{
        @Override
        public void foo() {
            System.out.println("你好");
        }
    }
    interface invokeHandler{
        void invoke();
    }

    public static void main(String[] args) {
        $procy0 proxy = new $procy0(new invokeHandler() {
            @Override
            public void invoke() {
                System.out.println("before");
                new Target().foo();
                System.out.println("afetr");

            }
        });
        proxy.foo();

    }
}
