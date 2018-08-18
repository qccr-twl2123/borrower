package com.trj.jk.web.domain.entity.thirdparty.soopay;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component("agreementParamWithoutNotify")
public class SignableAgreementRequesParam {
	
	private String service;
	@Value("${app.soopay.merid}")	
	private String mer_id;
	@Value("${app.soopay.sign.version}")		
	private String version;
	@Value("${app.soopay.sign.charset}")			
	private String charset;
	@Value("${app.soopay.sign.type}")				
	private String sign_type;
	@Value("${app.soopay.sign.format}")					
	private String res_format;
	
	public SignableAgreementRequesParam(){
		
	}
	
	public SignableAgreementRequesParam(AgreementRequestParam param) {
		this.service=param.getService();
		this.mer_id = param.getMer_id();
		this.version = param.getVersion();
		this.charset = param.getCharset();
		this.res_format = param.getRes_format();
		this.sign_type = param.getSign_type();
	}
	
	
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getMer_id() {
		return mer_id;
	}
	public void setMer_id(String mer_id) {
		this.mer_id = mer_id;
	}

	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getRes_format() {
		return res_format;
	}
	public void setRes_format(String res_format) {
		this.res_format = res_format;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}	
	
	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
 
}
