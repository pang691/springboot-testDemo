package com.taikang.test.annotation;

import com.taikang.test.annotation.in.AnnotaionCheckBean;
import com.taikang.test.annotation.out.RestMessage;
import com.taikang.test.annotation.out.RestMessageBean;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api(tags = "自定义注解")
public class AnnotationController {
    /**
     * 通过注解前端数据校验
     */
    @PostMapping("/annotaion/in")
//    @ApiOperation(value = "自定义注解校验",notes = "自定义注解校验",produces = MediaType.APPLICATION_JSON_VALUE,
//    response = String.class,httpMethod = "POST")
    public String check(@Validated AnnotaionCheckBean annotaionCheckBean){
        System.out.println(annotaionCheckBean.toString());
        return "success";
    }
    @RestMessage
    @GetMapping("/annotaion/out")
//    @ApiOperation(value = "自定义注解校验",notes = "自定义注解校验",produces = MediaType.APPLICATION_JSON_VALUE,
//    response = String.class,httpMethod = "POST")
    public RestMessageBean check(){
        RestMessageBean restMessageBean = new RestMessageBean();
        restMessageBean.setItemName("1");
        return restMessageBean;
    }

}
