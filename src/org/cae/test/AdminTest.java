package org.cae.test;

import static org.junit.Assert.*;

import org.cae.dao.IAdminDao;
import org.cae.entity.Admin;
import org.cae.service.IAdminService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AdminTest {

	private IAdminService adminService;
	private IAdminDao adminDao;

	@SuppressWarnings("resource")
	@Before
	public void init() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		adminService = (IAdminService) ctx.getBean("adminService");
		adminDao=(IAdminDao) ctx.getBean("adminDao");
	}

	@Test
	public void getAdminInfoDao() {
		Admin admin=new Admin.Builder().adminUseraccount("kuma").adminPassword("fsxjl2017").build();
		adminDao.getAdminInfoDao(admin);
	}

}
