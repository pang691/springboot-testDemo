package com.taikang.health.iams.dao;

import com.taikang.health.iams.po.AutzUserRolePO;

import java.util.List;

public interface AutzUserRoleDao {

    void insertBatch(List<AutzUserRolePO> list);

}