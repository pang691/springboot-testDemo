package com.taikang.health.iams.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

@ApiModel("RolePut")
public class RolePut implements BO {

    private static final long serialVersionUID = 34733774035L;

    @ApiModelProperty(value = "ID", example = "1", required = true)
    @NotEmpty
    private String id;

    @ApiModelProperty(value = "角色名称", example = "iams", required = true)
    @NotEmpty
    private String roleName;

    @ApiModelProperty(value = "角色代码", example = "iams", required = true)
    @NotEmpty
    private String roleCode;

    @ApiModelProperty(value = "状态[1:有效;0:无效]", example = "1", required = true)
    @NotEmpty
    private int status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}