package com.taikang.health.iams.service.impl;

import com.taikang.health.iams.service.AutzRoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutzRoleResourceServiceImpl extends CrudServiceSupport implements AutzRoleResourceService {

    @Autowired
	private AutzRoleResourceDao autzRoleResourceDao;

	@Override
	public AutzRoleResourceDao getDefaultDao() {
		return autzRoleResourceDao;
	}

}