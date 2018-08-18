package com.trj.jk.web.config.interceptor;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.trj.commons.result.Result;
import com.trj.jk.web.domain.entity.login.AuthResult;
import com.trj.jk.web.util.JsonUtils;
import com.trj.jk.web.util.SessionUtil;

public class SecurityInterceptor implements HandlerInterceptor {

	private static final Logger LOG = LoggerFactory.getLogger(SecurityInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		Serializable userLogonInfo = SessionUtil.getUserLogonInfo();
		if (userLogonInfo == null) {
			LOG.info("=================preHandle=================" + false);
			AuthResult<Object> result = new AuthResult<Object>();
			result.setCode(403);
			result.setSuccess(false);
			response.setHeader("Content-Type","application/json; charset=utf-8" );
			response.getWriter().write(JsonUtils.objectToJsonString(result));

			return false;
		}

		LOG.info("=================preHandle=================" + true);

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// TODO Auto-generated method stub

	}

}
