package com.taikang.test.AopLogger;

import com.sun.xml.internal.ws.util.UtilException;
import com.taikang.test.AopLogger.utilTool.UtilAop;
import com.taikang.test.AopLogger.utilTool.UtilClass;
import com.taikang.test.AopLogger.utilTool.UtilDate;
import com.taikang.test.annotation.UtilTools.UtilWeb;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.service.ResponseMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Aspect
@Component
public class AopAccessLoggerAspect {

    @Around(value = "@annotation(com.taikang.test.AopLogger.AccessLogger)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result =null;
        LoggerInfo loggerInfo = resolver(joinPoint);
        //执行开始
        long   requestMills = System.currentTimeMillis();
        String requestTime  = requestMills+"";
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            if(loggerInfo!=null){
                if ( !(e instanceof Exception) ) {

                    loggerInfo.setExceptionInfo(e.getMessage());
                }
            }
            throw e;
        } finally {
            if(loggerInfo!=null){
                //执行结束
                long   responseMills = System.currentTimeMillis();
                String responseTime = responseMills + "";
                loggerInfo.setRequestTime(requestTime);
                loggerInfo.setResponseTime(responseTime);
                loggerInfo.setUseTime(responseMills-requestMills);

                //日志暂时不记录返回报文内容，有可能很大
                loggerInfo.setResponseContent("fastJsonHttpMessageConverter.toJSONString(result)");
                //只记录返回状态
                if (result instanceof ResponseMessage){
                    ResponseMessage response = (ResponseMessage) result;
                    loggerInfo.setResponseCode(String.valueOf(response.getCode()));
                        loggerInfo.setExceptionInfo(response.getMessage());
                }

                System.out.println(loggerInfo.toString());
            }
        }
        return result;
    }

    public LoggerInfo resolver(ProceedingJoinPoint pjp){
        LoggerInfo logInfo = new LoggerInfo();
        HttpServletRequest request = UtilWeb.currentRequestInSpring();
        String uri = request.getRequestURI();
//        if(excludes!=null){
//            for(String excludeuri : excludes){
//                if(UtilString.startsWith(uri , excludeuri)){
//                    return null;
//                }
//            }
//        }

        Class<?> target = pjp.getTarget().getClass();
        //构建描述字段
        StringBuilder describe = new StringBuilder();
        //方法签名
        MethodSignature methodSignature = ((MethodSignature) pjp.getSignature());
        Method method = methodSignature.getMethod();
        String methodName = UtilAop.getMethodName(pjp);
        //类和方法注解
        AccessLogger classAnnotation = UtilClass.getAnnotation(target, AccessLogger.class);
        AccessLogger methodAnnotation = UtilClass.getAnnotation(method, AccessLogger.class);
        if (classAnnotation != null) {
            describe.append(classAnnotation.value());
        }
        if (methodAnnotation != null) {
            if (classAnnotation != null)
                describe.append("-");
            describe.append(methodAnnotation.value());
        }
        //构建方法参数字段
        LinkedHashMap<String, Object> param = new LinkedHashMap<>();
        String[] paramNames = methodSignature.getParameterNames();
        Object[] args = pjp.getArgs();
        for (int i = 0; i < paramNames.length; i++) {
            Object arg = args[i];
            String argString;
            if (arg instanceof HttpServletRequest
                    || arg instanceof HttpSession
                    || arg instanceof HttpServletResponse
                    || arg instanceof MultipartFile
                    || arg instanceof MultipartFile[]
                    //权限对象也不记录
                    || arg instanceof Authentication) {
                continue;
            }
            if (arg instanceof String) {
                argString = (String) arg;
            }
            else if (arg instanceof Number) {
                argString = String.valueOf(arg);
            }
            else if (arg instanceof Date) {
                argString = null;
            }
            else {
                try {
                    //argString = JsonHelper.toJsonString(arg);
                    argString = "";
                } catch (Exception e) {
                    continue;
                }
            }
            param.put(paramNames[i], argString);
        }
        //构造PO对象
        Map<String, String> header = UtilWeb.getHeaders(request);
        logInfo.setId("1");
        logInfo.setModuleDesc(describe.toString());//方法描述
        logInfo.setClassName(target.getName());//当前访问映射到的类名
        logInfo.setClientIp("127.0.0.1");//ip地址
        logInfo.setRequestMethod(request.getMethod().concat(".").concat(methodName));//方法：GET.includes()
        logInfo.setRequestHeader("JsonHelper.toJsonString(header)");//http请求头
        String authorization = request.getHeader("Authorization");
        if(StringUtils.isNotEmpty(authorization)){
            logInfo.setRequestHeader("Authorization:"+authorization);//http授权头
        }
        logInfo.setReferer(header.get("referer"));//referer
        logInfo.setRequestUri(request.getRequestURI());//请求相对路径
        logInfo.setRequestUrl(UtilWeb.getBasePath(request).concat(logInfo.getRequestUri().substring(1)));//请求绝对路径
        logInfo.setUserAgent(header.get("user-agent"));//客户端标识
        logInfo.setRequestParam("JsonHelper.toJsonString(param)");//请求参数
        return logInfo;
    }
}
