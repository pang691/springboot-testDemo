package com.taikang.health.iams.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "AccessTokenOut",description = "出参")
public class AccessTokenOut implements BO {

    private static final long serialVersionUID = 293289556241308990L;

    @ApiModelProperty(value = "客户端ID", example = "iams")
    private String clientId;
    @ApiModelProperty(value = "用户ID", example = "1001")
    private String userId;
    @ApiModelProperty(value = "访问token", example = "da94246ba96eeb495da8b199c102cbee")
    private String accessToken;
    @ApiModelProperty(value = "刷新token", example = "da94246ba96eeb495da8b199c102cbee")
    private String refreshToken;
    @ApiModelProperty(value = "访问token有效时长", example = "3600")
    private int expireIn;
    @ApiModelProperty(value = "刷新token有效时长", example = "7200")
    private int refreshTokenValidity;
    @ApiModelProperty(value = "创建时间", example = "2019-01-21 11:13:28")
    private Date createDate;
    @ApiModelProperty(value = "更新时间", example = "2019-01-21 11:13:28")
    private Date accessTokenUpdateTime;


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public int getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public void setRefreshTokenValidity(int refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    public Date getCreateDate() {
        if (this.createDate != null) {
            return (Date) this.createDate.clone();
        }
        return null;
    }

    public void setCreateDate(Date createDate) {
        if (createDate != null) {
            this.createDate = (Date) createDate.clone();
        } else {
            this.createDate = null;
        }
    }

    public Date getAccessTokenUpdateTime() {
        if (this.accessTokenUpdateTime != null) {
            return (Date) this.accessTokenUpdateTime.clone();
        }
        return null;
    }

    public void setAccessTokenUpdateTime(Date accessTokenUpdateTime) {
        if (accessTokenUpdateTime != null) {
            this.accessTokenUpdateTime = (Date) accessTokenUpdateTime.clone();
        } else {
            this.accessTokenUpdateTime = null;
        }
    }
}