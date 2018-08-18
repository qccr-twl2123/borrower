package com.trj.jk.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.trj.jk.web.domain.LoanRepayRecord;
import com.trj.jk.web.domain.LoanRepayRecordCriteria;
import com.trj.jk.web.domain.entity.RepayRecordBean;
import com.trj.mybatis.mapper.BaseMapper;

@Mapper
public interface LoanRepayRecordMapper extends BaseMapper<LoanRepayRecord, LoanRepayRecordCriteria, Integer> {
	public List<RepayRecordBean> selectRepayRecordByUid(Map<String, Object> paramMap,PageBounds pageBounds);
	
	public List<Map<String, Object>> getLoansDetails(Map<String, Object> paramMap,PageBounds pageBounds);

	public List<RepayRecordBean> selectRepayRecordByLoanApplyId(Map<String, Object> paramMap, PageBounds pageBounds);

}