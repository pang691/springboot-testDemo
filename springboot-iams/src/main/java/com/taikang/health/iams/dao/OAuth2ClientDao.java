package com.taikang.health.iams.dao;

import com.taikang.health.iams.po.OAuth2Client;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface OAuth2ClientDao {
    List<OAuth2Client> getClientsByUserId(String userId);

    OAuth2Client selectSingle(OAuth2Client query);
}