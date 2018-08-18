package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.UserContacts;
import com.trj.jk.web.domain.UserContactsCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserContactsMapper extends BaseMapper<UserContacts, UserContactsCriteria, Integer> {
}