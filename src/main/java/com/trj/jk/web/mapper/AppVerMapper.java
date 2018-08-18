package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.AppVer;
import com.trj.jk.web.domain.AppVerCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppVerMapper extends BaseMapper<AppVer, AppVerCriteria, Integer> {
}