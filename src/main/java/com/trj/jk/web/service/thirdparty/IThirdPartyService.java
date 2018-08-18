package com.trj.jk.web.service.thirdparty;

import com.trj.jk.web.domain.entity.thirdparty.ThirdPartyProcessResult;




public interface IThirdPartyService {
	
	public byte REQUEST_TYPE_BIND_APPLY = 0;
	public byte REQUEST_TYPE_BIND_CONFIRM = 1;
	public byte REQUEST_TYPE_BIND_NOTIFY = 2;
	public byte REQUEST_TYPE_ORDER = 3;
	public byte REQUEST_TYPE_PAY = 4;
	public byte REQUEST_TYPE_TRADE_NOTIFY = 5;
	
	public byte REQUEST_TYPE_UNBIND_APPLY = 6;
	public byte REQUEST_TYPE_UNBIND_NOTIFY = 7;
	
	public boolean REQUEST_SOURCE_TRJ = false;
	public boolean REQUEST_SOURCE_THIRDPARTY = true;	
	
	public ThirdPartyProcessResult sendRequestProcess(Object requestBean, byte stage) throws Exception;
	
	public ThirdPartyProcessResult notifyProcess(Object bean, byte stage) ;
	
	
	
}
