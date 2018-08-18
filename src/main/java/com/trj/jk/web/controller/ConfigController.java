package com.trj.jk.web.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trj.commons.result.Result;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.domain.exception.CheckException;
import com.trj.jk.web.service.IConfigService;
import com.trj.jk.web.util.SessionUtil;

@RestController
@RequestMapping(value={"/config"},method=RequestMethod.POST)
public class ConfigController {

	private static final Logger LOG = Logger.getLogger(ConfigController.class);	
	
	@Resource
	private IConfigService configService;
	
	@RequestMapping("/pwdAndSafe")
	public Result<Object> pwdAndSafe(){
		return null;
	}
	
	@RequestMapping("/pwdManage")
	public Result<Object> pwdManage(){
		return null;
	}
	
	@RequestMapping("/pushMassage")
	public Result<Object> pushMassage(){
		return null;
	}
	
	@RequestMapping("/bidMobile")
	public Result<Object> bidMobile(){
		return null;
	}
	
	@RequestMapping("/empowerManage")
	public Result<Object> empowerManage(){
		return null;
	}
	
	@RequestMapping("/mobile/change")
	public Result<Object> changeMobile(String mobile,String pwd,String verifyCode){
		Result<Object> result = new Result<Object>();
		LOG.info("修改绑定手机号接口调用》》》》》》");
		LOG.info(String.format("修改绑定手机号接口调用参数：mobile:%s,pwd:%s,verifyCode:%s",mobile,pwd,verifyCode));
		try{
			Integer uid=(Integer) SessionUtil.getUserLogonInfo();
			if(mobile!=null&&pwd!=null&&verifyCode!=null&&uid!=null){
				configService.changeMobile(uid, mobile,pwd,verifyCode);
				SessionUtil.invalidSession();
				result.setMessage(ErrorMessageConstant.MODIFY_SUCCESS);
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		}catch(CheckException e){
			LOG.info(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		}catch(Exception e){
			LOG.info(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("修改绑定手机号接口返回:%s",result.toString()));
		return result;
	}

}
