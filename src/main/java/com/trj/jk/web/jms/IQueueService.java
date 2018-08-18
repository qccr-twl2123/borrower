package com.trj.jk.web.jms;

import com.trj.message.service.api.SmsSendParam;

public interface IQueueService {

	public void sendSms(final SmsSendParam param);

}
