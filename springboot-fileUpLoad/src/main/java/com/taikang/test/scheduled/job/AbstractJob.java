package com.taikang.test.scheduled.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public abstract class AbstractJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException  {
        executeTask();
    }

    /**
     * 需要写的业务逻辑
     */
    public abstract void executeTask();
}

