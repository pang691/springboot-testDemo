package com.taikang.test.autz.service;

import com.taikang.test.autz.bo.AutzUserPO;
import com.taikang.test.autz.bo.OAuth2Client;
import com.taikang.test.autz.util.OAuth2AccessToken;

public interface OAuth2Service {
    String accessToken();

    String refreshToken();

    AutzUserPO selectByUsername(String userName);

    OAuth2Client selectSingle(String clientId, String clientSecret, String s);

    void addAccessToken(OAuth2AccessToken token);
}
