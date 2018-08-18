package com.trj.jk.web.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.trj.jk.web.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trj.commons.result.Result;
import com.trj.jk.web.service.fraud.IFraudService;

@RestController()
@RequestMapping("/fraud")
public class FraudController {

	private static final Logger	LOG	= LoggerFactory.getLogger(FraudController.class);

	@Autowired
	private IFraudService		baiqishiFraudService;

	/**
	 * 白骑士接口
	 * @param tokenKey
	 * @param eventType
	 * @param request
	 * @return
	 */
	@PostMapping("/riskDecision")
	public Result<Boolean> riskDecision(String tokenKey, String eventType, HttpServletRequest request) {
		LOG.info("白骑士调用接口开始执行....");
		LOG.info(String.format("参数：tokenKey=%s,eventType=%s", tokenKey,eventType));
		Result<Boolean> result = new Result<Boolean>();
		Boolean data = Boolean.TRUE;
		try {

			Map<String, String> params = new HashMap<String, String>();

			Enumeration<String> names = request.getParameterNames();

			while (names.hasMoreElements()) {
				String key = names.nextElement();
				if (!"tokenKey".equals(key) && !"eventType".equals(key)) {
					String value = request.getParameter(key);
					params.put(key, value);
				}
			}

            params.put("uid", null != SessionUtil.getUserLogonInfo() ? SessionUtil.getUserLogonInfo().toString() : null);
            LOG.info("params={}", params);

			baiqishiFraudService.riskDecision(tokenKey, eventType, params);
		} catch (Exception ex) {
			LOG.error(ex.getMessage(), ex);
			result.setSuccess(false);
			result.setMessage(ex.getMessage());
			data = Boolean.FALSE;
		}
		result.setData(data);
		LOG.info(String.format("白骑士调用接口返回：%s", result.toString()));
		return result;
	}

}
