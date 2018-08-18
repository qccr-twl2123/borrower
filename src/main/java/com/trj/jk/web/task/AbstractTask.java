package com.trj.jk.web.task;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public abstract class AbstractTask {

	protected Logger	logger	= LoggerFactory.getLogger(getClass());

	@Value("${app.task.autoTaskIp}")
	private String		autoTaskIp;

	public abstract String getName();

	public void execute() {
		if (canRun()) {
			logger.info("定时任务({})开始执行", getName());
			try {
				doExecute();
				logger.info("定时任务({})执行结束", getName());
			} catch (Exception ex) {
				logger.info("定时任务({})执行失败", getName());
			}
		}
	}

	protected abstract void doExecute();

	public boolean canRun() {
		boolean result = false;

		List<String> localAddress = getAllLocalAddress();

		logger.info("localAddress=" + localAddress + ",autoTaskIp=" + autoTaskIp);

		if (localAddress.contains(autoTaskIp)) {
			result = true;
		}

		return result;
	}

	private final List<String> getAllLocalAddress() {
		List<String> ret = new ArrayList<String>();
		try {
			Enumeration<NetworkInterface> n = NetworkInterface.getNetworkInterfaces();
			if (n != null) {
				while (n.hasMoreElements()) {
					NetworkInterface e = n.nextElement();
					Enumeration<InetAddress> a = e.getInetAddresses();
					if (a != null) {
						while (a.hasMoreElements()) {
							InetAddress address = a.nextElement();
							if (!address.isLoopbackAddress() && !address.getHostAddress().contains(":")) {
								ret.add(address.getHostAddress());
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		return ret;
	}

}
