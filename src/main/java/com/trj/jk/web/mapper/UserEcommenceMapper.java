package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.UserEcommence;
import com.trj.jk.web.domain.UserEcommenceCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserEcommenceMapper extends BaseMapper<UserEcommence, UserEcommenceCriteria, Integer> {
}