package com.trj.jk.web.service;

import com.trj.jk.web.domain.UserBasic;
import com.trj.jk.web.domain.entity.login.LoginResult;
import com.trj.jk.web.model.request.LoginReq;
import com.trj.jk.web.model.request.RegisterReq;
import com.trj.jk.web.model.request.ResetPasswordReq;
import com.trj.jk.web.model.response.LoginRes;

import java.util.List;

public interface ILoginService {
	public Boolean signOn(RegisterReq registerReq);
	
	public int signOnAuto(UserBasic user);	
	
	public UserBasic getUserByMobile(String mobile);
	
	public LoginRes logon(LoginReq loginReq, boolean isEncrypt);
	
	public void resetPassword(ResetPasswordReq resetPasswordReq);
	
	public void logout();
	
}
