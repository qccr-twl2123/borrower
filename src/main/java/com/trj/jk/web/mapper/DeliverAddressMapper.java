package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.DeliverAddress;
import com.trj.jk.web.domain.DeliverAddressCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeliverAddressMapper extends BaseMapper<DeliverAddress, DeliverAddressCriteria, Integer> {
}