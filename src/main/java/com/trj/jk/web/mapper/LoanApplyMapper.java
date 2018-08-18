package com.trj.jk.web.mapper;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.trj.jk.web.domain.LoanApply;
import com.trj.jk.web.domain.LoanApplyCriteria;
import com.trj.jk.web.domain.entity.LimitAuditBean;
import com.trj.mybatis.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoanApplyMapper extends BaseMapper<LoanApply, LoanApplyCriteria, Integer> {

	 List<LimitAuditBean> selectValidateLimitAuditList(Map<String, Object> paramMap,PageBounds pageBounds);
	
	 List<LimitAuditBean> selectInvalidLimitAuditList(Map<String, Object> paramMap,PageBounds pageBounds);

	 List<LimitAuditBean> selectLimitAuditListByProductCode(Map<String, Object> paramMap,PageBounds pageBounds);

	 LoanApply queryRefuseApplyInMonth(@Param("uid") Integer uid, @Param("productId") Integer productId);

	 LoanApply queryRefuseApplyByVaildDate(@Param("uid") Integer uid, @Param("productId") Integer productId);

	 List<Map> getLoanApplyListByProductCode(@Param("uid") Integer uid, @Param("productCode") String productCode);

}