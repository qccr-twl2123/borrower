package com.trj.jk.web.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PrintTask extends AbstractTask {

	@Override
//	@Scheduled(cron = "${app.task.cron.PrintTask}")
	public void execute() {
		super.execute();
	}

	@Override
	public String getName() {
		return "定时打印任务";
	}

	@Override
	protected void doExecute() {
		logger.info(getClass().getName());
	}

}
