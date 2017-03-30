package org.cae.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cae.dao.ICallDao;
import org.cae.dao.impl.CallDaoImpl;
import org.cae.entity.CallRecord;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CallTest {

	private ICallDao callDao;
	
	@SuppressWarnings("resource")
	@Before
	public void init(){
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		callDao=(ICallDao) ctx.getBean("callDao");
	}

	@Test
	public void test(){
		callDao.getCallDao(null);
	}
}
