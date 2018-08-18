package com.trj.jk.web.domain.entity.es.seal;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.timevale.esign.sdk.tech.bean.result.LoginResult;
import com.timevale.esign.sdk.tech.service.AccountService;
import com.timevale.esign.sdk.tech.service.EsignsdkService;
import com.timevale.esign.sdk.tech.service.SealService;
import com.timevale.esign.sdk.tech.service.SignService;
import com.timevale.esign.sdk.tech.service.factory.AccountServiceFactory;
import com.timevale.esign.sdk.tech.service.factory.EsignsdkServiceFactory;
import com.timevale.esign.sdk.tech.service.factory.SealServiceFactory;
import com.timevale.esign.sdk.tech.service.factory.SignServiceFactory;
import com.trj.jk.web.service.IUserService;
import com.trj.jk.web.util.SpringUtil;


public class ElectronicSealServiceFactory {

	private static final Logger		LOG		= LoggerFactory.getLogger(ElectronicSealServiceFactory.class);

	private volatile static boolean	init	= false;

	private static String			devId	= null;
	

	private synchronized static final void init() {
		if (!init) {
			try {

				SignatureLoginBean signatureLoginBean =(SignatureLoginBean)SpringUtil.getBean("signatureLoginBean");		

				LOG.info(String.format("Esignsdk login start {projectId=%s,projectSecret=%s}", signatureLoginBean.getId(), signatureLoginBean.getSecret()));
				EsignsdkService esignsdkService = EsignsdkServiceFactory.instance();
				LoginResult loginResult = esignsdkService.login(signatureLoginBean.getId(), signatureLoginBean.getSecret());
				if (loginResult.getErrCode() != 0) {
					throw new Exception(String.format("Esignsdk login fail,{errCode=%s,msg=%s}", loginResult.getErrCode(), loginResult.getMsg()));
				}

				devId = loginResult.getAccountId();

				LOG.info("Esignsdk login end");

			} catch (Exception ex) {
				LOG.error(ex.getMessage(), ex);
			} finally {
				init = true;
			}

		}
	}

	public static final String getDevId() {
		if (devId == null && !init) {
			init();
		}
		return devId;
	}

	private static final void validateDevId() {
		getDevId();
		if (devId == null) {
			throw new RuntimeException("e签宝登陆失败");
		}
	}

	public static final AccountService getAccountService() {
		validateDevId();
		return AccountServiceFactory.instance();
	}

	public static final SealService getSealService() {
		validateDevId();
		return SealServiceFactory.instance();
	}

	public static final SignService getSignService() {
		validateDevId();
		return SignServiceFactory.instance();
	}
	
}
