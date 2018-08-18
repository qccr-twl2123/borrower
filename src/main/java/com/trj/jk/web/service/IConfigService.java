package com.trj.jk.web.service;

import java.util.List;

import com.trj.jk.web.domain.BankBranch;
import com.trj.jk.web.domain.City;
import com.trj.jk.web.domain.Province;

public interface IConfigService {
	void changeMobile(Integer uid,String mobile,String pwd,String verifyCode);
	
	List<Province> getProvinces();
	
	List<City> getCitiesByProvinceCode(String code);
	
	List<BankBranch> getBankBranchesByCity(String city, String bankCode);


	List<BankBranch> getBankBranchesByCityCode(String cityCode, String bankCode);
}
