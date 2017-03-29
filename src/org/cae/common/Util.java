package org.cae.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.cae.entity.CallRecord;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Util {

	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	
	@SuppressWarnings("unchecked")
	public static Object toJson(Object target){
		try{
			if(target instanceof Map){
				return JSONObject.fromObject((Map<String,Object>)target);
			}
			else if(target instanceof List){
				return JSONArray.fromObject((List<Map<String,Object>>)target);
			}
			else if(target instanceof CallRecord){
				return JSONObject.fromObject((CallRecord)target);
			}
			else if(target instanceof JSONObject || target instanceof JSONArray){
				return target;
			}
			System.err.println("传入的参数不是map或者list!");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	public static String time2String(Date date){
		return sdf.format(date);
	}

	public static String getNow(){
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
	
	public static boolean isNull(Object object){
		boolean result=false;
		if(object instanceof String){
			String temp=(String) object;
			if(temp!=null&&!temp.equals(""))
				result=true;
			else
				result=false;
		}
		return result;
	}
}
