package org.cae.dao;

import org.cae.common.DaoResult;
import org.cae.object.dto.Admin;

public interface IAdminDao extends IBaseDao {

	DaoResult getAdminInfoDao(Admin admin);
}
