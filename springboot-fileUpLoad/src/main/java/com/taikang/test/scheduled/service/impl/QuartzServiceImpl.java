package com.taikang.test.scheduled.service.impl;

import com.taikang.test.scheduled.bo.QuartzBo;
import com.taikang.test.scheduled.config.PagerResult;
import com.taikang.test.scheduled.dao.QuartzDao;
import com.taikang.test.scheduled.manager.QuartzManager;
import com.taikang.test.scheduled.service.IQuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
@Service("quartzService")
public class QuartzServiceImpl implements IQuartzService {
    @Autowired
    private QuartzDao quartzDao;
    @Autowired
    private QuartzManager quartzManager;
    //@Transactional(rollbackFor = Exception.class)
    public int addJob(QuartzBo bo) throws Exception {
        try {

            int result = quartzDao.addJob(bo);
            if (result > 0) {
                quartzManager.startJob1(bo.getClassName(), bo.getJobCron());
            }
            return result;
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    //@Transactional(rollbackFor = Exception.class)
    public int modifyJob(QuartzBo bo) throws Exception {
        try {
            int result = quartzDao.modifyJob(bo);
            if (result > 0) {
                quartzManager.modifyJob(bo.getClassName(), bo.getJobCron());
            }
            return result;
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    //@Transactional(rollbackFor = Exception.class)
    public int removeJob(String id)  throws Exception {
        QuartzBo bo = getQuartzBoById(id);
        try {
            quartzDao.removeJob(id);
            quartzManager.deleteJob(bo.getClassName());
            return 1;
        }catch (Exception e){
            throw new Exception(e);
        }
    }
    //@Transactional(rollbackFor = Exception.class)
    public String closeJob(String id,Integer state)  throws Exception {
        QuartzBo bo = getQuartzBoById(id);
        String message = "";
        try {
            quartzDao.modifyJobState(id,state);
            if(state==1){
                quartzManager.startJob1(bo.getClassName(),bo.getJobCron());
                message = "任务开启成功";
            }else{
                quartzManager.deleteJob(bo.getClassName());
                message = "任务关闭成功";
            }
            return message;
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("任务已经开启成功，不能重复开启");
        }

    }

    @Override
    public List<QuartzBo> queryQuartzBoAll(Map<String, Object> map) {
        return quartzDao.queryQuartzBoAll(map);
    }

    @Override
    public PagerResult<QuartzBo> queryQuartzBoList(QuartzBo bo) {
        List<QuartzBo> list = quartzDao.queryQuartzBoList(bo);
        //bo.setTotal(list.size());
        return PagerResult.build(bo.getPager(), list);
    }


    @Override
    public QuartzBo getQuartzBoById(String id) {
        return quartzDao.getQuartzBoById(id);
    }
}
