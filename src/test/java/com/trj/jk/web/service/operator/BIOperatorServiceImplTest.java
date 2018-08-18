package com.trj.jk.web.service.operator;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.trj.jk.web.domain.entity.authentication.bi.BiRetResult;
import com.trj.jk.web.domain.entity.authentication.bi.OperatorResetPwdBean;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BIOperatorServiceImplTest {
	
	@Value("${app.service.path}")
	private String biServicePath = null; 
	
	@Resource
	private IOperatorService operatorService;

	@Test
	public void testOperatorVerifyCode() {
		OperatorResetPwdBean bean = new OperatorResetPwdBean();
		bean.setName("张贵明");
		bean.setIdentityId("411526199210156759");
		bean.setMobile("15067146690");
		BiRetResult result= operatorService.getVerifyCode(bean);
	}
	
	@Test
	public void testOperatorResetPwd() {
		OperatorResetPwdBean bean = new OperatorResetPwdBean();
		bean.setVerifyCode("810542");
		bean.setPassword("121314");
		bean.setMobile("15067146690");
		bean.setWebsite("chinamobilezj");
		bean.setToken("5a6e41362af145ffaae0253701f3b301");
		operatorService.resetPwd(bean);
	}
}
