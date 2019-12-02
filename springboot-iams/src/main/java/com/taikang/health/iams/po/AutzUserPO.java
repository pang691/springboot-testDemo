package com.taikang.health.iams.po;

import com.google.common.base.Objects;
import com.taikang.health.iams.bo.BasePO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

public class AutzUserPO extends BasePO {

    private static final long serialVersionUID = 5700470661402769717L;

    public static class Property{
        private Property() {}

        // 用户姓名
        public static final String name = "name";
        // 用户名
        public static final String username = "username";
        // 用户密码
        //public static final String password = "password";
        // 用户邮箱
        public static final String email = "email";
        // 最后登录IP
        public static final String lastLoginIp = "lastLoginIp";
        // 最后登录时间
        public static final String lastLoginTime = "lastLoginTime";
        // 是否有效（1：有效 0：无效）
        public static final String enable = "enable";
    }

    // 用户姓名
    private String name;
    // 用户名
    private String username;
    // 用户密码
    private String password;
    // 用户邮箱
    private String email;
    // 最后登录IP
    private String lastLoginIp;
    // 最后登录时间
    private Date lastLoginTime;
    // 是否有效（1：有效 0：无效）
    private int enable;

    //构造方法
    public AutzUserPO() {
        super();
    }

    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static AutzUserPO build(){
        return new AutzUserPO();
    }

    //属性读写
    public String getName() {
        return name;
    }

    public AutzUserPO setName(String name) {
        this.name = name;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AutzUserPO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AutzUserPO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AutzUserPO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public AutzUserPO setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
        return this;
    }

    public Date getLastLoginTime() {
        if (lastLoginTime != null) {
            return new Date(lastLoginTime.getTime());
        } else {
            return null;
        }
    }

    public AutzUserPO setLastLoginTime(Date lastLoginTime) {
        if (lastLoginTime != null){
            this.lastLoginTime = new Date(lastLoginTime.getTime());
            return this;
        }else{
            return this;
        }
    }

    public int getEnable() {
        return enable;
    }

    public AutzUserPO setEnable(int enable) {
        this.enable = enable;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o){ return true;}
        if (!(o instanceof AutzUserPO)){ return false;}
        if (!super.equals(o)){ return false;}
        AutzUserPO that = (AutzUserPO) o;
        return enable == that.enable &&
                Objects.equal(name, that.name) &&
                Objects.equal(username, that.username) &&
                Objects.equal(password, that.password) &&
                Objects.equal(email, that.email) &&
                Objects.equal(lastLoginIp, that.lastLoginIp) &&
                Objects.equal(lastLoginTime, that.lastLoginTime);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), name, username, password, email, lastLoginIp, lastLoginTime, enable);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this , ToStringStyle.SHORT_PREFIX_STYLE );
    }


}