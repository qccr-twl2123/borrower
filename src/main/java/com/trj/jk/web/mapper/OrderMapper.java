package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.Order;
import com.trj.jk.web.domain.OrderCriteria;
import com.trj.jk.web.dto.OrderItemDTO;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper extends BaseMapper<Order, OrderCriteria, Integer> {

    public Integer saveOrder(Order order);

    public List<OrderItemDTO> getOrderItemByUidOrMobile(Map map);
}