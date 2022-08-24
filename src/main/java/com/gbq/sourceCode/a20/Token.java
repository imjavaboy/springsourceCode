package com.gbq.sourceCode.a20;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 郭本琪
 * @description 功能。标注了注解的信息，可以讲token信息获取道
 * @date 2022/8/24
 * @Copyright 总有一天，会见到成功
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {
}
