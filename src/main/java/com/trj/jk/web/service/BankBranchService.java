package com.trj.jk.web.service;

import com.trj.jk.web.domain.BankBranch;

import java.util.List;

/**
 * Created by xierongli on 17/9/30.
 */
public interface BankBranchService {

    List<BankBranch> queryByCityNameAndName(String cityName, String bankBranchName);
}
