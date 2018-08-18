package com.trj.jk.web.service;

import com.trj.jk.web.domain.UserProfession;
import com.trj.jk.web.model.response.UserAddressAndCompanyRes;

/**
 * Created by xierongli on 17/8/9.
 */
public interface UserProfessionService {

    int insert(UserProfession userProfession);

    UserAddressAndCompanyRes queryUserProfession(Integer uid,Integer loanApplyId);
}
