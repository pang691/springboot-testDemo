package com.taikang.health.iams.po;

import com.taikang.health.iams.bo.BasePO;

import java.util.Objects;

public class OAuth2Client extends BasePO {
    private static final long serialVersionUID = -2185766021222571606L;
    /**客户端名称 */
    private String name;
    /**密钥 */
    private String secret;
    /**备注 */
    private String remark;
    /**状态 */
    private int status;
    /**客户端类型  */
    private int clientType;

    /**
     * 获取 客户端名称
     * @return String 客户端名称
     */

    public String getName(){
        return this.name;
    }

    /**
     * 设置 客户端名称
     * @param name 客户端名称
     */
    public OAuth2Client setName(String name){
        this.name=name;
        return this;
    }
    /**
     * 获取 密钥
     * @return String 密钥
     */
    public String getSecret(){
        return this.secret;
    }

    /**
     * 设置 密钥
     * @param secret 密钥
     */
    public OAuth2Client setSecret(String secret){
        this.secret=secret;
        return this;
    }
    /**
     * 获取 备注
     * @return String 备注
     */
    public String getRemark(){
        return this.remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark){
        this.remark=remark;
    }
    /**
     * 获取 状态
     * @return int 状态
     */
    public int getStatus(){
        return this.status;
    }

    /**
     * 设置 状态
     * @param status 状态
     */
    public OAuth2Client setStatus(int status){
        this.status=status;
        return this;
    }

    /**
     * 获取 客户端类型
     * @return int 客户端类型
     */
    public int getClientType() {
        return this.clientType;
    }

    /**
     * 设置 客户端类型
     * @param clientType 客户端类型
     */
    public void setClientType(int clientType) {
        this.clientType = clientType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OAuth2Client that = (OAuth2Client) o;
        return status == that.status &&
                clientType == that.clientType &&
                Objects.equals(name, that.name) &&
                Objects.equals(secret, that.secret) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, secret, remark, status, clientType);
    }
}