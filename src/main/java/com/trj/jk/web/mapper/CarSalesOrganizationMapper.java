package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.CarSalesOrganization;
import com.trj.jk.web.domain.CarSalesOrganizationCriteria;
import com.trj.mybatis.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CarSalesOrganizationMapper extends BaseMapper<CarSalesOrganization, CarSalesOrganizationCriteria, Integer> {
	List<CarSalesOrganization> getSoCity();
}