package com.nieyue.schedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.ScheduleJob;

/**
 * 
 * quartz增删改查实现
 * @author 聂跃
 * @date 2017年7月28日
 */
@Service
public class QuartzEventServiceImpl implements QuartzEventService {
  @Autowired
  private Scheduler scheduler;
  
 
  /** 
   * 所有任务列表 
   */  
  public List<ScheduleJob> list(){  
      List<ScheduleJob> list = new ArrayList<ScheduleJob>();  
     try {  
          for(String groupJob: scheduler.getJobGroupNames()){  
              for(JobKey jobKey: scheduler.getJobKeys(GroupMatcher.<JobKey>groupEquals(groupJob))){  
                  List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);  
                  for (Trigger trigger: triggers) {  
                      Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());  
                      JobDetail jobDetail = scheduler.getJobDetail(jobKey);  

                      String cronExpression = "";
                    		 Date createDate = new Date();  

                      if (trigger instanceof CronTrigger) {  
                          CronTrigger cronTrigger = (CronTrigger) trigger;  
                          cronExpression = cronTrigger.getCronExpression();  
                          createDate = cronTrigger.getStartTime(); 
                      }  
                      ScheduleJob info = new ScheduleJob();  
                      info.setJobName(jobKey.getName());  
                      info.setJobGroup(jobKey.getGroup());  
                      info.setDescription(jobDetail.getDescription());  
                      info.setJobStatus(triggerState.name());  
                      info.setCronExpression(cronExpression);  
                      info.setCreateDate(createDate);  
                      info.setJobId(jobDetail.getJobDataMap().getInt("jobId"));  
                      info.setType(jobDetail.getJobDataMap().getInt("type"));
                      info.setScheduleJobId(jobDetail.getJobDataMap().getInt("scheduleJobId"));
                      info.setMethodName(jobDetail.getJobDataMap().getString("methodName"));
                      info.setUpdateDate((Date)jobDetail.getJobDataMap().get("updateDate"));
                      info.setJobStatus(jobDetail.getJobDataMap().getString("jobStatus"));
                      list.add(info);  
                  }                     
              }  
          }             
      } catch (SchedulerException e) {  
      }  

      return list;  
  }
  /** 
   * 保存定时任务 
   * @param ScheduleJob 
   */  
  @Transactional(propagation=Propagation.REQUIRED)
   @Override
  public boolean addScheduleJob(ScheduleJob scheduleJob) {  
	  boolean b=false;
      String jobName = scheduleJob.getJobName(),   
             jobGroup = scheduleJob.getJobGroup(),   
             cronExpression =scheduleJob.getCronExpression(),  
             jobDescription = scheduleJob.getDescription();
             //createDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");  
      		Integer jobId = scheduleJob.getJobId();
      		Integer  type = scheduleJob.getType();   
      		String  methodName = scheduleJob.getMethodName();   
      		Integer  scheduleJobId = scheduleJob.getScheduleJobId(); 
      		Date  updateDate = scheduleJob.getUpdateDate();
      		 Date createDate = scheduleJob.getCreateDate();
      		String  jobStatus = scheduleJob.getJobStatus();
      try {  
          if (checkExists(jobName, jobGroup)) { 
        	  return b;
          }

          TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);  
          JobKey jobKey = JobKey.jobKey(jobName, jobGroup);  
          CronScheduleBuilder schedBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();  
          CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withDescription(String.valueOf(createDate.getTime())).withSchedule(schedBuilder).build();  


          @SuppressWarnings("unchecked")
		Class<? extends Job> clazz = (Class<? extends Job>)Class.forName(jobName); 
          JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(jobKey).withDescription(jobDescription).build();  
          jobDetail.getJobDataMap().put("jobId", jobId);
          jobDetail.getJobDataMap().put("type", type);
          jobDetail.getJobDataMap().put("methodName", methodName);
          jobDetail.getJobDataMap().put("scheduleJobId", scheduleJobId);
          jobDetail.getJobDataMap().put("updateDate", updateDate);
          jobDetail.getJobDataMap().put("jobStatus", jobStatus);
          scheduler.scheduleJob(jobDetail, trigger); 
          if(jobStatus.equals("PAUSED")){
   			 scheduler.pauseTrigger(triggerKey);  
 			  scheduler.pauseJob(new JobKey(jobName,jobGroup));
           }
   		 if(jobStatus.equals("NORMAL")){
   			 scheduler.resumeTrigger(triggerKey);  
 			 scheduler.resumeJob(new JobKey(jobName,jobGroup));  
   		 }
          b=true;
      } catch (SchedulerException | ClassNotFoundException e) {  
      }
      return b; 
  }
  
  /** 
   * 修改定时任务 
   * @param ScheduleJob 
   */  
   @Transactional(propagation=Propagation.REQUIRED)
   @Override
  public boolean updateScheduleJob(ScheduleJob scheduleJob) {
	   boolean b=false;
      String jobName = scheduleJob.getJobName(),   
             jobGroup = scheduleJob.getJobGroup(),   
             cronExpression = scheduleJob.getCronExpression(),  
             jobDescription = scheduleJob.getDescription(); 
             Date createDate = scheduleJob.getCreateDate();
             Integer jobId = scheduleJob.getJobId();
      		Integer  type = scheduleJob.getType();   
      		String  methodName = scheduleJob.getMethodName();   
      		Integer  scheduleJobId = scheduleJob.getScheduleJobId(); 
      		Date  updateDate = scheduleJob.getUpdateDate();
      		String  jobStatus = scheduleJob.getJobStatus();
      try {  
          if (!checkExists(jobName, jobGroup)) {  
             // throw new ServiceException(String.format("Job不存在, jobName:{%s},jobGroup:{%s}", jobName, jobGroup));  
          return b;
          }  
          TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);  
          JobKey jobKey = new JobKey(jobName, jobGroup);  
          CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();  
          CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withDescription(String.valueOf(createDate.getTime())).withSchedule(cronScheduleBuilder).build();  

          JobDetail jobDetail = scheduler.getJobDetail(jobKey); 
          jobDetail.getJobDataMap().put("jobId", jobId);
          jobDetail.getJobDataMap().put("type", type);
          jobDetail.getJobDataMap().put("methodName", methodName);
          jobDetail.getJobDataMap().put("scheduleJobId", scheduleJobId);
          jobDetail.getJobDataMap().put("updateDate", updateDate);
          jobDetail.getJobDataMap().put("jobStatus", jobStatus);
          
          jobDetail.getJobBuilder().withDescription(jobDescription);  
          HashSet<Trigger> triggerSet = new HashSet<Trigger>();  
          triggerSet.add(cronTrigger);  
          
          scheduler.scheduleJob(jobDetail, triggerSet, true); 
          if(jobStatus.equals("PAUSED")){
  			 scheduler.pauseTrigger(triggerKey);  
			  scheduler.pauseJob(new JobKey(jobName,jobGroup));
          }
  		 if(jobStatus.equals("NORMAL")){
  			 scheduler.resumeTrigger(triggerKey);  
			 scheduler.resumeJob(new JobKey(jobName,jobGroup));  
  		 }
          b=true ;
      } catch (SchedulerException e) {  
          //throw new ServiceException("类名不存在或执行表达式错误");  
      }  
      return b;
  }  

  /** 
   * 删除定时任务 
   * @param jobName 
   * @param jobGroup 
   */  
  @Transactional(propagation=Propagation.REQUIRED)
  @Override
  public boolean delScheduleJob(String jobName, String jobGroup){  
	  TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);  
	  boolean b=false;
	  try {  
		  if (checkExists(jobName, jobGroup)) {  
			  scheduler.pauseTrigger(triggerKey);  
			b = scheduler.deleteJob(new JobKey(jobName, jobGroup));  
		  }  
	  } catch (SchedulerException e) {  
	  }  
	  return b; 
  }  
  /** 
   * 暂停定时任务 
   * @param jobName 
   * @param jobGroup 
   */  
  @Transactional(propagation=Propagation.REQUIRED)
  @Override
  public boolean pauseScheduleJob(String jobName, String jobGroup){  
	  TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);  
	  boolean b=false;
	  try {  
		  if (checkExists(jobName, jobGroup)) {  
			  scheduler.pauseTrigger(triggerKey);  
			  scheduler.pauseJob(new JobKey(jobName,jobGroup));
			  b=true;
		  }  
	  } catch (SchedulerException e) {  
	  }  
	  return b;
  }  
  /** 
   * 暂停所有定时任务 
   */  
  @Transactional(propagation=Propagation.REQUIRED)
  @Override
  public boolean pauseAllScheduleJob(){ 
	  boolean b=false;
			  try {
				scheduler.pauseAll();
				b=true;
			} catch (SchedulerException e) {
			} 
	 return b;
  }  
  /** 
   * 恢复定时任务 
   * @param jobName 
   * @param jobGroup 
   */  
  @Transactional(propagation=Propagation.REQUIRED)
  @Override
  public boolean resumeScheduleJob(String jobName, String jobGroup){  
	  TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);  
	  boolean b=false;
	  try {  
		  if (checkExists(jobName, jobGroup)) {  
			  scheduler.resumeTrigger(triggerKey);  
			  scheduler.resumeJob(new JobKey(jobName,jobGroup));  
			  b=true;
		  }  
	  } catch (SchedulerException e) {  
		  //  throw new ServiceException(e.getMessage());  
	  }  
	  return b;
  }  
  /** 
   * 恢复所有定时任务 
   * @param jobName 
   * @param jobGroup 
   */  
  @Transactional(propagation=Propagation.REQUIRED)
  @Override
  public boolean resumeAllScheduleJob(){ 
	  boolean b=false;
			  try {
				scheduler.resumeAll();
				b=true;
			} catch (SchedulerException e) {
			} 
	 return b;
  } 
      /** 
       * 验证是否存在 
       * @param jobName 
       * @param jobGroup 
       * @throws SchedulerException 
       */  
      private boolean checkExists(String jobName, String jobGroup) throws SchedulerException{  
          TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);  
          return scheduler.checkExists(triggerKey);  
      }  
  }  