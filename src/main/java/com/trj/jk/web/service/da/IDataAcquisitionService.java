package com.trj.jk.web.service.da;

import com.trj.jk.web.domain.UserDeviceInfo;
import com.trj.jk.web.domain.UserFaceLog;
import com.trj.jk.web.domain.UserPhonebookInfo;

/**
 * app端数据采集服务接口
 * @author l46001
 *
 */
public interface IDataAcquisitionService {

	/**
	 * 添加 app端采集用户的设备信息
	 * @param deviceInfo
	 * @return
	 */
	int addDeviceInfo(UserDeviceInfo deviceInfo,String mobile);
	
	
	/**
	 * 添加 app端采集用户的手机通讯录信息
	 * @param userPhonebookInfo
	 * @return
	 */
	int addPhonebookInfo(UserPhonebookInfo userPhonebookInfo,String mobile);

	/**
	 * 记录app端取活体记录
	 * @param userFaceLog
	 */
	void livingBodyRecord(UserFaceLog userFaceLog);
}
