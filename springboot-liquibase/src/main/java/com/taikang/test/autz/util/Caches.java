package com.taikang.test.autz.util;

public enum Caches {
    auth("auth"),
    dbmeta("dbmeta"),
    accessToken("accessToken"),
    dict("dict");

    private String cacheName = null;

    private Caches(String cacheName) {
        this.cacheName = cacheName;
    }

    public String getCacheName() {
        return this.cacheName;
    }
}