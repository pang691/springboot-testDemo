package com.taikang.test.autz.config;

import com.taikang.test.autz.bo.UserModel;

public class AnonymousUserModel extends UserModel {
    private static final long serialVersionUID = -5038987870410749646L;
    public static String anonymous = "anonymous";

    public AnonymousUserModel() {
        this(anonymous, anonymous, "", anonymous);
    }

    private AnonymousUserModel(String id, String username, String password, String name) {
        super(id, username, password, name);
    }
}