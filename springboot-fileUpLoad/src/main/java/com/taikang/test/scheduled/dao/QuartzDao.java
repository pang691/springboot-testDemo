package com.taikang.test.scheduled.dao;


import com.taikang.test.scheduled.bo.QuartzBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface QuartzDao {
    int addJob(QuartzBo bo);
    int modifyJob(QuartzBo bo);
    int removeJob(String id);
    List<QuartzBo> queryQuartzBoList(QuartzBo bo);
    QuartzBo getQuartzBoById(String id);
    int modifyJobState(@Param("id") String id, @Param("state") Integer state);

    List<QuartzBo> queryQuartzBoAll(Map<String, Object> map);
}