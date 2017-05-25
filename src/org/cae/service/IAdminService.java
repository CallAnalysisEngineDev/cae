package org.cae.service;

import org.cae.common.ServiceResult;
import org.cae.security.ShakeHand;

public interface IAdminService {

	ServiceResult loginService(ShakeHand shakeHand);
	
	String getPublicKeyService();
	
	void setKeyService(Integer userId, ShakeHand shakeHand);
	
	void removeKeyService(Integer userId);
}
