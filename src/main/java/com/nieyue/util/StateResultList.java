package com.nieyue.util;

import java.util.List;

/**
 * 返回状态List Bean
 * @author yy
 *
 */
public class StateResultList {
	private Integer code;
	private String msg;
	private List<?> list;
	public StateResultList() {
		super();
	}

	
	
	public StateResultList(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}



	public StateResultList(Integer code, String msg, List<?> list) {
		super();
		this.code = code;
		this.msg = msg;
		this.list = list;
	}



	public Integer getCode() {
		return code;
	}



	public void setCode(Integer code) {
		this.code = code;
	}



	public String getMsg() {
		return msg;
	}



	public void setMsg(String msg) {
		this.msg = msg;
	}



	public List<?> getList() {
		return list;
	}



	public void setList(List<?> list) {
		this.list = list;
	}

}
