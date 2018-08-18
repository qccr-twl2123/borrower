package com.trj.jk.web.domain.entity.personInfo;

import java.io.Serializable;

public class PersonInfoBean implements Serializable{

	/**
	 * 基本信息
	 */
	private BasicallyInfo basicallyInfo;
	
	/**
	 * 资产信息
	 */
	private AssetInfo assetInfo;
	
	/**
	 * 增信信息
	 */
	private CreditInfo creditInfo;

	public BasicallyInfo getBasicallyInfo() {
		return basicallyInfo;
	}

	public void setBasicallyInfo(BasicallyInfo basicallyInfo) {
		this.basicallyInfo = basicallyInfo;
	}

	public AssetInfo getAssetInfo() {
		return assetInfo;
	}

	public void setAssetInfo(AssetInfo assetInfo) {
		this.assetInfo = assetInfo;
	}

	public CreditInfo getCreditInfo() {
		return creditInfo;
	}

	public void setCreditInfo(CreditInfo creditInfo) {
		this.creditInfo = creditInfo;
	}
	
	
}
