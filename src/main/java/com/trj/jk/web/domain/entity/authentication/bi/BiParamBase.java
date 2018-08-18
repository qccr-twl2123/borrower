package com.trj.jk.web.domain.entity.authentication.bi;


public class BiParamBase {

	private Integer uid;
	
	private Integer certficationId;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getCertficationId() {
		return certficationId;
	}

	public void setCertficationId(Integer certficationId) {
		this.certficationId = certficationId;
	}

	@Override
	public String toString() {
		return "BiParamBase [uid=" + uid + ", certficationId=" + certficationId + "]";
	}

	
		
}
