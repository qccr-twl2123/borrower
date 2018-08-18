package com.trj.jk.web.service.impl;

import com.trj.jk.web.convert.ObjectConvert;
import com.trj.jk.web.domain.*;
import com.trj.jk.web.mapper.LoanApplyAddressMapper;
import com.trj.jk.web.mapper.LoanApplyProfessionMapper;
import com.trj.jk.web.mapper.UserExtMapper;
import com.trj.jk.web.mapper.UserProfessionMapper;
import com.trj.jk.web.model.request.UserAddressAndCompanyReq;
import com.trj.jk.web.service.LoanApplyAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by xierongli on 17/8/9.
 */
@Service
public class LoanApplyAddressServiceImpl implements LoanApplyAddressService {

    @Autowired
    private LoanApplyAddressMapper loanApplyAddressMapper;
    @Autowired
    private UserProfessionMapper userProfessionMapper;
    @Autowired
    private UserExtMapper userExtMapper;
    @Autowired
    private LoanApplyProfessionMapper loanApplyProfessionMapper;


    @Transactional
    public void insertLoanApplyPersonInfo(UserAddressAndCompanyReq userAddressAndCompanyReq,Integer uid) {
        //插入jk_loan_apply_address居住地址信息
        LoanApplyAddress loanApplyAddress = ObjectConvert.convertObject(userAddressAndCompanyReq,LoanApplyAddress.class);
        loanApplyAddress.setUid(uid);
        loanApplyAddressMapper.insertSelective(loanApplyAddress);
        //插入jk_loan_apply_profession
        LoanApplyProfession loanApplyProfession = ObjectConvert.convertObject(userAddressAndCompanyReq,LoanApplyProfession.class);
        loanApplyProfession.setUid(uid);
        loanApplyProfessionMapper.insertSelective(loanApplyProfession);

        //插入jk_user_profession 公司信息
        UserProfession userProfession = ObjectConvert.convertObject(userAddressAndCompanyReq,UserProfession.class);
        userProfession.setUid(uid);
        userProfessionMapper.insertSelective(userProfession);
        //更新user_ext中用户的居住信息
        UserExt userExt = new UserExt();
        userExt.setResidentialProvince(userAddressAndCompanyReq.getResidentialProvince());
        userExt.setResidentialCity(userAddressAndCompanyReq.getResidentialCity());
        userExt.setResidentialDistrict(userAddressAndCompanyReq.getResidentialDistrict());
        userExt.setResidentialAddress(userAddressAndCompanyReq.getResidentialAddress());
        UserExtCriteria userExtCriteria = new UserExtCriteria();
        userExtCriteria.createCriteria().andUidEqualTo(uid);
        userExtMapper.updateByCriteriaSelective(userExt,userExtCriteria);
    }
}
