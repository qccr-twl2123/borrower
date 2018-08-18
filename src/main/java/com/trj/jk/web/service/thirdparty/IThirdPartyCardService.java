package com.trj.jk.web.service.thirdparty;

import com.trj.jk.web.domain.UserBankCard;
import com.trj.jk.web.domain.entity.bankcard.BankCardBean;
import com.trj.jk.web.domain.entity.thirdparty.ErrorResult;


public interface IThirdPartyCardService extends IThirdPartyService{
	public ErrorResult bindCard(Object requestBean, BankCardBean cardBean,Boolean flag) throws Exception;
	
	public ErrorResult bindConfirm(Object requestBean, UserBankCard cardBean) throws Exception;
	
	public ErrorResult unBindCard(Object requestBean) throws Exception;
	
	public ErrorResult bindCardNotify(Object bean) throws Exception;

}
