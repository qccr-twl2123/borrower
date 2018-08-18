package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.Goods;
import com.trj.jk.web.domain.GoodsCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsMapper extends BaseMapper<Goods, GoodsCriteria, Integer> {
}