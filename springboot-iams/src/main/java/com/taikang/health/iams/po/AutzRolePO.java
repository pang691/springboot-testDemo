package com.taikang.health.iams.po;

import com.google.common.base.Objects;
import com.taikang.health.iams.bo.BasePO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

public class AutzRolePO extends BasePO {

	private static final long serialVersionUID = 4066692514093585496L;

	/*-------------------------------------------
         |                属性名常量               |
        ===========================================*/
    public static class Property{
        private Property() {}
	    // 角色名称
	    public static final String roleName = "roleName";
	    // 角色CODE
	    public static final String roleCode = "roleCode";
		//状态
		public static final String status = "status";
		// 创建时间
		public static final String createTime = "createTime";
		//更新时间
		public static final String updateTime = "updateTime";

    }

	// 角色名称
	private String roleName;
	// 角色CODE
	private String roleCode;

	private int status;

	private Date createTime;

	private Date updateTime;


	//构造方法
	public AutzRolePO() {
		super();
	}

	public String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static AutzRolePO build(){
	   return new AutzRolePO();
	}

    //属性读写
	public String getRoleName() {
		return roleName;
	}

	public AutzRolePO setRoleName(String roleName) {
		this.roleName = roleName;
		return this;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public AutzRolePO setRoleCode(String roleCode) {
		this.roleCode = roleCode;
		return this;
	}

	public int getStatus() {
		return status;
	}

	public AutzRolePO setStatus(int status) {
		this.status = status;
		return  this;
	}

	public Date getUpdateTime() {
		if(updateTime !=null){
			return new Date(updateTime.getTime());
		}else{
			return null;
		}
	}

	public AutzRolePO setUpdateTime(Date updateTime) {
		if (updateTime != null){
			this.updateTime = new Date(updateTime.getTime());
			return this;
		}else{
			return this;
		}
	}

	public Date getCreateTime() {
		if(createTime!=null){
			return  new Date(createTime.getTime());
		}else{
			return null;
		}
	}

	public AutzRolePO setCreateTime(Date createTime) {
		if (createTime != null){
			this.createTime = new Date(createTime.getTime());
			return this;
		}else{
			return this;
		}
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) {return true;}
		if (!(o instanceof AutzRolePO)){ return false;}
		if (!super.equals(o)) {return false;}
		AutzRolePO that = (AutzRolePO) o;
		return status == that.status &&
				Objects.equal(roleName, that.roleName) &&
				Objects.equal(roleCode, that.roleCode) &&
				Objects.equal(createTime, that.createTime) &&
				Objects.equal(updateTime, that.updateTime);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(super.hashCode(), roleName, roleCode, status, createTime, updateTime);
	}

	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this , ToStringStyle.SHORT_PREFIX_STYLE );
    }

}