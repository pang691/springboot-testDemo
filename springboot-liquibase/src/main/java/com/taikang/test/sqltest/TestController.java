package com.taikang.test.sqltest;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sql")
public class TestController {
    @Autowired
    private ITestService testService;
    @GetMapping("/test")
    @ApiOperation(value = "批量更新", notes = "批量更新", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "access_token", value = "请求token", required = false, paramType = "header", dataType = "String"),
            @ApiImplicitParam(name = "hosCode", value = "医院编码", required = false, paramType = "form", dataType = "String")

    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "上传成功！"),
            @ApiResponse(code = 500, message = "上传失败！")
    })
    public String foreachupdate(){
        testService.foreachupdate();
        return "11";
    }
}
