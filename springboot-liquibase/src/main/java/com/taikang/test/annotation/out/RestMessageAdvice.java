package com.taikang.test.annotation.out;

import com.taikang.test.annotation.UtilTools.UtilWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@Order(1)
public class RestMessageAdvice implements ResponseBodyAdvice<Object> {

    @Autowired
    private Dict2Names dict2Names;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.hasMethodAnnotation(RestMessage.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {

        RestMessage restMessage = returnType.getMethodAnnotation(RestMessage.class);
        HttpServletRequest nativeRequest = UtilWeb.currentRequestInSpring();
        HttpServletResponse nativeResponse = UtilWeb.currentResponseInSpring();

        RestMessageBean restMessageBean = (RestMessageBean)body;
        if(null != restMessageBean){
            dict2Names.code2Name(restMessageBean);
        }

        return restMessageBean;
    }
}
