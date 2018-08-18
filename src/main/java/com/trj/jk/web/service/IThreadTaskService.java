package com.trj.jk.web.service;

public interface IThreadTaskService {

	/**
	 * 异步执行任务
	 * 
	 * @param task
	 */
	public void asyncExecute(final Task task);

	public interface Task {
		public String getName();

		public void doExecute();
	}
}
