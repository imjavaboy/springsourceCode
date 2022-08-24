package com.gbq.sourceCode.a20;


import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author 郭本琪
 * @description 自定义解析器
 * @date 2022/8/24
 * @Copyright 总有一天，会见到成功
 */

public class TokenArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    //是否支持某个参数
    public boolean supportsParameter(MethodParameter methodParameter) {
        Token token = methodParameter.getParameterAnnotation(Token.class);

        return token != null;
    }

    @Override
    //解析过程
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {

        return nativeWebRequest.getHeader("token");
    }
}
