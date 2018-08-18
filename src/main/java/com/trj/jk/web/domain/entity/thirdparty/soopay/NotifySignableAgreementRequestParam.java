package com.trj.jk.web.domain.entity.thirdparty.soopay;

import org.springframework.stereotype.Component;

@Component("agreementParamWithNotify")
public class NotifySignableAgreementRequestParam extends SignableAgreementRequesParam{
	String notify_url;

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
}