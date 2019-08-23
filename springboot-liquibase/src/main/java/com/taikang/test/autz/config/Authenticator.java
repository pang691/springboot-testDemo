package com.taikang.test.autz.config;

import com.taikang.test.autz.bo.UserModel;

import javax.servlet.http.HttpServletRequest;

public interface Authenticator {
    Authentication authenticate(HttpServletRequest var1) throws Exception;

    UserModel bindingUser(HttpServletRequest var1) throws Exception;

    default Authentication currentQuietly(HttpServletRequest request) {
        try {
            return this.authenticate(request);
        } catch (Exception var3) {
            return null;
        }
    }
}