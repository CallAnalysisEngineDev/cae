package org.cae.service;

import org.cae.common.ServiceResult;
import org.cae.mail.entity.MailMessage;

public interface IUserService {

	ServiceResult adviceService(MailMessage mailMessage);
}
