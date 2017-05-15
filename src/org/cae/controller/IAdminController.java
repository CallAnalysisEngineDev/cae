package org.cae.controller;

import java.util.Map;

import org.cae.common.Condition;
import org.cae.common.ShakeHand;
import org.cae.entity.Admin;
import org.cae.entity.Song;
import org.springframework.web.servlet.ModelAndView;

public interface IAdminController {

	Map<String,Object> shakeHand(ShakeHand shakeHand);
	
	String loginController(Admin admin);
	
	ModelAndView queryAllSongController(Condition condition, Song song);
}
