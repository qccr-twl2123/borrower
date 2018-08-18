package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.CarSoBankCard;
import com.trj.jk.web.domain.CarSoBankCardCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CarSoBankCardMapper extends BaseMapper<CarSoBankCard, CarSoBankCardCriteria, Integer> {
}