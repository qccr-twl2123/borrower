package com.trj.jk.web.controller;


import com.alibaba.fastjson.JSON;
import com.trj.commons.result.Result;
import com.trj.jk.web.domain.LoanLimit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserController {
	

	@Autowired
	private UserController userController;

	@Test
	public void doSignature(){
		LoanLimit loanLimit = new LoanLimit();
		loanLimit.setLoanApplyId(7048);
		loanLimit.setUid(765);
		String code = "dsada";
		Result<Object>  result = userController.doSignature(loanLimit, code);
		System.out.println(JSON.toJSONString(result));
	}


	@Test
	public void testVerifyCode(){
		String mobile  ="13148317787";
		String code ="004911";
		Integer cardId = 515;
		System.out.println(JSON.toJSONString(userController.verifyCode(mobile,code,cardId)));
	}

}
