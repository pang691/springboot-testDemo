package com.taikang.health.iams.po;

import com.google.common.base.Objects;
import com.taikang.health.iams.bo.BasePO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

public class AutzResourcePO extends BasePO {

	private static final long serialVersionUID = -1475567997930626963L;

	/*-------------------------------------------
        |                属性名常量               |
       ===========================================*/
	public static class Property{
		private Property() {}
		// 资源编码
		public static final String resourceCode = "resourceCode";
		// 资源名称
		public static final String resourceName = "resourceName";
		// 模块名称
		public static final String module = "module";
		// 父资源id
		public static final String parentId = "parentId";
		// 资源路径
		public static final String uri = "uri";
		// 资源类型
		public static final String type = "type";
		// 是否有效
		public static final String status = "status";
		//创建时间
		public static final String createTime = "createTime";

		//更新时间
		public static final String updateTime = "updateTime";
	}

	// 资源编码
	private String resourceCode;

	// 资源名称
	private String resourceName;
	//模块名称
	private String module;
	// 父资源id
	private String parentId;

	//资源路径
	private String uri;
	//资源类型
	private int type;
	//状态
	private int status;
	//创建日期
	private Date createTime;
	//更新时间
	private Date updateTime;
	public String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	//构造方法
	public AutzResourcePO() {
		super();
	}


	public static AutzResourcePO build(){
		return new AutzResourcePO();
	}

	public Date getUpdateTime() {
		if (this.updateTime != null) {
			return new Date(this.updateTime.getTime());
		} else {
			return null;
		}
	}

	public AutzResourcePO setUpdateTime(Date updateTime) {
		if (updateTime != null){
			this.updateTime = new Date(updateTime.getTime());
			return this;
		}else{
			return this;
		}
	}

	public String getResourceCode() {
		return resourceCode;
	}

	public AutzResourcePO setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
		return this;
	}

	public String getResourceName() {
		return resourceName;
	}

	public AutzResourcePO setResourceName(String resourceName) {
		this.resourceName = resourceName;
		return this;
	}

	public String getModule() {
		return module;
	}

	public AutzResourcePO setModule(String module) {
		this.module = module;
		return  this;
	}

	public String getParentId() {
		return parentId;
	}

	public AutzResourcePO setParentId(String parentId) {
		this.parentId = parentId;
		return this;
	}

	public String getUri() {
		return uri;
	}

	public AutzResourcePO setUri(String uri) {
		this.uri = uri;
		return  this;
	}

	public int getType() {
		return type;
	}

	public AutzResourcePO setType(int type) {
		this.type = type;
		return  this;
	}

	public int getStatus() {
		return status;
	}

	public AutzResourcePO setStatus(int status) {
		this.status = status;
		return this;
	}

	public Date getCreateTime() {
		if (createTime != null) {
			return new Date(createTime.getTime());
		} else {
			return null;
		}
	}

	public AutzResourcePO setCreateTime(Date createTime) {
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
		if (!(o instanceof AutzResourcePO)){ return false;}
		if (!super.equals(o)){ return false;}
		AutzResourcePO that = (AutzResourcePO) o;
		return type == that.type &&
				status == that.status &&
				Objects.equal(resourceCode, that.resourceCode) &&
				Objects.equal(resourceName, that.resourceName) &&
				Objects.equal(module, that.module) &&
				Objects.equal(parentId, that.parentId) &&
				Objects.equal(uri, that.uri) &&
				Objects.equal(createTime, that.createTime) &&
				Objects.equal(updateTime, that.updateTime);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(super.hashCode(), resourceCode, resourceName, module, parentId, uri, type, status, createTime, updateTime);
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this , ToStringStyle.SHORT_PREFIX_STYLE );
	}

}