package com.trj.jk.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.gson.Gson;
import com.trj.commons.result.Result;
import com.trj.jk.web.domain.BankBranch;
import com.trj.jk.web.domain.City;
import com.trj.jk.web.domain.Code;
import com.trj.jk.web.domain.UserBankCard;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.domain.entity.ResultMessageConstant;
import com.trj.jk.web.domain.entity.bankcard.BankCardBean;
import com.trj.jk.web.domain.entity.constant.Constant;
import com.trj.jk.web.domain.entity.page.PageBean;
import com.trj.jk.web.domain.entity.thirdparty.ErrorResult;
import com.trj.jk.web.domain.entity.thirdparty.soopay.BindConfirmBusRequestParam;
import com.trj.jk.web.domain.entity.thirdparty.soopay.BindVerifyBusRequestParam;
import com.trj.jk.web.domain.entity.thirdparty.soopay.SoopayBusBean;
import com.trj.jk.web.domain.exception.ServiceException;
import com.trj.jk.web.service.IBankCardService;
import com.trj.jk.web.service.ICodeService;
import com.trj.jk.web.service.IConfigService;
import com.trj.jk.web.service.thirdparty.IThirdPartyCardService;
import com.trj.jk.web.util.SessionUtil;
import com.umpay.api.exception.ReqDataException;
import com.umpay.api.exception.RetDataException;

@RequestMapping(value = { "card" })
@RestController
public class BankCardController extends BaseController{
	private static final Logger LOG = Logger.getLogger(BankCardController.class);

	@Autowired
	private IThirdPartyCardService soopayCardService;
	
	@Autowired
	private IBankCardService bankCardService;
	
	@Autowired
	private ICodeService codeService;
	
	@Autowired
	private IConfigService configService;
	
	
	private static Gson gson=new Gson();
	
	
	
	
	/**
	 * 是否绑卡接口
	 * @return
	 */
	@RequestMapping(value = "/isBindCard", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> isBindCard() {
		Result<Object> jr = new Result<Object>();
		try{
			Integer uid = (Integer)SessionUtil.getUserLogonInfo();
			LOG.info("是否绑卡接口开始执行...");
			LOG.info(String.format("参数:uid=%s", uid));
			if(uid!=null){
				boolean flag = bankCardService.isBindCard(uid);
				jr.setSuccess(true);
				jr.setMessage(ResultMessageConstant.GET_IS_BIND_CARD_SUCCESS);
				Map< String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("isBindCard", flag);
				jr.setData(dataMap);
			}else{
				jr.setSuccess(false);
				jr.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		}catch(Exception e){
			LOG.error(e.getMessage());
			jr.setSuccess(false);
			jr.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("是否绑卡接口返回：%s", gson.toJson(jr)));
		return jr;
	}

	/**
	 * 绑卡申请
	 * 
	 * @return
	 */
	@RequestMapping(value = "/bind/request", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> toBind(BankCardBean payCardBean) {

		final Result<Object> jr = new Result<Object>();
		String exceptionMsg = null;
		ErrorResult result = null;
		
		if(payCardBean.getUid()==null){
			Integer uid = (Integer)SessionUtil.getUserLogonInfo();		
			payCardBean.setUid(uid);
		}

		if (StringUtils.isEmpty(payCardBean.getMobile())) {
			payCardBean.setMobile(payCardBean.getMediaId());
		}
		try {
			Object busBean = new BindVerifyBusRequestParam(payCardBean);
			SoopayBusBean bean = new SoopayBusBean();
			bean.setBusBean(busBean);
			bean.setService("req_bind_verify_shortcut");
			result = soopayCardService.bindCard(bean, payCardBean,true);
		} catch (ReqDataException e) {
			LOG.error(e.getMessage(), e);
			exceptionMsg = ErrorMessageConstant.ERR_SOOPAY_SIGN;
		} catch (RetDataException e) {
			LOG.error(e.getMessage(), e);
			exceptionMsg = ErrorMessageConstant.ERR_SOOPAY_VERIFY;
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			exceptionMsg = e.getMessage();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			exceptionMsg = ErrorMessageConstant.ERR_OCCURS;
		}

		if (exceptionMsg != null) {
			jr.setMessage(exceptionMsg);
			jr.setSuccess(false);
		} else {
			Map resultMap = new HashMap<String, Object>();
			resultMap.put("result", result);
			resultMap.put("cardId", payCardBean.getId());
			resultMap.put("userId", payCardBean.getUid());
			resultMap.put("bindNo", payCardBean.getBindNo());
			jr.setData(resultMap);
			jr.setMessage(ErrorMessageConstant.OK_BIND_CARD_APPLY);

		}

		return jr;

	}

	/**
	 * 绑卡确认
	 * 
	 * @return
	 */
	@RequestMapping(value = "/bind/confirm", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> bindConfirm(UserBankCard payCardBean, String verifyCode) {
		String exceptionMsg = null;
		ErrorResult result = null;
		final Result<Object> jr = new Result<Object>();

		try {
			if(payCardBean.getUid()==null){
				Integer uid = (Integer)SessionUtil.getUserLogonInfo();		
				payCardBean.setUid(uid);
			}
			Object busBean = new BindConfirmBusRequestParam(payCardBean, verifyCode);
			SoopayBusBean bean = new SoopayBusBean();
			bean.setBusBean(busBean);
			bean.setService("req_bind_confirm_shortcut");
			result = soopayCardService.bindConfirm(bean, payCardBean);
		} catch (ReqDataException e) {
			LOG.error(e.getMessage(), e);
			exceptionMsg = ErrorMessageConstant.ERR_SOOPAY_SIGN;
		} catch (RetDataException e) {
			LOG.error(e.getMessage(), e);
			exceptionMsg = ErrorMessageConstant.ERR_SOOPAY_VERIFY;
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			exceptionMsg = e.getMessage();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			exceptionMsg = ErrorMessageConstant.ERR_OCCURS;
		}

		if (exceptionMsg != null) {
			jr.setMessage(exceptionMsg);
			jr.setSuccess(false);
		} else {
			jr.setData(result);
			jr.setMessage(ErrorMessageConstant.OK_BIND_CARD_CONFIRM);
		}

		return jr;

	}
	
	/**
	 * 我的银行卡列表获取接口
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> list(PageBean bean){
		Result<Object> jr = new Result<Object>();
		HashMap map = new HashMap();
		LOG.info("银行卡列表获取接口开始执行...");
		try{
			Integer uid = (Integer)SessionUtil.getUserLogonInfo();
			LOG.info(String.format("参数：uid=%s", uid));
			PageBounds pageBounds=getPageBounds(bean);
			if(uid!=null){
				PageList<Map<String, Object>> cardList = bankCardService.getCardListByUid(uid, pageBounds);
				map.put("list", cardList);
				map.put("limit", cardList.getPaginator().getLimit());
				map.put("totalPages", cardList.getPaginator().getTotalPages());
				map.put("page", cardList.getPaginator().getPage());
				jr.setData(map);
				jr.setMessage(ErrorMessageConstant.SUCCESS);
			}else{
				jr.setSuccess(false);
				jr.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		}catch(Exception e){
			LOG.error(e.getMessage());
			jr.setSuccess(false);
			jr.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("银行卡列表获取接口返回：%s", gson.toJson(jr)));
		return jr;
		
	}

	
	/**
	 * 获取银行类型信息接口
	 * @return
	 */
	@RequestMapping(value = "/populate/info", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> getPopulateData(){
		Result<Object> jr = new Result<Object>();
		LOG.info("获取银行类型信息接口开始执行...");
		try{
			List<Code> bankList = codeService.getCodeByKey(Constant.CODE_BANK_BRANCH);
		
			jr.setData(bankList);
			jr.setMessage(ErrorMessageConstant.SUCCESS);
		}catch(Exception e){
			LOG.error(e.getMessage());
			jr.setSuccess(false);
			jr.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("获取银行类型信息接口返回：%s",gson.toJson(jr)));
		return jr;
		
	}	
	
	/**
	 * 获取银行城市信息接口
	 * @param provinceCode
	 * @return
	 */
	@RequestMapping(value = "/cities/get", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> getCitiesByProvinceCode(String provinceCode){
		Result<Object> jr = new Result<Object>();
		LOG.info("获取银行城市信息接口开始执行...");
		LOG.info(String.format("参数：provinceCode=%s",provinceCode));
		try{
			List<City> cities = configService.getCitiesByProvinceCode(provinceCode);
			jr.setData(cities);
			jr.setMessage(ErrorMessageConstant.SUCCESS);
		}catch(Exception e){
			LOG.error(e.getMessage());
			jr.setSuccess(false);
			jr.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("获取银行城市信息接口返回：%s",gson.toJson(jr)));
		return jr;
		
	}			
	
	/**
	 * 获取银行支行信息接口
	 * @param city
	 * @param bankCode
	 * @return
	 */
	@RequestMapping(value = "/bankbranches/get", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> getBankBranchesByCityCode(String city,String bankCode){
		Result<Object> jr = new Result<Object>();
		LOG.info("获取银行支行信息接口开始执行...");
		LOG.info(String.format("参数：city=%s,bankCode=%s",city,bankCode));
		try{
			List<BankBranch> bankBranches = configService.getBankBranchesByCity(city,bankCode);
			jr.setData(bankBranches);
			jr.setMessage(ErrorMessageConstant.SUCCESS);
		}catch(Exception e){
			LOG.error(e.getMessage());
			jr.setSuccess(false);
			jr.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("获取银行支行信息接口返回：%s",gson.toJson(jr)));
		return jr;
		
	}


	/**
	 * 绑卡申请
	 *
	 * @return
	 */
	@RequestMapping(value = "/bind/requestFromTrj", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> toBindFromTrj(BankCardBean payCardBean) {

		final Result<Object> jr = new Result<Object>();
		String exceptionMsg = null;
		ErrorResult result = null;

		if(payCardBean.getUid()==null){
			Integer uid = (Integer)SessionUtil.getUserLogonInfo();
			payCardBean.setUid(uid);
		}

		if (StringUtils.isEmpty(payCardBean.getMobile())) {
			payCardBean.setMobile(payCardBean.getMediaId());
		}
		try {
			Object busBean = new BindVerifyBusRequestParam(payCardBean);
			SoopayBusBean bean = new SoopayBusBean();
			bean.setBusBean(busBean);
			bean.setService("req_bind_verify_shortcut");
			result = soopayCardService.bindCard(bean, payCardBean,true);
		} catch (ReqDataException e) {
			LOG.error(e.getMessage(), e);
			exceptionMsg = ErrorMessageConstant.ERR_SOOPAY_SIGN;
		} catch (RetDataException e) {
			LOG.error(e.getMessage(), e);
			exceptionMsg = ErrorMessageConstant.ERR_SOOPAY_VERIFY;
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			exceptionMsg = e.getMessage();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			exceptionMsg = ErrorMessageConstant.ERR_OCCURS;
		}

		if (exceptionMsg != null) {
			jr.setMessage(exceptionMsg);
			jr.setSuccess(false);
		} else {
			Map resultMap = new HashMap<String, Object>();
			resultMap.put("result", result);
			resultMap.put("cardId", payCardBean.getId());
			resultMap.put("userId", payCardBean.getUid());
			resultMap.put("bindNo", payCardBean.getBindNo());
			jr.setData(resultMap);
			jr.setMessage(ErrorMessageConstant.OK_BIND_CARD_APPLY);

		}

		return jr;

	}

	/**
	 * 绑卡确认
	 *
	 * @return
	 */
	@RequestMapping(value = "/bind/confirmFromTrj", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> bindConfirmFromTrj(UserBankCard payCardBean, String verifyCode) {
		String exceptionMsg = null;
		ErrorResult result = null;
		final Result<Object> jr = new Result<Object>();

		try {
			if(payCardBean.getUid()==null){
				Integer uid = (Integer)SessionUtil.getUserLogonInfo();
				payCardBean.setUid(uid);
			}
			Object busBean = new BindConfirmBusRequestParam(payCardBean, verifyCode);
			SoopayBusBean bean = new SoopayBusBean();
			bean.setBusBean(busBean);
			bean.setService("req_bind_confirm_shortcut");
			result = soopayCardService.bindConfirm(bean, payCardBean);
		} catch (ReqDataException e) {
			LOG.error(e.getMessage(), e);
			exceptionMsg = ErrorMessageConstant.ERR_SOOPAY_SIGN;
		} catch (RetDataException e) {
			LOG.error(e.getMessage(), e);
			exceptionMsg = ErrorMessageConstant.ERR_SOOPAY_VERIFY;
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			exceptionMsg = e.getMessage();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			exceptionMsg = ErrorMessageConstant.ERR_OCCURS;
		}

		if (exceptionMsg != null) {
			jr.setMessage(exceptionMsg);
			jr.setSuccess(false);
		} else {
			jr.setData(result);
			jr.setMessage(ErrorMessageConstant.OK_BIND_CARD_CONFIRM);
		}

		return jr;

	}
	
	
}
