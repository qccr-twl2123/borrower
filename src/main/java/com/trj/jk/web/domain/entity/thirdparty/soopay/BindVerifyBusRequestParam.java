package com.trj.jk.web.domain.entity.thirdparty.soopay;

import com.trj.jk.web.domain.UserBankCard;
import com.trj.jk.web.domain.entity.enumtype.IDENTITY;

public class BindVerifyBusRequestParam {
	private String media_id;
	private String media_type;
	private String card_id;
	private String identity_type;
	private String identity_code;
	private String card_holder;
	
	public BindVerifyBusRequestParam(){
		
	}
	
	public BindVerifyBusRequestParam(Object obj) {
		UserBankCard card = (UserBankCard)obj;
		this.media_id = card.getMediaId();
		//手机
		this.media_type = "MOBILE";
		this.card_id = card.getCardId();
		this.identity_type = IDENTITY.IDENTITY_CARD.getDescription();
		this.identity_code = card.getIdentityCode();
		this.card_holder = card.getCardHolder();
	}
	
	public String getMedia_id() {
		return media_id;
	}
	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
	public String getMedia_type() {
		return media_type;
	}
	public void setMedia_type(String media_type) {
		this.media_type = media_type;
	}
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
	public String getIdentity_type() {
		return identity_type;
	}
	public void setIdentity_type(String identity_type) {
		this.identity_type = identity_type;
	}
	public String getIdentity_code() {
		return identity_code;
	}
	public void setIdentity_code(String identity_code) {
		this.identity_code = identity_code;
	}
	public String getCard_holder() {
		return card_holder;
	}
	public void setCard_holder(String card_holder) {
		this.card_holder = card_holder;
	}
}
