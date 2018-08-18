package com.trj.jk.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.trj.jk.web.domain.ThirdpartyRequestResponseLog;
import com.trj.jk.web.mapper.ThirdpartyRequestResponseLogMapper;
import com.trj.jk.web.service.ILogService;

public class LogServiceImpl implements ILogService{
	@Autowired
	private ThirdpartyRequestResponseLogMapper thirdpartyRequestResponseMapper;

	@Override
	public void saveRequestLog(ThirdpartyRequestResponseLog log) {
		thirdpartyRequestResponseMapper.insertSelective(log);
	}

}
