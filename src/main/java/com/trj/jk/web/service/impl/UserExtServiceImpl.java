package com.trj.jk.web.service.impl;

import com.trj.jk.web.domain.UserExt;
import com.trj.jk.web.mapper.UserExtMapper;
import com.trj.jk.web.service.UserExtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xierongli on 17/8/17.
 */
@Service
public class UserExtServiceImpl implements UserExtService {

    @Autowired
    private UserExtMapper userExtMapper;


    @Override
    public int insertSelective(UserExt userExt) {
        return userExtMapper.insertSelective(userExt);
    }

    @Override
    public int updateByPrimaryKey(UserExt userExt) {
        return userExtMapper.updateByPrimaryKey(userExt);
    }
}
