package org.cae.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class CallAspect {
	
	private Logger logger=Logger.getLogger(this.getClass().getName());
	
	@Before("execution(* org.cae.dao.impl.CallDaoImpl.*(..))")
	public void beforeCallDao(){
		System.out.println("test");
	}
}
