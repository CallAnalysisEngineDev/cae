package org.cae.service.impl;

import org.cae.common.ServiceResult;
import org.cae.mail.entity.MailMessage;

import static org.cae.common.Util.toJson;

import org.cae.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Override
	public ServiceResult adviceService(MailMessage mailMessage) {
		jmsTemplate.send(session -> session.createTextMessage(toJson(mailMessage)));
		ServiceResult result = new ServiceResult();
		result.setSuccessed(true);
		return result;
	}

}
