--liquibase formatted sql
--changeset itw_pangxl01:1
create table user (
id varchar(32) not null comment 'id'
);
--changeset itw_pangxl01:2
alter table user add column name varchar(32) comment '姓名';

