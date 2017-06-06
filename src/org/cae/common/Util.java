package org.cae.common;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.cae.entity.Entity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {

	private static SimpleDateFormat dateSdf=new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat timeSdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static String toJson(Object target){
		ObjectMapper mapper=new ObjectMapper();
		try{
			return mapper.writeValueAsString(target);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	public static String date2String(Date date){
		return dateSdf.format(date);
	}
	
	public static String time2String(Date date){
		return timeSdf.format(date);
	}

	public static String getNowDate(){
		return date2String(new Date());
	}
	
	public static String getNowTime(){
		return time2String(new Date());
	}
	
	public static String getBefore(long time){
		return time2String(new Date(System.currentTimeMillis()-time));
	}
	
	public static String getCharId(){
		return getCharId(new String(), 10);
	}
	
	public static String getCharId(int size){
		return getCharId(new String(),size);
	}
	
	public static String getCharId(String pre,int size){
		StringBuffer theResult=new StringBuffer();
		theResult.append(pre);
		String a = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for(int i=0;i<size-pre.length();i++){
			int rand =(int)(Math.random() * a.length());
			theResult.append(a.charAt(rand));
		}
		return theResult.toString();
	}
	
	public static short getRandom(int randomRange){
		Random random=new Random();
		return (short) random.nextInt(randomRange);
	}
	
	public static boolean isNotNull(Object object){
		boolean result=false;
		if(object==null)
			return result;
		if(object instanceof String){
			String temp=(String) object;
			if(temp!=null&&!temp.equals(""))
				result=true;
			else
				result=false;
		}
		else if(object instanceof Entity){
			result=(object!=null?true:false);
		}
		else if(object instanceof List){
			List list=(List) object;
			if(list.size()>0)
				result=true;
			else
				result=false;
		}
		return result;
	}
	
	public static void logStackTrace(Logger logger,StackTraceElement[] stackTrace){
		String stackInfo="";
		for(StackTraceElement element:stackTrace){
			stackInfo+=element+"\n";
		}
		logger.error(stackInfo);
	}
	
	public static String md5(String str){
		try{
			MessageDigest md=MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			return new BigInteger(1,md.digest()).toString(16);
		}catch(Exception ex){
			return null;
		}
	}
	
	public static String byte2base64(byte[] deStr) {
		BASE64Encoder encoder=new BASE64Encoder();
		return encoder.encode(deStr);
	}

	public static byte[] base642byte(String enStr){
		try {
			BASE64Decoder decoder=new BASE64Decoder();
			return decoder.decodeBuffer(enStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getFieldErrors(BindingResult bindingResult){
		List<String> list=new ArrayList<String>();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		for(FieldError fieldError:fieldErrors){
			list.add(fieldError.getDefaultMessage());
		}
		return toJson(list);
	}
}
