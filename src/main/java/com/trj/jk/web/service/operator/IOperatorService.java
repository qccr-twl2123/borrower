package com.trj.jk.web.service.operator;

import com.trj.jk.web.domain.entity.authentication.bi.BiRetResult;
import com.trj.jk.web.domain.entity.authentication.bi.CertResult;
import com.trj.jk.web.domain.entity.authentication.bi.OperatorResetPwdBean;

/**
 * 运营商相关服务接口
 * @author 
 *
 */
public interface IOperatorService {

	/**
	 * 修改运营商服务密码发送短信
	 */
	public BiRetResult getVerifyCode(OperatorResetPwdBean bean);
	
	/**
	 * 重置服务密码
	 * @param bean
	 */
	public CertResult resetPwd(OperatorResetPwdBean bean);
}
