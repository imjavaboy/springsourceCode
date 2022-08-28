package com.gbq.sourceCode.a31;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class WebConfig {
    @ControllerAdvice
    static class MyControllerAdvice {
        @ExceptionHandler
        @ResponseBody
        public Map<String, Object> handle(Exception e) {
            Map<String,Object> map = new HashMap<>();
            map.put("error",e.getMessage());
            return map;
        }
    }

    //注入exception的处理器
    @Bean
    public ExceptionHandlerExceptionResolver resolver() {
        ExceptionHandlerExceptionResolver resolver = new ExceptionHandlerExceptionResolver();
        List list = new ArrayList();
        list.add(new MappingJackson2HttpMessageConverter());
        resolver.setMessageConverters(list);
        return resolver;
    }
}
