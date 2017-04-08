package org.cae.service.impl;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.cae.common.Condition;
import org.cae.common.IConstant;
import org.cae.common.ServiceResult;
import org.cae.dao.ICallDao;
import org.cae.dao.ISongDao;
import org.cae.entity.CallRecord;
import org.cae.entity.Song;
import org.cae.service.ICallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("callService")
public class CallServiceImpl implements ICallService {
	
	private Logger logger=Logger.getLogger(this.getClass().getName());
	
	@Resource(name="callDao")
	private ICallDao callDao;
	@Resource(name="callLucene")
	private ICallDao callLucene;
	@Autowired
	private ISongDao songDao;

	@Override
	public ServiceResult querySongForHomepageService() {
		ServiceResult result=new ServiceResult();
		Map<String,Object> map=songDao.getSongForHomepageDao();
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
		ServiceResult result=new ServiceResult();
		CallRecord cr=callDao.getCallDao(callRecord);
		if(cr==null){
			Song song=songDao.getSongDao(callRecord.getSong());
			if(song==null){
				result.setSuccessed(false);
				result.setErrInfo("查询结果为空");
				return result;
			}
			else{
				cr=new CallRecord();
				cr.setSong(song);
			}
		}
		result.setSuccessed(true);
		result.setResult(cr);
		return result;
	}
	
	@Override
	@Transactional
	public ServiceResult addCallService(CallRecord callRecord) {
		return new ServiceResult(callDao.saveCallDao(callRecord));
	}
	
	@Override
	public ServiceResult removeCallService(CallRecord callRecord) {
		return new ServiceResult(callDao.deleteCallDao(callRecord));
	}
	
	@Override
	public ServiceResult removeCallService(List<CallRecord> callRecords) {
		return new ServiceResult(callDao.deleteCallDao(callRecords));
	}

	@Override
	public ServiceResult queryAllSongService(Condition condition, Song song) {
		ServiceResult result=null;
		condition.setPageLimit(IConstant.CALL_SEARCH_LIMIT);
		List<Song> songList=songDao.getAllSongDao(condition, song);
		if(songList.size()==0){
			result=new ServiceResult();
			result.setSuccessed(false);
			result.setErrInfo("查询结果为空");
			return result;
		}
		
		int totalPage=0;
		int count=songDao.getSongCountDao(condition, song);
		if(count%condition.getPageLimit()==0)
			totalPage=count/condition.getPageLimit();
		else
			totalPage=(count/condition.getPageLimit())+1;
		
		result=new ServiceResult();
		result.setSuccessed(true);
		result.setResult(songList);
		result.setNowPage(condition.getPage());
		result.setTotalPage(totalPage);
		return result;
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
