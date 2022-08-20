package com.gbq.sourceCode.day08;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * @Author CandyWall
 * @Date 2022/4/1--1:55
 * @Description
 */
@Slf4j
@Scope("request")
@Component
public class BeanForRequest {
    @PreDestroy
    public void destroy() {
        System.out.println(("destroy"));
    }
}
