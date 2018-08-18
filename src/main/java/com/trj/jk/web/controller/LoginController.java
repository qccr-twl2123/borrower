package com.trj.jk.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.trj.commons.result.Results;
import com.trj.jk.web.domain.entity.regex.RegexContext;
import com.trj.jk.web.model.request.LoginReq;
import com.trj.jk.web.model.request.RegisterReq;
import com.trj.jk.web.model.request.ResetPasswordReq;
import com.trj.jk.web.model.response.LoginRes;
import com.trj.jk.web.util.DigestUtil;
import com.trj.jk.web.validator.Assert;
import com.trj.jk.web.validator.ValidatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.trj.commons.result.Result;
import com.trj.jk.web.domain.UserBasic;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.domain.exception.LoginException;
import com.trj.jk.web.service.ILoginService;
import com.trj.jk.web.util.SessionUtil;

@RestController
@RequestMapping(value = {"login"},method = RequestMethod.POST)
public class LoginController {
	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private ILoginService loginService;
	
	@RequestMapping("/signon") 
	public Result<LoginRes> signOn(RegisterReq registerReq){
		ValidatorUtils.validateEntity(registerReq);
		LoginRes loginRes = null;
		Boolean result = loginService.signOn(registerReq);
		if(result){
			LoginReq loginReq = new LoginReq();
			loginReq.setMobile(registerReq.getMobile());
			loginReq.setPassword(DigestUtil.getMD5(registerReq.getPassword()));
			//注册完登录
			loginRes = loginService.logon(loginReq, true);
		}
		return Results.newSuccessResult(loginRes);
	}
	
	
	@RequestMapping("/login") 
	public Result<LoginRes> login(LoginReq loginReq){
		ValidatorUtils.validateEntity(loginReq);
		LoginRes loginRes = loginService.logon(loginReq, false);
		return Results.newSuccessResult(loginRes);
	}
	
	@RequestMapping("/logout") 
	public Result<Boolean> logout(){
		try {
			SessionUtil.invalidSession();
		} catch (LoginException e) {
			LOG.error(e.getMessage(), e);
			return Results.newFailedResult(e.getMessage());
		}catch(Exception e) {
			LOG.error(e.getMessage(), e);
			return Results.newFailedResult(ErrorMessageConstant.OK_LOGOUT);

		}
		return Results.newSuccessResult(true);
	}

	@RequestMapping("/is_login")
	@ResponseBody
	public Result<Object> isLogin(HttpServletRequest request){
		final Result<Object> result = new Result<Object>();
		
		Object logonInfo= SessionUtil.getAttribute("USER_LOGON_INFO");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		if(logonInfo !=null) {
			result.setMessage(ErrorMessageConstant.SUCCESS);
			dataMap.put("is_login", true);
		}else{
			result.setMessage(ErrorMessageConstant.UN_LOGIN);
			dataMap.put("is_login", false);
		}
		result.setData(dataMap);
		return result;
	}

    @RequestMapping("/is_registered")
    @ResponseBody
    public Result<Object> isRegisterUser(String mobile){
		Assert.isTrue(!RegexContext.match(RegexContext.MOBILE_REGEX,mobile),"手机格式不正确");
		final Result<Object> result = new Result<Object>();
        try{
            UserBasic user = loginService.getUserByMobile(mobile);
            if(user==null){
                result.setMessage(ErrorMessageConstant.MOBIEL_UNREGISTERED);
                result.setData(new Boolean(false));
            }else{
                result.setData(new Boolean(true));
                result.setMessage(ErrorMessageConstant.MOBILE_REGISTERED);
            }
        }catch(LoginException e){
            LOG.error(e.getMessage(),e);
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }catch(Exception e) {
            LOG.error(e.getMessage(),e);
            result.setSuccess(false);

            result.setMessage(ErrorMessageConstant.ERR_OCCURS);
        }
        return result;

    }
	
	
	@RequestMapping("/find_password")
	public Result<Boolean> findPassword(ResetPasswordReq resetPasswordReq){
		ValidatorUtils.validateEntity(resetPasswordReq);
		loginService.resetPassword(resetPasswordReq);
		return Results.newSuccessResult(true);
	}
	
		
}
