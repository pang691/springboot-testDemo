package com.taikang.health.iams.web;

import com.taikang.health.iams.bo.AccessToken;
import com.taikang.health.iams.enums.ClientType;
import com.taikang.health.iams.ldapauth.LdapAuthentication;
import com.taikang.health.iams.po.AutzRolePO;
import com.taikang.health.iams.po.AutzUserPO;
import com.taikang.health.iams.po.OAuth2Client;
import com.taikang.health.iams.service.AuthRoleService;
import com.taikang.health.iams.service.AuthUserService;
import com.taikang.health.iams.service.OAuth2ClientService;
import com.taikang.health.iams.service.OAuth2Service;
import com.taikang.health.iams.util.LoginCacheHolder;
import com.taikang.health.iams.util.MessageAuto;
import com.taikang.health.iams.util.TokenCacheHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.ResponseMessage;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/oauth2")
@Api(tags = "令牌")
public class AutzApi {

    private static Logger logger = LoggerFactory.getLogger(AutzApi.class);

    @Autowired
    private OAuth2ClientService oAuth2ClientService;
    @Autowired
    private OAuth2Service oauth2Service;
    @Autowired
    private LdapAuthentication ldapAuthentication;
    @Autowired
    private AuthUserService authUserService;
    @Autowired
    private AuthRoleService authRoleService;

    @Autowired(required = false)
    private OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());


    @ApiOperation(value = "获取access_token", notes = "可用来获取access_token", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, paramType = "form", dataType = "String", value = "请求方ID", name = "client_id"),
            @ApiImplicitParam(required = true, paramType = "form", dataType = "String", value = "请求方密码", name = "client_secret"),
            @ApiImplicitParam(required = true, paramType = "form", dataType = "String", value = "当前用户账号", name = "username"),
            @ApiImplicitParam(required = true, paramType = "form", dataType = "String", value = "当前用户密码", name = "password"),
            @ApiImplicitParam(required = true, paramType = "form", dataType = "String", value = "grant type", name = "grant_type")
    })
    @PostMapping(value = "/access_token")
    public String accessToken(javax.servlet.http.HttpServletRequest request) throws Exception {
        logger.info("-----进入access_token,-begin-----");
        OAuthTokenRequest tokenRequest = new OAuthTokenRequest(request);
        String clientId = tokenRequest.getClientId();
        String clientSecret = tokenRequest.getClientSecret();
        String userName = tokenRequest.getUsername();
        String userPassword = tokenRequest.getPassword();
        String remoteHost = request.getRemoteHost().replaceAll("\\.", "_").replaceAll(":", "_");
        String grantType = tokenRequest.getGrantType();

        // 第一步：判断授权码类型password []
        if (!grantType.equals(GrantType.PASSWORD.toString())) {
            throw new Exception(MessageAuto.getDefaultBean().getMessage("1002", null, null));
        }
        // 第二步：判断当前IP是否被锁定[need cache]
        boolean userIpLocked = LoginCacheHolder.getUserIpLocked(remoteHost);
        if (userIpLocked) {
            throw new Exception(MessageAuto.getDefaultBean().getMessage("1003", null, null));
        }
        // 第三步：判断当前账号是否锁定[need cache]
        boolean userIdLocked = LoginCacheHolder.getUserIdLocked(userName);
        if (userIdLocked) {
            LoginCacheHolder.setLoginErrorNumPerDayPerIp(remoteHost);
            throw new Exception(MessageAuto.getDefaultBean().getMessage("1004", null, null));
        }
        // 第四步：根据传参确定是否存在的客户端[need database]
        OAuth2Client query = new OAuth2Client();
        query.setSecret(clientSecret).setStatus(1).setId(clientId);
        OAuth2Client client = oAuth2ClientService.selectSingle(query);
        if (null == client) {
            throw new Exception(MessageAuto.getDefaultBean().getMessage("1001", null, null));
        }
        //  1.接口令牌（API TOKEN） 2.用户令牌（USER TOKEN）

        // 第五步： Ldap登录
        boolean loginSuccess = false;
        if (ClientType.USER_TOKEN.value() == (client.getClientType())) {
            logger.info("请求LDAP服务验证信息");
            loginSuccess = ldapAuthentication.login(userName, userPassword);
            logger.info("LDAP验证信息:{}", ldapAuthentication.getMsgInfo());
        }

        AutzUserPO localUser = null;
        // 第六步：数据库登录
        if (!loginSuccess) {
            logger.info("尝试数据库验证");
            //userPassword = org.springframework.util.DigestUtils.md5DigestAsHex(userPassword.getBytes())
            AutzUserPO queryUser = AutzUserPO.build();
            queryUser.setUsername(userName).setPassword(userPassword);
            localUser = authUserService.selectSingle(queryUser);
            loginSuccess = localUser != null && localUser.getUsername() != null;
        }

        // 第七步：判断是否登录成功
        if (!loginSuccess) {
            logger.info("验证失败");
            LoginCacheHolder.setLoginErrorNumPerHourPerId(userName);
            LoginCacheHolder.setLoginErrorNumPerDayPerIp(remoteHost);
            throw new Exception(MessageAuto.getDefaultBean().getMessage("1005", null, null));
        }

        // 根据用户名获取认证信息
        if (null == localUser) {
            logger.info("LDAP验证通过，将用户信息插入到系统用户信息表，需要分配角色");
            AutzUserPO userPo = AutzUserPO.build().setName(userName).setUsername(userName).setEnable(1);
            authUserService.insertAutzUserPO(userPo);
            throw new Exception(MessageAuto.getDefaultBean().getMessage("1006", null, null));
        }

        String userId = localUser.getId();
        // 用户和角色（客户端）绑定；每个客户端一个角色
        boolean bind = false;
        List<AutzRolePO> roles = authRoleService.getRolesByUserId(userId);
        for (AutzRolePO role : roles) {
            // name 数据表中的 role_code 字段
            if (role.getRoleCode().equals(clientId)) {
                bind = true;
                break;
            }
        }
        if (!bind) {
            throw new Exception(MessageAuto.getDefaultBean().getMessage("1007", null, null));
        }


        logger.info("验证通过");
        // 第八步：取缓存token
        AccessToken token = TokenCacheHolder.getToken(clientId, userId);
        // 第九步：生成新token，存数据库
        if (token == null) {
            String accessToken = oauthIssuerImpl.accessToken();
            String refreshToken = oauthIssuerImpl.refreshToken();
            // 构造entity，存数据库
            token = AccessToken.build();
            token.setRefreshTokenValidity(oauth2Service.getRefreshTokenValidity())
                    .setAccessTokenUpdateTime(new Date())
                    .setExpireIn(oauth2Service.getDefaultExpireIn())
                    .setCreateDate(new Date())
                    .setAccessToken(accessToken)
                    .setRefreshToken(refreshToken)
                    .setUserId(userId)
                    .setClientId(clientId);
            oauth2Service.addAccessToken(token);
        }
        // 第十步：返回信息
        OAuthResponse response = OAuthASResponse
                .tokenResponse(HttpServletResponse.SC_OK)
                .setTokenType("bearer")
                .setAccessToken(token.getAccessToken())
                .setExpiresIn(String.valueOf(token.getExpireIn()))
                .setRefreshToken(token.getRefreshToken())
                .setScope("public")
                .buildJSONMessage();
        return response.getBody();

    }

    @ApiOperation(value = "刷新access_token", notes = "刷新access_token", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "refresh_token", value = "grant refresh_token", required = true, paramType = "header", dataType = "String")
    })
    @RequestMapping(value = "/refresh_token", method = {RequestMethod.POST, RequestMethod.GET})
    public String refresh(javax.servlet.http.HttpServletRequest request) throws Exception {
        String refreshToken = request.getHeader("refresh_token");
        if (StringUtils.isEmpty(refreshToken)) {
            throw new Exception(MessageAuto.getDefaultBean().getMessage("401100", null, null));
        }
        String newAccessToken = oauthIssuerImpl.accessToken();
        AccessToken accessToken = oauth2Service.refreshToken(refreshToken, newAccessToken);
        OAuthResponse response = OAuthASResponse
                .tokenResponse(HttpServletResponse.SC_OK)
                .setTokenType("bearer")
                .setAccessToken(accessToken.getAccessToken())
                .setExpiresIn(String.valueOf(accessToken.getExpireIn()))
                .setRefreshToken(accessToken.getRefreshToken())
                .setScope("public")
                .buildJSONMessage();
        return response.getBody();
    }


}