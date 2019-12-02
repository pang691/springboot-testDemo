package com.taikang.health.iams.service.impl;

import com.taikang.health.iams.service.AutzUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutzUserRoleServiceImpl extends CrudServiceSupport implements AutzUserRoleService {

    @Autowired
	private AutzUserRoleDao autzUserRoleDao;

    @Autowired
	private AutzUserRoleAccess autzUserRoleAccess;

	@Override
	public AutzUserRoleDao getDefaultDao() {
		return autzUserRoleDao;
	}

	@Override
	public void insertBatch(List<AutzUserRolePO> collection){
		autzUserRoleDao.insertBatch(collection);
	}
	@Override
	public int deleteUserRole(AutzUserRolePO po){
		return autzUserRoleAccess.deleteUserRole(po);
	}

}