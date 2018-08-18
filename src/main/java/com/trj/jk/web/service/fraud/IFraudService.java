package com.trj.jk.web.service.fraud;

import java.util.Map;

public interface IFraudService {

	public void riskDecision(String tokenKey, String eventType, Map<String, String> params);

}
