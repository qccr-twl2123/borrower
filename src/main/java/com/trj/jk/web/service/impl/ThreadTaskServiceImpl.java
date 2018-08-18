package com.trj.jk.web.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.trj.jk.web.service.IThreadTaskService;

@Service("threadTaskService")
public class ThreadTaskServiceImpl implements IThreadTaskService {

	private static final Logger		LOG	= LoggerFactory.getLogger(ThreadTaskServiceImpl.class);

	@Resource
	private ThreadPoolTaskExecutor	taskExecutor;

	@Override
	public void asyncExecute(final Task task) {
		taskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				LOG.info("异步任务({})开始执行", task.getName());
				long start = System.currentTimeMillis();
				task.doExecute();
				LOG.info("异步任务({})执行结束,耗时={}ms.", task.getName(), (System.currentTimeMillis() - start));
			}
		});
	}

}
