package com.gbq.sourceCode.proxyMock;


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
       invokeHandler.invoke();
    }
}
