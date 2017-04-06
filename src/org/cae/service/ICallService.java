package org.cae.service;

import java.util.List;

import org.cae.common.Condition;
import org.cae.common.ServiceResult;
import org.cae.entity.CallRecord;
import org.cae.entity.Song;

public interface ICallService {

	ServiceResult querySongForHomepageService();
	
	ServiceResult queryAllCallService(Condition condition,CallRecord callRecord);
	
	ServiceResult queryCallService(CallRecord callRecord);
	
	ServiceResult addCallService(CallRecord callRecord);
	
	ServiceResult removeCallService(CallRecord callRecord);
	
	ServiceResult removeCallService(List<CallRecord> callRecords);
	
	ServiceResult queryAllSongService(Condition condition,Song song);
	
	ServiceResult addSongService(Song song);
	
	ServiceResult removeSongService(Song song);
}
