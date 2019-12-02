package com.taikang.health.iams.bo;

import io.swagger.annotations.ApiModel;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Set;

@ApiModel("Authentication")
public class AuthenticationOut implements BO {

    private static final long serialVersionUID = 293289556241308990L;

    private UserModel user;
    private Set<RoleModel> roles;



    public AuthenticationOut() {
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




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}