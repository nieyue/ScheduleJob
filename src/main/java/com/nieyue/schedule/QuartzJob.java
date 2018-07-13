package com.nieyue.schedule;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.nieyue.util.HttpClientUtil;
public class QuartzJob implements Job{
	@Value("${myPugin.quartzJobUrl}")
	String quartzJobUrl;
	@Autowired
	RedisLock redisLock;


	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
			try {
				JobDataMap jdm = arg0.getJobDetail().getJobDataMap();
				if(redisLock.checkStatus((int) jdm.get("type"),(int) jdm.get("jobId"))){
					//System.out.println("jobId"+jdm.get("jobId")+new Date().toLocaleString());					
					HttpClientUtil.doGet(quartzJobUrl+"&jobId="+jdm.get("jobId")+"&type="+jdm.get("type"));
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}


}
