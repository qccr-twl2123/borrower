package com.trj.jk.web.service;


import com.trj.jk.web.model.request.UserAddressAndCompanyReq;

/**
 * Created by xierongli on 17/8/9.
 */
public interface LoanApplyAddressService {

    void insertLoanApplyPersonInfo(UserAddressAndCompanyReq userAddressAndCompanyReq,Integer uid);

}
