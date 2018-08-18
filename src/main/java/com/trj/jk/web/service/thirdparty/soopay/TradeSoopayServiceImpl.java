package com.trj.jk.web.service.thirdparty.soopay;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.trj.jk.web.domain.LoanRepayRecord;
import com.trj.jk.web.domain.LoanRepayRecordCriteria;
import com.trj.jk.web.domain.entity.constant.Status;
import com.trj.jk.web.domain.entity.thirdparty.ErrorResult;
import com.trj.jk.web.domain.entity.thirdparty.ThirdPartyProcessResult;
import com.trj.jk.web.domain.entity.thirdparty.soopay.SignableAgreementRequesParam;
import com.trj.jk.web.mapper.LoanRepayRecordMapper;
import com.trj.jk.web.service.thirdparty.IThirdPartyService;
import com.trj.jk.web.service.thirdparty.IThirdPartyTradeService;


@Transactional
public class TradeSoopayServiceImpl extends AbstractSoopayServiceImpl implements IThirdPartyTradeService{

	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

	@Autowired
	private LoanRepayRecordMapper loanRepayRecordMapper;
	
	@Resource(name="agreementParamWithoutNotify")
	private SignableAgreementRequesParam payAgreementParam;


	@Override
	public ErrorResult trade(Object bean, LoanRepayRecord payBean) throws Exception {
		//测试用不真实交易
//		List<Code> testCodeList = DictionaryUtil.getCodesByKey("E999");		
//		if(testCodeList != null && !testCodeList.isEmpty()){
//			payBean.setStatus(FiUserRepayRecord.ORDER_SUCCESS);
//			chargeBackService.saveUserRepayRecord(payBean);
//			return new ErrorResult();
//		}
		//发送交易请求		
		ThirdPartyProcessResult result = sendRequestProcess(bean, IThirdPartyService.REQUEST_TYPE_ORDER);
		result.getLog().setUid(payBean.getUid());
		if(result.getData()!=null){
			result.getLog().setRefId("trade_no: " + result.getData().get("trade_no"));
		}
		
		saveRequestResponseLog(result.getLog());
		
		if(result.getResult().getError()== null) {
			payBean.setStatus(Status.ORDER_SUCCESS);
		}else{
			payBean.setStatus(Status.ORDER_FAIL);
			payBean.setRepayErrorInfo(result.getResult().getError().getErrorMsg());
		}
		payBean.setTradeNo((String)result.getData().get("trade_no"));
		payBean.setTradeState((String)result.getData().get("trade_state"));
		loanRepayRecordMapper.insert(payBean);
		
		return result.getResult();		
	}

	@Override
	public ErrorResult pay(Object bean, LoanRepayRecord payBean) throws Exception {
		//测试用不真实交易
//		List<Code> testCodeList = DictionaryUtil.getCodesByKey("E999");		
//		if(testCodeList != null && !testCodeList.isEmpty()){
//			return new ErrorResult();
//		}
		
		setAgreementParam(payAgreementParam);
		//发送支付请求
		ThirdPartyProcessResult result = sendRequestProcess(bean, IThirdPartyService.REQUEST_TYPE_PAY);
		result.getLog().setUid(payBean.getUid().intValue());
		result.getLog().setRefId("trade_no: " + payBean.getTradeNo() + " cardId: " + payBean.getBankCardId());
		
		saveRequestResponseLog(result.getLog());
		
		payBean.setMerDate((String)result.getData().get("mer_date"));
		payBean.setPayDate((String)result.getData().get("pay_date"));
		payBean.setSettleDate((String)result.getData().get("settle_date"));
		payBean.setTradeState((String)result.getData().get("trade_state"));
			 				
		return result.getResult();		
	}
	

	public SignableAgreementRequesParam getPayAgreementParam() {
		return payAgreementParam;
	}

	public void setPayAgreementParam(SignableAgreementRequesParam payAgreementParam) {
		this.payAgreementParam = payAgreementParam;
	}

	@Override
	public ErrorResult tradeNotify(Object bean) throws Exception {
		ThirdPartyProcessResult result = notifyProcess(bean, IThirdPartyService.REQUEST_TYPE_TRADE_NOTIFY);
		String orderId =  (String)result.getData().get("order_id");
		LoanRepayRecordCriteria criteria = new LoanRepayRecordCriteria();
		criteria.createCriteria().andOrderIdEqualTo(orderId);
		LoanRepayRecord record=null;
		List<LoanRepayRecord> list = loanRepayRecordMapper.selectByCriteria(criteria);
		if(list !=null && list.size()>0){
			record = list.get(0);
		}
		if(record!=null){
			result.getLog().setUid(record.getUid());
		}else{
			result.getLog().setUid(00000);
		}
		result.getLog().setRefId("order id: " + orderId);
		
		saveRequestResponseLog(result.getLog());

		if(result.getResult().getError()== null) {
			Map data = result.getData();
			if(StringUtils.isNotBlank(orderId)) {
				record.setStatus(Status.REPAY_NOTIFY_RECEIVED);
				record.setPayType((String)data.get("pay_type"));
				record.setTradeState((String)data.get("trade_state"));
				record.setErrorCode((String)data.get("error_code"));
				record.setPaySeq((String)data.get("pay_seq"));
				loanRepayRecordMapper.updateByPrimaryKeySelective(record);
			}	
		}
		
		//TODO　发交易成功短信
		
		return result.getResult();		
	}

	@Override
	protected void setResponseParam(Map requestMap, Map responseParamMap) {
		String orderId = (String)requestMap.get("order_id");
		String sequenceNo = orderId.substring(4,orderId.length());
		responseParamMap.put("order_id", requestMap.get("order_id"));
		responseParamMap.put("mer_date", requestMap.get("mer_date"));
		responseParamMap.put("mer_check_date", dateFormat.format(new Date()));
		responseParamMap.put("mer_trace", sequenceNo);
	}	
		

	
}
