package com.taikang.test.importExcel.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserDto {
    @ApiModelProperty(value = "编号")
    private String id;

    @ApiModelProperty(value = "用户名称")
    private String name;

    @ApiModelProperty(value = "用户年龄")
    private Integer age;
}
