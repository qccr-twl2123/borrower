package com.trj.jk.web.service;

import com.trj.jk.web.domain.Token;
import com.trj.jk.web.domain.TokenCriteria;

/**
 * Created by xierongli on 17/8/2.
 */
public interface TokenService {

    int insert(Token token);

    Token queryByToken(String token);

    String createToken(String verifyCode);

    Token queryByMobile(String mobile);

    void delete(String token);

    Boolean queryByCodeAndToken(String verificationCode, String token);

    int update(Token token, TokenCriteria tokenCriteria);

    Token queryByMobileCodeAndToken(String token, String mobileVerificationCode);

    int updateToken(String mobile, String token);
}
