package com.taikang.health.iams.dao;

import com.taikang.health.iams.po.AutzUserClientPO;

import java.util.List;

public interface AutzUserClientDao {

    void insertBatch(List<AutzUserClientPO> list);

}