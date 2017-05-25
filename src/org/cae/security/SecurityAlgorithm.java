package org.cae.security;

public interface SecurityAlgorithm {

	final static Integer ASK_PUBKEY = 1;
	
	final static Integer ENCTYPT_KEY = 2;
	
	String encrypt(String info);
	
	String decrypt(String encryptInfo);
	
	String getPublicKey();
	
	void setKey(String key);
}
