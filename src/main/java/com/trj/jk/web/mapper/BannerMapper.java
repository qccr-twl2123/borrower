package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.Banner;
import com.trj.jk.web.domain.BannerCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BannerMapper extends BaseMapper<Banner, BannerCriteria, Integer> {
}