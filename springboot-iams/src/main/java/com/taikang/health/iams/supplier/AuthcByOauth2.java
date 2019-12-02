package com.taikang.health.iams.supplier;

import com.taikang.health.iams.bo.UserModel;
import com.taikang.health.iams.util.MessageAuto;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.cache.Cache;

import javax.servlet.http.HttpServletRequest;
import java.net.Authenticator;

public class AuthcByOauth2 implements Authenticator {

    private String message = "could not found token from current request!";


    @Override
    public Authentication authenticate(HttpServletRequest request) {
        String userId = getUserId(request);
        return AuthHolder.getByUserId(userId);

    }

    @Override
    public UserModel bindingUser(HttpServletRequest request) {
        String userId = getUserId(request);
        if (userId != null) {
            Authentication auth = AuthHolder.getByUserId(userId);
            if (auth != null) {
                return auth.getUser();
            }
        }
        return null;
    }

    private String getUserId(HttpServletRequest request) {
        String token = null;
        OAuth2Supplier oAuth2Manager = OAuth2Holder.getManager();
        if (oAuth2Manager != null) {
            token = getAccessTokenByRequest(request);
            if (UtilString.isEmpty(token)) {
                throw new AuthcException(MessageAuto.getDefaultBean().getMessage("401103", null, null), 401103);
            }
        }
        if (UtilString.isEmpty(token)) {
            throw new AuthcException(MessageAuto.getDefaultBean().getMessage("401103", null, null), 401103);
        }
        return getUserIdFormTokenCache(token);
    }

    public String getUserIdFormTokenCache(String accessToken) {
        AccessToken auth2Access = null;
        boolean inCache = false;
        Cache cache = CacheManagerHolder.getManager().getCache(Caches.accessToken.getCacheName());
        if (cache != null) {
            Cache.ValueWrapper wrapper = cache.get(accessToken);
            if (wrapper != null) {
                auth2Access = (AccessToken) wrapper.get();
                inCache = true;
            }
        }

        if (auth2Access == null) {
            OAuth2Supplier oAuth2Manager = OAuth2Holder.getManager();
            auth2Access = ((OAuth2SupplierOnline) oAuth2Manager).getAccessTokenModel(accessToken);
        }

        if (auth2Access == null) {
            throw new AuthcException(MessageAuto.getDefaultBean().getMessage("401101", null, null), 401101);
        } else if (auth2Access.getAccessTokenLeftTime() <= 0L) {
            if (cache != null) {
                cache.evict(accessToken);
            }
            throw new AuthcException(MessageAuto.getDefaultBean().getMessage("401102", null, null), 401102);
        } else {
            if (!inCache && cache != null) {
                cache.put(accessToken, auth2Access);
            }

            return auth2Access.getUserId();
        }
    }

    public String getAccessTokenByRequest(HttpServletRequest request) {
        String token = request.getHeader("access_token");
        int arrLength = 2;
        if (token == null) {
            String authorization = request.getHeader("Authorization");
            if (authorization != null) {
                String[] arr = authorization.split("[ ]");
                if (arr.length == 1) {
                    token = arr[0];
                } else if (arr.length == arrLength) {
                    token = arr[1];
                }
            }
        }

        return token;
    }
}