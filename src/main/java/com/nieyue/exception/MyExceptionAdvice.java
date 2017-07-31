package com.nieyue.exception;

import org.apache.http.HttpException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;

/**
 * 统一异常处理
 * @author 聂跃
 * @date 2017年4月12日
 */
@RestControllerAdvice
public class MyExceptionAdvice {
	
	@ExceptionHandler(value=Exception.class)
	@ResponseBody
	public StateResult jsonErrorHandler( Exception e) throws Exception {
	       return ResultUtil.getFail();
	    }
	@ExceptionHandler(value=BindException.class)
	@ResponseBody
	public StateResult paramsErrorHandler( Exception e) throws Exception {
		return ResultUtil.getSlefSR(50000, "参数绑定错误");
	}
	@ExceptionHandler(value=MissingServletRequestParameterException.class)
	@ResponseBody
	public StateResult requiredParamsErrorHandler( Exception e) throws Exception {
		return ResultUtil.getSlefSR(50001, "缺少参数");
	}
	@ExceptionHandler(value={MySessionException.class})
	@ResponseBody
	public StateResult requestSessionErrorHandler2( Exception e) throws Exception {
		return ResultUtil.getSlefSR(70000, "没有权限");
	}
	@ExceptionHandler(value={HttpMediaTypeNotAcceptableException.class})
	@ResponseBody
	public StateResult requestSessionErrorHandler( Exception e) throws Exception {
		return ResultUtil.getSlefSR(70001, "406");
	}
	@ExceptionHandler(value={TestApiException.class})
	@ResponseBody
	public StateResult testApiHandler( Exception e) throws Exception {
		return ResultUtil.getSlefSR(10000, "测试接口已关闭");
	}
	@ExceptionHandler(value={HttpException.class,})
	@ResponseBody
	public StateResult httpException( Exception e) throws Exception {
		return ResultUtil.getSlefSR(40004, "http请求异常");
	}
	
}
