package org.cae.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class CallAspect {
	
	@Before("execution(* org.cae.dao.impl.CallDaoImpl.*(..))")
	public void beforeCallDao(){
		System.out.println("test");
	}
}
