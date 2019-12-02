package com.taikang.health.iams.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("RoleGet")
public class RoleGet extends BasePO {

    private static final long serialVersionUID = 34733774035L;

    @ApiModelProperty(value = "角色ID", example = "1")
    private String roleId;

    @ApiModelProperty(value = "角色名称", example = "iams")
    private String roleName;

    @ApiModelProperty(value = "角色代码", example = "iams")
    private String roleCode;

    @ApiModelProperty(value = "状态", example = "1")
    private Integer status;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}