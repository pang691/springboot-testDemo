package com.taikang.health.iams.service.impl;

import com.taikang.health.iams.dao.AutzRoleResourceDao;
import com.taikang.health.iams.service.AutzRoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutzRoleResourceServiceImpl implements AutzRoleResourceService {

    @Autowired
	private AutzRoleResourceDao autzRoleResourceDao;

}