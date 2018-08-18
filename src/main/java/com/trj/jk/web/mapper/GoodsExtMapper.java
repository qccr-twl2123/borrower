package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.GoodsExt;
import com.trj.jk.web.domain.GoodsExtCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsExtMapper extends BaseMapper<GoodsExt, GoodsExtCriteria, Integer> {
}