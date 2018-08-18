package com.trj.jk.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trj.jk.web.domain.AppDownloadDeviceInfo;
import com.trj.jk.web.mapper.AppDownloadDeviceInfoMapper;
import com.trj.jk.web.service.IDeviceService;

@Service
@Transactional
public class DeviceServiceImpl implements IDeviceService{
	
	@Resource
	private AppDownloadDeviceInfoMapper appDownloadDeviceInfoMapper;

	@Override
	public void saveAppDownloadDeviceInfo(AppDownloadDeviceInfo info) {
		appDownloadDeviceInfoMapper.insertSelective(info);
	}

}
