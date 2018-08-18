package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.Province;
import com.trj.jk.web.domain.ProvinceCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProvinceMapper extends BaseMapper<Province, ProvinceCriteria, String> {
}