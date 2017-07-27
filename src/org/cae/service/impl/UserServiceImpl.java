package org.cae.service.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.cae.common.ServiceResult;
import static org.cae.common.Util.toJson;
import org.cae.mail.entity.MailMessage;
import org.cae.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Override
	public ServiceResult adviceService(final MailMessage mailMessage) {
		jmsTemplate.send(new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(toJson(mailMessage));
			}

		});
		ServiceResult result = new ServiceResult();
		result.setSuccessed(true);
		return result;
	}

}
