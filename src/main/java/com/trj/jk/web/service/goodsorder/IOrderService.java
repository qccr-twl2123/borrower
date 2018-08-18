package com.trj.jk.web.service.goodsorder;

import com.trj.jk.web.domain.Order;
import com.trj.jk.web.domain.OrderItem;
import com.trj.jk.web.dto.OrderItemDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangxin on 2017/5/31.
 */
public interface IOrderService {

    public Integer saveOrder(Order order, OrderItem orderItem);

    public Map<String, Object> getOrderAddressByTrjUid(String mobile,Integer trjUid);

    /*public boolean checkReferralCode(String referralCode);*/

    public Order findOrderById(Integer id);

    public void updateOrder(Order order);

    public List<OrderItemDTO> getOrderItemByUidOrMobile(Integer uid, String mobile);
    
    public void createOrderAudit(Order order);
}
