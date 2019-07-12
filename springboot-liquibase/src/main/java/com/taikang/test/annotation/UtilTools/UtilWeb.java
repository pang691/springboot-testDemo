package com.taikang.test.annotation.UtilTools;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UtilWeb {

    public static HttpServletRequest currentRequestInSpring(){
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        }catch (Exception e){
            return null;
        }
    }

    public static HttpServletResponse currentResponseInSpring(){
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        }catch (Exception e){
            return null;
        }
    }
}
