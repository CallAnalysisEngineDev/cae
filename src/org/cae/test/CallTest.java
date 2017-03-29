package org.cae.test;

import static org.junit.Assert.*;

import org.cae.dao.ICallDao;
import org.cae.dao.impl.CallDaoImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CallTest {

	private ICallDao callDao;
	
	@Before
	public void init(){
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		callDao=(ICallDao) ctx.getBean("callDao");
	}
	

}
