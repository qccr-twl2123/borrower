package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.UserExt;
import com.trj.jk.web.domain.UserExtCriteria;
import com.trj.jk.web.domain.entity.user.UserBean;
import com.trj.mybatis.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserExtMapper extends BaseMapper<UserExt, UserExtCriteria, Integer> {
	
	public UserBean getUserInfo(Integer uid);
}