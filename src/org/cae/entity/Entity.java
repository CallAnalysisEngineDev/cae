package org.cae.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Entity {

	public String toString(){
		String result=null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			result=mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}
}
