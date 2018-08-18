package com.trj.jk.web.service.da;

import java.util.List;

import javax.annotation.Resource;

import com.trj.jk.web.util.UtilConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.trj.jk.web.domain.UserBasic;
import com.trj.jk.web.domain.UserBasicCriteria;
import com.trj.jk.web.domain.UserDeviceInfo;
import com.trj.jk.web.domain.UserFaceLog;
import com.trj.jk.web.domain.UserPhonebookInfo;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.domain.exception.CheckException;
import com.trj.jk.web.mapper.UserBasicMapper;
import com.trj.jk.web.mapper.UserDeviceInfoMapper;
import com.trj.jk.web.mapper.UserFaceLogMapper;
import com.trj.jk.web.mapper.UserPhonebookInfoMapper;
import com.trj.jk.web.util.SessionUtil;

/**
 * app端数据采集服务类
 * @author l46001
 *
 */
@Service("dataAcquisitionService")
public class DataAcquisitionServiceImpl implements IDataAcquisitionService{

	private static final Logger	LOG	= LoggerFactory.getLogger(DataAcquisitionServiceImpl.class);
	
	@Resource
	private UserBasicMapper userBasicMapper;
	
	@Resource
	private UserDeviceInfoMapper userDeviceInfoMapper;
	
	@Resource
	private UserPhonebookInfoMapper userPhonebookInfoMapper;
	
	@Resource
	private UserFaceLogMapper userFaceLogMapper;
	
	@Override
	public int addDeviceInfo(UserDeviceInfo deviceInfo,String mobile) {
		Integer uid=(Integer)SessionUtil.getUserLogonInfo();
		if(uid==null && mobile==null){
			throw new CheckException(ErrorMessageConstant.ERR_PARAM_ERROR);
		}
		if(uid==null){
			uid = getUidByMobile(mobile);
		}
		deviceInfo.setUid(uid);
		userDeviceInfoMapper.insertSelective(deviceInfo);
		return deviceInfo.getId();
	}

	@Override
	public int addPhonebookInfo(UserPhonebookInfo userPhonebookInfo,String mobile) {
		Integer uid=(Integer)SessionUtil.getUserLogonInfo();
		if(uid==null && mobile==null){
			throw new CheckException(ErrorMessageConstant.ERR_PARAM_ERROR);
		}
		if(uid==null){
			uid = getUidByMobile(mobile);
		}
		userPhonebookInfo.setUid(uid);
		userPhonebookInfoMapper.insertSelective(userPhonebookInfo);
		return userPhonebookInfo.getId();
	}
	
	private int getUidByMobile(String mobile){
		int uid;
		UserBasicCriteria userBasicCriteria = new UserBasicCriteria();
		userBasicCriteria.createCriteria().andMobileEqualTo(mobile).andTenantEqualTo(UtilConstant.TENANT_JKWEB);
		List<UserBasic> userBasicList = userBasicMapper.selectByCriteria(userBasicCriteria);
		if(userBasicList!=null && !userBasicList.isEmpty()){
			uid=userBasicList.get(0).getId();
		}else{
			throw new CheckException(ErrorMessageConstant.ERR_USERBASIC_ERROR);
		}
		return uid;
	}

	@Override
	public void livingBodyRecord(UserFaceLog userFaceLog) {
		Assert.notNull(userFaceLog, "userFaceLog不可以为空！");
		Assert.notNull(userFaceLog.getStatus(), "调用结果不可以为空！");
		Integer uid=(Integer)SessionUtil.getUserLogonInfo();
		userFaceLog.setUid(uid);
		userFaceLog.setType((byte)2);
		userFaceLogMapper.insertSelective(userFaceLog);
	}
}
