package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.OrderItem;
import com.trj.jk.web.domain.OrderItemCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem, OrderItemCriteria, Integer> {
}