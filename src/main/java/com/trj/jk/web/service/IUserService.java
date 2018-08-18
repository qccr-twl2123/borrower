package com.trj.jk.web.service;

import java.util.Map;

import com.trj.jk.web.domain.LoanLimit;
import com.trj.jk.web.domain.UserBasic;
import com.trj.jk.web.domain.entity.user.UserBean;

public interface IUserService {
	void modifyPassword(UserBean userBean);
	
	void checkUserInfo(UserBean userBean);
	
	Map<String, Object> verifyCode(String mobile, String verifyCode,Integer bankCardId);
	
	String getName(Integer uid);
	
	String getIdentityId(Integer uid);
	
	boolean signatureAccredit(Integer uid);
	
	void electronSignature(Integer uid,LoanLimit loanLimit,String verifyCode) throws Exception;
	
	String doContractPublish(String url,Map<String, String> param);

	void esOpenAccount(Integer uid);
	
	boolean checkPassword(UserBasic userBasic);
	
	void getVerifyCode(Integer uid,String mobile,Byte mtype,Integer bankCardId);
	String getUserInfo(Integer uid);

	void updateUserHouseFundCertStatus(Integer uid,boolean success);

	Map<String, Object> getUserInfoByTrjUid(Integer trjUid);

	UserBasic getUserBasicByMobile(String mobile);

	void doInterestFreeSignature(Integer uid, Integer orderId,String verifyCode);

	 void generateContractAndSignAccredit(Integer uid,LoanLimit loanLimit,String verifyCode,String contractsTplNo);

}
