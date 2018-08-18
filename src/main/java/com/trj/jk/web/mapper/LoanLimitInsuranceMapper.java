package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.LoanLimitInsurance;
import com.trj.jk.web.domain.LoanLimitInsuranceCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoanLimitInsuranceMapper extends BaseMapper<LoanLimitInsurance, LoanLimitInsuranceCriteria, Integer> {
}