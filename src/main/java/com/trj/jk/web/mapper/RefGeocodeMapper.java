package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.RefGeocode;
import com.trj.jk.web.domain.RefGeocodeCriteria;
import com.trj.mybatis.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RefGeocodeMapper extends BaseMapper<RefGeocode, RefGeocodeCriteria, Integer> {

	public List<RefGeocode> getAllProvinceInfo();
	
	public List<RefGeocode> getAllCityInfoByProvinceCode(Map<String, Object> paramMap);
	
	public List<RefGeocode> getAllDistrictInfoByCityCode(Map<String, Object> paramMap);
	
}