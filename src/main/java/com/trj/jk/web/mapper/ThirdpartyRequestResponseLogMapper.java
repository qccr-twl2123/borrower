package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.ThirdpartyRequestResponseLog;
import com.trj.jk.web.domain.ThirdpartyRequestResponseLogCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ThirdpartyRequestResponseLogMapper extends BaseMapper<ThirdpartyRequestResponseLog, ThirdpartyRequestResponseLogCriteria, Integer> {
}