package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.UserFaceLog;
import com.trj.jk.web.domain.UserFaceLogCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserFaceLogMapper extends BaseMapper<UserFaceLog, UserFaceLogCriteria, Integer> {
}