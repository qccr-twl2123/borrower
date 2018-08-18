package com.trj.jk.web.controller.v3;

import com.google.common.collect.Lists;
import com.trj.commons.result.Results;
import com.trj.commons.result.Result;
import com.trj.jk.web.controller.BaseController;
import com.trj.jk.web.convert.ObjectConvert;
import com.trj.jk.web.domain.*;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.domain.entity.bankcard.BankCardBean;
import com.trj.jk.web.domain.entity.constant.Constant;
import com.trj.jk.web.domain.entity.thirdparty.ErrorResult;
import com.trj.jk.web.domain.entity.thirdparty.soopay.BindConfirmBusRequestParam;
import com.trj.jk.web.domain.entity.thirdparty.soopay.BindVerifyBusRequestParam;
import com.trj.jk.web.domain.entity.thirdparty.soopay.SoopayBusBean;
import com.trj.jk.web.domain.exception.ServiceException;
import com.trj.jk.web.enums.BankCodeEnum;
import com.trj.jk.web.exception.RRException;
import com.trj.jk.web.model.request.BindBankCardApplyReq;
import com.trj.jk.web.model.request.BindBankCardConfirmReq;
import com.trj.jk.web.model.response.BankConfRes;
import com.trj.jk.web.model.response.BindBankCardApplyRes;
import com.trj.jk.web.service.*;
import com.trj.jk.web.service.thirdparty.IThirdPartyCardService;
import com.trj.jk.web.validator.Assert;
import com.trj.jk.web.validator.ValidatorUtils;
import com.umpay.api.exception.ReqDataException;
import com.umpay.api.exception.RetDataException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("BankCardController_v3")
@RequestMapping(value = {"/v3/card"})
public class BankCardController extends BaseController {

	@Autowired
	private IThirdPartyCardService soopayCardService;
	@Autowired
	private IBankCardService bankCardService;
	@Autowired
	private ICodeService codeService;
	@Autowired
	private IConfigService configService;
	@Autowired
	private BankBranchService bankBranchService;



	/**
	 * 查询用户是否绑定银行卡
	 * @param
	 * @return true or false
	 * @author xierongli
	 * @date 17/9/7 下午2:19
	 */
	@RequestMapping(value = "/isBindCard", method = RequestMethod.GET)
	public Result<Map< String, Object>> isBindCard() {
		logger.info("是否绑定银行卡");
		Integer uid = getCurrentUid();
		Assert.isNull(uid,"用户ID不为空");
		Boolean result = bankCardService.isBindCard(uid);
		Map< String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("isBindCard", result);
		return Results.newSuccessResult(dataMap);
	}

	/**
	 * 绑卡银行卡申请
	 * @param
	 * @return
	 * @author xierongli
	 * @date 17/9/7 下午2:38
	 */
	@RequestMapping(value = "/toBind", method = RequestMethod.POST)
	public Result<BindBankCardApplyRes> bindBankCardApply(BindBankCardApplyReq bindBankCardApplyReq) {
		logger.info("绑卡银行卡申请");
		ValidatorUtils.validateEntity(bindBankCardApplyReq);
		Assert.isTrue(CollectionUtils.isEmpty(bankBranchService.queryByCityNameAndName(bindBankCardApplyReq.getCity(),bindBankCardApplyReq.getBankBranch())),"支行信息不合法");
		Integer uid = getCurrentUid();
		Assert.isNull(uid,"用户ID不存在");
		bindBankCardApplyReq.setUid(uid);
		ErrorResult result = null;
		BankCardBean bankCardBean = null;
		try {
			bankCardBean = ObjectConvert.convertObject(bindBankCardApplyReq,BankCardBean.class);
			Object busBean = new BindVerifyBusRequestParam(bankCardBean);
			SoopayBusBean bean = new SoopayBusBean();
			bean.setBusBean(busBean);
			bean.setService("req_bind_verify_shortcut");
			result = soopayCardService.bindCard(bean, bankCardBean,false);
		} catch (ReqDataException e) {
			throw new RRException(ErrorMessageConstant.ERR_SOOPAY_SIGN);
		} catch (RetDataException e) {
			throw new RRException(ErrorMessageConstant.ERR_SOOPAY_VERIFY);
		} catch (ServiceException e) {
			throw new RRException(e.getMessage());
		} catch (Exception e) {
			throw new RRException(ErrorMessageConstant.ERR_OCCURS);
		}
		BindBankCardApplyRes bindBankCardApplyRes = new BindBankCardApplyRes();
		bindBankCardApplyRes.setResult(result);
		bindBankCardApplyRes.setUserId(bindBankCardApplyReq.getUid());
		bindBankCardApplyRes.setBindNo(bankCardBean.getBindNo());
		bindBankCardApplyRes.setUserBankCardId(bankCardBean.getId());
		return Results.newSuccessResult(bindBankCardApplyRes);

	}

	/**
	 * 绑卡银行卡确认
	 * @param   bindBankCardConfirmReq
	 * @return  
	 * @author xierongli
	 * @date 17/9/8 下午2:53
	 */
	@RequestMapping(value = "/bindConfirm", method = RequestMethod.POST)
	@ResponseBody
	public Result<ErrorResult> bindConfirm(BindBankCardConfirmReq bindBankCardConfirmReq) {
		logger.info("绑卡银行卡确认");
		Integer uid = getCurrentUid();
		Assert.isNull(uid,"用户ID不能为为空");
		ValidatorUtils.validateEntity(bindBankCardConfirmReq);
		bindBankCardConfirmReq.setUid(uid);
		String exceptionMsg = null;
		ErrorResult result = null;
		try {
			UserBankCard userBankCard = ObjectConvert.convertObject(bindBankCardConfirmReq,UserBankCard.class);
			Object busBean = new BindConfirmBusRequestParam(userBankCard, bindBankCardConfirmReq.getVerifyCode());
			SoopayBusBean bean = new SoopayBusBean();
			bean.setBusBean(busBean);
			bean.setService("req_bind_confirm_shortcut");
			result = soopayCardService.bindConfirm(bean, userBankCard);
		} catch (ReqDataException e) {
			logger.error(e.getMessage(), e);
			throw new RRException(ErrorMessageConstant.ERR_SOOPAY_SIGN);
		} catch (RetDataException e) {
			logger.error(e.getMessage(), e);
			throw new RRException(ErrorMessageConstant.ERR_SOOPAY_VERIFY);
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			throw new RRException(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RRException(ErrorMessageConstant.ERR_OCCURS);
		}
		return Results.newSuccessResult(result);

	}



	/**
	 * 获取银行类型信息接口
	 * @param
	 * @return
	 * @author xierongli
	 * @date 17/9/8 下午2:58
	 */
	@RequestMapping(value = "/banks", method = RequestMethod.GET)
	public Result<List<Code>> bankBranch(){
		logger.info("获取银行类型信息接口开始执行...");
//		Result<Object> jr = new Result<Object>();
		List<Code> bankList = codeService.getCodeByKey(Constant.CODE_BANK_BRANCH);
        //@去掉枚举控制
//		List<String> availableBankList = BankCodeEnum.getBankList();
		List<Code> newBankList = Lists.newArrayList();
		for(Code code : bankList){
//			if(availableBankList.contains(code.getCodeName())){
				newBankList.add(code);
//			}
		}
		return Results.newSuccessResult(newBankList);

	}


	/**
	 * 获取银行城市信息接口
	 * @param  provinceCode 省份code
	 * @return
	 * @author xierongli
	 * @date 17/9/8 下午3:01
	 */
	@RequestMapping(value = "/bankCity", method = RequestMethod.GET)
	public Result<Map< String, Object>> bankCity(String provinceCode){
		logger.info("获取银行城市信息接口开始执行...");
		List<Province> provinces = configService.getProvinces();
		List<City> cities = configService.getCitiesByProvinceCode(provinceCode);
		Map< String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("province", provinces);
		dataMap.put("city", cities);
		return Results.newSuccessResult(dataMap);

	}

	/**
	 * 获取银行支行信息接口
	 * @param cityCode
	 * @param bankCode
	 * @return
	 */
	@RequestMapping(value = "/bankBranches", method = RequestMethod.GET)
	public Result<List<BankBranch>> getBankBranchesByCityCode(String cityCode,String bankCode){
		logger.info("获取银行支行信息接口开始执行...");
		Assert.isBlank(cityCode,"城市编号不为空");
		Assert.isBlank(bankCode,"银行编码不为空");
		List<BankBranch> bankBranches = configService.getBankBranchesByCityCode(cityCode,bankCode);
		return Results.newSuccessResult(bankBranches);

	}
	/**
	 * 获取银行列表
	 * @return
	 * @author xierongli
	 * @date 17/9/8 下午3:52
	 */
	@RequestMapping(value = "/bank/List")
	public Result<List<BankConfRes>> getBankConfList(){
		logger.info("获取银行列表信息接口开始执行...");
		List<BankConfRes> bankConfResList = bankCardService.getBankConfList();
		return Results.newSuccessResult(bankConfResList);
	}
}
