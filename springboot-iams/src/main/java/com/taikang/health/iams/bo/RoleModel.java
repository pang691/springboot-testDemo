package com.taikang.health.iams.bo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class RoleModel implements BO {
    private static final long serialVersionUID = 243532130057399490L;
    private String id;
    private String name;

    public RoleModel() {
    }

    public static RoleModel build() {
        return new RoleModel();
    }

    public RoleModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public RoleModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public RoleModel setName(String name) {
        this.name = name;
        return this;
    }

    public String toString() {
        return JSON.toJSONStringWithDateFormat(this, "yyyy-MM-dd HH:mm:ss", new SerializerFeature[0]);
    }
}