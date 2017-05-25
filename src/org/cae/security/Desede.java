package org.cae.security;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.cae.common.Util;
import org.springframework.stereotype.Component;

@Component("3des")
public class Desede implements SecurityAlgorithm {
	
	private Key key;
	
	public void setKey(String key){
		try {   
			//对比DES  
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");    
            this.key = keyFactory.generateSecret(new DESedeKeySpec(key.getBytes("UTF-8")));    
        } catch (Exception e) {
        	e.printStackTrace();
        } 
	}
	
	@Override
	public String encrypt(String info) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String decrypt(String encryptInfo) {
		byte[] temp=Util.base642byte(encryptInfo);
		Cipher cipher;    
        byte[] byteFina = null;    
        try {
        	cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");    
            cipher.init(Cipher.DECRYPT_MODE, key,SecureRandom.getInstance("SHA1PRNG"));    
            byteFina = cipher.doFinal(temp);   
            return new String(byteFina,"UTF-8");
        } catch (Exception e) {    
            e.printStackTrace();   
        } finally {    
            cipher = null;    
        } 
        return null;
	}

	@Override
	public String getPublicKey() {
		// TODO Auto-generated method stub
		return null;
	}
}
