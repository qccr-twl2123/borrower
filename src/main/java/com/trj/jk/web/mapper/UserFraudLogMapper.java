package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.UserFraudLog;
import com.trj.jk.web.domain.UserFraudLogCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserFraudLogMapper extends BaseMapper<UserFraudLog, UserFraudLogCriteria, Integer> {
}