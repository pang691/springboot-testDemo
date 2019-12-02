package com.taikang.health.iams.service;

import com.taikang.health.iams.po.AutzResourcePO;

import java.util.List;

public interface AuthResourceService {

    List<AutzResourcePO> listResources(String userId);

    List<AutzResourcePO> roleResources(String roleId);


}