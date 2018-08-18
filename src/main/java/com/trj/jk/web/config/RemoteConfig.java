package com.trj.jk.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpComponentsHttpInvokerRequestExecutor;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

import com.trj.crm.service.api.IFooService;
import com.trj.crm.service.api.IJbpmService;
import com.trj.message.service.api.ISmsService;

@Configuration
public class RemoteConfig {

	@Value("${app.remote.domain.crm}")
	private String	remoteDomainCrm		= null;
	@Value("${app.remote.domain.message}")
	private String	remoteDomainMessage	= null;

	@Bean
	public HttpComponentsHttpInvokerRequestExecutor httpInvokerRequestExecutor() {
		return new HttpComponentsHttpInvokerRequestExecutor();
	}

	@Bean("fooService")
	public HttpInvokerProxyFactoryBean fooService(HttpComponentsHttpInvokerRequestExecutor httpInvokerRequestExecutor) {
		HttpInvokerProxyFactoryBean factoryBean = new HttpInvokerProxyFactoryBean();
		factoryBean.setServiceUrl(remoteDomainCrm + "/remote/fooService");
		factoryBean.setServiceInterface(IFooService.class);
		factoryBean.setHttpInvokerRequestExecutor(httpInvokerRequestExecutor);
		return factoryBean;
	}

	
	@Bean("smsService")
	public HttpInvokerProxyFactoryBean smsService(HttpComponentsHttpInvokerRequestExecutor httpInvokerRequestExecutor) {
		HttpInvokerProxyFactoryBean factoryBean = new HttpInvokerProxyFactoryBean();
		factoryBean.setServiceUrl(remoteDomainMessage + "/remote/SmsService");
		factoryBean.setServiceInterface(ISmsService.class);
		factoryBean.setHttpInvokerRequestExecutor(httpInvokerRequestExecutor);
		return factoryBean;
	}
	
	@Bean("jbpmService")
	public HttpInvokerProxyFactoryBean jbpmService(HttpComponentsHttpInvokerRequestExecutor httpInvokerRequestExecutor) {
		HttpInvokerProxyFactoryBean factoryBean = new HttpInvokerProxyFactoryBean();
		factoryBean.setServiceUrl(remoteDomainCrm + "/remote/remoteJbpmService");
		factoryBean.setServiceInterface(IJbpmService.class);
		factoryBean.setHttpInvokerRequestExecutor(httpInvokerRequestExecutor);
		return factoryBean;
	}	

//	@Bean("mobileMessageService")
//	public HttpInvokerProxyFactoryBean mobileMessageService(HttpComponentsHttpInvokerRequestExecutor httpInvokerRequestExecutor) {
//		HttpInvokerProxyFactoryBean factoryBean = new HttpInvokerProxyFactoryBean();
//		factoryBean.setServiceUrl(remoteDomainCrm + "/remote/mobileMessageService");
//		factoryBean.setServiceInterface(IMobileMessageService.class);
//		factoryBean.setHttpInvokerRequestExecutor(httpInvokerRequestExecutor);
//		return factoryBean;
//	}
}
