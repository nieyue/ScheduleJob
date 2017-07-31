package com.nieyue.schedule;

import java.util.List;

import com.nieyue.bean.ScheduleJob;
/**
 * quartz事件接口
 * @author 聂跃
 * @date 2017年7月28日
 */
public interface QuartzEventService {
	/**新增定时任务  */  
	 public boolean addScheduleJob(ScheduleJob scheduleJob);
	 /**浏览list定时任务  */  
	 public List<ScheduleJob> list();
	 /**修改定时任务  */  
	 public boolean updateScheduleJob(ScheduleJob scheduleJob);
	 /**删除定时任务  */  
	  public boolean delScheduleJob(String jobName, String jobGroup);
	  /**暂停定时任务  */  
	  public boolean pauseScheduleJob(String jobName, String jobGroup);
	  /**恢复定时任务  */  
	  public boolean resumeScheduleJob(String jobName, String jobGroup);
	  /**暂停全部定时任务  */  
	  public boolean pauseAllScheduleJob();
	  /**恢复全部定时任务  */  
	  public boolean resumeAllScheduleJob();
	 
}
