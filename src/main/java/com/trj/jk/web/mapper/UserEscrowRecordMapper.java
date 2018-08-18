package com.trj.jk.web.mapper;


import com.trj.jk.web.domain.UserEscrowRecord;
import com.trj.jk.web.domain.UserEscrowRecordCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserEscrowRecordMapper extends BaseMapper<UserEscrowRecord, UserEscrowRecordCriteria, Integer> {
}