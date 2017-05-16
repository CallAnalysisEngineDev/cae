package org.cae.security;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component("3des")
public class Desede implements SecurityAlgorithm {

	private ConcurrentHashMap<String, String> keys=new ConcurrentHashMap<String, String>();
	
	@Override
	public String encrypt(String info) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String decrypt(String encryptInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean setKey(String userId,String key){
		// TODO Auto-generated method stub
		return true;
	}
	
	public void removeKey(String userId){
		// TODO Auto-generated method stub
		keys.remove(userId);
	}
}
