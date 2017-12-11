package org.cae.controller.impl;

import java.util.Map;

import javax.validation.Valid;

import static org.cae.common.Util.getFieldErrors;

import org.cae.common.Generator;
import org.cae.controller.IUserController;
import org.cae.mail.entity.MailMessage;
import org.cae.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserControllerImpl implements IUserController {

	@Autowired
	private IUserService userService;

	@Override
	@RequestMapping(value = "/advice", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> adviceController(@Valid MailMessage mailMessage, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, Object> result = Generator.hashMap();
			result.put("errInfo", getFieldErrors(bindingResult));
			return result;
		}
		return userService.adviceService(mailMessage).toMap();
	}

}
