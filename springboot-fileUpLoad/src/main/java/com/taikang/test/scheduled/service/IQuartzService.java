package com.taikang.test.scheduled.service;

import com.taikang.test.scheduled.bo.QuartzBo;
import com.taikang.test.scheduled.config.PagerResult;

import java.util.List;
import java.util.Map;

public interface IQuartzService {

    /**
     * 添加定时任务
     * @param bo
     * @return
     * @throws Exception
     */
    int addJob(QuartzBo bo) throws Exception;

    /**
     * 修改定时任务
     * @param bo
     * @return
     * @throws Exception
     */
    int modifyJob(QuartzBo bo) throws Exception;

    /**
     * 删除定时任务
     * @param id
     * @return
     * @throws Exception
     */
    int removeJob(String id) throws Exception;

    /**
     * 查询定时任务集合
     * @param bo
     * @return
     * @throws Exception
     */
    PagerResult<QuartzBo> queryQuartzBoList(QuartzBo bo) throws Exception;

    /**
     * 通过id获取定时任务的信息
     * @param id
     * @return
     * @throws Exception
     */
    QuartzBo getQuartzBoById(String id) throws Exception;

    /**
     * 关闭定时任务
     * @param id
     * @param state
     * @return
     * @throws Exception
     */
    String closeJob(String id, Integer state) throws Exception;

    /**
     * 查询所有开启的定时任务
     * @param map
     * @return
     */
    List<QuartzBo> queryQuartzBoAll(Map<String, Object> map);

}