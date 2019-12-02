package com.taikang.health.iams.service.impl;

import com.taikang.health.iams.po.AutzResourcePO;
import com.taikang.health.iams.service.AuthResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthResourceServiceImpl implements AuthResourceService {

    private static Logger _log = LoggerFactory.getLogger(AuthResourceServiceImpl.class);

    @Autowired
    private AutzResourceDao autzPermissionDao;

    @Override
    public AutzResourceDao getDefaultDao() {
        return autzPermissionDao;
    }

    @Override
    public List<AutzResourcePO> listResources(String userId) {
        return autzPermissionDao.listResources(userId);
    }

    @Override
    public List<AutzResourcePO> roleResources(String roleId) {
        return autzPermissionDao.roleResources(roleId);
    }




}