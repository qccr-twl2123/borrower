package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.Token;
import com.trj.jk.web.domain.TokenCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TokenMapper extends BaseMapper<Token, TokenCriteria, Integer> {
}