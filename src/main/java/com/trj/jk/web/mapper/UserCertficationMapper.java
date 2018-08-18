package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.UserCertfication;
import com.trj.jk.web.domain.UserCertficationCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserCertficationMapper extends BaseMapper<UserCertfication, UserCertficationCriteria, Integer> {
}