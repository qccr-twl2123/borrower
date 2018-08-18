package com.trj.jk.web.mapper.jdbc;

import java.util.List;
import java.util.Map;

import com.trj.jk.web.domain.entity.MobileValidateCode;

public interface JdbcDao {
	String  getTrbsVerifyCodeStatus(String mobile, String verifyCode);

	Map<String, Object> getSmstplByMtype(Byte mtype);

	long saveVerifyCode(MobileValidateCode mobileValidateCode);

	String getTrjVerifyCodeStatus(String mobile, String verifyCode);
	
	List<Map<String, Object>> getContractByLoanLimitId(Integer loanLimitId);
}
