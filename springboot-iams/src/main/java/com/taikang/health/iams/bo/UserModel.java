package com.taikang.health.iams.bo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class UserModel implements BO {
    private static final long serialVersionUID = 8275078394534198675L;
    private String id;
    private String username;
    private String password;
    private String name;

    public static UserModel build() {
        return new UserModel();
    }

    public UserModel() {
    }

    public UserModel(String id, String username, String password, String name) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public UserModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return this.username;
    }

    public UserModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return this.password;
    }

    public UserModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public UserModel setName(String name) {
        this.name = name;
        return this;
    }

    public String toString() {
        return JSON.toJSONStringWithDateFormat(this, "yyyy-MM-dd HH:mm:ss", new SerializerFeature[0]);
    }
}