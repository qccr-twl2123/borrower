package com.trj.jk.web.domain.entity.thirdparty;

public class ErrorInfo {
	private String code;
	private String errorMsg;
	
	public ErrorInfo() {
		
	}
	
	public ErrorInfo(String code, String errorMsg) {
		this.code = code;
		this.errorMsg = errorMsg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
