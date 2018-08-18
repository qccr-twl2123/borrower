package com.trj.jk.web.mapper;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.trj.jk.web.domain.LoanLimit;
import com.trj.jk.web.domain.LoanLimitCriteria;
import com.trj.jk.web.domain.entity.LoanBean;
import com.trj.mybatis.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoanLimitMapper extends BaseMapper<LoanLimit, LoanLimitCriteria, Integer> {

	public List<LoanBean> selectUnfinishedLoansByUid(Map<String, Object> paramMap,PageBounds pageBounds);

	public List<LoanBean>  selectFinishedLoansByUid(Map<String, Object> paramMap,PageBounds pageBounds);
}