package com.trj.jk.web.service.thirdparty.soopay;


import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.trj.jk.web.domain.ThirdpartyRequestResponseLog;
import com.trj.jk.web.domain.entity.thirdparty.ErrorInfo;
import com.trj.jk.web.domain.entity.thirdparty.ErrorResult;
import com.trj.jk.web.domain.entity.thirdparty.ThirdPartyProcessResult;
import com.trj.jk.web.domain.entity.thirdparty.soopay.SignableAgreementRequesParam;
import com.trj.jk.web.domain.entity.thirdparty.soopay.SoopayBusBean;
import com.trj.jk.web.domain.exception.ServiceException;
import com.trj.jk.web.service.thirdparty.AbstractThirdPartyServiceImpl;
import com.trj.jk.web.service.thirdparty.IThirdPartyService;
import com.trj.jk.web.util.HttpClientUtils;
import com.trj.jk.web.util.MapUtil;
import com.umpay.api.common.ReqData;
import com.umpay.api.exception.VerifyException;
import com.umpay.api.paygate.v40.Mer2Plat_v40;
import com.umpay.api.paygate.v40.Plat2Mer_v40;
import com.umpay.api.util.PlainUtil;


public abstract class AbstractSoopayServiceImpl extends AbstractThirdPartyServiceImpl implements IThirdPartyService {
	public static Properties p = new Properties();
	private static Gson gson = new Gson();
	private static final Logger	LOG	= Logger.getLogger(AbstractSoopayServiceImpl.class);
	public static final String RET_CODE_0000 = "0000";
	public static final String ERR_CODE_0001 = "0001";
	public static final String ERR_CODE_00060710 = "00060710";
	public static final String MSG_SUCCESS = "返回成功";
	public static final String MSG_FAIL = "返回失败";
	
	private SignableAgreementRequesParam agreementParam;
	
	public SignableAgreementRequesParam getAgreementParam() {
		return agreementParam;
	}

	public void setAgreementParam(SignableAgreementRequesParam agreementParam) {
		this.agreementParam = agreementParam;
	}


	@Override
	public ThirdPartyProcessResult sendRequestProcess(Object bean, byte stage) throws Exception {
		String completeUrl = null;
		ThirdPartyProcessResult processResult = new ThirdPartyProcessResult();
		ErrorResult result = new ErrorResult();
		
		SoopayBusBean soopayBean = (SoopayBusBean)bean;
		if(soopayBean ==null || soopayBean.getService()==null || soopayBean.getBusBean()==null) {
			throw new ServiceException("请求参数错误"); 
		}
		
		agreementParam.setService(soopayBean.getService());
		//生成签名参数
		Map<String, String> signParamMap = MapUtil.getMapFromObject(soopayBean.getBusBean(), agreementParam);
		
		//完成签名
		LOG.info(gson.toJson(signParamMap));
		ReqData requestData = Mer2Plat_v40.makeReqDataByGet(signParamMap);
		completeUrl = requestData.getUrl();
		
		LOG.info("complete url = " + completeUrl);
		
		ThirdpartyRequestResponseLog log = generateLogWithRequest(IThirdPartyService.REQUEST_SOURCE_TRJ, stage, completeUrl, completeUrl);		
				
		if(StringUtils.isBlank(log.getResponseInfo())) {
			LOG.error("bind card fail, no response " + completeUrl);
			ErrorInfo errorInfo  = new ErrorInfo(ERR_CODE_0001, "联动优势请求失败");
			result.setMessage("联动优势请求失败");
			result.setError(errorInfo);
			processResult.setResult(result);
			return processResult;
		}
		
		//联动平台返回商户数据验签
		Map responseData = Plat2Mer_v40.getResData(log.getResponseInfo());

		if(RET_CODE_0000.equals((String)responseData.get("ret_code"))) {
			result.setMessage((String)responseData.get("ret_msg"));
			processResult.setResult(result);
			processResult.setData(responseData);
			log.setRemark(soopayBean.getService() + MSG_SUCCESS);
		}else {
			ErrorInfo errorInfo = new ErrorInfo((String)responseData.get("ret_code"), (String)responseData.get("ret_msg"));
			result.setError(errorInfo);
			processResult.setResult(result);
			processResult.setData(responseData);			
			log.setRemark(soopayBean.getService() + MSG_FAIL + "：　" + errorInfo.getCode() + "| " + errorInfo.getErrorMsg());			
		}
		
		processResult.setLog(log);
		
		return processResult;
	}

	@Override
	public ThirdPartyProcessResult notifyProcess(Object bean, byte stage) {
		ThirdPartyProcessResult processResult = new ThirdPartyProcessResult();
		String remark=null;
		ErrorResult result = new ErrorResult();		
		Map requestMap = (Map)bean;
		Map responseParamMap = new HashMap();
		String plain = null;
		responseParamMap.put("mer_id", agreementParam.getMer_id());
		responseParamMap.put("version", agreementParam.getVersion());
		setResponseParam(requestMap, responseParamMap);

		try{
			//验签
			Plat2Mer_v40.getPlatNotifyData(requestMap);
			//得到请求串
			Map retMap = PlainUtil.notifyPlain(requestMap, false);
			plain = retMap.get("plain").toString();
			responseParamMap.put("ret_code", RET_CODE_0000);
			responseParamMap.put("ret_msg", "处理成功");
			remark = requestMap.get("service") + MSG_SUCCESS;
					
		}catch (VerifyException e) {
			LOG.error(e.getMessage(), e);
			ErrorInfo errorInfo = new ErrorInfo(ERR_CODE_00060710,"验签失败");
			result.setError(errorInfo);
			responseParamMap.put("ret_code", ERR_CODE_00060710);
			responseParamMap.put("ret_msg", "验签失败");
			remark=requestMap.get("service") + MSG_FAIL + ": " + "验签失败";
		}
		
		StringBuffer response = new StringBuffer("<META NAME=\"MobilePayPlatform CONTENT=\"" );

		response.append(Mer2Plat_v40.merNotifyResData(responseParamMap)).append(" />");	
		
		//生成请求log信息
		ThirdpartyRequestResponseLog log = generateLogWithResponse(IThirdPartyService.REQUEST_SOURCE_THIRDPARTY, stage, plain,response.toString());

		log.setRemark(remark);
				
		if(!requestMap.get("error_code").equals(RET_CODE_0000)) {
			log.setRemark(requestMap.get("service") + MSG_FAIL + ": " + "交易返回码： " + requestMap.get("error_code"));
		}
		
		processResult.setLog(log);
		processResult.setResult(result);
		processResult.setData(requestMap);
		return processResult;
	}
	

	@Override
	protected String doSendRequest(Object requestParam) {
		String url=(String)requestParam;
		String response =  HttpClientUtils.httpGetByUrl(url);
		return response;	

	}
	
	protected abstract void setResponseParam(Map requestMap, Map responseParamMap);
		
}
