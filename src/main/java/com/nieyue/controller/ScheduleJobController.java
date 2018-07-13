package com.nieyue.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nieyue.bean.ScheduleJob;
import com.nieyue.schedule.QuartzEventService;
import com.nieyue.service.ScheduleJobService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;



/**
 * 工作计划控制类
 * @author yy
 *
 */
@RestController
@RequestMapping(value={"/scheduleJob"})
public class ScheduleJobController {
	
	@Autowired
	QuartzEventService quartzEventService;
	@Autowired
	ScheduleJobService scheduleJobService;
	/**
	 * getsession
	 * @return
	 */
	@RequestMapping(value={"/getSession"})
	public String getSession(
			HttpSession session,
			HttpServletResponse response
			){
		return session.getId();
		
	}
	/**
	 * getsession
	 * @return
	 */
	@RequestMapping(value={"/test/accept"})
	public String testAccept(
			@RequestParam("jobId") Integer jobId,
			@RequestParam("type") Integer type,
			HttpSession session,
			HttpServletResponse response
			){
		String value="{jobId:"+jobId+",type:"+type+"}";
		return value;
		
	}
	/**
	 * getsession
	 * @return
	 */
	@RequestMapping(value={"/test/turn"})
	public String testPause(
			@RequestParam("scheduleJobId") Integer scheduleJobId,
			@RequestParam("jobStatus") String jobStatus,
			HttpSession session,
			HttpServletResponse response
			){
		ScheduleJob sj = scheduleJobService.loadScheduleJob(scheduleJobId);
		if(jobStatus.equals("PAUSED")){
			quartzEventService.pauseScheduleJob(sj.getJobName(),sj.getJobGroup());
		}
		if(jobStatus.equals("NORMAL")){
			quartzEventService.resumeScheduleJob(sj.getJobName(),sj.getJobGroup());
		}
		return "";
		
	}
	/**
	 * add
	 * @return
	 * @throws SchedulerException 
	 */
	@RequestMapping(value={"/add"})
	public StateResult addScheduleJob(
			HttpSession session,
			@ModelAttribute ScheduleJob scheduleJob,
			HttpServletResponse response
			) throws SchedulerException{
		boolean b = scheduleJobService.addScheduleJob(scheduleJob);
		return ResultUtil.getSR(b);
		
	}
	/**
	 * 修改
	 * @return
	 * @throws SchedulerException 
	 */
	@RequestMapping(value={"/update"})
	public StateResult updateScheduleJobList(
			HttpSession session,
			@ModelAttribute ScheduleJob scheduleJob,
			HttpServletResponse response
			) throws SchedulerException{
		boolean b =scheduleJobService.updateScheduleJob(scheduleJob);
		return ResultUtil.getSR(b);
		
	}
	/**
	 *删除
	 * @return
	 * @throws SchedulerException 
	 */
	@RequestMapping(value={"/delete"})
	public StateResult delScheduleJob(
			HttpSession session,
			@RequestParam("scheduleJobId") Integer scheduleJobId,
			HttpServletResponse response
			) throws SchedulerException{
		boolean b = scheduleJobService.delScheduleJob(scheduleJobId);
		return ResultUtil.getSR(b);
		
	}
	/**
	 *加载
	 * @return
	 * @throws SchedulerException 
	 */
	@RequestMapping(value={"/load"})
	public StateResultList loadScheduleJob(
			HttpSession session,
			@RequestParam("scheduleJobId") Integer scheduleJobId,
			HttpServletResponse response
			) throws SchedulerException{
		List<ScheduleJob> l=new ArrayList<ScheduleJob>();
		ScheduleJob sj = scheduleJobService.loadScheduleJob(scheduleJobId);
		l.add(sj);
		return ResultUtil.getSlefSRSuccessList(l);
		
	}
	/**
	 *总数
	 * @return
	 * @throws SchedulerException 
	 */
	@RequestMapping(value={"/count"})
	public StateResultList countAll(
			HttpSession session,
			HttpServletResponse response
			) throws SchedulerException{
		List<Integer> l=new ArrayList<Integer>();
		int f = scheduleJobService.countAll();
		l.add(f);
		return ResultUtil.getSlefSRSuccessList(l);
		
	}
	/**
	 * list
	 * @return
	 * @throws SchedulerException 
	 */
	@RequestMapping(value={"/list"})
	public StateResultList browseScheduleJobList(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="schedule_job_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="asc") String orderWay
			){
		List<ScheduleJob> l=new ArrayList<ScheduleJob>();
		 l = scheduleJobService.browsePagingScheduleJob(pageNum, pageSize, orderName, orderWay);
		return  ResultUtil.getSlefSRSuccessList(l); 
		
	}
	/**
	 * list
	 * @return
	 * @throws SchedulerException 
	 */
	@RequestMapping(value={"/listquartz"})
	public StateResultList list(
			){
		List<ScheduleJob> l=new ArrayList<ScheduleJob>();
		l = quartzEventService.list();
		return  ResultUtil.getSlefSRSuccessList(l);
		
	}
	/**
	 *暂停
	 * @return
	 * @param scheduleJobId 不存在则全部转状态
	 * @throws SchedulerException 
	 */
	@RequestMapping(value={"/turn"})
	public StateResult turnScheduleJob(
			HttpSession session,
			@RequestParam(value="scheduleJobId",required=false) Integer scheduleJobId,
			@RequestParam("jobStatus") String jobStatus,
			HttpServletResponse response
			) throws SchedulerException{
		if(scheduleJobId==null||scheduleJobId.equals("")){
			List<ScheduleJob> l = scheduleJobService.browsePagingScheduleJob(1, Integer.MAX_VALUE, "schedule_job_id", "asc");
			l.forEach((sj)->{
				sj.setJobStatus(jobStatus);
				scheduleJobService.updateScheduleJob(sj);	
			});
			return ResultUtil.getSuccess();
		}
		ScheduleJob sj = scheduleJobService.loadScheduleJob(scheduleJobId);
		sj.setJobStatus(jobStatus);
		scheduleJobService.updateScheduleJob(sj);
		return ResultUtil.getSuccess();
		
	}

}
