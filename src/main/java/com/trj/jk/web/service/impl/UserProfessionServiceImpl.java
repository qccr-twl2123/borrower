package com.trj.jk.web.service.impl;

import com.trj.jk.web.convert.ObjectConvert;
import com.trj.jk.web.domain.*;
import com.trj.jk.web.mapper.LoanApplyAddressMapper;
import com.trj.jk.web.mapper.UserExtMapper;
import com.trj.jk.web.mapper.UserProfessionMapper;
import com.trj.jk.web.model.response.UserAddressAndCompanyRes;
import com.trj.jk.web.service.UserProfessionService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xierongli on 17/8/9.
 */
@Service
public class UserProfessionServiceImpl implements UserProfessionService {

    @Autowired
    private UserProfessionMapper userProfessionMapper;
    @Autowired
    private UserExtMapper userExtMapper;
    @Autowired
    private LoanApplyAddressMapper loanApplyAddressMapper;

    @Override
    public int insert(UserProfession userProfession) {
        return userProfessionMapper.insert(userProfession);
    }

    @Override
    public UserAddressAndCompanyRes queryUserProfession(Integer uid,Integer loanApplyId) {
        //查询用户的公司信息
        UserProfessionCriteria userProfessionCriteria = new UserProfessionCriteria();
        userProfessionCriteria.createCriteria().andUidEqualTo(uid);
        userProfessionCriteria.setOrderByClause("id desc");
        List<UserProfession> userProfessionList = userProfessionMapper.selectByCriteria(userProfessionCriteria);
        UserAddressAndCompanyRes userAddressAndCompanyRes = new UserAddressAndCompanyRes();
        if(CollectionUtils.isNotEmpty(userProfessionList)){
            userAddressAndCompanyRes = ObjectConvert.convertObject(userProfessionList.get(0),UserAddressAndCompanyRes.class);
        }
         //查询用户居住地址,先从loan_apply_address取,再从user_ext中取
        LoanApplyAddressCriteria loanApplyAddressCriteria = new LoanApplyAddressCriteria();
        loanApplyAddressCriteria.createCriteria().andLoanApplyIdEqualTo(loanApplyId);
        loanApplyAddressCriteria.setOrderByClause("id desc");
        List<LoanApplyAddress> loanApplyAddressList = loanApplyAddressMapper.selectByCriteria(loanApplyAddressCriteria);
        if(CollectionUtils.isNotEmpty(loanApplyAddressList)){
            LoanApplyAddress loanApplyAddress = loanApplyAddressList.get(0);
            userAddressAndCompanyRes.setResidentialProvince(loanApplyAddress.getResidentialProvince());
            userAddressAndCompanyRes.setResidentialCity(loanApplyAddress.getResidentialCity());
            userAddressAndCompanyRes.setResidentialDistrict(loanApplyAddress.getResidentialDistrict());
            userAddressAndCompanyRes.setResidentialAddress(loanApplyAddress.getResidentialAddress());
        }else{
            UserExtCriteria userExtCriteria = new UserExtCriteria();
            userExtCriteria.createCriteria().andUidEqualTo(uid);
            List<UserExt> userExtList = userExtMapper.selectByCriteria(userExtCriteria);
            if(CollectionUtils.isNotEmpty(userExtList)){
                userAddressAndCompanyRes.setResidentialProvince(userExtList.get(0).getResidentialProvince());
                userAddressAndCompanyRes.setResidentialCity(userExtList.get(0).getResidentialCity());
                userAddressAndCompanyRes.setResidentialDistrict(userExtList.get(0).getResidentialDistrict());
                userAddressAndCompanyRes.setResidentialAddress(userExtList.get(0).getResidentialAddress());
            }
        }
        return userAddressAndCompanyRes;
    }
}
