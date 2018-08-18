package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.UserCreditValue;
import com.trj.jk.web.domain.UserCreditValueCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserCreditValueMapper extends BaseMapper<UserCreditValue, UserCreditValueCriteria, Integer> {
}