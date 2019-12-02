package com.taikang.test.scheduled.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;


@Slf4j
public class MyJob extends AbstractJob {
    //@Autowired
    //private MyService myService;
 
    @Override
    public void executeTask() {
        log.info("任务开始执行了");
        this.service();
        log.info("任务执行结束了");
    }
    @Async
    public void service() {
        System.out.println("啦啦啦啦啦啦啦啦");
    }
}