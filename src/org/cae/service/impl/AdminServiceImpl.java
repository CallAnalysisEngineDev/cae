package org.cae.service.impl;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.cae.common.DaoResult;
import org.cae.common.ServiceResult;
import org.cae.dao.IAdminDao;
import org.cae.entity.Admin;
import org.cae.security.Desede;
import org.cae.security.SecurityAlgorithm;
import org.cae.security.ShakeHand;
import org.cae.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service("adminService")
public class AdminServiceImpl implements IAdminService {

	private ConcurrentHashMap<Integer, SecurityAlgorithm> keys=new ConcurrentHashMap<Integer, SecurityAlgorithm>();
	@Autowired
	private IAdminDao adminDao;
	@Resource(name="rsa")
	private SecurityAlgorithm rsa;

	@Override
	public ServiceResult loginService(ShakeHand shakeHand) {
		ServiceResult theResult = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			Map<String,Object> map=mapper.readValue(shakeHand.getMessage(), Map.class);
			String key=rsa.decrypt((String)map.get("k"));
			SecurityAlgorithm desede=new Desede(key);
			desede.setKey(key);
			String userAccount=desede.decrypt((String)map.get("u"));
			String password=desede.decrypt((String)map.get("p"));
			DaoResult daoResult=adminDao.getAdminInfoDao(new Admin(userAccount, password));
			if(daoResult.isSuccessed()){
				Admin admin=(Admin) daoResult.getResult();
				keys.put(admin.getAdminId(), desede);
			}
			theResult=new ServiceResult(daoResult);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return theResult;
	}
	
	@Override
	public String getPublicKeyService() {
		return rsa.getPublicKey();
	}
	
	@Override
	public void setKeyService(Integer userId,ShakeHand shakeHand) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeKeyService(Integer userId) {
		// TODO Auto-generated method stub
		
	}
}
