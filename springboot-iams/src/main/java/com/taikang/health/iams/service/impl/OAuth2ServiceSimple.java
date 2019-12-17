package com.taikang.health.iams.service.impl;

import com.taikang.health.iams.bo.AccessToken;
import com.taikang.health.iams.bo.AccessTokenModel;
import com.taikang.health.iams.dao.OAuth2AccessDao;
import com.taikang.health.iams.po.OAuth2Access;
import com.taikang.health.iams.service.OAuth2Service;
import com.taikang.health.iams.util.CacheManagerHolder;
import com.taikang.health.iams.util.Caches;
import com.taikang.health.iams.util.MessageAuto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OAuth2ServiceSimple implements OAuth2Service {

    @Autowired
    private OAuth2AccessDao oAuth2AccessDao;

    @Value("${oauth2.access_token.expiresIn:86400}")
    private String accessTokenExpiresIn;
    @Value("${oauth2.refresh_token.expiresIn:172800}")
    private String refreshTokenExpiresIn;
    /**允许刷新token 10分钟*/
    @Value("${oauth2.refresh_token.allowRefreshTime:600}")
    private String allowRefreshTime;

    @Override
    public void addAccessToken(AccessToken auth2Access) {
        if (auth2Access.getId() == null) {
            auth2Access.setId("1");
        }
        //删除旧的token,相同clientId,userId只有一个token
        AccessToken accessTokens = new AccessToken();
        accessTokens.setClientId(auth2Access.getClientId()).setUserId(auth2Access.getUserId());
        AccessToken accesses = oAuth2AccessDao.selectSingle(accessTokens);
        if (null != accesses) {
                this.removeAccessFromCache(accesses.getAccessToken());

            oAuth2AccessDao.deleteAccessToken(accessTokens);
        }
        // 设置有效时间
        if (0 == auth2Access.getExpireIn()) {
            auth2Access.setExpireIn(getDefaultExpireIn());
        }
        if (0 == auth2Access.getRefreshTokenValidity()) {
            auth2Access.setRefreshTokenValidity(getRefreshTokenValidity());
        }
        oAuth2AccessDao.insertAccessToken(auth2Access);
    }

    /**
     * 并发问题
     */
    @Override
    @Transactional(noRollbackFor = {RuntimeException.class})
    public AccessToken refreshToken(String refreshToken, String newAccessToken) throws Exception {
        OAuth2Access accessTokenModel = oAuth2AccessDao.selectByRefreshToken(refreshToken);
        if (accessTokenModel == null) {
            throw new Exception(MessageAuto.getDefaultBean().getMessage("401104", null, null));
        }
        if (accessTokenModel.getRefreshTokenLeftTime() <= 0L) {
            throw new Exception(MessageAuto.getDefaultBean().getMessage("401105", null, null));
        }
        // 更新token
        if (accessTokenModel.getRefreshTokenLeftTime() <= Long.parseLong(allowRefreshTime)) {
            String oldAccessToken = accessTokenModel.getAccessToken();
            accessTokenModel.setAccessToken(newAccessToken);
            accessTokenModel.setExpireIn(getDefaultExpireIn());
            accessTokenModel.setRefreshTokenValidity(getRefreshTokenValidity());
            accessTokenModel.setAccessTokenUpdateTime(new Date());
            accessTokenModel.setCreateDate(new Date());

            //移除旧的accesstoken缓存
            removeAccessFromCache(oldAccessToken);

            //如果refresh_token没过期的话  采用refresh_token更新新的access_token
            //oAuth2AccessDao.updateIncludesByPk(token.getId(), token, "accessToken", "accessTokenUpdateTime", "createDate")
            // update table set access_token='a' where access_token='B' and id='C'
            OAuth2Access oldToken = new OAuth2Access();
            oldToken.setAccessToken(oldAccessToken);
            int result =oAuth2AccessDao.updateByIdAndKeys(accessTokenModel, oldToken);
            if (0 == result) {
                accessTokenModel = oAuth2AccessDao.selectByRefreshToken(refreshToken);
            }
        }
        AccessToken accessTokens = new AccessToken();
        BeanUtils.copyProperties(accessTokenModel, accessTokens);
        return  accessTokens;

    }
/**
     * 移除旧的缓存
     */
    private void removeAccessFromCache(String accessToken) {
        String cacheKey = accessToken;
        Cache cache = CacheManagerHolder.getManager().getCache(Caches.accessToken.getCacheName());
        cache.evict(cacheKey);
    }


    @Override
    public AccessToken getAccessToken(String accessToken) {
        OAuth2Access auth2Access = oAuth2AccessDao.selectByAccessToken(accessToken);
        AccessToken accessTokens = new AccessToken();
        if (auth2Access != null) {
            BeanUtils.copyProperties(auth2Access, accessTokens);
            return accessTokens;
        }
        return null;
    }

    /**
     * 根据userId获取token
     */
    @Override
    public AccessToken getAccessTokenByUserId(String userId) {
        OAuth2Access auth2Access = oAuth2AccessDao.selectByUserId(userId);
        AccessToken accessToken = new AccessToken();
        if (auth2Access != null) {
            BeanUtils.copyProperties(auth2Access, accessToken);
            return accessToken;
        }
        return null;
    }

    @Override
    public int getDefaultExpireIn() {
        return Integer.decode(accessTokenExpiresIn);
    }

    @Override
    public int getRefreshTokenValidity() {
        return Integer.decode(refreshTokenExpiresIn);
    }

    @Override
    public AccessToken selectSingle(AccessToken accessTokens) {
        return oAuth2AccessDao.selectSingle(accessTokens);
    }


}