package org.cae.security;

import java.security.KeyPair;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component("rsa")
public class Rsa implements SecurityAlgorithm {

	private KeyPair keyPair;
	
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

	@PostConstruct
	public void generateKey(){
		// TODO Auto-generated method stub
	}
	
	public String getPublicKey(){
		// TODO Auto-generated method stub
		return null;
	}
}
