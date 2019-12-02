package com.taikang.health.iams.service;

import com.taikang.health.iams.po.AutzUserRolePO;

import java.util.List;

public interface AutzUserRoleService  {

    void insertBatch(List<AutzUserRolePO> collection);

    int deleteUserRole(AutzUserRolePO po);

}