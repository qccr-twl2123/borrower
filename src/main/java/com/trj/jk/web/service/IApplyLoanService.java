package com.trj.jk.web.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.trj.jk.web.domain.*;
import com.trj.jk.web.domain.entity.LoanRepayPlanBean;
import com.trj.jk.web.model.request.LoanApplyReq;
import com.trj.jk.web.model.request.OpenLoanApplyReq;
import com.trj.jk.web.model.request.UserProfessionReq;

public interface IApplyLoanService {

	public Integer addLoanApply(LoanApply loanApply);


	public void saveFaceAuthResult(LoanFaceAuth authResult);

	public PageList<LoanRepayPlanBean> getLoanRepayPlansByDate(Integer uid,Integer status,String date,PageBounds pageBounds);

	public int addLoanApplyContactsInfo(List<UserContacts> contacts, Integer loanApplyId);

	public void updateLoanApply(LoanApply apply);

	public Integer addLoanApplyProfessionInfo(UserProfession userProfession,Integer loanApplyId);

	public Map<String, Object> getIdentityAuthInfo(Integer uid);

	public void finish(Integer applyId,Integer uid);

	public LoanApply selectByPrimaryKey(Integer uid);

	public void addLoanApplyAddress(LoanApplyAddress loanApplyAddress);

	public void addLoanApplyCar(LoanApplyCar loanApplyCar);

	public Map<String, Object> getLoanApplyCar(Integer loanApplyId);

	public Map<String, Object>  getBuyCarInfo(Integer loanApplyId,Integer uid);

	public List<Map<String, Object>> getSoCity();

	public List<Map<String, Object>> getSalesOrganization(String cityCode);

	public  List<LoanApply> queryLoanApply(Integer uid,Integer status);

	public Boolean saveLoanApply(LoanApplyReq loanApplyDTO, Integer uid);

	public int addLoanApplyContactsInfo(List<UserContacts> contacts, Integer loanApplyId,Integer uid,String stepCode);

	public Boolean saveLoanApplyProfessionInfo(UserProfessionReq userProfessionReq);

	public LoanApply getLoanApplyByOrderNo(Integer uid,String orderNo);


	public LoanProduct getLoanProductByCode(String productCode);

	public LoanApply getLoanApplyByApplyId(String applyId);

	public LoanLimit getLoanLimitByApplyId(Integer applyId);
}

