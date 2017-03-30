package org.cae.controller.impl;

import java.util.logging.Logger;

import org.cae.controller.ICallController;
import org.cae.service.ICallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CallControllerImpl implements ICallController {

	private Logger logger=Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private ICallService callService;
}
