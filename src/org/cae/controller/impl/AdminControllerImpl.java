package org.cae.controller.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.cae.common.Condition;
import org.cae.common.ServiceResult;
import org.cae.controller.IAdminController;
import org.cae.entity.Song;
import org.cae.security.SecurityAlgorithm;
import org.cae.security.ShakeHand;
import org.cae.service.IAdminService;
import org.cae.service.ICallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminControllerImpl implements IAdminController {

	@Autowired
	private IAdminService adminService;
	@Autowired
	private ICallService callService;
	
	@Override
	@RequestMapping("/shakeHand")
	@ResponseBody
	public Map<String, Object> shakeHand(HttpSession session,ShakeHand shakeHand) {
		Map<String,Object> theResult=new HashMap<String,Object>();
		Integer type=shakeHand.getType();
		if(type==SecurityAlgorithm.ASK_PUBKEY){
			theResult.put("publicKey", adminService.getPublicKeyService());
		}
		else if(type==SecurityAlgorithm.ENCTYPT_KEY){
			ServiceResult serviceResult=adminService.loginService(shakeHand);
			if(serviceResult.isSuccessed()){
				
			}
		}
		return theResult;
	}

	@Override
	public ModelAndView queryAllSongController(Condition condition, Song song) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView removeKeyController(HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}

}
