package org.cae.test;

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
	public void querySongForHomepageService(){
		ServiceResult result=callService.querySongForHomepageService();
		System.out.println(result);
	}
	
	@Test
	public void queryAllSongService(){
		Condition condition=new Condition();
		condition.setPage(1);
		Song song=new Song.Builder()
					.songName("")
					.build();
		ServiceResult result=callService.queryAllSongService(condition, song);
		System.out.println(result);
	}
	
	@Test
	public void queryCallService(){
		Song song=new Song.Builder()
						.songName("gkgn")
						.build();
		CallRecord callRecord=new CallRecord.Builder()
									.song(song)
									.build();
		ServiceResult result=callService.queryCallService(callRecord);
		System.out.println(result);
	}
	
	@Test
	public void addCallService(){
		CallRecord callRecord=new CallRecord.Builder()
								.song(new Song.Builder().songName("gugu").build())
								.callSource("/aqours/海岸通りで待ってるよ.html")
								.callVersion((short)25)
								.build();
		ServiceResult result=callService.addCallService(callRecord);
		System.out.println(result);
	}
	
	@Test
	public void removeCallService(){
		CallRecord callRecord=new CallRecord.Builder()
								.callId("CR-gq5UvSY")
								.build();
		ServiceResult result=callService.removeCallService(callRecord);
		System.out.println(result);
	}
	
	@Test
	public void removeCallsService(){
		List<CallRecord> callRecords=new ArrayList<CallRecord>();
		CallRecord callRecord=new CallRecord.Builder()
									.callId("CR-bGaS4yp")
									.build();
		callRecords.add(callRecord);
		callRecord=new CallRecord.Builder()
									.callId("CR-0NPVFFF")
									.build();
		callRecords.add(callRecord);
		ServiceResult result=callService.removeCallService(callRecords);
		System.out.println(result);
	}
}
