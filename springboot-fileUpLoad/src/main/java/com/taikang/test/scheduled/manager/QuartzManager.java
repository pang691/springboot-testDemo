package com.taikang.test.scheduled.manager;

import com.taikang.test.scheduled.bo.QuartzBo;
import com.taikang.test.scheduled.service.IQuartzService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class QuartzManager {
 
    /**
     * 任务调度
     */
    @Autowired
    private Scheduler scheduler;
    @Autowired
    private IQuartzService quartzService;

    /**
     * 开始执行定时任务
     */
    public void startJob() throws SchedulerException {
//        List<QuartzBo> list = new ArrayList<>();
//        QuartzBo bo = new QuartzBo();
//        bo.setJobName("com.taikang.common.quartz.job.MyJob");
//        //bo.setJobGroup("1");
//        bo.setJobCron("*/5 * * * * ?");
//        QuartzBo bo2 = new QuartzBo();
//        bo2.setJobName("com.taikang.common.quartz.job.MyJob2");
//        //bo2.setJobGroup("2");
//        bo2.setJobCron("*/10 * * * * ?");
//        list.add(bo);
//        list.add(bo2);
        List<QuartzBo> listBo = null;
        Map<String,Object> map = new HashMap<>();
        map.put("isOpen","1");
        try {
            listBo = quartzService.queryQuartzBoAll(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(null != listBo && listBo.size()>0){
            for (QuartzBo bb : listBo) {
                startJobTask(scheduler,bb);
            }
        }
        scheduler.start();
    }
 
    /**
     * 启动定时任务
     * @param scheduler
     */
    private void startJobTask(Scheduler scheduler , QuartzBo bb) throws SchedulerException {
        Class classs = null;
        try{
            classs = Class.forName(bb.getClassName());
        }catch (Exception e){
            e.printStackTrace();
        }
        JobDetail jobDetail= JobBuilder.newJob(classs).withIdentity(bb.getClassName()).build();
        CronScheduleBuilder cronScheduleBuilder=CronScheduleBuilder.cronSchedule(bb.getJobCron());
        CronTrigger cronTrigger=TriggerBuilder.newTrigger().withIdentity(bb.getClassName())
                .withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
 
    }
    public void startJob1(String name,String cron) throws SchedulerException {
        Class classs = null;
        try{
            classs = Class.forName(name);
        }catch (Exception e){

        }
        // 通过JobBuilder构建JobDetail实例，JobDetail规定只能是实现Job接口的实例
        // JobDetail 是具体Job实例
        JobDetail jobDetail = JobBuilder.newJob(classs).withIdentity(name).build();
        // 基于表达式构建触发器
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
        // CronTrigger表达式触发器 继承于Trigger
        // TriggerBuilder 用于构建触发器实例
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(name)
                .withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }
    /**
     * 获取Job信息
     * @param name
     * @param group
     */
    public String getJobInfo(String name,String group) throws SchedulerException {
        TriggerKey triggerKey=new TriggerKey(name,group);
        CronTrigger cronTrigger= (CronTrigger) scheduler.getTrigger(triggerKey);
        return String.format("time:%s,state:%s",cronTrigger.getCronExpression(),
                scheduler.getTriggerState(triggerKey).name());
    }
/**
     * 修改任务的执行时间
     * @param name
     * @param cron cron表达式
     * @return
     * @throws SchedulerException
     */
    public boolean modifyJob(String name,String cron) throws SchedulerException{
        Date date=null;
        TriggerKey triggerKey=new TriggerKey(name);
        CronTrigger cronTrigger= (CronTrigger) scheduler.getTrigger(triggerKey);
        String oldTime=cronTrigger.getCronExpression();
        if (!oldTime.equalsIgnoreCase(cron)){
            CronScheduleBuilder cronScheduleBuilder=CronScheduleBuilder.cronSchedule(cron);
            CronTrigger trigger=TriggerBuilder.newTrigger().withIdentity(name)
                    .withSchedule(cronScheduleBuilder).build();
            date=scheduler.rescheduleJob(triggerKey,trigger);
        }
        return date !=null;
    }


    /**
     * 暂停所有任务
     * @throws SchedulerException
     */
    public void pauseAllJob()throws SchedulerException{
        scheduler.pauseAll();
    }
 
    /**
     * 暂停某个任务
     * @param name
     * @param group
     * @throws SchedulerException
     */
    public void pauseJob(String name,String group)throws SchedulerException{
        JobKey jobKey=new JobKey(name,group);
        JobDetail jobDetail=scheduler.getJobDetail(jobKey);
        if (jobDetail==null)
            return;
        scheduler.pauseJob(jobKey);
    }
 
    /**
     * 恢复所有任务
     * @throws SchedulerException
     */
    public void resumeAllJob()throws SchedulerException{
        scheduler.resumeAll();
    }
    /**
     * 恢复某个任务
     */
    public void resumeJob(String name,String group)throws SchedulerException{
        JobKey jobKey=new JobKey(name,group);
        JobDetail jobDetail=scheduler.getJobDetail(jobKey);
        if (jobDetail==null)
            return;
        scheduler.resumeJob(jobKey);
    }
 
    /**
     * 删除某个任务
     * @param name
     * @throws SchedulerException
     */
    public void deleteJob(String name)throws SchedulerException{
        JobKey jobKey=new JobKey(name);
        JobDetail jobDetail=scheduler.getJobDetail(jobKey);
        if (jobDetail==null)
            return;
        scheduler.deleteJob(jobKey);
    }


    /**
     * 查询当前任务的状态
     */
    public void queryJobStatus(String name) throws SchedulerException {
        JobKey jobKey=new JobKey(name);

        TriggerKey triggerKey = TriggerKey.triggerKey(name);
        Trigger.TriggerState triggerState = scheduler.getTriggerState(triggerKey);
        System.out.println(triggerState);
    }


}