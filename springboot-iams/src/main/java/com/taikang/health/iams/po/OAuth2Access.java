package com.taikang.health.iams.po;

import com.taikang.health.iams.bo.BasePO;

import java.util.Date;

public class OAuth2Access extends BasePO {

    private static final long serialVersionUID = -8094454408304146160L;
    //客户端ID
    private String clientId;
    //关联用户
    private String userId;
    //认证码
    private String accessToken;
    //刷新码
    private String refreshToken;
    //access_token过期时间
    private int expireIn;
    //生成日期
    private Date createDate;

    //refresh_token有效时长
    private int refreshTokenValidity;
    //access的更新时间
    private Date accessTokenUpdateTime;

    public Date getAccessTokenUpdateTime() {
        if (accessTokenUpdateTime != null) {
            return new Date(accessTokenUpdateTime.getTime());
        } else {
            return null;
        }
    }

    public void setAccessTokenUpdateTime(Date accessTokenUpdateTime) {
        if (accessTokenUpdateTime != null){
            this.accessTokenUpdateTime = new Date(accessTokenUpdateTime.getTime());
        }else{
            this.accessTokenUpdateTime =  null;
        }
    }


    /**
     * 获取 客户端ID
     *
     * @return String 客户端ID
     */
    public String getClientId() {
        return this.clientId;
    }

    /**
     * 设置 客户端ID
     *
     * @param clientId 客户端ID
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * 获取 关联用户
     *
     * @return String 关联用户
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * 设置 关联用户
     *
     * @param userId 关联用户
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取 认证码
     *
     * @return String 认证码
     */
    public String getAccessToken() {
        return this.accessToken;
    }

    /**
     * 设置 认证码
     *
     * @param accessToken 认证码
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }


    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public int getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public void setRefreshTokenValidity(int refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    /**
     * 获取 过期时间
     *
     * @return int 过期时间
     */
    public int getExpireIn() {
        return this.expireIn;
    }

    /**
     * 设置 过期时间
     *
     * @param expireIn 过期时间
     */
    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    /**
     * 获取 生成日期
     *
     * @return java.util.Date 生成日期
     */
    public Date getCreateDate() {
        if(createDate == null){
            return null;
        }
        return (Date) this.createDate.clone();
    }

    /**
     * 设置 生成日期
     *
     * @param createDate 生成日期
     */
    public void setCreateDate(Date createDate) {
        if(createDate == null){
            this.createDate = null;
        }else {
            this.createDate = (Date) createDate.clone();
        }
    }

    //access_token有效期剩余时间
    public long getLeftTime() {
        return getExpireIn() - (System.currentTimeMillis() - getCreateDate().getTime()) / 1000;
    }

    //refresh_token有效时长
    public long getRefreshTokenLeftTime() {
        return getRefreshTokenValidity() - (System.currentTimeMillis() - getCreateDate().getTime()) / 1000;
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}