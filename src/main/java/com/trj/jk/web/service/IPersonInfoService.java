package com.trj.jk.web.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.trj.jk.web.domain.*;
import com.trj.jk.web.domain.entity.personInfo.AdressInfoBean;
import com.trj.jk.web.domain.entity.personInfo.DriveLicence;
import com.trj.jk.web.domain.entity.personInfo.InfoBean;
import com.trj.jk.web.domain.entity.personInfo.PersonInfoBean;
import com.trj.jk.web.domain.entity.personInfo.ShowUserCar;
import com.trj.jk.web.domain.entity.personInfo.ShowUserHouse;
import com.trj.jk.web.domain.entity.personInfo.certfication.CertificationPage;
import com.trj.jk.web.model.response.UserContactsRes;


public interface IPersonInfoService {
//	 PersonInfoBean getPersonInfo(Integer uid);
	
	 InfoBean getInfo(Integer uid);
	
	 UserExt getIdentityInfo(Integer uid);
	
	 List<UserProfession> getProfessionListByUid(Integer uid);
	
	 Integer addProfessionInfo(UserProfession userProfession);
	
	 UserProfession getProfessionInfo(Integer uid);
	
	 Integer updateProfessionInfo(UserProfession userProfession);
	
	 List<UserContacts> getContactsListByUid(Integer uid);


	 UserContacts getContactsInfo(Integer contactsId);
	
	 Integer addContactsInfo(UserContacts userContacts);
	
	 Integer updateContactsInfo(UserContacts userContacts);
	
	 Integer deleteContactsInfo(Integer id);
	
	 PageList<UserCar> getCarListByUid(Integer uid,PageBounds bean);
	
	 Integer addCarInfo(UserCar userCar);
	
	 ShowUserCar getCarInfo(Integer carId);
	
	 Integer updateCarInfo(UserCar userCar);
	
	 Integer deleteCarInfo(Integer carId);
	
	 PageList<UserHouse> getHouseListByUid(Integer uid,PageBounds pageBounds);
	
	 Integer addHouseInfo(UserHouse userHouse);
	
	 ShowUserHouse getHouseInfo(Integer houseId);
	
	 Integer updateHouseInfo(UserHouse userHouse);
	
	 Integer deleteHouseInfo(Integer houseId);
	
	 Map<String, Object> getScore(Integer uid);
	
	 List<String> getSalaryScope();
	
	 CertificationPage getCertificationPage(Integer userId, Integer productId, Integer loanApplyId) throws IllegalArgumentException, IllegalAccessException;
	
	 Integer addOrUpdateDriveLicenceInfo(Integer uid,UserDriveLicence driveLicence);
	
	 DriveLicence getDriveLicenceInfo(Integer uid);
	
	 Integer addOrUpdateAdressInfo(Integer uid,UserExt userExt);
	
	 AdressInfoBean getAdressInfo(Integer uid,Integer loanApplyId);
	
	 Map<String, Object> isIdentityAuth(Integer uid);

	 List<UserCreditValue> queryUserCreditValueListByUid(Integer uid);

}
