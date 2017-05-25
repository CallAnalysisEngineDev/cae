package org.cae.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;

import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.cae.common.Util;
import org.springframework.stereotype.Component;

@Component("rsa")
public class Rsa implements SecurityAlgorithm {

	private Logger logger=Logger.getLogger(getClass());
	private KeyPair keyPair;
	//公钥秘钥持久化的文件名
	private final static String KEY_PATH = "rsa.key";
	//密钥的长度,越长效率越低、安全性越高
	private final static Integer KEY_LENGTH = 512;
	
	@Override
	public String encrypt(String info) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String decrypt(String encryptInfo) {
		logger.info("收到密文,密文内容为:"+encryptInfo);
		try {
			Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding", "BC");
            cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
            byte[] plainText = cipher.doFinal(Util.base642byte(encryptInfo));
            return new String(plainText);
        } catch (Exception e) {  
        	e.printStackTrace();
        } 
		return null;
	}

	@PostConstruct
	public void generateKey(){
		File keyFile=new File(KEY_PATH);
		if(keyFile.exists()){
			logger.info("已经存在rsa.key,直接导入公钥秘钥");
			readKeyPair();
			return;
		}
		try {
			KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA", new BouncyCastleProvider());
			keyPairGenerator.initialize(KEY_LENGTH, new SecureRandom());
			keyPair=keyPairGenerator.generateKeyPair();
			logger.info("公钥/秘钥生成成功");
			logger.info("当前的公钥是:"+Util.byte2base64(keyPair.getPublic().getEncoded()));
			logger.info("当前的秘钥是:"+Util.byte2base64(keyPair.getPrivate().getEncoded()));
			saveKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	private void readKeyPair(){
		try {
			FileInputStream fis=new FileInputStream(KEY_PATH);
			ObjectInputStream oos = new ObjectInputStream(fis);
			keyPair = (KeyPair) oos.readObject();
			logger.info("当前的公钥是:"+Util.byte2base64(keyPair.getPublic().getEncoded()));
			logger.info("当前的秘钥是:"+Util.byte2base64(keyPair.getPrivate().getEncoded()));
	        oos.close();
	        fis.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	private void saveKeyPair() {
		try {
			FileOutputStream fos=new FileOutputStream(KEY_PATH);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(keyPair);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public String getPublicKey(){
		return Util.byte2base64(keyPair.getPublic().getEncoded());
	}

	@Override
	public void setKey(String key) {
		// TODO Auto-generated method stub
		
	}
}
