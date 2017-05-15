package org.cae.test;

import static org.junit.Assert.*;

import org.cae.service.IAdminService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AdminTest {

	private IAdminService adminService;
	
	@SuppressWarnings("resource")
	@Before
	public void init(){
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		adminService=(IAdminService) ctx.getBean("adminService");
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
