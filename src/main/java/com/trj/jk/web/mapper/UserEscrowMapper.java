package com.trj.jk.web.mapper;


import com.trj.jk.web.domain.UserEscrow;
import com.trj.jk.web.domain.UserEscrowCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserEscrowMapper extends BaseMapper<UserEscrow, UserEscrowCriteria, Integer> {
}