package com.trj.jk.web.domain.entity.authentication.bi;

import java.util.List;

public class BiResult {
	private String retmessage;
	private Integer retcode;
	private List<BiRetResult> retresult;
	public String getRetmessage() {
		return retmessage;
	}
	public void setRetmessage(String retmessage) {
		this.retmessage = retmessage;
	}
	public Integer getRetcode() {
		return retcode;
	}
	public void setRetcode(Integer retcode) {
		this.retcode = retcode;
	}
	
	public List<BiRetResult> getRetresult() {
		return retresult;
	}
	public void setRetresult(List<BiRetResult> retresult) {
		this.retresult = retresult;
	}
	@Override
	public String toString() {
		return "BiResult [retmessage=" + retmessage + ", retcode=" + retcode + ", retresult=" + retresult + "]";
	}
	
}
