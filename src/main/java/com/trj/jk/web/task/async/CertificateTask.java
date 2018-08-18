package com.trj.jk.web.task.async;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.trj.jk.web.domain.UserBasic;
import com.trj.jk.web.domain.UserCertfication;
import com.trj.jk.web.domain.entity.authentication.bi.BiResult;
import com.trj.jk.web.domain.entity.constant.Constant;
import com.trj.jk.web.domain.entity.constant.Status;
import com.trj.jk.web.mapper.UserBasicMapper;
import com.trj.jk.web.mapper.UserCertficationMapper;
import com.trj.jk.web.service.IAuthenticationService;
import com.trj.jk.web.service.IThreadTaskService;
import com.trj.jk.web.util.HttpClientUtils;
import com.trj.jk.web.util.SpringUtil;

public class CertificateTask implements IThreadTaskService.Task{
	
	private String url;
	private Object bean;
	private Integer id;
	private Integer uid;
	private Byte type;

	public CertificateTask(String url, Object bean, Integer id, Integer uid, Byte type) {
		super();
		this.url = url;
		this.bean = bean;
		this.id = id;
		this.uid=uid;
		this.type=type;
	}

	@Override
	public String getName() {
				
		return getClass().getName();
	}

	@Override
	public void doExecute() {
		IAuthenticationService authenticationService = (IAuthenticationService)SpringUtil.getBean("authenticationService");
		authenticationService.certificate(bean, url, id, uid, type);

	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}	
	
	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}	

}
