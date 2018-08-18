package com.trj.jk.web.service.impl;

import com.trj.jk.web.domain.UserBankCard;
import com.trj.jk.web.domain.UserBankCardCriteria;
import com.trj.jk.web.mapper.UserBankCardMapper;
import com.trj.jk.web.service.UserBankCardService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xierongli
 * @version $$Id: trj-jk-web, v 0.1 2018/5/8 下午4:29 mark1xie Exp $$
 */
@Service
public class UserBankCardServiceImpl implements UserBankCardService {

    @Autowired
    private UserBankCardMapper userBankCardMapper;

    @Override
    public UserBankCard findByCardId(String cardId) {
        UserBankCardCriteria userBankCardCriteria = new UserBankCardCriteria();
        userBankCardCriteria.createCriteria().andCardIdEqualTo(cardId).andStatusEqualTo(new Byte("2"));
        List<UserBankCard> userBankCardList = userBankCardMapper.selectByCriteria(userBankCardCriteria);
        if(CollectionUtils.isNotEmpty(userBankCardList)){return userBankCardList.get(0);}
        return null;
    }
}
