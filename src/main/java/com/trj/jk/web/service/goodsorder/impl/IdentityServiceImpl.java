package com.trj.jk.web.service.goodsorder.impl;

import com.trj.jk.web.domain.UserBasic;
import com.trj.jk.web.domain.UserExt;
import com.trj.jk.web.domain.UserExtCriteria;
import com.trj.jk.web.domain.exception.CheckException;
import com.trj.jk.web.mapper.UserBasicMapper;
import com.trj.jk.web.mapper.UserExtMapper;
import com.trj.jk.web.service.IUserService;
import com.trj.jk.web.service.goodsorder.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhangxin on 2017/6/2.
 */
@Service
@Transactional
public class IdentityServiceImpl implements IdentityService {

    @Autowired
    private UserExtMapper userExtMapper;

    @Autowired
    private UserBasicMapper userBasicMapper;

    @Autowired
    private IUserService userService;

    /**
    * @description:净水器初次注册时将身份证正反面照片上传到userext表中,保存成功后验证e签宝账户是否生成，未生成需要生成e签宝账户
    * @author:章欣
    * @Time: 14:08 2017/6/22
    */
    @Override
    public void saveIdentity(UserExt userExt) {
        UserExtCriteria userExtCriteria=new UserExtCriteria();
        userExtCriteria.createCriteria().andUidEqualTo(userExt.getUid());
        List<UserExt> list=userExtMapper.selectByCriteria(userExtCriteria);
        UserExt oldUserExt=null;
        if(null!=list&&list.size()>0){
            oldUserExt=list.get(0);
        }else{
            throw new CheckException("用户不存在！");
        }
        oldUserExt.setIdentityCardFrontImageId(userExt.getIdentityCardFrontImageId());
        oldUserExt.setIdentityCardOppositeImageId(userExt.getIdentityCardOppositeImageId());
        userExtMapper.updateByPrimaryKey(oldUserExt);
        //验证e签宝账户是否生成，未生成需生成e签宝账户
        UserBasic userBasic=userBasicMapper.selectByPrimaryKey(userExt.getUid());
        if(null==userBasic.getEsAccountId()||userBasic.getEsAccountId()<=0){
            userService.esOpenAccount(userBasic.getId());
        }
    }
}
