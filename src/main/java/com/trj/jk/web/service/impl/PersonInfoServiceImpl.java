package com.trj.jk.web.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.gson.Gson;
import com.trj.jk.web.domain.*;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.domain.entity.personInfo.*;
import com.trj.jk.web.domain.entity.personInfo.certfication.CertficationInfo;
import com.trj.jk.web.domain.entity.personInfo.certfication.CertificationPage;
import com.trj.jk.web.domain.entity.regex.RegexContext;
import com.trj.jk.web.domain.exception.CheckException;
import com.trj.jk.web.mapper.*;
import com.trj.jk.web.service.IPersonInfoService;
import com.trj.jk.web.util.UtilConstant;
import com.umpay.api.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.*;

@Service
@Transactional
public class PersonInfoServiceImpl implements IPersonInfoService{
	
	private static Gson gson=new Gson();
	
	@Value("${app.upload.download}")
	private String downloadImgUrl;	
	
	@Resource
	private UserProfessionMapper userProfessionMapper;
	
	@Resource
	private UserContactsMapper userContactsMapper;
	
	@Resource
	private UserCarMapper userCarMapper;
	
	@Resource
	private UserHouseMapper userHouseMapper;
	
	@Resource
	private UserExtMapper userExtMapper;
	
	@Resource
	private UserCertficationMapper userCertficationMapper;
	
	@Resource
	private UserBasicMapper userBasicMapper;
	
	@Resource
	private AttachmentMapper attachmentMapper;
	
	@Resource
	private ProvinceMapper provinceMapper;
	
	@Resource
	private CityMapper cityMapper;
	
	@Resource
	private CodeMapper codeMapper;
	
	@Resource
	private LoanProductMapper productMapper;

	@Resource
	private UserCreditValueMapper userCreditValueMapper;
	
	@Resource
	private LoanApplyAddressMapper loanApplyAddressMapper;
	
	@Resource
	private UserDriveLicenceMapper userDriveLicenceMapper;
	
	@Resource
	private LoanApplyCarMapper loanApplyCarMapper;

	@Override
	public List<UserProfession> getProfessionListByUid(Integer uid) {
		UserProfessionCriteria userProfessionCriteria = new UserProfessionCriteria();
		userProfessionCriteria.createCriteria().andUidEqualTo(uid);
		return userProfessionMapper.selectByCriteria(userProfessionCriteria);
	}

	@Override
	public Integer addProfessionInfo(UserProfession userProfession) {
		if(StringUtil.isNotEmpty(userProfession.getCorpEmail())){
			if(!userProfession.getCorpEmail().matches(RegexContext.EMAIL_REGEX)){
				throw new CheckException(ErrorMessageConstant.ERR_EMAIL_ERROR);
			}
		}
		if(!userProfession.getCorpTel().matches(RegexContext.TEL_REGEX)){
			throw new CheckException(ErrorMessageConstant.ERR_TEL_ERROR);
		}
					
		return userProfessionMapper.insert(userProfession);
		
	}

	
	@Override
	public List<UserContacts> getContactsListByUid(Integer uid) {
		UserContactsCriteria userContactsCriteria = new UserContactsCriteria();
		userContactsCriteria.createCriteria().andUidEqualTo(uid);
		return userContactsMapper.selectByCriteria(userContactsCriteria);
	}



	@Override
	public Integer addContactsInfo(UserContacts userContacts) {
		if(userContacts.getMobile()!=null && (userContacts.getMobile().startsWith("86") ||userContacts.getMobile().startsWith("+86"))){
			userContacts.setMobile(userContacts.getMobile().substring(userContacts.getMobile().indexOf("6")+1, userContacts.getMobile().length()));
		}
		UserContactsCriteria userContactsCriteria = new UserContactsCriteria();
		userContactsCriteria.createCriteria().andUidEqualTo(userContacts.getUid()).andMobileEqualTo(userContacts.getMobile());
		List<UserContacts> userContactsList = userContactsMapper.selectByCriteria(userContactsCriteria);
		if(userContactsList!=null && !userContactsList.isEmpty()){
			throw new CheckException(ErrorMessageConstant.ERR_DATA_ERROR);
		}
		if(!userContacts.getMobile().matches(RegexContext.MOBILE_REGEX)){
			throw new CheckException(ErrorMessageConstant.ERR_MOBILE_ERROR);
		}
		return userContactsMapper.insert(userContacts);
	}

	
	@Override
	public PageList<UserCar>  getCarListByUid(Integer uid,PageBounds bean) {
		UserCarCriteria userCarCriteria = new UserCarCriteria();
		userCarCriteria.createCriteria().andUidEqualTo(uid);
		userCarMapper.selectByCriteria(userCarCriteria);
		return (PageList<UserCar> )userCarMapper.selectByCriteriaWithRowbounds(userCarCriteria, bean);
	}

	@Override
	public Integer addCarInfo(UserCar userCar) {
		UserCarCriteria userCarCriteria = new UserCarCriteria();
		userCarCriteria.createCriteria().andUidEqualTo(userCar.getUid()).andCarNoEqualTo(userCar.getCarNo().trim());
		List<UserCar> userCars = userCarMapper.selectByCriteria(userCarCriteria);
		if(userCars!=null&&!userCars.isEmpty()){
			throw new CheckException(ErrorMessageConstant.ERR_DATA_CAR_ERROR);
		}
		if(!userCar.getCarNo().matches(RegexContext.CARNO_REGEX)){
			throw new CheckException(ErrorMessageConstant.ERR_CARNO_ERROR);
		}
		if(!userCar.getOwner().matches(RegexContext.CHINESE_REGEX)){
			throw new CheckException(ErrorMessageConstant.ERR_NAME_ERROR);
		}
		if(!userCar.getVinNo().matches(RegexContext.CARVIN_REGEX)){
			throw new CheckException(ErrorMessageConstant.ERR_CARVIN_ERROR);
		}
		return userCarMapper.insert(userCar);
	}
	
	@Override
	public PageList<UserHouse> getHouseListByUid(Integer uid,PageBounds pageBounds) {
		UserHouseCriteria userHouseCriteria = new UserHouseCriteria();
		userHouseCriteria.createCriteria().andUidEqualTo(uid);
		return (PageList<UserHouse>)userHouseMapper.selectByCriteriaWithRowbounds(userHouseCriteria, pageBounds);
	}

	@Override
	public Integer addHouseInfo(UserHouse userHouse) {
		UserHouseCriteria userHouseCriteria = new UserHouseCriteria();
		userHouseCriteria.createCriteria().andUidEqualTo(userHouse.getUid()).andAddressEqualTo(userHouse.getAddress());
		List<UserHouse> userHouses = userHouseMapper.selectByCriteria(userHouseCriteria);
		if(userHouses!=null && !userHouses.isEmpty()){
			throw new CheckException(ErrorMessageConstant.ERR_DATA_ERROR);
		}
		if(!userHouse.getAddress().matches(RegexContext.ADRESS_REGEX)){
			throw new CheckException(ErrorMessageConstant.ERR_ADRESS_ERROR);
		}
		if(!userHouse.getOwner().matches(RegexContext.CHINESE_REGEX)){
			throw new CheckException(ErrorMessageConstant.ERR_NAME_ERROR);
		}
		return userHouseMapper.insert(userHouse);
	}

	
//	@Override
//	public PersonInfoBean getPersonInfo(Integer uid) {
//		PersonInfoBean personInfoBean = new PersonInfoBean();
//		//获取基本信息
//		BasicallyInfo basicallyInfo = new BasicallyInfo();
//		UserExtCriteria userExtCriteria = new UserExtCriteria();
//		userExtCriteria.createCriteria().andUidEqualTo(uid);
//		List<UserExt> UserExtList= userExtMapper.selectByCriteria(userExtCriteria);
//		if(UserExtList!=null && !UserExtList.isEmpty()){
//			//获取驾驶证信息
//			String driveLicenceNo = UserExtList.get(0).getDriveLicence();
//			String name = UserExtList.get(0).getName();
//			if(driveLicenceNo!=null && name!=null){
//				DriveLicence driveLicence = new DriveLicence();
//				driveLicence.setName(name);
//				driveLicence.setDriveLicenceNo(driveLicenceNo);
//				basicallyInfo.setDriverLicence(driveLicence);
//			}
//			basicallyInfo.setUserExt(UserExtList.get(0));
//		}
//		UserProfessionCriteria userProfessionCriteria = new UserProfessionCriteria();
//		userProfessionCriteria.createCriteria().andUidEqualTo(uid);
//		List<UserProfession> userProfessionsList = userProfessionMapper.selectByCriteria(userProfessionCriteria);
//		basicallyInfo.setUserProfessions(userProfessionsList);
//		UserContactsCriteria userContactsCriteria = new UserContactsCriteria();
//		userContactsCriteria.createCriteria().andUidEqualTo(uid);
//		List<UserContacts> userContactsList = userContactsMapper.selectByCriteria(userContactsCriteria);
//		if(userContactsList!=null && !userContactsList.isEmpty()){
//			basicallyInfo.setUserContacts(userContactsList);
//		}
//		personInfoBean.setBasicallyInfo(basicallyInfo);
//		//获取资产信息
//		AssetInfo assetInfo = new AssetInfo();
//		UserCarCriteria userCarCriteria = new UserCarCriteria();
//		userCarCriteria.createCriteria().andUidEqualTo(uid);
//		List<UserCar> userCarList = userCarMapper.selectByCriteria(userCarCriteria);
//		if(userCarList!=null && !userCarList.isEmpty()){
//			List<ShowUserCar> showUserCarList = new ArrayList<ShowUserCar>();
//			for(UserCar userCar:userCarList){
//				if(userCar!=null && userCar.getAttachId()!=null){
//					showUserCarList.add(userCarChangeShowUserCar(userCar));
//				}
//			}
//			assetInfo.setUserCars(showUserCarList);
//		}
//		
//		UserHouseCriteria userHouseCriteria = new UserHouseCriteria();
//		userHouseCriteria.createCriteria().andUidEqualTo(uid);
//		List<UserHouse> userHouseList = userHouseMapper.selectByCriteria(userHouseCriteria);
//		if(userHouseList!=null && !userHouseList.isEmpty()){
//			List<ShowUserHouse> showUserHouseList = new ArrayList<ShowUserHouse>();
//			for(UserHouse userHouse:userHouseList){
//				showUserHouseList.add(userHouseChangeShowUserHouse(userHouse));
//			}
//			assetInfo.setUserHouses(showUserHouseList);
//		}
//		
//		UserBasic userBasic = userBasicMapper.selectByPrimaryKey(uid);
//		if(userBasic!=null && userBasic.getHouseFundCertStatus()!=null){				
//			assetInfo.setAccumulationFund(userBasic.getHouseFundCertStatus());
//		}
//		
//		personInfoBean.setAssetInfo(assetInfo);
//		
//		//获取信用信息
//		CreditInfo creditInfo = new CreditInfo();
//		if(userBasic!=null && userBasic.getCreditCardCertStatus()!=null){				
//			creditInfo.setCreditCard(userBasic.getCreditCardCertStatus());
//		}
//		if(userBasic!=null && userBasic.getCreditInformationCertStatus()!=null){				
//			creditInfo.setCreditInformation(userBasic.getCreditInformationCertStatus());
//		}
//		personInfoBean.setCreditInfo(creditInfo);
//		return personInfoBean;
//
//	}

	@Override
	public UserProfession getProfessionInfo(Integer uid) {
		UserProfessionCriteria userProfessionCriteria = new UserProfessionCriteria();
		userProfessionCriteria.createCriteria().andUidEqualTo(uid);
		List<UserProfession> userProfessionList = userProfessionMapper.selectByCriteria(userProfessionCriteria);
		if(userProfessionList!=null && !userProfessionList.isEmpty()){
			return userProfessionList.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public Integer updateProfessionInfo(UserProfession userProfession) {
		if(StringUtil.isNotEmpty(userProfession.getCorpEmail())){
			if(!userProfession.getCorpEmail().matches(RegexContext.EMAIL_REGEX)){
				throw new CheckException(ErrorMessageConstant.ERR_EMAIL_ERROR);
			}
		}
		if(!userProfession.getCorpTel().matches(RegexContext.TEL_REGEX)){
			throw new CheckException(ErrorMessageConstant.ERR_TEL_ERROR);
		}
		return userProfessionMapper.updateByPrimaryKey(userProfession);
	}

	@Override
	public UserContacts getContactsInfo(Integer contactsId) {
		return userContactsMapper.selectByPrimaryKey(contactsId);
	}

	@Override
	public Integer updateContactsInfo(UserContacts userContacts) {
		return userContactsMapper.updateByPrimaryKey(userContacts);
	}

	@Override
	public ShowUserCar getCarInfo(Integer carId) {
		ShowUserCar showUserCar = null;
		UserCar userCar = userCarMapper.selectByPrimaryKey(carId);
		if(userCar!=null && userCar.getAttachId()!=null){
			return userCarChangeShowUserCar(userCar);
		}
		return showUserCar;
	}

	@Override
	public ShowUserHouse getHouseInfo(Integer houseId) {
		ShowUserHouse showUserHouse = null;
		UserHouse userHouse = userHouseMapper.selectByPrimaryKey(houseId);
		if(userHouse!=null && userHouse.getAttachId()!=null){
			showUserHouse = userHouseChangeShowUserHouse(userHouse);
		}
		return showUserHouse;
	}

	public ShowUserCar userCarChangeShowUserCar(UserCar userCar){
		ShowUserCar showUserCar = new ShowUserCar();
		showUserCar.setId(userCar.getId());
		showUserCar.setUid(userCar.getUid());
		showUserCar.setAttachId(userCar.getAttachId());
		showUserCar.setCarNo(userCar.getCarNo());
		showUserCar.setOwner(userCar.getOwner());
		showUserCar.setBrand(userCar.getBrand());
		showUserCar.setVinNo(userCar.getVinNo());
		showUserCar.setRegisterDate(userCar.getRegisterDate());
		List<String> imgUrls = new ArrayList<String>();
		if(userCar.getAttachId()!=null){
			List<String> attachIds = Arrays.asList(userCar.getAttachId().split(","));
			for(String attachId:attachIds){
				Attachment attachment = attachmentMapper.selectByPrimaryKey(Integer.parseInt(attachId.trim()));
				if(attachment!=null && attachment.getAttachPath()!=null && attachment.getSaveName()!=null){
					imgUrls.add(downloadImgUrl+attachment.getAttachPath()+attachment.getSaveName());
				}
			}
		}
		showUserCar.setImgUrls(imgUrls);
		return showUserCar;
	}
	
	public ShowUserHouse userHouseChangeShowUserHouse(UserHouse userHouse){
		ShowUserHouse showUserHouse = new ShowUserHouse();
		showUserHouse.setId(userHouse.getId());
		showUserHouse.setUid(userHouse.getUid());
		showUserHouse.setOwner(userHouse.getOwner());
		showUserHouse.setAddress(userHouse.getAddress());
		showUserHouse.setAttachId(userHouse.getAttachId());
		List<String> imgUrls = new ArrayList<String>();
		if(userHouse.getAttachId()!=null){
			List<String> attachIds = Arrays.asList(userHouse.getAttachId().split(","));
			for(String attachId:attachIds){
				Attachment attachment = attachmentMapper.selectByPrimaryKey(Integer.parseInt(attachId.trim()));
				if(attachment!=null && attachment.getAttachPath()!=null && attachment.getSaveName()!=null){
					imgUrls.add(downloadImgUrl+attachment.getAttachPath()+attachment.getSaveName());
				}
			}
			
		}
		showUserHouse.setImgUrls(imgUrls);
		return showUserHouse;
	}

	@Override
	public Integer updateHouseInfo(UserHouse userHouse) {
		UserHouseCriteria userHouseCriteria = new UserHouseCriteria();
		userHouseCriteria.createCriteria().andUidEqualTo(userHouse.getUid()).andAddressEqualTo(userHouse.getAddress().trim()).andIdNotEqualTo(userHouse.getId());
		List<UserHouse> userHouses = userHouseMapper.selectByCriteria(userHouseCriteria);
		if(userHouses!=null && !userHouses.isEmpty()){
			throw new CheckException(ErrorMessageConstant.ERR_DATA_ERROR);
		}
		if(!userHouse.getAddress().matches(RegexContext.ADRESS_REGEX)){
			throw new CheckException(ErrorMessageConstant.ERR_ADRESS_ERROR);
		}
		if(!userHouse.getOwner().matches(RegexContext.CHINESE_REGEX)){
			throw new CheckException(ErrorMessageConstant.ERR_NAME_ERROR);
		}		
		return userHouseMapper.updateByPrimaryKey(userHouse);
		
	}

	@Override
	public Integer updateCarInfo(UserCar userCar) {
		UserCarCriteria userCarCriteria = new UserCarCriteria();
//		userCarCriteria.createCriteria().andUidEqualTo(userCar.getUid()).andCarNoEqualTo(userCar.getCarNo().trim()).andVinNoEqualTo(userCar.getVinNo().trim()).andOwnerEqualTo(userCar.getOwner().trim()).andBrandEqualTo(userCar.getBrand().trim()).andRegisterDateEqualTo(userCar.getRegisterDate().trim()).andAttachIdEqualTo(userCar.getAttachId().trim());
		userCarCriteria.createCriteria().andUidEqualTo(userCar.getUid()).andCarNoEqualTo(userCar.getCarNo()).andIdNotEqualTo(userCar.getId());
		List<UserCar> userCars = userCarMapper.selectByCriteria(userCarCriteria);
		if(userCars!=null&&!userCars.isEmpty()){
			throw new CheckException(ErrorMessageConstant.ERR_DATA_ERROR);
		}
		if(!userCar.getCarNo().matches(RegexContext.CARNO_REGEX)){
			throw new CheckException(ErrorMessageConstant.ERR_CARNO_ERROR);
		}
		if(!userCar.getOwner().matches(RegexContext.CHINESE_REGEX)){
			throw new CheckException(ErrorMessageConstant.ERR_NAME_ERROR);
		}
		if(!userCar.getVinNo().matches(RegexContext.CARVIN_REGEX)){
			throw new CheckException(ErrorMessageConstant.ERR_CARVIN_ERROR);
		}
		return userCarMapper.updateByPrimaryKey(userCar);
		
	}

	@Override
	@Deprecated
	public Map<String, Object> getScore(Integer uid) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		UserBasic userBasic = userBasicMapper.selectByPrimaryKey(uid);
		Short changfuScore = userBasic.getChangfuScore();
		dataMap.put("changfuScore", changfuScore);
		String level = "";
		if(changfuScore<=200){			
			level= "信用值一般";
		}
		if (200<changfuScore&&changfuScore<=500) {
			level= "信用值正常";
		}
		if (500<changfuScore&&changfuScore<=750) {
			level= "信用值良好";
		}
		if(750<changfuScore){
			level= "信用值优秀";
		}
		dataMap.put("level", level);
		return dataMap;
	}

	@Override
	public List<String> getSalaryScope() {
		List<String> salaryScopeList = new ArrayList<String>();
		CodeCriteria codeCriteria = new CodeCriteria();
		codeCriteria.createCriteria().andCodeKeyEqualTo(UtilConstant.SALARY_SCOPE);
		List<Code> codeList= codeMapper.selectByCriteria(codeCriteria);
		if(codeList!=null && !codeList.isEmpty()){
			for(Code code:codeList){
				salaryScopeList.add(code.getCodeName());
			}
		}
		return salaryScopeList;
	}

	@Override
	public Integer deleteContactsInfo(Integer id) {
		return userContactsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public InfoBean getInfo(Integer uid) {
		InfoBean infoBean = new InfoBean();
		UserBasic userBasic = userBasicMapper.selectByPrimaryKey(uid);
		//身份信息
		if(userBasic.getIsIdentityAuth()==UtilConstant.AUTHENTICATION_STATUS_2){
			UserExtCriteria userExtCriteria = new UserExtCriteria();
			userExtCriteria.createCriteria().andUidEqualTo(uid);
			UserExt userExt = userExtMapper.selectByCriteria(userExtCriteria).get(0);
			String newString = "";
			for(int i=0;i<userExt.getName().length()-1;i++){
				newString+="*";
			}
			infoBean.setIdentityInfo(userExt.getName().replace(userExt.getName().substring(1,userExt.getName().length()), newString));
		}else{
			CodeCriteria codeCriteria = new CodeCriteria();
			codeCriteria.createCriteria().andCodeKeyEqualTo(UtilConstant.AUTHENTICATION_STATUS_KEY).andCodeNoEqualTo(String.valueOf(userBasic.getIsIdentityAuth()));
			infoBean.setIdentityInfo(codeMapper.selectByCriteria(codeCriteria).get(0).getCodeName());
		}
		//工作信息
		UserProfessionCriteria userProfessionCriteria = new UserProfessionCriteria();
		userProfessionCriteria.createCriteria().andUidEqualTo(uid);
		List<UserProfession> userProfessionsList = userProfessionMapper.selectByCriteria(userProfessionCriteria);
		if(userProfessionsList!=null && !userProfessionsList.isEmpty()){
			infoBean.setProfessionInfo(UtilConstant.PERSON_INFO_STATUS_0);
		}else {
			infoBean.setProfessionInfo(UtilConstant.PERSON_INFO_STATUS_1);
		}
		//联系人信息
		UserContactsCriteria userContactsCriteria = new UserContactsCriteria();
		userContactsCriteria.createCriteria().andUidEqualTo(uid);
		List<UserContacts> userContactsList = userContactsMapper.selectByCriteria(userContactsCriteria);
		if(userContactsList!=null && !userContactsList.isEmpty()){
			infoBean.setContactsInfo(UtilConstant.PERSON_INFO_STATUS_0);
		}else {
			infoBean.setContactsInfo(UtilConstant.PERSON_INFO_STATUS_1);
		}
		
		UserExtCriteria userExtCriteria = new UserExtCriteria();
		userExtCriteria.createCriteria().andUidEqualTo(uid);
		List<UserExt> userExts = userExtMapper.selectByCriteria(userExtCriteria);
		UserExt userExt = new UserExt();
		if(userExts!=null && !userExts.isEmpty()){
			userExt= userExts.get(0);
		}
		//驾驶证信息
		UserDriveLicenceCriteria userDriveLicenceCriteria = new UserDriveLicenceCriteria();
		userDriveLicenceCriteria.createCriteria().andUidEqualTo(uid);
		List<UserDriveLicence> userDriveLicenceList = userDriveLicenceMapper.selectByCriteria(userDriveLicenceCriteria);
		if(userDriveLicenceList!=null && !userDriveLicenceList.isEmpty()){
			infoBean.setDriveLicenceInfo(UtilConstant.PERSON_INFO_STATUS_0);
		}else {
			infoBean.setDriveLicenceInfo(UtilConstant.PERSON_INFO_STATUS_1);
		}
		//住址信息
		if(StringUtil.isNotEmpty(userExt.getResidentialAddress()) && StringUtil.isNotEmpty(userExt.getFamilyAddress())){
			infoBean.setAddressInfo(UtilConstant.PERSON_INFO_STATUS_0);
		}else{
			infoBean.setAddressInfo(UtilConstant.PERSON_INFO_STATUS_1);
		}
		//车辆信息
		UserCarCriteria userCarCriteria = new UserCarCriteria();
		userCarCriteria.createCriteria().andUidEqualTo(uid);
		List<UserCar> userCarList = userCarMapper.selectByCriteria(userCarCriteria);
		if(userCarList!=null && !userCarList.isEmpty()){
			infoBean.setCarInfo(UtilConstant.PERSON_INFO_STATUS_0);
		}else{
			infoBean.setCarInfo(UtilConstant.PERSON_INFO_STATUS_1);
		}
		//房产信息
		UserHouseCriteria userHouseCriteria = new UserHouseCriteria();
		userHouseCriteria.createCriteria().andUidEqualTo(uid);
		List<UserHouse> userHouseList = userHouseMapper.selectByCriteria(userHouseCriteria);
		if(userHouseList!=null && !userHouseList.isEmpty()){
			infoBean.setHouseInfo(UtilConstant.PERSON_INFO_STATUS_0);
		}else{
			infoBean.setHouseInfo(UtilConstant.PERSON_INFO_STATUS_1);
		}
		//信用卡认证信息
		CodeCriteria codeCriteria1 = new CodeCriteria();
		codeCriteria1.createCriteria().andCodeKeyEqualTo(UtilConstant.AUTHENTICATION_STATUS_KEY).andCodeNoEqualTo(String.valueOf(userBasic.getCreditCardCertStatus()));
		infoBean.setCreditCardInfo(codeMapper.selectByCriteria(codeCriteria1).get(0).getCodeName());
		//央行征信信息
		CodeCriteria codeCriteria2 = new CodeCriteria();
		codeCriteria2.createCriteria().andCodeKeyEqualTo(UtilConstant.AUTHENTICATION_STATUS_KEY).andCodeNoEqualTo(String.valueOf(userBasic.getCreditInformationCertStatus()));
		infoBean.setCreditInformation(codeMapper.selectByCriteria(codeCriteria2).get(0).getCodeName());
		return infoBean;
	}

	@Override
	public Integer deleteCarInfo(Integer carId) {
		return userCarMapper.deleteByPrimaryKey(carId);
	}

	@Override
	public Integer deleteHouseInfo(Integer houseId) {
		return userHouseMapper.deleteByPrimaryKey(houseId);
	}

	@Override
	public UserExt getIdentityInfo(Integer uid) {
		UserExt userExt = new UserExt();
		UserExtCriteria userExtCriteria = new UserExtCriteria();
		userExtCriteria.createCriteria().andUidEqualTo(uid);
		List<UserExt> userExts = userExtMapper.selectByCriteria(userExtCriteria);
		if(userExts!=null && !userExts.isEmpty()){
			UserExt ext = userExts.get(0);
			String name ="";
			if(ext.getName()!=null){
				name = ext.getName().substring(0, 1);
				for(int i=0;i<ext.getName().length()-1;i++){
					name+="*";
				}
			}
			userExt.setName(name);
			if(ext.getIdentityType()!=null){
				CodeCriteria codeCriteria = new CodeCriteria();
				codeCriteria.createCriteria().andCodeKeyEqualTo(UtilConstant.IDENTITY_TYPE_KEY).andCodeNoEqualTo(ext.getIdentityType());
				userExt.setIdentityType(codeMapper.selectByCriteria(codeCriteria).get(0).getCodeName());
			}
			userExt.setIdentityId(ext.getIdentityId().replace(userExts.get(0).getIdentityId().substring(3, userExts.get(0).getIdentityId().length()-4), "***********"));
		}
		return userExt;
	}

	@Override
	public CertificationPage getCertificationPage(Integer userId, Integer productId, Integer loanApplyId) throws IllegalArgumentException, IllegalAccessException {
		LoanProduct loanProduct = productMapper.selectByPrimaryKey(productId);
		CertificationPage certificationPage = gson.fromJson(loanProduct.getCertificationPageLayout(), CertificationPage.class);
		UserBasic user = userBasicMapper.selectByPrimaryKey(userId);
		UserExtCriteria userExtCriteria = new UserExtCriteria();
		userExtCriteria.createCriteria().andUidEqualTo(userId);
		UserExt userExt = userExtMapper.selectByCriteria(userExtCriteria).get(0);		
		List<LoanApplyAddress> addressList = null;
		//用于判断
		//惠装贷装修地址每次都要申请都要填写
		if(UtilConstant.LOAN_PRODUCTCODE_C6.equals(loanProduct.getProductCode())) {
			LoanApplyAddressCriteria critera=new LoanApplyAddressCriteria();
			critera.createCriteria().andLoanApplyIdEqualTo(loanApplyId);
			addressList = loanApplyAddressMapper.selectByCriteria(critera);
		}		
		
		for(Field field:UserBasic.class.getDeclaredFields()) {
			field.setAccessible(true);
			if(certificationPage!=null){
				if(certificationPage.getMandatorys()!=null && certificationPage.getMandatorys().size()>0){
					for(CertficationInfo certficationInfo:certificationPage.getMandatorys()) {
						if(field.getName().equals(certficationInfo.getField())){
							Byte value = (Byte)field.get(user);
							CodeCriteria codeCriteria = new CodeCriteria();
							codeCriteria.createCriteria().andCodeKeyEqualTo(UtilConstant.AUTHENTICATION_STATUS_KEY).andCodeNoEqualTo(value==1?"3":value.toString());
							certficationInfo.setResult(codeMapper.selectByCriteria(codeCriteria).get(0).getCodeName());
							if("isAddressFill".equals(certficationInfo.getField())){
								if(UtilConstant.LOAN_PRODUCTCODE_C1.equals(loanProduct.getProductCode())){//购车宝
									if(StringUtil.isNotEmpty(userExt.getResidentialProvince())&&StringUtil.isNotEmpty(userExt.getResidentialCity())&&StringUtil.isNotEmpty(userExt.getResidentialDistrict())&&StringUtil.isNotEmpty(userExt.getResidentialAddress())){
										certficationInfo.setResult("已填写");
									}else{
										certficationInfo.setResult("未填写");
									}
								}
								if(UtilConstant.LOAN_PRODUCTCODE_C6.equals(loanProduct.getProductCode())){//惠装贷
									if(addressList==null || addressList.size()==0){
										certficationInfo.setResult("未填写");
									}else{
										certficationInfo.setResult("已填写");
									}
								}
								
								if(UtilConstant.LOAN_PRODUCTCODE_C9.equals(loanProduct.getProductCode())){//工薪贷
									if(StringUtil.isNotEmpty(userExt.getFamilyProvince())&&StringUtil.isNotEmpty(userExt.getFamilyCity())&&StringUtil.isNotEmpty(userExt.getFamilyDistrict())&&StringUtil.isNotEmpty(userExt.getFamilyAddress())
											&&StringUtil.isNotEmpty(userExt.getResidentialProvince())&&StringUtil.isNotEmpty(userExt.getResidentialCity())&&StringUtil.isNotEmpty(userExt.getResidentialDistrict())&&StringUtil.isNotEmpty(userExt.getResidentialAddress()) ){
										certficationInfo.setResult("已填写");
									}else{
										certficationInfo.setResult("未填写");
									}
								}
								
							}
						
					}
				}
			}
				
				if(certificationPage.getOptionals()!=null && certificationPage.getOptionals().size()>0){
					for(CertficationInfo certficationInfo:certificationPage.getOptionals()) {
						if(field.getName().equals(certficationInfo.getField())){
							Byte value = (Byte)field.get(user);
							CodeCriteria codeCriteria = new CodeCriteria();
							codeCriteria.createCriteria().andCodeKeyEqualTo(UtilConstant.AUTHENTICATION_STATUS_KEY).andCodeNoEqualTo(value==1?"3":value.toString());
							certficationInfo.setResult(codeMapper.selectByCriteria(codeCriteria).get(0).getCodeName());
						}
					}
				}	
				
				if(certificationPage.getSelectOne()!=null && certificationPage.getSelectOne().size()>0){
					for(CertficationInfo certficationInfo:certificationPage.getSelectOne()) {
						if(field.getName().equals(certficationInfo.getField())){
							Byte value = (Byte)field.get(user);
							CodeCriteria codeCriteria = new CodeCriteria();
							codeCriteria.createCriteria().andCodeKeyEqualTo(UtilConstant.AUTHENTICATION_STATUS_KEY).andCodeNoEqualTo(value==1?"3":value.toString());
							certficationInfo.setResult(codeMapper.selectByCriteria(codeCriteria).get(0).getCodeName());
						}
					}
	
				}		
				
			}			
		}
		certificationPage.setIsQualify(isQualify(certificationPage));
		return certificationPage;
	}

	private boolean isQualify(CertificationPage certificationPage){
		boolean flag = true;
		boolean selectOneFlag = false;
		List<CertficationInfo> list = new ArrayList<CertficationInfo>();
		if(certificationPage.getMandatorys()!=null && !certificationPage.getMandatorys().isEmpty()){
			list.addAll(certificationPage.getMandatorys());
		}
		if(certificationPage.getOptionals()!=null && !certificationPage.getOptionals().isEmpty()){
			list.addAll(certificationPage.getOptionals());
		}
		if(certificationPage.getSelectOne()!=null && !certificationPage.getSelectOne().isEmpty()){
			list.addAll(certificationPage.getSelectOne());
		}
		if(list!=null&&!list.isEmpty()){
			List<CertficationInfo> selectOneList = new ArrayList<CertficationInfo>();
			for(CertficationInfo certficationInfo:list){
				if(certficationInfo.getPassStatus()==1){//必须通过
					if(certficationInfo.getKey().equals("address")){continue;}
					if(!"已填写".equals(certficationInfo.getResult()) && !"认证成功".equals(certficationInfo.getResult())){
						flag=false;
					}
				}
				if(certficationInfo.getPassStatus()==2){//二选一
					selectOneList.add(certficationInfo);
				}
			}
			
			if(selectOneList!=null&&!selectOneList.isEmpty()){//检测二选一是否有成功的
				for(CertficationInfo certficationInfo:selectOneList){
					if("已填写".equals(certficationInfo.getResult()) || "认证成功".equals(certficationInfo.getResult())){
						selectOneFlag=true;
					}
				}
			}else{//没有二选一选项
				selectOneFlag=true;
			}
			if(!flag){
				return false;
			}else{
				if(selectOneFlag){
					return true;
				}else {
					return false;
				}
			}
		}
		return false;
	}
	
	@Override
	public Integer addOrUpdateDriveLicenceInfo(Integer uid, UserDriveLicence driveLicence) {
		if(driveLicence.getDriveLicence()==null){
			throw new CheckException(ErrorMessageConstant.ERR_DRIVELICENCENO_NULL_ERROR);
		}
		if(!driveLicence.getDriveLicence().matches(RegexContext.IDENTITY_REGEX)){
			throw new CheckException(ErrorMessageConstant.ERR_DRIVELICENCENO_ERROR);
		}
		if(driveLicence.getDriverName()==null){
			throw new CheckException(ErrorMessageConstant.ERR_NAME_NULL_ERROR);
		}
		if(!driveLicence.getDriverName().matches(RegexContext.CHINESE_REGEX)){
			throw new CheckException(ErrorMessageConstant.ERR_NAME_ERROR);
		}
		if(driveLicence.getAttachId()==null){
			throw new CheckException(ErrorMessageConstant.ERR_ATTACHID_NULL_ERROR);
		}
		driveLicence.setUid(uid);
		if(driveLicence.getId()!=null && driveLicence.getId()!=0 ){//更新
			userDriveLicenceMapper.updateByPrimaryKeySelective(driveLicence);
		}else{//插入
			userDriveLicenceMapper.insert(driveLicence);
		}
		//跟新用户驾驶证信息是否填写状态
		UserBasic userBasic = userBasicMapper.selectByPrimaryKey(uid);
		userBasic.setIsDriveLicenceFill(Byte.parseByte("1"));
		userBasicMapper.updateByPrimaryKey(userBasic);
		return driveLicence.getId();
	}

	
	@Override
	public DriveLicence getDriveLicenceInfo(Integer uid) {
		DriveLicence driveLicence = null;
		UserDriveLicenceCriteria userDriveLicenceCriteria = new UserDriveLicenceCriteria();
		userDriveLicenceCriteria.createCriteria().andUidEqualTo(uid);
		List<UserDriveLicence> userDriveLicenceList = userDriveLicenceMapper.selectByCriteria(userDriveLicenceCriteria);
		if(userDriveLicenceList!=null&&!userDriveLicenceList.isEmpty()){
			UserDriveLicence userDriveLicence=userDriveLicenceList.get(0);
			driveLicence = new DriveLicence();
			driveLicence.setId(userDriveLicence.getId());
			driveLicence.setDriverName(userDriveLicence.getDriverName());
			driveLicence.setDriveLicence(userDriveLicence.getDriveLicence());
			driveLicence.setDriveLicenceFile(userDriveLicence.getDriveLicenceFile());
			driveLicence.setAttachId(userDriveLicence.getAttachId());
			if(userDriveLicence.getAttachId()!=null){
				List<String> imgUrls = new ArrayList<String>();
				String[] attachIds = userDriveLicence.getAttachId().split(",");
				for(String attacchId:attachIds){
					Attachment attachment = attachmentMapper.selectByPrimaryKey(Integer.parseInt(attacchId));
					imgUrls.add(downloadImgUrl+attachment.getAttachPath()+attachment.getSaveName());
				}
				driveLicence.setImgUrls(imgUrls);
			}
		}
		return driveLicence;
	}

	@Override
	public Integer addOrUpdateAdressInfo(Integer uid,UserExt userExt) {
		if(!userExt.getResidentialAddress().matches(RegexContext.ADRESS_REGEX)||!userExt.getFamilyAddress().matches(RegexContext.ADRESS_REGEX)){
			throw new CheckException(ErrorMessageConstant.ERR_ADRESS_ERROR);
		}
		UserExtCriteria userExtCriteria = new UserExtCriteria();
		userExtCriteria.createCriteria().andUidEqualTo(uid);
		List<UserExt> userExts = userExtMapper.selectByCriteria(userExtCriteria);
		userExts.get(0).setResidentialProvince(userExt.getResidentialProvince());
		userExts.get(0).setResidentialCity(userExt.getResidentialCity());
		userExts.get(0).setResidentialDistrict(userExt.getResidentialDistrict());
		userExts.get(0).setResidentialAddress(userExt.getResidentialAddress());
		userExts.get(0).setFamilyProvince(userExt.getFamilyProvince());
		userExts.get(0).setFamilyCity(userExt.getFamilyCity());
		userExts.get(0).setFamilyDistrict(userExt.getFamilyDistrict());
		userExts.get(0).setFamilyAddress(userExt.getFamilyAddress());
		userExtMapper.updateByPrimaryKey(userExts.get(0));
		//更新用户表地址是否填写状态
		UserBasic userBasic = userBasicMapper.selectByPrimaryKey(uid);
		if(userBasic.getIsAddressFill()!=1){
			userBasic.setIsAddressFill(Byte.parseByte("1"));
			userBasicMapper.updateByPrimaryKey(userBasic);
		}
//		if(applyId!=null){//借款流程里面调用，同步地址信息到jk_loan_apply_address表里
//			LoanApplyAddressCriteria loanApplyAddressCriteria = new LoanApplyAddressCriteria();
//			loanApplyAddressCriteria.createCriteria().andLoanApplyIdEqualTo(applyId);
//			List<LoanApplyAddress> loanApplyAddresseList = loanApplyAddressMapper.selectByCriteria(loanApplyAddressCriteria);
//			if(loanApplyAddresseList!=null && !loanApplyAddresseList.isEmpty() ){
//				LoanApplyAddress loanApplyAddress = loanApplyAddresseList.get(0);
//				loanApplyAddress.setFamilyAddress(userExt.getFamilyAddress());
//				loanApplyAddress.setFamilyDistrict(userExt.getFamilyDistrict());
//				loanApplyAddress.setFamilyCity(userExt.getFamilyCity());
//				loanApplyAddress.setFamilyProvince(userExt.getFamilyProvince());
//				loanApplyAddress.setResidentialAddress(userExt.getResidentialAddress());
//				loanApplyAddress.setResidentialDistrict(userExt.getResidentialDistrict());
//				loanApplyAddress.setResidentialCity(userExt.getResidentialCity());
//				loanApplyAddress.setResidentialProvince(userExt.getResidentialProvince());
//				loanApplyAddressMapper.updateByPrimaryKeySelective(loanApplyAddress);	
//			}else{
//				LoanApplyAddress loanApplyAddress = new LoanApplyAddress();
//				loanApplyAddress.setUid(uid);
//				loanApplyAddress.setLoanApplyId(applyId);
//				loanApplyAddress.setFamilyAddress(userExt.getFamilyAddress());
//				loanApplyAddress.setFamilyDistrict(userExt.getFamilyDistrict());
//				loanApplyAddress.setFamilyCity(userExt.getFamilyCity());
//				loanApplyAddress.setFamilyProvince(userExt.getFamilyProvince());
//				loanApplyAddress.setResidentialAddress(userExt.getResidentialAddress());
//				loanApplyAddress.setResidentialDistrict(userExt.getResidentialDistrict());
//				loanApplyAddress.setResidentialCity(userExt.getResidentialCity());
//				loanApplyAddress.setResidentialProvince(userExt.getResidentialProvince());
//				loanApplyAddressMapper.insert(loanApplyAddress);
//			}
//		}
		return userExts.get(0).getId();
	}

	
	@Override
	public AdressInfoBean getAdressInfo(Integer uid,Integer loanApplyId) {
		AdressInfoBean adressInfoBean = new AdressInfoBean(); 
		UserExtCriteria userExtCriteria = new UserExtCriteria();
		userExtCriteria.createCriteria().andUidEqualTo(uid);
		UserExt userExt = userExtMapper.selectByCriteria(userExtCriteria).get(0);
		if(loanApplyId==null){//个人信息页面请求接口
			if(userExt!=null){
				adressInfoBean.setResidentialProvince(userExt.getResidentialProvince());
				adressInfoBean.setResidentialCity(userExt.getResidentialCity());
				adressInfoBean.setResidentialDistrict(userExt.getResidentialDistrict());
				adressInfoBean.setResidentialAddress(userExt.getResidentialAddress());
				adressInfoBean.setFamilyProvince(userExt.getFamilyProvince());
				adressInfoBean.setFamilyCity(userExt.getFamilyCity());
				adressInfoBean.setFamilyDistrict(userExt.getFamilyDistrict());
				adressInfoBean.setFamilyAddress(userExt.getFamilyAddress());
				adressInfoBean.setDecorationProvince(userExt.getDecorationProvince());
				adressInfoBean.setDecorationCity(userExt.getDecorationCity());
				adressInfoBean.setDecorationDistrict(userExt.getDecorationDistrict());
				adressInfoBean.setDecorationAddress(userExt.getDecorationAddress());
			}
			
		}else{//借款申请流程请求接口
			LoanApplyAddressCriteria loanApplyAddressCriteria = new LoanApplyAddressCriteria();
			loanApplyAddressCriteria.createCriteria().andLoanApplyIdEqualTo(loanApplyId);
			List<LoanApplyAddress> loanApplyAddresseList = loanApplyAddressMapper.selectByCriteria(loanApplyAddressCriteria);
			if(loanApplyAddresseList!=null && !loanApplyAddresseList.isEmpty()){//再次进入页面
				LoanApplyAddress loanApplyAddress = loanApplyAddresseList.get(0);
				adressInfoBean.setResidentialProvince(loanApplyAddress.getResidentialProvince());
				adressInfoBean.setResidentialCity(loanApplyAddress.getResidentialCity());
				adressInfoBean.setResidentialDistrict(loanApplyAddress.getResidentialDistrict());
				adressInfoBean.setResidentialAddress(loanApplyAddress.getResidentialAddress());
				adressInfoBean.setFamilyProvince(loanApplyAddress.getFamilyProvince());
				adressInfoBean.setFamilyCity(loanApplyAddress.getFamilyCity());
				adressInfoBean.setFamilyDistrict(loanApplyAddress.getFamilyDistrict());
				adressInfoBean.setFamilyAddress(loanApplyAddress.getFamilyAddress());
				adressInfoBean.setDecorationProvince(loanApplyAddress.getDecorationProvince());
				adressInfoBean.setDecorationCity(loanApplyAddress.getDecorationCity());
				adressInfoBean.setDecorationDistrict(loanApplyAddress.getDecorationDistrict());
				adressInfoBean.setDecorationAddress(loanApplyAddress.getDecorationAddress());
			}else{//首次进入页面
				adressInfoBean.setResidentialProvince(userExt.getResidentialProvince());
				adressInfoBean.setResidentialCity(userExt.getResidentialCity());
				adressInfoBean.setResidentialDistrict(userExt.getResidentialDistrict());
				adressInfoBean.setResidentialAddress(userExt.getResidentialAddress());
				adressInfoBean.setFamilyProvince(userExt.getFamilyProvince());
				adressInfoBean.setFamilyCity(userExt.getFamilyCity());
				adressInfoBean.setFamilyDistrict(userExt.getFamilyDistrict());
				adressInfoBean.setFamilyAddress(userExt.getFamilyAddress());
			}
		}
		return adressInfoBean;
	}

	@Override
	public Map<String, Object> isIdentityAuth(Integer uid) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		UserBasic userBasic = userBasicMapper.selectByPrimaryKey(uid);
		UserExtCriteria userExtCriteria = new UserExtCriteria();
		userExtCriteria.createCriteria().andUidEqualTo(uid);
		UserExt userExt = userExtMapper.selectByCriteria(userExtCriteria).get(0);
		dataMap.put("is_identity_auth",userBasic.getIsIdentityAuth());
		dataMap.put("name",userExt.getName());
		dataMap.put("identity_type",userExt.getIdentityType());
		dataMap.put("identity_id",userExt.getIdentityId());
		return dataMap;
	}

	@Override
	public List<UserCreditValue> queryUserCreditValueListByUid(Integer uid) {
		UserCreditValueCriteria userCreditValueCriteria = new UserCreditValueCriteria();
		userCreditValueCriteria.createCriteria().andUidEqualTo(uid);
		return userCreditValueMapper.selectByCriteria(userCreditValueCriteria);
	}
}
