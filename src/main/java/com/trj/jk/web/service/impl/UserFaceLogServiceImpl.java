package com.trj.jk.web.service.impl;

import com.trj.jk.web.domain.UserFaceLog;
import com.trj.jk.web.mapper.UserFaceLogMapper;
import com.trj.jk.web.service.UserFaceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xierongli on 17/10/25.
 */
@Service
public class UserFaceLogServiceImpl implements UserFaceLogService {

    @Autowired
    private UserFaceLogMapper userFaceLogMapper;

    @Override
    public int insert(UserFaceLog userFaceLog) {
        return userFaceLogMapper.insertSelective(userFaceLog);
    }


    @Override
    public UserFaceLog generate(Integer uid, Integer type, String result,Integer status) {
        UserFaceLog userFaceLog = new UserFaceLog();
        userFaceLog.setUid(uid);
        userFaceLog.setType(type.byteValue());
        userFaceLog.setResult(result);
        userFaceLog.setStatus(status.byteValue());
        return userFaceLog;
    }

}
