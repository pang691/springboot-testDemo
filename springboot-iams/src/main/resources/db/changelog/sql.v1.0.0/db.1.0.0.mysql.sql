/*Table structure for table `autz_user` */

CREATE TABLE `autz_user` (
  `user_id` varchar(32) NOT NULL COMMENT '用户表ID',
  `name` varchar(64) DEFAULT NULL COMMENT '用户姓名',
  `username` varchar(100) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '用户密码',
  `email` varchar(100) DEFAULT NULL COMMENT '用户邮箱',
  `last_login_ip` varchar(100) DEFAULT NULL COMMENT '最后登录IP',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `enable` varchar(1) DEFAULT '1' COMMENT '是否有效（1：有效 0：无效）',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB COMMENT='用户表';


/*Table structure for table `autz_user_role` */

CREATE TABLE `autz_user_role` (
  `user_role_id` varchar(32) NOT NULL COMMENT '用户角色关联表ID',
  `user_id` int(32) DEFAULT NULL COMMENT '用户ID',
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色表ID',
  `remark` varchar(200) DEFAULT NULL COMMENT '说明',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_role_id`),
  KEY `fk_reference_3` (`user_id`) USING BTREE,
  KEY `fk_reference_6` (`role_id`) USING BTREE
) ENGINE=InnoDB ROW_FORMAT=COMPACT COMMENT='用户多权限配置表';


/*Table structure for table `security_access` */

CREATE TABLE `security_access` (
  `access_id` varchar(32) NOT NULL COMMENT '通道ID',
  `client_id` varchar(32) DEFAULT NULL COMMENT '客户端ID',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `access_token` varchar(100) DEFAULT NULL COMMENT '访问令牌',
  `refresh_token` varchar(100) DEFAULT NULL COMMENT '刷新令牌',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `expire_in` varchar(32) DEFAULT NULL COMMENT '访问令牌失效时间',
  `REFRESH_TOKEN_VALIDITY` int(11) DEFAULT NULL COMMENT 'refreshtoken有效时间',
  `ACCESS_TOKEN_UPDATE_TIME` datetime DEFAULT NULL COMMENT 'ACCESS更新时间',
  PRIMARY KEY (`access_id`)
) ENGINE=InnoDB COMMENT='OAUTH2 TOKEN(SECURITY_ACCESS)';


/*Table structure for table `security_client` */

CREATE TABLE `security_client` (
  `client_id` varchar(32) NOT NULL COMMENT '客户端ID',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `secret` varchar(100) DEFAULT NULL COMMENT '秘钥',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB COMMENT='OAUTH2客户端(SECURITY_CLIENT)';


/*Table structure for table `security_audit_logger` */

CREATE TABLE `security_audit_logger` (
  `audit_logger_id` varchar(32) NOT NULL COMMENT '审计表ID',
  `client_ip` varchar(32) DEFAULT NULL COMMENT '客户端IP',
  `request_uri` varchar(500) DEFAULT NULL COMMENT '请求URI',
  `request_url` varchar(500) DEFAULT NULL COMMENT '请求URL',
  `request_method` varchar(256) DEFAULT NULL COMMENT '请求方式',
  `referer` varchar(500) DEFAULT NULL COMMENT '请求来源',
  `user_agent` varchar(1000) DEFAULT NULL COMMENT '代理人',
  `request_header` varchar(1000) DEFAULT NULL COMMENT '请求头',
  `response_code` varchar(32) DEFAULT NULL COMMENT '响应码',
  `response_content` varchar(1000) DEFAULT NULL COMMENT '响应内容',
  `user_id` varchar(32) DEFAULT NULL COMMENT '客户ID',
  `class_name` varchar(500) DEFAULT NULL COMMENT '类名',
  `module_desc` varchar(500) DEFAULT NULL COMMENT '模块说明',
  `request_param` varchar(2000) DEFAULT NULL COMMENT '请求参数',
  `exception_info` varchar(5000) DEFAULT NULL COMMENT '异常信息',
  `request_time` varchar(20) DEFAULT NULL COMMENT '请求时间',
  `response_time` varchar(20) DEFAULT NULL COMMENT '响应时间',
  `use_time` int(11) DEFAULT NULL COMMENT '耗时',
  PRIMARY KEY (`audit_logger_id`)
) ENGINE=InnoDB COMMENT='日志审计表(SECURITY_AUDIT_LOGGER)';


/*Table structure for table `unique_machineid` */

CREATE TABLE `unique_machineid` (
  `machine_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，机器标识',
  `ip_port` varchar(32) DEFAULT NULL COMMENT 'ip:port',
  PRIMARY KEY (`machine_id`)
) ENGINE=InnoDB COMMENT='ID生成器使用的唯一机器标识';


/*================  data  ======= */
/*Data for the table `autz_user` */

insert  into `autz_user`(`user_id`,`name`,`username`,`password`,`email`,`last_login_ip`,`last_login_time`,`enable`) values
('0001','系统管理员','system_admin','1234q!',NULL,NULL,NULL,'1'),
('0002','空间管理','space_admin','1234q!',NULL,NULL,NULL,'1');

insert  into `autz_user`(`user_id`,`name`,`username`,`password`,`email`,`last_login_ip`,`last_login_time`,`enable`) values
('4084603657160704','匿名用户','anonymous','pass1234',NULL,NULL,NULL,'1'),
('4084603657160705','高端医疗险','ship','pass1234',NULL,NULL,NULL,'1'),
('4084603657160706','健保通','jbt','jbt',NULL,NULL,NULL,'1'),
('4084603657160707','规则引擎','tkre','pass1234',NULL,NULL,NULL,'1');

/*Data for the table `security_client` */

insert  into `security_client`(`client_id`,`name`,`secret`,`remark`,`status`) values ('pdms','pdms','pdms',NULL,'1');
insert  into `security_client`(`client_id`,`name`,`secret`,`remark`,`status`) values ('jbt_admin','jbt','7dab05683d31cda2af508a5d42c60d9d',NULL,'1');
insert  into `security_client`(`client_id`,`name`,`secret`,`remark`,`status`) values ('ship_admin','ship','23402d775bfe9fd82b4334cd1bed2a43','','1');
insert  into `security_client`(`client_id`,`name`,`secret`,`remark`,`status`) values ('tkre','tkre','c8e3fa8f7f54a6938c894dae9e2adadc',NULL,'1');

COMMIT;