package com.taikang.test.scheduled.listener;

import com.taikang.test.scheduled.manager.QuartzManager;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Set;

/**
 * @author pangxiaolaing
 */

@Configuration
public class ApplicationStartQuartzJobListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationStartQuartzJobListener.class);
    @Autowired
    private QuartzManager quartzManager;

    @Autowired
    private Scheduler scheduler;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            reFefreshAllJobs();
            quartzManager.startJob();
            logger.info("任务已经启动......");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 刷新
     *
     * 项目启动前启动所有的Job  或者重启所有的Job
     */
    private void reFefreshAllJobs() throws SchedulerException {
        synchronized (logger){
            Set<JobKey> jobKeys = this.scheduler.getJobKeys(GroupMatcher.anyGroup());
            this.scheduler.pauseJobs(GroupMatcher.anyGroup());
            /**
             * 暂停所有的Job
             * 删除数据库中注册的所有Job
             */
            for (JobKey jobKey:jobKeys) {
                this.scheduler.unscheduleJob(TriggerKey.triggerKey(jobKey.getName()));
                this.scheduler.deleteJob(jobKey);
            }
        }
    }

}
 

