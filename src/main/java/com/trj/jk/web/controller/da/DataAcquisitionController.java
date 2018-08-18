package com.trj.jk.web.controller.da;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.trj.commons.result.Result;
import com.trj.jk.web.controller.BankCardController;
import com.trj.jk.web.controller.BaseController;
import com.trj.jk.web.domain.UserDeviceInfo;
import com.trj.jk.web.domain.UserFaceLog;
import com.trj.jk.web.domain.UserPhonebookInfo;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.domain.entity.ResultMessageConstant;
import com.trj.jk.web.domain.exception.CheckException;
import com.trj.jk.web.service.da.IDataAcquisitionService;

/**
 * app端数据采集Controller
 * @author l46001
 *
 */
@RequestMapping(value = {"/da"})
@RestController
public class DataAcquisitionController extends BaseController{
	
	private static final Logger LOG = Logger.getLogger(BankCardController.class);
	
	@Resource
	private IDataAcquisitionService dataAcquisitionService;
	
	/**
	 * 采集用户设备基本信息
	 * @param deviceInfo
	 * @return
	 */
	@RequestMapping(value="/deviceInfo",method=RequestMethod.POST)
	@ResponseBody
	public Result<Object> deviceInfo(UserDeviceInfo deviceInfo,String mobile) {
		Result<Object> result = new Result<Object>();
		LOG.info("采集用户设备基本信息接口开始执行....");
		LOG.info(String.format("参数：%s",deviceInfo.toString()));
		try{
			int id = dataAcquisitionService.addDeviceInfo(deviceInfo,mobile);
			result.setSuccess(true);
			result.setMessage(ResultMessageConstant.DA_DEVICE_SUCCESS);
			result.setData(id);
		}catch(CheckException e){
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		}catch(Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("采集用户设备基本信息接口返回结果：%s",result.toString()));
		return result;
	}
	
	
	/**
	 * 采集用户手机通讯录信息
	 * @param
	 * @return
	 */
	@RequestMapping(value="/phonebookInfo",method=RequestMethod.POST)
	@ResponseBody
	public Result<Object> phonebookInfo(UserPhonebookInfo userMobileAppInfo,String mobile) {
		Result<Object> result = new Result<Object>();
		LOG.info("采集用户手机通讯录信息接口开始执行....");
		LOG.info(String.format("参数：%s",userMobileAppInfo.toString()));
		try{
			int id = dataAcquisitionService.addPhonebookInfo(userMobileAppInfo,mobile);
			result.setSuccess(true);
			result.setMessage(ResultMessageConstant.DA_PHONEBOOK_SUCCESS);
			result.setData(id);
		}catch(CheckException e){
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		}catch(Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("采集用户手机安装的app信息接口返回结果：%s",result.toString()));
		return result;
	}
	
	/**
	 * 记录app端取活体记录接口
	 * @param userFaceLog
	 * @return
	 */
	@RequestMapping(value="/livingBody/record",method=RequestMethod.POST)
	@ResponseBody
	public Result<Object> livingBodyRecord(UserFaceLog userFaceLog) {
		Result<Object> result = new Result<Object>();
		LOG.info("=======记录app端取活体记录接口接口开始执行======");
		LOG.info(String.format("参数：%s",userFaceLog.toString()));
		try{
			dataAcquisitionService.livingBodyRecord(userFaceLog);
			result.setSuccess(true);
			result.setMessage(ResultMessageConstant.DA_PHONEBOOK_SUCCESS);
		}catch(CheckException e){
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		}catch(Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("记录app端取活体记录接口接口返回结果：%s",result.toString()));
		return result;
	}
}
