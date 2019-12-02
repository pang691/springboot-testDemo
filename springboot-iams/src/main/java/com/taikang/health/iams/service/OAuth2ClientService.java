package com.taikang.health.iams.service;

import com.taikang.health.iams.po.OAuth2Client;

public interface OAuth2ClientService {
    OAuth2Client selectSingle(OAuth2Client query);
}