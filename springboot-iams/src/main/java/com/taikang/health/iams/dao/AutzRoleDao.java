package com.taikang.health.iams.dao;

import com.taikang.health.iams.po.AutzRolePO;

import java.util.List;

public interface AutzRoleDao {
    List<AutzRolePO> getRolesByUserId(String userId);
}