package com.taikang.test.importExcel;

import com.taikang.test.importExcel.bo.UserDto;
import com.taikang.test.importExcel.util.ExcelUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@RestController
@Api(tags = "导入Excel文件")
@Slf4j
public class InportExcelController {
    @Autowired
    private ExcelUtil excelUtil;
    @PostMapping(value = "/uploadExcel", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    @ApiOperation(value = "用户信息Excel导入数据", notes = "用户信息Excel导入数据", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "上传成功！"),
            @ApiResponse(code = 500, message = "上传失败！")
    })
    public String uploadExcel(@ApiParam(value = "用户信息Excel导入数据", required = true)MultipartFile file) throws Exception {
        List<UserDto> dtoList = excelUtil.readExcelFileToDTO(file, UserDto.class);
        log.info("长度："+dtoList.size());
        return "导入成功";
    }
}
