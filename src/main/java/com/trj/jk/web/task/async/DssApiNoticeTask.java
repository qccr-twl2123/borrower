package com.trj.jk.web.task.async;


import com.google.gson.Gson;
import com.trj.jk.web.service.IThreadTaskService;
import com.trj.jk.web.util.HttpClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class DssApiNoticeTask implements IThreadTaskService.Task{

	public static final Logger logger = LoggerFactory.getLogger(DssApiNoticeTask.class);

	private String url;

	private Map<String, String> param;


	public DssApiNoticeTask(String url, Map<String, String> param) {
		super();
		this.url = url;
		this.param = param;
	}

	@Override
	public String getName() {
		return getClass().getName();
	}

	@Override
	public void doExecute() {
		Map<String, String> reqHeadMap = new HashMap<String, String>();
		reqHeadMap.put("Content-Type", "application/json");
		String postData = new Gson().toJson(param);
		String body = HttpClientUtils.httpPost(url, postData, null, reqHeadMap, null);
		logger.info("通知博士盾打分结果:{}",body);
	}

}
