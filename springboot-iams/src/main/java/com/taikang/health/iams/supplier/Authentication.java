package com.taikang.health.iams.supplier;

import com.google.common.collect.Sets;
import com.taikang.health.iams.bo.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.*;

public class Authentication implements BO {
    private static final long serialVersionUID = 293289556241308990L;
    private UserModel user;
    private Set<RoleModel> roles;
    private Set<PermissionModel> permissions;
    private Map<String, String> attributes = new HashMap();

    public Authentication() {
    }

    public Optional<RoleModel> getRole(String id) {
        return null == id ? null : this.getRoles().stream().filter((role) -> {
            return role.getId().equals(id);
        }).findAny();
    }

    public Optional<PermissionModel> getPermission(String id) {
        return null == id ? null : this.getPermissions().parallelStream().filter((permission) -> {
            return permission.getId().equals(id);
        }).findAny();
    }

    public DataAccess getDataAccess() {
        if (this.permissions != null && !this.permissions.isEmpty()) {
            Set<DataAccess> accesses = Sets.newHashSet();
            Iterator var2 = this.permissions.iterator();

            while(var2.hasNext()) {
                PermissionModel perm = (PermissionModel)var2.next();
                if (perm.getDataAccesses() == null || perm.getDataAccesses().isEmpty()) {
                    break;
                }

                accesses.addAll(perm.getDataAccesses());
            }

            return !accesses.isEmpty() ? (DataAccess)accesses.iterator().next() : null;
        } else {
            return null;
        }
    }

    public UserModel getUser() {
        return this.user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Set<RoleModel> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<RoleModel> roles) {
        this.roles = roles;
    }

    public Set<PermissionModel> getPermissions() {
        return this.permissions;
    }

    public void setPermissions(Set<PermissionModel> permissions) {
        this.permissions = permissions;
    }

    public <T extends Serializable> Optional<T> fetchAttribute(String name) {
        return (Optional<T>) Optional.of((Serializable)this.attributes.get(name));
    }

    public void addAttribute(String name, String value) {
        this.attributes.put(name, value);
    }

    public String getAttribute(String name) {
        String value = (String)this.attributes.get(name);
        return value;
    }

    public Map<String, String> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}