package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.UserBasic;
import com.trj.jk.web.domain.UserBasicCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserBasicMapper extends BaseMapper<UserBasic, UserBasicCriteria, Integer> {
}