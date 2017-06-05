package org.cae.service.impl;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.cae.common.Condition;
import org.cae.common.DaoResult;
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
	@Autowired
	private ISongDao songDao;

	@Override
	public ServiceResult querySongForHomepageService() {
		ServiceResult result;
		DaoResult daoResult=songDao.getSongForHomepageDao();
		if(!daoResult.isSuccessed()){
			result=new ServiceResult(daoResult);
			return result;
		}
		result=new ServiceResult();
		Map<String,Object> map=(Map<String, Object>) daoResult.getResult();
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
		ServiceResult result=new ServiceResult();
		condition.setPageLimit(IConstant.CALL_SEARCH_LIMIT);
		DaoResult daoResult=callDao.getAllCallDao(condition, callRecord);
		if(!daoResult.isSuccessed()){
			result=new ServiceResult(daoResult);
			return result;
		}
		List<CallRecord> callList =(List<CallRecord>) daoResult.getResult();
		if(callList.size()==0){
			result=new ServiceResult();
			result.setSuccessed(false);
			result.setErrInfo("查询结果为空");
			return result;
		}
		int totalPage=0;
		daoResult=callDao.getCallCountDao(condition, callRecord);
		int count=(int) daoResult.getResult();
		if(count%condition.getPageLimit()==0)
			totalPage=count/condition.getPageLimit();
		else
			totalPage=(count/condition.getPageLimit())+1;
		
		result.setSuccessed(true);
		result.setResult(callList);
		result.setNowPage(condition.getPage());
		result.setTotalPage(totalPage);
		return result;
	}
	
	@Override
	public ServiceResult queryCallService(CallRecord callRecord) {
		ServiceResult result=new ServiceResult();
		DaoResult daoResult=callDao.getCallDao(callRecord);
		if(!daoResult.isSuccessed()){
			result=new ServiceResult(daoResult);
			return result;
		}
		CallRecord cr=(CallRecord) daoResult.getResult();
		if(cr==null){
			daoResult=songDao.getSongDao(callRecord.getSong());
			if(!daoResult.isSuccessed()){
				result=new ServiceResult(daoResult);
				return result;
			}
			Song song=(Song) daoResult.getResult();
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
		DaoResult daoResult=songDao.getAllSongDao(condition, song);
		if(!daoResult.isSuccessed()){
			result=new ServiceResult(daoResult);
			return result;
		}
		List<Song> songList=(List<Song>) daoResult.getResult();
		if(songList.size()==0){
			result=new ServiceResult();
			result.setSuccessed(false);
			result.setErrInfo("查询结果为空");
			return result;
		}
		
		int totalPage=0;
		daoResult=songDao.getSongCountDao(condition, song);
		if(!daoResult.isSuccessed()){
			result=new ServiceResult(daoResult);
			return result;
		}
		int count=(int) daoResult.getResult();
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
		return new ServiceResult(songDao.saveSongDao(song));
	}

	@Override
	public ServiceResult removeSongService(Song song) {
		return new ServiceResult(songDao.deleteSongDao(song));
	}
	
}
