package com.taikang.test.autz.util;

public enum LoginCaches {

    /**
     * 每小时次数限制
     */
    LOGINERRORNUMPERHOURPERID("loginErrorNumPerHourPerId"),
    /**
     * 每天次数限制
     */
    LOGINERRORNUMPERDAYPERIP("loginErrorNumPerDayPerIP"),
    /**
     * 锁定用户ID
     */
    USERIDLOCKED("userIdLocked"),
    /**
     * 锁定用户IP
     */
    USERIPLOCKED("userIPLocked");

    private String cacheName = null;

    LoginCaches(String cacheName) {
        this.cacheName = cacheName;
    }

    public String getCacheName() {
        return this.cacheName;
    }
}