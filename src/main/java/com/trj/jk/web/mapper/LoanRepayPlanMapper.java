package com.trj.jk.web.mapper;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.trj.jk.web.domain.LoanRepayPlan;
import com.trj.jk.web.domain.entity.LoanRepayPlanBean;
import com.trj.jk.web.domain.LoanRepayPlanCriteria;
import com.trj.jk.web.domain.entity.RepayPlanInfoBean;
import com.trj.jk.web.dto.OrderBillDTO;
import com.trj.mybatis.mapper.BaseMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoanRepayPlanMapper extends BaseMapper<LoanRepayPlan, LoanRepayPlanCriteria, Integer> {
	
	 BigDecimal getMounthRepayAmount(Map<String, Object> paramMap);
	
	 List<RepayPlanInfoBean> getRepayPlans(Map<String, Object> paramMap, PageBounds pageBounds);

	 List<LoanRepayPlanBean> getLoanRepayPlansByUid(Map<String, Object> paramMap, PageBounds pageBounds);
	
	 List<LoanRepayPlanBean> getLoanRepayPlansByDate(Map<String, Object> paramMap, PageBounds pageBounds);

	 BigDecimal getInLoanAmountByUid(@Param("uid") Integer uid);

	 List<OrderBillDTO> getLoanRepayPlanByLimitId(Map map);
}