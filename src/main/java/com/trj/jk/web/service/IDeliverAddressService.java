package com.trj.jk.web.service;

import com.trj.jk.web.domain.DeliverAddress;

import java.util.List;

/**
 * 收货地址service
 * Created by xierongli on 17/6/7.
 */
public interface IDeliverAddressService {

    int insert(DeliverAddress deliverAddress);

    List<DeliverAddress> querForList(Integer uid);

    DeliverAddress queryForDeliverAddress(Integer uid);

    Integer findAddressIdByDistrictFullCode(String districtFullCode,String address,String mobile,Integer uid);

}
