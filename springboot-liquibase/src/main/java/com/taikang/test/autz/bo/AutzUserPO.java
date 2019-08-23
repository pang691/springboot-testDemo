package com.taikang.test.autz.bo;

import lombok.Data;

import java.util.Date;
@Data
public class AutzUserPO {
	// user_id
	private String userId;
	// 用户名
	private String userName;
	// 用户密码
	private String userPwd;
	// 是否有效（1：有效 0：无效）
	private String deptId;
	// dept_path_id
	private String deptPathId;
	// dept_path_name
	private String deptPathName;
	// description
	private String description;
	// is_enabled
	private String isEnabled;
	// 更新时间
	private Date updateTime;
	// 创建时间
	private Date createTime;
}