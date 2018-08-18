package com.trj.jk.web.domain.entity.personInfo;

import java.io.Serializable;

public class AdressInfoBean implements Serializable{
	
	private String residentialProvince;
	
	private String residentialCity;
	
	private String residentialDistrict;
	
	private String residentialAddress;
	
	private String familyProvince;
	
	private String familyCity;
	
	private String familyDistrict;
	
	private String familyAddress;

	private String decorationAddress;
	
	private String decorationDistrict;

	private String decorationCity;

	private String decorationProvince;

	public String getResidentialProvince() {
		return residentialProvince;
	}

	public void setResidentialProvince(String residentialProvince) {
		this.residentialProvince = residentialProvince;
	}

	public String getResidentialCity() {
		return residentialCity;
	}

	public void setResidentialCity(String residentialCity) {
		this.residentialCity = residentialCity;
	}

	public String getResidentialDistrict() {
		return residentialDistrict;
	}

	public void setResidentialDistrict(String residentialDistrict) {
		this.residentialDistrict = residentialDistrict;
	}

	public String getResidentialAddress() {
		return residentialAddress;
	}

	public void setResidentialAddress(String residentialAddress) {
		this.residentialAddress = residentialAddress;
	}

	public String getFamilyProvince() {
		return familyProvince;
	}

	public void setFamilyProvince(String familyProvince) {
		this.familyProvince = familyProvince;
	}

	public String getFamilyCity() {
		return familyCity;
	}

	public void setFamilyCity(String familyCity) {
		this.familyCity = familyCity;
	}

	public String getFamilyDistrict() {
		return familyDistrict;
	}

	public void setFamilyDistrict(String familyDistrict) {
		this.familyDistrict = familyDistrict;
	}

	public String getFamilyAddress() {
		return familyAddress;
	}

	public void setFamilyAddress(String familyAddress) {
		this.familyAddress = familyAddress;
	}

	public String getDecorationAddress() {
		return decorationAddress;
	}

	public void setDecorationAddress(String decorationAddress) {
		this.decorationAddress = decorationAddress;
	}

	public String getDecorationDistrict() {
		return decorationDistrict;
	}

	public void setDecorationDistrict(String decorationDistrict) {
		this.decorationDistrict = decorationDistrict;
	}

	public String getDecorationCity() {
		return decorationCity;
	}

	public void setDecorationCity(String decorationCity) {
		this.decorationCity = decorationCity;
	}

	public String getDecorationProvince() {
		return decorationProvince;
	}

	public void setDecorationProvince(String decorationProvince) {
		this.decorationProvince = decorationProvince;
	}

	@Override
	public String toString() {
		return "AdressInfoBean [residentialProvince=" + residentialProvince + ", residentialCity=" + residentialCity
				+ ", residentialDistrict=" + residentialDistrict + ", residentialAddress=" + residentialAddress
				+ ", familyProvince=" + familyProvince + ", familyCity=" + familyCity + ", familyDistrict="
				+ familyDistrict + ", familyAddress=" + familyAddress + ", decorationAddress=" + decorationAddress
				+ ", decorationDistrict=" + decorationDistrict + ", decorationCity=" + decorationCity
				+ ", decorationProvince=" + decorationProvince + "]";
	}

}
