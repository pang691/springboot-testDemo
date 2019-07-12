package com.taikang.test.annotation.in;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "AnnotaionCheckBean",description = "自定义注解类")
public class AnnotaionCheckBean {
    @ApiModelProperty(value = "名字",required = true,example = "1")
    @AnnotationCustom(classified = "name")
    private String name;
    @ApiModelProperty(value = "名字",required = true,example = "dict")
    @AnnotationCustom(classified = "1")
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "AnnotaionCheckBean{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public void setValue(String value) {
        this.value = value;
    }
}
