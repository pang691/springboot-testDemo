package com.taikang.test.upload.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class User {
    @ApiModelProperty(value = "姓名",example = "zhangsan")
    private String name;
    @ApiModelProperty(value = "id",example = "123")
    private Integer id;
}
