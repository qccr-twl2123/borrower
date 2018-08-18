package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.UserPhonebookInfo;
import com.trj.jk.web.domain.UserPhonebookInfoCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserPhonebookInfoMapper extends BaseMapper<UserPhonebookInfo, UserPhonebookInfoCriteria, Integer> {
}