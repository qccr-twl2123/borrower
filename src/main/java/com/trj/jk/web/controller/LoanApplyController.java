package com.trj.jk.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.trj.commons.result.Result;
import com.trj.jk.web.domain.LoanApply;
import com.trj.jk.web.domain.LoanApplyAddress;
import com.trj.jk.web.domain.LoanApplyCar;
import com.trj.jk.web.domain.UserContacts;
import com.trj.jk.web.domain.UserProfession;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.domain.entity.ResultMessageConstant;
import com.trj.jk.web.domain.entity.personInfo.certfication.CertificationPage;
import com.trj.jk.web.domain.exception.CheckException;
import com.trj.jk.web.service.IApplyLoanService;
import com.trj.jk.web.service.IPersonInfoService;
import com.trj.jk.web.service.IThreadTaskService;
import com.trj.jk.web.service.zhima.ZhimaCreditScoreService;
import com.trj.jk.web.task.async.ZhimaCreditScoreTask;
import com.trj.jk.web.util.DateTimeUtil;
import com.trj.jk.web.util.SessionUtil;



/**
 * 融资申请相关业务controller类
 * @author l46001
 *
 */
@RestController
@RequestMapping(value={"/loan/apply"})
public class LoanApplyController {
	private static final Logger LOG = Logger.getLogger(LoanApplyController.class);

	@Autowired
	private IPersonInfoService personInfoService;

	@Resource
	private IApplyLoanService applyLoanService;

	@Resource
	private ZhimaCreditScoreService zhimaCreditScoreService;

	@Autowired
	private IThreadTaskService threadTaskService;

	/**
	 * 借款流程获取产品对应的认证页面信息接口
	 * @param productId
	 * @param loanApplyId
	 * @return
	 */
	@RequestMapping(value = "/certfication/get", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> getCertfication(Integer productId,Integer loanApplyId){
		Result<Object> jr = new Result<Object>();
		LOG.info("借款流程获取产品对应的认证页面信息接口开始执行...");
		try{
			Integer userId = (Integer)SessionUtil.getUserLogonInfo();
			LOG.info(String.format("参数：productId=%s,loanApplyId=%s,uid=%s", productId,loanApplyId,userId));
			CertificationPage page = personInfoService.getCertificationPage(userId, productId,loanApplyId);
			jr.setData(CertificationPage.transform(page));
			jr.setMessage(ResultMessageConstant.GET_CERTIFICATION_SUCCESS);

		}catch(Exception e){
			LOG.error(e.getMessage());
			jr.setSuccess(false);
			jr.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("借款流程获取产品对应的认证页面信息接口返回：%s",jr.toString()));
		return jr;

	}


	/**
	 * 借款申请提交接口
	 * @param bean
	 * @return
	 */
	@RequestMapping(value={"/add"},method=RequestMethod.POST)
	@ResponseBody
	public Result<Object> addLoanApply(LoanApply loanApply) {
		final Result<Object> result = new Result<Object>();
		LOG.info("借款申请提交接口开始执行...");
		try {
			Integer uid = (Integer)SessionUtil.getUserLogonInfo();
			if(uid!=null && loanApply!=null){
				LOG.info(String.format("参数：uid=%s,loanApply=%s", uid,loanApply));
				loanApply.setUid(uid);
				applyLoanService.addLoanApply(loanApply);
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.ADD_LOAN_APPLY_SUCCESS);
				result.setData(loanApply);
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		} catch(CheckException e){
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("借款申请提交接口返回：%s",result.toString()));
		return result;
	}


	/**
	 * 借款流程添加联系人信息接口
	 * @param contactStr
	 * @param loanApplyId
	 * @return
	 */
	@RequestMapping(value={"/contacts/fill"},method=RequestMethod.POST)
	@ResponseBody
	public Result<Object> fillContacts(@RequestParam(value="contactStr",required=true) String contactStr, @RequestParam(value="loanApplyId",required=true) Integer loanApplyId){
		final Result<Object> result = new Result<Object>();
		Gson gson =  new Gson();
		try {
			Integer uid = (Integer)SessionUtil.getUserLogonInfo();
			List<UserContacts> contacts=(List<UserContacts>)gson.fromJson(contactStr,new TypeToken<List<UserContacts>>(){}.getType());
			LOG.info("借款流程添加联系人信息接口开始执行...");
			if(contacts!=null && contacts.size()>2 && loanApplyId!=null){
				LOG.info(String.format("参数：loanApplyId=%s,contacts=%s", loanApplyId,contacts.toString()));
				int id=applyLoanService.addLoanApplyContactsInfo(contacts, loanApplyId);
				result.setMessage(ResultMessageConstant.ADD_CONTACTS_SUCCESS);
				result.setData(contacts);
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}

		} catch(CheckException e){
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("借款流程添加联系人信息接口返回：%s",result.toString()));
		return result;



	}


	/**
	 * 借款流程添加职业信息接口
	 * @param userProfession
	 * @param loanApplyId
	 * @return
	 */
	@RequestMapping(value={"/profession/fill"},method=RequestMethod.POST)
	@ResponseBody
	public Result<Object> fillProfession(UserProfession userProfession,Integer loanApplyId){
		final Result<Object> result = new Result<Object>();
		try {
			Integer uid = (Integer)SessionUtil.getUserLogonInfo();
			LOG.info("借款申请流程职业信息接口开始执行....");
			if(userProfession!=null && loanApplyId!=null){
				LOG.info(String.format("参数：userProfession:%s,loanApplyId:%s", userProfession.toString(),loanApplyId));
				userProfession.setUid(uid);
				applyLoanService.addLoanApplyProfessionInfo(userProfession,loanApplyId);
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.ADD_PROFESSION_SUCCESS);
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		} catch(CheckException e){
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		return result;
	}


	/**
	 * 借款流程地址信息处理接口
	 * @param loanApplyAddress
	 * @return
	 */
	@RequestMapping(value={"/address/fill"},method=RequestMethod.POST)
	@ResponseBody
	public Result<Object> fillAddress(LoanApplyAddress loanApplyAddress){
		final Result<Object> result = new Result<Object>();
		LOG.info("借款流程地址信息处理接口开始执行....");
		LOG.info(String.format("参数：loanApplyAddress=%s", loanApplyAddress.toString()));
		try {
			if(loanApplyAddress!=null && loanApplyAddress.getLoanApplyId()!=null){
				applyLoanService.addLoanApplyAddress(loanApplyAddress);
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.SUBMIT_ADREES_SUCCESS);
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		} catch(CheckException e){
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("借款流程地址信息处理接口返回：%s", result.toString()));
		return result;
	}

	/**
	 * 借款流程购车宝订车信息页面接口
	 * @param loanApplyCar
	 * @return
	 */
	@RequestMapping(value={"/buyCarInfo/get"},method=RequestMethod.GET)
	@ResponseBody
	public Result<Object> getBuyCarInfo(Integer loanApplyId){
		final Result<Object> result = new Result<Object>();
		LOG.info("借款流程购车宝订车信息页面接口开始执行....");
		try {
			Integer uid=(Integer)SessionUtil.getUserLogonInfo();
			if(loanApplyId!=null&&uid!=null){
				LOG.info(String.format("参数：uid=%s,loanApplyId=%s",uid,loanApplyId));
				Map<String,Object> dataMap = applyLoanService.getBuyCarInfo(loanApplyId,uid);
				result.setSuccess(true);
				result.setData(dataMap);
				result.setMessage(ResultMessageConstant.GET_BUYCAR_INFO_SUCCESS);
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		} catch(CheckException e){
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("借款流程购车宝订车信息页面接口返回：%s", result.toString()));
		return result;
	}


	/**
	 * 借款流程购车宝添加购买车辆信息时获取销售机构城市信息接口
	 * so:销售机构
	 * @return
	 */
	@RequestMapping(value={"/salesCity/get"},method=RequestMethod.GET)
	@ResponseBody
	public Result<Object> getSalesCity(){
		final Result<Object> result = new Result<Object>();
		LOG.info("借款流程购车宝添加购买车辆信息时获取销售机构城市信息接口开始执行....");
		try {
			List<Map<String, Object>> dataList = applyLoanService.getSoCity();
			result.setSuccess(true);
			result.setData(dataList);
			result.setMessage(ResultMessageConstant.GET_SALES_ORGANIZATION_CITY_SUCCESS);
		} catch(CheckException e){
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("借款流程购车宝添加购买车辆信息时获取销售机构城市信息接口返回：%s", result.toString()));
		return result;
	}


	/**
	 * 借款流程购车宝添加购买车辆信息时获取销售机构名称列表接口
	 * @return
	 */
	@RequestMapping(value={"/salesOrganization/get"},method=RequestMethod.GET)
	@ResponseBody
	public Result<Object> getSalesOrganization(String cityCode){
		final Result<Object> result = new Result<Object>();
		LOG.info("借款流程购车宝添加购买车辆信息时获取销售机构名称列表接口开始执行....");
		try {
			List<Map<String, Object>> dataList = applyLoanService.getSalesOrganization(cityCode);
			result.setSuccess(true);
			result.setData(dataList);
			result.setMessage(ResultMessageConstant.GET_SALES_ORGANIZATION_SUCCESS);
		} catch(CheckException e){
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("借款流程购车宝添加购买车辆信息时获取销售机构名称列表接口返回：%s", result.toString()));
		return result;
	}

	/**
	 * 借款流程购车宝车辆信息处理接口
	 * @param loanApplyAddress
	 * @return
	 */
	@RequestMapping(value={"/car/fill"},method=RequestMethod.POST)
	@ResponseBody
	public Result<Object> fillCar(LoanApplyCar loanApplyCar){
		final Result<Object> result = new Result<Object>();
		LOG.info("借款流程购车宝车辆信息处理接口开始执行....");
		try {
			if(loanApplyCar!=null && loanApplyCar.getLoanApplyId()!=null){
				LOG.info(String.format("参数：loanApplyCar=%s", loanApplyCar.toString()));
				applyLoanService.addLoanApplyCar(loanApplyCar);
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.ADD_CAR_SUCCESS);
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		} catch(CheckException e){
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("借款流程购车宝车辆信息处理接口返回：%s", result.toString()));
		return result;
	}

	/**
	 * 借款流程购车宝根据借款申请Id获取车辆信息接口
	 * @param loanApplyAddress
	 * @return
	 */
	@RequestMapping(value={"/car/get"},method=RequestMethod.GET)
	@ResponseBody
	public Result<Object> getCar(Integer loanApplyId){
		final Result<Object> result = new Result<Object>();
		LOG.info("借款流程购车宝根据借款申请Id获取车辆信息接口开始执行....");
		LOG.info(String.format("参数：loanApplyId=%s", loanApplyId));
		try {
			if(loanApplyId!=null){
				Map<String, Object> dataMap = applyLoanService.getLoanApplyCar(loanApplyId);
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.GET_CAR_SUCCESS);
				result.setData(dataMap);
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		} catch(CheckException e){
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("借款流程购车宝根据借款申请Id获取车辆信息接口返回：%s", result.toString()));
		return result;
	}

	/**
	 * 借款流程身份信息认证方式获取
	 * @return
	 */
	@RequestMapping(value={"/getIdentityAuthInfo"},method=RequestMethod.GET)
	@ResponseBody
	public Result<Object> getIdentityAuthInfo(){
		LOG.info("借款流程身份信息认证方式获取接口开始执行....");
		final Result<Object> result = new Result<Object>();
		try {
			Integer uid = (Integer)SessionUtil.getUserLogonInfo();
			if(uid!=null){
				Map<String, Object> dataMap = applyLoanService.getIdentityAuthInfo(uid);
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.GET_IDENTITY_AUTH_SUCCESS);
				result.setData(dataMap);
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		} catch(CheckException e){
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("借款流程身份信息认证方式获取接口返回：%s", result.toString()));
		return result;

	}

	/**
	 * 借款流程借款申请最后完成接口
	 * @param applyId
	 * @param userExt
	 * @param type
	 * @return
	 */
	@RequestMapping(value={"/finish"},method=RequestMethod.POST)
	@ResponseBody
	public Result<Object> finish(Integer applyId){
		final Result<Object> result = new Result<Object>();
		LOG.info("借款流程借款申请最后完成接口开始执行....");
		LOG.info(String.format("finish接口在调传入参数：applyId：%s",applyId));
		try {
			Integer uid = (Integer)SessionUtil.getUserLogonInfo();
			applyLoanService.finish(applyId,uid);
			//此处处理芝麻信用分的检测
			LoanApply loanApply=applyLoanService.selectByPrimaryKey(applyId);
			List<Integer> scorelist = new ArrayList<Integer>(Arrays.asList(600, 650, 700, 750, 900));//此处临时做了假数据
			String currentDate= DateTimeUtil.getFormatDate(new Date(), "yyyy-MM-dd 00:00:00");
			String targetDate=DateTimeUtil.getFormatDate(new Date(), "yyyy-MM-06 59:59:59");
			Date startDate=null;
			Date endDate=new Date();
			if(currentDate.compareTo(targetDate)>0){
				Calendar cal_1=Calendar.getInstance();//获取当前日期
				cal_1.set(Calendar.DAY_OF_MONTH,6);//设置为6号,当前日期既为本月第6天
				startDate=cal_1.getTime();
			}else{
				Calendar cal_1=Calendar.getInstance();//获取当前日期
				cal_1.add(Calendar.MONTH, -1);
				cal_1.set(Calendar.DAY_OF_MONTH,6);//设置为6号,当前日期既为上月第6天
				startDate =cal_1.getTime();
			}
			for(int score: scorelist){
				boolean isSelected=zhimaCreditScoreService.hasQuery(loanApply.getIdentityId(), score, startDate, endDate);
				if(!isSelected){
					threadTaskService.asyncExecute(new ZhimaCreditScoreTask(loanApply.getName(), loanApply.getIdentityId(), score));
				}
			}
			result.setSuccess(true);
			result.setMessage(ResultMessageConstant.SUBMIT_LOAN_APPLY_SUCCESS);
		} catch(CheckException e){
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("借款流程借款申请最后完成接口返回：%s", result.toString()));
		return result;

	}




}


