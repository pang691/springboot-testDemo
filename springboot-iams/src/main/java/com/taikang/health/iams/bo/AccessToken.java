package com.taikang.health.iams.bo;

import java.util.Date;
import java.util.Objects;

public class AccessToken extends AccessTokenModel {

    private static final long serialVersionUID = 3501707234733774035L;

    public static AccessToken build() {
        return new AccessToken();
    }

    /**
     * 全局刷新token有效性
     */
    private int refreshTokenValidity;

    /**
     * accesstoken更新时间
     */
    private Date accessTokenUpdateTime;


    /**
     * 是否为有效 token
     */
    public boolean isValidToken() {
        return getAccessTokenLeftTime() > 0L;
    }


    public int getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public AccessToken setRefreshTokenValidity(int tokenValidity) {
        this.refreshTokenValidity = tokenValidity;
        return this;
    }

    /**
     * 判断refreshToken剩余时间
     */
    public long getRefreshTokenLeftTime() {
        return this.getRefreshTokenValidity() < 0 ? 1L : (long) this.getRefreshTokenValidity() - (System.currentTimeMillis() - this.getCreateDate().getTime()) / 1000L;
    }

    public long getAccessTokenLeftTime() {
        return this.getExpireIn() < 0 ? 1L : (long) this.getExpireIn() - (System.currentTimeMillis() - this.getAccessTokenUpdateTime().getTime()) / 1000L;
    }

    public Date getAccessTokenUpdateTime() {
        if (accessTokenUpdateTime != null) {
            return new Date(accessTokenUpdateTime.getTime());
        } else {
            return null;
        }
    }

    public AccessToken setAccessTokenUpdateTime(Date accessTokenUpdateTime) {
        if (accessTokenUpdateTime != null) {
            this.accessTokenUpdateTime = new Date(accessTokenUpdateTime.getTime());
            return this;
        } else {
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AccessToken token = (AccessToken) o;
        return refreshTokenValidity == token.refreshTokenValidity &&
                Objects.equals(accessTokenUpdateTime, token.accessTokenUpdateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), refreshTokenValidity, accessTokenUpdateTime);
    }
}