package com.trj.jk.web.mapper;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.trj.jk.web.domain.UserBankCard;
import com.trj.jk.web.domain.UserBankCardCriteria;
import com.trj.mybatis.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserBankCardMapper extends BaseMapper<UserBankCard, UserBankCardCriteria, Integer> {
	
	public List<Map<String, Object>> selectBankCardInfoByUid(Map<String, Object> paramMap,PageBounds pageBounds);

	public List<Map<String, Object>> selectBankCardInfoByUid(Map<String, Object> paramMap);
}