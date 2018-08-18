package com.trj.jk.web.domain.entity.thirdparty.soopay;

import com.trj.jk.web.domain.UserBankCard;

public class PayBusRequestParam {
	
	public PayBusRequestParam(){
		
	}
	
	public PayBusRequestParam(String tradeNo, String userIp, UserBankCard cardInfo){
		this.trade_no = tradeNo;
		this.mer_cust_id = cardInfo.getMerCustId()==null?"":cardInfo.getMerCustId();
		this.usr_busi_agreement_id = cardInfo.getUsrBusiAgreementId()==null?"":cardInfo.getUsrBusiAgreementId();
		this.usr_pay_agreement_id = cardInfo.getUsrPayAgreementId();
		this.user_ip = userIp==null?"":userIp;
	}
	
	
	private String trade_no;
	private String mer_cust_id;
	private String usr_busi_agreement_id;
	private String usr_pay_agreement_id;
	private String user_ip;
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	public String getMer_cust_id() {
		return mer_cust_id;
	}
	public void setMer_cust_id(String mer_cust_id) {
		this.mer_cust_id = mer_cust_id;
	}
	public String getUsr_busi_agreement_id() {
		return usr_busi_agreement_id;
	}
	public void setUsr_busi_agreement_id(String usr_busi_agreement_id) {
		this.usr_busi_agreement_id = usr_busi_agreement_id;
	}
	public String getUsr_pay_agreement_id() {
		return usr_pay_agreement_id;
	}
	public void setUsr_pay_agreement_id(String usr_pay_agreement_id) {
		this.usr_pay_agreement_id = usr_pay_agreement_id;
	}
	public String getUser_ip() {
		return user_ip;
	}
	public void setUser_ip(String user_ip) {
		this.user_ip = user_ip;
	}

}
