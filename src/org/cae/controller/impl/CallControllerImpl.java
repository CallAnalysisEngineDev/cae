package org.cae.controller.impl;

import org.cae.controller.ICallController;
import org.cae.service.ICallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CallControllerImpl implements ICallController {

	@Autowired
	private ICallService callService;
}
