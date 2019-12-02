--liquibase formatted sql
--changeset pangxl01:1.0.0

/*
 Date: 17/07/2019 15:36:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

/* 先删除 */

DROP TABLE IF EXISTS `autz_permission`;
DROP TABLE IF EXISTS `autz_resource`;
DROP TABLE IF EXISTS `autz_role`;
DROP TABLE IF EXISTS `autz_role_resource`;
DROP TABLE IF EXISTS `autz_setting`;
DROP TABLE IF EXISTS `autz_setting_permission`;
DROP TABLE IF EXISTS `autz_user`;
DROP TABLE IF EXISTS `autz_user_role`;
DROP TABLE IF EXISTS `security_access`;
DROP TABLE IF EXISTS `security_client`;
DROP TABLE IF EXISTS `security_audit_logger`;
DROP TABLE IF EXISTS `unique_machineid`;


/*Table structure for table `autz_permission` */

CREATE TABLE `autz_permission` (
  `PERMISSION_ID` varchar(32) NOT NULL,
  `MODULE` varchar(200) DEFAULT NULL,
  `URI` varchar(200) DEFAULT NULL,
  `REMARK` varchar(200) DEFAULT NULL,
  `PRIORITY` varchar(32) DEFAULT NULL,
  `ENABLE` varchar(1) DEFAULT '1',
  PRIMARY KEY (`PERMISSION_ID`)
) ENGINE=InnoDB;


/*Table structure for table `autz_resource` */

CREATE TABLE `autz_resource` (
  `resource_id` varchar(32) NOT NULL COMMENT '资源表ID',
  `resource_code` varchar(32) DEFAULT NULL COMMENT '资源名称',
  `resource_name` varchar(64) DEFAULT NULL COMMENT '资源描述',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父资源编码菜单',
  `uri` varchar(100) DEFAULT NULL COMMENT '访问地址URL',
  `type` varchar(10) DEFAULT NULL COMMENT '类型(1.菜单menu 2.资源element(rest-api) 3.资源分类)',
  `status` varchar(10) DEFAULT NULL COMMENT '状态(1.正常 2.禁用)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `module` varchar(100) DEFAULT NULL COMMENT '模块',
  PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB COMMENT='资源信息表';


/*Table structure for table `autz_role` */

CREATE TABLE `autz_role` (
  `role_id` varchar(32) NOT NULL COMMENT '角色表ID',
  `role_name` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(32) DEFAULT NULL COMMENT '角色CODE',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` smallint(6) DEFAULT NULL COMMENT '状态(0：删除 1：正常)',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB COMMENT='角色表';


/*Table structure for table `autz_role_resource` */

CREATE TABLE `autz_role_resource` (
  `role_resource_id` varchar(32) NOT NULL COMMENT '权限设置中间表ID',
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色表ID',
  `resource_id` varchar(32) DEFAULT NULL COMMENT '资源表ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_resource_id`),
  KEY `fk_reference_7` (`role_id`),
  KEY `fk_reference_8` (`resource_id`),
  CONSTRAINT `fk_reference_7` FOREIGN KEY (`role_id`) REFERENCES `autz_role` (`role_id`),
  CONSTRAINT `fk_reference_8` FOREIGN KEY (`resource_id`) REFERENCES `autz_resource` (`resource_id`)
) ENGINE=InnoDB COMMENT='角色资源设置中间表';


/*Table structure for table `autz_setting` */

CREATE TABLE `autz_setting` (
  `SETTING_ID` varchar(32) NOT NULL,
  `TYPE` varchar(100) DEFAULT NULL,
  `SETTING_FOR` varchar(100) DEFAULT NULL,
  `REMARK` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`SETTING_ID`)
) ENGINE=InnoDB;


/*Table structure for table `autz_setting_permission` */

CREATE TABLE `autz_setting_permission` (
  `SETTING_PERMISSION_ID` varchar(32) NOT NULL,
  `SETTING_ID` varchar(32) DEFAULT NULL,
  `PERMISSION_ID` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`SETTING_PERMISSION_ID`)
) ENGINE=InnoDB;

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

/*Data for the table `security_client` */

insert  into `security_client`(`client_id`,`name`,`secret`,`remark`,`status`) values ('pdms','pdms','pdms',NULL,'1');
insert  into `security_client`(`client_id`,`name`,`secret`,`remark`,`status`) values ('jbt_admin','jbt','7dab05683d31cda2af508a5d42c60d9d',NULL,'1');
insert  into `security_client`(`client_id`,`name`,`secret`,`remark`,`status`) values ('ship_admin','ship','23402d775bfe9fd82b4334cd1bed2a43','','1');
insert  into `security_client`(`client_id`,`name`,`secret`,`remark`,`status`) values ('tkre','tkre','c8e3fa8f7f54a6938c894dae9e2adadc',NULL,'1');

alter table `security_client` add column client_type varchar(1) not null default '1' COMMENT '客户端类型：1:usertoken 2:apitoken';

--changeset pangxl01:1.0.0.2

/* 唯一索引*/
create unique index idx_uni_username        on autz_user(username);
create unique index idx_uni_rolecode        on autz_role(role_code);
create unique index idx_uni_userid_roleid   on autz_user_role(user_id,role_id);
create unique index idx_uni_clientid        on security_client(client_id);


ALTER TABLE autz_user_role MODIFY user_id varchar(32) not null COMMENT '用户ID';

ALTER TABLE security_audit_logger
  CHANGE response_content   response_content    blob NULL COMMENT '响应内容',
  CHANGE request_param      request_param       blob NULL COMMENT '请求参数',
  CHANGE exception_info     exception_info      blob null COMMENT '异常信息';

--changeset pangxl01:1.0.0.2
DROP TABLE IF EXISTS `autz_user_client`;

ALTER TABLE security_audit_logger
  CHANGE user_agent         user_agent          blob NULL COMMENT '代理人',
  CHANGE request_header     request_header      blob NULL COMMENT '请求头';

CREATE TABLE `autz_user_client` (
  `user_client_id` varchar(32) NOT NULL COMMENT '用户客户端关联表ID',
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `client_id` varchar(32) DEFAULT NULL COMMENT '客户端表ID',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_client_id`)
) ENGINE=InnoDB;


/* 唯一索引*/
create unique index idx_uni_userid_clientid on autz_user_client(user_id,client_id);

COMMIT;