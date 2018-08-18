package com.trj.jk.web.service;

import com.trj.jk.web.model.response.UserContactsRes;

/**
 * Created by xierongli on 17/8/10.
 */
public interface UserContactService {

    UserContactsRes queryForList(Integer uid);
}
