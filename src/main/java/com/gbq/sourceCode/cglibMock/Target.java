package com.gbq.sourceCode.cglibMock;


/**
 * @author 郭本琪
 * @description
 * @date 2022/8/21
 * @Copyright 总有一天，会见到成功
 */

public class Target {
    public void save(){
        System.out.println("save()");
    }
    public void save(int i){
        System.out.println("save(int)");
    }
    public void save(long j){
        System.out.println("save(long");
    }
}
