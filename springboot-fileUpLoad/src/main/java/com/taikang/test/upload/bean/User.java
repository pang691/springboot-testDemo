package com.taikang.test.upload.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class User implements Serializable {
    @ApiModelProperty(value = "姓名",example = "zhangsan")
    private String name;
    @ApiModelProperty(value = "id",example = "123")
    private Integer id;
//    private MultipartFile[] files;
    private User users;
}
