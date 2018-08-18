package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.UserDeviceInfo;
import com.trj.jk.web.domain.UserDeviceInfoCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDeviceInfoMapper extends BaseMapper<UserDeviceInfo, UserDeviceInfoCriteria, Integer> {
}