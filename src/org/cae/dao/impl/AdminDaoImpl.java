package org.cae.dao.impl;

import org.apache.log4j.Logger;
import org.cae.common.DaoResult;
import org.cae.common.Generator;

import static org.cae.common.Util.*;

import org.cae.dao.IAdminDao;
import org.cae.object.dto.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import top.starrysea.kql.clause.WhereType;
import top.starrysea.kql.facede.EntitySqlResult;
import top.starrysea.kql.facede.KumaSqlDao;
import top.starrysea.kql.facede.ListSqlResult;

@Repository("adminDao")
public class AdminDaoImpl implements IAdminDao {

	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private KumaSqlDao kumaSqlDao;

	@Override
	public Logger getLogger() {
		return logger;
	}

	@Override
	public DaoResult getAdminInfoDao(Admin admin) {
		kumaSqlDao.selectMode();
		ListSqlResult theResult = kumaSqlDao.select("1").from(Admin.class)
				.where("admin_useraccount", WhereType.EQUALS, admin.getAdminUseraccount()).endForList(String.class);
		if (theResult.getResult().isEmpty()) {
			return new DaoResult(false, "管理员账号不存在");
		}
		try {
			EntitySqlResult<Admin> adminResult = kumaSqlDao.select("admin_id").from(Admin.class)
					.where("admin_useraccount", WhereType.EQUALS, admin.getAdminUseraccount())
					.where("admin_password", WhereType.EQUALS, md5(admin.getAdminPassword()))
					.endForObject((rs, row) -> Generator.admin(rs.getInt("admin_id")));
			if (isNotNull(adminResult.getResult())) {
				return new DaoResult(true, adminResult.getResult());
			}
		} catch (Exception e) {
			logger.error("有两个相同的管理员账号!", e);
			return new DaoResult(false, e.getMessage());
		}
		return new DaoResult(false, "密码错误");
	}

}
