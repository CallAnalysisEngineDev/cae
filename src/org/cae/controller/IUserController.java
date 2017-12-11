package org.cae.controller;

import java.util.Map;

import org.cae.mail.entity.MailMessage;
import org.springframework.validation.BindingResult;

public interface IUserController {

	Map<String, Object> adviceController(MailMessage mailMessage, BindingResult result);
}
