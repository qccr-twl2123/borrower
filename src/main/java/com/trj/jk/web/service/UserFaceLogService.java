package com.trj.jk.web.service;

import com.trj.jk.web.domain.UserFaceLog;

/**
 * Created by xierongli on 17/10/25.
 */
public interface UserFaceLogService {

    int insert(UserFaceLog userFaceLog);


    UserFaceLog generate(Integer uid, Integer type,String result,Integer status);
}
