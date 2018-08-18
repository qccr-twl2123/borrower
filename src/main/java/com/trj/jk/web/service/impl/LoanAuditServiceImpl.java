package com.trj.jk.web.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.trj.jk.web.domain.*;
import com.trj.jk.web.domain.entity.LimitAuditBean;
import com.trj.jk.web.enums.LoanApplyStatusEnum;
import com.trj.jk.web.enums.UserLoanShowStatusEnum;
import com.trj.jk.web.mapper.CodeMapper;
import com.trj.jk.web.mapper.LoanApplyMapper;
import com.trj.jk.web.mapper.LoanAuditMapper;
import com.trj.jk.web.mapper.LoanLimitMapper;
import com.trj.jk.web.model.response.CashOutRes;
import com.trj.jk.web.model.response.LoanLimitInsuranceRes;
import com.trj.jk.web.service.LoanAuditService;
import com.trj.jk.web.util.UtilConstant;
import com.trj.jk.web.validator.Assert;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by xierongli on 17/9/12.
 */
@Service
public class LoanAuditServiceImpl implements LoanAuditService {


    @Autowired
    private LoanAuditMapper loanAuditMapper;
    @Autowired
    private LoanApplyMapper loanApplyMapper;
    @Autowired
    private LoanLimitMapper loanLimitMapper;
    @Autowired
    private CodeMapper codeMapper;

    @Override
    public PageList<LimitAuditBean> getLimitAuditList(Integer uid, PageBounds pageBounds) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("uid", uid);
        PageList<LimitAuditBean> limitAuditBeanPageList = (PageList<LimitAuditBean>) loanApplyMapper.selectValidateLimitAuditList(paramMap, pageBounds);
        return buildList(limitAuditBeanPageList);
    }

    @Override
    public PageList<LimitAuditBean> getLimitAuditListByProduct(Integer uid, String productCode, PageBounds pageBounds) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("uid", uid);
        paramMap.put("productCode", productCode);
        PageList<LimitAuditBean> limitAuditBeanPageList = (PageList<LimitAuditBean>) loanApplyMapper.selectLimitAuditListByProductCode(paramMap, pageBounds);
        return buildList(limitAuditBeanPageList);
    }

    @Override
    public CashOutRes getCashOutModel(Integer userId,String orderNo, String productCode) {
        //检测申请订单
        LoanApplyCriteria loanApplyCriteria = new LoanApplyCriteria();
        loanApplyCriteria.createCriteria().andOrderNoEqualTo(orderNo).andUidEqualTo(userId);
        List<LoanApply> loanApplyList = loanApplyMapper.selectByCriteria(loanApplyCriteria);
        Assert.isTrue(CollectionUtils.isEmpty(loanApplyList),"订单号不合法");
        LoanApply loanApply = loanApplyList.get(0);
        Assert.isTrue(!loanApply.getStatus().equals(LoanApplyStatusEnum.SUCCESS.getCode().byteValue()),"该订单未审核通过");
        //检测订单审核
        LoanAuditCriteria loanAuditCriteria = new LoanAuditCriteria();
        loanAuditCriteria.createCriteria().andApplyLoanIdEqualTo(loanApply.getId());
        List<LoanAudit> loanAuditList = loanAuditMapper.selectByCriteria(loanAuditCriteria);
        Assert.isTrue(CollectionUtils.isEmpty(loanAuditList),"订单未审核");
        LoanAudit loanAudit = loanAuditList.get(0);
        CashOutRes cashOutRes = new CashOutRes();
        cashOutRes.setCreditAmount(loanAudit.getAmountLimit());
        //贷款期限
        List<Integer> loanTermList = Lists.newArrayList();
        loanTermList.add(6);
        loanTermList.add(12);
        cashOutRes.setLoanTermList(loanTermList);
        //借款用途
        List<String> loanUseTypeList = Lists.newArrayList();
        loanUseTypeList.add("婚庆服务");
        loanUseTypeList.add("旅游消费");
        loanUseTypeList.add("购车消费");
        loanUseTypeList.add("助学进修");
        loanUseTypeList.add("百货家电");
        loanUseTypeList.add("医疗服务");
        loanUseTypeList.add("装修建材");
        cashOutRes.setLoanUseTypeList(loanUseTypeList);
        cashOutRes.setMonthRate(loanAudit.getInterest().toString());
        //保险费用
        List<LoanLimitInsuranceRes> loanLimitInsuranceResList = Lists.newArrayList();
        LoanLimitInsuranceRes loanLimitInsuranceRes1 = new LoanLimitInsuranceRes();
        loanLimitInsuranceRes1.setTerm(6);
        loanLimitInsuranceRes1.setAmount((loanAudit.getAmountLimit().multiply(new BigDecimal("0.005")).setScale(2,BigDecimal.ROUND_HALF_UP)));
        LoanLimitInsuranceRes loanLimitInsuranceRes2 = new LoanLimitInsuranceRes();
        loanLimitInsuranceRes2.setTerm(12);
        loanLimitInsuranceRes2.setAmount((loanAudit.getAmountLimit().multiply(new BigDecimal("0.008")).setScale(2,BigDecimal.ROUND_HALF_UP)));
        loanLimitInsuranceResList.add(loanLimitInsuranceRes1);
        loanLimitInsuranceResList.add(loanLimitInsuranceRes2);
        cashOutRes.setLoanLimitInsuranceResList(loanLimitInsuranceResList);

        return cashOutRes;
    }



    public PageList<LimitAuditBean> buildList(PageList<LimitAuditBean> limitAuditBeanPageList) {
        if (limitAuditBeanPageList != null && !limitAuditBeanPageList.isEmpty()) {
            for (LimitAuditBean limitAuditBean : limitAuditBeanPageList) {
                LoanLimitCriteria loanLimitCriteria = new LoanLimitCriteria();
                loanLimitCriteria.createCriteria().andLoanApplyIdEqualTo(limitAuditBean.getApplyId());
                List<LoanLimit> loanLimits = loanLimitMapper.selectByCriteria(loanLimitCriteria);
                //计算 借款申请的 借款总额and借款数
                if (CollectionUtils.isNotEmpty(loanLimits)) {
                    BigDecimal loanAmount = new BigDecimal(0);
                    limitAuditBean.setLoanNumber(loanLimits.size());
                    for (LoanLimit loanLimit : loanLimits) {
                        loanAmount = loanAmount.add(loanLimit.getUseAmount());
                    }
                    limitAuditBean.setLoanAmount(loanAmount);
                }

                //设置还款方式集合
                CodeCriteria codeCriteria = new CodeCriteria();
                codeCriteria.createCriteria().andCodeKeyEqualTo(UtilConstant.REPAY_TYPE).andCodeNoEqualTo(limitAuditBean.getRepayType());
                List<Code> codes = codeMapper.selectByCriteria(codeCriteria);
                limitAuditBean.setRepayTypeList(codes);
                if (limitAuditBean.getInterest() != null) {
                    limitAuditBean.setInterest(limitAuditBean.getInterest().divide(new BigDecimal(100)));
                }

                //借款展示状态
                if (limitAuditBean.getStatus() == LoanApplyStatusEnum.RUNNING.getCode().byteValue()) {
                    limitAuditBean.setShowStatus(UserLoanShowStatusEnum.AUDIT_RUNNING.getCode().byteValue());
                    limitAuditBean.setShowStatusText(UserLoanShowStatusEnum.AUDIT_RUNNING.getDescription());
                } else {
                    limitAuditBean.setShowStatus(UserLoanShowStatusEnum.APPLY_LOAN.getCode().byteValue());
                    limitAuditBean.setShowStatusText(UserLoanShowStatusEnum.APPLY_LOAN.getDescription());
                }

                //设置productType
                CodeCriteria codeCriteria1 = new CodeCriteria();
                codeCriteria1.createCriteria().andCodeKeyEqualTo(UtilConstant.LOAN_PRODUCT_TYPE_KEY).andCodeNoEqualTo(limitAuditBean.getProductCode());
                limitAuditBean.setProductType(Integer.parseInt(codeMapper.selectByCriteria(codeCriteria1).get(0).getCodeName()));

                //设置CurTermRate
                Map<String, Object> rateMap = new LinkedHashMap<String, Object>();
                rateMap.put("6", "0.03");
                rateMap.put("6", "0.03");
                rateMap.put("12", "0.05");
                rateMap.put("18", "0.07");
                rateMap.put("18", "0.07");
                rateMap.put("24", "0.08");
                limitAuditBean.setCurTermRate(rateMap);

                //设置期限列表
                limitAuditBean.setTerm1List(generateTermList(limitAuditBean.getTerm1(), limitAuditBean.getTermUnit()));
                limitAuditBean.setTerm2List(generateTermList(limitAuditBean.getTerm2(), limitAuditBean.getTermUnit()));
            }
            return limitAuditBeanPageList;
        }
        return null;
    }


    public List<String> generateTermList(String term, String timeUnit) {
        if (StringUtils.isBlank(term)) {
            return new ArrayList<>();
        }
        List<String> termList = new ArrayList<String>();
        String[] term1Arra = term.split(",");
        for (String term1 : term1Arra) {
            CodeCriteria codeCriteria2 = new CodeCriteria();
            codeCriteria2.createCriteria().andCodeKeyEqualTo(UtilConstant.DATE_UNIT_KEY).andCodeNoEqualTo(timeUnit);
            termList.add(term1 + ("month".equals(timeUnit) ? "个" : "") + codeMapper.selectByCriteria(codeCriteria2).get(0).getCodeName());
        }
        return termList;
    }

}
