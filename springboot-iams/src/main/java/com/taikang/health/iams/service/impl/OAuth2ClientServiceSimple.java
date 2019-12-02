package com.taikang.health.iams.service.impl;

import com.taikang.health.iams.dao.OAuth2ClientDao;
import com.taikang.health.iams.po.OAuth2Client;
import com.taikang.health.iams.service.OAuth2ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OAuth2ClientServiceSimple implements OAuth2ClientService {

    @Autowired
    private OAuth2ClientDao oAuth2ClientDao;

    @Override
    public OAuth2Client selectSingle(OAuth2Client query) {
        return oAuth2ClientDao.selectSingle(query);
    }
}