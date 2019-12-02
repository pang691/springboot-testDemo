package com.taikang.health.iams.dao;

import com.taikang.health.iams.po.AutzPermissionPO;

import java.util.List;

public interface AutzPermissionDao {

    List<AutzPermissionPO> listPermission(String userId);
}