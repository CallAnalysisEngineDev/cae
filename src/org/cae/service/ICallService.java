package org.cae.service;

import java.util.List;

import org.cae.common.Condition;
import org.cae.common.ServiceResult;
import org.cae.entity.CallRecord;
import org.cae.entity.Song;

public interface ICallService {

	public ServiceResult queryAllCallService(Condition condition,CallRecord callRecord);
	
	public ServiceResult queryCallService(CallRecord callRecord);
	
	public ServiceResult addCallService(CallRecord callRecord);
	
	public ServiceResult removeCallService(CallRecord callRecord);
	
	public ServiceResult removeCallService(List<CallRecord> callRecords);
	
	public ServiceResult modifyCallService(CallRecord callRecord);
	
	public ServiceResult queryAllSongService(Condition condition,Song song);
	
	public ServiceResult addSongService(Song song);
	
	public ServiceResult removeSongService(Song song);
}
