package org.cae.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class CallAspect {
	
	private Logger logger=Logger.getLogger(this.getClass());
	
	@Before("execution(* org.cae.*.*.*.*(..))")
	public void beforeCallDao(JoinPoint jp){
		logger.info("执行["+jp.getTarget().getClass().getSimpleName()+"."+jp.getSignature().getName()+"]方法");
	}
}
