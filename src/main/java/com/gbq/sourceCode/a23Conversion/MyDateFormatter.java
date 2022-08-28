package com.gbq.sourceCode.a23Conversion;


import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author 郭本琪
 * @description 日期转换
 * @date 2022/8/28
 * @Copyright 总有一天，会见到成功
 */

public class MyDateFormatter implements Formatter<Date> {
    private final String desc;

    public MyDateFormatter(String desc) {
        this.desc = desc;
    }

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy|MM|dd");

        return dateFormat.parse(text);
    }

    @Override
    public String print(Date object, Locale locale) {
        System.out.println("进入方法"+desc);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy|MM|dd");

        return dateFormat.format(object);
    }
}
