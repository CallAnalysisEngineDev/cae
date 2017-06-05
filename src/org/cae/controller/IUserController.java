package org.cae.controller;

import java.util.Map;

import org.cae.mail.entity.MailMessage;

public interface IUserController {

	Map<String,Object> adviceController(MailMessage mailMessage);
}
