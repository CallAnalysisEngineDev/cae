package org.cae.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cae.common.Condition;
import org.cae.object.view.in.Song;
import org.cae.security.ShakeHand;
import org.springframework.web.servlet.ModelAndView;

public interface IAdminController {

	void adminLoginView(HttpServletRequest request, HttpServletResponse response);

	Map<String, Object> shakeHand(HttpSession session, ShakeHand shakeHand);

	ModelAndView queryAllSongController(HttpSession session, Condition condition, Song song);

	ModelAndView removeKeyController(HttpSession session);
}
