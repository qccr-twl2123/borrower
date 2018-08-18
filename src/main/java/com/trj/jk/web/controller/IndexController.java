package com.trj.jk.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trj.commons.result.InvokerResult;
import com.trj.commons.result.Result;
import com.trj.crm.service.api.IFooService;
import com.trj.crm.service.api.IJbpmService;
import com.trj.jk.web.domain.AppDownloadDeviceInfo;
import com.trj.jk.web.service.IDeviceService;
import com.trj.jk.web.util.SessionUtil;

import cz.mallat.uasparser.OnlineUpdater;
import cz.mallat.uasparser.UASparser;
import cz.mallat.uasparser.UserAgentInfo;

@RestController
public class IndexController {

	private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);
	
	private static  UASparser uaParser = null;
	
	@Autowired
	private IFooService fooService;
	
	@Autowired
	private IJbpmService jbpmService;
	@Autowired
	private IDeviceService deviceService;

	@Value("${app.pkg.url.ios}")
	private String iosPkgUrl;	
	
	@Value("${app.pkg.url.android}")
	private String andriodPkgUrl;
	
	static {
		try{
			uaParser =new UASparser(OnlineUpdater.getVendoredInputStream());
		}catch(IOException e){
			LOG.error(e.getMessage(),e);

		}
	}	

	@RequestMapping(value={"/hello"},method=RequestMethod.POST)
	public Result<String> hello(HttpServletRequest request) {

		LOG.info("visit IndexController hello() method");

		String name = request.getParameter("name");

		if (name != null) {
			// session.setAttribute("name", name);
			SessionUtil.setUserLogonInfo(name);
		} else {
			// name = (String) session.getAttribute("name");
			name = (String) SessionUtil.getUserLogonInfo();
		}

		SessionUtil.invalidSession();

		Result<String> result = new Result<String>();
		result.setData(name);

		return result;
	}

	@RequestMapping("/userInfo")
	public Result<String> userInfo() {
		Result<String> result = new Result<String>();
		result.setData("投融家");
		return result;
	}

	@RequestMapping("/fooUserInfo")
	public Result<String> fooUserInfo() {
		Result<String> result = new Result<String>();
		result.setData("投保家");
		
		InvokerResult ret = fooService.say("投融家");
		LOG.info("===============" + ret.getData());
		return result;
	}
	
		@RequestMapping("/jbpmInfo")
	public Result<String> jbpmInfo() {
		Result<String> result = new Result<String>();
		
		InvokerResult ret = jbpmService.startProcess(28, "1");
		return result;
	}	
	
	@RequestMapping("/pkg")
	public void getPkgUrl(HttpServletRequest request, HttpServletResponse response) {

		try {
			String userAgentStr = request.getHeader("User-Agent");
			String channel = request.getParameter("from");
			if(StringUtils.isNotBlank(userAgentStr)){
				LOG.info(userAgentStr);
				AppDownloadDeviceInfo deviceInfo = new AppDownloadDeviceInfo();
				if(StringUtils.isNotBlank(channel)){
					deviceInfo.setChannel(channel);
				}
				UserAgentInfo userAgentInfo = IndexController.uaParser.parse(userAgentStr);
				System.out.println(userAgentInfo.getOsFamily());
				if("Android".equalsIgnoreCase(userAgentInfo.getOsFamily())){
					deviceInfo.setOsFamily(Byte.valueOf("0"));
					deviceService.saveAppDownloadDeviceInfo(deviceInfo);
					response.sendRedirect(andriodPkgUrl);
				}else if("IOS".equalsIgnoreCase(userAgentInfo.getOsFamily())) {
					deviceInfo.setOsFamily(Byte.valueOf("1"));
					deviceService.saveAppDownloadDeviceInfo(deviceInfo);
					
					response.sendRedirect(iosPkgUrl);

				}
			}				
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
		
	}		

}
