package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.LoanApplyContacts;
import com.trj.jk.web.domain.LoanApplyContactsCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoanApplyContactsMapper extends BaseMapper<LoanApplyContacts, LoanApplyContactsCriteria, Integer> {
}