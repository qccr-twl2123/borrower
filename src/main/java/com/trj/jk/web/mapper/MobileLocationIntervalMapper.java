package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.MobileLocationInterval;
import com.trj.jk.web.domain.MobileLocationIntervalCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MobileLocationIntervalMapper extends BaseMapper<MobileLocationInterval, MobileLocationIntervalCriteria, Integer> {
}