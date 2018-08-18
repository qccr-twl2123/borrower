package com.trj.jk.web.domain.entity;


public enum ResultStatus { 
	OK(0, "OK"),
	BAD_REQUEST(400, "Bad request."), 
	NOT_ACCEPTABLE(406, "Not acceptable."), 
	URI_TOO_LONG(414, "Request-URI too long."), 
	INTERNAL_ERROR(500, "Internal server error."),
	ERROR_OCCUR(500, "Sorry, the request cannot be completed. Please wait a moment and try again."),
	
	NULL_USER_PASSWORD(400, "User name or password can not be null, please check your vt configuration."),
	
	INTERFACE_ERROR(400, "Interface envoke error.");

	private Integer code;
	private String message;

	private ResultStatus(int code, String message) {
		this.code = code;
		this.message = message;
	}
	

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
