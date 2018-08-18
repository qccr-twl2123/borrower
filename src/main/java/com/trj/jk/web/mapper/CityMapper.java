package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.City;
import com.trj.jk.web.domain.CityCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CityMapper extends BaseMapper<City, CityCriteria, String> {
}