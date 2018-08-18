package com.trj.jk.web.service.impl;


import java.util.List;

import com.trj.jk.web.model.request.LoginReq;
import com.trj.jk.web.model.request.RegisterReq;
import com.trj.jk.web.model.request.ResetPasswordReq;
import com.trj.jk.web.model.response.LoginRes;
import com.trj.jk.web.validator.Assert;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trj.jk.web.domain.UserBasic;
import com.trj.jk.web.domain.UserBasicCriteria;
import com.trj.jk.web.domain.UserExt;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.domain.entity.constant.Status;
import com.trj.jk.web.domain.entity.login.LoginResult;
import com.trj.jk.web.domain.entity.login.UserLogonInfo;
import com.trj.jk.web.domain.exception.LoginException;
import com.trj.jk.web.mapper.UserBasicMapper;
import com.trj.jk.web.mapper.UserExtMapper;
import com.trj.jk.web.mapper.jdbc.JdbcDao;
import com.trj.jk.web.service.ILoginService;
import com.trj.jk.web.util.DigestUtil;
import com.trj.jk.web.util.SessionUtil;
import com.trj.jk.web.util.UtilConstant;

import javax.rmi.CORBA.Util;


@Transactional
@Service
public class LoginServiceImpl implements ILoginService{
	
	@Autowired
	private UserBasicMapper userBasicMapper;
	
	@Autowired
	private UserExtMapper userExtMapper;
	
	@Autowired
	private JdbcDao jdbcDao;
	
	@Override
	public Boolean signOn(RegisterReq registerReq) {
		String status=jdbcDao.getTrjVerifyCodeStatus(registerReq.getMobile(), registerReq.getVerifyCode());
		Assert.isNull(status,ErrorMessageConstant.ERR_VERIFY_INCORRECT);
		Assert.isTrue("0".endsWith(status),ErrorMessageConstant.ERR_VERIFY_INVALID);

		UserBasicCriteria userBasicCriteria = new UserBasicCriteria();
		userBasicCriteria.createCriteria().andMobileEqualTo(registerReq.getMobile()).andTenantEqualTo(UtilConstant.TENANT_JKWEB);
		List<UserBasic> userBasicList = userBasicMapper.selectByCriteria(userBasicCriteria);
		Assert.isTrue(CollectionUtils.isNotEmpty(userBasicList),ErrorMessageConstant.MOBILE_REGISTERED);

		UserBasic userBasic = new UserBasic();
		userBasic.setMobile(registerReq.getMobile());
		userBasic.setPassword(DigestUtil.getMD5(registerReq.getPassword()));
		userBasic.setTenant(UtilConstant.TENANT_JKWEB);
		userBasicMapper.insertSelective(userBasic);
		
		UserExt ext = new UserExt();
		ext.setUid(userBasic.getId());
		userExtMapper.insertSelective(ext);
		return true;
	}

	@Override
	public UserBasic getUserByMobile(String mobile) {
		if(StringUtils.isEmpty(mobile)){
			throw new LoginException(ErrorMessageConstant.ERR_LOGIN_NULL);
		}
		UserBasicCriteria criteria = new UserBasicCriteria();
		criteria.createCriteria().andMobileEqualTo(mobile).andTenantEqualTo(UtilConstant.TENANT_JKWEB);
		List<UserBasic> list = userBasicMapper.selectByCriteria(criteria);
		if(list !=null && !list.isEmpty()) {
			UserBasic user=list.get(0);
			if(Status.USER_INVALID==user.getStatus()) {
				throw new LoginException(ErrorMessageConstant.ERR_STATUS_INVALID);
			}
			return user;
		}else {
			return null;
		}
	}
	
	@Override
	public LoginRes logon(LoginReq loginReq, boolean isEncrypt) {
		String mobile = loginReq.getMobile();
		String password = loginReq.getPassword();
		//md5加密后的密码
		String md5Pwd=password;
		if(!isEncrypt) {md5Pwd= DigestUtil.getMD5(password);}

		UserBasicCriteria example = new UserBasicCriteria();
		example.createCriteria().andMobileEqualTo(mobile).andTenantEqualTo(UtilConstant.TENANT_JKWEB);
		List<UserBasic> userBasicList =	userBasicMapper.selectByCriteria(example);
		Assert.isTrue(CollectionUtils.isEmpty(userBasicList),ErrorMessageConstant.MOBIEL_UNREGISTERED);
		UserBasic userBasic = userBasicList.get(0);
		//验证用户状态and密码的合法性
		Assert.isTrue(Status.USER_INVALID.equals(userBasic.getStatus()),ErrorMessageConstant.ERR_STATUS_INVALID);
		Assert.isTrue(!userBasic.getPassword().equals(md5Pwd),ErrorMessageConstant.ERR_LOGIN_INCORRECT);

		//用户状态更新
		SessionUtil.invalidSession();
		SessionUtil.setUserLogonInfo(userBasic.getId());
		//构建返回参数
		LoginRes result = new LoginRes();
		result.setUid(userBasic.getId());
		result.setName(userBasic.getName());
		result.setMobile(mobile);
		result.setIdentityId(userBasic.getIdentityId());
		return result;
		
	}
	
	
	@Override
	public void logout() {
		SessionUtil.invalidSession();
	}
	
	

	@Override
	public void resetPassword(ResetPasswordReq resetPasswordReq) {
		String status=jdbcDao.getTrjVerifyCodeStatus(resetPasswordReq.getMobile(), resetPasswordReq.getVerifyCode());
		Assert.isNull(status,ErrorMessageConstant.ERR_VERIFY_INCORRECT);
		Assert.isTrue("0".endsWith(status),ErrorMessageConstant.ERR_VERIFY_INVALID);

		UserBasicCriteria userBasicCriteria = new UserBasicCriteria();
		userBasicCriteria.createCriteria().andMobileEqualTo(resetPasswordReq.getMobile());

		List<UserBasic> userBasicList =	userBasicMapper.selectByCriteria(userBasicCriteria);
		Assert.isTrue(CollectionUtils.isEmpty(userBasicList),ErrorMessageConstant.MOBIEL_UNREGISTERED);

		UserBasic userBasic = new UserBasic();
		String pwd=resetPasswordReq.getPassword();
		String md5Pwd= DigestUtil.getMD5(pwd);
		userBasic.setPassword(md5Pwd);

		userBasicMapper.updateByCriteriaSelective(userBasic, userBasicCriteria);
	}
	
	@Override
	public int signOnAuto(UserBasic user) {
		if(StringUtils.isEmpty(user.getMobile())){
			throw new LoginException(ErrorMessageConstant.ERR_LOGIN_NULL);			
		}
		
		if(StringUtils.isEmpty(user.getPassword())){
			throw new LoginException(ErrorMessageConstant.ERR_LOGIN_NULL);
		}		
		
		UserBasicCriteria criteria = new UserBasicCriteria();
		criteria.createCriteria().andMobileEqualTo(user.getMobile());
		List<UserBasic> list = userBasicMapper.selectByCriteria(criteria);
		if(list !=null && !list.isEmpty()) {
			throw new LoginException(ErrorMessageConstant.MOBILE_REGISTERED);
		}
		
		String pwd=user.getPassword();
		
		String md5Pwd= DigestUtil.getMD5(pwd);
		
		user.setPassword(md5Pwd);
		
		userBasicMapper.insertSelective(user);
		
		UserExt ext = new UserExt();
		ext.setUid(user.getId());
		userExtMapper.insertSelective(ext);	
		
		return user.getId();
	}	



	
	

}
