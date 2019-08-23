package com.taikang.test.autz.config;

public interface AuthSupplier {
    Authentication getByUserId(String var1);

    Authentication getByUsername(String var1);
}