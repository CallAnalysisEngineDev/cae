package org.cae.aspect;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class CallAspect {
	
	private Logger logger=Logger.getLogger(this.getClass().getName());
	
	@Before("execution(* org.cae.*.*.*.*(..))")
	public void beforeCallDao(JoinPoint jp){
		logger.log(Level.INFO, "执行["+jp.getTarget().getClass().getSimpleName()+"."+jp.getSignature().getName()+"]方法");
	}
}
