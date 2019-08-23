package com.taikang.test.autz.service;

import com.taikang.test.autz.bo.AutzUserPO;
import com.taikang.test.autz.bo.OAuth2Client;
import com.taikang.test.autz.util.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OAuth2ServiceImpl implements OAuth2Service{
    @Override
    public String accessToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String refreshToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public AutzUserPO selectByUsername(String userName) {
        return null;
    }

    @Override
    public OAuth2Client selectSingle(String clientId, String clientSecret, String s) {
        return null;
    }

    @Override
    public void addAccessToken(OAuth2AccessToken token) {

    }
}
