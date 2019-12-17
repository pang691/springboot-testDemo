package com.taikang.health.iams.bo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Sets;

import java.util.Set;

public class PermissionModel implements BO {
    private static final long serialVersionUID = 2884458309896109934L;
    private String id;
    private String uri;
    private String module;
    private Set<String> actions;
    private Set<DataAccess> dataAccesses;

    public PermissionModel() {
    }

    public static PermissionModel build() {
        return new PermissionModel();
    }

    public String getId() {
        return this.id;
    }

    public PermissionModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getUri() {
        return this.uri;
    }

    public PermissionModel setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public String getModule() {
        return this.module;
    }

    public PermissionModel setModule(String module) {
        this.module = module;
        return this;
    }

    public Set<String> getActions() {
        return this.actions;
    }

    public void setActions(Set<String> actions) {
        this.actions = actions;
    }

    public Set<DataAccess> getDataAccesses() {
        return this.dataAccesses;
    }

    public void setDataAccesses(Set<DataAccess> dataAccesses) {
        this.dataAccesses = dataAccesses;
    }

    public PermissionModel addAction(String action) {
        if (this.actions == null) {
            this.actions = Sets.newHashSet();
        }

        this.actions.add(action);
        return this;
    }

    public PermissionModel addDataAccess(String dataAccess) {
        if (this.dataAccesses == null) {
            this.dataAccesses = Sets.newHashSet();
        }

        this.dataAccesses.add(DataAccess.fromString(dataAccess));
        return this;
    }

    public String toString() {
        return JSON.toJSONStringWithDateFormat(this, "yyyy-MM-dd HH:mm:ss", new SerializerFeature[0]);
    }
}