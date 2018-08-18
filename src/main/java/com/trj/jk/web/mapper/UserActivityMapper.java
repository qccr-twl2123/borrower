package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.UserActivity;
import com.trj.jk.web.domain.UserActivityCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserActivityMapper extends BaseMapper<UserActivity, UserActivityCriteria, Integer> {
}