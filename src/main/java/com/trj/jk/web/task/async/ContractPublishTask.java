package com.trj.jk.web.task.async;


import java.util.Map;

import com.trj.jk.web.service.IThreadTaskService;
import com.trj.jk.web.service.IUserService;
import com.trj.jk.web.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContractPublishTask implements IThreadTaskService.Task{

	public static final Logger logger = LoggerFactory.getLogger(ContractPublishTask.class);

	private String url;
	
	private Map<String, String> param;
	

	public ContractPublishTask(String url, Map<String, String> param) {
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
		IUserService authenticationService = (IUserService)SpringUtil.getBean("userService");
		String result = authenticationService.doContractPublish(url, param);
		logger.info("合同发布CRM异步生成返回结果:{}",result);
		
	}

}
