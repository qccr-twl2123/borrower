package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.UserDriveLicence;
import com.trj.jk.web.domain.UserDriveLicenceCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDriveLicenceMapper extends BaseMapper<UserDriveLicence, UserDriveLicenceCriteria, Integer> {
}