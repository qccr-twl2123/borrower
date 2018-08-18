package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.LoanApplyAddress;
import com.trj.jk.web.domain.LoanApplyAddressCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoanApplyAddressMapper extends BaseMapper<LoanApplyAddress, LoanApplyAddressCriteria, Integer> {
}