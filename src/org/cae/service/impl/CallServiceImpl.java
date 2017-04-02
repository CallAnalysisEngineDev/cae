package org.cae.service.impl;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.cae.common.Condition;
import org.cae.common.ServiceResult;
import org.cae.dao.ICallDao;
import org.cae.entity.CallRecord;
import org.cae.entity.Song;
import org.cae.service.ICallService;
import org.springframework.stereotype.Service;

@Service("callService")
public class CallServiceImpl implements ICallService {
	
	private Logger logger=Logger.getLogger(this.getClass().getName());
	
	@Resource(name="callDao")
	private ICallDao callDao;
	@Resource(name="callLucene")
	private ICallDao callLucene;

	@Override
	public ServiceResult queryCallForHomepageService() {
		ServiceResult result=new ServiceResult();
		Map<String,Object> map=callDao.getCallForHomepageDao();
		if(((List)(map.get("red"))).size()==0||((List)(map.get("newest"))).size()==0){
			logger.log(Level.SEVERE, "热门歌曲或最新修改歌曲为空!");
			result.setSuccessed(false);
			result.setErrInfo("结果为空");
		}
		else{
			result.setSuccessed(true);
			result.setResult(map);
		}
		return result;
	}
	
	@Override
	public ServiceResult queryAllCallService(Condition condition,
			CallRecord callRecord) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ServiceResult queryCallService(CallRecord callRecord) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ServiceResult addCallService(CallRecord callRecord) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ServiceResult removeCallService(CallRecord callRecord) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ServiceResult removeCallService(List<CallRecord> callRecords) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ServiceResult modifyCallService(CallRecord callRecord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult queryAllSongService(Condition condition, Song song) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult addSongService(Song song) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult removeSongService(Song song) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
