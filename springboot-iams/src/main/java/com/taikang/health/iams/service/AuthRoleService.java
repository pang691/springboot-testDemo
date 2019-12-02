package com.taikang.health.iams.service;

import com.taikang.health.iams.po.AutzRolePO;

import java.util.List;

public interface AuthRoleService {
    /**
     * 根据用户Id获取该用户的菜单列表
     *
     * @param userId 用户Id
     * @return 菜单列表
     */
    List<AutzRolePO> getRolesByUserId(String userId);


}