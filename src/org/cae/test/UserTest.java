package org.cae.test;

import org.cae.mail.entity.Mail;
import org.cae.mail.entity.MailMessage;
import org.cae.service.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {

	private IUserService service;
	
	@Before
	public void init(){
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		service=(IUserService) ctx.getBean("userService");
	}
	
	@Test
	public void adviceService() {
		MailMessage mailMessage=new MailMessage();
		mailMessage.setType(1);
		Mail mail=new Mail();
		mail.setTitle("测试邮件主题111");
		mail.setContent("测试邮件内容111");
		mailMessage.setMail(mail);
		System.out.println(service.adviceService(mailMessage));
	}

}
