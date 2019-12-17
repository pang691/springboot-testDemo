package com.taikang.health.iams.dao;

import com.taikang.health.iams.po.AutzResourcePO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AutzResourceDao {
    List<AutzResourcePO> listResources(String userId);

    List<AutzResourcePO> roleResources(String roleId);

}