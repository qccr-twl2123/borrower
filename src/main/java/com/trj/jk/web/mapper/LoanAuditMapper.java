package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.LoanAudit;
import com.trj.jk.web.domain.LoanAuditCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoanAuditMapper extends BaseMapper<LoanAudit, LoanAuditCriteria, Integer> {
}