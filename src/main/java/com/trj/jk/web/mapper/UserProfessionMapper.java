package com.trj.jk.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.trj.jk.web.domain.UserProfession;
import com.trj.jk.web.domain.UserProfessionCriteria;
import com.trj.mybatis.mapper.BaseMapper;

@Mapper
public interface UserProfessionMapper extends BaseMapper<UserProfession, UserProfessionCriteria, Integer> {
	public List<Map<String, Object>> getProfessionInfoByUid(Integer uid);
}