package com.trj.jk.web.service.impl;

import com.trj.jk.web.convert.ObjectConvert;
import com.trj.jk.web.domain.UserContacts;
import com.trj.jk.web.domain.UserContactsCriteria;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.mapper.UserContactsMapper;
import com.trj.jk.web.model.response.RelationRes;
import com.trj.jk.web.model.response.UserContactsRes;
import com.trj.jk.web.service.UserContactService;
import com.trj.jk.web.validator.Assert;
import com.xiaoleilu.hutool.util.CollectionUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xierongli on 17/8/10.
 */
@Service
public class UserContactServiceImpl implements UserContactService {

    @Autowired
    private UserContactsMapper userContactsMapper;

    @Override
    public UserContactsRes queryForList(Integer uid) {
        UserContactsRes userContactsRes = new UserContactsRes();
        UserContactsCriteria userContactsCriteria = new UserContactsCriteria();
        userContactsCriteria.createCriteria().andUidEqualTo(uid);
        List<UserContacts> userContactsList = userContactsMapper.selectByCriteria(userContactsCriteria);
        if(CollectionUtil.isEmpty(userContactsList)){
            userContactsRes.setFamily(new RelationRes());
            userContactsRes.setFriend(new RelationRes());
            userContactsRes.setColleague(new RelationRes());
            return userContactsRes;}


        RelationRes family = null;
        RelationRes friend = null;
        RelationRes colleague = null;
        for(UserContacts userContacts: userContactsList){
            RelationRes relationRes = ObjectConvert.convertObject(userContacts,RelationRes.class);
            switch (userContacts.getRelation()){
                case "同事":
                    if(colleague == null){
                        colleague = relationRes;
                    }
                    break;
                case "朋友":
                    if(friend == null){
                        friend = relationRes;
                    }
                    break;
                default:
                    if(family == null){
                        family = relationRes;
                    }
            }
        }
        userContactsRes.setFamily(family==null?new RelationRes():family);
        userContactsRes.setFriend(friend==null?new RelationRes():friend);
        userContactsRes.setColleague(colleague==null?new RelationRes():colleague);
        return userContactsRes;
    }



}
