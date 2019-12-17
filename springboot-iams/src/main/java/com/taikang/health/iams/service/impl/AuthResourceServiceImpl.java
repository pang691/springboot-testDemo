package com.taikang.health.iams.service.impl;

import com.taikang.health.iams.dao.AutzResourceDao;
import com.taikang.health.iams.po.AutzResourcePO;
import com.taikang.health.iams.service.AuthResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("authResourceService")
public class AuthResourceServiceImpl implements AuthResourceService {

    private static Logger _log = LoggerFactory.getLogger(AuthResourceServiceImpl.class);

    @Autowired
    private AutzResourceDao autzResourceDao;

    @Override
    public List<AutzResourcePO> listResources(String userId) {
        return autzResourceDao.listResources(userId);
    }

    @Override
    public List<AutzResourcePO> roleResources(String roleId) {
        return autzResourceDao.roleResources(roleId);
    }
}