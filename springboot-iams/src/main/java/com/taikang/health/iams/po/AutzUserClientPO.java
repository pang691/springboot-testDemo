package com.taikang.health.iams.po;

import com.google.common.base.Objects;
import com.taikang.health.iams.bo.BasePO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

public class AutzUserClientPO extends BasePO {

    private static final long serialVersionUID = 2821079601141071197L;

    // 用户ID
    private String userId;
    // 角色表ID
    private String clientId;
    // 创建时间
    private Date createTime;
    // 更新时间
    private Date updateTime;

    private String createBy;
    private String updateBy;

    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    //构造方法
    public AutzUserClientPO() {
        super();
    }

    public static AutzUserClientPO build() {
        return new AutzUserClientPO();
    }

    //属性读写

    public String getCreateBy() {
        return this.createBy;
    }

    public AutzUserClientPO setCreateBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public String getUpdateBy() {
        return this.updateBy;
    }

    public AutzUserClientPO setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public AutzUserClientPO setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getClientId() {
        return clientId;
    }

    public AutzUserClientPO setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }


    public Date getCreateTime() {
        if (createTime != null) {
            return new Date(createTime.getTime());
        } else {
            return null;
        }

    }

    public AutzUserClientPO setCreateTime(Date createTime) {
        if (createTime != null) {
            this.createTime = new Date(createTime.getTime());
            return this;
        } else {
            return this;
        }
    }

    public Date getUpdateTime() {
        if (updateTime != null) {
            return new Date(updateTime.getTime());
        } else {
            return null;
        }
    }

    public AutzUserClientPO setUpdateTime(Date updateTime) {
        if (updateTime != null) {
            this.updateTime = new Date(updateTime.getTime());
            return this;
        } else {
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AutzUserClientPO)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        AutzUserClientPO that = (AutzUserClientPO) o;
        return Objects.equal(userId, that.userId) &&
                Objects.equal(clientId, that.clientId) &&
                Objects.equal(createTime, that.createTime) &&
                Objects.equal(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), userId, clientId, createTime, updateTime);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}