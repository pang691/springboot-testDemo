package com.taikang.health.iams.dao;

import com.taikang.health.iams.po.OAuth2Access;

public interface OAuth2AccessDao {

    /**
     * 根据ID删除
     */
    int deleteById(String id);

    OAuth2Access selectByAccessToken(String accessToken);

    OAuth2Access selectByRefreshToken(String refreshToken);

    OAuth2Access selectByUserId(String userId);

    /**
     * 指定条件更新
     *
     * @param newToken 新数据对象
     * @param oldToken 旧数据对象
     * @return 结果
     * @author xuyb15
     * @date 2019/9/2 10:18
     */
    int updateByIdAndKeys(OAuth2Access newToken, OAuth2Access oldToken);

}