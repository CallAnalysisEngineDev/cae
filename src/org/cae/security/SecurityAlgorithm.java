package org.cae.security;

public interface SecurityAlgorithm {

	final static Integer ASK_PUBKEY = 1;
	
	final static Integer ANS_PUBKEY = 2;
	
	final static Integer ENCTYPT_KEY = 3;
	
	final static Integer ANS_SUCCESS_KEY = 4;
	
	String encrypt(String info);
	
	String decrypt(String encryptInfo);
}
