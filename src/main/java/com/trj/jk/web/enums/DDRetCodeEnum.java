package com.trj.jk.web.enums;

/**
 * 多多理财结果
 * @author -ming-
 *
 */
public enum DDRetCodeEnum {

	RCM_3303(3303, "参数异常"), RCM_3304(3304, "用户已存在"),RCM_3305(3305, "该用户已经开通存管账号"),RCM_3324(3324, "渠道不存在");

	private Integer code	= null;
	private String msg		= null;

	private DDRetCodeEnum(Integer code, String msg) {
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
