--liquibase formatted sql
--changeset itw_pangxl01:1
create table user (
id varchar(32) not null comment 'id'
);
