package com.taikang.health.iams.web;

import com.taikang.health.hicm.iams.bo.AccessToken;
import com.taikang.health.iams.bo.AccessTokenOut;
import com.taikang.health.iams.bo.AuthenticationOut;
import com.taikang.health.iams.util.TokenCacheHolder;
import com.taikang.xboot.accesslog.annotation.AccessLogger;
import com.taikang.xboot.auth.api.AuthHolder;
import com.taikang.xboot.auth.api.AuthSupplier;
import com.taikang.xboot.auth.api.model.Authentication;
import com.taikang.xboot.sdk.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/auth")
@Api(tags = "认证")
public class AuthController {

    private static Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthSupplier authSupplier;

    @ApiOperation(value = "查询token信息", notes = "查询token信息", httpMethod = "GET",
            produces = MediaType.APPLICATION_JSON_VALUE, response = AccessTokenOut.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "验证token(通过Oauth2获得)", paramType = "header", dataType = "String", required = true)
    })
    @GetMapping
    public ResponseMessage getAccessTokenModel(@RequestHeader("Authorization") String accessToken) {
        AccessToken token = TokenCacheHolder.getToken(accessToken);
        return ResponseMessage.ok(token);
    }


    @ApiOperation(value = "查询用户信息", notes = "查询用户信息", httpMethod = "GET",
            produces = MediaType.APPLICATION_JSON_VALUE, response = AuthenticationOut.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "验证token(通过Oauth2获得)", paramType = "header", dataType = "String", required = true)
    })
    @GetMapping("/auth")
    public ResponseMessage getAuth(@RequestHeader("Authorization") String accessToken, @RequestParam("userId") String userId) {
        logger.info("查询认证信息-{}", userId);
        AccessToken token = TokenCacheHolder.getToken(accessToken);
        userId = token.getUserId();
        // redis 缓存
        //Authentication auth = AuthHolder.getByUserId(userId)
        Authentication auth = authSupplier.getByUserId(userId);
        return ResponseMessage.ok(auth);
    }

}