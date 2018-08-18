package com.trj.jk.web.domain.entity.authentication;

import java.io.Serializable;

import org.apache.http.conn.ssl.PrivateKeyStrategy;

public class AuthenticationResultBean implements Serializable{

	private String isIdentityAuth;
	
	private String mobileCertStatus;
	
	private String diplomaCertStatus;
	
	private String houseFundCertStatus;
	
	private String socialInsuranceCertStatus;
	
	private String alipayCertStatus;
	
	private String ecommenceCertStatus;
	
	private String professionCertStatus;
	
	private String mobileReallynameCertStatus;

	public String getIsIdentityAuth() {
		return isIdentityAuth;
	}

	public void setIsIdentityAuth(String isIdentityAuth) {
		this.isIdentityAuth = isIdentityAuth;
	}

	public String getMobileCertStatus() {
		return mobileCertStatus;
	}

	public void setMobileCertStatus(String mobileCertStatus) {
		this.mobileCertStatus = mobileCertStatus;
	}

	public String getDiplomaCertStatus() {
		return diplomaCertStatus;
	}

	public void setDiplomaCertStatus(String diplomaCertStatus) {
		this.diplomaCertStatus = diplomaCertStatus;
	}

	public String getHouseFundCertStatus() {
		return houseFundCertStatus;
	}

	public void setHouseFundCertStatus(String houseFundCertStatus) {
		this.houseFundCertStatus = houseFundCertStatus;
	}

	public String getSocialInsuranceCertStatus() {
		return socialInsuranceCertStatus;
	}

	public void setSocialInsuranceCertStatus(String socialInsuranceCertStatus) {
		this.socialInsuranceCertStatus = socialInsuranceCertStatus;
	}

	public String getAlipayCertStatus() {
		return alipayCertStatus;
	}

	public void setAlipayCertStatus(String alipayCertStatus) {
		this.alipayCertStatus = alipayCertStatus;
	}
	
	public String getEcommenceCertStatus() {
		return ecommenceCertStatus;
	}

	public void setEcommenceCertStatus(String ecommenceCertStatus) {
		this.ecommenceCertStatus = ecommenceCertStatus;
	}

	public String getProfessionCertStatus() {
		return professionCertStatus;
	}

	public void setProfessionCertStatus(String professionCertStatus) {
		this.professionCertStatus = professionCertStatus;
	}

	public String getMobileReallynameCertStatus() {
		return mobileReallynameCertStatus;
	}

	public void setMobileReallynameCertStatus(String mobileReallynameCertStatus) {
		this.mobileReallynameCertStatus = mobileReallynameCertStatus;
	}
	
	
}
