package com.taikang.health.iams.enums;

public enum LoginCaches {

    /**
     * 每小时次数限制
     */
    LOGIN_ERROR_NUM_PER_HOUR_PER_ID("loginErrorNumPerHourPerId"),
    /**
     * 每天次数限制
     */
    LOGIN_ERROR_NUM_PER_DAY_PER_IP("loginErrorNumPerDayPerIP"),
    /**
     * 锁定用户ID
     */
    USER_ID_LOCKED("userIdLocked"),
    /**
     * 锁定用户IP
     */
    USER_IP_LOCKED("userIPLocked"),
    /**
     * ldap用户
     */
    LDAP_LOGIN_USER("ldapLoginUser");

    private String cacheName = null;

    LoginCaches(String cacheName) {
        this.cacheName = cacheName;
    }

    public String getCacheName() {
        return this.cacheName;
    }
}