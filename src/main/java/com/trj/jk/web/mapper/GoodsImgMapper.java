package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.GoodsImg;
import com.trj.jk.web.domain.GoodsImgCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsImgMapper extends BaseMapper<GoodsImg, GoodsImgCriteria, Integer> {
}