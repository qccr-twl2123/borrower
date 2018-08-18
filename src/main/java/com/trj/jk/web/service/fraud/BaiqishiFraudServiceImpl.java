package com.trj.jk.web.service.fraud;

import com.trj.jk.web.domain.UserFraudLog;
import com.trj.jk.web.mapper.UserFraudLogMapper;
import com.trj.jk.web.util.HttpClientUtils;
import com.trj.jk.web.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("baiqishiFraudService")
public class BaiqishiFraudServiceImpl implements IFraudService {

    private static final Logger LOG = LoggerFactory.getLogger(BaiqishiFraudServiceImpl.class);

    @Value("${app.fraud.baiqishi.domain}")
    private String domain;
    @Value("${app.fraud.baiqishi.partnerId}")
    private String partnerId;
    @Value("${app.fraud.baiqishi.verifyKey}")
    private String verifyKey;
    @Value("${app.fraud.baiqishi.appId}")
    private String appId;

    @Autowired
    private UserFraudLogMapper userFraudLogMapper;

    @Override
    public void riskDecision(String tokenKey, String eventType, Map<String, String> params) {
        // TODO Auto-generated method stub
        Assert.notNull(tokenKey, "tokenKey不能为空");
        Assert.notNull(eventType, "事件类型不能为空");

        UserFraudLog userFraudLog = new UserFraudLog();
        userFraudLog.setTokenKey(tokenKey);
        userFraudLog.setEventType(eventType);
        userFraudLog.setMobile(params.get("mobile"));
        userFraudLog.setPlatform(params.get("platform"));
        userFraudLog.setUid(null != params.get("uid") ? Integer.valueOf(params.get("uid")) : null);
        userFraudLog.setCtime(new Date());
        userFraudLog.setMtime(new Date());
        userFraudLogMapper.insert(userFraudLog);

        Map<String, String> paraMap = generateBaseParaMap();
        paraMap.put("eventType", eventType);
        paraMap.put("tokenKey", tokenKey);
        if (params != null && !params.isEmpty()) {
            paraMap.putAll(params);
        }

        String url = domain + "/services/decision";
        String postData = JsonUtils.objectToJsonString(paraMap);

        LOG.info("url={},params={},postData={}", url, paraMap, postData);

        String resultStr = HttpClientUtils.httpPost(domain + "/services/decision", postData, null, null, "UTF-8");
        LOG.info("resultStr={}", resultStr);
    }

    private Map<String, String> generateBaseParaMap() {
        Map<String, String> result = new HashMap<String, String>();
        result.put("partnerId", partnerId);
        result.put("verifyKey", verifyKey);
        result.put("appId", appId);
        return result;
    }

}
