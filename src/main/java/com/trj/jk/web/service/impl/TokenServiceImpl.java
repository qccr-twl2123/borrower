package com.trj.jk.web.service.impl;

import com.trj.jk.web.domain.Token;
import com.trj.jk.web.domain.TokenCriteria;
import com.trj.jk.web.mapper.TokenMapper;
import com.trj.jk.web.service.TokenService;
import com.trj.jk.web.validator.Assert;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by xierongli on 17/8/2.
 */
@Service("tokenService")
public class TokenServiceImpl implements TokenService {
    //1个小时后过期
    private final static int EXPIRE = 3600 * 1;

    @Autowired
    private TokenMapper tokenMapper;

    @Override
    public int insert(Token token) {
       return  tokenMapper.insertSelective(token);
    }

    @Override
    public Token queryByToken(String token) {
        TokenCriteria tokenCriteria = new TokenCriteria();
        tokenCriteria.createCriteria().andTokenEqualTo(token);
        List<Token> tokenList = tokenMapper.selectByCriteria(tokenCriteria);
        if(CollectionUtils.isNotEmpty(tokenList)){return tokenList.get(0);}
        return null;
    }

    @Override
    public String createToken(String verifyCode) {
        //生成一个token
        String token = UUID.randomUUID().toString();
        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //判断是否生成过token
        Token tokenEntity = new Token();
        tokenEntity = new Token();
        tokenEntity.setToken(token);
        tokenEntity.setImgVerificationCode(verifyCode);
        tokenEntity.setExpireTime(expireTime);
        //保存token
        insert(tokenEntity);
        return token;
    }

    @Override
    public Token queryByMobile(String mobile) {
        if(StringUtils.isBlank(mobile)){return null;}
        TokenCriteria tokenCriteria = new TokenCriteria();
        tokenCriteria.createCriteria().andMobileEqualTo(mobile);
        List<Token> tokenList = tokenMapper.selectByCriteria(tokenCriteria);
        if(CollectionUtils.isNotEmpty(tokenList)){return tokenList.get(0);}
        return null;
    }

    @Override
    public void delete(String token) {
        TokenCriteria tokenCriteria = new TokenCriteria();
        tokenCriteria.createCriteria().andTokenEqualTo(token);
        tokenMapper.deleteByCriteria(tokenCriteria);
    }

    @Override
    public Boolean queryByCodeAndToken(String verificationCode, String token) {
        TokenCriteria tokenCriteria = new TokenCriteria();
        tokenCriteria.createCriteria().andTokenEqualTo(token).andImgVerificationCodeEqualTo(verificationCode);
        List<Token> tokenList = tokenMapper.selectByCriteria(tokenCriteria);
        if(CollectionUtils.isNotEmpty(tokenList)){return true;}
        return false;
    }

    @Override
    public int update(Token token, TokenCriteria tokenCriteria) {
        return tokenMapper.updateByCriteriaSelective(token,tokenCriteria);
    }

    @Override
    public Token queryByMobileCodeAndToken(String token, String mobileVerificationCode) {
        TokenCriteria tokenCriteria = new TokenCriteria();
        tokenCriteria.createCriteria().andTokenEqualTo(token).andMobileVerificationCodeEqualTo(mobileVerificationCode);
        List<Token> tokenList = tokenMapper.selectByCriteria(tokenCriteria);
        if(CollectionUtils.isNotEmpty(tokenList)){return tokenList.get(0);}
        return null;
    }

    @Override
    public int updateToken(String mobile, String token) {
        Token tokenModel = queryByToken(token);
        Assert.isNull(tokenModel,"口令不合法");
        //过期时间
        Date expireTime = new Date(new Date().getTime() + EXPIRE * 1000);
        tokenModel.setMobile(mobile);
        TokenCriteria tokenCriteria = new TokenCriteria();
        tokenCriteria.createCriteria().andTokenEqualTo(token);
        return tokenMapper.updateByCriteria(tokenModel, tokenCriteria);
    }
}
