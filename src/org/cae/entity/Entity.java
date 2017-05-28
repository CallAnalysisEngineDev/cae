package org.cae.entity;

import org.cae.common.Util;

public class Entity {

	public String toString(){
		return Util.toJson(this);
	}
}
