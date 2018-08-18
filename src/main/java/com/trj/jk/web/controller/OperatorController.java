package com.trj.jk.web.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.trj.commons.result.Result;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.domain.entity.ResultMessageConstant;
import com.trj.jk.web.domain.entity.authentication.bi.BiRetResult;
import com.trj.jk.web.domain.entity.authentication.bi.CertResult;
import com.trj.jk.web.domain.entity.authentication.bi.OperatorResetPwdBean;
import com.trj.jk.web.domain.exception.CheckException;
import com.trj.jk.web.service.IAuthenticationService;

/**
 * 运营商相关服务Controller类
 *
 */
@RestController
@RequestMapping(value = {"operator"})
public class OperatorController extends BaseController{

	private static final Logger LOG = LoggerFactory.getLogger(OperatorController.class);

	@Resource
	private IAuthenticationService authenticationService;
	/**
	 * 运营商修改服务密码获取验证码接口
	 * @param uid：用户uid
	 * @param loanApplyId：借款申请id
	 * @return
	 */
	@RequestMapping(value={"/getVerifyCode"},method=RequestMethod.POST)
	@ResponseBody
	public Result<Object> getVerifyCode(@RequestParam(value="mobile",required=true) String mobile) {
		Result<Object> result = new Result<Object>();
		LOG.info("======运营商修改服务密码获取验证码接口开始执行======");
		try {
			BiRetResult biRetResult = authenticationService.getVerifyCode(mobile);
			result.setSuccess(true);
			result.setMessage(ResultMessageConstant.GET_VERIFY_CODE_SUCCESS);
			result.setData(biRetResult);
		}catch(CheckException ex){
			LOG.info(ex.getMessage(),ex);
			result.setSuccess(false);
			result.setMessage(ex.getMessage());
		}catch (RuntimeException ex) {
			LOG.error(ex.getMessage(), ex);
			result.setSuccess(false);
			result.setMessage(ex.getMessage());
			}catch (Exception ex) {
			LOG.error(ex.getMessage(), ex);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("运营商修改服务密码获取验证码接口返回：%s",result.toString()));
		return result;
	}
	
	
	/**
	 * 运营商修改服务密码接口
	 * @param uid：用户uid
	 * @param loanApplyId：借款申请id
	 * @return
	 */
	@RequestMapping(value={"/password/modify"},method=RequestMethod.POST)
	@ResponseBody
	public Result<Object> modifyPassword(OperatorResetPwdBean bean) {
		Result<Object> result = new Result<Object>();
		LOG.info("======运营商认证修改服务密码接口开始执行======");
		try {
			CertResult certResult = authenticationService.modifyPassword(bean);
			result.setSuccess(true);
			result.setMessage(ResultMessageConstant.SUBMIT_RESET_PWD);
			result.setData(certResult);
		}catch(CheckException ex){
			LOG.info(ex.getMessage(),ex);
			result.setSuccess(false);
			result.setMessage(ex.getMessage());
		}catch (Exception ex) {
			LOG.error(ex.getMessage(), ex);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("认运营商修改服务密码接口返回：%s",result.toString()));
		return result;
	}
	
	
}
