package com.taikang.health.iams.util;

import com.taikang.health.iams.enums.LoginCaches;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class LoginCacheHolder {

    private static final Logger logger = LoggerFactory.getLogger(TokenCacheHolder.class);

    private static String loginErrorNumPerHourPerID;
    private static String loginErrorNumPerDayPerIP;


    @Value("${login.loginErrorNumPerDayPerIP}")
    private String loginErrorNumPerDayPerIp;

    @Value("${login.loginErrorNumPerHourPerId}")
    private String loginErrorNumPerHourPerId;



    @PostConstruct
    private void init() {
        logger.info("LoginCacheHolder init:每个ID每小时错误次数={},每个IP每天错误次数={}"
                , loginErrorNumPerHourPerId, loginErrorNumPerDayPerIp);
        LoginCacheHolder.loginErrorNumPerHourPerID = loginErrorNumPerHourPerId;
        LoginCacheHolder.loginErrorNumPerDayPerIP = loginErrorNumPerDayPerIp;
    }

    /**
     * 存放Ldap用户信息
     */
    public static void setLdapLoginUser(String username,String password) {
        Cache cache = CacheManagerHolder.getManager()
                .getCache(LoginCaches.LDAP_LOGIN_USER.getCacheName());
        cache.put(username, password);
    }
    /**
     * 获取Ldap用户信息
     */
    public static String getLdapLoginUser(String username) {
        Cache cache = CacheManagerHolder.getManager()
                .getCache(LoginCaches.LDAP_LOGIN_USER.getCacheName());
        return cache.get(username, String.class);
    }

    /**
     * 存放用户ID登录错误的次数
     */
    public static void setLoginErrorNumPerHourPerId(String userId) {
        Cache cacheOfLoginErrorNumPerHour = CacheManagerHolder.getManager()
                .getCache(LoginCaches.LOGIN_ERROR_NUM_PER_HOUR_PER_ID.getCacheName());
        Integer cacheVal = cacheOfLoginErrorNumPerHour.get(userId, Integer.class);
        int num = cacheVal == null ? 0 : cacheVal;
        num += 1;
        if (num >= Integer.decode(loginErrorNumPerHourPerID)) {
            Cache cacheOfUserIdLocked = CacheManagerHolder.getManager()
                    .getCache(LoginCaches.USER_ID_LOCKED.getCacheName());
            cacheOfUserIdLocked.put(userId, true);
        }
        cacheOfLoginErrorNumPerHour.put(userId, num);
    }
    /**
     * 获取是否用户ID锁定
     */
    public static boolean getUserIdLocked(String userId) {
        Cache cacheOfUserIdLocked = CacheManagerHolder.getManager()
                .getCache(LoginCaches.USER_ID_LOCKED.getCacheName());
        Cache cacheOfLoginErrorNumPerHour = CacheManagerHolder.getManager()
                .getCache(LoginCaches.LOGIN_ERROR_NUM_PER_HOUR_PER_ID.getCacheName());
        return getLocked(userId, cacheOfUserIdLocked, cacheOfLoginErrorNumPerHour, loginErrorNumPerHourPerID);
    }
    /**
     * 存放用户IP登录错误的次数
     */
    public static void setLoginErrorNumPerDayPerIp(String userIp) {
        Cache cache = CacheManagerHolder.getManager()
                .getCache(LoginCaches.LOGIN_ERROR_NUM_PER_DAY_PER_IP.getCacheName());
        Integer cacheVal = cache.get(userIp, Integer.class);
        int num = cacheVal == null ? 0 : cacheVal;
        num += 1;
        if (num >= Integer.decode(loginErrorNumPerDayPerIP)) {
            Cache cacheOfUserIdLocked = CacheManagerHolder.getManager()
                    .getCache(LoginCaches.USER_IP_LOCKED.getCacheName());
            cacheOfUserIdLocked.put(userIp, true);
        }
        cache.put(userIp, num);
    }
    /**
     * 获取是否用户IP锁定
     */
    public static boolean getUserIpLocked(String userIp) {
        Cache cacheOfUserIpLocked = CacheManagerHolder.getManager()
                .getCache(LoginCaches.USER_IP_LOCKED.getCacheName());
        Cache cacheOfLoginErrorNumPerHour = CacheManagerHolder.getManager()
                .getCache(LoginCaches.LOGIN_ERROR_NUM_PER_DAY_PER_IP.getCacheName());
        return getLocked(userIp, cacheOfUserIpLocked, cacheOfLoginErrorNumPerHour, loginErrorNumPerDayPerIP);
    }

    private static boolean getLocked(String userId, Cache cacheOfUserIdLocked, Cache cacheOfLoginErrorNumPerHour, String loginErrorNum) {
        Integer num = cacheOfLoginErrorNumPerHour.get(userId, Integer.class);
        boolean userIdLocked = (num != null && num >= Integer.decode(loginErrorNum));
        cacheOfUserIdLocked.put(userId, userIdLocked);
        return userIdLocked;
    }


}