package com.trj.jk.web.task.async;

import com.trj.jk.web.service.IThreadTaskService;
import com.trj.jk.web.service.zhima.ZhimaCreditScoreService;
import com.trj.jk.web.util.SpringUtil;

public class ZhimaCreditScoreTask implements IThreadTaskService.Task{

	private String name;
	
	private String certNo;
	
	private int score;
	
	public ZhimaCreditScoreTask(String name, String certNo, int score) {
		this.name=name;
		this.certNo=certNo;
		this.score=score;
	}

	@Override
	public String getName() {
		return getClass().getName();
	}

	@Override
	public void doExecute() {
		ZhimaCreditScoreService zhimaCreditScoreService=(ZhimaCreditScoreService)SpringUtil.getBean("zhimaCreditScoreService");
		zhimaCreditScoreService.queryAdmittance(name, certNo, score);
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
