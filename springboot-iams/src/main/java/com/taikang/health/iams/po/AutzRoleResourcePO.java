package com.taikang.health.iams.po;

import com.google.common.base.Objects;
import com.taikang.health.iams.bo.BasePO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

public class AutzRoleResourcePO extends BasePO {

	private static final long serialVersionUID = 1278483189163086375L;


	// 资源表主键
	private String resourceId;
	// 角色表ID
	private String roleId;
	// 创建时间
	private Date createTime;

	//构造方法
	public AutzRoleResourcePO() {
		super();
	}

	public String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static AutzRoleResourcePO build(){
	   return new AutzRoleResourcePO();
	}

    //属性读写
	public String getResourceId() {
		return resourceId;
	}

	public AutzRoleResourcePO setResourceId(String resourceId) {
		this.resourceId = resourceId;
		return this;
	}

	public String getRoleId() {
		return roleId;
	}

	public AutzRoleResourcePO setRoleId(String roleId) {
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

	public AutzRoleResourcePO setCreateTime(Date createTime) {
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
		if (!(o instanceof AutzRoleResourcePO)) {return false;}
		if (!super.equals(o)) {return false;}
		AutzRoleResourcePO that = (AutzRoleResourcePO) o;
		return Objects.equal(resourceId, that.resourceId) &&
				Objects.equal(roleId, that.roleId) &&
				Objects.equal(createTime, that.createTime);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(super.hashCode(), resourceId, roleId, createTime);
	}

	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this , ToStringStyle.SHORT_PREFIX_STYLE );
    }

}