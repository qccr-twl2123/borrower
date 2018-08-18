package com.trj.jk.web.service;

import com.trj.jk.web.domain.UserExt;

/**
 * Created by xierongli on 17/8/17.
 */
public interface UserExtService {

    int insertSelective(UserExt userExt);


    int updateByPrimaryKey(UserExt userExt);
}
