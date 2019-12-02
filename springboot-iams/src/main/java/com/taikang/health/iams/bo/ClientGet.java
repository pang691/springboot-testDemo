package com.taikang.health.iams.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Client")
public class ClientGet implements BO {

    private static final long serialVersionUID = 34733774035L;

    /**
     * 客户端名称
     */
    @ApiModelProperty(value = "客户端ID", example = "1")
    private String id;
    /**
     * 客户端名称
     */
    @ApiModelProperty(value = "客户端名称", example = "iams")
    private String name;

    /**状态 */
    @ApiModelProperty(value = "状态[1:有效;0:无效]", example = "1")
    private int status;

    /**客户端类型  */
    @ApiModelProperty(value = "类型[1:用户;2:API]", example = "1")
    private int clientType;


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
}