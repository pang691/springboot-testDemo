package com.taikang.health.iams.util;

import com.taikang.health.iams.bo.AccessToken;
import com.taikang.health.iams.exception.AuthcException;
import com.taikang.health.iams.service.OAuth2Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Component
public class TokenCacheHolder {

    private static final Logger logger = LoggerFactory.getLogger(TokenCacheHolder.class);

    @Autowired
    private OAuth2Service service;
    private static OAuth2Service serviceTarget;

    @PostConstruct
    private void init() {
        logger.info("TokenCacheHolder init");
        serviceTarget = service;
    }

    /**
     * 获取 token
     */
    public static String getAccessTokenByRequest(HttpServletRequest request) {
        String token = request.getHeader("access_token");
        if (token == null) {
            String authorization = request.getHeader("Authorization");
            if (authorization != null) {
                String[] arr = authorization.split("[ ]");
                if (arr.length == 1) {
                    token = arr[0];
                } else if (2 == arr.length) {
                    token = arr[1];
                }
            }
        }
        if (token == null) {
            token = request.getParameter("access_token");
        }
        return token;
    }


    /**
     * 获取 token
     */
    public static AccessToken getToken(String accessToken) {
        AccessToken auth2Access = null;
        boolean inCache = false;
        Cache cache = CacheManagerHolder.getManager().getCache(Caches.accessToken.getCacheName());
        Cache.ValueWrapper wrapper = cache.get(accessToken);
        if (wrapper != null) {
            auth2Access = (AccessToken) wrapper.get();
            inCache = true;
            logger.debug("已从缓存中获取TokenModel信息-{}", accessToken);
        }
        if (auth2Access == null) {
            logger.info("从数据库获取TokenModel信息");
            auth2Access = serviceTarget.getAccessToken(accessToken);
        }
        if (auth2Access == null) {
            throw new AuthcException(MessageAuto.getDefaultBean().getMessage("401101", null, null), 401101);
        }
        if (auth2Access.getAccessTokenLeftTime() <= 0L) {
            if (inCache) {
                cache.evict(accessToken);
            }
            throw new AuthcException(MessageAuto.getDefaultBean().getMessage("401102", null, null), 401102);
        }
        if (!inCache) {
            cache.put(accessToken, auth2Access);
        }

        return auth2Access;
    }

    public static AccessToken getToken(String clientId, String userId) {
        Objects.requireNonNull(clientId);
        Objects.requireNonNull(userId);
        AccessToken auth2Access = null;
        Cache cache = CacheManagerHolder.getManager().getCache(Caches.accessToken.getCacheName());
        String cacheKey = clientId + ":" + userId;
        Cache.ValueWrapper wrapper = cache.get(cacheKey);
        if (wrapper != null) {
            auth2Access = (AccessToken) wrapper.get();
            logger.debug("已从缓存中获取TokenModel信息 clientId:userId-{}", cacheKey);
        }
        // 缓存的token无效
        if (auth2Access == null || auth2Access.getAccessTokenLeftTime() <= 0L) {
            AccessToken accessTokens = new AccessToken();
            accessTokens.setClientId(clientId).setUserId(userId);

            auth2Access = serviceTarget.selectSingle(accessTokens);
            // 数据库是否有效token
            if (auth2Access != null && auth2Access.getAccessTokenLeftTime() > 0L) {
                cache.put(cacheKey, auth2Access);
            }else{
                auth2Access = null;
            }
        }
        return auth2Access;
    }


}