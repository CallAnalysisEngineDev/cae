package org.cae.controller.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.cae.common.Condition;
import org.cae.common.ServiceResult;
import org.cae.controller.IAdminController;
import org.cae.entity.Admin;
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
		else if(type==SecurityAlgorithm.ENCTYPT_DATA){
			ServiceResult serviceResult=adminService.loginService(shakeHand);
			if(serviceResult.isSuccessed()){
				Admin admin=(Admin) serviceResult.getResult();
				session.setAttribute("adminId", admin.getAdminId());
			}
			theResult=serviceResult.toMap();
		}
		return theResult;
	}
	
	@Override
	@RequestMapping(value="/all")
	public ModelAndView queryAllSongController(HttpSession session, Condition condition, Song song) {
		Integer adminId=(Integer) session.getAttribute("adminId");
		if(adminId==null){
			ModelAndView mav=new ModelAndView();
			mav.setViewName("login");
		}
		ModelAndView mav=new ModelAndView();
		mav.setViewName("WEB-INF/index");
		return mav;
	}

	@Override
	public ModelAndView removeKeyController(HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}

}
