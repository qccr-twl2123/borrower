package com.trj.jk.web.service.thirdparty;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.trj.jk.web.domain.ThirdpartyRequestResponseLog;
import com.trj.jk.web.mapper.ThirdpartyRequestResponseLogMapper;



public abstract class AbstractThirdPartyServiceImpl {
		
	
	@Autowired
	private ThirdpartyRequestResponseLogMapper thirdpartyRequestResponseMapper;
	
	@Value("${app.soopay.url}")
	private String url;
	
	@Value("${app.soopay.platform}")	
	private String thirdPartyName;
	
	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getThirdPartyName() {
		return thirdPartyName;
	}


	public void setThirdPartyName(String thirdPartyName) {
		this.thirdPartyName = thirdPartyName;
	}
	
	@Transactional (propagation=Propagation.REQUIRES_NEW)
	public void saveRequestResponseLog(ThirdpartyRequestResponseLog log) {
		thirdpartyRequestResponseMapper.insertSelective(log);

	}
	
	protected ThirdpartyRequestResponseLog generateLogWithRequest(boolean requestSource, byte requestType, String request, Object requestParam) {
		ThirdpartyRequestResponseLog log = new ThirdpartyRequestResponseLog();
		log.setRequestType(requestType);
		log.setRequestSource(requestSource);
		log.setThirdpartyName(getThirdPartyName());
		log.setRequestInfo(request);
		String response = doSendRequest(requestParam);
		log.setResponseInfo(response);
		return log;
	}
	
	protected ThirdpartyRequestResponseLog generateLogWithResponse(boolean requestSource, byte requestType, String request, String response) {
		ThirdpartyRequestResponseLog log = new ThirdpartyRequestResponseLog();
		log.setRequestType(requestType);
		log.setRequestSource(requestSource);
		log.setThirdpartyName(getThirdPartyName());
		log.setRequestInfo(request);
		log.setResponseInfo(response);
		return log;
	}	
	
	protected abstract String doSendRequest(Object requestParam);
	

}
