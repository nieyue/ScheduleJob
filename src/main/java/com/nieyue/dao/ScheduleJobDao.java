package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.ScheduleJob;
@Mapper
public interface ScheduleJobDao {
	/** 新增定时任务*/	
	public boolean addScheduleJob(ScheduleJob scheduleJob) ;	
	/** 删除定时任务 */	
	public boolean delScheduleJob(Integer scheduleJobId) ;
	/** 更新定时任务*/	
	public boolean updateScheduleJob(ScheduleJob scheduleJob);
	/** 装载定时任务 */	
	public ScheduleJob loadScheduleJob(Integer scheduleJobId);	
	/** 定时任务总共数目 */	
	public int countAll();	
	/** 分页定时任务信息 */
	public List<ScheduleJob> browsePagingScheduleJob(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		

}
