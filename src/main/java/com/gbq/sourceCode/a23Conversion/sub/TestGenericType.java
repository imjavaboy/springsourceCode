package com.gbq.sourceCode.a23Conversion.sub;


import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/28
 * @Copyright 总有一天，会见到成功
 */

public class TestGenericType {
    public static void main(String[] args) {
        //获取泛型参数,jdk获取
        Type type = StudentDao.class.getGenericSuperclass();
        System.out.println(type);
        if (type instanceof ParameterizedType){
            System.out.println(Arrays.toString(((ParameterizedType) type).getActualTypeArguments()));
        }

        //spring获取
        Class<?> aClass = GenericTypeResolver.resolveTypeArgument(StudentDao.class, BaseDao.class);
        System.out.println(aClass);


    }
}
