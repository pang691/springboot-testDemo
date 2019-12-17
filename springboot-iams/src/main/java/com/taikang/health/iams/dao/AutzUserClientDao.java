package com.taikang.health.iams.dao;

import com.taikang.health.iams.po.AutzUserClientPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AutzUserClientDao {

    void insertBatch(List<AutzUserClientPO> list);

}