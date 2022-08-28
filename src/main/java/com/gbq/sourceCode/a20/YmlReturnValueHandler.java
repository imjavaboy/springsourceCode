package com.gbq.sourceCode.a20;


import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;


import javax.servlet.http.HttpServletResponse;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/25
 * @Copyright 总有一天，会见到成功
 */

public class YmlReturnValueHandler implements HandlerMethodReturnValueHandler {
    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        //判断有没有加注解
        YML yml = methodParameter.getMethodAnnotation(YML.class);
        return yml != null;
    }

    @Override//返回值
    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest) throws Exception {

//        String str = new Yaml().dump(o);
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        response.setContentType("text/plain;charset=utf-8");
//        response.getWriter().print(str);

        //设置请求处理完毕
        modelAndViewContainer.setRequestHandled(true);

    }
}
