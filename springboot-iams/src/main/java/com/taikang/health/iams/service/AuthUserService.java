package com.taikang.health.iams.service;

import com.taikang.health.iams.po.AutzUserPO;

import java.util.List;

public interface AuthUserService {

    AutzUserPO selectByUsername(String username);

    AutzUserPO selectByUserId(String userId);

    List<String> addAutzUser(AutzUserPO[] autzUserPOS);

    boolean deleteAutzUser(List<AutzUserPO> list);

    List<AutzUserPO> queryAutzUser(AutzUserPO autzUserPOS);

    AutzUserPO selectSingle(AutzUserPO queryUser);

    void insertAutzUserPO(AutzUserPO userPo);
}