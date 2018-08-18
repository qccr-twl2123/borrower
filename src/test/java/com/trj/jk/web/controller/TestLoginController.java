package com.trj.jk.web.controller;

import com.alibaba.fastjson.JSON;
import com.trj.jk.web.model.request.CheckMobileReq;
import com.trj.jk.web.model.request.RegisterReq;
import com.trj.jk.web.model.request.ResetPasswordReq;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.trj.commons.result.Result;
import com.trj.jk.web.domain.UserBasic;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLoginController {
	
	@Autowired
	private LoginController loginController;
	
	private static Gson gson = new Gson();
	@Test
	public void testSignon(){
		RegisterReq registerReq = new RegisterReq();
		registerReq.setMobile("17899033421");
		registerReq.setPassword("m1232323");
		registerReq.setVerifyCode("8893");
		System.out.println(JSON.toJSONString(loginController.signOn(registerReq)));
	}
	
	@Test
	public void testIsRegistered(){
		String mobile = "15355099765";
		loginController.isRegisterUser(mobile);
	}
	

	@Test
	public void testLogin(){

	}

	@Test
	public void testFindPassword(){
		ResetPasswordReq resetPasswordReq = new ResetPasswordReq();
		resetPasswordReq.setMobile("15355099654");
		resetPasswordReq.setPassword("31231ss");
		resetPasswordReq.setVerifyCode("wwe22");
		System.out.println(JSON.toJSONString(loginController.findPassword(resetPasswordReq)));
	}




}
