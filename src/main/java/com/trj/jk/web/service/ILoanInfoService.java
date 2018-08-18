package com.trj.jk.web.service;

import java.math.BigDecimal;
import java.util.Map;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.trj.jk.web.domain.LoanLimit;
import com.trj.jk.web.domain.entity.LimitAuditBean;
import com.trj.jk.web.domain.entity.LoanBean;
import com.trj.jk.web.domain.entity.LoanRepayPlanBean;
import com.trj.jk.web.domain.entity.RepayPlanInfoBean;
import com.trj.jk.web.model.request.CutInterestReq;
import com.trj.jk.web.model.request.MonthlyRepayReq;
import com.trj.jk.web.model.response.CutInterestRes;
import com.trj.jk.web.model.response.MonthlyRepayRes;

public interface ILoanInfoService {

	
	 PageList<LimitAuditBean> getLimitAuditList(Integer uid,PageBounds pageBounds,Byte isValid);
	
	 PageList<LoanBean> getMyLoansByUid(Integer uid,PageBounds pageBounds,Byte isValid);
	
	 Object getUnFinishLoansDetails(Integer loanLimitId);

	 PageList<Map<String, Object>> getFinishLoansDetails(Integer loanLimitId,PageBounds pageBounds);
	
	 PageList<RepayPlanInfoBean> getRepayPlans(Integer loanLimitId,PageBounds pageBounds);
	
	 PageList<LoanRepayPlanBean> getLoanRepayPlansByUid(Integer uid,Integer status,PageBounds pageBounds);
	
	 PageList<LoanRepayPlanBean> getLoanRepayPlansByDate(Integer uid,Integer status,String date,PageBounds pageBounds);
	 Map<String, Object> getLoanRepayPlanByLimitId(Integer limitId);

	 MonthlyRepayRes getMonthlyRepay(MonthlyRepayReq monthlyRepayReq);


	 CutInterestRes getLoanProductRateTry(CutInterestReq cutInterestReq);

	 PageList<LoanLimit> getLoanApplyScrollingMessage(PageBounds bean);

	BigDecimal calculateInsurance(Integer term, BigDecimal amount);
}
