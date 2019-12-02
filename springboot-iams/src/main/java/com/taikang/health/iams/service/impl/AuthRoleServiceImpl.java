package com.taikang.health.iams.service.impl;

import com.taikang.health.iams.po.AutzRolePO;
import com.taikang.health.iams.service.AuthRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthRoleServiceImpl  implements AuthRoleService {
    private static Logger _log = LoggerFactory.getLogger(AuthRoleServiceImpl.class);

    @Autowired
    private AutzRoleDao authRoleDao;

    @Override
    public AutzRoleDao getDefaultDao() {
        return authRoleDao;
    }

    @Override
    public List<AutzRolePO> getRolesByUserId(String userId) {
        List<AutzRolePO> roles = authRoleDao.getRolesByUserId(userId);
        _log.info("请求菜单入参：用户Id：{}", userId);

        return roles;
    }

}