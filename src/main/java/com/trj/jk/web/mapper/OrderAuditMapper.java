package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.OrderAudit;
import com.trj.jk.web.domain.OrderAuditCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderAuditMapper extends BaseMapper<OrderAudit, OrderAuditCriteria, Integer> {
}