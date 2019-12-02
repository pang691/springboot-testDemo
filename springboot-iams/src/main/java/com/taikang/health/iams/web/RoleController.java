package com.taikang.health.iams.web;

import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.service.ResponseMessage;

@RestController
@RequestMapping("role")
@Api(tags = "角色", description = "role")
public class RoleController extends BaseController {

    @Autowired
    private AuthRoleService service;

    @ApiOperation(value = "获取角色", notes = "获取角色", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE,
            response = AutzRolePO.class
    )
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, paramType = "header", dataType = "String", value = "access_token", name = "access_token"),
            @ApiImplicitParam(required = false, paramType = "query", dataType = "String", value = "角色ID", name = "roleId"),
            @ApiImplicitParam(required = false, paramType = "query", dataType = "String", value = "角色名称", name = "roleName"),
            @ApiImplicitParam(required = false, paramType = "query", dataType = "String", value = "角色代码", name = "roleCode"),
            @ApiImplicitParam(required = false, paramType = "query", dataType = "Integer", value = "状态[1:有效;0:无效]", name = "status"),
            @ApiImplicitParam(required = false, paramType = "query", dataType = "Integer", value = "页码", name = "pageNo"),
            @ApiImplicitParam(required = false, paramType = "query", dataType = "Integer", value = "每页数量", name = "pageSize")
    })
    @GetMapping
    public ResponseMessage get(@ApiIgnore RoleGet role) {
        ResponseMessage responseMessage;
        // BY ID
        if (!StringUtils.isEmpty(role.getRoleId())) {
            AutzRolePO rolePo = service.selectByPk(role.getRoleId(), AutzRolePO.class);
            responseMessage = ResponseMessage.ok(rolePo);
        }
        // List OR Page
        else {
            List<AutzRolePO> list = service.select(role, AutzRolePO.class);
            responseMessage = ResponseMessage.ok(role.getPager(), list);
        }
        return responseMessage;
    }


    @ApiOperation(value = "新增角色", notes = "新增角色", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = AutzRolePO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, paramType = "header", dataType = "String", value = "access_token", name = "access_token"),
            @ApiImplicitParam(name = "query", value = "角色", paramType = "body", dataType = "RolePost")
    })
    @ApiResponses(@ApiResponse(code = 200, message = "", response = RolePost.class))
    @PostMapping
    public ResponseMessage save(@JsonParam @ApiIgnore RolePost role) {
        AutzRolePO target = new AutzRolePO();
        BeanUtils.copyProperties(role, target);
        Date currentDate = new Date();
        target.setId(IdHelper.genWorkerId());
        //  有效
        target.setStatus(EnableStatus.ENABLE.value());
        target.setCreateTime(currentDate);
        target.setUpdateTime(currentDate);
        service.insert(target);
        return ResponseMessage.ok(target);
    }

    @ApiOperation(value = "修改角色", notes = "修改角色", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, paramType = "header", dataType = "String", value = "access_token", name = "access_token"),
            @ApiImplicitParam(name = "query", value = "角色", paramType = "body", dataType = "RolePut")
    })
    @ApiResponses(@ApiResponse(code = 200, message = "", response = RolePut.class))
    @PutMapping
    public ResponseMessage update(@JsonParam @ApiIgnore RolePut role) {
        AutzRolePO target = new AutzRolePO();
        BeanUtils.copyProperties(role, target);
        service.updateByPk(target);
        target = service.selectByPk(role.getId(), AutzRolePO.class);
        return ResponseMessage.ok(target);
    }

    @ApiOperation(value = "删除角色", notes = "删除角色", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE,
            response = AutzRolePO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, paramType = "header", dataType = "String", value = "access_token", name = "access_token"),
            @ApiImplicitParam(required = true, paramType = "query", dataType = "String", value = "ID", name = "id")
    })
    @DeleteMapping
    public ResponseMessage delete(String id) {
        service.deleteByPk(id);
        return ResponseMessage.ok();
    }

}