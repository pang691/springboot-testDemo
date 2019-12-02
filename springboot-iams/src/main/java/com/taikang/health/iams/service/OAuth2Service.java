package com.taikang.health.iams.service;

import com.taikang.health.iams.bo.AccessToken;

public interface OAuth2Service  {

    void addAccessToken(AccessToken auth2Access);

    //AccessToken refreshToken(String refreshToken, String newRefreshToken, String newAccessToken);
    AccessToken refreshToken(String refreshToken, String newAccessToken) throws Exception;

    AccessToken getAccessToken(String accessToken);

    /**根据userId获取token*/
    AccessToken getAccessTokenByUserId(String userId);

    int getDefaultExpireIn();

    int getRefreshTokenValidity();

}
