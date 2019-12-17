package com.taikang.health.iams.supplier;

import com.taikang.health.iams.bo.UserModel;
import com.taikang.health.iams.exception.AuthcException;

import javax.servlet.http.HttpServletRequest;

public interface Authenticator {
    Authentication authenticate(HttpServletRequest var1) throws AuthcException;

    UserModel bindingUser(HttpServletRequest var1) throws AuthcException;

    default Authentication currentQuietly(HttpServletRequest request) {
        try {
            return this.authenticate(request);
        } catch (AuthcException var3) {
            return null;
        }
    }
}