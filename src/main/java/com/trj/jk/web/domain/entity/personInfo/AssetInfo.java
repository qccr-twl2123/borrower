package com.trj.jk.web.domain.entity.personInfo;

import java.io.Serializable;
import java.util.List;


public class AssetInfo implements Serializable{
	/**
	 * 车辆信息
	 */
	private List<ShowUserCar> userCars;
	
	/**
	 * 房产信息
	 */
	private List<ShowUserHouse> userHouses;

	/**
	 * 公积金
	 */
	private Byte accumulationFund;
	

	public List<ShowUserCar> getUserCars() {
		return userCars;
	}

	public void setUserCars(List<ShowUserCar> userCars) {
		this.userCars = userCars;
	}

	public List<ShowUserHouse> getUserHouses() {
		return userHouses;
	}

	public void setUserHouses(List<ShowUserHouse> userHouses) {
		this.userHouses = userHouses;
	}

	public Byte getAccumulationFund() {
		return accumulationFund;
	}

	public void setAccumulationFund(Byte accumulationFund) {
		this.accumulationFund = accumulationFund;
	}

	
	
}
