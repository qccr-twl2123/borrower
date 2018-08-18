package com.trj.jk.web.service.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.trj.jk.web.service.IThreadTaskService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadTaskServiceImplTest {

	@Resource
	private IThreadTaskService threadTaskService;

	@Test
	public void testAsyncExecute() {
		threadTaskService.asyncExecute(new IThreadTaskService.Task() {
			
			@Override
			public String getName() {
				return getClass().getName();
			}
			
			@Override
			public void doExecute() {
				System.out.println("#############################");
			}
		});
	}

}
