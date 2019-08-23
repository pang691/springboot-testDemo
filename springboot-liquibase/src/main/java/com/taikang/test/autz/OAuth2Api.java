package com.taikang.test.autz;

import com.taikang.test.autz.bo.AutzUserPO;
import com.taikang.test.autz.bo.OAuth2Client;
import com.taikang.test.autz.config.AuthHolder;
import com.taikang.test.autz.config.Authentication;
import com.taikang.test.autz.service.OAuth2Service;
import com.taikang.test.autz.util.LoginCaches;
import com.taikang.test.autz.util.OAuth2AccessToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.service.GrantType;

import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

@RestController
@RequestMapping("oauth2")
@Api(tags = "登录认证", description = "登录认证API")
public class OAuth2Api {
    private static Logger logger = LoggerFactory.getLogger(OAuth2Api.class);
    @Autowired
    private OAuth2Service oauth2Service;
    //@Value("${oauth2.access_token.expiresIn}")
    private String accessTokenExpiresIn;
    //@Value("${oauth2.refresh_token.expiresIn}")
    private String refreshTokenExpiresIn;
    //@Value("${login.loginErrorNumPerHourPerId}")
    private String loginErrorNumPerHourPerId;
    //@Value("${login.loginErrorNumPerDayPerIP}")
    private String loginErrorNumPerDayPerIP;


    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "获取access_token", notes = "可用来获取access_token", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "grant_type", defaultValue = "password", value = "password", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "client_id", defaultValue = "ship_admin", value = "请求方ID(ship_admin)", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "client_secret", defaultValue = "23402d775bfe9fd82b4334cd1bed2a43", value = "请求方密码(23402d775bfe9fd82b4334cd1bed2a43)", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "username", defaultValue = "ship", value = "当前用户账号(ship)", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "password", defaultValue = "pass1234", value = "当前用户密码(pass1234)", required = true, paramType = "form", dataType = "String")
    })
    @RequestMapping(value = "/access_token", method = {RequestMethod.POST})
    public String accessToken(@RequestParam(value = OAuth.OAUTH_GRANT_TYPE, defaultValue = "password") String grantType,
                            HttpServletRequest request) {
        try {
            logger.info("-----进入access_token,-begin-----");
            // 判断当前IP是否被锁定
            boolean userIPLocked = getUserIPLocked(request.getRemoteHost());
            if (!userIPLocked) {
                OAuthTokenRequest tokenRequest = new OAuthTokenRequest(request);
                //获取OAuth2客户端
                String clientId = tokenRequest.getClientId();
                String clientSecret = tokenRequest.getClientSecret();
//                QueryParamAsPO query = QueryParamAsPO.build()
//                        .where("id", clientId)
//                        .and("secret", clientSecret).and("status", 1);
                OAuth2Client client = oauth2Service.selectSingle(clientId,clientSecret,"1");
                //验证OAuth2客户端
                Authentication authentication = null;
                if (null == client) {
                    throw new Exception("客户端未授权");
                }

                //authorization_code：授权码模式(即先登录获取code,再获取token)
                //password：密码模式(将用户名,密码传过去,直接获取token)
                //client_credentials：客户端模式(无用户,用户向客户端注册,然后客户端以自己的名义向’服务端’获取资源)
                if (grantType.equals("password")) {
                    String userName = tokenRequest.getParam(OAuth.OAUTH_USERNAME);
                    String userPassword = tokenRequest.getParam(OAuth.OAUTH_PASSWORD);
                    //根据用户名获取认证信息
                    authentication = AuthHolder.getByUsername(userName);
                    // 登陆前判断当前账号是否锁定
                    boolean userIdLocked = getUserIdLocked(userName);
                    if (!userIdLocked) {
                        //执行ldap登录
                        boolean loginseccess = false;
//                        boolean loginseccess = ldapAuthentication.login(userName, userPassword);
//                        logger.info("LDAP验证信息:{}", ldapAuthentication.getMsgInfo());
//                        if (loginseccess) {
//                            logger.info("LDAP验证通过，将用户信息插入到系统用户信息表");
//                            AutzUserPO autzUserPO = authUserService.selectByUsername(userName);
//                            if (null == autzUserPO) {
//                                AutzUserPO userPo = AutzUserPO.build().setUserName(userName).setIsEnabled("1");
//                                userPo.setUserPwd(userPassword);
//                                userPo.setUserId(userName);
//                                authUserService.insert(userPo);
//                                QueryParamAsPO build = QueryParamAsPO.build().where("role_code", "no_perm");
//                                List<AutzRolePO> role = authRoleService.select(build, AutzRolePO.class);
//                                if (role.size() > 0) {
//                                    AutzUserRolePO po = AutzUserRolePO.build();
//                                    po.setUserId(userName).setRoleId(role.get(0).getId());
//                                    po.setId(IdHelper.genWorkerId());
//                                    autzUserRoleService.insertUserRole(po);
//                                }
//                            }
//                        }
                        if (!loginseccess) {
                            logger.info("LDAP验证失败，尝试数据库验证");
                            //尝试普通登录
                            AutzUserPO user = oauth2Service.selectByUsername(userName);
                            if (user != null && userPassword.equals(user.getUserPwd()) && userName.equals(user.getUserId())) {
                                logger.info("数据库验证通过");
                                loginseccess = true;
                            } else {
                                logger.info("数据库验证失败");
                            }
                        }
                        if (!loginseccess) {
                            setLoginErrorNumPerHourPerId(userName);
                            setLoginErrorNumPerDayPerIP(request.getRemoteHost());
                            throw new Exception("用户名或密码错误,请重新登录");
                        }

                        if (!loginseccess && null == authentication) {
                            throw new Exception("账号不存在");
                        }

                        //生成Token
                        String accessToken = oauth2Service.accessToken();
                        String refreshToken = oauth2Service.refreshToken();

                        //构造entity
                        OAuth2AccessToken token = OAuth2AccessToken.build();
                        Calendar instance = Calendar.getInstance();
                        instance.setTimeInMillis(System.currentTimeMillis());
                        token.setRefreshTokenValidity(Integer.decode(refreshTokenExpiresIn))
                                .setAccessTokenUpdateTime(instance.getTime())
                                .setExpireIn(Integer.decode(accessTokenExpiresIn))
                                .setCreateDate(instance.getTime())
                                .setAccessToken(accessToken)
                                .setRefreshToken(refreshToken)
                                .setUserId(userName)
                                .setClientId(clientId);
                        //更新access_token
                        oauth2Service.addAccessToken(token);
                        OAuthResponse response = OAuthASResponse
                                .tokenResponse(HttpServletResponse.SC_OK)
                                .setTokenType("bearer")
                                .setAccessToken(accessToken)
                                .setExpiresIn(accessTokenExpiresIn)
                                .setRefreshToken(refreshToken)
                                .setScope("public")
                                .buildJSONMessage();
                        return response.getBody();
                    } else {
                        setLoginErrorNumPerDayPerIP(request.getRemoteHost());
                        throw new Exception("当前用户已锁定，请稍后再试\n");
                    }
                } else {
                    throw new Exception("不支持的授权类型");
                }
            } else {
                throw new Exception("登录失败次数过多，请稍后再试");
            }
        }catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    private boolean getUserIPLocked(String userIP) {
        Cache cacheOfUserIPLocked = CacheManagerHolder.getManager().getCache(LoginCaches.USERIPLOCKED.getCacheName());
        Cache cacheOfLoginErrorNumPerHour = CacheManagerHolder.getManager().getCache(LoginCaches.LOGINERRORNUMPERDAYPERIP.getCacheName());
        return getLocked(userIP, cacheOfUserIPLocked, cacheOfLoginErrorNumPerHour, loginErrorNumPerDayPerIP);
    }
    private boolean getLocked(String userId, Cache cacheOfUserIdLocked, Cache cacheOfLoginErrorNumPerHour, String loginErrorNumPerHourPerId) {
        boolean userIdLocked;
        Integer num = cacheOfLoginErrorNumPerHour.get(userId, Integer.class);
        userIdLocked = num != null && num >= Integer.decode(loginErrorNumPerHourPerId);
        cacheOfUserIdLocked.put(userId, userIdLocked);
        return userIdLocked;
    }
    private void setLoginErrorNumPerHourPerId(String userId) {
        Cache cacheOfLoginErrorNumPerHour = CacheManagerHolder.getManager().getCache(LoginCaches.LOGINERRORNUMPERHOURPERID.getCacheName());
        Integer num = cacheOfLoginErrorNumPerHour.get(userId, Integer.class);
        if (num == null) {
            num = 1;
        } else {
            num += 1;
            if (num >= Integer.decode(loginErrorNumPerHourPerId)) {
                Cache cacheOfUserIdLocked = CacheManagerHolder.getManager().getCache(LoginCaches.USERIDLOCKED.getCacheName());
                cacheOfUserIdLocked.put(userId, true);
            }
        }
        cacheOfLoginErrorNumPerHour.put(userId, num);
    }

    private boolean getUserIdLocked(String userId) {
        Cache cacheOfUserIdLocked = CacheManagerHolder.getManager().getCache(LoginCaches.USERIDLOCKED.getCacheName());
        Cache cacheOfLoginErrorNumPerHour = CacheManagerHolder.getManager().getCache(LoginCaches.LOGINERRORNUMPERHOURPERID.getCacheName());
        return getLocked(userId, cacheOfUserIdLocked, cacheOfLoginErrorNumPerHour, loginErrorNumPerHourPerId);
    }

    private void setLoginErrorNumPerDayPerIP(String userIP) {
        Cache cache = CacheManagerHolder.getManager().getCache(LoginCaches.LOGINERRORNUMPERDAYPERIP.getCacheName());
        Integer num = cache.get(userIP, Integer.class);
        if (num == null) {
            num = 1;
        } else {
            num += 1;
            if (num >= Integer.decode(loginErrorNumPerDayPerIP)) {
                Cache cacheOfUserIdLocked = CacheManagerHolder.getManager().getCache(LoginCaches.USERIPLOCKED.getCacheName());
                cacheOfUserIdLocked.put(userIP, true);
            }
        }
        cache.put(userIP, num);
    }

}
