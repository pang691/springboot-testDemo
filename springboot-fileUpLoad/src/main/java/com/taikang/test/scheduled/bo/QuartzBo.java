package com.taikang.test.scheduled.bo;

import com.taikang.test.basebo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "quartzBo", description = "quartzBo")
public class QuartzBo extends BaseBO {
    @ApiModelProperty(value = "任务ID", example="1")
    private String id;
    @ApiModelProperty(value = "任务名称", example="insuranceCardJob")
    private String jobName;
    @ApiModelProperty(value = "任务全类名", example="com.taikang.health.timer.scheduled.job.InsuranceCardJob")
    private String className;
    @ApiModelProperty(value = "任务定时表达式", example="0/59 * * * * ?")
    private String jobCron;
    @ApiModelProperty(value = "任务状态", example="1")
    private Integer state;
    @ApiModelProperty(value = "任务创建时间")
    private Date createTime;
    @ApiModelProperty(value = "开启or关闭", example="1")
    private String isOpen = "1";

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }



    public String getJobCron() {
        return jobCron;
    }

    public void setJobCron(String jobCron) {
        this.jobCron = jobCron;
    }

    @Override
    public String toString() {
        return "QuartzBo{" +
                "id=" + id +
                ", jobName='" + jobName + '\'' +
                ", className='" + className + '\'' +
                ", jobCron='" + jobCron + '\'' +
                ", state=" + state +
                ", createTime=" + createTime +
                ", isOpen='" + isOpen + '\'' +
                '}';
    }
}

