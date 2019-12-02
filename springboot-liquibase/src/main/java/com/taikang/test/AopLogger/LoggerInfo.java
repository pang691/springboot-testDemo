package com.taikang.test.AopLogger;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class LoggerInfo {
    private String id;
    private String clientIp;
    private String requestUri;
    private String requestUrl;
    private String requestMethod;
    private String referer;
    private String userAgent;
    private String requestHeader;
    private String responseCode;
    private String responseContent;
    private String userId;
    private String className;
    private String moduleDesc;
    private String requestParam;
    private String exceptionInfo;
    private String requestTime;
    private String responseTime;
    private long useTime = 1L;
    private Map<String, String> attributes = new HashMap();
}
