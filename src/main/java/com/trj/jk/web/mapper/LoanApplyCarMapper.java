package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.LoanApplyCar;
import com.trj.jk.web.domain.LoanApplyCarCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoanApplyCarMapper extends BaseMapper<LoanApplyCar, LoanApplyCarCriteria, Integer> {
}