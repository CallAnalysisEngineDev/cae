package org.cae.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.cae.common.Condition;
import org.cae.common.ServiceResult;
import org.cae.entity.CallRecord;
import org.cae.entity.Song;
import org.cae.service.ICallService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CallTest {

	private ICallService callService;
	
	@SuppressWarnings("resource")
	@Before
	public void init(){
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		callService=(ICallService) ctx.getBean("callService");
	}

	@Test
	public void queryCallForHomepageService(){
		ServiceResult result=callService.queryCallForHomepageService();
		System.out.println(result.getResult());
	}
	
	@Test
	public void queryAllSongService(){
		Condition condition=new Condition();
		condition.setPage(1);
		Song song=new Song();
		song.setSongName("");
		ServiceResult result=callService.queryAllSongService(condition, song);
		System.out.println(result);
	}
	
	@Test
	public void queryCallService(){
		CallRecord callRecord=new CallRecord();
		Song song=new Song("gkgn");
		callRecord.setSong(song);
		ServiceResult result=callService.queryCallService(callRecord);
		System.out.println(result);
	}
	
	@Test
	public void addCallService(){
		CallRecord callRecord=new CallRecord();
		callRecord.setSong(new Song("1"));
		callRecord.setCallSource("/test11");
		callRecord.setCallVersion((short)11);
		ServiceResult result=callService.addCallService(callRecord);
		System.out.println(result);
	}
	
	@Test
	public void removeCallService(){
		CallRecord callRecord=new CallRecord("CR-gq5UvSY");
		ServiceResult result=callService.removeCallService(callRecord);
		System.out.println(result);
	}
	
	@Test
	public void removeCallsService(){
		List<CallRecord> callRecords=new ArrayList<CallRecord>();
		CallRecord callRecord=new CallRecord("CR-7kXOjZd");
		callRecords.add(callRecord);
		callRecord=new CallRecord("CR-dUfbpIk");
		callRecords.add(callRecord);
		callRecord=new CallRecord("CR-TBl3k6G");
		callRecords.add(callRecord);
		ServiceResult result=callService.removeCallService(callRecords);
		System.out.println(result);
	}
}
