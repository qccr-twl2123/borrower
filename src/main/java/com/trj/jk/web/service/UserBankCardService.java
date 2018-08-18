package com.trj.jk.web.service;

import com.trj.jk.web.domain.UserBankCard;

/**
 * ${DESCRIPTION}
 *
 * @author xierongli
 * @version $$Id: trj-jk-web, v 0.1 2018/5/8 下午4:29 mark1xie Exp $$
 */
public interface UserBankCardService {

    UserBankCard findByCardId(String cardId);
}
