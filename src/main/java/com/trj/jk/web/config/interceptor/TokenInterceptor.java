package com.trj.jk.web.config.interceptor;

import com.trj.jk.web.annotation.TokenCheck;
import com.trj.jk.web.domain.Token;
import com.trj.jk.web.exception.RRException;
import com.trj.jk.web.service.TokenService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token 验证 拦截器
 * Created by xierongli on 17/8/2.
 */
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenService tokenService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        TokenCheck annotation;
        if(handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(TokenCheck.class);
        }else{
            return true;
        }
        //注解为空则不验证token
        if(annotation == null){
            return true;
        }

        //从header中获取token
        String token = request.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if(StringUtils.isBlank(token)){
            token = request.getParameter("token");
        }

        //token为空
        if(StringUtils.isBlank(token)){
            throw new RRException("token不能为空");
        }
        //查询token信息
        Token tokenEntity = tokenService.queryByToken(token);
        if(tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()){
            throw new RRException("token失效，请重新获取");
        }
        return true;
    }
}
