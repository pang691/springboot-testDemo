package com.taikang.health.iams.service;

import com.taikang.health.iams.po.AutzPermissionPO;

import java.util.List;

public interface AutzPermissionService {

    List<AutzPermissionPO> listPermission(String userId);
}