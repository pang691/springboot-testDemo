package com.taikang.health.iams.dao;

import com.taikang.health.iams.po.AutzUserPO;

import java.util.List;

public interface AutzUserDao {
    AutzUserPO getByUserName(String username);

    AutzUserPO getByUserId(String userId);

    AutzUserPO selectSingle(AutzUserPO queryUser);

    void addAutzUser(AutzUserPO autzUserPO);

    boolean deleteByUserId(List<AutzUserPO> list);

    List<AutzUserPO> queryAutzUser(AutzUserPO autzUserPO);
}