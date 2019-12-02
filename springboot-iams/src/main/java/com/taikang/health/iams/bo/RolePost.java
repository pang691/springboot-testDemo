package com.taikang.health.iams.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

@ApiModel("RolePost")
public class RolePost implements BO {

    private static final long serialVersionUID = 34733774035L;


    @ApiModelProperty(value = "角色名称", example = "iams", required = true)
    @NotEmpty
    private String roleName;

    @ApiModelProperty(value = "角色代码", example = "iams", required = true)
    @NotEmpty
    private String roleCode;

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
}