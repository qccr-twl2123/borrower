package com.trj.jk.web.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import com.google.common.collect.Lists;
import com.trj.jk.web.domain.*;
import com.trj.jk.web.dto.OrderBillDTO;
import com.trj.jk.web.enums.CutRateEnum;
import com.trj.jk.web.enums.LoanRepayPlanStatusEnum;
import com.trj.jk.web.model.request.CutInterestReq;
import com.trj.jk.web.model.request.MonthlyRepayReq;
import com.trj.jk.web.model.response.CutInterestRes;
import com.trj.jk.web.model.response.MonthRepaymentRes;
import com.trj.jk.web.model.response.MonthlyRepayRes;
import com.trj.jk.web.util.AverageCapitalPlusInterestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.trj.jk.web.domain.entity.ApplyDetailsBean;
import com.trj.jk.web.domain.entity.LimitAuditBean;
import com.trj.jk.web.domain.entity.LoanBean;
import com.trj.jk.web.domain.entity.LoanRepayPlanBean;
import com.trj.jk.web.domain.entity.RepayDetailsBean;
import com.trj.jk.web.domain.entity.RepayDetailsInfoBean;
import com.trj.jk.web.domain.entity.RepayPlanInfoBean;
import com.trj.jk.web.mapper.AttachmentMapper;
import com.trj.jk.web.mapper.CodeMapper;
import com.trj.jk.web.mapper.LoanApplyAddressMapper;
import com.trj.jk.web.mapper.LoanApplyContactsMapper;
import com.trj.jk.web.mapper.LoanApplyMapper;
import com.trj.jk.web.mapper.LoanApplyProfessionMapper;
import com.trj.jk.web.mapper.LoanAuditMapper;
import com.trj.jk.web.mapper.LoanFaceAuthMapper;
import com.trj.jk.web.mapper.LoanLimitMapper;
import com.trj.jk.web.mapper.LoanProductMapper;
import com.trj.jk.web.mapper.LoanRepayGeneralMapper;
import com.trj.jk.web.mapper.LoanRepayPlanMapper;
import com.trj.jk.web.mapper.LoanRepayRecordMapper;
import com.trj.jk.web.mapper.MobileLocationIntervalMapper;
import com.trj.jk.web.mapper.UserBasicMapper;
import com.trj.jk.web.mapper.UserContactsMapper;
import com.trj.jk.web.mapper.UserExtMapper;
import com.trj.jk.web.mapper.UserProfessionMapper;
import com.trj.jk.web.service.ILoanInfoService;
import com.trj.jk.web.service.RedisNumberGenerator;
import com.trj.jk.web.util.UtilConstant;

@Service
@Transactional
public class LoanInfoServiceImpl implements ILoanInfoService{

private static final Logger LOG = Logger.getLogger(ILoanInfoService.class);
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	
	@Resource
	private  LoanApplyMapper loanApplyMapper;
	@Resource
	private  LoanProductMapper loanProductMapper;
	@Resource
	private LoanLimitMapper loanLimitMapper;
	@Resource
	private LoanRepayPlanMapper loanRepayPlanMapper;
	@Resource
	private LoanRepayRecordMapper loanRepayRecordMapper;
	@Resource
	private CodeMapper codeMapper;
	@Resource
	private LoanRepayGeneralMapper loanRepayGeneralMapper;
	
	@Value("${app.upload.download}")
	private String downloadImgUrl;
	
	//crm系统域名
	@Value("${app.remote.domain.crm}")
	private String	remoteDomainCrm;

	@Value("${app.remote.domain.auditService}")
	private String auditProcessServiceUrl;	
	
	
	@Override
	public PageList<LoanBean> getMyLoansByUid(Integer uid,PageBounds pageBounds,Byte isValid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uid", uid);
		if(String.valueOf(isValid).equals(UtilConstant.VALIDATE)){
			PageList<LoanBean> loanBeans = (PageList<LoanBean>)loanLimitMapper.selectUnfinishedLoansByUid(paramMap,pageBounds);
			if(loanBeans!=null && !loanBeans.isEmpty()){
				for(LoanBean loanBean:loanBeans){
					CodeCriteria codeCriteria = new CodeCriteria();
					codeCriteria.createCriteria().andCodeKeyEqualTo(UtilConstant.DATE_UNIT_KEY).andCodeNoEqualTo(loanBean.getTermUnit());
					if("month".equals(loanBean.getTermUnit())){
						loanBean.setTermStr(loanBean.getTerm()+"个"+codeMapper.selectByCriteria(codeCriteria).get(0).getCodeName());
					}else{
						loanBean.setTermStr(loanBean.getTerm()+codeMapper.selectByCriteria(codeCriteria).get(0).getCodeName());
					}
					if(loanBean.getStatus()==UtilConstant.LOAN_LIMIT_STATUS_2){//正常还款中
						loanBean.setShowStatus(UtilConstant.APP_LOAN_SHOW_STATUS_6);
						LoanRepayPlanCriteria loanRepayPlanCriteria = new LoanRepayPlanCriteria();
						loanRepayPlanCriteria.createCriteria().andLoanLimitIdEqualTo( loanBean.getLoanLimitId()).andStatusNotEqualTo(Byte.parseByte("2"));
						loanRepayPlanCriteria.setOrderByClause(" period_number ASC");
						List<LoanRepayPlan> loanRepayPlans = loanRepayPlanMapper.selectByCriteria(loanRepayPlanCriteria);
						if(loanRepayPlans!=null && !loanRepayPlans.isEmpty()){
							loanBean.setRepayDate(dateFormat.format(loanRepayPlans.get(0).getRepayDate()));
							loanBean.setRepayAmount(loanRepayPlans.get(0).getAmount());
						}
					}
					if(loanBean.getStatus()==UtilConstant.LOAN_LIMIT_STATUS_3){//逾期
						LoanRepayGeneralCriteria loanRepayGeneralCriteria = new LoanRepayGeneralCriteria();
						loanRepayGeneralCriteria.createCriteria().andLoanLimitIdEqualTo(loanBean.getLoanLimitId()).andStatusEqualTo(Byte.parseByte("3"));
						List<LoanRepayGeneral> loanRepayGenerals = loanRepayGeneralMapper.selectByCriteria(loanRepayGeneralCriteria);
						BigDecimal repayAmount = new BigDecimal("0.00");
						if(loanRepayGenerals!=null && !loanRepayGenerals.isEmpty()){
							for(LoanRepayGeneral loanRepayGeneral:loanRepayGenerals){
								repayAmount=repayAmount.add(loanRepayGeneral.getThisPeriodAmount());
							}
						}
						loanBean.setRepayAmount(repayAmount);
						loanBean.setShowStatus(UtilConstant.APP_LOAN_SHOW_STATUS_5);
					}
					if(loanBean.getStatus()==UtilConstant.LOAN_LIMIT_STATUS_1){//募集中
						loanBean.setShowStatus(UtilConstant.APP_LOAN_SHOW_STATUS_8);
					}
				}
			}
			return loanBeans;
		}
		if(String.valueOf(isValid).equals(UtilConstant.INVALID)){
			PageList<LoanBean> loanBeans = (PageList<LoanBean>)loanLimitMapper.selectFinishedLoansByUid(paramMap,pageBounds);
			if(loanBeans!=null && !loanBeans.isEmpty()){
				for(LoanBean loanBean:loanBeans){
					CodeCriteria codeCriteria = new CodeCriteria();
					codeCriteria.createCriteria().andCodeKeyEqualTo(UtilConstant.DATE_UNIT_KEY).andCodeNoEqualTo(loanBean.getTermUnit());
					if("month".equals(loanBean.getTermUnit())){
						loanBean.setTermStr(loanBean.getTerm()+"个"+codeMapper.selectByCriteria(codeCriteria).get(0).getCodeName());
					}else{
						loanBean.setTermStr(loanBean.getTerm()+codeMapper.selectByCriteria(codeCriteria).get(0).getCodeName());
					}
					loanBean.setShowStatus(UtilConstant.APP_LOAN_SHOW_STATUS_7);
				}
			}
			return loanBeans;
		}
		return null;
	}



	@Override
	public PageList<LimitAuditBean> getLimitAuditList(Integer uid, PageBounds pageBounds, Byte isValid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uid", uid);
		if(String.valueOf(isValid).equals(UtilConstant.VALIDATE)){
			PageList<LimitAuditBean> dataList = (PageList<LimitAuditBean>)loanApplyMapper.selectValidateLimitAuditList(paramMap,pageBounds);
			if(dataList!=null && !dataList.isEmpty()){
				for(LimitAuditBean bean:dataList){
					Integer applyId = bean.getApplyId();
					if(applyId!=null){
						LoanLimitCriteria loanLimitCriteria = new LoanLimitCriteria();
						loanLimitCriteria.createCriteria().andLoanApplyIdEqualTo(applyId);
						List<LoanLimit> loanLimits = loanLimitMapper.selectByCriteria(loanLimitCriteria);
						if(loanLimits!=null && !loanLimits.isEmpty()){
							BigDecimal loanAmount = new BigDecimal(0);
							bean.setLoanNumber(loanLimits.size());
							for(LoanLimit loanLimit:loanLimits){
								loanAmount = loanAmount.add(loanLimit.getUseAmount());
							}
							bean.setLoanAmount(loanAmount);
						}else{
							bean.setLoanNumber(0);
							bean.setLoanAmount(new BigDecimal(0));
						}
					}
					CodeCriteria codeCriteria = new CodeCriteria();
					codeCriteria.createCriteria().andCodeKeyEqualTo(UtilConstant.REPAY_TYPE).andCodeNoEqualTo(bean.getRepayType());
					List<Code> codes = codeMapper.selectByCriteria(codeCriteria);
					bean.setRepayTypeList(codes);
					if(bean.getInterest()!=null){
						bean.setInterest(bean.getInterest().divide(new BigDecimal(100)));
					}
					if(bean.getStatus()==UtilConstant.LOAN_APPLY_STATUS_1){
						bean.setShowStatus(UtilConstant.APP_LOAN_SHOW_STATUS_4);
					}else{
						bean.setShowStatus(UtilConstant.APP_LOAN_SHOW_STATUS_3);
					}
					CodeCriteria codeCriteria1 = new CodeCriteria(); 
					codeCriteria1.createCriteria().andCodeKeyEqualTo(UtilConstant.LOAN_PRODUCT_TYPE_KEY).andCodeNoEqualTo(bean.getProductCode());
					bean.setProductType(Integer.parseInt(codeMapper.selectByCriteria(codeCriteria1).get(0).getCodeName()));
					Map<String, Object> rateMap = new LinkedHashMap<String, Object>() {
						{
							put("6", "0.03");
							put("12", "0.05");
							put("18", "0.07");
							put("24", "0.08");
						}
					};
					bean.setCurTermRate(rateMap);
					if(bean.getTermUnit()!=null){
						if(bean.getTerm1()!=null){
							List<String> term1List = new ArrayList<String>();
							String[] term1Arra = bean.getTerm1().split(",");
							for(String term1:term1Arra){
								CodeCriteria codeCriteria2= new CodeCriteria();
								codeCriteria2.createCriteria().andCodeKeyEqualTo(UtilConstant.DATE_UNIT_KEY).andCodeNoEqualTo(bean.getTermUnit());
								term1List.add(term1+("month".equals(bean.getTermUnit())?"个":"")+codeMapper.selectByCriteria(codeCriteria2).get(0).getCodeName());
							}
							bean.setTerm1List(term1List);
						}
						if(bean.getTerm2()!=null){
							List<String> term2List = new ArrayList<String>();
							String[] term2Arra = bean.getTerm2().split(",");
							for(String term2:term2Arra){
								CodeCriteria codeCriteria3= new CodeCriteria();
								codeCriteria3.createCriteria().andCodeKeyEqualTo(UtilConstant.DATE_UNIT_KEY).andCodeNoEqualTo(bean.getTermUnit());
								term2List.add(term2+("month".equals(bean.getTermUnit())?"个":"")+codeMapper.selectByCriteria(codeCriteria3).get(0).getCodeName());
							}
							bean.setTerm2List(term2List);
						}
					}
				}
			}
			return dataList;
		}
		if(String.valueOf(isValid).equals(UtilConstant.INVALID)){
			PageList<LimitAuditBean> limitAuditBeans = (PageList<LimitAuditBean>)loanApplyMapper.selectInvalidLimitAuditList(paramMap,pageBounds);
			if(limitAuditBeans!=null && !limitAuditBeans.isEmpty()){
				for(LimitAuditBean limitAuditBean:limitAuditBeans){
					Integer applyId = limitAuditBean.getApplyId();
					if(applyId!=null){
						LoanLimitCriteria loanLimitCriteria = new LoanLimitCriteria();
						loanLimitCriteria.createCriteria().andLoanApplyIdEqualTo(applyId);
						List<LoanLimit> loanLimits = loanLimitMapper.selectByCriteria(loanLimitCriteria);
						if(loanLimits!=null && !loanLimits.isEmpty()){
							BigDecimal loanAmount = new BigDecimal(0);
							limitAuditBean.setLoanNumber(loanLimits.size());
							for(LoanLimit loanLimit:loanLimits){
								loanAmount = loanAmount.add(loanLimit.getUseAmount());
							}
							limitAuditBean.setLoanAmount(loanAmount);
						}else{
							limitAuditBean.setLoanNumber(0);
							limitAuditBean.setLoanAmount(new BigDecimal(0));
						}
					}
					if(limitAuditBean.getStatus()==UtilConstant.LOAN_APPLY_STATUS_3){
						limitAuditBean.setShowStatus(UtilConstant.APP_LOAN_SHOW_STATUS_1);
					}else{
						if(limitAuditBean.getLoanNumber()!=0){
							limitAuditBean.setShowStatus(UtilConstant.APP_LOAN_SHOW_STATUS_2);
						}else{
							limitAuditBean.setShowStatus(UtilConstant.APP_LOAN_SHOW_STATUS_0);
						}
					}
				}
			}
			
			return limitAuditBeans;
		}
		return null;
	}

	@Override
	public Object getUnFinishLoansDetails(Integer loanLimitId) {
			ApplyDetailsBean applyDetailsBean = new ApplyDetailsBean();
			LoanLimit loanLimit=loanLimitMapper.selectByPrimaryKey(loanLimitId);
			if(loanLimit!=null){
				applyDetailsBean.setLoanLimitId(loanLimitId);
				applyDetailsBean.setLoanAmount("¥"+loanLimit.getUseAmount());
				CodeCriteria codeCriteria = new CodeCriteria();
				codeCriteria.createCriteria().andCodeKeyEqualTo(UtilConstant.DATE_UNIT_KEY).andCodeNoEqualTo(loanLimit.getTermUnit());
				if("month".equals(loanLimit.getTermUnit())){					
					applyDetailsBean.setTerm(loanLimit.getTerm()+"个"+codeMapper.selectByCriteria(codeCriteria).get(0).getCodeName());
				}else{
					applyDetailsBean.setTerm(loanLimit.getTerm()+codeMapper.selectByCriteria(codeCriteria).get(0).getCodeName());
				}
				
				CodeCriteria codeCriteria1 = new CodeCriteria();
				codeCriteria1.createCriteria().andCodeKeyEqualTo(UtilConstant.LOAN_LIMIT_STATUS_KEY).andCodeNoEqualTo(String.valueOf(loanLimit.getStatus()));

				applyDetailsBean.setStatus(codeMapper.selectByCriteria(codeCriteria1).get(0).getCodeName());
				applyDetailsBean.setApplyNo(loanLimit.getLoanReceiptId());

				LoanProduct loanProduct = loanProductMapper.selectByPrimaryKey(loanLimit.getProductId());
				applyDetailsBean.setProductName(loanProduct.getName());

				LoanRepayPlanCriteria loanRepayPlanCriteria = new LoanRepayPlanCriteria();
				loanRepayPlanCriteria.createCriteria().andLoanLimitIdEqualTo(loanLimitId).andStatusNotEqualTo(Byte.parseByte("2"));
				loanRepayPlanCriteria.setOrderByClause(" period_number ASC");

				List<LoanRepayPlan> loanRepayPlans = loanRepayPlanMapper.selectByCriteria(loanRepayPlanCriteria);
				if(loanRepayPlans!=null){
					applyDetailsBean.setWaitRepayPeriods(loanRepayPlans.size()+"期");
				}
				if(loanLimit.getStatus()==2){//正常还款中
					LoanRepayPlan loanRepayPlan=loanRepayPlans.get(0);
					applyDetailsBean.setRepayAmount("¥"+loanRepayPlan.getAmount());
					applyDetailsBean.setRepayDate(dateFormat.format(loanRepayPlan.getRepayDate()));
					applyDetailsBean.setPenaltyInterestAmount((loanRepayPlan.getPenaltyInterestAmount()==null?"¥0.00":("¥"+loanRepayPlan.getPenaltyInterestAmount())));
				}
				if(loanLimit.getStatus()==3){//逾期
					LoanRepayGeneralCriteria loanRepayGeneralCriteria = new LoanRepayGeneralCriteria();
					loanRepayGeneralCriteria.createCriteria().andLoanLimitIdEqualTo(loanLimitId);
					LoanRepayGeneral loanRepayGeneral = loanRepayGeneralMapper.selectByCriteria(loanRepayGeneralCriteria).get(0);
					applyDetailsBean.setRepayDate(dateFormat.format(new Date()));
					applyDetailsBean.setRepayAmount("¥"+loanRepayGeneral.getThisPeriodAmount());
					applyDetailsBean.setPenaltyInterestAmount(loanRepayGeneral.getPenaltyInterestAmount()==null?"¥0.00":("¥"+loanRepayGeneral.getPenaltyInterestAmount()));
				}
			
			
			List<RepayDetailsBean> repayDetailsBeans = new ArrayList<RepayDetailsBean>();
			List<RepayDetailsInfoBean> list1 = new ArrayList<RepayDetailsInfoBean>();
			list1.add(new RepayDetailsInfoBean("借据号",applyDetailsBean.getApplyNo()));
			list1.add(new RepayDetailsInfoBean("产品名称",applyDetailsBean.getProductName()));
			List<RepayDetailsInfoBean> list2 = new ArrayList<RepayDetailsInfoBean>();
			list2.add(new RepayDetailsInfoBean("借款金额",String.valueOf(applyDetailsBean.getLoanAmount())));
			list2.add(new RepayDetailsInfoBean("借款期限",applyDetailsBean.getTerm()));
			list2.add(new RepayDetailsInfoBean("借款状态",String.valueOf(applyDetailsBean.getStatus())));
			list2.add(new RepayDetailsInfoBean("剩余还款期数",String.valueOf(applyDetailsBean.getWaitRepayPeriods())));
			List<RepayDetailsInfoBean> list3 = new ArrayList<RepayDetailsInfoBean>();
			list3.add(new RepayDetailsInfoBean("本期还款日",String.valueOf(applyDetailsBean.getRepayDate())));
			list3.add(new RepayDetailsInfoBean("本期还款金额",String.valueOf(applyDetailsBean.getRepayAmount())));
			list3.add(new RepayDetailsInfoBean("罚息",applyDetailsBean.getPenaltyInterestAmount()==null?"¥0.00":String.valueOf(applyDetailsBean.getPenaltyInterestAmount())));
			RepayDetailsBean repayDetailsBean1 = new RepayDetailsBean();
			repayDetailsBean1.setLabel("基本信息");
			repayDetailsBean1.setList(list1);
			repayDetailsBeans.add(repayDetailsBean1);
			RepayDetailsBean repayDetailsBean2 = new RepayDetailsBean();
			repayDetailsBean2.setLabel("借款信息");
			repayDetailsBean2.setList(list2);
			repayDetailsBeans.add(repayDetailsBean2);
			RepayDetailsBean repayDetailsBean3 = new RepayDetailsBean();
			repayDetailsBean3.setLabel("还款信息");
			repayDetailsBean3.setList(list3);
			repayDetailsBeans.add(repayDetailsBean3);
			return repayDetailsBeans;
		}
			return null;
	}

	@Override
	public PageList<Map<String, Object>> getFinishLoansDetails(Integer loanLimitId,PageBounds pageBounds) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanLimitId", loanLimitId);
		paramMap.put("repayDelayStatus", UtilConstant.REPAY_DELAY_STATUS_KEY);
		PageList<Map<String, Object>> LoansDetailsList = (PageList<Map<String, Object>>)loanRepayRecordMapper.getLoansDetails(paramMap,pageBounds);
		return LoansDetailsList;
	}
	
	
	@Override
	public PageList<RepayPlanInfoBean> getRepayPlans(Integer loanLimitId, PageBounds pageBounds) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanLimitId", loanLimitId);
		PageList<RepayPlanInfoBean> dataList = (PageList<RepayPlanInfoBean>)loanRepayPlanMapper.getRepayPlans(paramMap,pageBounds);
		if(dataList!=null && !dataList.isEmpty()){
			for(RepayPlanInfoBean repayPlanInfoBean:dataList){
				if(repayPlanInfoBean.getStatus()== LoanRepayPlanStatusEnum.SUCCESS.getCode().byteValue()){
					repayPlanInfoBean.setStatusDescribe("已还");
				}else if (repayPlanInfoBean.getStatus()==LoanRepayPlanStatusEnum.PART.getCode().byteValue()) {
					repayPlanInfoBean.setStatusDescribe("部分已还");
				}else{
					repayPlanInfoBean.setStatusDescribe("未还");
				}
			}
		}
		return dataList;
	}
	
	@Override
	public PageList<LoanRepayPlanBean> getLoanRepayPlansByUid(Integer uid,Integer status,PageBounds pageBounds) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uid", uid);
		paramMap.put("status", status);
		PageList<LoanRepayPlanBean> dataList=(PageList<LoanRepayPlanBean>) loanRepayPlanMapper.getLoanRepayPlansByUid(paramMap,pageBounds);
		return dataList;
	}
	

	@Override
	public PageList<LoanRepayPlanBean> getLoanRepayPlansByDate(Integer uid,Integer status,String date,PageBounds pageBounds) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uid", uid);
		paramMap.put("status", status);
		paramMap.put("date", date);
		PageList<LoanRepayPlanBean> dataList=(PageList<LoanRepayPlanBean>) loanRepayPlanMapper.getLoanRepayPlansByDate(paramMap,pageBounds);
		return dataList;
	}
	



	@Override
	public MonthlyRepayRes getMonthlyRepay(MonthlyRepayReq monthlyRepayReq) {
		MonthlyRepayRes monthlyRepayRes = new MonthlyRepayRes();
		Double yearRate = 0.0;
		String yearRateText ="";
		int term = 0;
		if(UtilConstant.REPAY_TYPE_PERMONTH.equals(monthlyRepayReq.getRepayType())){
			if(monthlyRepayReq.getTerm()!=null && monthlyRepayReq.getTerm().contains("月")){
				term = Integer.parseInt(monthlyRepayReq.getTerm().substring(0,monthlyRepayReq.getTerm().indexOf("个")));
				if(0< term &&term<=3){
					yearRate=0.08;
					yearRateText="8%";
				}else if (3< term &&term<=6) {
					yearRate=0.10;
					yearRateText="10%";
				}else if (6< term &&term<=12) {
					yearRate=0.11;
					yearRateText="11%";
				}else{
					yearRate=0.12;
					yearRateText="12%";
				}
			}
		}
		monthlyRepayRes.setYearRate(yearRateText);
		double perMonthPrincipalInterest = AverageCapitalPlusInterestUtils.getPerMonthPrincipalInterest(monthlyRepayReq.getLoanAmount().doubleValue(), yearRate, term);
		monthlyRepayRes.setMonthlyRepayAmount(String.valueOf(perMonthPrincipalInterest));
		return monthlyRepayRes;
	}



	public LoanProduct getLoanProductByCode(String productCode) {
		LoanProductCriteria loanProductCriteria = new LoanProductCriteria();
		loanProductCriteria.createCriteria().andProductCodeEqualTo(productCode);
		List<LoanProduct> productList = loanProductMapper.selectByCriteria(loanProductCriteria);
		return productList.isEmpty() ? null : productList.get(0);
            }

    public CutInterestRes getLoanProductRateTry(CutInterestReq cutInterestReq){
		BigDecimal loanAmount = new BigDecimal(cutInterestReq.getAmount());
        //计算保险费
		BigDecimal cutAmount = calculateInsurance(cutInterestReq.getTerm(),loanAmount);
		//可用金额
		BigDecimal limitAmount = loanAmount.subtract(cutAmount);
		//每期还款
		BigDecimal rateAmount = loanAmount.multiply(new BigDecimal(cutInterestReq.getInterest()));
		BigDecimal termAmount = (loanAmount.divide(new BigDecimal(cutInterestReq.getTerm()),2,BigDecimal.ROUND_HALF_UP).add(rateAmount)).setScale(2,BigDecimal.ROUND_HALF_UP);;

		//计算每期应还
		List<MonthRepaymentRes> monthRepaymentResList = Lists.newArrayList();
		for (int i = 1; i <= cutInterestReq.getTerm(); i++) {
			MonthRepaymentRes  monthRepaymentRes = new MonthRepaymentRes();
			monthRepaymentRes.setTerm(i);
			monthRepaymentRes.setTermAmount(termAmount.toString());
			monthRepaymentResList.add(monthRepaymentRes);
        }

		//封装返回数据
		CutInterestRes cutInterestRes = new CutInterestRes();
		cutInterestRes.setCutAmount(cutAmount.toString());
		cutInterestRes.setLimitAmount(limitAmount.toString());
		cutInterestRes.setTermList(monthRepaymentResList);
		cutInterestRes.setInterest(cutInterestReq.getInterest());
		cutInterestRes.setTerm(cutInterestReq.getTerm());
		return cutInterestRes;
    }

    public  BigDecimal calculateInsurance(Integer term, BigDecimal amount){
		//测算费率
		BigDecimal initAmount = BigDecimal.ZERO;
		if(term == 6){
			initAmount =  (amount.multiply(new BigDecimal("0.05"))).setScale(2,BigDecimal.ROUND_HALF_UP);
		}else if(term == 12){
			initAmount = (amount.multiply(new BigDecimal("0.08"))).setScale(2,BigDecimal.ROUND_HALF_UP);
		}
		//计算每月购买份数
		Integer monthShare = (int)Math.ceil(initAmount.divide(new BigDecimal("10"),2, BigDecimal.ROUND_HALF_EVEN).divide(new BigDecimal(term.toString()),2, BigDecimal.ROUND_HALF_EVEN).doubleValue());
		//计算总保额
		BigDecimal totalAmount= new BigDecimal(monthShare * 10 * term);
		return totalAmount;
	}



	@Override
	public Map<String, Object> getLoanRepayPlanByLimitId(Integer limitId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		LoanRepayGeneralCriteria loanRepayGeneralCriteria=new LoanRepayGeneralCriteria();
		loanRepayGeneralCriteria.createCriteria().andLoanLimitIdEqualTo(limitId);
		LoanRepayGeneral loanRepayGeneral=null;
		List<LoanRepayGeneral> loanRepayGenerals=loanRepayGeneralMapper.selectByCriteria(loanRepayGeneralCriteria);
		List<OrderBillDTO> orderBillDTOList=null;
		if(null!=loanRepayGenerals&&loanRepayGenerals.size()>0){
			loanRepayGeneral=loanRepayGenerals.get(0);
			Map map=new HashMap();
			map.put("limitId",limitId);
			orderBillDTOList=loanRepayPlanMapper.getLoanRepayPlanByLimitId(map);
			dataMap.put("amount",loanRepayGeneral.getAmount());
			dataMap.put("restamount",loanRepayGeneral.getRestAmount());
			dataMap.put("repayedamount",loanRepayGeneral.getRepayedAmount());
			dataMap.put("orderbill",orderBillDTOList);

		}
		return dataMap;
	}

	@Override
	public PageList<LoanLimit> getLoanApplyScrollingMessage(PageBounds bean) {
		LoanLimitCriteria loanLimitCriteria = new LoanLimitCriteria();
		loanLimitCriteria.createCriteria().andStatusEqualTo(new Byte("2")).andTenantEqualTo("jkWeb");
		loanLimitCriteria.setOrderByClause("id desc");
		return (PageList<LoanLimit>) loanLimitMapper.selectByCriteriaWithRowbounds(loanLimitCriteria, bean);
	}

}
