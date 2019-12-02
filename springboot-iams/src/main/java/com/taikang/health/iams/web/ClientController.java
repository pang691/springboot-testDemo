package com.taikang.health.iams.web;

import io.swagger.annotations.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.service.ResponseMessage;

import javax.validation.Valid;

@RestController
@RequestMapping("client")
@Api(tags = "客户端", description = "client")
public class ClientController extends BaseController {

    @Autowired
    private OAuth2ClientService service;

    @ApiOperation(value = "获取客户端", notes = "获取客户端", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE,
            response = OAuth2Client.class
    )
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, paramType = "header", dataType = "String", value = "access_token", name = "access_token"),
            @ApiImplicitParam(paramType = "query", dataType = "String", value = "客户端ID", name = "clientId"),
            @ApiImplicitParam(paramType = "query", dataType = "String", value = "名称", name = "name"),
            @ApiImplicitParam(paramType = "query", dataType = "Integer", value = "类型[1:用户;2:API]", name = "clientType"),
            @ApiImplicitParam(paramType = "query", dataType = "Integer", value = "状态[1:有效;0:无效]", name = "status"),
            @ApiImplicitParam(paramType = "query", dataType = "Integer", value = "页码", name = "pageNo"),
            @ApiImplicitParam(paramType = "query", dataType = "Integer", value = "每页数量", name = "pageSize")
    })
    @GetMapping
    public ResponseMessage getClient(@ApiIgnore ClientGet client) {
        List<OAuth2Client> clients = service.select(client, OAuth2Client.class);
        return ResponseMessage.ok(client.getPager(), clients);
    }


    @ApiOperation(value = "新增客户端", notes = "新增客户端", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = OAuth2Client.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, paramType = "header", dataType = "String", value = "access_token", name = "access_token"),
            @ApiImplicitParam(name = "query", value = "客户端信息", paramType = "body", dataType = "ClientPost")
    })
    @ApiResponses(@ApiResponse(code = 200, message = "", response = ClientPost.class))
    @PostMapping
    public ResponseMessage save(@Valid @ApiIgnore ClientPost client) {
        OAuth2Client target = new OAuth2Client();
        BeanUtils.copyProperties(client, target);
        String id = client.getId();
        if (StringUtils.isEmpty(id)) {
            id = IdHelper.genWorkerId();
        }
        target.setId(id);
        target.setSecret(IdHelper.genUuid());
        service.insert(target);
        return ResponseMessage.ok(target);
    }

    @ApiOperation(value = "修改客户端", notes = "修改客户端", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE,
            response = OAuth2Client.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, paramType = "header", dataType = "String", value = "access_token", name = "access_token"),
            @ApiImplicitParam(name = "query", value = "客户端信息", paramType = "body", dataType = "ClientPut")
    })
    @PutMapping
    public ResponseMessage update(@Valid @ApiIgnore ClientPut client) {
        OAuth2Client target = new OAuth2Client();
        BeanUtils.copyProperties(client, target);
        String id = client.getId();
        target.setId(id);
        service.updateByPk(target);
        target = service.selectByPk(id, OAuth2Client.class);
        return ResponseMessage.ok(target);
    }

    @ApiOperation(value = "更新客户端密码", notes = "更新客户端密码", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE,
            response = OAuth2Client.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, paramType = "header", dataType = "String", value = "access_token", name = "access_token"),
            @ApiImplicitParam(required = true, paramType = "query", dataType = "String", value = "客户端ID", name = "clientId"),
    })
    @PutMapping("/secret")
    public ResponseMessage update(@Valid String clientId) {
        OAuth2Client client = new OAuth2Client();
        client.setId(clientId);
        client.setSecret(IdHelper.genUuid());
        service.updateIncludesByPk(clientId, client, "secret");
        client = service.selectByPk(clientId, OAuth2Client.class);
        return ResponseMessage.ok(client);
    }

    @ApiOperation(value = "删除客户端", notes = "删除客户端", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, paramType = "header", dataType = "String", value = "access_token", name = "access_token"),
            @ApiImplicitParam(required = true, paramType = "query", dataType = "String", value = "客户端ID", name = "clientId")
    })
    @DeleteMapping
    public ResponseMessage deleteClient(@NotEmpty String clientId) {
        logger.info("删除客户端{}", clientId);
        service.deleteByPk(clientId);
        return ResponseMessage.ok();
    }


}