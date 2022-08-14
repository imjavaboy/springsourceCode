package com.gbq.sourceCode.day05.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

/**
 * @Author CandyWall
 * @Date 2022/3/29--12:42
 * @Description
 */
@Controller
@Slf4j
public class Bean3 {
    public Bean3() {
        System.out.println("我被 Spring 管理啦");
    }
}
