package com.nieyue.schedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;

import com.nieyue.util.HttpClientUtil;
public class QuartzJob implements Job{
	@Value("${myPugin.quartzJobUrl}")
	String quartzJobUrl;
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
			try {
				HttpClientUtil.doGet(quartzJobUrl+"&jobId="+arg0.getJobDetail().getJobDataMap().getInt("jobId")+"&type="+arg0.getJobDetail().getJobDataMap().getInt("type"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}


}
