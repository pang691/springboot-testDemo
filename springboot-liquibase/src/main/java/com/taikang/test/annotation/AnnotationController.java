package com.taikang.test.annotation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "自定义注解")
public class AnnotationController {

    @PostMapping("/annotaion")
//    @ApiOperation(value = "自定义注解校验",notes = "自定义注解校验",produces = MediaType.APPLICATION_JSON_VALUE,
//    response = String.class,httpMethod = "POST")
    public String check(@Validated AnnotaionCheckBean annotaionCheckBean){
        System.out.println(annotaionCheckBean.toString());
        return "success";
    }
}
