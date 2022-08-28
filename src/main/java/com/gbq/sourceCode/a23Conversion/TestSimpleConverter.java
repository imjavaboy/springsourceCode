package com.gbq.sourceCode.a23Conversion;


import org.springframework.beans.SimpleTypeConverter;

import java.util.Date;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/28
 * @Copyright 总有一天，会见到成功
 */

public class TestSimpleConverter {
    public static void main(String[] args) {
        //只具备类型转化 的功能
        SimpleTypeConverter typeConverter = new SimpleTypeConverter();
        Integer integer = typeConverter.convertIfNecessary("13", Integer.class);
        Date date = typeConverter.convertIfNecessary("1999/08/05", Date.class);
        System.out.println(integer);
        System.out.println(date);
    }
}
