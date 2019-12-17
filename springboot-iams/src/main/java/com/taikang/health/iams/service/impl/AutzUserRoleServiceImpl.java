package com.taikang.health.iams.service.impl;

import com.taikang.health.iams.bo.UserRolePost;
import com.taikang.health.iams.dao.AutzUserRoleDao;
import com.taikang.health.iams.po.AutzUserRolePO;
import com.taikang.health.iams.service.AutzUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutzUserRoleServiceImpl implements AutzUserRoleService {

    @Autowired
	private AutzUserRoleDao autzUserRoleDao;


	@Override
	public void insertBatch(List<AutzUserRolePO> collection){
		autzUserRoleDao.insertBatch(collection);
	}
	@Override
	public int deleteUserRole(AutzUserRolePO po){
		return autzUserRoleDao.deleteUserRole(po);
	}

	@Override
	public void insertUserRolePost(UserRolePost userRolePost) {
		autzUserRoleDao.insertUserRolePost(userRolePost);
	}

}