package com.taikang.health.iams.service.impl;

import com.taikang.health.iams.service.AutzPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("autzPermissionService")
public class AutzPermissionServiceImpl extends CrudServiceSupport implements AutzPermissionService {

    private static Logger _log = LoggerFactory.getLogger(AutzPermissionServiceImpl.class);

    @Autowired
    private AutzPermissionDao autzPermissionDaoMy;

    @Override
    public AutzPermissionDao getDefaultDao() {
        return autzPermissionDaoMy;
    }


    @Override
    public List<AutzPermissionPO> listPermission(String userId) {
        return autzPermissionDaoMy.listPermission(userId);
    }
}