package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.AppDownloadDeviceInfo;
import com.trj.jk.web.domain.AppDownloadDeviceInfoCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppDownloadDeviceInfoMapper extends BaseMapper<AppDownloadDeviceInfo, AppDownloadDeviceInfoCriteria, Integer> {
}