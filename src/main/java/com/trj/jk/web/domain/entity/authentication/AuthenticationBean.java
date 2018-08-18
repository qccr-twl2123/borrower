package com.trj.jk.web.domain.entity.authentication;

import java.io.Serializable;

public class AuthenticationBean implements Serializable{
	
	Integer loanApplyId;

	//姓名
	private String name;
	
	private Byte source;
	
	//省份证
	private String identityId;
	
	//地区
	private String area;
	
	//手机号
	private String mobile;
	
	//服务密码
	private String servicePwd;
	
	//短信验证码
	private String mobileValidateCode;
	
	//学信网账号
	private String xxAccount;
	
	//学信网密码
	private String xxPwd;
	
	//支付宝账号
	private String alipayAccount;
	
	//支付宝登录密码
	private String alipayPwd;
	
	//邮箱账号
	private String email;
	
	//邮箱密码
	private String emailPwd;
	
	//独立密码
	private String independentPwd;
	
	//征信账号
	private String creditAccount;
	
	//征信密码
	private String creditPwd;
	
	//社保账号
	private String socialInsuranceAccount;
	
	//社保密码
	private String socialInsurancePwd;
	
	//公积金账号
	private String houseFundAccount;
	
	//公积金密码
	private String houseFundPwd;

	//移动第二次短信验证码
	private String cmccVerifyCode;
	
	//运营商认证类型：０　实名认证　１　服务认证
	private Byte operatorVerifyType;
	
	//京东账户
	private String accountJd;
	//京东密码
	private String passwordJd;

	//排序
	private String sort;
	// 社保登录信息，目前无法预知全部参数
	private String soinsBean;

	//运营商认证联系人信息
	private String contactTel1;
	private String contactName1;
	private String contactType1;
	private String contactTel2;
	private String contactName2;
	private String contactType2;	
	private String contactTel3;
	private String contactName3;	
	private String contactType3;

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getAccountJd() {
		return accountJd;
	}

	public void setAccountJd(String accountJd) {
		this.accountJd = accountJd;
	}

	public String getPasswordJd() {
		return passwordJd;
	}

	public void setPasswordJd(String passwordJd) {
		this.passwordJd = passwordJd;
	}

	
	public String getContactTel1() {
		return contactTel1;
	}

	public void setContactTel1(String contactTel1) {
		this.contactTel1 = contactTel1;
	}

	public String getContactName1() {
		return contactName1;
	}

	public void setContactName1(String contactName1) {
		this.contactName1 = contactName1;
	}

	public String getContactType1() {
		return contactType1;
	}

	public void setContactType1(String contactType1) {
		this.contactType1 = contactType1;
	}

	public String getContactTel2() {
		return contactTel2;
	}

	public void setContactTel2(String contactTel2) {
		this.contactTel2 = contactTel2;
	}

	public String getContactName2() {
		return contactName2;
	}

	public void setContactName2(String contactName2) {
		this.contactName2 = contactName2;
	}

	public String getContactType2() {
		return contactType2;
	}

	public void setContactType2(String contactType2) {
		this.contactType2 = contactType2;
	}

	public String getContactTel3() {
		return contactTel3;
	}

	public void setContactTel3(String contactTel3) {
		this.contactTel3 = contactTel3;
	}

	public String getContactName3() {
		return contactName3;
	}

	public void setContactName3(String contactName3) {
		this.contactName3 = contactName3;
	}

	public String getContactType3() {
		return contactType3;
	}

	public void setContactType3(String contactType3) {
		this.contactType3 = contactType3;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentityId() {
		return identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getServicePwd() {
		return servicePwd;
	}

	public void setServicePwd(String servicePwd) {
		this.servicePwd = servicePwd;
	}

	public String getMobileValidateCode() {
		return mobileValidateCode;
	}

	public void setMobileValidateCode(String mobileValidateCode) {
		this.mobileValidateCode = mobileValidateCode;
	}

	public String getXxAccount() {
		return xxAccount;
	}

	public void setXxAccount(String xxAccount) {
		this.xxAccount = xxAccount;
	}

	public String getXxPwd() {
		return xxPwd;
	}

	public void setXxPwd(String xxPwd) {
		this.xxPwd = xxPwd;
	}

	public String getAlipayAccount() {
		return alipayAccount;
	}

	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}

	public String getAlipayPwd() {
		return alipayPwd;
	}

	public void setAlipayPwd(String alipayPwd) {
		this.alipayPwd = alipayPwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailPwd() {
		return emailPwd;
	}

	public void setEmailPwd(String emailPwd) {
		this.emailPwd = emailPwd;
	}

	public String getIndependentPwd() {
		return independentPwd;
	}

	public void setIndependentPwd(String independentPwd) {
		this.independentPwd = independentPwd;
	}

	public String getCreditAccount() {
		return creditAccount;
	}

	public void setCreditAccount(String creditAccount) {
		this.creditAccount = creditAccount;
	}

	public String getCreditPwd() {
		return creditPwd;
	}

	public void setCreditPwd(String creditPwd) {
		this.creditPwd = creditPwd;
	}

	public String getSocialInsuranceAccount() {
		return socialInsuranceAccount;
	}

	public void setSocialInsuranceAccount(String socialInsuranceAccount) {
		this.socialInsuranceAccount = socialInsuranceAccount;
	}

	public String getSocialInsurancePwd() {
		return socialInsurancePwd;
	}

	public void setSocialInsurancePwd(String socialInsurancePwd) {
		this.socialInsurancePwd = socialInsurancePwd;
	}

	public String getHouseFundAccount() {
		return houseFundAccount;
	}

	public void setHouseFundAccount(String houseFundAccount) {
		this.houseFundAccount = houseFundAccount;
	}

	public String getHouseFundPwd() {
		return houseFundPwd;
	}

	public void setHouseFundPwd(String houseFundPwd) {
		this.houseFundPwd = houseFundPwd;
	}
	
	public String getCmccVerifyCode() {
		return cmccVerifyCode;
	}

	public void setCmccVerifyCode(String cmccVerifyCode) {
		this.cmccVerifyCode = cmccVerifyCode;
	}	
	
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	public Byte getSource() {
		return source;
	}

	public void setSource(Byte source) {
		this.source = source;
	}
	
	public Integer getLoanApplyId() {
		return loanApplyId;
	}

	public void setLoanApplyId(Integer loanApplyId) {
		this.loanApplyId = loanApplyId;
	}

	public String getSoinsBean() {
		return soinsBean;
	}

	public void setSoinsBean(String soinsBean) {
		this.soinsBean = soinsBean;
	}

	@Override
	public String toString() {
		return "AuthenticationBean [loanApplyId=" + loanApplyId + ", name=" + name + ", source=" + source
				+ ", identityId=" + identityId + ", area=" + area + ", mobile=" + mobile + ", servicePwd=" + servicePwd
				+ ", mobileValidateCode=" + mobileValidateCode + ", xxAccount=" + xxAccount + ", xxPwd=" + xxPwd
				+ ", alipayAccount=" + alipayAccount + ", alipayPwd=" + alipayPwd + ", email=" + email + ", emailPwd="
				+ emailPwd + ", independentPwd=" + independentPwd + ", creditAccount=" + creditAccount + ", creditPwd="
				+ creditPwd + ", socialInsuranceAccount=" + socialInsuranceAccount + ", socialInsurancePwd="
				+ socialInsurancePwd + ", houseFundAccount=" + houseFundAccount + ", houseFundPwd=" + houseFundPwd
				+ ", cmccVerifyCode=" + cmccVerifyCode + ", operatorVerifyType=" + operatorVerifyType + ", accountJd="
				+ accountJd + ", passwordJd=" + passwordJd + ", sort=" + sort + ", contactTel1=" + contactTel1
				+ ", contactName1=" + contactName1 + ", contactType1=" + contactType1 + ", contactTel2=" + contactTel2
				+ ", contactName2=" + contactName2 + ", contactType2=" + contactType2 + ", contactTel3=" + contactTel3
				+ ", contactName3=" + contactName3 + ", contactType3=" + contactType3 + "]";
	}	
	
	
}
