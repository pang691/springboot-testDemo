package com.taikang.health.iams.dao;

import com.taikang.health.iams.po.AutzUserPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AutzUserDao {
    AutzUserPO getByUserName(String username);

    AutzUserPO getByUserId(String userId);

    AutzUserPO selectSingle(AutzUserPO queryUser);

    void addAutzUser(AutzUserPO autzUserPO);

    boolean deleteByUserId(List<AutzUserPO> list);

    List<AutzUserPO> queryAutzUser(AutzUserPO autzUserPO);

    void insertAutzUserPO(AutzUserPO userPo);
}