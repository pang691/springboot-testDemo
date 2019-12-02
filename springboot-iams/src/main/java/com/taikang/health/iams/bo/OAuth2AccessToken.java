package com.taikang.health.iams.bo;

import com.google.common.base.Objects;

import java.util.Date;

public class OAuth2AccessToken extends AccessTokenModel {

    private static final long serialVersionUID = 3501707234733774035L;

    public static OAuth2AccessToken build() {
        return new OAuth2AccessToken();
    }

    /**
     * 全局刷新token有效性
     */
    private int refreshTokenValidity;

    /**
     * accesstoken更新时间
     */
    private Date accessTokenUpdateTime;

    public int getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public OAuth2AccessToken setRefreshTokenValidity(int tokenValidity) {
        this.refreshTokenValidity = tokenValidity;
        return this;
    }

    public Date getAccessTokenUpdateTime() {
        if (accessTokenUpdateTime != null) {
            //正确值
            return new Date(accessTokenUpdateTime.getTime());
        } else {
            return null;
        }
    }

    public OAuth2AccessToken setAccessTokenUpdateTime(Date accessTokenUpdateTime) {
        if (accessTokenUpdateTime != null) {
            this.accessTokenUpdateTime = new Date(accessTokenUpdateTime.getTime());
            return this;
        } else {
            return this;
        }
    }


    /**
     * 判断refresh Token剩余时间
     */
    public long getRefreshTokenLeftTime() {
        return this.getRefreshTokenValidity() < 0 ? 1L : (long) this.getRefreshTokenValidity() - (System.currentTimeMillis() - this.getCreateDate().getTime()) / 1000L;
    }

    /**
     * 判断Access Token剩余时间
     */
    public long getAccessTokenLeftTime() {
        return this.getExpireIn() < 0 ? 1L : (long) this.getExpireIn() - (System.currentTimeMillis() - this.getAccessTokenUpdateTime().getTime()) / 1000L;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OAuth2AccessToken)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        OAuth2AccessToken that = (OAuth2AccessToken) o;
        return refreshTokenValidity == that.refreshTokenValidity &&
                Objects.equal(accessTokenUpdateTime, that.accessTokenUpdateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), refreshTokenValidity, accessTokenUpdateTime);
    }


}