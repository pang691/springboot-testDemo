package com.taikang.health.iams.dao;

import com.taikang.health.iams.po.AutzRolePO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AutzRoleDao {
    List<AutzRolePO> getRolesByUserId(String userId);
}