package org.cae.test;

import org.cae.dao.IAdminDao;
import org.cae.object.dto.Admin;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AdminTest {

	private IAdminDao adminDao;

	@SuppressWarnings("resource")
	@Before
	public void init() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		adminDao=(IAdminDao) ctx.getBean("adminDao");
	}

	@Test
	public void getAdminInfoDao() {
		Admin admin=new Admin.Builder().adminUseraccount("kuma").adminPassword("fsxjl2017").build();
		System.out.println(adminDao.getAdminInfoDao(admin).isSuccessed());
	}

}
