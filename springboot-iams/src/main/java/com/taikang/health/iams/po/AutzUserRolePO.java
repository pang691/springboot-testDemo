package com.taikang.health.iams.po;

import com.google.common.base.Objects;
import com.taikang.health.iams.bo.BasePO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

public class AutzUserRolePO extends BasePO {

	private static final long serialVersionUID = 2821079601141071197L;

	/*-------------------------------------------
         |                属性名常量               |
        ===========================================*/
    public static class Property{
        private Property() {}
	    // 用户ID
	    public static final String userId = "userId";
	    // 角色表ID
	    public static final String roleId = "roleId";
	    // 创建时间
	    public static final String createTime = "createTime";
	    // 更新时间
	    public static final String updateTime = "updateTime";
    }

	// 用户ID
	private String userId;
	// 角色表ID
	private String roleId;
	// 创建时间
	private Date createTime;
	// 更新时间
	private Date updateTime;


	//构造方法
	public AutzUserRolePO() {
		super();
	}

	public String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static AutzUserRolePO build(){
	   return new AutzUserRolePO();
	}

    //属性读写
	public String getUserId() {
		return userId;
	}

	public AutzUserRolePO setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getRoleId() {
		return roleId;
	}

	public AutzUserRolePO setRoleId(String roleId) {
		this.roleId = roleId;
		return this;
	}


	public Date getCreateTime() {
		if (createTime != null) {
			return new Date(createTime.getTime());
		} else {
			return null;
		}

	}

	public AutzUserRolePO setCreateTime(Date createTime) {
		if (createTime != null){
			this.createTime = new Date(createTime.getTime());
			return this;
		}else{
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

	public AutzUserRolePO setUpdateTime(Date updateTime) {
		if (updateTime != null){
			this.updateTime = new Date(updateTime.getTime());
			return this;
		}else{
			return this;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o){ return true;}
		if (!(o instanceof AutzUserRolePO)){ return false;}
		if (!super.equals(o)) {return false;}
		AutzUserRolePO that = (AutzUserRolePO) o;
		return Objects.equal(userId, that.userId) &&
				Objects.equal(roleId, that.roleId) &&
				Objects.equal(createTime, that.createTime) &&
				Objects.equal(updateTime, that.updateTime);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(super.hashCode(), userId, roleId, createTime, updateTime);
	}

	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this , ToStringStyle.SHORT_PREFIX_STYLE );
    }

}