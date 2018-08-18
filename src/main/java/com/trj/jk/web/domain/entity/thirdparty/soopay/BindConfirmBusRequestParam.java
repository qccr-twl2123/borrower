package com.trj.jk.web.domain.entity.thirdparty.soopay;

import com.trj.jk.web.domain.UserBankCard;

public class BindConfirmBusRequestParam extends BindVerifyBusRequestParam{
	private String bind_no;
	private String verify_code;
	private String mer_cust_id;
	
	public BindConfirmBusRequestParam(Object obj, String verifyCode){
		super(obj);
		UserBankCard card = (UserBankCard)obj;
		this.bind_no = card.getBindNo();
		this.verify_code = verifyCode;
		this.mer_cust_id = card.getUid().toString();
		
	}
	public String getBind_no() {
		return bind_no;
	}
	public void setBind_no(String bind_no) {
		this.bind_no = bind_no;
	}
	public String getVerify_code() {
		return verify_code;
	}
	public void setVerify_code(String verify_code) {
		this.verify_code = verify_code;
	}
	public String getMer_cust_id() {
		return mer_cust_id;
	}
	public void setMer_cust_id(String mer_cust_id) {
		this.mer_cust_id = mer_cust_id;
	}
}
