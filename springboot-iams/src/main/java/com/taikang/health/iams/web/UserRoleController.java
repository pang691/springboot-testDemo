package com.taikang.health.iams.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.service.ResponseMessage;

import java.util.ArrayList;

@RestController
@RequestMapping("user_role")
@Api(tags = "用户角色", description = "user_role")
public class UserRoleController extends BaseController {

    @Autowired
    private AutzUserRoleService service;


    @ApiOperation(value = "新增用户角色", notes = "新增用户角色", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = UserRolePost.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, paramType = "header", dataType = "String", value = "access_token", name = "access_token")
    })
    @PostMapping
    public ResponseMessage save(@JsonParam UserRolePost[] in) {
        List<AutzUserRolePO> list = new ArrayList<>();
        AutzUserRolePO po;
        Date currentDate = new Date();
        for (UserRolePost o : in) {
            po = new AutzUserRolePO();
            po.setId(IdHelper.genWorkerId());
            po.setUserId(o.getUserId());
            po.setRoleId(o.getRoleId());
            po.setCreateTime(currentDate);
            po.setUpdateTime(currentDate);
            list.add(po);
        }
        service.insertBatch(list);
        return ResponseMessage.ok();
    }


    @ApiOperation(value = "删除用户角色", notes = "删除用户角色", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, paramType = "header", dataType = "String", value = "access_token", name = "access_token"),
            @ApiImplicitParam(paramType = "query", dataType = "String", value = "主键ID", name = "userRoleId"),
            @ApiImplicitParam(paramType = "query", dataType = "String", value = "用户ID", name = "userId"),
            @ApiImplicitParam(paramType = "query", dataType = "String", value = "角色ID", name = "roleId"),
    })
    @DeleteMapping
    public ResponseMessage deleteClient(String userRoleId, String userId, String roleId) {
        logger.info("删除客户端{}", userRoleId);
        if (!StringUtils.isEmpty(userRoleId)) {
            service.deleteByPk(userRoleId);
        } else {
            if(userId != null && userId!=""){
                AutzUserRolePO po = new AutzUserRolePO();
                po.setRoleId(roleId);
                po.setUserId(userId);
                service.deleteUserRole(po);
            }
        }
        return ResponseMessage.ok();
    }


}