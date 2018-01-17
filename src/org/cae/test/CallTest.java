package org.cae.test;

import java.util.ArrayList;
import java.util.List;

import org.cae.common.Condition;
import org.cae.common.ServiceResult;
import org.cae.object.dto.CallRecord;
import org.cae.object.dto.Song;
import org.cae.service.ICallService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CallTest {

	private ICallService callService;

	@SuppressWarnings("resource")
	@Before
	public void init() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		callService = (ICallService) ctx.getBean("callService");
	}

	@Test
	public void querySongForHomepageService() {
		ServiceResult result = callService.querySongForHomepageService();
		System.out.println(result);
	}

	@Test
	public void queryAllSongService() {
		Condition condition = new Condition();
		condition.setPage(1);
		Song song = new Song.Builder().build();
		ServiceResult result = callService.queryAllSongService(condition, song);
		System.out.println(result);
	}

	@Test
	public void queryAllCallService() {
		Condition condition = new Condition();
		condition.setPage(1);
		condition.setPageLimit(10);
		System.out.println(callService.queryAllCallService(condition, new CallRecord.Builder().build()));
	}
	
	@Test
	public void queryCallService() {
		Song song = new Song.Builder().songId("jier").build();
		CallRecord callRecord = new CallRecord.Builder().song(song).build();
		ServiceResult result = callService.queryCallService(callRecord);
		System.out.println(result);
	}

	@Test
	public void addSongService() {
		Song song=new Song.Builder().songId("test").songName("asd").songSellTime("2011-11-11 11:11:11").songOwner("me").songCover("asd").songCreateTime("2011-11-11 11:11:11").songClick(11).songLastModifyTime("2011-11-11 11:11:11").songVideo((short)1).build();
		System.out.println(callService.addSongService(song));
	}
	
	@Test
	public void removeSongService() {
		Song song=new Song.Builder().songId("test").build();
		System.out.println(callService.removeSongService(song));
	}
	
	@Test
	public void addCallService() {
		CallRecord callRecord = new CallRecord.Builder()
				.song(new Song.Builder().songId("jier").build())
				.callSource("/aqours/asdasdasd.html").callVersion((short) 25)
				.build();
		ServiceResult result = callService.addCallService(callRecord);
		System.out.println(result);
	}

	@Test
	public void removeCallService() {
		CallRecord callRecord = new CallRecord.Builder().callId("CR-tUdoWRb")
				.build();
		ServiceResult result = callService.removeCallService(callRecord);
		System.out.println(result);
	}

	@Test
	public void removeCallsService() {
		List<CallRecord> callRecords = new ArrayList<CallRecord>();
		CallRecord callRecord = new CallRecord.Builder().callId("CR-CvwweaH")
				.build();
		callRecords.add(callRecord);
		callRecord = new CallRecord.Builder().callId("CR-xNWVIZ0").build();
		callRecords.add(callRecord);
		ServiceResult result = callService.removeCallService(callRecords);
		System.out.println(result);
	}
}
