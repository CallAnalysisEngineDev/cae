package org.cae.controller.impl;

import java.util.Map;

import javax.validation.Valid;
import javax.servlet.http.HttpSession;

import static org.cae.common.Util.getFieldErrors;
import static org.cae.common.Util.getCharId;

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
	@RequestMapping(value="/token",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, String> getTokenController(HttpSession session) {
		Map<String,String> theResult=Generator.hashMap();
		String token=getCharId(10);
		theResult.put("token", token);
		session.setAttribute("token", token);
		return theResult;
	}
	
	@Override
	@RequestMapping(value="/advice",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> adviceController(@Valid MailMessage mailMessage, BindingResult bindingResult, HttpSession session) {
		if(bindingResult.hasErrors()){
			Map<String,Object> result=Generator.hashMap();
			result.put("errInfo", getFieldErrors(bindingResult));
			return result;
		}
		String token=(String) session.getAttribute("token");
		if(!mailMessage.getToken().equals(token)){
			Map<String,Object> result=Generator.hashMap();
			result.put("errInfo", "token令牌错误");
			return result;
		}
		return userService.adviceService(mailMessage).toMap();
	}
}
