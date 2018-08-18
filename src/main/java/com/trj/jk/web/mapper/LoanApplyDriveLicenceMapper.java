package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.LoanApplyDriveLicence;
import com.trj.jk.web.domain.LoanApplyDriveLicenceCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoanApplyDriveLicenceMapper extends BaseMapper<LoanApplyDriveLicence, LoanApplyDriveLicenceCriteria, Integer> {
}