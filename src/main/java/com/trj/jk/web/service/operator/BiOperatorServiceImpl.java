package com.trj.jk.web.service.operator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.trj.jk.web.domain.entity.authentication.bi.BiResult;
import com.trj.jk.web.domain.entity.authentication.bi.BiRetResult;
import com.trj.jk.web.domain.entity.authentication.bi.CertResult;
import com.trj.jk.web.domain.entity.authentication.bi.OperatorResetPwdBean;
import com.trj.jk.web.domain.entity.constant.Status;
import com.trj.jk.web.util.HttpClientUtils;
import com.trj.jk.web.util.JsonUtils;

@Service("biOperatorService")
public class BiOperatorServiceImpl implements IOperatorService{

	private static final Logger	LOG	= LoggerFactory.getLogger(BiOperatorServiceImpl.class);
	
	@Value("${app.service.path}")
	private String biServicePath;
	
	/**
	 * 获取短信验证码type
	 */
	private static final String RESEND_RESET_PWD_CAPTCHA_TYPE="RESEND_RESET_PWD_CAPTCHA";
	
	/**
	 * 重置服务密码type
	 */
	private static final String SUBMIT_RESET_PWD_TYPE="SUBMIT_RESET_PWD";
	
	@Override
	public BiRetResult getVerifyCode(OperatorResetPwdBean bean) {
		Assert.notNull(bean.getName(),"姓名不可以为空！");
		Assert.notNull(bean.getIdentityId(),"身份证号不可以为空！");
		Assert.notNull(bean.getMobile(),"手机号不可以为空！");
		String url = biServicePath+"jxl_operator_resetpwd/";
		LOG.info("修改运营商密码获取验证码调用的url={}",url);
		Map<String, String> reqHeadMap = new HashMap<String, String>(); 
		reqHeadMap.put("Content-Type", "application/json");
		LOG.info("修改运营商密码获取验证码调用的reqHeadMap={}",reqHeadMap.toString());
		bean.setType(RESEND_RESET_PWD_CAPTCHA_TYPE);
		String postData = getPostData(bean);
		LOG.info("修改运营商密码获取验证码调用的postData={}",postData);
		String body = HttpClientUtils.httpPost(url, postData, null, reqHeadMap, null);
		LOG.info("修改运营商密码获取验证码调用的返回body={}",body);
		BiResult biResult = (BiResult)JsonUtils.string2Object(body, BiResult.class);
		//获取短息验证码只会返回1002码
		if(Status.STATUS_PROCESS_10002!=biResult.getRetcode()){
			throw new RuntimeException(biResult.getRetmessage());
		}
		List<BiRetResult> retresult = biResult.getRetresult();
		return retresult.get(0);
	}

	
	@Override
	public CertResult resetPwd(OperatorResetPwdBean bean) {
		CertResult result = new CertResult();
		Assert.notNull(bean.getVerifyCode(),"短信验证码不可以为空！");
		Assert.notNull(bean.getPassword(),"新密码不可以为空！");
		Assert.notNull(bean.getMobile(),"手机号不可以为空！");
		Assert.notNull(bean.getWebsite(),"website不可以为空！");
		Assert.notNull(bean.getToken(),"token不可以为空！");
		String url = biServicePath+"jxl_operator_resetpwd/";
		LOG.info("重置运营商服务密码调用的url={}",url);
		Map<String, String> reqHeadMap = new HashMap<String, String>(); 
		reqHeadMap.put("Content-Type", "application/json");
		LOG.info("重置运营商服务密码调用的reqHeadMap={}",reqHeadMap.toString());
		bean.setType(SUBMIT_RESET_PWD_TYPE);
		String postData = getPostData(bean);
		LOG.info("重置运营商服务密码调用的postData={}",postData);
		String body = HttpClientUtils.httpPost(url, postData, null, reqHeadMap, null);
		LOG.info("重置运营商服务密码调用的返回body={}",body);
		BiResult biResult = (BiResult)JsonUtils.string2Object(body, BiResult.class);
		int retCode = biResult.getRetcode();
		if(Status.STATUS_PROCESS_10001==retCode){
			result.setSuccess(true);
			result.setRetcode(retCode);
			result.setRetmessage("再次输入短信验证码！");
		}else if(Status.STATUS_PROCESS_10004==retCode){
			result.setSuccess(false);
			result.setRetcode(retCode);
			result.setRetmessage("短信验证码错误！");
		}else if(Status.STATUS_PROCESS_10006==retCode){
			result.setSuccess(false);
			result.setRetcode(retCode);
			result.setRetmessage("短信验证码失效系统已自动重新下发！");
		}else if(Status.STATUS_PROCESS_10010==retCode){
			result.setSuccess(false);
			result.setRetcode(retCode);
			result.setRetmessage("新密码格式错误！");
		}else if(Status.STATUS_PROCESS_11000==retCode){
			result.setSuccess(true);
			result.setRetcode(retCode);
			result.setRetmessage("重置密码成功！");
		}else if(Status.STATUS_PROCESS_30000==retCode){
			result.setSuccess(false);
			result.setRetcode(retCode);
			result.setRetmessage("网络异常，运营商异常或当天无法下发短信验证码所导致的无法登陆！");
		}else if(Status.STATUS_PROCESS_31000==retCode){
			result.setSuccess(false);
			result.setRetcode(retCode);
			result.setRetmessage("重置密码失败！建议到营业厅重置！");
		}else if(retCode<0){
			result.setSuccess(false);
			result.setRetcode(retCode);
			result.setRetmessage("请求超时,请重新认证!");
		}
		return result;
	}
	
	/**
	 *构造请求json字符串
	 * @param bean
	 * @return
	 */
	private String getPostData(OperatorResetPwdBean bean){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		if(RESEND_RESET_PWD_CAPTCHA_TYPE.equals(bean.getType())){//发送验证码
			dataMap.put("type", bean.getType());
			dataMap.put("name", bean.getName());
			dataMap.put("cell_phone_num", bean.getMobile());
			dataMap.put("id_card_num", bean.getIdentityId());
		}
		if(SUBMIT_RESET_PWD_TYPE.equals(bean.getType())){
			dataMap.put("type", bean.getType());
			dataMap.put("captcha", bean.getVerifyCode());
			dataMap.put("password", bean.getPassword());
			dataMap.put("cell_phone_num", bean.getMobile());
			dataMap.put("website", bean.getWebsite());
			dataMap.put("token", bean.getToken());
		}
		return JsonUtils.objectToJsonString(dataMap);
	}

}
