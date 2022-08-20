package com.gbq.sourceCode.day08;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * @Author CandyWall
 * @Date 2022/4/1--13:01
 * @Description
 */
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class PrototypeBean2 {
}
