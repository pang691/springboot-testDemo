package com.taikang.health.iams.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

@ApiModel("ClientPut")
public class ClientPut implements BO {

    private static final long serialVersionUID = 34733774035L;

    @ApiModelProperty(value = "客户端ID", example = "1")
    @NotEmpty
    private String id;

    /**
     * 客户端名称
     */
    @ApiModelProperty(value = "客户端名称", example = "iams", required = true)
    @NotEmpty
    private String name;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态[1:有效;0:无效]", example = "1", required = true)
    @NotEmpty
    private int status;

    /**
     * 客户端类型
     */
    @ApiModelProperty(value = "类型[1:用户;2:API]", example = "1", required = true)
    @NotEmpty
    private int clientType;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", example = "iams")
    private String remark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getClientType() {
        return clientType;
    }

    public void setClientType(int clientType) {
        this.clientType = clientType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}