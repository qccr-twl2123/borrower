package com.trj.jk.web.domain.entity.thirdparty;

public class ErrorResult extends ProcessResult{
	public ErrorResult(){
		//默认为空
		this.error=null;
	}
	ErrorInfo error;
	

	public ErrorInfo getError() {
		return error;
	}

	public void setError(ErrorInfo error) {
		this.error = error;
	}
}
