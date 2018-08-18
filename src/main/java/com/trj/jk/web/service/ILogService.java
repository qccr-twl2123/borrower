package com.trj.jk.web.service;

import com.trj.jk.web.domain.ThirdpartyRequestResponseLog;

public interface ILogService {
	void saveRequestLog(ThirdpartyRequestResponseLog log);
}
