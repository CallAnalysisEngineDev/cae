package org.cae.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.cae.common.Condition;
import org.cae.entity.Song;
import org.cae.security.ShakeHand;
import org.springframework.web.servlet.ModelAndView;

public interface IAdminController {

	Map<String,Object> shakeHand(HttpSession session,ShakeHand shakeHand);
	
	ModelAndView queryAllSongController(HttpSession session, Condition condition, Song song);
	
	ModelAndView removeKeyController(HttpSession session);
}
