package com.taikang.health.iams.bo;

import java.util.Date;

public class AccessTokenModel implements BO {
    private static final long serialVersionUID = -8095454408304146160L;
    private String id;
    private String clientId;
    private String userId;
    private String accessToken;
    private String refreshToken;
    private int expireIn;
    private Date createDate;

    public AccessTokenModel() {
    }

    public static AccessTokenModel build() {
        return new AccessTokenModel();
    }

    public String getId() {
        return this.id;
    }

    public AccessTokenModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getClientId() {
        return this.clientId;
    }

    public AccessTokenModel setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getUserId() {
        return this.userId;
    }

    public AccessTokenModel setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public AccessTokenModel setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public AccessTokenModel setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public int getExpireIn() {
        return this.expireIn;
    }

    public AccessTokenModel setExpireIn(int expireIn) {
        this.expireIn = expireIn;
        return this;
    }

    public Date getCreateDate() {
        return this.createDate == null ? null : (Date)this.createDate.clone();
    }

    public AccessTokenModel setCreateDate(Date createDate) {
        if (createDate == null) {
            this.createDate = null;
        } else {
            this.createDate = (Date)createDate.clone();
        }

        return this;
    }

    public long getLeftTime() {
        return this.getExpireIn() < 0 ? 1L : (long)this.getExpireIn() - (System.currentTimeMillis() - this.getCreateDate().getTime()) / 1000L;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}