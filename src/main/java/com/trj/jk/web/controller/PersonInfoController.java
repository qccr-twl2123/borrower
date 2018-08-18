package com.trj.jk.web.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.google.common.collect.Lists;
import com.trj.commons.result.Results;
import com.trj.jk.web.domain.*;

import com.trj.jk.web.enums.CreditLevelEnum;
import com.trj.jk.web.enums.UserCreditValueTypeEnum;
import com.trj.jk.web.model.dto.UserCreditItemValueDTO;
import com.trj.jk.web.model.dto.UserCreditValueDTO;
import com.trj.jk.web.model.response.UserAddressAndCompanyRes;
import com.trj.jk.web.service.UserProfessionService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.trj.commons.result.Result;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.domain.entity.ResultMessageConstant;
import com.trj.jk.web.domain.entity.page.PageBean;
import com.trj.jk.web.domain.entity.personInfo.AdressInfoBean;
import com.trj.jk.web.domain.entity.personInfo.DriveLicence;
import com.trj.jk.web.domain.entity.personInfo.InfoBean;
import com.trj.jk.web.domain.entity.personInfo.ShowUserCar;
import com.trj.jk.web.domain.entity.personInfo.ShowUserHouse;
import com.trj.jk.web.domain.exception.CheckException;
import com.trj.jk.web.service.IAttachmentService;
import com.trj.jk.web.service.IPersonInfoService;
import com.trj.jk.web.util.JsonUtils;
import com.trj.jk.web.util.SessionUtil;

/**
 * 个人信息controller类
 * @author l46001
 *
 */
@RestController
@RequestMapping(value={"/personInfo"})
public class PersonInfoController extends BaseController{
	
	@Value("${app.upload.path}")
	private  String uploadPath;

	private static final Logger LOG = LoggerFactory.getLogger(PersonInfoController.class);

	@Resource
	private IPersonInfoService personInfoService;
	
	@Resource
	private IAttachmentService attachmentService;
	@Autowired
	private UserProfessionService userProfessionService;
	

	/**
	 * 获取 个人信息接口
	 * @param uid:对应用户id
	 * @return
	 */
	@RequestMapping(value={"/getInfo"},method=RequestMethod.GET)
	@ResponseBody
	public Result<Object> getInfo(){
		final Result<Object> result = new Result<Object>();
		Integer uid = (Integer)SessionUtil.getUserLogonInfo();
		LOG.info(String.format("个人信息主页面信息获取接口执行》》》》参数：uid=%s",String.valueOf(uid)));
		try {
			if(uid!=null){
				InfoBean Info = personInfoService.getInfo(uid);
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.GET_PERSONINFO_SUCCESS);
				result.setData(Info);
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);	
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);	
		}
		LOG.info(String.format("个人信息主页面信息获取接口返回结果：%s",result.toString()));
		return result;
	}
	
	/**
	 * 获取 身份信息接口
	 * @param uid:对应用户id
	 * @return
	 */
	@RequestMapping(value={"basicallyInfo/getIdentityInfo"},method=RequestMethod.GET)
	@ResponseBody
	public Result<Object> getIdentityInfo(){
		final Result<Object> result = new Result<Object>();
		try {
			Integer uid = (Integer)SessionUtil.getUserLogonInfo();
			LOG.info(String.format("获取 身份信息接口接口执行》》》》参数：uid=%s",String.valueOf(uid)));
			if(uid!=null){
				UserExt userExt = personInfoService.getIdentityInfo(uid);
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.GET_PERSONINFO_SUCCESS);
				result.setData(userExt);
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);	
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);	
		}
		LOG.info(String.format("获取 身份信息接口接口返回结果：%s",result.toString()));
		return result;
	}
	
	/**
	 * 驾驶证添加或更新接口
	 */
	@RequestMapping(value={"/basicallyInfo/addOrUpdateDriveLicenceInfo"},method=RequestMethod.POST)
	@ResponseBody
	public Result<Object> addOrUpdateDriveLicenceInfo(UserDriveLicence driveLicence) {
		final Result<Object> result = new Result<Object>();
		try {
			Integer uid = (Integer)SessionUtil.getUserLogonInfo();
			LOG.info(String.format("驾驶证添加或更新接口接口执行》》》》参数：uid=%s,driveLicence=%s",String.valueOf(uid),driveLicence.toString()));
			if (uid!=null && driveLicence!=null) {
				Integer userExtId=personInfoService.addOrUpdateDriveLicenceInfo(uid,driveLicence);
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.UPDATA_DRIVELICENCE_SUCCESS);
				result.setData(userExtId);
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);	
			}
		}catch(CheckException e){
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());	
		}
		catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);	
		}
		LOG.info(String.format("驾驶证添加或更新接口》》》》返回：%s",result.toString()));
		return result;
	}
	
	/**
	 * 驾驶证获取接口
	 */
	@RequestMapping(value={"/basicallyInfo/getDriveLicenceInfo"},method=RequestMethod.GET)
	@ResponseBody
	public Result<Object> getDriveLicenceInfo() {
		final Result<Object> result = new Result<Object>();
		try {
			Integer uid = (Integer)SessionUtil.getUserLogonInfo();
			LOG.info(String.format("驾驶证获取接口接口执行》》》》参数：uid=%s",String.valueOf(uid)));
			if(uid!=null){
				DriveLicence driveLicence=personInfoService.getDriveLicenceInfo(uid);
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.GET_DRIVELICENCE_SUCCESS);
				result.setData(driveLicence);
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);	
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);	
		}
		LOG.info(String.format("驾驶证获取接口》》》》返回：%s",result.toString()));
		return result;
	}

	/**
	 * 地址信息添加或更新接口
	 */
	@RequestMapping(value={"/basicallyInfo/addOrUpdateAdressInfo"},method=RequestMethod.POST)
	@ResponseBody
	public Result<Object> addOrUpdateAdressInfo(UserExt userExt){
		final Result<Object> result = new Result<Object>();
		try {
			Integer uid = (Integer)SessionUtil.getUserLogonInfo();
			LOG.info(String.format("地址信息添加或更新接口执行》》》》参数：userExt=%s",userExt.toString()));
			if(uid!=null){
				personInfoService.addOrUpdateAdressInfo(uid,userExt);
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.SUBMIT_ADREES_SUCCESS);
				result.setData(userExt.getId());
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);	
			}
		} catch(CheckException e){
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());	
		}catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);	
		}
		LOG.info(String.format("地址信息添加或更新接口》》》》返回：%s",result.toString()));
		return result;
	}
	
	
	/**
	 * 地址信息获取接口
	 */
	@RequestMapping(value={"/basicallyInfo/getAdressInfo"},method=RequestMethod.GET)
	@ResponseBody
	public Result<Object> getAdressInfo(Integer loanApplyId) {
		final Result<Object> result = new Result<Object>();
		try {
			Integer uid = (Integer)SessionUtil.getUserLogonInfo();
			LOG.info(String.format("地址信息获取接口执行》》》》参数：uid=%s,loanApplyId=%s",uid,loanApplyId));
			if(uid!=null){
				AdressInfoBean adressInfoBean=personInfoService.getAdressInfo(uid,loanApplyId);
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.GET_ADRESS_SUCCESS);
				result.setData(adressInfoBean);
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);	
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);	
		}
		LOG.info(String.format("地址信息获取接口》》》》返回：%s",result.toString()));
		return result;
	}
	
	
	/**
	 * 获取 个人信息/基本信息/职业信息 列表接口
	 * @param uid:对应用户id
	 * @return
	 */
	@RequestMapping(value={"/basicallyInfo/getProfessionList"},method=RequestMethod.GET)
	@ResponseBody
	public Result<Object> getProfessionList() {
		final Result<Object> result = new Result<Object>();
		try {
			Integer uid = (Integer)SessionUtil.getUserLogonInfo();
			LOG.info(String.format("获取 个人信息/基本信息/职业信息 列表接口执行》》》》参数：uid=%s",uid));
			if(uid!=null){
				List<UserProfession> ProfessionList = personInfoService.getProfessionListByUid(uid);
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.GET_PROFESSION_LIST_SUCCESS);
				result.setData(ProfessionList);
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);			
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);	
		}
		LOG.info(String.format("获取 个人信息/基本信息/职业信息 列表接口》》》》返回：%s",result.toString()));
		return result;
	}
	
	 /**
	 * 获取某条 个人信息/基本信息/职业信息 接口
	 * @param uid:对应用户id
	 * @return
	 */
	@RequestMapping(value={"/basicallyInfo/getProfession"},method=RequestMethod.GET)
	@ResponseBody
	public Result<Object> getProfessionInfo() {
		final Result<Object> result = new Result<Object>();
		Integer uid = (Integer)SessionUtil.getUserLogonInfo();
		LOG.info(String.format("获取某条 个人信息/基本信息/职业信息 接口》》》》参数：uid=%s",uid));
		try {
			if(uid!=null){
				UserProfession userProfession = personInfoService.getProfessionInfo(uid);
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.GET_PROFESSION_SUCCESS);
				result.setData(userProfession);
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);	
		}
		LOG.info(String.format("获取某条 个人信息/基本信息/职业信息 接口》》》》返回：%s",result.toString()));
		return result;
	}
	
	
	 /**
	 * 添加 个人信息/基本信息/职业信息 接口
	 * @param uid:对应用户id
	 * @return
	 */
	@RequestMapping(value={"/basicallyInfo/addProfession"},method=RequestMethod.POST)
	@ResponseBody
	public Result<Object> addProfessionInfo(UserProfession userProfession) {
		final Result<Object> result = new Result<Object>();
		Integer uid = (Integer)SessionUtil.getUserLogonInfo();
		LOG.info(String.format("添加 个人信息/基本信息/职业信息 接口》》》》参数：uid=%s,userProfession=%s",uid,userProfession.toString()));
		try {
			if(userProfession!=null && uid!=null){
				userProfession.setUid(uid);
				personInfoService.addProfessionInfo(userProfession);
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.ADD_PROFESSION_SUCCESS);
				result.setData(userProfession.getId());
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		}catch(CheckException e){
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		}catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);	
		}
		LOG.info(String.format("添加 个人信息/基本信息/职业信息 接口》》》》返回：%s",result.toString()));
		return result;
	}
	
	
	
		
	/**
	 * update某条 个人信息/基本信息/职业信息 接口
	 * @return
	 */
	@RequestMapping(value={"/basicallyInfo/updateProfession"},method=RequestMethod.POST)
	@ResponseBody
	public Result<Object> updateProfessionInfo(UserProfession userProfession) {
		final Result<Object> result = new Result<Object>();
		Integer uid = (Integer) SessionUtil.getUserLogonInfo();
		LOG.info(String.format("update某条 个人信息/基本信息/职业信息 接口 》》》》参数：uid=%s,userProfession=%s", uid, userProfession.toString()));
		try {
			if (userProfession != null && uid != null) {
				userProfession.setUid(uid);
				personInfoService.updateProfessionInfo(userProfession);
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.UPDATE_PROFESSION_SUCCESS);
				result.setData(userProfession.getId());
			} else {
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		} catch (CheckException e) {
			LOG.error(e.getMessage(), e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("update某条 个人信息/基本信息/职业信息 接口 》》》》返回：%s", result.toString()));
		return result;
	}
		
	/**
	 * 获取联系人列表
	 * @param uid
	 * @return
	 */
	@RequestMapping(value={"/basicallyInfo/getContactsList"},method=RequestMethod.GET)
	@ResponseBody
	public Result<Object> getContactsList() {
		final Result<Object> result = new Result<Object>();
		Integer uid = (Integer)SessionUtil.getUserLogonInfo();
		LOG.info(String.format("获取联系人列表接口 》》》》参数：uid=%s",uid));
		try {
			if(uid!=null){
				List<UserContacts> UserContactsList = personInfoService.getContactsListByUid(uid);
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.GET_CONTACTS_LIST_SUCCESS);
				result.setData(UserContactsList);
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);	
		}
		LOG.info(String.format("获取联系人列表接口 》》》》返回：%s",result.toString()));
		return result;
	}


	/**
	 * 添加多个联系人
	 * @param userContactsList
	 * @return
	 */
	@RequestMapping(value={"/basicallyInfo/addContactsList"},method=RequestMethod.POST)
	@ResponseBody
	public Result<Object> addContactsList(HttpServletRequest request) {
		final Result<Object> result = new Result<Object>();
		Integer uid = (Integer)SessionUtil.getUserLogonInfo();
		try {
			List<UserContacts> userContactsList = new ArrayList<UserContacts>(JsonUtils.stringToCollectionWithShortTime(String.valueOf(request.getParameter("userContactsList")), UserContacts.class));
			LOG.info(String.format("添加多个联系人接口 》》》》参数：uid=%s,userContactsList=%s",uid,request.getParameter("userContactsList")));
			if(userContactsList!=null && !userContactsList.isEmpty() && uid!=null){
				List<Integer> ids = new ArrayList<Integer>();
				for(UserContacts userContacts:userContactsList){
					userContacts.setUid(uid);
					personInfoService.addContactsInfo(userContacts);
					ids.add(userContacts.getId());
				}
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.ADD_CONTACTS_SUCCESS);
				result.setData(ids);
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}	
		}catch(CheckException e){
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());	
		}catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);	
		}
		LOG.info(String.format("添加多个联系人接口 》》》》返回：%s",result.toString()));
		return result;
	}
	

	/**
	 * 删除多个联系人信息
	 * @param uid
	 * @return
	 */
	@RequestMapping(value={"/basicallyInfo/deleteContactsList"},method=RequestMethod.GET)
	@ResponseBody
	public Result<Object> updateContactsInfo(HttpServletRequest request) {
		final Result<Object> result = new Result<Object>();
		try {
			List<Integer> ids = new ArrayList<Integer>(JsonUtils.stringToCollectionWithShortTime(String.valueOf(request.getParameter("ids")), Integer.class));
			LOG.info(String.format("删除多个联系人信息接口 》》》》参数：%s",request.getParameter("ids")));
			if(ids!=null && !ids.isEmpty()){
				for(Integer id:ids){
					personInfoService.deleteContactsInfo(id);
				}
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.SELETE_CONTACTS_SUCCESS);
				result.setData(ids);
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);	
		}
		LOG.info(String.format("获取联系人列表接口 》》》》返回：%s",result.toString()));
		return result;
	}
	
	/**
	 * 获取车辆列表
	 * @param uid
	 * @return
	 */
	@RequestMapping(value={"/assetInfo/getCarList"},method=RequestMethod.GET)
	@ResponseBody
	public Result<Object> getCarList(PageBean bean) {
		final Result<Object> result = new Result<Object>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		try {	
			Integer uid = (Integer)SessionUtil.getUserLogonInfo();
			PageBounds pageBounds=getPageBounds(bean);
			LOG.info(String.format("获取车辆列表接口 》》》》参数：uid=%s",uid));
			if(uid!=null){
				PageList<UserCar> userCars = personInfoService.getCarListByUid(uid,pageBounds);
				dataMap.put("list", userCars);
				dataMap.put("limit", userCars.getPaginator().getLimit());
				dataMap.put("totalPages", userCars.getPaginator().getTotalPages());
				dataMap.put("page", userCars.getPaginator().getPage());
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.GET_CAR_LIST_SUCCESS);
				result.setData(dataMap);
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);	
		}
		LOG.info(String.format("获取车辆列表接口 》》》》返回：%s",result.toString()));
		return result;
	}
	
	
	/**
	 * 获取某个车辆信息
	 * @return
	 */
	@RequestMapping(value={"/assetInfo/getCar"},method=RequestMethod.GET)
	@ResponseBody
	public Result<Object> getCarInfo(Integer carId) {
		final Result<Object> result = new Result<Object>();
		try {	
			LOG.info(String.format("获取某个车辆信息接口 》》》》参数：carId=%s",carId));
			if(carId!=null){
				ShowUserCar ShowuserCar = personInfoService.getCarInfo(carId);
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.GET_CAR_SUCCESS);
				result.setData(ShowuserCar);
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);	
		}
		LOG.info(String.format("获取某个车辆信息接口 》》》》返回：%s",result.toString()));
		return result;
	}
	
	//待完善
	/**
	 * 添加车辆信息
	 * @param request
	 * @param response
	 * @param userCar
	 * @return
	 */
	@RequestMapping(value={"/assetInfo/addCar"},method=RequestMethod.POST)
	@ResponseBody
	public Result<Object> addCarInfo(HttpServletRequest request,UserCar userCar) {
		final Result<Object> result = new Result<Object>();
		Integer uid = (Integer)SessionUtil.getUserLogonInfo();
		LOG.info(String.format("添加车辆信息接口 》》》》参数：userCar=%s",userCar.toString()));
		try {
			if(userCar!=null && uid!=null){

				userCar.setUid(uid);
				personInfoService.addCarInfo(userCar);
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.ADD_CAR_SUCCESS);
				result.setData(userCar.getId());
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		}catch(CheckException e){
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());	
		}catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);	
		}
		LOG.info(String.format("添加车辆信息接口 》》》》返回：%s",result.toString()));
		return result;
	}
	
	
	/**
	 * 更新车辆信息
	 * @param userCar
	 * @return
	 */
	@RequestMapping(value={"/assetInfo/updateCar"},method=RequestMethod.POST)
	@ResponseBody
	public Result<Object> updateCarInfo(HttpServletRequest request,UserCar userCar) {
		final Result<Object> result = new Result<Object>();
		Integer uid = (Integer)SessionUtil.getUserLogonInfo();
		LOG.info(String.format("更新车辆信息接口 》》》》参数：uid=%s,userCar=%s",uid,userCar.toString()));
		try {
			
			if(userCar!=null && uid!=null){
				userCar.setUid(uid);
				personInfoService.updateCarInfo(userCar);
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.UPDATE_CAR_SUCCESS);
				result.setData(userCar.getId());
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		} catch(CheckException e){
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		}catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);	
		}
		LOG.info(String.format("更新车辆信息接口 》》》》返回：%s",result.toString()));
		return result;
	}
	
	/**
	 * 删除某个车辆信息
	 * @param userCar
	 * @return
	 */
	@RequestMapping(value={"/assetInfo/deleteCar"},method=RequestMethod.GET)
	@ResponseBody
	public Result<Object> deleteCarInfo(Integer carId) {
		final Result<Object> result = new Result<Object>();
		LOG.info(String.format("删除某个车辆信息接口 》》》》参数：carId=%s",carId));
		try {
			if(carId!=null){
				personInfoService.deleteCarInfo(carId);
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.SELECT_CAR_SUCCESS);
				result.setData(carId);
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);	
		}
		LOG.info(String.format("删除某个车辆信息接口 》》》》返回：%s",result.toString()));
		return result;
	}
	
	
	/**
	 * 获取房产信息列表
	 * @param uid
	 * @return
	 */
	@RequestMapping(value={"/assetInfo/getHouseList"},method=RequestMethod.GET)
	@ResponseBody
	public Result<Object> getHouseList(PageBean bean) {
		final Result<Object> result = new Result<Object>();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		try {		
			Integer uid = (Integer)SessionUtil.getUserLogonInfo();
			LOG.info(String.format("获取房产信息列表信息接口 》》》》参数：uid=%s",uid));
			PageBounds pageBounds=getPageBounds(bean);
			if(uid!=null){
				PageList<UserHouse> userHouses = personInfoService.getHouseListByUid(uid,pageBounds);
				dataMap.put("list", userHouses);
				dataMap.put("limit", userHouses.getPaginator().getLimit());
				dataMap.put("totalPages", userHouses.getPaginator().getTotalPages());
				dataMap.put("page", userHouses.getPaginator().getPage());
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.GET_HOUSE_LIST_SUCCESS);
				result.setData(dataMap);
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);	
		}
		LOG.info(String.format("获取房产信息列表信息接口 》》》》返回：%s",result.toString()));
		return result;
	}
	
	/**
	 * 获取某个房产信息
	 * @param houseId
	 * @return
	 */
	@RequestMapping(value={"/assetInfo/getHouse"},method=RequestMethod.GET)
	@ResponseBody
	public Result<Object> getHouseInfo(Integer houseId) {
		final Result<Object> result = new Result<Object>();
		LOG.info(String.format("获取某个房产信息接口 》》》》参数：houseId=%s",houseId));
		try {		
			if(houseId!=null){
				ShowUserHouse showUserHouse = personInfoService.getHouseInfo(houseId);
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.GET_HOUSE_SUCCESS);
				result.setData(showUserHouse);
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);	
		}
		LOG.info(String.format("获取房产信息列表信息接口 》》》》返回：%s",result.toString()));
		return result;
	}
	
	
	
	/**
	 * 添加房产信息
	 * @param file
	 * @param userHouse
	 * @return
	 */
	@RequestMapping(value={"/assetInfo/addHouse"},method=RequestMethod.POST)
	@ResponseBody
	public Result<Object> addHouseInfo(UserHouse userHouse) {
		final Result<Object> result = new Result<Object>();
		Integer uid = (Integer)SessionUtil.getUserLogonInfo();
		LOG.info(String.format("添加房产信息接口 》》》》参数：uid=%s,userHouse=%s",uid,userHouse.toString()));
		try {	
			if(userHouse!=null && uid!=null ){
				userHouse.setUid(uid);
				personInfoService.addHouseInfo(userHouse);
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.ADD_HOUSE_SUCCESS);
				result.setData(userHouse.getId());
			 }else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		}catch(CheckException e){
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());	
		}catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);	
		}
		LOG.info(String.format("添加房产信息接口 》》》》返回：%s",result.toString()));
		return result;
	}
	
	
	/**
	 * 更新房产信息
	 * @param file
	 * @param userHouse
	 * @return
	 */
	@RequestMapping(value={"/assetInfo/updateHouse"},method=RequestMethod.POST)
	@ResponseBody
	public Result<Object> updateHouseInfo(//@RequestParam("file") MultipartFile file,
			UserHouse userHouse) {
		final Result<Object> result = new Result<Object>();
		Integer uid = (Integer)SessionUtil.getUserLogonInfo();
		LOG.info(String.format("更新房产信息接口 》》》》参数：uid=%s,userHouse=%s",uid,userHouse.toString()));
		try {	
			if(userHouse!=null && uid!=null){
				userHouse.setUid(uid);
				personInfoService.updateHouseInfo(userHouse);
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.UPDATE_HOUSE_SUCCESS);
				result.setData(userHouse.getId());
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		}catch(CheckException e){
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());	
		}catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);	
		}
		LOG.info(String.format("更新房产信息接口 》》》》返回：%s",result.toString()));
		return result;
	}
	
	
	/**
	 * 删除某个房产信息
	 * @param userCar
	 * @return
	 */
	@RequestMapping(value={"/assetInfo/deleteHouse"},method=RequestMethod.GET)
	@ResponseBody
	public Result<Object> deleteHouseInfo(Integer houseId) {
		final Result<Object> result = new Result<Object>();
		LOG.info(String.format("删除某个房产信息接口 》》》》参数：houseId=%s",houseId));
		try {
			if(houseId!=null){
				personInfoService.deleteHouseInfo(houseId);
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.SELECT_HOUSE_SUCCESS);
				result.setData(houseId);
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);	
		}
		LOG.info(String.format("删除某个房产信息接口 》》》》返回：%s",result.toString()));
		return result;
	}


	/**
	 * 长富分获取接口
	 * @return  UserCreditValueDTO
	 */
	@RequestMapping(value={"/getScore"},method=RequestMethod.GET)
	@ResponseBody
	public Result<UserCreditValueDTO> getScore() {
		Integer uid =  (Integer)SessionUtil.getUserLogonInfo();
		LOG.info("长富分获取接口,UID:[{}]", uid);
		try {
			if(uid != null){
				List<UserCreditValue> userCreditValues = personInfoService.queryUserCreditValueListByUid(uid);
				if(CollectionUtils.isNotEmpty(userCreditValues)){
					UserCreditValueDTO userCreditValueDTO = new UserCreditValueDTO();
					userCreditValueDTO.setUid(uid);
					List<UserCreditItemValueDTO> userCreditItemValueDTOs = Lists.newArrayList();
					Integer totalScore = 0;
					for(UserCreditValue userCreditValue : userCreditValues){
						UserCreditItemValueDTO userCreditItemValueDTO = new UserCreditItemValueDTO();
						userCreditItemValueDTO.setValue(userCreditValue.getValue());
						userCreditItemValueDTO.setType(userCreditValue.getType().intValue());
						userCreditItemValueDTO.setDescription(UserCreditValueTypeEnum.getDescByType(userCreditValue.getType().intValue()));
						Integer standardInt =UserCreditValueTypeEnum.getStandardByType(userCreditValue.getType().intValue());
						userCreditItemValueDTO.setStandard(standardInt);
						BigDecimal value = new BigDecimal(userCreditValue.getValue());
						BigDecimal standard = new BigDecimal(standardInt);
						userCreditItemValueDTO.setRatio(value.divide(standard,2,RoundingMode.HALF_UP).doubleValue());
						totalScore += userCreditValue.getValue();
						userCreditItemValueDTOs.add(userCreditItemValueDTO);
					}
					userCreditValueDTO.setTotal(totalScore);
					userCreditValueDTO.setLevel(CreditLevelEnum.getLevelByValue(totalScore));
					userCreditValueDTO.setUserCreditItemValueDTOList(userCreditItemValueDTOs);
					userCreditValueDTO.setCreditLevelDTOs(CreditLevelEnum.getCreditLevels());
					return Results.newSuccessResult(userCreditValueDTO);
				}else{
					return Results.newFailedResult(ErrorMessageConstant.USER_CREDIT_VALUE_NOT_EXIST);
				}
			}
		} catch (Exception e) {
			LOG.error("长富分接口异常,Message:[{}]",e.getMessage());
			return Results.newFailedResult(ErrorMessageConstant.ERR_OCCURS);
		}
		return Results.newFailedResult(ErrorMessageConstant.USER_CREDIT_VALUE_NOT_EXIST);
	}




}


