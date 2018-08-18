package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.UserCar;
import com.trj.jk.web.domain.UserCarCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserCarMapper extends BaseMapper<UserCar, UserCarCriteria, Integer> {
}