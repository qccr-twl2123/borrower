package com.trj.jk.web.controller;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.*;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.trj.jk.web.model.response.BankConfRes;
import com.trj.jk.web.service.impl.BankCardServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.trj.commons.result.Result;
import com.trj.jk.web.domain.entity.bankcard.BankCardBean;
import com.trj.jk.web.service.ILoginService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBankCardController {
	
	@Autowired
	private BankCardController bankCardController;

	@Autowired
	private BankCardServiceImpl bankCardService;
	
	@Test
	public void testBindRequest(){

		BankCardBean payCardBean = new BankCardBean();
		payCardBean.setCardHolder("胡高翔");
		payCardBean.setMediaId("15868480733");
		payCardBean.setCardId("6214835710080084");
		payCardBean.setIdentityCode("330722198302180035");
		payCardBean.setMobile("15868480733");
		payCardBean.setUid(5);
		
		Result<Object> result = null;
		try {
			 result = bankCardController.toBind(payCardBean);		
		}catch (Exception e) {
			e.printStackTrace();
		}
		

	}
	
	//@Test
	public void testBindConfirm(){

		BankCardBean payCardBean = new BankCardBean();
		payCardBean.setCardHolder("胡高翔");
		payCardBean.setMediaId("15868480733");
		payCardBean.setCardId("6214835710080084");
		payCardBean.setIdentityCode("330722198302180035");
		payCardBean.setMobile("15868480733");
		payCardBean.setBindNo("3702221803987023");
		payCardBean.setUid(5);		
		payCardBean.setId(1);
		
		Result<Object> result = null;
		try {
			 result = bankCardController.bindConfirm(payCardBean,"1380");		
		}catch (Exception e) {
			e.printStackTrace();
		}
		

	}	
	
	
//	
//	//@Test 
//	public void testBindConfirm() {
//		DBContextHolder.openCrmDB();
//		
//		payCardBean = new FiUserOnlinePayCard();
//		payCardBean.setCardHolder("胡高翔");
//		payCardBean.setMediaId("15868480733");
//		payCardBean.setCardId("6214835710080084");
//		payCardBean.setIdentityCode("330722198302180035");
//		payCardBean.setVerifyCode("3950");
//		payCardBean.setBindNo("3611082028050162");
//		payCardBean.setUid(48895);
//		payCardBean.setId(new Long(16));
//		
//		Object busBean = new BindConfirmBusRequestParam(payCardBean);
//		SoopayBusBean bean = new SoopayBusBean();
//		bean.setBusBean(busBean);
//		bean.setService("req_bind_confirm_shortcut");
//		ErrorResult result = null;
//		try {
//			 result = cardSoopayConnector.bindConfirm(bean, payCardBean);		
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		DBContextHolder.closeCrmDB();
//		
//	}
//	
//	//@Test
//	public void testTrade(){
//		DBContextHolder.openCrmDB();
//		
//		userPrjRepayRecordBean = new FiUserRepayRecord();
//		userPrjRepayRecordBean.setUid(48895);
//		userPrjRepayRecordBean.setRepayInfoId(8l);
//	//	userPrjRepayRecordBean.setRepayPlanId("1");
////		userPrjRepayRecordBean.setGoodsId("3");
//		userPrjRepayRecordBean.setOrderId("GMDD201512280000003");
//		userPrjRepayRecordBean.setAmount(new BigDecimal(1.00));
////		userPrjRepayRecordBean.setExpireTime("1440");
////		userPrjRepayRecordBean.setRiskExpand("");
////		userPrjRepayRecordBean.setGoodsInf("");
////		userPrjRepayRecordBean.setUserIp("");
////		userPrjRepayRecordBean.setExpand("");
////		userPrjRepayRecordBean.setMerPriv("");
//		
//		Object busBean = new TradeBusRequestParam(userPrjRepayRecordBean);
//		SoopayBusBean bean = new SoopayBusBean();
//		bean.setBusBean(busBean);
//		bean.setService("pay_req_shortcut");
//		ErrorResult result = null;	
//		try {
//			  result = tradeSoopayConnector.trade(bean, userPrjRepayRecordBean);
//	
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//		DBContextHolder.closeCrmDB();
//				
//	}
//	
//	@Test
//	public void testPay(){
//		DBContextHolder.openCrmDB();
//		
//		userPrjRepayRecordBean = new FiUserRepayRecord();
//		userPrjRepayRecordBean.setUid(48895);
//		userPrjRepayRecordBean.setRepayInfoId(8l);
//	//	userPrjRepayRecordBean.setGoodsId("3");		
//		FiUserOnlinePayCard defaultCard = bankcardPayService.getUserDefaultCard(48895);
//		Object busBean = new PayBusRequestParam("3611191500906836", userPrjRepayRecordBean.getUserIp(),defaultCard);
//		SoopayBusBean bean = new SoopayBusBean();
//		bean.setBusBean(busBean);
//		bean.setService("agreement_pay_confirm_shortcut");		
//		ErrorResult result = null;	
//		try {
//			result = tradeSoopayConnector.pay(bean, userPrjRepayRecordBean);
//	
//		}catch(Exception e){
//			e.printStackTrace();
//		}		
//		
//		DBContextHolder.closeCrmDB();
//	}
	@Test
	public void test(){
		String test = "LDC99999994444222";
		
		String TEL_REGEX = "^[a-zA-Z0-9]{17}$";
		System.out.println(test.matches(TEL_REGEX));
	
	}
	
	
	@Test
	public void test1(){
		
		java.util.Random r=new java.util.Random(); 
		  for(int i=0;i<6;i++){ 
		    System.out.println(r.nextInt(10));
		  }
	
	}

	@Test
	public void getBankConfList(){
		List<BankConfRes> list = bankCardService.getBankConfList();
		System.out.println(JSON.toJSONString(list));
	}

	@Test
	public void testGetBankBranchesByCityCode(){
		String city = "宁德市";
		String bankCode ="001";
		System.out.println(JSON.toJSONString(bankCardController.getBankBranchesByCityCode(city,bankCode)));
	}





}
	
	  
