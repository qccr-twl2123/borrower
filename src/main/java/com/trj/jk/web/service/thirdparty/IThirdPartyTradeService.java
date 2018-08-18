package com.trj.jk.web.service.thirdparty;

import com.trj.jk.web.domain.LoanRepayRecord;
import com.trj.jk.web.domain.entity.thirdparty.ErrorResult;



public interface IThirdPartyTradeService extends IThirdPartyService{
	
	public ErrorResult trade(Object requestBean, LoanRepayRecord payBean) throws Exception;
	
	public ErrorResult pay(Object requestBean, LoanRepayRecord payBean) throws Exception;
	
	public ErrorResult tradeNotify(Object bean) throws Exception;
}
