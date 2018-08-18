package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.LoanApplyProfession;
import com.trj.jk.web.domain.LoanApplyProfessionCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoanApplyProfessionMapper extends BaseMapper<LoanApplyProfession, LoanApplyProfessionCriteria, Integer> {
}