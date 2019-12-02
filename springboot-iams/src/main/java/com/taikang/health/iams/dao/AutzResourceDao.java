package com.taikang.health.iams.dao;

import com.taikang.health.iams.po.AutzResourcePO;

import java.util.List;

public interface AutzResourceDao {
    List<AutzResourcePO> listResources(String userId);

    List<AutzResourcePO> roleResources(String roleId);

    List<AutzResourcePO> menuResourcesByUid(String userId);
}