package com.taikang.health.iams.dao;

import com.taikang.health.iams.po.AutzPermissionPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AutzPermissionDao {

    List<AutzPermissionPO> listPermission(String userId);
}