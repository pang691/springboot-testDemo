package com.taikang.health.iams.service.impl;

import com.taikang.health.iams.bo.AccessToken;
import com.taikang.health.iams.bo.UserRolePost;
import com.taikang.health.iams.dao.AutzUserDao;
import com.taikang.health.iams.po.AutzUserPO;
import com.taikang.health.iams.po.AutzUserRolePO;
import com.taikang.health.iams.service.AuthUserService;
import com.taikang.health.iams.service.AutzUserRoleService;
import com.taikang.health.iams.service.OAuth2Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthUserServiceImpl implements AuthUserService {


    private static Logger _log = LoggerFactory.getLogger(AuthUserServiceImpl.class);

    @Autowired
    private AutzUserDao autzUserDao;

    @Autowired
    private OAuth2Service oAuth2Service;

    @Autowired
    private AutzUserRoleService service;

    @Resource
    private FastJsonRedisTemplate fjr;

    @Override
    public AutzUserDao getDefaultDao() {
        return autzUserDao;
    }

    @Override
    public AutzUserPO selectByUsername(String username) {
        return autzUserDao.getByUserName(username);
    }

    @Override
    public AutzUserPO selectByUserId(String userId) {
        return autzUserDao.getByUserId(userId);
    }

    @Transactional
    @Override
    public List<String> addAutzUser(AutzUserPO[] autzUserPOS) {
        List<String> idList = new ArrayList<>();
        for (AutzUserPO autzUserPO : autzUserPOS) {
            if (autzUserPO != null) {
                String userId = autzUserPO.getId();
                AutzUserPO autzUserPO1 = selectByUserId(userId);
                if (autzUserPO1 == null) {
                    autzUserPO.setId(userId);
                    autzUserPO.setPassword("1234q!");
                    autzUserDao.addAutzUser(autzUserPO);
                    UserRolePost userRolePost = new UserRolePost();
                    userRolePost.setUserId(autzUserPO.getId());
                    userRolePost.setRoleId("4084603657160106");
                    service.insert(userRolePost);
                    idList.add(autzUserPO.getId());
                } else {
                    idList.add(autzUserPO1.getId());
                }
            } else {
                continue;
            }
        }
        return idList;
    }

    @Transactional
    @Override
    public boolean deleteAutzUser(List<AutzUserPO> list) {
        for (AutzUserPO autzUserPO : list) {
            if(autzUserPO != null){
                String id = autzUserPO.getId();
                String keyUserId = "auth:"+id;
                AutzUserPO byUserId = autzUserDao.getByUserId(id);
                String username = byUserId.getUsername();
                String keyUserName = "auth:"+username;
                String lockId = "userIdLocked:"+username;
                AccessToken accessTokenByUserId = oAuth2Service.getAccessTokenByUserId(id);
                if(accessTokenByUserId != null){
                    String accessToken = accessTokenByUserId.getAccessToken();
                    String token = "accessToken:"+accessToken;
                    String pdmsToken = "accessToken:pdms:"+id;
                    /**
                     * 调用清除缓存方法
                     */
                    fjr.delete(keyUserId);
                    fjr.delete(keyUserName);
                    fjr.delete(lockId);
                    fjr.delete(token);
                    fjr.delete(pdmsToken);
                }
                AutzUserRolePO po = new AutzUserRolePO();
                po.setUserId(id);
                service.deleteUserRole(po);
            }
        }
        return autzUserDao.deleteByUserId(list);
    }

    @Override
    public List<AutzUserPO> queryAutzUser(AutzUserPO autzUserPO) {
        List<AutzUserPO> list = autzUserDao.queryAutzUser(autzUserPO);
        return list;
    }

    @Override
    public AutzUserPO selectSingle(AutzUserPO queryUser) {
        return autzUserDao.selectSingle(queryUser);
    }
}