package org.cae.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.cae.mail.entity.MailMessage;
import org.springframework.validation.BindingResult;

public interface IUserController {

	Map<String,String> getTokenController(HttpSession session);
	
	Map<String,Object> adviceController(MailMessage mailMessage, BindingResult result, HttpSession session);
}
