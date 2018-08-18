package com.trj.jk.web.controller;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.trj.jk.web.service.IAuthenticationService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPythonCall {
	
	@Autowired
	private IAuthenticationService authenticationService;

	@Test
	public void test() {
		try{
			authenticationService.pythonCall();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
