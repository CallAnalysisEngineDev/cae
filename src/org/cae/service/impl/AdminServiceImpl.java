package org.cae.service.impl;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.cae.common.ServiceResult;
import org.cae.dao.IAdminDao;
import org.cae.security.SecurityAlgorithm;
import org.cae.security.ShakeHand;
import org.cae.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service("adminService")
public class AdminServiceImpl implements IAdminService {

	private ConcurrentHashMap<String, String> keys=new ConcurrentHashMap<String, String>();
	@Autowired
	private IAdminDao adminDao;
	@Resource(name="3des")
	private SecurityAlgorithm desede;
	@Resource(name="rsa")
	private SecurityAlgorithm rsa;

	@Override
	public ServiceResult loginService(ShakeHand shakeHand) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Map<String,Object> map=mapper.readValue(shakeHand.getMessage(), Map.class);
			String key=rsa.decrypt((String)map.get("key"));
			desede.setKey(key);
			String userAccount=desede.decrypt((String)map.get("useraccount"));
			String password=desede.decrypt((String)map.get("password"));
			System.out.println(userAccount+":"+password);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
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
