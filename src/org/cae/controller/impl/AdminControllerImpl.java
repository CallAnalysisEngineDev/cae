package org.cae.controller.impl;

import java.util.Map;

import org.cae.common.Condition;
import org.cae.common.ShakeHand;
import org.cae.controller.IAdminController;
import org.cae.entity.Admin;
import org.cae.entity.Song;
import org.cae.service.IAdminService;
import org.cae.service.ICallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminControllerImpl implements IAdminController {

	@Autowired
	private IAdminService adminService;
	@Autowired
	private ICallService callService;
	
	@Override
	public Map<String, Object> shakeHand(ShakeHand shakeHand) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String loginController(Admin admin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView queryAllSongController(Condition condition, Song song) {
		// TODO Auto-generated method stub
		return null;
	}

}
