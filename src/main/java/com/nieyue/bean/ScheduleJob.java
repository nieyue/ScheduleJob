package com.nieyue.bean;

import java.util.Date;

/**
 * 计划任务信息
 * @author 聂跃
 * @date 2017年7月28日
 */
public class ScheduleJob {
    /**
     * 计划Id
     */
    private Integer scheduleJobId;  
    /**
     * 创建时间
     */
    private Date createDate;  
    /**
     * 更新时间 
     */
    private Date updateDate;  
    /** 
     * 任务名称 
     */ 
    private String jobName;  
    /** 
     * 任务分组 
     */ 
    private String jobGroup;  
    /** 
     * 任务状态 是否启动任务 
     */
    private String jobStatus;  
    /** 
     * cron表达式 
     */  
    private String cronExpression;  
    /** 
     * 描述 
     */  
    private String description;
    /** 
     * 任务Id (传参)
     */ 
    private Integer jobId;
    /** 
     * 任务类型 (传参)
     */ 
    private Integer type;
    
    /** 
     * 任务调用的方法名 
     */ 
    private String methodName;
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobGroup() {
		return jobGroup;
	}
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	public String getCronExpression() {
		return cronExpression;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public ScheduleJob() {
		super();
	}
	public Integer getScheduleJobId() {
		return scheduleJobId;
	}
	public void setScheduleJobId(Integer scheduleJobId) {
		this.scheduleJobId = scheduleJobId;
	}
	public Integer getJobId() {
		return jobId;
	}
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public ScheduleJob(Integer scheduleJobId, Date createDate, Date updateDate, String jobName, String jobGroup,
			String jobStatus, String cronExpression, String description, Integer jobId, Integer type,
			String methodName) {
		super();
		this.scheduleJobId = scheduleJobId;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.jobName = jobName;
		this.jobGroup = jobGroup;
		this.jobStatus = jobStatus;
		this.cronExpression = cronExpression;
		this.description = description;
		this.jobId = jobId;
		this.type = type;
		this.methodName = methodName;
	}
	@Override
	public String toString() {
		return "ScheduleJob [scheduleJobId=" + scheduleJobId + ", createDate=" + createDate + ", updateDate="
				+ updateDate + ", jobName=" + jobName + ", jobGroup=" + jobGroup + ", jobStatus=" + jobStatus
				+ ", cronExpression=" + cronExpression + ", description=" + description + ", jobId=" + jobId + ", type="
				+ type + ", methodName=" + methodName + "]";
	}  
    
  
}
