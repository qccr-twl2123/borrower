package com.trj.jk.web.domain.entity;

import java.io.Serializable;

public class ProductSearchBean implements Serializable{
	
	private String city;
	
	private Byte limitCode;
	
	private Integer limitScopeFrom;
	
	private Integer limitScopeTo;
	
	private Byte termCode;
	
	private Integer termFromDay;
	
	private Integer termToDay;
	
	private Byte sort;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Byte getLimitCode() {
		return limitCode;
	}

	public void setLimitCode(Byte limitCode) {
		this.limitCode = limitCode;
	}

	public Integer getLimitScopeFrom() {
		return limitScopeFrom;
	}

	public void setLimitScopeFrom(Integer limitScopeFrom) {
		this.limitScopeFrom = limitScopeFrom;
	}

	public Integer getLimitScopeTo() {
		return limitScopeTo;
	}

	public void setLimitScopeTo(Integer limitScopeTo) {
		this.limitScopeTo = limitScopeTo;
	}

	public Byte getTermCode() {
		return termCode;
	}

	public void setTermCode(Byte termCode) {
		this.termCode = termCode;
	}

	public Integer getTermFromDay() {
		return termFromDay;
	}

	public void setTermFromDay(Integer termFromDay) {
		this.termFromDay = termFromDay;
	}

	public Integer getTermToDay() {
		return termToDay;
	}

	public void setTermToDay(Integer termToDay) {
		this.termToDay = termToDay;
	}

	public Byte getSort() {
		return sort;
	}

	public void setSort(Byte sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "ProductSearchBean [city=" + city + ", limitCode=" + limitCode + ", limitScopeFrom=" + limitScopeFrom
				+ ", limitScopeTo=" + limitScopeTo + ", termCode=" + termCode + ", termFromDay=" + termFromDay
				+ ", termToDay=" + termToDay + ", sort=" + sort + "]";
	}

	
}
