package com.nieyue.util;

/**
 * 返回状态Bean
 * @author yy
 *
 */
public class StateResult {
	private Integer code;
	private String msg;
	public StateResult() {
		super();
	}

	public StateResult(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
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
}
