package com.trj.jk.web.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.trj.commons.result.Result;
import com.trj.jk.web.domain.LoanLimit;
import com.trj.jk.web.domain.UserBasic;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.domain.entity.ResultMessageConstant;
import com.trj.jk.web.domain.entity.user.UserBean;
import com.trj.jk.web.domain.exception.CheckException;
import com.trj.jk.web.service.IUserService;
import com.trj.jk.web.util.SessionUtil;

@RestController
@RequestMapping(value = {"user"})
public class UserController {
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserService userService;
	
	/**
	 * 验证验证码接口
	 * @param mobile
	 * @param verifyCode
	 * @param bankCardId
	 * @return
	 */
	@RequestMapping(value="/verifyCode/verify",method=RequestMethod.POST) 
	@ResponseBody		
	public Result<Object> verifyCode(String mobile,String  verifyCode,Integer bankCardId){
		final Result<Object> result = new Result<Object>();
		LOG.info("验证验证码接口开始执行....");
		LOG.info(String.format("参数：mobile=%s,verifyCode=%s,bankCardId=%s", mobile,verifyCode,bankCardId));
		try{
			Map<String, Object> dataMap = userService.verifyCode(mobile,verifyCode,bankCardId);
			if(Boolean.valueOf(dataMap.get("isValid").toString())){
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.VERIFYCODE_CHECK_SUCCESS);
			}else{
				result.setSuccess(false);
				result.setMessage(dataMap.get("message").toString());
			}
			result.setData(dataMap);
		}catch(CheckException e){
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);			
			result.setMessage(e.getMessage());
		}catch(Exception e){
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);			
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("验证验证码接口返回:%s",result.toString()));
		return result;
	}
	
	/**
	 * 密码比对接口
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/password/check",method=RequestMethod.POST)
	@ResponseBody	
	public Result<Object> checkPassword(UserBasic user){
		final Result<Object> result = new Result<Object>();
		LOG.info("密码比对接口开始执行....");
		LOG.info(String.format("参数：user=%s", user.toString()));
		try{
			if(user.getMobile()!=null&&user.getPassword()!=null){
				boolean isIdentical = userService.checkPassword(user);
				if(isIdentical){
					result.setSuccess(true);
					result.setMessage(ResultMessageConstant.PASSWORD_CHECK_SUCCESS);
				}else{
					result.setSuccess(false);
					result.setMessage(ResultMessageConstant.PASSWORD_CHECK_FAIL);
				}
			}else{
				result.setSuccess(false);			
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		}catch(CheckException e){
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);			
			result.setMessage(e.getMessage());
		}catch(Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);			
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("密码比对接口返回:%s",result.toString()));
		return result;				
	}
	
	

	/**
	 * 密码修改接口
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/password/modify",method=RequestMethod.POST)
	@ResponseBody	
	public Result<Object> modifyPassword(UserBean user){
		final Result<Object> result = new Result<Object>();
		LOG.info("密码修改接口开始执行....");
		LOG.info(String.format("参数：user=%s", user.toString()));
		try{
			Integer userId=(Integer)SessionUtil.getUserLogonInfo();
			user.setId(userId);
			userService.modifyPassword(user);
			result.setMessage(ErrorMessageConstant.MODIFY_SUCCESS);
		}catch(CheckException e){
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);			
			result.setMessage(e.getMessage());
		}catch(Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);			
			
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("密码修改接口返回:%s",result.toString()));
		return result;				
	}	
	
	/**
	 * 获取手机验证码接口
	 * @param mobile
	 * @param mtype
	 * @param bankCardId
	 * @return
	 */
	@RequestMapping(value={"/getVerifyCode"},method=RequestMethod.GET)
	@ResponseBody	
	public Result<Object> getVerifyCode(Integer uid,String mobile,Byte mtype,Integer bankCardId){
		final Result<Object> result = new Result<Object>();
		LOG.info("获取手机验证码接口开始执行....");
		LOG.info(String.format("参数:mobile=%s,mtype=%s,bankCardId=%s",mobile,mtype,bankCardId));
		try{
			if(null==uid){
				uid=(Integer)SessionUtil.getUserLogonInfo();
			}
			if(bankCardId==null && mobile==null){
				result.setSuccess(false);			
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
				return result;
			}
			if(mtype!=null){
				userService.getVerifyCode(uid,mobile,mtype,bankCardId);
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.GET_VERIFY_CODE_SUCCESS);
			}else{
				result.setSuccess(false);			
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		}catch(CheckException e){
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);			
			result.setMessage(e.getMessage());
		}
		catch(Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);			
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("获取手机验证码接口返回:%s",result.toString()));
		return result;			
	}
	
	/**
	 * e签宝开户接口
	 * @return
	 */
	@RequestMapping(value={"/esOpenAccount"},method=RequestMethod.GET)
	@ResponseBody
	public Result<Object> esOpenAccount(Integer uid) {
		final Result<Object> result = new Result<Object>();
		LOG.info("e签宝开户接口开始执行....");
		try {
			//Integer uid=(Integer)SessionUtil.getUserLogonInfo();
			if(null==uid){
				uid=(Integer)SessionUtil.getUserLogonInfo();
			}
			if(uid!=null){
				userService.esOpenAccount(uid);
				result.setSuccess(true);			
				result.setMessage(ErrorMessageConstant.SAVE_SUCCESS);
			}else{
				result.setSuccess(false);			
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		}catch(CheckException e){
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);			
			result.setMessage(e.getMessage());
		}catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);			
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("e签宝开户接口返回:%s",result.toString()));
		return result;
	}
	
	
	/**
	 * 合同签章授权接口（即电子签章发送验证码接口）
	 */
	@RequestMapping(value={"/signatureAccredit"},method=RequestMethod.GET)
	@ResponseBody
	public Result<Object> signatureAccredit(Integer uid) {
		final Result<Object> result = new Result<Object>();
		LOG.info("电子签章发送验证码接口开始执行....");
		try {
			//Integer uid=(Integer)SessionUtil.getUserLogonInfo();
			if(null==uid){
				uid=(Integer)SessionUtil.getUserLogonInfo();
			}
			if(uid!=null){
				userService.signatureAccredit(uid);
				result.setSuccess(true);			
				result.setMessage(ResultMessageConstant.GET_VERIFY_CODE_SUCCESS);
			}else{
				result.setSuccess(false);			
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		}catch(CheckException e){
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);			
			result.setMessage(e.getMessage());
		}catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);			
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("电子签章发送验证码接口返回:%s",result.toString()));
		return result;
	}
	
	/**
	 * 合同签章接口
	 */
	@RequestMapping(value={"/doSignature"},method=RequestMethod.POST)
	@ResponseBody
	public Result<Object> doSignature(LoanLimit loanLimit,String verifyCode) {
		final Result<Object> result = new Result<Object>();
		LOG.info("合同签章接口开始执行....");
		LOG.info(String.format("参数:loanLimit=%s,verifyCode=%s",loanLimit,verifyCode));
		try {
			Integer uid=(Integer)SessionUtil.getUserLogonInfo();
			if(uid!=null&&loanLimit!=null&&verifyCode!=null){
				loanLimit.setUid(uid);
				loanLimit.setStatus(Byte.parseByte("1"));
				loanLimit.setIsContractPublish(Byte.parseByte("0"));
				userService.electronSignature(uid,loanLimit,verifyCode);
				result.setSuccess(true);			
				result.setMessage("电子签章调用成功！");
			}else{
				result.setSuccess(false);			
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		}catch(RuntimeException e){
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);			
			result.setMessage(e.getMessage());
		}
		catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);			
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("合同签章接口返回:%s",result.toString()));
		return result;
	}
	
}
