package org.cae.controller.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.cae.common.ServiceResult;
import org.cae.controller.ICallController;
import org.cae.entity.CallRecord;
import org.cae.service.ICallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CallControllerImpl implements ICallController {

	private Logger logger=Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private ICallService callService;
	
	@RequestMapping("/test")
	@ResponseBody
	public Map<String,Object> test(CallRecord callRecord){
		System.out.println(callRecord.getCallId());
		Map<String,Object> map=new HashMap<>();
		map.put("aaa", "111");
		map.put("bbb", "222");
		return map;
	}

	@Override
	@RequestMapping("/home")
	@ResponseBody
	public Map<String, Object> homepage() {
		ServiceResult result=callService.queryCallForHomepageService();
		Map<String, Object> theResult;
		if(result.isSuccessed()){
			theResult=(Map<String, Object>) result.getResult();
			theResult.put("successed", result.isSuccessed());
		}
		else{
			theResult=new HashMap<String,Object>();
			theResult.put("successed", result.isSuccessed());
			theResult.put("errInfo", result.getErrInfo());
		}
		return theResult;
	}
}
