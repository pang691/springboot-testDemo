package com.taikang.health.iams.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

@ApiModel("UserRolePost")
public class UserRolePost implements BO {

    private static final long serialVersionUID = 34733774035L;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", example = "1")
    @NotEmpty
    private String userId;
    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID", example = "2")
    @NotEmpty
    private String roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}