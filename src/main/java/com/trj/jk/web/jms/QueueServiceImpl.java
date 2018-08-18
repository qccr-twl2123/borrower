package com.trj.jk.web.jms;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.trj.message.service.api.SmsSendParam;

@Service("queueService")
public class QueueServiceImpl implements IQueueService {

	@Resource
	private JmsTemplate	jmsTemplate;

	@Resource
	private Gson		gson;

	@Override
	public void sendSms(final SmsSendParam param) {
		// TODO Auto-generated method stub
		jmsTemplate.send("sendSms", new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(gson.toJson(param));
			}
		});
	}

}
