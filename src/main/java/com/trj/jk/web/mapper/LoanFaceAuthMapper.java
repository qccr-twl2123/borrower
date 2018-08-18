package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.LoanFaceAuth;
import com.trj.jk.web.domain.LoanFaceAuthCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoanFaceAuthMapper extends BaseMapper<LoanFaceAuth, LoanFaceAuthCriteria, Integer> {
}