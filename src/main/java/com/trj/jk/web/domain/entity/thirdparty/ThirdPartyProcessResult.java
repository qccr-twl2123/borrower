package com.trj.jk.web.domain.entity.thirdparty;

import java.util.Map;

import com.trj.jk.web.domain.ThirdpartyRequestResponseLog;


public class ThirdPartyProcessResult {
	private ErrorResult result;
	private ThirdpartyRequestResponseLog log;
	private Map data;
	
	
	public Map getData() {
		return data;
	}
	public void setData(Map data) {
		this.data = data;
	}
	public ErrorResult getResult() {
		return result;
	}
	public void setResult(ErrorResult result) {
		this.result = result;
	}
	public ThirdpartyRequestResponseLog getLog() {
		return log;
	}
	public void setLog(ThirdpartyRequestResponseLog log) {
		this.log = log;
	}
}
