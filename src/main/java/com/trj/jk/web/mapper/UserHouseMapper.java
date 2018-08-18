package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.UserHouse;
import com.trj.jk.web.domain.UserHouseCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserHouseMapper extends BaseMapper<UserHouse, UserHouseCriteria, Integer> {
}