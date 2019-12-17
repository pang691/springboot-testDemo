//package com.taikang.health.iams.web;
//
//import com.taikang.health.iams.po.AutzUserPO;
//import com.taikang.health.iams.service.AuthUserService;
//import com.taikang.xboot.accesslog.annotation.AccessLogger;
//import com.taikang.xboot.sdk.ResponseMessage;
//import com.taikang.xboot.web.BaseController;
//import com.taikang.xboot.web.resolver.JsonParam;
//import io.swagger.annotations.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//import springfox.documentation.annotations.ApiIgnore;
//
//import javax.validation.Valid;
//import java.util.Arrays;
//import java.util.List;
//
//
//@RestController
//@RequestMapping("authuser/authuser")
//@Api(value = "AuthUserController", description = "authuser", tags = "authuser/authuser")
//public class AuthUserController  {
//    private static Logger logger = LoggerFactory.getLogger(AuthUserController.class);
//
//    @Autowired
//    private AuthUserService authUserService;
//
//    /**
//     * 功能描述:给用户分配角色，插入auth_user表数据
//     *
//     * @Param: SpaceRoleUserBO
//     * @Return:com.taikang.xboot.sdk.ResponseMessage
//     * @Author:itw_dubin
//     * @Create:2018/11/27
//     * @Modify:
//     */
//    @ApiOperation(value = "用户分配角色，插入auth_user表数据", notes = "用户分配角色，插入auth_user表数据", httpMethod = "POST",
//            produces = MediaType.APPLICATION_JSON_VALUE, response = AutzUserPO.class)
//    @ApiImplicitParams({
//
//    })
//    @PostMapping
//    public List addAutzUser(AutzUserPO[] autzUserPOS) {
//        List<String> list = authUserService.addAutzUser(autzUserPOS);
//        return list;
//    }
//
//    /**
//     * 功能描述:删除auth_user表数据
//     *
//     * @Param: SpaceRoleUserBO
//     * @Return:com.taikang.xboot.sdk.ResponseMessage
//     * @Author:itw_dubin
//     * @Create:2018/11/27
//     * @Modify:
//     */
//    @ApiOperation(value = "删除auth_user表数据", notes = "删除auth_user表数据", httpMethod = "DELETE",
//            produces = MediaType.APPLICATION_JSON_VALUE, response = AutzUserPO.class)
//    @ApiImplicitParams({
//            // @ApiImplicitParam(name = "relationUserId", value = "pdms用户id", required = true, paramType = "query", dataType = "String")
//    })
//    @DeleteMapping
//    public ResponseMessage deleteAutzUser(@JsonParam @ApiParam AutzUserPO[] autzUserPOS){
//
//        if(autzUserPOS == null || autzUserPOS.length == 0) {
//            return ResponseMessage.error("请输入参数");
//        }
//        List<AutzUserPO> list = Arrays.asList(autzUserPOS);;
//        return  ResponseMessage.ok(authUserService.deleteAutzUser(list));
//
//    }
//
//    /**
//     * 功能描述:查询auth_user表数据
//     *
//     * @Param: SpaceRoleUserBO
//     * @Return:com.taikang.xboot.sdk.ResponseMessage
//     * @Author:itw_dubin
//     * @Create:2018/11/27
//     * @Modify:
//     */
//    @ApiOperation(value = "查询auth_user表数据", notes = "查询auth_user表数据", httpMethod = "GET",
//            produces = MediaType.APPLICATION_JSON_VALUE, response = AutzUserPO.class)
//    @ApiImplicitParams({
//            // @ApiImplicitParam(name = "relationUserId", value = "pdms用户id", required = true, paramType = "query", dataType = "String")
//            @ApiImplicitParam(name = "userId", value = "autzid", required = false, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "name", value = "姓名", required = false, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "userName", value = "用户名", required = false, paramType = "query", dataType = "String")
//    })
//    @GetMapping
//    public ResponseMessage queryAutzUser(@ApiIgnore @Valid AutzUserPO autzUserPO) {
//        List<AutzUserPO> list = authUserService.queryAutzUser(autzUserPO);
//        return ResponseMessage.ok(list);
//    }
//}