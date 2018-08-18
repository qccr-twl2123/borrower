package com.trj.jk.web.domain.entity.thirdparty.soopay;
/**
 * 
 * @snoopy平台的协议参数
 * @author gaoxiang
 *
 */
public class AgreementRequestParam extends SignableAgreementRequesParam{

	private String sign;
	

	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}


}
